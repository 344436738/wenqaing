<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<base href="<%=basePath%>">
<!-- 下拉框 -->
<link rel="stylesheet" href="static/ace/css/chosen.css" />
<!-- jsp文件头和头部 -->
<%@ include file="../system/index/top.jsp"%>

</head>
<body class="no-skin">

	<!-- /section:basics/navbar.layout -->
	<div class="main-container" id="main-container">
		<!-- /section:basics/sidebar -->
		<div class="main-content">
			<div class="main-content-inner">
				<div class="page-content">
					<div class="row">
						<div class="col-xs-12">
	
						<!-- 检索  -->
						<form action="command/list.do" method="post" name="Form" id="Form">
						<!-- 检索  -->
					
						<table id="simple-table" class="table table-striped table-bordered table-hover"  style="margin-top:5px;">
							<thead>
								<tr>
									<th class="center">
									<label><input type="checkbox" class="ace" id="zcheckbox" /><span class="lbl"></span></label>
									</th>
									<th class="center" style="width: 50px;">序号</th>
									<th class="center">trigger名称</th>
									<th class="center">表达式</th>
									<th class="center">Job名称</th>
									<th class="center">任务类名</th>
									<th class="center">方法名</th>
									<th class="center">是否并发启动</th>
								    <th class="center">计划任务</th>
								    <th class="center">存在的springBean</th>
								    
									<th class="center">操作</th>
												
								</tr>
							</thead>
													
							<tbody>
								
							<!-- 开始循环 -->	
							<c:choose>
								<c:when test="${not empty varList}">
								
									<c:forEach items="${varList}" var="var" varStatus="vs">
										<tr>
											<td class='center' style="width: 30px;">
												<label><input type='checkbox' name='ids' class="ace" value="${var.id}" /><span class="lbl"></span></label>
											</td>
											<td class='center' style="width: 30px;">${var.id}</td>
											<td class='center' style="width: 30px;">${var.triggername}</td>
													<td class="center">${var.cronexpression}</td>
													<td>${var.jobdetailname} ...</td>
													<td class="center">${var.targetobject}</td>
													<td class="center">${var.methodname}</td>
													<td class="center">
														${var.concurrent}
													</td>
													
										       <c:if test="${var.state == 1 }">
													<td class="center">
											                               启动
													</td>
													</c:if>
													<c:if test="${var.state  == 0 }">
													<td class="center">
											                              停止
													</td>
										        </c:if>			
											
										
											<td class="center">
											   ${var.isspringbean}
											</td>
											<td class="center">
													<a class="btn btn-sm btn-success" onclick="add();">新增</a>
													<a class="btn btn-sm btn-success" onclick="edit();">修改</a>
													<a class="btn btn-sm btn-danger" onclick="makeAll('确定要删除选中的数据吗?');" title="删除" ><i class='ace-icon fa fa-trash-o bigger-120'></i></a>
													<c:if test="${var.state == 1 }">
													<a class="btn btn-sm btn-success" onclick="statrt();">启动</a>
													</c:if>
													<c:if test="${var.state  == 0 }">
													<a class="btn btn-sm btn-success" onclick="end();">停止</a>
													</c:if>
													
											</td>
											
										</tr>
									
									</c:forEach>
								</c:when>
								<c:otherwise>
									<tr class="main_info">
										<td colspan="100" class="center" >没有相关数据</td>
									</tr>
								</c:otherwise>
							</c:choose>
							</tbody>
						</table>
					</form>
					
					</div>
					<!-- /.col -->
				</div>
				<!-- /.row -->
			</div>
			<!-- /.page-content -->
		</div>
	</div>
	<!-- /.main-content -->

	<!-- 返回顶部 -->
	<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
		<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
	</a>

	</div>
	<!-- /.main-container -->

	<!-- basic scripts -->
	<!-- 页面底部js¨ -->
	<%@ include file="../system/index/foot.jsp"%>
	<!-- ace scripts -->
	<script src="static/ace/js/ace/ace.js"></script>
	<!-- 删除时确认窗口 -->
	<script src="static/ace/js/bootbox.js"></script>
	<!-- ace scripts -->
	<script src="static/ace/js/ace/ace.js"></script>
	<!-- 下拉框 -->
	<script src="static/ace/js/chosen.jquery.js"></script>
	<!--提示框-->
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
	<script type="text/javascript">
		$(top.hangge());
		
		
		//新增
		function add(){
			
		}
		
		//删除
		function del(Id){
			
		}
		
		//修改
		function edit(Id){
			
		}
		
		$(function(){ 
			//复选框全选控制
			var active_class = 'active';
			$('#simple-table > thead > tr > th input[type=checkbox]').eq(0).on('click', function(){
				var th_checked = this.checked;//checkbox inside "TH" table header
				$(this).closest('table').find('tbody > tr').each(function(){
					var row = this;
					if(th_checked) $(row).addClass(active_class).find('input[type=checkbox]').eq(0).prop('checked', true);
					else $(row).removeClass(active_class).find('input[type=checkbox]').eq(0).prop('checked', false);
				});
			});
			
		});
		
		
		//批量操作
		function makeAll(msg){
			bootbox.confirm(msg, function(result) {
				if(result) {
					var str = '';
					for(var i=0;i < document.getElementsByName('ids').length;i++)
					{
						  if(document.getElementsByName('ids')[i].checked){
						  	if(str=='') str += document.getElementsByName('ids')[i].value;
						  	else str += ',' + document.getElementsByName('ids')[i].value;
						  }
					}
					if(str==''){
						bootbox.dialog({
							message: "<span class='bigger-110'>您没有选择任何内容!</span>",
							buttons: 			
							{ "button":{ "label":"确定", "className":"btn-sm btn-success"}}
						});
						$("#zcheckbox").tips({
							side:3,
				            msg:'点这里全选',
				            bg:'#AE81FF',
				            time:8
				        });
						
						return;
					}else{
						if(msg == '确定要删除选中的数据吗?'){
							top.jzts();
							$.ajax({
								type: "POST",
								url: '<%=basePath%>command/deleteAll.do?tm='+new Date().getTime(),
						    	data: {DATA_IDS:str},
								dataType:'json',
								//beforeSend: validateData,
								cache: false,
								success: function(data){
							
								}
							});
						}
					}
				}
			});
		}
		
		
		</script>
		
</body>
</html>

