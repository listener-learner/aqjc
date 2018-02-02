<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tyust.bean.user.TBaseUserInfo"%>
<%@ page import="com.tyust.bean.unit.TBaseUnitInfo"%>
<%@ page
	import="org.springframework.context.ApplicationContext,org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@ page import="com.tyust.dao.unit.IunitDao"%>
<%@ page import="com.tyust.dao.user.IuserDao"%>
<%@ page import="java.util.*,com.tyust.common.DateHandler"%>
<%
	ApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getSession().getServletContext());
	
	String pbsApplyId = request.getParameter("pbsApplyId");
	String pbsApplyUnitId = request.getParameter("pbsApplyUnitId");
	String pbsApplyUserId = request.getParameter("pbsApplyUserId");
	// 获取申请部门信息
	IunitDao unitDao = (IunitDao)context.getBean("Unit");
	TBaseUnitInfo unit = unitDao.findUnit(pbsApplyUnitId);
	
%>
<!DOCTYPE html>
<html>
<head>
<title>屏蔽室申请</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">

<link rel="stylesheet"	href="<%=request.getContextPath() %>/newStyle/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"	href="<%=request.getContextPath() %>/newStyle/font-awesome-4.5.0/css/font-awesome.min.css">
<link rel="stylesheet"	href="<%=request.getContextPath() %>/newStyle/ionicons-2.0.1/css/ionicons.min.css">
<link rel="stylesheet"	href="<%=request.getContextPath() %>/newStyle/dist/css/AdminLTE.min.css">
<link rel="stylesheet"	href="<%=request.getContextPath() %>/newStyle/plugins/iCheck/flat/blue.css">
<link rel="stylesheet"	href="<%=request.getContextPath() %>/newStyle/dist/css/skins/skin-blue.min.css">
<link rel="stylesheet"	href="<%=request.getContextPath() %>/newStyle/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/newStyle/plugins/select2/select2.css">

<link rel="stylesheet" type="text/css"	href="<%=request.getContextPath() %>/newStyle/plugins/lightbox/css/lightbox.min.css" />
<link rel="stylesheet" type="text/css" 	href="<%=request.getContextPath() %>/newStyle/plugins/bootstrap-fileinput/css/fileinput.min.css" />
<style>
.dataTab input {
	width: 220px;
	height: 23px;
}

.dataTab select {
	width: 110px;
	height: 22px;
}
</style>

