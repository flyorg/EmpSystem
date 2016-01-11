package com.system.employer.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import com.system.service.IEmployerService;
import com.system.service.impl.EmployerServiceImpl;

/**
 * Servlet implementation class DeleteAction
 */
@WebServlet("/delete.action")
public class DeleteAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteAction() {
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
		String empid=request.getParameter("empid");
		IEmployerService ies=new EmployerServiceImpl();
		if(ies.selectEmp(empid)!=null)
		{
			int row=ies.deleteEmp(empid);
			if(row>0)
			{
				JOptionPane.showMessageDialog(null, "学员信息删除成功!");
				request.getRequestDispatcher("/employ_list.jsp").forward(request, response);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "学员信息删除失败!");
				request.getRequestDispatcher("/employ_list.jsp").forward(request, response);
			}
		}
	}

}
