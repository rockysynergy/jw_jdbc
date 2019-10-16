package com.kmenpin.jdbc.mybatis;

public class User {
	private int id;
	private String userName;
	private String corp;
	
	public User(Integer id, String user_name, String corp) {
		this.id = id;
		this.userName = user_name;
		this.corp = corp;
	}
	
	public User(String user_name, String corp) {
		this.userName = user_name;
		this.corp = corp;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String user_name) {
		userName = user_name;
	}
	
	public String getCorp() {
		return corp;
	}
	
	public void setCorp(String corp) {
		this.corp = corp;
	}
}
