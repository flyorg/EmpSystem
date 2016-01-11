package com.system.findpwd.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.system.dao.IUserDao;
import com.system.dao.impl.UserDaoImpl;
import com.system.entity.User;
import com.system.tool.EmailUtils;

/**
 * Servlet implementation class FindAction
 */
@WebServlet("/find.action")
public class FindAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username=request.getParameter("userNameOrEmail");
		User use=null;
		IUserDao userDao= UserDaoImpl.getInstance();
		try 
		{
			use=userDao.findUserByNameOrEmail(username);
		} 
		catch (SQLException e) 
		{
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		if (use == null) {
			request.setAttribute("errorMsg", username + "，不存在！");
			request.getRequestDispatcher("/find.jsp").forward(request, response);
			return;
		}
		
		// 发送重新设置密码的链接
		EmailUtils.sendResetPasswordEmail(use);
		
		request.setAttribute("sendMailMsg", "您的申请已提交成功，请查看您的"+use.getUser_email()+"邮箱。");
		
		request.getRequestDispatcher("/findSuccess.jsp").forward(request, response);
	}

}
