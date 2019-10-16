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
		//1. 声明配置文件的目录
		String resource = "conf.xml";
		//2. 加载应用配置文件
		InputStream is = HelloMyBatisMoreOp.class.getClassLoader()
				.getResourceAsStream(resource);
		//3. 创建SqlSessionFactory
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder()
				.build(is);
		//4. 获取Session
		SqlSession session = sessionFactory.openSession(true);
		
		try {
			//5.获取操作类
			UserOp userOp = session.getMapper(UserOp.class);
			User user = new User("Winnie Luo", "HOPE WW");
			
			// 插入
			userOp.addUser(user);
			System.out.println("New Id: " + user.getId());
			
			// 查询
			user = userOp.getUser(user.getId());
			System.out.println(user.getId() + " " + user.getUserName() + " " + user.getCorp());
			
			// 更新
			user.setUserName("Hall");
			userOp.updateUser(user);
			
			// 删除
			userOp.deleteUser(user.getId());
		} finally {
			//7.关闭Session
			session.close();
		}
	}
}
