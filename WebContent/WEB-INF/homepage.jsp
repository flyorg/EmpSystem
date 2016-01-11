<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>无名后台管理系统</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="js/menu.js"></script>
</head>
<body>
<div class="top"></div>
<div id="header">
	<div class="logo">无名后台管理系统</div>
	<div class="navigation">
	<%
	String name=null;
	if(request.getCookies()!=null)
		{
			Cookie[] cookie=request.getCookies();
			for(Cookie c : cookie)
			{
				if(c.getName().equals("empname"))
				{
					name=c.getValue();
					break;
				}
				else
				{
					name=request.getSession().getAttribute("username").toString();
				}
			}
		}%>
		<ul>
			<li>时&nbsp;间&nbsp;:&nbsp;<font id="showtime"></font></li>
		 	<li>欢迎您!&nbsp;&nbsp;&nbsp;<%=name%></li>
			<li><a href="">修改密码</a></li>
			<li><a href="logout.action">注销</a></li>
		</ul>
	</div>
</div>
<div id="content">
	<div class="left_menu">
				<ul id="nav_dot">
      <li>
          <h4 class="M1"><span></span>员工信息管理</h4>
          <div class="list-item none">
            <a href="employ_list.jsp" target="homepage">员工信息管理</a>
          </div>
        </li>
        <li>
          <h4 class="M2"><span></span>员工单位管理</h4>
          <div class="list-item none">
            <a href="register.html" target="homepage">单位工作功能</a>
            <a href="register.html" target="homepage">单位信息查询功能</a>
            <a href="register.html" target="homepage">单位信息修改功能</a>        
           </div>
        </li>
        <li>
          <h4 class="M3"><span></span>系统master管理</h4>
          <div class="list-item none">
            <a href="register.html" target="homepage">字段master管理</a>
            <a href="register.html" target="homepage">系统参数管理</a>
            <a href="register.html" target="homepage">系统权限设置</a>
			<a href="register.html" target="homepage">文档模板管理</a>
          </div>
        </li>
  </ul>
		</div>
		<div class="m-right">
			<div class="right-nav">
					<ul>
							<li><img src="images/home.png" align="top"/></li>
								<li style="margin-left:25px;">您当前的位置：</li>
								<li><a href="#">首页</a></li>
						</ul>
			</div>
			<div class="main">
			<iframe name="homepage" id="iframepage" frameborder="0" scrolling="auto" height=100% width=100%>
			</iframe>
			</div>
		</div>
</div>
<div class="bottom"></div>
<div id="footer"><p>Copyright©  2015 版权所有 京ICP备05019125号-10</p></div>
<script>navList(12);</script>
</body>
</html>