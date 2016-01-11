package com.system.login.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.system.entity.User;
import com.system.service.IUserService;
import com.system.service.impl.UserServiceImpl;

/**
 * Servlet implementation class LoginAction
 */
@WebServlet("/login.action")
public class LoginAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginAction() {
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
		User user=new User();
		String checkcode=null;
		String checkbox=null;
		if(request.getParameter("username")!=null && request.getParameter("password")!=null)
		{
			user.setUser_name(request.getParameter("username"));
			user.setUser_pwd(request.getParameter("password"));
			checkcode=request.getParameter("checkcode").toLowerCase();
			checkbox=request.getParameter("savechecked");
		}
		
		IUserService ius=new UserServiceImpl();
		boolean flag=false;
		try 
		{
			flag=ius.checkLogin(user);
		} 
		catch (SQLException e)
		{
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		if(flag == true && checkcode.equals(request.getSession().getAttribute("checkcode").toString()))
		{
			if(checkbox!=null)
			{
				Cookie cookie=new Cookie("empname",user.getUser_name());
				cookie.setMaxAge(30);
				response.addCookie(cookie);
			}
			else
			{
				Cookie cookie=new Cookie("name",user.getUser_name());
				cookie.setMaxAge(30);
				response.addCookie(cookie);
			}
			request.getSession().setAttribute("username", user.getUser_name());
			request.setAttribute("success","登录成功,1秒后跳转");
			request.getRequestDispatcher("login.jsp").forward(request,response);
		}
		else if(!checkcode.equals(request.getSession().getAttribute("checkcode").toString()))
		{
			request.setAttribute("errorimg", "验证码输入错误!");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		else
		{
			request.setAttribute("error","用户名密码输入错误请重新输入");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

}
