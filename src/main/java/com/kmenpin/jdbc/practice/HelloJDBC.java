package com.kmenpin.jdbc.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HelloJDBC {
//	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
//	static final String DB_URL = "jdbc:mysql://localhost:3306/jw_learn?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&useSSL=false&serverTimezone=GMT";
	// ���timezone�쳣�ķ��� 1. ʹ�������DB_URL 2. ʹ�������DB_URLȻ����MYSQL serverʹ��set global time_zone = "+8:00";
	static final String DB_URL = "jdbc:mysql://localhost:3306/jw_learn";
	static final String USER = "root";
	static final String PASSWORD = "MYSQLglm@2";
	
	public static void helloword() throws ClassNotFoundException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		// 1.װ����������
		Class.forName(JDBC_DRIVER);
		// 2. �������ݿ�����
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			// 3. ִ��SQL���
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select user_name from user");
			
			//4.��ȡ���
			while(rs.next()) {
				System.out.println("Hellow"+rs.getString("user_name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) conn.close();
				if (stmt != null) stmt.close();
				if (rs != null) rs.close();
			} catch (SQLException e) {
				// ignore
			}
		}
	}
	
	public static void main(String[] args) throws ClassNotFoundException {
		helloword();
	}
}
