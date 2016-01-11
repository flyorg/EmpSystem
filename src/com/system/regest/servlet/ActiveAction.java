package com.system.regest.servlet;

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
import com.system.tool.GenerateLinkUtils;

/**
 * Servlet implementation class ActiveAction
 */
@WebServlet("/active.action")
public class ActiveAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActiveAction() {
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
		String username = request.getParameter("userName");
		if(username!=null)
		{
		IUserDao userDao = UserDaoImpl.getInstance();
		User user=null;
		try {
			user = userDao.findUserByName(username);
		} catch (SQLException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		user.setActived(GenerateLinkUtils.verifyCheckcode(user, request));
		int row=0;
		try 
		{
			row = userDao.updateUser(user);
		} 
		catch (SQLException e) 
		{
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		if(row>0)
		{
			request.getSession().setAttribute("user", user);
			request.getRequestDispatcher("/activeSuccess.jsp").forward(request, response);
		}
		}
	}

}
