package com.system.resetpwd.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.system.dao.IUserDao;
import com.system.dao.impl.UserDaoImpl;
import com.system.entity.User;

/**
 * Servlet implementation class RestAction
 */
@WebServlet("/rest.action")
public class RestAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RestAction() {
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
		String userName = request.getParameter("userName");
		String newPassword = request.getParameter("newPassword");
		String newPassword2 = request.getParameter("newPassword2");
		Map<String,String> errors = new HashMap<String, String>();
		if (newPassword == null || "".equals(newPassword)) {
			errors.put("newPassword", "新密码不能为空！");
		}
		
		if (newPassword2 == null || "".equals(newPassword2)) {
			errors.put("newPassword2", "确认新密码不能为空！");
		}
		
		if (!newPassword.equals(newPassword2)) {
			errors.put("passwordError", "两次输入的密码不一致！");
		}
		
		if (!errors.isEmpty()) {
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("/resetpwd.jsp?userName=" + userName).forward(request, response);
			return;
		}
		
		IUserDao userDao = UserDaoImpl.getInstance();
		int row=0;
		try 
		{
			User use = userDao.findUserByName(userName);
			if(use!=null)
			{
				User user=use;
				user.setUser_pwd(newPassword);
				row=userDao.updateUser(user);
			}
		} 
		catch (SQLException e) 
		{
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		if(row>0)
		{
			request.setAttribute("success", "密码重置成功！");
		}
		else
		{
			request.setAttribute("success", "密码重置失败,请检查申请用户/邮箱是否正确!");
		}
		request.getRequestDispatcher("/resetSuccess.jsp").forward(request, response);
	}

}
