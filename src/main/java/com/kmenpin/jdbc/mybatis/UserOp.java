package com.kmenpin.jdbc.mybatis;

public interface UserOp {

	public void addUser(User user);
	
	public void updateUser(User user);
	
	public void deleteUser(int id);
	
	public User getUser(int id);
}
