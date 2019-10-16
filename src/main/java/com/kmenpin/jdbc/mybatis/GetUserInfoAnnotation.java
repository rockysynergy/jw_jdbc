package com.kmenpin.jdbc.mybatis;

import org.apache.ibatis.annotations.Select;

public interface GetUserInfoAnnotation {
	
	@Select("select id, user_name, corp from user where id = #{id}")
	public User getUser(int id);
}
