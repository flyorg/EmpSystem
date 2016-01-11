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
 * Servlet implementation class DeleteAnyEmpAction
 */
@WebServlet("/deleteAnyEmp.action")
public class DeleteAnyEmpAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteAnyEmpAction() {
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
		String checkeds=request.getParameter("checkeds");
		String[] checked=checkeds.split(",");
		IEmployerService ies=new EmployerServiceImpl();
		int[] rows=ies.deleteAnyEmp(checked);
		for(int i : rows)
		{
			if(i<1)
			{
				JOptionPane.showMessageDialog(null, "学员信息批量删除失败！");
				request.getRequestDispatcher("/employ_list.jsp").forward(request, response);
				break;
			}
		}
		JOptionPane.showMessageDialog(null, "学员信息批量删除成功！");
		request.getRequestDispatcher("/employ_list.jsp").forward(request, response);
	}
}
