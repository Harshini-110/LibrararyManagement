package com.kce.book.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBUtil{
	public static Connection getConnection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url="jdbc:oracle:thin:@localhost:1521:xe";
			 String user="system";
			 String pass="oracle123";
			Connection connection ;
			connection = DriverManager.getConnection(url,user,pass);
			return connection;
		} catch (ClassNotFoundException |SQLException  e) {
			e.printStackTrace();
			return null;
		}
	}
	
}