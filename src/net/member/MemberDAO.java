package net.member;

import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

import net.member.MemberDTO;
import net.pds.PdsDTO;
import net.utility.*;

public class MemberDAO {
	
	private DBOpen dbopen=null;
	private DBClose dbclose=null;
	
	private Connection con=null;
	private PreparedStatement pstmt=null;
	private StringBuilder sql=null;
	
	public MemberDAO() {
		dbopen=new DBOpen();
		dbclose=new DBClose();
	}
	
	public int duplecateID(String id) {
		ResultSet rs=null;
		int cnt=0;
		
		try {
			con=dbopen.getConnection();
			sql=new StringBuilder();
			
			sql.append(" SELECT COUNT(id) AS cnt");
			sql.append(" FROM member "); 
			sql.append(" WHERE id=?");
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				cnt=rs.getInt("cnt");
			}
		}catch(Exception e) {
			System.out.println("아이디 중복조회 실패 : "+e);
		}finally {
			dbclose.close(con, pstmt, rs);
		}//try end
		
		return cnt;
		
	}//duplecateID() end
	
	public int duplecateEmail(String email) {
		ResultSet rs=null;
		int cnt=0;
		
		try {
			con=dbopen.getConnection();
			sql=new StringBuilder();
			
			sql.append(" SELECT COUNT(email) AS cnt "); 
			sql.append(" FROM member "); 
			sql.append(" WHERE email=? ");
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, email);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				cnt=rs.getInt("cnt");
			}
		}catch(Exception e) {
			System.out.println("아이디 중복조회 실패 : "+e);
		}finally {
			dbclose.close(con, pstmt, rs);
		}//try end
		
		return cnt;
		
	}//duplecateID() end
	
	public int insert(MemberDTO dto) {
		int res=0;
		try {
			con=dbopen.getConnection();	//DB연결
			
			sql = new StringBuilder();
			sql.append(" insert into member(id, passwd, mname, tel, email, zipcode, address1, address2, job, mlevel, mdate) ");
			sql.append(" values(?, ?, ?, ?, ?, ?, ?, ?, ?, 'D1', now())");
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPasswd());
			pstmt.setString(3, dto.getMname());
			pstmt.setString(4, dto.getTel());
			pstmt.setString(5, dto.getEmail());
			pstmt.setString(6, dto.getZipcode());
			pstmt.setString(7, dto.getAddress1());
			pstmt.setString(8, dto.getAddress2());
			pstmt.setString(9, dto.getJob());
			
			res=pstmt.executeUpdate();
			
		}catch(Exception e) {
			System.out.println("추가실패 : "+e);
		}finally {
			dbclose.close(con, pstmt);
		}//try end
		
		return res;
	}
	
	public String loginProc(MemberDTO dto) {
		ResultSet rs=null;
		String mlevel=null;
		
		try {
			con=dbopen.getConnection();
			sql=new StringBuilder();
			
			sql.append(" SELECT mlevel");
			sql.append(" FROM member");
			sql.append(" WHERE id=? AND passwd=?"); 
			sql.append(" AND mlevel in('A1', 'B1', 'C1', 'D1')");
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPasswd());
			rs=pstmt.executeQuery();
			if(rs.next()) {
				mlevel=rs.getString("mlevel");
			}
		}catch(Exception e) {
			System.out.println("로그인 실패 : "+e);
		}finally {
			dbclose.close(con, pstmt, rs);
		}//try end
		
		return mlevel;
	}//login() end

	//회원관리
	public int recordCount() {
		ResultSet rs=null;
		int cnt=0;
		
		try {
			con=dbopen.getConnection();
			sql=new StringBuilder();
			
			sql.append(" SELECT COUNT(id) cnt");
			sql.append(" FROM member");
			
			pstmt=con.prepareStatement(sql.toString());
			rs=pstmt.executeQuery();
			if(rs.next()) {
				cnt=rs.getInt("cnt");
			}else {
				
			}
		}catch(Exception e) {
			System.out.println("회원 조회 실패 : "+e);
		}finally {
			dbclose.close(con, pstmt, rs);
		}//try end
		
		return cnt;
	}
	
	public ArrayList<MemberDTO> list(String col) {
		ResultSet rs=null;
		StringBuilder sql=null;
		ArrayList<MemberDTO> list=null;
		
		try {
			con=dbopen.getConnection();
			sql=new StringBuilder();
			sql.append(" SELECT id, passwd, mname, tel, email, mdate, mlevel");
			sql.append(" FROM member");
			
			if(col.equals("id")||col.equals("")) {
				sql.append(" ORDER BY id ASC");
			}else if(col.equals("mname")) {
				sql.append(" ORDER BY mname ASC");
			}else if(col.equals("mdate")) {
				sql.append(" ORDER BY mdate DESC");
			}//if end
			
			pstmt=con.prepareStatement(sql.toString());
			
			rs=pstmt.executeQuery();
			if(rs.next()) {
				list=new ArrayList<>();		//전체저장
				do {
					MemberDTO dto=new MemberDTO();	//한줄저장
					dto.setId(rs.getString("id"));
					dto.setPasswd(rs.getString("passwd"));
					dto.setMname(rs.getString("mname"));
					dto.setTel(Utility.checkNull(rs.getString("tel")));
					dto.setEmail(rs.getString("email"));
					dto.setMdate(rs.getString("mdate"));
					dto.setMlevel(rs.getString("mlevel"));
					list.add(dto);
				}while(rs.next());
			}//if end
			
		}catch(Exception e) {
			System.out.println("목록실패 : "+e);
		}finally {
			dbclose.close(con, pstmt, rs);
		}//try end
		
		return list;
		
	}//list() end
	
	
	public int updateM(String id, String mlevel) {
		int res=0;
		
		try {
			con=dbopen.getConnection();
			
			sql = new StringBuilder();
			sql.append(" UPDATE member"); 
			sql.append(" SET mlevel=?"); 
			sql.append(" WHERE id=?");
				
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, mlevel);
			pstmt.setString(2, id);

			res=pstmt.executeUpdate();
			
		}catch(Exception e) {
			System.out.println("수정실패 : "+e);
		}finally {
			dbclose.close(con, pstmt);
		}//try end
		
		return res;
	}//updateM() end
	
	
	public int memDel(String id) {
		int res=0;
		
		try {
			con=dbopen.getConnection();
			
			sql = new StringBuilder();
			sql.append(" DELETE FROM member");
			sql.append(" WHERE id=?");
				
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, id);

			res=pstmt.executeUpdate();
			
		}catch(Exception e) {
			System.out.println("삭제실패 : "+e);
		}finally {
			dbclose.close(con, pstmt);
		}//try end
		
		return res;
		
	}//memDel() end////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	public String idsearch(String mname, String email) {
		ResultSet rs=null;
		String id=null;
		try {
			con=dbopen.getConnection();
			sql=new StringBuilder();
			
			sql.append(" SELECT id");
			sql.append(" FROM member");
			sql.append(" WHERE mname=? AND email=?");
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, mname);
			pstmt.setString(2, email);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				id=rs.getString("id");
			}else {
				System.out.println("   dkdk");
			}
		}catch(Exception e) {
			System.out.println("조회 실패 : "+e);
		}finally {
			dbclose.close(con, pstmt, rs);
		}//try end
		
		return id;
	}//idsearch() end//////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	public String pwsearch(String mname, String email) {		//비번찾기
		ResultSet rs=null;
		String passwd=null;
		
		char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
		int idx = 0; 
		StringBuffer sb = new StringBuffer();
		//System.out.println("charSet.length :::: "+charSet.length);
		for (int i = 0; i < 8; i++) {
			idx = (int) (charSet.length * Math.random()); // 36 * 생성된 난수를 Int로 추출 (소숫점제거) 
			//System.out.println("idx :::: "+idx);
			sb.append(charSet[idx]);
		}
		
		try {
			con=dbopen.getConnection();
			sql=new StringBuilder();
			
			sql.append(" SELECT passwd, mname, email");
			sql.append(" FROM member");
			sql.append(" WHERE mname=? AND email=?");
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, mname);
			pstmt.setString(2, email);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				passwd=rs.getString("passwd");
			}
		}catch(Exception e) {
			System.out.println("조회 실패 : "+e);
		}finally {
			dbclose.close(con, pstmt, rs);
		}//try end
		
		return sb.toString();
	}//idpwsearch() end/////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	public int newpwSend(String passwd, String email) {
		int res=0;
		try {
			con=dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" UPDATE member ");
			sql.append(" SET passwd=?");
			sql.append(" WHERE email=?");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, passwd);
			pstmt.setString(2, email);
			res=pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("임시비밀번호실패 : "+e);
		}finally {
			dbclose.close(con, pstmt);
		}//try end
		return res;
	}//newpwSend() end/////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	public MemberDTO memUpdateForm(MemberDTO dto) {	//회원정보 수정폼
		ResultSet rs=null;
		
		try {
			con=dbopen.getConnection();
			sql=new StringBuilder();
			
			sql.append(" SELECT id, passwd, mname, email, tel, zipcode, address1, address2, job ");
			sql.append(" FROM member");
			sql.append(" WHERE id=? AND passwd=?"); 
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPasswd());
			rs=pstmt.executeQuery();
			if(rs.next()) {
				dto=new MemberDTO();
				dto.setId(rs.getString("id"));
				dto.setPasswd(rs.getString("passwd"));
				dto.setMname(rs.getString("mname"));
				dto.setEmail(rs.getString("email"));
				dto.setTel(rs.getString("tel"));
				dto.setZipcode(rs.getString("zipcode"));
				dto.setAddress1(rs.getString("address1"));
				dto.setAddress2(rs.getString("address2"));
				dto.setJob(rs.getString("job"));
			}
		}catch(Exception e) {
			System.out.println("회원정보 불러오기 실패 : "+e);
		}finally {
			dbclose.close(con, pstmt, rs);
		}//try end
		
		return dto;
	}//memUpdateForm() end///////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	public int memUpdate(MemberDTO dto) {
		int res=0;
		
		try {
			con=dbopen.getConnection();
			
			sql = new StringBuilder();
			sql.append(" UPDATE member"); 
			sql.append(" SET passwd=?, mname=?, email=?, tel=?, zipcode=?, address1=?, address2=?, job=?"); 
			sql.append(" WHERE id=?");
				
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getPasswd());
			pstmt.setString(2, dto.getMname());
			pstmt.setString(3, dto.getEmail());
			pstmt.setString(4, dto.getTel());
			pstmt.setString(5, dto.getZipcode());
			pstmt.setString(6, dto.getAddress1());
			pstmt.setString(7, dto.getAddress2());
			pstmt.setString(8, dto.getJob());
			pstmt.setString(9, dto.getId());

			res=pstmt.executeUpdate();
			
		}catch(Exception e) {
			System.out.println("수정실패 : "+e);
		}finally {
			dbclose.close(con, pstmt);
		}//try end
		
		return res;
	}
	
	
	
	public int memDelete(MemberDTO dto) {	//회원탈퇴 → 등급 F1으로 변경
		int res=0;
		
		try {
			con=dbopen.getConnection();
			
			sql = new StringBuilder();
			sql.append(" UPDATE member"); 
			sql.append(" SET mlevel='F1'"); 
			sql.append(" WHERE id=?");
				
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getId());

			res=pstmt.executeUpdate();
			
		}catch(Exception e) {
			System.out.println("회원 탈퇴 실패 : "+e);
		}finally {
			dbclose.close(con, pstmt);
		}//try end
		
		return res;
		
		
	}//memDel() end///////////////////////////////////////////////////////////////////////////////////////////////////////////////

	
	
}//class end
