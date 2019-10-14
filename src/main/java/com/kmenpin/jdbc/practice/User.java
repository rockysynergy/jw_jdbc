package com.kmenpin.jdbc.practice;

public class User {
	String username;
	Boolean sex;
	
	public void setUsername(String username) {
		this.username =  username;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setSex(boolean sex) {
		this.sex = sex;
	}
	
	public boolean getSex() {
		return sex;
	}
	
	public String toString() {
		return "username: " + username + " Sex: " + sex;
	}
}
