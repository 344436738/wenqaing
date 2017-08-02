

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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

<!-- jsp文件头和头部 -->
<%@ include file="../index/top.jsp"%>
<!-- 百度echarts -->

<link rel="stylesheet" type="text/css" href="uploadify/uploadify.css"> 

<script src="plugins/echarts/echarts.min.js"></script>
<script type="text/javascript" src="static/ace/js/jquery.js"></script>
<script src="uploadify/jquery.uploadify.min.js"></script>
</head>
<body class="no-skin">

	<!-- /section:basics/navbar.layout -->
	<div class="main-container" id="main-container">
		<!-- /section:basics/sidebar -->
		<div class="main-content">
			<div class="main-content-inner">
				<div class="page-content">
					<div class="hr hr-18 dotted hr-double"></div>
					<div class="row">
						<div class="col-xs-12">

					<div class="knowledgemodifyload">
				        <span style="float:left; margin-right: 20px;" >文件上传:</span>
				        <span><a href="javascript:$('#file_upload').uploadify('upload', '*')">上传文件</a></span>  
				     </div>   
				    <div class="knowledgeUpload"  style="float: left; width: 80%; clear: none; padding-top: 0;">
				        <div id="queue">
				        	<div class='updaimg' id="img">
				        		
				        	</div>	
				        	
				        </div><!-上传队列展示区-->  	
					 	<div class="knowledgemodifyloaddes" id="pdf"></div>
				        <input   type="file" name="file_upload" id="file_upload"/>
				        <span>可以上传word、excel、ppt、pdf、jpg/jpeg、tiff、bmp、gif、png等图像文件、视频文件</span>
				        <div style="padding-top: 15px;">支持一次上传多个文件,图像文件需要生成缩略图</div>
				    </div>
							
							
							<div id="main" style="width: 600px;height:300px;"></div>
							<script type="text/javascript">
							$(document).ready(function(){
								var html="";
								 $('#file_upload').uploadify({
							            'swf'      : "uploadify/uploadify.swf",
							            'uploader' : '<%=path%>/action/file/uploadFile',
							        	'cancelImg' :	"uploadify/cancel.png",
							            'height': 25,
							            'whith' :120,
							            'auto'  : false,
							            'fileDataName':'file',
							            
							            'buttonText' : '选择文件...',
							            'fileTypeExts' : '*.doc; *.docx; *.ppt; *.pdf; *.jpg; *.jpeg; *.tiff; *.bmp; *.gif; *.png',
							            'multi'    : true,
							            'method'   :'post',
							            'debug':false,
							            'simUploadLimit':6,
							            'onUploadSuccess' : function(file,data, response) {
							            
							            	var obj = jQuery.parseJSON(data);
							            alert(obj.url)
							            		 html ="<div class='updaimg-img'><img src='"+obj.url+"' alt='' widht='200' height='200'/></div>";
							            		 $("#img").append(html);
							            			//图片预览
							            
							            	
							            
							               } 
							        });
							});
						    </script>
							
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

	<!-- ace scripts -->
	<!-- inline scripts related to this page -->
	<script type="text/javascript">
		$(top.hangge());
	</script>
</body>
</html>



















