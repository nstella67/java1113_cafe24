package net.utility;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBOpen { //데이터베이스 연결
  
  public Connection getConnection() {
	 /* 카페24 MySQL DB연결 정보 */
	String url="jdbc:mysql://localhost/lnr67";
	String user="lnr67";
	String password="snnu!9310@";
	String driver="org.gjt.mm.mysql.Driver";
	
	Connection con = null;
    
    try {

        Class.forName(driver);
        con = DriverManager.getConnection(url, user, password);

    }catch (Exception e) {
        System.out.println("DB연결 실패 : " + e);
    }//try end   
    
    return con;
    
  }//end

}//class end
