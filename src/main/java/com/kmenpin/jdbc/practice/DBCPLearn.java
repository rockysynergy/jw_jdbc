package com.kmenpin.jdbc.practice;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.dbcp2.BasicDataSource;

public class DBCPLearn {
	
	public static BasicDataSource ds = null;
	
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static String DB_URL = "jdbc:mysql://localhost:3306/jw_learn";
	static final String USER = "root";
	static final String PASSWORD = "MYSQLglm@2";
	
	public static void dbpoolInit() {
		ds = new BasicDataSource();
		ds.setUrl(DB_URL);
		ds.setDriverClassName(JDBC_DRIVER);
		ds.setUsername(USER);
		ds.setPassword(PASSWORD);
	}
	
	public void dBCPLearn() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = ds.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from user");
			
			while (rs.next()) {
				System.out.println(rs.getString("user_name"));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) conn.close();
				if (stmt != null ) stmt.close();
				if (rs != null ) rs.close();
			} catch (SQLException e1) {
				// ignore
			}
		}
	}
	
	public static void main(String[] args) {
		dbpoolInit();
		new DBCPLearn().dBCPLearn();
	}
}
