package com.system.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.system.dao.impl.UserDaoImpl;
import com.system.dao.IUserDao;
import com.system.db.DbUtil;
import com.system.entity.User;

public class UserDaoImpl implements IUserDao {
	private static UserDaoImpl instance = new UserDaoImpl();

	private UserDaoImpl() {
	}

	public static UserDaoImpl getInstance() {
		return instance;
	}
	@Override
	public List<User> executeAllUser() throws SQLException {
		List<User> users = new ArrayList<User>();
		String sql = "select * from empsystem";
		Object[] parameters = {};
		ResultSet rs = DbUtil.executeQuery(sql, parameters);
		while (rs.next()) {
			User u = new User();
			String username = rs.getString("emp_name");
			String password = rs.getString("emp_pwd");
			u.setUser_name(username);
			u.setUser_pwd(password);
			users.add(u);
		}
		DbUtil.close();
		return users;
	}

	//Map<Integer, User> userm = new HashMap<Integer, User>();

	@Override
	public int addUser(User user) throws SQLException {
		String select = "insert into empsystem values(?,?,?,?)";
		Object[] param = { user.getUser_name(), user.getUser_pwd(),
				user.getUser_email(), user.isActived() };
		int row = 0;
		row = DbUtil.executeUpdate(select, param);
		DbUtil.close();
		return row;
	}

	@Override
	public int updateUser(User user) throws SQLException {
		String select = "update empsystem set emp_pwd = ?,emp_actived = ? where emp_name = ? and emp_email = ?";
		Object[] param = { user.getUser_pwd(),user.isActived(),user.getUser_name(),
				user.getUser_email(),};
		int row = 0;
		row = DbUtil.executeUpdate(select, param);
		DbUtil.close();
		return row;
	}

	@Override
	public User findUserByName(String userName) throws SQLException {
		String select = "select * from empsystem where emp_name = ?";
		Object[] param = {userName};
		ResultSet rs = DbUtil.executeQuery(select, param);
		User user=null;
		while(rs.next())
		{
			user=new User();
			user.setUser_name(rs.getString("emp_name"));
			user.setUser_pwd(rs.getString("emp_pwd"));
			user.setUser_email(rs.getString("emp_email"));
			user.setActived((rs.getString("emp_actived").equals("1")?true:false));
		}
		DbUtil.close();
		return user;
	}

	@Override
	public User findUserByNameOrEmail(String nameOrEmail) throws SQLException {
		String select="select * from empsystem where emp_name = ? or emp_email = ?";
		Object[] param={nameOrEmail,nameOrEmail};
		User user=null;
		ResultSet rs=DbUtil.executeQuery(select, param);
		while(rs.next())
		{
			user=new User();
			user.setUser_name(rs.getString("emp_name"));
			user.setUser_pwd(rs.getString("emp_pwd"));
			user.setUser_email(rs.getString("emp_email"));
			user.setActived((rs.getString("emp_actived").equals("1")?true:false));
		}
		DbUtil.close();
		return user;
	}
}
