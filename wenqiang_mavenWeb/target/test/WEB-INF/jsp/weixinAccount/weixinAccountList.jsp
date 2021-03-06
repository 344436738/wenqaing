<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<base href="<%=basePath%>">
<meta charset="utf-8" />
<title></title>

        <link rel="stylesheet" href="static/ace/css/bootstrap.css" />
		<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css" />
		<link rel="stylesheet" href="bootstrap/css/bootstrap-table.css" />
		
		<script  src="static/js/jquery-1.7.2.js"></script>
		<script src="static/ace/js/ace-extra.js"></script>
		<script src="bootstrap/js/bootstrap.min.js"></script>
		<script src="bootstrap/js/bootstrap-table.js"></script>
		<script src="bootstrap/js/bootstrap-table-zh-CN.js"></script>
<script type="text/javascript" src="/wenqiang_mavenWeb/static/js/weixinAccount/weixinAccount.js"></script>


			


</head>
<body>	
	
	

				
<!--查询条件  -->
<div class="panel">
   <div class="panel-heading">
		<form action="" class="form-inline" id="searchForm">
				<span class="manage_unit clearfix"> <span class="manage_span_name">公众帐号名称：</span> <input
					type="text" name="accountname" id="accountname" class="form-control" />
			</span>
				<button type="button"  onclick="search()" class="btn btn-primary">
						 <i class="fa  fa-search"></i>查询</button>
		</form>
		<div class="dashed-line"></div>
		<!--toolbar-->
		<div class="tacz" id="toolbar">
			<span class="btn btn-primary" onclick="add()" fun="add">
				<i class="fa glyphicon-plus"></i> 
				增加</span>
				<span class="btn btn-primary" onclick="update()" fun="edit">
				<i class="fa glyphicon-pencil"></i> 
				修改</span> 
				<span class="btn btn-maroon" onclick="del()" fun="remove">
				删除</span>
		</div>
	</div>
	
<!-- 编辑窗口   ----->
	
		
		
	<div class="modal fade" id="editDiv" tabindex="-1" role="dialog" 
	   aria-labelledby="myModalLabel" aria-hidden="true">
	   <div class="modal-dialog">
		  <div class="modal-content">
			 <div class="modal-header">
				<button type="button" class="close" 
				   data-dismiss="modal" aria-hidden="true">
					  &times;
				</button>
				<h4 class="modal-title" id="myModalLabel">
				微信账号信息
				</h4>
			 </div>
			 <div class="modal-body">
			<form action="${msUrl}/weixinAccount/save.do" method="post"
				class="list_con_form clearfix" id="editForm">
				
						<input id="id" name="id" type="hidden" class="gldint texwd160">
						<p class="clearfix mtb8">
							<label class="control-label">公众帐号名称:</label>
							<input id="accountname" name="accountname" type="text" class="form-control"
							data-rule-required="true" data-rule-maxlength="30" /> 
						</p>
						 
						
						<p class="clearfix mtb8">
							<label class="control-label">公众帐号Token:</label>
							<input id="accounttoken" name="accounttoken" type="text"
								class="form-control" data-rule-required="true">
						</p>
						
						<p class="clearfix mtb8">
							<label class="control-label">公众微信号:</label>
							<input id="accountnumber" name="accountnumber" type="text"
								class="form-control">
						</p>
						
						 <p class="clearfix mtb8">
						 	<label class="control-label">原始ID:</label>
							<span class="manage_span_name inline_inblo wd170 txtri">原始ID:</span>
							<input id="WEIXIN_ACCOUNTID" name="WEIXIN_ACCOUNTID" type="text"
								class="form-control">
						</p>
						
						  <p class="clearfix mtb8">
						    <label class="control-label">公众号类型:</label>
							<input id="accounttype" name="accounttype" type="text"
								class="form-control">
						</p>
						
						<p class="clearfix mtb8">
							<label class="control-label">电子邮箱:</label>
							<input id="accountemail" name="accountemail" type="text"
								class="form-control" data-rule-required="true" data-rule-email="true"
						data-rule-maxlength="50">
						</p>
						
						<p class="clearfix mtb8">
							<label class="control-label">公众账号描述:</label>
							<input id="accountdesc" name="accountdesc" type="text"
								class="form-control" data-rule-required="true">
						</p>
						
						 <p class="clearfix mtb8">
							<label class="control-label">公众账号Appid:</label>
							<input id="accountappid" name="accountappid" type="text"
								class="form-control" data-rule-required="true">
						</p>
						
						<p class="clearfix mtb8">
							<label class="control-label">公众账号AppSecret:</label>
							<input id="accountappsecret" name="accountappsecret" type="text"
								class="form-control"  data-rule-required="true">
						</p>
			</form>
 		 </div>
         <div class="modal-footer">
            <button type="button" class="btn btn-default" 
              data-dismiss="modal" >关闭
            </button>
            <button type="button" onclick="updateSave()" class="btn btn-primary">
               提交更改
            </button>
         </div>
      </div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>
<div class="panel-body">
<table id="table" class="table_data"></table>
</div></div>


	


</body>
