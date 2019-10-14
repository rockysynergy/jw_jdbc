package com.kmenpin.jdbc.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

public class Insert {
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static String DB_URL = "jdbc:mysql://localhost:3306/jw_learn";
	static final String USER = "root";
	static final String PASSWORD = "MYSQLglm@2";
	
	public static void insertUsers(Set<String> users) throws ClassNotFoundException {
		Connection conn = null;
		Statement stmt = null;
		
		// 装入驱动程序
		Class.forName(JDBC_DRIVER);
		
		// 建立数据库连接
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			
			// 3.执行SQL语句
			stmt = conn.createStatement();
			stmt.setFetchSize(1);
			for (String user : users) {
				stmt.addBatch("insert into user(user_name) values (\"" + user + "\")");
			}
			stmt.executeBatch();
			stmt.clearBatch();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//5.清理环境
			try {
				if (conn != null) conn.close();
				if (stmt != null) stmt.close();
			} catch (SQLException e) {
				// ignore
			}
		}
	}
	
	public static void main(String[] args) throws ClassNotFoundException {
		Set<String> users = new HashSet<String>();
		users.add("You yo");
		users.add("Jack Me");
		users.add("Jack Ma");
		insertUsers(users);
	}
}
