function check()
{
	//验证正则表达式
	//验证用户名只能输入数字、字母、下划线
	var regname=/^[0-9a-zA-Z]+$/;
	//验证邮箱格式
	var regemail=/^[A-Za-z0-9]+([-_.][A-Za-zd]+)*@([A-Za-zd]+[-.])+[A-Za-zd]{2,5}$/;
	//通过节点id获取节点
	//获取输入框节点
	var name=document.getElementById("username");//姓名输入框
	var pwd=document.getElementById("password");//密码输入框
	var pwd1=document.getElementById("password1");//再次输入密码框
	var email=document.getElementById("email");//邮箱输入框
	//获取提示框节点
	var uname=document.getElementById("uname");//用户名提示框
	var upwd=document.getElementById("upwd");//密码提示款
	var upwd1=document.getElementById("upwd1");//再次输入密码提示框
	var uemail=document.getElementById("uemail");//邮箱提示框
	
	if(!regname.test(name.value))//验证输入用户名
	{
		uname.setAttribute("style","color:red");
		return "uname";
	}
	else
	{
		uname.setAttribute("style","display:none");
	}
	
	if(pwd.value.length<6)
	{
		upwd.setAttribute("style","color:red");
		return "upwd";
	}
	else
	{
		upwd.setAttribute("style","display:none");
	}
	
	if(pwd1.value!=pwd.value)
	{
		upwd1.setAttribute("style","color:red");
		return "upwd1";
	}
	else
	{
		upwd1.setAttribute("style","display:none");
	}
	
	if(!regemail.test(email.value))
	{
		uemail.setAttribute("style","color:red");
		return "uemail";
	}
	else
	{
		uemail.setAttribute("style","display:none");
	}
}
//提交表单时判断数据填写是否规范
function submit1()
{
	switch(check())
	{
		case "uname":
		{
			alert("* 用户名只能是数字、字母、下划线组成");
			return false;
		}
		case "upwd":
		{
			alert("* 密码至少为6位");
			return false;
		}
		case "upwd1":
		{
			alert("必须和初始密码保持一致");
			return false;
		}
		case "uemail":
		{
			alert("邮箱不能以 - _ .以及其它特殊字符开头和结束，邮箱域名结尾为2~5个字母，比如cn、com、name");
			return false;
		}
		default :
		{
			return true;
		}
	}
}