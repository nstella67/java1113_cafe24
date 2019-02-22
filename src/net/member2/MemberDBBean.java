package net.member2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import net.utility.DBClose;
import net.utility.DBOpen;

public class MemberDBBean {
	DBOpen  dbopen =null;
	DBClose dbclose=null;
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	StringBuilder sql = null;

	public MemberDBBean() {
		dbopen =new DBOpen();
		dbclose=new DBClose();
	}
	
	public int duplecateID(String id) {
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
	
	
	public int login(String id, String passwd) {
		int res=0;
		try {
			con=dbopen.getConnection();
			sql=new StringBuilder();
			
			sql.append(" SELECT *");
			sql.append(" FROM member");
			sql.append(" WHERE id=? AND passwd=?"); 
			sql.append(" AND mlevel in('A1', 'B1', 'C1', 'D1')");
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			pstmt.setString(2, passwd);
			rs=pstmt.executeQuery();
			if(rs.next()){
				res=1;
			}
		}catch(Exception e) {
			
		}finally {
			dbclose.close(con, pstmt, rs);
		}//try end
		System.out.println(res);
		return res;
	}//login() end
	
	public int insert(MemberDataBean article) {
		int res=0;
		try {
			con=dbopen.getConnection();	//DB연결
			
			sql = new StringBuilder();
			sql.append(" INSERT INTO member(id, passwd, mname, tel, email, zipcode, address1, address2, job, mlevel, mdate) ");
			sql.append(" VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, 'D1', now())");
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, article.getId());
			pstmt.setString(2, article.getPasswd());
			pstmt.setString(3, article.getMname());
			pstmt.setString(4, article.getTel());
			pstmt.setString(5, article.getEmail());
			pstmt.setString(6, article.getZipcode());
			pstmt.setString(7, article.getAddress1());
			pstmt.setString(8, article.getAddress2());
			pstmt.setString(9, article.getJob());
			
			res=pstmt.executeUpdate();
			
		}catch(Exception e) {
			System.out.println("추가실패 : "+e);
		}finally {
			dbclose.close(con, pstmt);
		}//try end
		
		return res;
	}
	
