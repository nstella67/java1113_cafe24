package net.pds;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import net.utility.DBClose;
import net.utility.DBOpen;
import net.utility.Utility;

public class PdsDAO {
	private DBOpen	dbopen=null;
	private DBClose dbclose=null;
	
	private Connection con=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;
	private StringBuilder sql=null;
	
	public PdsDAO() {
		dbopen=new DBOpen();
		dbclose=new DBClose();
	}
	
	
	public int count(String col, String word) {
		int cnt=0;
		try {
			con=dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" SELECT COUNT(*) AS CNT ");
			sql.append(" FROM tb_pds ");
			if(word.length()>=1) {	//검색어가 있는지?
				String search="";
				if(col.equals("wname")) {
					search += " WHERE wname LIKE '%"+word+"%'";
				}else if(col.equals("subject")) {
					search += " WHERE subject LIKE '%"+word+"%'";
				}
				sql.append(search);
			}//if end
			pstmt=con.prepareStatement(sql.toString());
			rs=pstmt.executeQuery();
			if(rs.next()) {
				cnt=rs.getInt("cnt");
			}
		}catch(Exception e) {
			System.out.println("글갯수 실패 : "+e);
		}finally {
			dbclose.close(con, pstmt, rs);
		}//try end
		return cnt;
	}//count() end///////////////////////////////////////////////////////////////////////////////////
	
	public ArrayList<PdsDTO> list(String col, String word, int nowPage, int recordPerPage) {
	    ArrayList<PdsDTO> list = null;
	    int startRow = ((nowPage-1) * recordPerPage) + 1;
	    int endRow = nowPage * recordPerPage; 
	    try {
	    	con=dbopen.getConnection();
			sql=new StringBuilder();
			word = word.trim(); // 문자열 좌우 공백 제거
/*			if (word.length() == 0){ // 검색을 안하는 경우
		        sql.append(" SELECT pdsno, wname, subject, readcnt, regdate, filename, rnum ");
		        sql.append(" FROM (");
		        sql.append("   SELECT pdsno, subject, wname, readcnt, regdate, filename, rownum as rnum");
				sql.append("	FROM(");*/
				sql.append("		SELECT pdsno, subject, wname, readcnt, regdate, filename");
		        sql.append(" 		FROM tb_pds ");
		        sql.append(" 		ORDER BY pdsno DESC ");
/*		        sql.append("       )");
		        sql.append("  )     ");
		        sql.append(" WHERE rnum >= "+startRow+" AND rnum <= "+endRow +" AND cnt!=0");
			}else{ // 검색을 하는 경우
		        sql.append(" SELECT pdsno, wname, subject, readcnt, regdate, rnum");
		        sql.append(" FROM(");
		        sql.append("      SELECT pdsno, wname, subject, readcnt, regdate, rownum as rnum");
		        sql.append("      FROM (");
		        sql.append("           SELECT pdsno, wname, subject, readcnt, regdate");
		        sql.append("           FROM tb_pds ");
		        //검색
		        if(word.length()>=1){
		          String search=" WHERE "+col+" LIKE '%"+word+"%' ";
		          sql.append(search);
		        }
		        sql.append("           ORDER BY pdsno DESC");
		        sql.append("      )");
		        sql.append(" )     ");
		        sql.append(" WHERE rnum >= "+startRow+" AND rnum <= "+endRow);*/
	        pstmt = con.prepareStatement(sql.toString());
/*			}//if end
*/	      rs=pstmt.executeQuery();
	      if(rs.next()) {
	        list=new ArrayList<PdsDTO>();
	        do {
	    	  PdsDTO dto=null;
	          dto=new PdsDTO();
	          dto.setPdsno(rs.getInt("pdsno"));
	          dto.setWname(rs.getString("wname"));
	          dto.setSubject(rs.getString("subject"));
	          dto.setReadcnt(rs.getInt("readcnt"));
	          dto.setRegdate(rs.getString("regdate"));
	          dto.setFilename(rs.getString("filename"));
	          list.add(dto);
	        }while(rs.next());
	      }//if end
	    } catch (Exception e) {
	      System.out.println(e.toString());
	    } finally {
	      dbclose.close(con, pstmt, rs);
	    }
	    return list;
	  } // list() end	//////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	public boolean insert(PdsDTO dto) {
		int res=0;
		try {
			con=dbopen.getConnection();	//DB연결
			
			sql = new StringBuilder();
			sql.append(" INSERT INTO  tb_pds(wname, subject, passwd, filename, filesize, regdate, ip)");
			sql.append(" VALUES( ?, ?, ?, ?, ?, now(), ?)");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getWname());
			pstmt.setString(2, dto.getSubject());
			pstmt.setString(3, dto.getPasswd());
			pstmt.setString(4, dto.getFilename());
			pstmt.setLong(5, dto.getFilesize());
			pstmt.setString(6, dto.getIp());
			res=pstmt.executeUpdate();
			//System.out.println(res);
			if(res==0) {
				return false;
			}else {
				return true;
			}
		}catch(Exception e) {
			System.out.println("추가실패 : "+e);
		}finally {
			dbclose.close(con, pstmt);
		}//try end
		return true;
	}//insert() end//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	public PdsDTO read(int pdsno) {
		StringBuilder sql=null;
		PdsDTO dto=null;
		try {
			incrementCnt(pdsno); //조회수 증가
			con=dbopen.getConnection();
			sql=new StringBuilder();
			sql.append(" SELECT pdsno, wname, subject, readcnt, filename, filesize, regdate, ip");
			sql.append(" FROM tb_pds");
			sql.append(" WHERE pdsno=?");
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setInt(1, pdsno);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				dto=new PdsDTO();
				dto.setPdsno(rs.getInt("pdsno"));
				dto.setWname(rs.getString("wname"));
				dto.setSubject(rs.getString("subject"));
				dto.setReadcnt(rs.getInt("readcnt"));
				dto.setFilename(rs.getString("filename"));
				dto.setFilesize(rs.getLong("filesize"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setIp(rs.getString("ip"));
			}
		}catch(Exception e) {
			System.out.println("상세보기 실패 : "+e);
		}finally {
			dbclose.close(con, pstmt, rs);
		}//try end
		return dto;
	}//read() end//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	 //조회수 증가
	 public void incrementCnt(int pdsno){
		 StringBuilder sql=null;
		 try {
			 con=dbopen.getConnection();
			 sql=new StringBuilder();
			 sql.append(" UPDATE tb_pds");
			 sql.append(" SET readcnt=readcnt+1");
			 sql.append(" WHERE pdsno=?");
			 pstmt=con.prepareStatement(sql.toString());
			 pstmt.setInt(1, pdsno);
			 pstmt.executeUpdate();
		 }catch(Exception e){
			 System.out.println(e);
		 }finally{
			 dbclose.close(con, pstmt);
		 }
	}//incrementCnt() end/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	public int delete(int pdsno, String passwd, String saveDir) {
		int res=0;
		try {
			//파일삭제하려는 파일명을 가져온다
			String filename="";
			PdsDTO oldDTO = read(pdsno);
			if(oldDTO!=null) {
				filename = oldDTO.getFilename();	//파일명 지워지기 전에 변수에 담기
			}//if end //1205 FileTest.java 참조
			con=dbopen.getConnection();	//DB연결
			sql = new StringBuilder();
			sql.append(" DELETE FROM tb_pds");
			sql.append(" WHERE passwd=? AND pdsno=?");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, passwd);
			pstmt.setInt(2, pdsno);
			res=pstmt.executeUpdate();
			if(res==1) {
				//테이블에서 레코드가 성공적으로 삭제가 되면 첨부된 파일도 삭제
				Utility.deleteFile(saveDir, filename);
			}
		}catch(Exception e) {
			System.out.println("삭제실패 : "+e);
		}finally {
			dbclose.close(con, pstmt);
		}//try end
		return res;
	}//delete() end/////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	public PdsDTO updateform(int pdsno, String passwd) {
		PdsDTO dto=null;
		try {
			con=dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" SELECT pdsno, wname, subject, passwd, ip");
			sql.append(" FROM tb_pds");
			sql.append(" WHERE passwd=? AND pdsno=?");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, passwd);
			pstmt.setInt(2, pdsno);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				dto=new PdsDTO();
				dto.setPdsno(rs.getInt("pdsno"));
				dto.setWname(rs.getString("wname"));
				dto.setSubject(rs.getString("subject"));
				dto.setPasswd(rs.getString("passwd"));
				dto.setIp(rs.getString("ip"));
			}else {
				dto=null;
			}
		}catch(Exception e) {
			System.out.println("실패 : "+e);
		}finally {
			dbclose.close(con, pstmt, rs);
		}//try end
		return dto;
	}//updateform() end/////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	public int update1(PdsDTO dto) {
		int res=0;
		try {
			con=dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" UPDATE tb_pds");
			sql.append(" SET wname=?, subject=?, passwd=?, ip=?");
			sql.append(" WHERE pdsno=?");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getWname());
			pstmt.setString(2, dto.getSubject());
			pstmt.setString(3, dto.getPasswd());
			pstmt.setString(4, dto.getIp());
			pstmt.setInt(5, dto.getPdsno());
			res=pstmt.executeUpdate();
			//System.out.println(res);
		}catch(Exception e) {
			System.out.println("수정실패 : "+e);
		}finally {
			dbclose.close(con, pstmt);
		}//try end
		return res;
	}//updat1() end/////////////////////////////////////////////////////////////////////////////////////////////////////

	
	public int update2(PdsDTO dto) {	//필요X
		int res=0;
		try {
			con=dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" UPDATE tb_pds");
			sql.append(" SET filename=?, filesize=?, ip=?");
			sql.append(" WHERE pdsno=?");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getFilename());
			pstmt.setLong(2, dto.getFilesize());
			pstmt.setString(3, dto.getIp());
			pstmt.setInt(4, dto.getPdsno());
			res=pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("수정실패 : "+e);
		}finally {
			dbclose.close(con, pstmt);
		}//try end
		return res;
	}//update2() end/////////////////////////////////////////////////////////////////////////////////////////////////////
	
	

	public int update3(PdsDTO dto) {
		int res=0;
		try {
			con=dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" UPDATE tb_pds");
			sql.append(" SET wname=?, subject=?, passwd=?, filename=?, filesize=?, ip=?");
			sql.append(" WHERE pdsno=?");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getWname());
			pstmt.setString(2, dto.getSubject());
			pstmt.setString(3, dto.getPasswd());
			pstmt.setString(4, dto.getFilename());
			pstmt.setLong(5, dto.getFilesize());
			pstmt.setString(6, dto.getIp());
			pstmt.setInt(7, dto.getPdsno());
			res=pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("수정실패 : "+e);
		}finally {
			dbclose.close(con, pstmt);
		}//try end
		return res;
	}//update2() end/////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	

}//class end










