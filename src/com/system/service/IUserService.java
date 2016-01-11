package com.system.service;

import java.sql.SQLException;

import com.system.entity.User;

public interface IUserService
{
	public abstract boolean checkLogin(User user) throws SQLException;
}
