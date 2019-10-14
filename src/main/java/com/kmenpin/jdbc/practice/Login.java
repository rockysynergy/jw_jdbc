package com.kmenpin.jdbc.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Login {
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/jw_learn";
	static final String USER = "root";
	static final String PASSWORD = "MYSQLglm@2";
	
	public static User login(String username, String password) throws ClassNotFoundException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		User user = null;
		
		// 1.装载驱动程序
		Class.forName(JDBC_DRIVER);
		// 2. 建立数据库连接
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			// 3. 执行SQL语句
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from user where "
					+ "user_name = '" + username + "' and password = '" + password + "'");
			
			//4.获取结果
			while(rs.next()) {
				user = new User();
				user.setUsername(rs.getString("user_name"));
				user.setSex(rs.getBoolean("sex"));
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
		
		return user;
	}
	
	public static User login2(String username, String password) throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		User user = null;
		
		// 1.装载驱动程序
		Class.forName(JDBC_DRIVER);
		// 2. 建立数据库连接
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			// 3. 执行SQL语句
			ptmt = conn.prepareStatement("select * from user where user_name = ? and password = ?");
			ptmt.setString(1, username);
			ptmt.setString(2, password);
			rs = ptmt.executeQuery();
			
			//4.获取结果
			while(rs.next()) {
				user = new User();
				user.setUsername(rs.getString("user_name"));
				user.setSex(rs.getBoolean("sex"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) conn.close();
				if (ptmt != null) ptmt.close();
				if (rs != null) rs.close();
			} catch (SQLException e) {
				// ignore
			}
		}
		
		return user;
	}
	
	public static void main(String[] args) throws ClassNotFoundException {
		System.out.println(login("Rocky Ou';-- ", "1234536") != null);
		System.out.println(login2("Rocky Ou';-- ", "1234536") != null);
		System.out.println(login2("Rocky Ou", "123456") != null);
	}
}