	public MemberDataBean modifyForm(MemberDataBean article) {	//회원정보 수정폼
		try {
			con=dbopen.getConnection();
			sql=new StringBuilder();
			sql.append(" SELECT id, passwd, mname, email, tel, zipcode, address1, address2, job ");
			sql.append(" FROM member");
			sql.append(" WHERE id=? AND passwd=?"); 
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, article.getId());
			pstmt.setString(2, article.getPasswd());
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				article=new MemberDataBean();
				article.setId(rs.getString("id"));
				article.setPasswd(rs.getString("passwd"));
				article.setMname(rs.getString("mname"));
				article.setEmail(rs.getString("email"));
				article.setTel(rs.getString("tel"));
				article.setZipcode(rs.getString("zipcode"));
				article.setAddress1(rs.getString("address1"));
				article.setAddress2(rs.getString("address2"));
				article.setJob(rs.getString("job"));
			}else {
				article=null;
			}
		}catch(Exception e) {
			System.out.println("회원정보 불러오기 실패 : "+e);
		}finally {
			dbclose.close(con, pstmt, rs);
		}//try end
		
		return article;
	}//memUpdate() end
	
	
	public int modify(MemberDataBean article) {	//회원정보수정
		int res=0;
		
		try {
			con=dbopen.getConnection();
			
			sql = new StringBuilder();
			sql.append(" UPDATE member"); 
			sql.append(" SET passwd=?, mname=?, email=?, tel=?, zipcode=?, address1=?, address2=?, job=?"); 
			sql.append(" WHERE id=?");
				
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, article.getPasswd());
			pstmt.setString(2, article.getMname());
			pstmt.setString(3, article.getEmail());
			pstmt.setString(4, article.getTel());
			pstmt.setString(5, article.getZipcode());
			pstmt.setString(6, article.getAddress1());
			pstmt.setString(7, article.getAddress2());
			pstmt.setString(8, article.getJob());
			pstmt.setString(9, article.getId());
			res=pstmt.executeUpdate();
			
			//System.out.println(article.getId()+article.getPasswd()+article.getMname());
			//System.out.println(res);
		}catch(Exception e) {
			System.out.println("수정실패 : "+e);
		}finally {
			dbclose.close(con, pstmt);
		}//try end
		
		return res;
	}//modify() end
	
	
	public int withdraw(String id, String passwd) {	//회원탈퇴
		int res=0;
		try {
			con=dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" UPDATE member"); 
			sql.append(" SET mlevel='F1'"); 
			sql.append(" WHERE id=? AND passwd=?");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			pstmt.setString(2, passwd);
			res=pstmt.executeUpdate();
			System.out.println(res);
		}catch(Exception e) {
			System.out.println("탈퇴 실패 : "+e);
		}finally {
			dbclose.close(con, pstmt);
		}//try end
		return res;
	}//withdraw() end//////////////////////////////////////////////////////여기까지!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

	
	public MemberDataBean searchId(MemberDataBean article) {	//아이디찾기
		try {
			con=dbopen.getConnection();
			sql=new StringBuilder();
			
			sql.append(" SELECT id, mname");
			sql.append(" FROM member");
			sql.append(" WHERE mname=? AND email=?");
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, article.getMname());
			pstmt.setString(2, article.getEmail());
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				article=new MemberDataBean();
				article.setId(rs.getString("id"));
				article.setMname(rs.getString("mname"));
				
			}else {
				article=null;
			}
		}catch(Exception e) {
			System.out.println("아이디 조회 실패 : "+e);
		}finally {
			dbclose.close(con, pstmt, rs);
		}//try end
		
		return article;
	}
	
	
	public MemberDataBean searchPw(MemberDataBean article) {	//비번찾기
		try {
			con=dbopen.getConnection();
			sql=new StringBuilder();
			
			sql.append(" SELECT id, passwd, email");
			sql.append(" FROM member");
			sql.append(" WHERE mname=? AND email=? AND id=?");
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, article.getMname());
			pstmt.setString(2, article.getEmail());
			pstmt.setString(3, article.getId());
			rs=pstmt.executeQuery();
			if(rs.next()) {
				article=new MemberDataBean();
				article.setId(rs.getString("id"));
				article.setPasswd(rs.getString("passwd"));
				article.setEmail(rs.getString("email"));
				article.setMname(rs.getString("mname"));
			}else {
				article=null;
			}
		}catch(Exception e) {
			System.out.println("비번 조회 실패 : "+e);
		}finally {
			dbclose.close(con, pstmt, rs);
		}//try end
		
		return article;
	}
	
	
	public String pwsearch(MemberDataBean article) {		//임시비밀번호 설정
		String passwd=null;
		
		char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
		int idx = 0; 
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 8; i++) {
			idx = (int) (charSet.length * Math.random());
			sb.append(charSet[idx]);
		}
		
		try {
			con=dbopen.getConnection();
			sql=new StringBuilder();
			
			sql.append(" SELECT passwd, mname, email");
			sql.append(" FROM member");
			sql.append(" WHERE email=?");
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, article.getEmail());
			rs=pstmt.executeQuery();
			if(rs.next()) {
				passwd=rs.getString("passwd");
			}
		}catch(Exception e) {
			System.out.println("임시비밀번호 설정 실패 : "+e);
		}finally {
			dbclose.close(con, pstmt, rs);
		}//try end
		
		return sb.toString();
	}//idpwsearch() end/////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	public int newPw(String passwd, String email) {	//임시비밀번호 발송
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
			System.out.println("임시비밀번호 실패 : "+e);
		}finally {
			dbclose.close(con, pstmt);
		}//try end
		return res;
	}//newpwSend() end/////////////////////////////////////////////////////////////////////////////////////////////////////////

}//class end
