<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*" import="com.system.entity.Employer" import="com.system.entity.PageModel"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="scripts/jquery/jquery-1.7.1.js"></script>
<link href="css/basic_layout.css" rel="stylesheet" type="text/css">
<link href="css/common_style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="scripts/authority/commonAll.js"></script>
<script type="text/javascript" src="scripts/fancybox/jquery.fancybox-1.3.4.js"></script>
<script type="text/javascript" src="scripts/fancybox/jquery.fancybox-1.3.4.pack.js"></script>
<link rel="stylesheet" type="text/css" href="css/jquery.fancybox-1.3.4.css" media="screen"></link>
<script type="text/javascript" src="scripts/artDialog/artDialog.js?skin=default"></script>
<title>信息管理系统</title>
<script type="text/javascript">
	$(document).ready(function(){
		/** 新增   **/
	    $("#addBtn").fancybox({
	    	'href'  : 'employ_edit.jsp',
	    	'width' : 733,
	        'height' : 530,
	        'type' : 'iframe',
	        'hideOnOverlayClick' : false,
	        'showCloseButton' : false,
	        'action':'addEmp.action',
	        'onClosed' : function() { 
	        	window.location.href = 'employ_list.jsp';
	        }
	    });
		
	    /**编辑   **/
	    $("a.edit").fancybox({
	    	'width' : 733,
	        'height' : 530,
	        'type' : 'iframe',
	        'hideOnOverlayClick' : false,
	        'showCloseButton' : false,
	        'onClosed' : function() { 
	        	window.location.href = 'employ_list.jsp';
	        }
	    });
	});
	/** 模糊查询来电用户  **/
	function search(){
		$("#submitForm").attr("action", "list.action").submit();
	}

	/** 新增   **/
	function add(){
		$("#submitForm").attr("action", "/xngzf/archives/luruFangyuan.action").submit();	
	}
	
	/** 删除 **/
	function del(empid){
		// 非空判断
		if(empid == '') return;
		if(confirm("您确定要删除吗？")){
			$("#submitForm").attr("action", "delete.action?empid=" + empid).submit();			
		}
	}
	
	/** 批量删除 **/
	function batchDel(){
		if($("input[name='IDCheck']:checked").size()<=0){
			art.dialog({icon:'error', title:'友情提示', drag:false, resize:false, content:'至少选择一条', ok:true,});
			return;
		}
		// 1）取出用户选中的checkbox放入字符串传给后台,form提交
		var allIDCheck = "";
		$("input[name='IDCheck']:checked").each(function(index, domEle){
			bjText = $(domEle).parent("td").parent("tr").last().children("td").last().prev().text();
// 			alert(bjText);
			// 用户选择的checkbox, 过滤掉“已审核”的，记住哦
			if($.trim(bjText)=="已审核"){
// 				$(domEle).removeAttr("checked");
				$(domEle).parent("td").parent("tr").css({color:"red"});
				$("#resultInfo").html("已审核的是不允许您删除的，请联系管理员删除！！！");
// 				return;
			}else{
				allIDCheck += $(domEle).val() + ",";
			}
		});
		// 截掉最后一个","
		if(allIDCheck.length>0) {
			allIDCheck = allIDCheck.substring(0, allIDCheck.length-1);
			// 赋给隐藏域
			if(confirm("您确定要批量删除这些记录吗？")){
				// 提交form
				$("#submitForm").attr("action", "deleteAnyEmp.action?checkeds="+allIDCheck).submit();
			}
		}
	}

	/** 普通跳转 **/
	function jumpPage(page){
		if(page<1)
			{
				page=1;
			}
		$("#submitForm").attr("action", "list.action?pageNo="+page).submit();
	}
	
	/** 输入页跳转 **/
	function jumpInputPage(){
		var jumptextNode=document.getElementById("jumpNumTxt").value;
		var pages="${requestScope.pm.pages}";
		if(jumptextNode!=null || jumptextNode!="")
			{
				if(parseInt(jumptextNode)>parseInt(pages))
					{
						jumptextNode=pages;
					}
				if(parseInt(jumptextNode)<1)
					{
						jumptextNode=1;
					}
				$("#submitForm").attr("action", "list.action?pageNo="+jumptextNode).submit();
			}
		}
</script>
<style>
	.alt td{ background:black !important;}
