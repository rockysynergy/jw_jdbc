package com.kmenpin.jdbc.mybatis;

import java.io.InputStream;

import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class HelloMyBatisAnnotation {
	public static void main(String[] args) {
		//1. ���������ļ���Ŀ¼
		String resource = "confAnnotation.xml";
		//2. ����Ӧ�������ļ�
		InputStream is = HelloMyBatis.class.getClassLoader()
				.getResourceAsStream(resource);
		//3. ����SqlSessionFactory
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder()
				.build(is);
		Configuration conf = sessionFactory.getConfiguration();
		conf.addMapper(GetUserInfoAnnotation.class);
		//4. ��ȡSession
		SqlSession session = sessionFactory.openSession();
		
		try {
			//5.��ȡ������
			GetUserInfoAnnotation getUserInfo = session.getMapper(GetUserInfoAnnotation.class);
			//6. ��ɲ�ѯ����
			User user = getUserInfo.getUser(2);
			System.out.println(user.getId() + " " + user.getUserName() + " " + user.getCorp());
		} finally {
			//7.�ر�Session
			session.close();
		}
	}
}
