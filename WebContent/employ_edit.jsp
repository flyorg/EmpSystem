<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.system.entity.Employer"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="scripts/jquery/jquery-1.7.1.js"></script>
<link href="css/basic_layout.css" rel="stylesheet" type="text/css">
<link href="css/common_style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="scripts/jquery/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="scripts/artDialog/artDialog.js?skin=default"></script>
<script type="text/javascript" src="js/DatePicker.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
			/*
			 * 提交
			 */
			$("#submitbutton").click(function() {
				if(validateForm()){
					document.getElementById('submitForm').submit();
				}
			});
		
		/*
		 * 取消
		 */
		$("#cancelbutton").click(function() {
			/**  关闭弹出iframe  **/
			window.parent.$.fancybox.close();
		});
		
		var result = 'null';
		if(result =='success'){
			/**  关闭弹出iframe  **/
			window.parent.$.fancybox.close();
		}
	});
	
	/** 表单验证  **/
	function validateForm(){
		if($("#empsex").val()==""){
			art.dialog({icon:'error', title:'友情提示', drag:false, resize:false, content:'填选择员工性别', ok:true,});
			return false;
		}
		if($("#empid").val()==""){
			art.dialog({icon:'error', title:'友情提示', drag:false, resize:false, content:'填写员工号', ok:true,});
			return false;
		}
		if($("#empname").val()==""){
			art.dialog({icon:'error', title:'友情提示', drag:false, resize:false, content:'填写员工名称', ok:true,});
			return false;
		}
		if($("#empplace").val()==""){
			art.dialog({icon:'error', title:'友情提示', drag:false, resize:false, content:'填写籍贯', ok:true,});
			return false;
		}
		if($("#emp_finsh_school").val()==""){
			art.dialog({icon:'error', title:'友情提示', drag:false, resize:false, content:'填写毕业院校', ok:true,});
			return false;
		}
		return true;
	}
</script>
</head>
<body>
<form id="submitForm" name="submitForm" action="addEmp.action" method="post">
	<input type="hidden" name="fyID" value="14458625716623" id="fyID"/>
	<div id="container">
			<div id="page_close">
				<a href="javascript:parent.$.fancybox.close();">
					<img src="images/page_close.png" width="20" height="20" style="vertical-align: text-top;"/>
				</a>
			</div>
		</div>
		<div class="ui_content">
		 <%Employer emp =null;
		 if(request.getAttribute("employer")!=null){
		 emp=(Employer)request.getAttribute("employer");}
		 else{emp=new Employer();}%>
			<table  cellspacing="0" cellpadding="0" width="100%" align="center"  border="0">
				<tr>
					<td class="ui_text_rt">员工号</td>
					<td class="ui_text_lt">
					<input type="text" id="empid" name="empid" value="<%=emp.getEmp_id()==0?"":emp.getEmp_id() %>" class="ui_input_txt01"/>
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt">员工名称</td>
					<td class="ui_text_lt">
						<input type="text" id="empname" name="empname" value="<%=emp.getEmp_name()==null?"":emp.getEmp_name() %>" class="ui_input_txt01" onkeyup="checkFyFh();"/>
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt">员工性别</td>
					<td class="ui_text_lt">
							<select name="empsex" id="empsex" class="ui_select01">
                            <option value="">--请选择--</option>
                            <option value="男"<%="男".equals(emp.getEmp_sex())?"selected":""%>>男</option>
                            <option value="女"<%="女".equals(emp.getEmp_sex())?"selected":""%>>女</option>
                            </select>
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt">籍贯</td>
					<td class="ui_text_lt">
						<input type="text" id="empplace" name="empplace" value="<%=emp.getEmp_place()==null?"":emp.getEmp_place() %>" class="ui_input_txt01"/>
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt">毕业院校</td>
					<td class="ui_text_lt">
					<input type="text" id="emp_finsh_school" name="emp_finsh_school" value="<%=emp.getEmp_finsh_school()==null?"":emp.getEmp_finsh_school() %>" class="ui_input_txt01"/>
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt">教育程度</td>
					<td class="ui_text_lt">
						<select name="empeducation" id="empeducation" class="ui_select01">
                            <option value="" selected="selected">--请选择--</option>
							<option value="博士"<%="博士".equals(emp.getEmp_education())?"selected":""%>>博士</option>
							<option value="硕士"<%="硕士".equals(emp.getEmp_education())?"selected":""%>>硕士</option>
                            <option value="本科"<%="本科".equals(emp.getEmp_education())?"selected":""%>>本科</option>
							<option value="专科"<%="男专科".equals(emp.getEmp_education())?"selected":""%>>专科</option>
                        </select>
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt">公司名称</td>
					<td class="ui_text_lt">
						<select name="emp_company_name" id="emp_company_name" class="ui_select01">
                            <option value=""selected="selected">--请选择--</option>
                            <option value="微软"<%="微软".equals(emp.getEmp_company_name())?"selected":""%>>微软</option>
                            <option value="谷歌"<%="谷歌".equals(emp.getEmp_company_name())?"selected":""%>>谷歌</option>
                            <option value="百度"<%="百度".equals(emp.getEmp_company_name())?"selected":""%>>百度</option>
                        </select>
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt">公司类别</td>
					<td class="ui_text_lt">
						<select name="emp_company_category" id="emp_company_category" class="ui_select01" onchange="getFyDhListByFyXqCode();">
                            <option value="" selected="selected">--请选择--</option>
                            <option value="互联网"<%="互联网".equals(emp.getEmp_company_category())?"selected":""%>>互联网</option>
                            <option value="传统企业"<%="传统企业".equals(emp.getEmp_company_category())?"selected":""%>>传统企业</option>
                        </select>
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt">注册时间</td>
					<td class="ui_text_lt">
					<input class="ui_input_txt01" id="emp_trainDate" name="emp_trainDate" value="<%=emp.getEmp_trainDate()==null?"":emp.getEmp_trainDate() %>" type="text" onfocus="setday(this)" readonly="readonly" />
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td class="ui_text_lt">
						&nbsp;<input id="submitbutton" type="button" value="提交" class="ui_input_btn01"/>
						&nbsp;<input id="cancelbutton" type="button" value="取消" class="ui_input_btn01"/>
					</td>
					<td><input name="flag" value="<%=request.getAttribute("flag")%>" style="display: none"></td>
				</tr>
			</table>
	</div>
</form>
</body>
</html>