</style>
</head>
<body>
	<form id="submitForm" name="submitForm" action="" method="post">
		<div id="container">
			<div class="ui_content">
				<div class="ui_text_indent">
					<div id="box_border">
						<div id="box_top">搜索</div>
						<div id="box_center">
						    <% String slname=null;
                            String sltype=null;
                            if(request.getAttribute("selectname")!=null)
                            {
                            	slname=request.getAttribute("selectname").toString();
                            };
                            if(request.getAttribute("selecttype")!=null)
                            {
                            	sltype=request.getAttribute("selecttype").toString();
                            }
                            %>
							公司类型
							<select name="companycategory" id="comtype" class="ui_select01" onchange="search()">
                                <option value="">--请选择--</option>
                                <option value="互联网" <%="互联网".equals(sltype)?"selected":"" %>>互联网</option>
                                <option value="传统企业" <%="传统企业".equals(sltype)?"selected":"" %>>传统企业</option>
                            </select>
                            
							公司名称
							<select name="companyname" id="comname" class="ui_select01" onchange="search()">
                                <option value="" >--请选择--</option>
								<option value="微软" <%="微软".equals(slname)?"selected":"" %>>微软</option>
								<option value="谷歌"	<%="谷歌".equals(slname)?"selected":"" %>>谷歌</option>
								<option value="百度"	<%="百度".equals(slname)?"selected":"" %>>百度</option>
                            </select>
							员工ID&nbsp;&nbsp;<input type="text" id="empid" name="empid" class="ui_input_txt02" value="${requestScope.emp_id}"
							onkeyup="this.value=this.value.replace(/\D/g,'')" maxlength="7" />
							<input type="button" value="查询" onclick="search();" /> 
							<input type="button" value="新增"  id="addBtn" /> 
							<input type="button" value="删除"  onclick="batchDel();" />
						</div>
					</div>
				</div>
			</div>
			<div class="ui_content">
				<div class="ui_tb">
					<table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
						<tr>
							<th width="30"><input type="checkbox" id="all" onclick="selectOrClearAllCheckbox(this);" />
							</th>
							<th>员工号</th>
							<th>员工姓名</th>
							<th>员工性别</th>
							<th>籍贯</th>
							<th>毕业院校</th>
							<th>教育程度</th>
							<th>公司名称</th>
							<th>公司类别</th>
							<th>注册时间</th>
							<th>操作</th>
						</tr>
						<c:forEach var="employer" items="${requestScope.pageList}">
							<tr>
								<td><input type="checkbox" name="IDCheck" value="${employer.emp_id}" class="acb" /></td>
								<td>${employer.emp_id}</td>
								<td>${employer.emp_name}</td>
								<td>${employer.emp_sex}</td>
								<td>${employer.emp_place}</td>
								<td>${employer.emp_finsh_school}</td>
								<td>${employer.emp_education}</td>
								<td>${employer.emp_company_name}</td>
								<td>${employer.emp_company_category}</td>
								<td>${employer.emp_trainDate}</td>
								<td>
									<a href="edit.action?empid=${employer.emp_id}" class="edit">编辑</a> 
									<a href="javascript:del(${employer.emp_id});">删除</a>
								</td>
							</tr>
						</c:forEach>
					</table>
					<div class="ui_content">
						<div class="ui_flt" style="height: 100%; line-height: 30px;">
						共有
						<span class="ui_txt_bold04">${requestScope.pm.empNum}</span>
						条记录，当前第
						<a href="javascript:void(0)" class="ui_txt_bold04" name="pageNo" >${pm.pageNo}</a>
						/<a href="javascript:void(0)" class="ui_txt_bold04" name="pages" >${pm.pages}</a>
						页
					</div>
					<%
					PageModel pm=(PageModel)request.getAttribute("pm");
					int size=5;
					if(pm!=null)
					{
						size=pm.getPageSize();
					}
					%>
					<div class="ui_frt">
						<!--    如果是第一页，则只显示下一页、尾页 -->
							<input type="button" value="首页" name="firstpage" onclick="jumpPage(${pm.pageNo==1?pm.pageNo:1});" />
							<input type="button" value="上一页" name="previouspage" onclick="jumpPage(${pm.pageNo-1>0?pm.pageNo-1:pm.pageNo});" />
							<input type="button" value="下一页" name="nextpage" onclick="jumpPage(${pm.pageNo+1>pm.pages?pm.pageNo:pm.pageNo+1});" />
							<input type="button" value="尾页" name="lastpage" onclick="jumpPage(${pm.pages});" />
						<!--     如果是最后一页，则只显示首页、上一页 -->
						转到第<input type="text" id="jumpNumTxt" class="ui_input_txt01" onkeyup="this.value=this.value.replace(/\D/g,'')" 
						 maxlength="3" />页
							 <input type="button"  value="跳转" onclick="jumpInputPage()" />
							 每页显示<select name="pageSize" onchange="search()">
							 <option value="3"<%=3==size?"selected":"" %>>3</option>
							 <option value="4"<%=4==size?"selected":"" %>>4</option>
							 <option value="5"<%=5==size?"selected":"" %>>5</option>
							 </select>条数据
					</div>
					</div>
				</div>
			</div>
		</div>
	</form>
</body>
</html>