package net.utility;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBOpen { //�����ͺ��̽� ����
  
  public Connection getConnection() {
	 /* ī��24 MySQL DB���� ���� */
	String url="jdbc:mysql://localhost/lnr67";
	String user="lnr67";
	String password="snnu!9310@";
	String driver="org.gjt.mm.mysql.Driver";
	
	Connection con = null;
    
    try {

        Class.forName(driver);
        con = DriverManager.getConnection(url, user, password);

    }catch (Exception e) {
        System.out.println("DB���� ���� : " + e);
    }//try end   
    
    return con;
    
  }//end

}//class end
