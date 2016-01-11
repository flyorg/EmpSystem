package com.system.dao;

import java.sql.SQLException;
import java.util.List;

import com.system.entity.User;

public interface IUserDao 
{
	public abstract List<User> executeAllUser() throws SQLException;
    int addUser(User user) throws SQLException;
    
    int updateUser(User user)throws SQLException;  
      
    User findUserByName(String userName)throws SQLException;  
      
    User findUserByNameOrEmail(String nameOrEmail) throws SQLException;  
}
