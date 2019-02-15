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
			sql.append(" FROM MEMBER "); 
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
			sql.append(" FROM MEMBER "); 
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
			sql.append(" UPDATE MEMBER"); 
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
	
	
	public int withdraw(MemberDataBean article) {	//회원탈퇴
		int res=0;
		try {
			con=dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" UPDATE MEMBER"); 
			sql.append(" SET mlevel='F1'"); 
			sql.append(" WHERE id=? AND passwd=?");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, article.getId());
			pstmt.setString(2, article.getPasswd());
			res=pstmt.executeUpdate();
			System.out.println(res);
		}catch(Exception e) {
			System.out.println("탈퇴 실패 : "+e);
		}finally {
			dbclose.close(con, pstmt);
		}//try end
		return res;
	}//withdraw() end

}//class end
