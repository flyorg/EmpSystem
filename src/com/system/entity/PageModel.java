package com.system.entity;

public class PageModel
{
	//总记录数
	private int empNum;
	//每页显示多少条数据
	private int pageSize;
	//第几页
	private int pageNo;
	//总页数
	private int pages;
	
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
	/**
	 * 获取总页数
	 * @return
	 */
	public int getTotalPages()
	{
		if(this.empNum%this.pageSize==0)
		{
			return this.empNum/this.pageSize;
		}
		else if(this.empNum%this.pageSize>0)
		{
			return this.empNum/this.pageSize + 1;
		}
		else
		{
			return 0;
		}
	}
	/**
	 * 获取首页
	 * @return
	 */
	public int getFirstPage()
	{
		return 1;
	}
	
	/**
	 * 获取上一页
	 * @return
	 */
	public int getPreviousPage()
	{
		return this.pageNo-1;
	}
	
	/**
	 * 获取下一页
	 * @return
	 */
	public int getNextPage()
	{
		return this.pageNo+1;
	}
	
	/**
	 * 获取末页
	 * @return
	 */
	public int getLastPage()
	{
		return getTotalPages();
	}
	
	public int getEmpNum() {
		return empNum;
	}
	public void setEmpNum(int empNum) {
		this.empNum = empNum;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
}
