package com.system.employer.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import com.system.entity.Employer;
import com.system.service.IEmployerService;
import com.system.service.impl.EmployerServiceImpl;

/**
 * Servlet implementation class AddEmpAction
 */
@WebServlet("/addEmp.action")
public class AddEmpAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddEmpAction() {
        super();
        // TODO Auto-generated constructor stub
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
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		IEmployerService ies=new EmployerServiceImpl();
		String empid=request.getParameter("empid");
		String flag=request.getParameter("flag")==null?"0":"1";
		if(!"1".equals(flag))
		{
			Employer emp=null;
			if(ies.selectEmp(empid)==null)
			{
				emp=new Employer();
				emp.setEmp_id(Integer.parseInt(empid));
				emp.setEmp_name(request.getParameter("empname"));
				emp.setEmp_sex(request.getParameter("empsex"));
				emp.setEmp_place(request.getParameter("empplace"));
				emp.setEmp_finsh_school(request.getParameter("emp_finsh_school"));
				emp.setEmp_education(request.getParameter("empeducation"));
				emp.setEmp_company_name(request.getParameter("emp_company_name"));
				emp.setEmp_company_category(request.getParameter("emp_company_category"));
				emp.setEmp_trainDate(request.getParameter("emp_trainDate"));
				
				ies.addEmp(emp);
				if(ies.selectEmp(empid)!=null)
				{
					JOptionPane.showMessageDialog(null, "学员信息添加成功!");
					request.getRequestDispatcher("/employ_edit.jsp").forward(request, response);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "学员信息添加失败!");
					request.getRequestDispatcher("/employ_edit.jsp").forward(request, response);
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "学员信息已存在");
				request.getRequestDispatcher("/employ_edit.jsp").forward(request, response);
			}
		}
		else
		{
			Employer emp=new Employer();
			emp.setEmp_id(Integer.parseInt(empid));
			emp.setEmp_name(request.getParameter("empname"));
			emp.setEmp_sex(request.getParameter("empsex"));
			emp.setEmp_place(request.getParameter("empplace"));
			emp.setEmp_finsh_school(request.getParameter("emp_finsh_school"));
			emp.setEmp_education(request.getParameter("empeducation"));
			emp.setEmp_company_name(request.getParameter("emp_company_name"));
			emp.setEmp_company_category(request.getParameter("emp_company_category"));
			emp.setEmp_trainDate(request.getParameter("emp_trainDate"));
			
			if(ies.selectEmp(empid)!=null)
			{
				ies.updateEmp(emp);
				JOptionPane.showMessageDialog(null, "学员信息修改成功!");
				request.getRequestDispatcher("/employ_edit.jsp").forward(request, response);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "学员信息修改失败!请检查是否有该ID学员信息");
				request.getRequestDispatcher("/employ_edit.jsp").forward(request, response);
			}
		}
	}
}
