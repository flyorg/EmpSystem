package com.system.dao;

import java.sql.SQLException;
import java.util.List;

import com.system.entity.Employer;

public interface IEmployerDao 
{
	public List<Employer> getList(String conditon) throws SQLException;
	public Employer selectEmp(String empid)throws SQLException;
	public int addEmp(Employer emp)throws SQLException;
	public int updateEmp(Employer emp) throws SQLException;
	public int deleteEmp(String empid) throws SQLException;
	public int[] deleteAnyEmp(String[] list) throws SQLException;
}
