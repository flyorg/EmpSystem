package com.system.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import oracle.jdbc.driver.OracleDriver;

/**
 * JDBC封装
 * 
 * @author spring
 *
 */
public class DbUtil
{
	static Connection conn = null;
	static PreparedStatement ptmt = null;
	static ResultSet rs = null;
	
	static
	{
		try
		{
			// 1.注册驱动(只做一次),或者说加载驱动
			DriverManager.registerDriver(new OracleDriver());
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
    
	/**
	 * 2.创建连接
	 * @throws SQLException
	 */
	public static Connection getConnect() throws SQLException
	{
		conn = DriverManager.getConnection(
				"jdbc:oracle:thin:@127.0.0.1:1521:orcl", "scott", "123456");
		return conn;
	}
	
	/**
	 * 3.创建PreparedStatement对象
	 * @throws SQLException 
	 */
	public static void getPst(String sql) throws SQLException
	{
		getConnect();
		ptmt = conn.prepareStatement(sql);
	}
	
	/**
	 * 4.执行sql语句
	 * 统一执行添加，修改，删除操作
	 * @throws SQLException 
	 */
	public static int executeUpdate(String sql,Object[] parameters) throws SQLException
	{
		getPst(sql);  
		//给？注入值
		for(int i = 0; i < parameters.length;i++)
		{
			ptmt.setObject(i+1, parameters[i]);
		}
		int flag = ptmt.executeUpdate();
		close();
		return flag;
	}
	/**
	 * 批量执行删除或者增加或者修改等操作
	 * @param sql
	 * @param parameters
	 * @return
	 * @throws SQLException
	 */
	public static int[] executeBatch(String sql,Object[] parameters) throws SQLException
	{
		getPst(sql);
		for(int i=0;i<parameters.length;i++)
		{
			ptmt.setObject(i+1, parameters[i]);
			ptmt.addBatch();
		}
		int[] rows=ptmt.executeBatch();
		close();
		return rows;
	}
	/**
	 * 4.执行sql语句
	 * 供查询调用
	 * @throws SQLException 
	 */
	public static ResultSet executeQuery(String sql,Object[] parameters) throws SQLException
	{
		getPst(sql);
		//给？注入值
		for(int i = 0; i < parameters.length;i++)
		{
			ptmt.setObject(i+1,parameters[i]);
		}
		rs = ptmt.executeQuery();
		return rs;
	}
	
	/**
	 * 6.释放资源
	 * @throws SQLException 
	 */
	public static void close() throws SQLException
	{
		if(rs != null)
		{
			rs.close();
		}
		if (ptmt != null)
		{
			ptmt.close();
		}
		if (conn != null)
		{
			conn.close();
		}
	}
	
	
}
