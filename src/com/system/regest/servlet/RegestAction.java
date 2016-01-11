package com.system.regest.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.system.dao.impl.UserDaoImpl;
import com.system.dao.IUserDao;
import com.system.entity.User;
import com.system.tool.EmailUtils;

/**
 * Servlet implementation class RegestAction
 */
@WebServlet("/regest.action")
public class RegestAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IUserDao userDao = UserDaoImpl.getInstance(); 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegestAction() {
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
        String userName = request.getParameter("username");  
        String password = request.getParameter("password");  
        String email = request.getParameter("email");
        
        User user = new User();  
        user.setUser_name(userName);  
        user.setUser_pwd(password);  
        user.setUser_email(email);  
        user.setActived(false);  
        
        int flag=0;
        try 
        {
        	User u=userDao.findUserByName(user.getUser_name());
        	if(u==null)
        	{
        		flag=userDao.addUser(user);
        	}
        	else
        	{
        		request.getSession().setAttribute("error", "该用户名已经被注册！");
        		request.getRequestDispatcher("/regest.jsp").forward(request, response);
        	}
		} 
        catch (SQLException e) 
        {
			e.printStackTrace();
		}  
          
        // 注册成功后,发送帐户激活链接
        if(flag>0)
        {
        	EmailUtils.sendAccountActivateEmail(user);
            // 注册成功直接将当前用户保存到session中  
            request.getSession().setAttribute("user", user);
            request.getRequestDispatcher("/regestSuccess.jsp").forward(request,response); 
        } 
    }
}
