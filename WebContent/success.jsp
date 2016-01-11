<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录成功</title>
</head>
<body>
<center>
<%
	
	if(request.getSession()!=null)
	{
			if(request.getSession().getAttribute("username")!=null)
			{
%>
				<jsp:forward page="WEB-INF/homepage.jsp">
				<jsp:param value="" name=""/>
				</jsp:forward>
<%
			}
			else
			{
%>
				<jsp:forward page="login.jsp">
				<jsp:param value="" name=""/>
				</jsp:forward>
<%
			}
%>
<%
	}
%>
<jsp:forward page="WEB-INF/homepage.jsp">
<jsp:param value="" name=""/>
</jsp:forward>
</center>
</body>
</html>