package com.kmenpin.jdbc.mybatis;

import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class HelloMyBatisMoreOp {
	public static void main(String[] args) {
		moreOp();
	}
	
	public static void moreOp() {
		//1. ���������ļ���Ŀ¼
		String resource = "conf.xml";
		//2. ����Ӧ�������ļ�
		InputStream is = HelloMyBatisMoreOp.class.getClassLoader()
				.getResourceAsStream(resource);
		//3. ����SqlSessionFactory
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder()
				.build(is);
		//4. ��ȡSession
		SqlSession session = sessionFactory.openSession(true);
		
		try {
			//5.��ȡ������
			UserOp userOp = session.getMapper(UserOp.class);
			User user = new User("Winnie Luo", "HOPE WW");
			
			// ����
			userOp.addUser(user);
			System.out.println("New Id: " + user.getId());
			
			// ��ѯ
			user = userOp.getUser(user.getId());
			System.out.println(user.getId() + " " + user.getUserName() + " " + user.getCorp());
			
			// ����
			user.setUserName("Hall");
			userOp.updateUser(user);
			
			// ɾ��
			userOp.deleteUser(user.getId());
		} finally {
			//7.�ر�Session
			session.close();
		}
	}
}
