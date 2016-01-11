package com.system.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.system.dao.IEmployerDao;
import com.system.db.DbUtil;
import com.system.entity.Employer;

public class EmployerDaoImpl implements IEmployerDao
{
	//模糊查询数据
	public List<Employer> getList(String condition) throws SQLException
	{
		//获取查询条件集合以逗号为分割符分割成字符串数组
		String[] conds=condition.split(",");
		//编写select查询条件
		String select="select * from (select e1.*,rownum rn from employer e1)e where e.emp_company_category like '%"+(conds[0].equals(" ")?"":conds[0])+"%' and e.emp_company_name like '%"+(conds[1].equals(" ")?"":conds[1])+"%' and e.emp_id like '%"+(conds[2].equals(" ")?"":conds[2])+"%'";
		Object[] num = {};
		List<Employer> list=new ArrayList<Employer>();
		//接收DbUtil帮助类查询方法返回的set集合
		ResultSet rs=DbUtil.executeQuery(select, num);
		//遍历rs集合,将数据添加到list中
		while(rs.next())
		{
			Employer emp=new Employer();
			emp.setEmp_id(Integer.parseInt(rs.getString("emp_id")));
			emp.setEmp_name(rs.getString("emp_name"));
			emp.setEmp_sex(rs.getString("emp_sex"));
			emp.setEmp_place(rs.getString("emp_place"));
			emp.setEmp_finsh_school(rs.getString("emp_finsh_school"));
			emp.setEmp_education(rs.getString("emp_education"));
			emp.setEmp_company_name(rs.getString("emp_company_name"));
			emp.setEmp_company_category(rs.getString("emp_company_category"));
			emp.setEmp_trainDate(rs.getString("emp_traindate"));
			list.add(emp);
		}
		//关闭IO流
		DbUtil.close();
		//返回list数据结果集合
		return list;
	}
	//根据id查询员工
	public Employer selectEmp(String empid) throws SQLException
	{
		String select="select * from employer where emp_id=?";
		Object[] param={empid};
		ResultSet rs=DbUtil.executeQuery(select, param);
		Employer emp=null;
		while(rs.next())
		{
			emp=new Employer();
			emp.setEmp_id(Integer.parseInt(rs.getString("emp_id")));
			emp.setEmp_name(rs.getString("emp_name"));
			emp.setEmp_sex(rs.getString("emp_sex"));
			emp.setEmp_place(rs.getString("emp_place"));
			emp.setEmp_finsh_school(rs.getString("emp_finsh_school"));
			emp.setEmp_education(rs.getString("emp_education"));
			emp.setEmp_company_name(rs.getString("emp_company_name"));
			emp.setEmp_company_category(rs.getString("emp_company_category"));
			emp.setEmp_trainDate(rs.getString("emp_traindate"));
		}
		DbUtil.close();
		return emp;
	}
	//添加学员信息
	public int addEmp(Employer emp) throws SQLException
	{
		String add="call addemp(?,?,?,?,?,?,?,?,?)";
		Object[] param={emp.getEmp_id(),emp.getEmp_name(),emp.getEmp_sex(),emp.getEmp_place(),
				emp.getEmp_finsh_school(),emp.getEmp_education(),emp.getEmp_company_name(),
				emp.getEmp_company_category(),emp.getEmp_trainDate()};
		int row=DbUtil.executeUpdate(add, param);
		DbUtil.close();
		return row;
	}
	//修改学员信息
	public int updateEmp(Employer emp) throws SQLException
	{
		String update="call updateemp(?,?,?,?,?,?,?,?,?)";
		Object[] param={emp.getEmp_id(),emp.getEmp_name(),emp.getEmp_sex(),emp.getEmp_place(),
				emp.getEmp_finsh_school(),emp.getEmp_education(),emp.getEmp_company_name(),
				emp.getEmp_company_category(),emp.getEmp_trainDate()};
		int row=DbUtil.executeUpdate(update, param);
		DbUtil.close();
		return row;
	}
	//删除学员信息
	public int deleteEmp(String empid) throws SQLException
	{
		String delete="delete from employer where emp_id = ?";
		Object[] param={empid};
		int row=DbUtil.executeUpdate(delete, param);
		DbUtil.close();
		return row;
	}
	//批量删除学员信息
	public int[] deleteAnyEmp(String[] empid) throws SQLException
	{
		String delete="delete from employer where emp_id = ?";
		Object[] param=empid;
		int[] rows=DbUtil.executeBatch(delete, param);
		DbUtil.close();
		return rows;
	}
}
