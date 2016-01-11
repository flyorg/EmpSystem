package com.system.service;

import java.util.List;

import com.system.entity.Employer;

public interface IEmployerService 
{
	public List<Employer> getList(String condition);
	public Employer selectEmp(String empid);
	public int addEmp(Employer emp);
	public int updateEmp(Employer emp);
	public int deleteEmp(String empid);
	public int[] deleteAnyEmp(String[] list);
}
