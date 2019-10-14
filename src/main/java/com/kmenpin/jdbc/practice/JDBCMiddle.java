package com.kmenpin.jdbc.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCMiddle {
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static String DB_URL = "jdbc:mysql://localhost:3306/jw_learn";
	static final String USER = "root";
	static final String PASSWORD = "MYSQLglm@2";
	
	public static void fetchData() throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		
		// װ����������
		Class.forName(JDBC_DRIVER);
		
		// �������ݿ�����
		try {
			DB_URL += "?useCursorFetch=true";
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			
			// 3.ִ��SQL���
			ptmt = conn.prepareStatement("select user_name from user");
			ptmt.setFetchSize(1);
			rs = ptmt.executeQuery();
			
			// 4.��ȡִ�н��
			while (rs.next()) {
				System.out.println("Hello " + rs.getString("user_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//5.������
			try {
				if (conn != null) conn.close();
				if (ptmt != null) ptmt.close();
				if (rs != null) rs.close();
			} catch (SQLException e) {
				// ignore
			}
		}
	}
	
	public static void main(String[] args) throws ClassNotFoundException {
		fetchData();
	}
}
