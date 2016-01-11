package com.system.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.system.dao.impl.EmployerDaoImpl;
import com.system.entity.Employer;
import com.system.service.IEmployerService;

public class EmployerServiceImpl implements IEmployerService
{
	EmployerDaoImpl emp=null;
	
	public EmployerServiceImpl()
	{
		emp=new EmployerDaoImpl();
	}
	//模糊查询数据
	public List<Employer> getList(String condition)
	{
		List<Employer> list=null;
		try 
		{
			list = emp.getList(condition);
		} 
		catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return list;
	}
	//根据id查询学员
	public Employer selectEmp(String empid)
	{
		Employer employer=null;
		try
		{
			employer=emp.selectEmp(empid);
		}
		catch (SQLException e)
		{
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return employer;
	}
	//新增学员信息
	public int addEmp(Employer emp1)
	{
		int row=0;
		try 
		{
			row=emp.addEmp(emp1);
		} 
		catch (SQLException e)
		{
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return row;
	}
	//修改学员信息
	public int updateEmp(Employer emp1)
	{
		int row=0;
		try 
		{
			row=emp.updateEmp(emp1);
		} 
		catch (SQLException e)
		{
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return row;
	}
	//删除学员信息
	public int deleteEmp(String empid)
	{
		int row=0;
		try 
		{
			row=emp.deleteEmp(empid);
		} 
		catch (SQLException e)
		{
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return row;
	}
	//批量删除学员信息
	public int[] deleteAnyEmp(String[] list)
	{
		int[] rows=null;
		try 
		{
			rows=emp.deleteAnyEmp(list);
		} 
		catch (SQLException e)
		{
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return rows;
	}
}