</head>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">

			<!-- Main content -->
			<section class="content">
				<div class="row">
					<div class="col-md-12">

						<div class="box box-solid box-primary">
							<div class="box-header with-border">
								<h3 class="box-title">屏蔽室检测申请</h3>
							</div>
							<!-- /.box-header -->

							<div class="box-body pad">
								<div class="row">
									<div class="col-md-8 col-md-offset-2">
										<div class="box box-info">
											<div class="box-header with-border">
												<h3 class="box-title">基本信息</h3>
											</div>
											<div class="box-body">
												<form id="pbsApplyInfo_form" class="form-horizontal">
													<div class="row">
														<div class="col-sm-6">
															<div class="form-group">
																<label for="input1" class="col-sm-4 control-label">申请单位</label>
																<div class="col-sm-8">
																	<select id="pbsApplyUnitName"
																		name="pbsApplyInfo.pbsApplyUnitId"
																		class="form-control select2" style="width: 100%;">
																	</select>
																</div>
															</div>
														</div>
														<div class="col-sm-6">
															<div class="form-group">
																<label for="pbsApplyDate" class="col-sm-4 control-label">申请日期</label>
																<div class="col-sm-8">
																	<input id="pbsApplyDate"
																		name="pbsApplyInfo.pbsApplyDate" type="text"
																		class="form-control"
																		onclick="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})">
																</div>
															</div>
														</div>
													</div>

													<div class="row">
														<div class="col-sm-6">
															<div class="form-group">
																<label for="input1" class="col-sm-4 control-label">申请人</label>
																<div class="col-sm-8">
																	<!-- 隐藏 -->
																	<input name="pbsApplyInfo.pbsApplyUserId"
																		value="<%=pbsApplyUserId %>" type="hidden"> <input
																		id="pbsApplyUserName"
																		name="pbsApplyInfo.pbsApplyUserName" type="text"
																		class="form-control">
																</div>
															</div>
														</div>
														<div class="col-sm-6">
															<div class="form-group">
																<label for="pbsApplyTel" class="col-sm-4 control-label">联系方式</label>
																<div class="col-sm-8">
																	<input id="pbsApplyTel" type="text"
																		name="pbsApplyInfo.pbsApplyTel" class="form-control">
																</div>
															</div>
														</div>
													</div>
													<!-- 屏蔽室申请id -->
													<input type="hidden" id="pbsApplyId"
														name="pbsApplyInfo.pbsApplyId" value="<%=pbsApplyId%>">
													<!-- 隐藏 操作：保存与提交-->
													<input type="hidden" id="operate" name="operate"
														value="save" />
												</form>
											</div>
										</div>

										<div class="box box-info">
											<div class="box-header with-border">
												<h3 class="box-title">申请书扫描照片PDF文件</h3>
											</div>
											<div class="box-body">
												<div class="row" id="file1">
													<div class="col-sm-8">
														<div id="fileName1"></div>
													</div>
													<div class="col-sm-4">
														<button style="float: right;" id="delAttach1"
															class="btn btn-info">删除</button>
													</div>
												</div>
											</div>
										</div>

										<div class="box box-info">
											<div class="box-header with-border">
												<h3 class="box-title">申请书电子版</h3>
											</div>
											<div class="box-body">
												<div class="row" id="file2">
													<div class="col-sm-8">
														<div id="fileName2"></div>
													</div>
													<div class="col-sm-4">
														<button style="float: right;" id="delAttach2"
															class="btn btn-info">删除</button>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<!-- /.box-body -->
							<div class="box-footer">
								<center>
									<button id="save" type="button" class="btn btn-default">保存</button>
									<button id="submit" type="button" style="margin-left: 20px;"
										class="btn btn-info">提交</button>
								</center>
							</div>
						</div>
						<!-- /.box -->

					</div>
					<!-- /.col -->
				</div>
				<!-- /.row -->
			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->

	</div>
	<!-- ./wrapper -->
	<script
		src="<%=request.getContextPath() %>/newStyle/plugins/jQuery/jQuery-2.1.4.min.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath() %>/newStyle/plugins/lightbox/js/lightbox-plus-jquery.min.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath() %>/newStyle/plugins/bootstrap-fileinput/js/fileinput.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath() %>/newStyle/plugins/bootstrap-fileinput/js/fileinput_locale_zh.js"></script>
	<script
		src="<%=request.getContextPath() %>/controls/JCalendar/WdatePicker.js"></script>
	
	<script type="text/javascript" src="<%=request.getContextPath() %>/newStyle/bootstrap/js/bootstrap.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath() %>/newStyle/plugins/select2/select2.full.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/newStyle/dist/js/app.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/newStyle/dist/js/demo.js"></script>
	
	<script>
	$(function() {
		showPbsApplyInfo();
		showPbsAttach1();
		showPbsAttach2();
		loadUnitName();
	});
	
	//以下变量表示是否进行了附件的修改，0：未修改；1：修改
	var isAttach1 = 0;
	var isAttach2 = 0;
	
	// 显示屏蔽室申请信息
	function showPbsApplyInfo() {
		$.ajax({
			url : '${pageContext.request.contextPath}/pbsApplyInfo_selPbsApplyInfoById.do',
			data : {'pbsApplyId' : '<%= pbsApplyId%>'},
			type : 'POST',
			dataType : 'json',
			success : function(response) {
				var info = eval(response);
				$("#pbsApplyDate").val(info.pbsApplyDate);
				$("#pbsApplyUserName").val(info.pbsApplyUserName);
				$("#pbsApplyTel").val(info.pbsApplyTel);
			}
		});
	}
	
	
	function showPbsAttach1(){
		$.ajax({
			url : '${pageContext.request.contextPath}/pbsApplyInfo_selPbsAttach.do',
			data : {'pbsApplyId' : '<%= pbsApplyId%>',
				'pbsAttachType' : '1'},
			type : 'POST',
			dataType : 'json',
			success : function(response) {
				var info = eval(response);
				var v = info[0];
				if (v == null) {
					$("#file1").empty();
					loadFileinput1();
				} else {
					var url = info[0].fileUrl;
					var fileName = info[0].fileName;
					var pbsAttachId = info[0].pbsAttachId;
					var URL = '${pageContext.request.contextPath}/pbsApplyInfo_downAttach.do?filename='+url;
					$("#fileName1").append("<a href='"+URL+"'>"+fileName+"</a>");
					$("#delAttach1").click(function (){
						delAttachConf(pbsAttachId,"1");
					});
				}
			}
		});
	}
	
	function showPbsAttach2(){
		$.ajax({
			url : '${pageContext.request.contextPath}/pbsApplyInfo_selPbsAttach.do',
			data : {'pbsApplyId' : '<%= pbsApplyId%>',
				'pbsAttachType' : '2'},
			type : 'POST',
			dataType : 'json',
			success : function(response) {
				var info = eval(response);
				var v = info[0];
				if (v == null) {
					$("#file2").empty();
					loadFileinput2();
				} else {
					var url = info[0].fileUrl;
					var fileName = info[0].fileName;
					var pbsAttachId = info[0].pbsAttachId;
					var URL = '${pageContext.request.contextPath}/pbsApplyInfo_downAttach.do?filename='+url;
					$("#fileName2").append("<a href='"+URL+"'>"+fileName+"</a>");
					$("#delAttach2").click(function (){
						delAttachConf(pbsAttachId,'2');
					});
				}
			}
		});
	}
	
	$(document).ready(function(){
		$("#save").click(function (){
			$("#operate").val("save");
			saveInfo();
		});
		$("#submit").click(function (){
			$("#operate").val("submit");
			saveInfo();
		});
	});
	
	// 保存信息
	function saveInfo(){
		if(isAttach1 == 1){
			if($("#attach1").val() == ""){
				alert("请上传申请书扫描照片PDF文件");
				return;
			}
		}
		if(isAttach2 == 1){
			if($("#attach2").val() == ""){
				alert("请上传申请书电子版");
				return;
			}
		}
		$.ajax({
			url : '<%=request.getContextPath()%>/pbsApplyInfo_savePbsApplyInfo.do',
			type : 'POST',
			dataType : 'json',
			data : $("#pbsApplyInfo_form").serializeArray(),
			success : function(data){
				var data = eval(data);
				if (data.result == "success"){
					// 获取保存好的屏蔽室申请ID
					alert("isAttach1="+isAttach1+" #attach1.val()= "+$("#attach1").val());
					alert("isAttach2="+isAttach2+" #attach2.val()= "+$("#attach2").val());
					
					var pbsApplyId = data.pbsApplyId;
					// 附件上传
					if (isAttach1 == 1 && $("#attach1").val() != ""){
						fileUpload1(pbsApplyId);
					}
					if (isAttach2 == 1 && $("#attach2").val() != ""){
						fileUpload2(pbsApplyId);
					}
					window.location.href="apply_list.jsp";
					alert("保存成功！");
			  	}
			},
			error : function(data){
				alert("保存失败");
			}
		});
	}
	
	
	//附件上传功能:传入申请id作为参数
	function fileUpload1(id){
		var v = $("#attach1").val();
		if (v != "") {
			//设置屏蔽室申请ID
			$("#attach_pbsApplyId1").val(id);
			var formData = new FormData($('#attach_form1')[0]);
			$.ajax({
			       url: '${pageContext.request.contextPath}/pbsApplyInfo_saveAttach.do',
			       data: formData,
			       async: false,
			       contentType: false,
			       processData: false,
			       cache: false,
			       type: 'post',
			       success: function(data) {
			    	   alert("附件上传成功！");
			       },
			       error : function(){
			    	   alert("附件上传失败，请重试！");
			       }
			   });
		}
		
	}
	function fileUpload2(id){
		var v = $("#attach2").val();
		if (v != "") {
			//设置屏蔽室申请ID
			$("#attach_pbsApplyId2").val(id);
			var formData = new FormData($('#attach_form2')[0]);
			$.ajax({
			       url: '${pageContext.request.contextPath}/pbsApplyInfo_saveAttach.do',
			       data: formData,
			       async: false,
			       contentType: false,
			       processData: false,
			       cache: false,
			       type: 'post',
			       success: function(data) {
			    	   alert("附件上传成功！");
			       },
			       error : function(){
			    	   alert("附件上传失败，请重试！");
			       }
			   });
		}
	}
	
	// 确认删除附件 
	function delAttachConf(pbsAttachId,pbsAttachType){
		var con = confirm("是否确认删除附件？");
		if (con == true) {
			delAttach(pbsAttachId,pbsAttachType);
		}
	}
	
	// 根据附件编号删除附件
	function delAttach(pbsAttachId,pbsAttachType){
		$.ajax({
			url : '${pageContext.request.contextPath}/pbsApplyInfo_delAttach.do',
			type : 'POST',
			data : {'pbsAttachId' : pbsAttachId},
			dataType : 'json',
			success : function (data){
				var data = eval(data);
				if (data.result == 'success'){
					
					// 加载上传附件
					if(pbsAttachType == '1'){
						// 清空
						$("#file1").empty();
						loadFileinput1();
					} else {
						// 清空
						$("#file2").empty();
						loadFileinput2();
					}
				}
			},
			error : function (){
				alert("删除附件失败");
			}
		});
	}
	
	function loadFileinput1(){
		var dom = '<form id="attach_form1" enctype="multipart/form-data">';
  	 	dom = dom + '<input id="attach_pbsApplyId1" name="pbsApplyInfo.pbsApplyId" type="hidden"/>';
  	 	dom = dom + '<input name="pbsAttach.pbsAttachType" type="hidden" value="1"/>';
		dom = dom + '<input id="attach1" name="pbsApplyInfoAttach" class="file" type="file" multiple data-min-file-count="1">';
		dom = dom + '</form>';
		$("#file1").html(dom);
		loadStyle1();
		isAttach1 = 1;
	}
	
	function loadFileinput2(){
		var dom = '<form id="attach_form2" enctype="multipart/form-data">';
  	 	dom = dom + '<input id="attach_pbsApplyId2" name="pbsApplyInfo.pbsApplyId" type="hidden"/>';
  	 	dom = dom + '<input name="pbsAttach.pbsAttachType" type="hidden" value="2"/>';
		dom = dom + '<input id="attach2" name="pbsApplyInfoAttach" class="file" type="file" multiple data-min-file-count="1">';
		dom = dom + '</form>';
		$("#file2").html(dom);
		loadStyle2();
		isAttach2 = 1;
	}
	//准备附件上传的样式
	function loadStyle1(){
		$("#attach1").fileinput({
			language : 'zh',
			showUpload : false, //是否显示上传按钮
			allowedFileExtensions : [ 'pdf' ]
		});
	}
	
	function loadStyle2(){
		$("#attach2").fileinput({
			language : 'zh',
			showUpload : false, //是否显示上传按钮
			allowedFileExtensions : [ 'doc', 'docx' ]
		});
	}
	
	// 加载部门名称
	function loadUnitName(){
		$.ajax({
			url : '<%=request.getContextPath()%>/loadUnitName.do',
			type : 'POST',
			dataType : 'json',
			success : function(data){
				var data = eval(data);
				var optionStr = "";
				for(var i=0;i<data.length;i++){
					if(data[i].unitId == <%=pbsApplyUnitId %>){
						optionStr += "<option value="+data[i].unitId+"  selected>"+data[i].unitName+"</option>";
					}
					else {
						optionStr += "<option value="+data[i].unitId+" >"+data[i].unitName+"</option>";
					}
				}
			
				$("#pbsApplyUnitName").append(optionStr);
				var select  = $(".select2").select2();
				
			},
			error : function(data){
				alert("error");
			}
		});
	}

	//Initialize Select2 Elements
	
</script>
</body>
</html>