package com.system.list.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.system.entity.Employer;
import com.system.entity.PageModel;
import com.system.service.impl.EmployerServiceImpl;

/**
 * Servlet implementation class ListAction
 */
@WebServlet("/list.action")
public class ListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListAction() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//处理编码
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		//创建业务逻辑层 emp
		EmployerServiceImpl emp=new EmployerServiceImpl();
		//创建分页实体类
		PageModel pm=new PageModel();
		int pageNo=0;
		//获取页面查询条件sb
		String condition1=request.getParameter("companycategory");
		String condition2=request.getParameter("companyname");
		String condition3=request.getParameter("empid");
		StringBuffer sb=new StringBuffer();
		if(condition1!=null)
		{
			sb.append(condition1!=""?condition1:" ").append(",");
		}
		if(condition2!=null)
		{
			sb.append(condition2!=""?condition2:" ").append(",");
		}
		if(condition3!=null)
		{
			sb.append(condition3!=""?condition3:" ");
		}
		//设置每页显示多少条数据
		pm.setPageSize(Integer.parseInt(request.getParameter("pageSize")));
		//设置当前多少页
		if(request.getParameter("pageNo")==null || "".equals(request.getParameter("pageNo")))
		{
			pageNo=1;
		}
		else
		{
			pageNo=Integer.parseInt(request.getParameter("pageNo"));
		}
		pm.setPageNo(pageNo);
		//通过sb条件查询数据返回list集合
		List<Employer> list=emp.getList(sb.toString());
		//设置显示总数据条数
		pm.setEmpNum(list.size());
		//设置显示多少页数据
		pm.setPages(pm.getTotalPages());
		//假分页截取数据集合
		List<Employer> pageList=new ArrayList<Employer>();
		int num1=(pm.getPageNo()-1)*pm.getPageSize();
		int num2=pm.getPageNo()*pm.getPageSize();
		for(int i=num1;i<(num2>list.size()?list.size():num2);i++)
		{
			pageList.add(list.get(i));
		}
		//将假分页数据集合传到转发页面中
		request.setAttribute("pageList", pageList);
		//将分页类传到转发页面中
		request.setAttribute("pm", pm);
		//固定下啦列表框的值.防止页面转发后选中值清空
		request.setAttribute("selectname", condition2);
		request.setAttribute("selecttype", condition1);
		request.setAttribute("emp_id", request.getParameter("empid"));
		//转发页面
		request.getRequestDispatcher("employ_list.jsp").forward(request, response);
	}

}
