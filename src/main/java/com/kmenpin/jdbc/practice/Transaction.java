package com.kmenpin.jdbc.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Savepoint;

public class Transaction {
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/jw_learn";
	static final String USER = "root";
	static final String PASSWORD = "MYSQLglm@2";
	

	public static void transferAccount() throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement ptmt = null;
		
		// 1.装载驱动程序
		Class.forName(JDBC_DRIVER);
		// 2. 建立数据库连接
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			// 3. 执行SQL语句
			ptmt = conn.prepareStatement(
					"update user set account = ? where user_name = ?");
			ptmt.setInt(1, 0);
			ptmt.setString(2, "Rocky Ou");
			ptmt.execute();
			
			ptmt.setInt(1, 100);
			ptmt.setString(2, "Joshua Ou");
			ptmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) conn.close();
				if (ptmt != null) ptmt.close();
			} catch (SQLException e) {
				// ignore
			}
		}
	}

	public static void transferAccount2() throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement ptmt = null;
		
		// 1.装载驱动程序
		Class.forName(JDBC_DRIVER);
		// 2. 建立数据库连接
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			conn.setAutoCommit(false);
			// 3. 执行SQL语句
			ptmt = conn.prepareStatement(
					"update user set account = ? where user_name = ?");
			ptmt.setInt(1, 0);
			ptmt.setString(2, "Rocky Ou");
			ptmt.execute();
			
			ptmt.setInt(1, 100);
			ptmt.setString(2, "Joshua Ou");
			ptmt.execute();
			conn.commit();
		} catch (SQLException e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) conn.close();
				if (ptmt != null) ptmt.close();
			} catch (SQLException e) {
				// ignore
			}
		}
	}
	
	public static void savePoint() throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement ptmt = null;
		Savepoint sp = null;
		
		// 1.装载驱动程序
		Class.forName(JDBC_DRIVER);
		// 2. 建立数据库连接
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			conn.setAutoCommit(false);
			// 3. 执行SQL语句
			ptmt = conn.prepareStatement("update user set account = ? where user_name = ?");
			ptmt.setInt(1, 0);
			ptmt.setString(2, "Rocky Ou");
			ptmt.execute();
			sp = conn.setSavepoint();
			
			ptmt.setInt(1, 100);
			ptmt.setString(2, "Joshua Ou");
			ptmt.execute();
			throw new SQLException();
		} catch (SQLException e) {
			if (conn != null) {
				try {
					conn.rollback(sp);
					
					ptmt.setInt(1, 100);
					ptmt.setString(2, "Eddy Ou");
					ptmt.execute();
					conn.commit();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) conn.close();
				if (ptmt != null) ptmt.close();
			} catch (SQLException e) {
				// ignore
			}
		}
	}
	
	public static void main(String[] args) throws ClassNotFoundException {
//		transferAccount();
//		transferAccount2();
		savePoint();
	}
}
