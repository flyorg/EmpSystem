package com.system.entity;

public class User
{
	private String user_name;//用户名
	private String user_pwd;//密码
	private String user_email;//邮箱
	private int id;//用户id
	private String randomCode;//随机码
	private boolean actived;
	
	public boolean isActived() {
		return actived;
	}

	public void setActived(boolean actived) {
		this.actived = actived;
	}

	public int getId() {
		return id;
	}

	public String getRandomCode() {
		return randomCode;
	}

	public void setRandomCode(String randomCode) {
		this.randomCode = randomCode;
	}

	public void setId(int id) {
		this.id = id;
	}

	/*
	 * 无参构造
	 */
	public User()
	{
	}
	
	/*
	 * 有参构造
	 */
	public User(String account,String password)
	{
		this.user_name = account;
		this.user_pwd = password;
	}
	
	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_pwd() {
		return user_pwd;
	}

	public void setUser_pwd(String user_pwd) {
		this.user_pwd = user_pwd;
	}
}
