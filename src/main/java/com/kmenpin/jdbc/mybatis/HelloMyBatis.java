package com.kmenpin.jdbc.mybatis;

import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class HelloMyBatis {
	public static void main(String[] args) {
		//1. ���������ļ���Ŀ¼
		String resource = "conf.xml";
		//2. ����Ӧ�������ļ�
		InputStream is = HelloMyBatis.class.getClassLoader()
				.getResourceAsStream(resource);
		//3. ����SqlSessionFactory
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder()
				.build(is);
		//4. ��ȡSession
		SqlSession session = sessionFactory.openSession();
		
		try {
			//5.��ȡ������
			GetUserInfo getUserInfo = session.getMapper(GetUserInfo.class);
			//6. ��ɲ�ѯ����
			User user = getUserInfo.getUser(2);
			System.out.println(user.getId() + " " + user.getUserName() + " " + user.getCorp());
		} finally {
			//7.�ر�Session
			session.close();
		}
	}
}
