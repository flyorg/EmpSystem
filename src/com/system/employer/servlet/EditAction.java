package com.system.employer.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.system.entity.Employer;
import com.system.service.IEmployerService;
import com.system.service.impl.EmployerServiceImpl;

/**
 * Servlet implementation class EditAction
 */
@WebServlet("/edit.action")
public class EditAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditAction() {
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
		Employer empl=ies.selectEmp(empid);
		if(empl!=null)
		{
			request.setAttribute("employer", empl);
			request.setAttribute("flag", "1");
			request.getRequestDispatcher("/employ_edit.jsp").forward(request, response);
		}
		else
		{
			
		}
	}

}
