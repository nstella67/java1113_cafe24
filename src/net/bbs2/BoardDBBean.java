package net.bbs2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import net.bbs.BbsDTO;
import net.utility.DBClose;
import net.utility.DBOpen;

public class BoardDBBean {
	DBOpen  dbopen =null;
	DBClose dbclose=null;
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	StringBuilder sql = null;

	public BoardDBBean() {
		dbopen =new DBOpen(); //�����ͺ��̽� ���� ��ü �Ҵ�
		dbclose=new DBClose();
	}
/*	
	//DBCP����� ����Ŭ ����
	private Connection getConnection() throws Exception{
		Context initCtx=new InitialContext();
		DataSource ds  =(DataSource)initCtx.lookup("java:comp/env/jdbc/oracle");
		return ds.getConnection();
	}
	*/
	//���⿡ �����Ͻ� ���� �ۼ�
	public void insertArticle(BoardDataBean article) throws Exception{
		int num=article.getNum();
		int ref=article.getRef();
		int re_step=article.getRe_step();
		int re_level=article.getRe_level();
		int number=0;

		//System.out.println("try��");//�ڡڡڡڡ�
		
		try {
			con=dbopen.getConnection();
			sql=new StringBuilder();
			pstmt=con.prepareStatement(" SELECT max(num) FROM board");
			rs=pstmt.executeQuery();
			
			//System.out.println("try��");//�ڡڡڡڡ�
			
			
			if(rs.next()) {
				//System.out.println("rs.next");//�ڡڡڡڡ�
				number=rs.getInt(1)+1;
			}else {
				//System.out.println("rs.next����");//�ڡڡڡڡ�
				number=1;
			}//if end
			
			//�亯���⿡�� �� ���� ������
			if(num!=0) {
				//System.out.println("num!=0");//�ڡڡڡڡ�
				sql.delete(0, sql.length());
				sql.append(" UPDATE board SET re_step=re_step+1 WHERE ref=? AND re_step>?");
				pstmt=con.prepareStatement(sql.toString());
				pstmt.setInt(1, ref);
				pstmt.setInt(2, re_step);
				pstmt.executeUpdate();
				re_step=re_step+1;
				re_level=re_level+1;
			}else {
				//System.out.println("num==0");//�ڡڡڡڡ�
				ref=number;
				re_step=0;
				re_level=0;
			}//if end
			
			sql.delete(0, sql.length());
			sql.append(" INSERT INTO board(num, writer, email, subject, passwd, reg_date, ref, re_step, re_level, content, ip)");
			sql.append(" VALUES ((SELECT ifnull(max(num),0)+1 FROM board as TB), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			//System.out.println(sql.toString());
			
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, article.getWriter());
			pstmt.setString(2, article.getEmail());
			pstmt.setString(3, article.getSubject());
			pstmt.setString(4, article.getPasswd());
			pstmt.setTimestamp(5, article.getReg_date());
			pstmt.setInt(6, ref);
			pstmt.setInt(7, re_step);
			pstmt.setInt(8, re_level);
			pstmt.setString(9, article.getContent());
			pstmt.setString(10, article.getIp());
			
			pstmt.executeUpdate();
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbclose.close(con, pstmt, rs);
		}
	}//insertArticle() end
	
	
	public int getArticleCount() throws Exception{	//�۰���
		int x=0;
		try {
			con=dbopen.getConnection();
			pstmt=con.prepareStatement(" SELECT COUNT(*) FROM board");
			rs=pstmt.executeQuery();
			if(rs.next()) {
				x=rs.getInt(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) try {rs.close(); }catch(SQLException e) {}
			if(pstmt!=null) try {pstmt.close(); }catch(SQLException e) {}
			if(con!=null) try {con.close(); }catch(SQLException e) {}
		}
		
		return x;
		
	}//getArticleCount() end
	
	
	public List getArticles(int start, int end) throws Exception{
		List articleList=null;
		start=start-1;
		//System.out.println("�ƾƾƾƾƾƾƾ�");
		sql=new StringBuilder();
		sql.append(" SELECT num, writer, email, subject, passwd, reg_date, ref, re_step, re_level, content, ip, readcount");
		sql.append(" FROM board ORDER BY ref DESC, re_step ASC");
		sql.append(" LIMIT "+start+" ,"+end);
		
		try {
			con=dbopen.getConnection();
			pstmt=con.prepareStatement(sql.toString());
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				articleList=new ArrayList(end);
				do {
					BoardDataBean article=new BoardDataBean();
					article.setNum(rs.getInt("num"));
					article.setWriter(rs.getString("writer"));
					article.setEmail(rs.getString("email"));
					article.setSubject(rs.getString("subject"));
					article.setPasswd(rs.getString("passwd"));
					article.setReg_date(rs.getTimestamp("reg_date"));
					article.setReadcount(rs.getInt("readcount"));
					article.setRef(rs.getInt("ref"));
					article.setRe_step(rs.getInt("re_step"));
					article.setRe_level(rs.getInt("re_level"));
					article.setContent(rs.getString("content"));
					article.setIp(rs.getString("ip"));
					
					articleList.add(article);
				}while(rs.next());
			}//if end
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbclose.close(con, pstmt, rs);
		}
		return articleList;
	}//getArticles() end
	
	
	public BoardDataBean getArticle(int num) {
		BoardDataBean article=null;
		try {
			con=dbopen.getConnection();
			StringBuilder sql=new StringBuilder();
			sql.append(" UPDATE board SET readcount=readcount+1 WHERE num=?");
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			//System.out.println(sql.toString());
			
			sql.delete(0, sql.length());
			sql.append(" SELECT num, writer, email, subject, passwd, reg_date, ref, re_step, re_level, content, ip, readcount");
			sql.append(" FROM board WHERE num=?");
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setInt(1, num);
			pstmt.executeQuery();
			rs=pstmt.executeQuery();
			if(rs.next()) {
				article=new BoardDataBean();
				article.setNum(rs.getInt("num"));
				article.setWriter(rs.getString("writer"));
				article.setEmail(rs.getString("email"));
				article.setSubject(rs.getString("subject"));
				article.setPasswd(rs.getString("passwd"));
				article.setReg_date(rs.getTimestamp("reg_date"));
				article.setReadcount(rs.getInt("readcount"));
				article.setRef(rs.getInt("ref"));
				article.setRe_step(rs.getInt("re_step"));
				article.setRe_level(rs.getInt("re_level"));
				article.setContent(rs.getString("content"));
				article.setIp(rs.getString("ip"));
			}//if end
		
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbclose.close(con, pstmt, rs);
		}//try end
		
		return article;
	}//getArticle() end
	
/*	
	public BoardDataBean passwdCheck(BoardDataBean article) {
		int num=article.getNum();		
		try {
			con=dbopen.getConnection();
			sql=new StringBuilder();
			sql.append(" SELECT passwd FROM board WHERE num=?");
			pstmt.executeQuery();
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				number=rs.getInt(1)+1;
			}else {
				number=1;
			}//if end
			
			sql.delete(0, sql.length());
			sql.append(" INSERT INTO board(num, writer, email, subject, passwd, reg_date, ref, re_step, re_level, content, ip)");
			sql.append(" VALUES ((SELECT ifnull(max(num),0)+1 FROM board), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			System.out.println(sql.toString());
			
			
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, article.getWriter());
			pstmt.setString(2, article.getEmail());
			pstmt.setString(3, article.getSubject());
			pstmt.setString(4, article.getPasswd());
			pstmt.setTimestamp(5, article.getReg_date());
			pstmt.setInt(6, ref);
			pstmt.setInt(7, re_step);
			pstmt.setInt(8, re_level);
			pstmt.setString(9, article.getContent());
			pstmt.setString(10, article.getIp());
			
			pstmt.executeUpdate();
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbclose.close(con, pstmt, rs);
		}
		return article;
	}*/
	
	
	public int delete(BoardDataBean article) {
		int res=0;
		try {
			con=dbopen.getConnection();	//DB����
			
			sql = new StringBuilder();
			sql.append(" DELETE FROM board");
			sql.append(" WHERE passwd=? AND num=?");
				
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, article.getPasswd());
			pstmt.setInt(2, article.getNum());
				
			res=pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("���� : "+e);
		}finally {
			dbclose.close(con, pstmt);
		}
		return res;
	}//delete() end///////////////////////////////////////////////////////////////////////////////
	
	
	public BoardDataBean updateform(BoardDataBean article) {		//updateform ������ ��������
		try {
			con=dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" SELECT num, writer, email, subject, content, passwd, ip");
			sql.append(" FROM board");
			sql.append(" WHERE passwd=? AND num=?");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, article.getPasswd());
			pstmt.setInt(2, article.getNum());
			rs=pstmt.executeQuery();
			if(rs.next()) {
				//System.out.println("rs.next ����");
				article=new BoardDataBean();
				article.setNum(rs.getInt("num"));
				article.setWriter(rs.getString("writer"));
				article.setEmail(rs.getString("email"));
				article.setSubject(rs.getString("subject"));
				article.setContent(rs.getString("content"));
				article.setPasswd(rs.getString("passwd"));
				article.setIp(rs.getString("ip"));
			}else {
				//System.out.println("rs.next ����");
				article=null;
			}
		}catch(Exception e) {
			System.out.println("������ �ҷ����� ���� : "+e);
		}finally {
			dbclose.close(con, pstmt, rs);
		}//try end
		return article;
	}//updateform() end/////////////////////////////////////////////////////////////////////////////////

	
	public int update(BoardDataBean article) {
		int res=0;
		try {
			con=dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" UPDATE board");
			sql.append(" SET writer=?, subject=?, email=?, content=?, passwd=?, ip=?");
			sql.append(" WHERE num=?");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, article.getWriter());
			pstmt.setString(2, article.getSubject());
			pstmt.setString(3, article.getEmail());
			pstmt.setString(4, article.getContent());
			pstmt.setString(5, article.getPasswd());
			pstmt.setString(6, article.getIp());
			pstmt.setInt(7, article.getNum());
			res=pstmt.executeUpdate();
			
		}catch(Exception e) {
			System.out.println("���� : "+e);
		}finally {
			dbclose.close(con, pstmt);
		}//try end
		
		return res;
	}//update() end///////////////////////////////////////////////////////////////////////////////////////
	
}//class end




























