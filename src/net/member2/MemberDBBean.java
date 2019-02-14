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

}//class end
