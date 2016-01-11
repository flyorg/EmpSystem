package com.system.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.system.dao.IUserDao;
import com.system.dao.impl.UserDaoImpl;
import com.system.entity.User;
import com.system.service.IUserService;

public class UserServiceImpl implements IUserService
{
	IUserDao userdao=UserDaoImpl.getInstance();
	@Override
	public boolean checkLogin(User user) throws SQLException
	{
		List<User> users=userdao.executeAllUser();
		for (int i = 0; i < users.size(); i++)
		{
			if (users.get(i).getUser_name().equals(user.getUser_name()) 
					&& users.get(i).getUser_pwd().equals(user.getUser_pwd()))
			{
				return true;
			}
		}
		return false;
	}
}
