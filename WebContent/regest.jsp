<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/regist.js"></script>
<title>注册管理员</title>
	<style type="text/css">
	font
	{
		color:red;
	}
	td.t1
	{
		width:200px;
	}
	</style>
</head>
<body>
    <center>
    <h1>注册</h1>
    <form action="regest.action" method="post" onSubmit="return submit1()">
    <table>
    <tr>
    <td><font>*</font> 用户名：</td>
    <td><input type="text" name ="username" id="username" onchange="check()"/></td>
    <td class="t1">
	<div id="uname" style="display:none">* 用户名只能是数字、字母、下划线组成
	</div>
	</td>
	</tr>
    <tr>
    <td><font>*</font> 初始密码：</td>
    <td><input type="password" name ="password" id="password" onchange="check()"/></td>
    <td class="t1"><div id="upwd" style="display:none">* 密码至少为6位</div></td>
	</tr>
	<tr>
    <td><font>*</font> 确认密码：</td>
    <td><input type="password" name ="password1" id="password1" onchange="check()"/></td>
    <td class="t1"><div id="upwd1" style="display:none">* 必须和初始密码保持一致</div></td>
	</tr>
	<tr>
    <td><font>*</font> 邮箱：</td>
    <td><input type="text" name ="email" id="email" value="@" onchange="check()"/></td>
	<td class="t1"><div id="uemail" style="display:none">* 邮箱不能以 - _ .以及其它特殊字符开头和结束，邮箱域名结尾为2~5个字母，比如cn、com、name</div></td>
    </tr>
    <tr>
    <td></td>
    <td><input type="submit" value="提交"/>&nbsp;&nbsp;
	    <input type="reset" value="重置"/>
    </td>
	<td></td>
    </tr>
	<tr>
	<td>
	<font>*为必填项</font>
	</td>
	<td><font>${sessionScope.error}</font></td>
	</tr>
    </table>
    </form>
    </center>
</body>
</html>