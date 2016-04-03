<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta charset="UTF-8">
<title>项目数据管理分析管理系统</title>
<meta name="description" content="overview &amp; stats">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
<link rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/assets/font-awesome/4.2.0/css/font-awesome.min.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/assets/fonts/fonts.googleapis.com.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style">
<link rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/jquery-ui.min.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/jquery.datetimepicker.css"/>
<script src="<%=request.getContextPath() %>/assets/js/ace-extra.min.js"></script>
<style type="text/css">
.jqstooltip { position: absolute;left: 0px;top: 0px;visibility: hidden;background: rgb(0, 0, 0) transparent;background-color: rgba(0,0,0,0.6);filter:progid:DXImageTransform.Microsoft.gradient(startColorstr=#99000000, endColorstr=#99000000);-ms-filter: "progid:DXImageTransform.Microsoft.gradient(startColorstr=#99000000, endColorstr=#99000000)";color: white;font: 10px arial, san serif;text-align: left;white-space: nowrap;padding: 5px;border: 1px solid white;z-index: 10000;}.jqsfield { color: white;font: 10px arial, san serif;text-align: left;}
</style>
</head>
<body class="no-skin">
<div id="navbar" class="navbar navbar-default">
	<script type="text/javascript">
		try{ace.settings.check('navbar' , 'fixed')}catch(e){}
	</script>
		<div class="navbar-container" id="navbar-container">
			<button type="button" class="navbar-toggle menu-toggler pull-left"
				id="menu-toggler" data-target="#sidebar">
				<span class="sr-only">Toggle sidebar</span> <span class="icon-bar"></span>
				<span class="icon-bar"></span> <span class="icon-bar"></span>
			</button>
			<div class="navbar-header pull-left">
				<a href="index.html" class="navbar-brand"> <small> <i
						class="fa fa-bar-chart"></i> 项目数据管理分析管理系统
				</small>
				</a>
			</div>
			<div class="navbar-buttons navbar-header pull-right" role="navigation">
				<ul class="nav ace-nav">
					<li class="light-blue">
						<a data-toggle="dropdown" href="#" class="dropdown-toggle"> 
							<img class="nav-user-photo" src="<%=request.getContextPath()%>/assets/avatars/user.jpg" alt="Jason's Photo"> 
							<span class="user-info"> <small>Welcome,</small> Admin</span> 
							<i class="ace-icon fa fa-caret-down"></i>
						</a>
						<ul
							class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
							<li><a href="#"> <i class="ace-icon fa fa-power-off"></i>退出</a></li>
						</ul>
					</li>
				</ul>
			</div>
		</div>
</div>
	<div class="main-container" id="main-container">
		<script type="text/javascript">
		try{ace.settings.check('main-container' , 'fixed')}catch(e){}
	</script>
		<div id="sidebar" class="sidebar responsive" data-sidebar="true"
			data-sidebar-scroll="true" data-sidebar-hover="true">
			<script type="text/javascript">
			try{ace.settings.check('sidebar' , 'fixed')}catch(e){}
		</script>
			<div class="sidebar-shortcuts" id="sidebar-shortcuts">
				<div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
					<button class="btn btn-success">
						<i class="ace-icon fa fa-gamepad"></i>
					</button>
					<button class="btn btn-info">
						<i class="ace-icon fa fa-video-camera"></i>
					</button>
					<button class="btn btn-warning">
						<i class="ace-icon fa fa-user"></i>
					</button>
					<button class="btn btn-danger">
						<i class="ace-icon fa fa-hospital-o"></i>
					</button>
				</div>
				<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
					<span class="btn btn-success"></span> <span class="btn btn-info"></span>
					<span class="btn btn-warning"></span> <span class="btn btn-danger"></span>
				</div>
			</div>
			<ul class="nav nav-list">
				<c:forEach items="${responsMenuList}" var="menu" varStatus="status">
					<li><a href="#" class="dropdown-toggle"> <i
							class="menu-icon fa fa-gamepad"></i> <span class="menu-text">${menu.menuName}</span>
							<b class="arrow fa fa-angle-down"></b>
					</a> <b class="arrow"></b>
						<ul class="submenu">
							<li class="active"><c:if test="${!empty menu.childMenu}">
									<c:forEach items="${menu.childMenu}" var="childMenu">
										<ul class="submenu">
											<li class=""><a href="${childMenu.menuLink}"
												target="_self"><i class="menu-icon fa fa-caret-right"></i>${childMenu.menuName}</a>
												<b class="arrow"></b></li>
										</ul>
									</c:forEach>
								</c:if> <b class="arrow"></b></li>
						</ul></li>
				</c:forEach>

			</ul>
			<div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
				<i class="ace-icon fa fa-angle-double-left"
					data-icon1="ace-icon fa fa-angle-double-left"
					data-icon2="ace-icon fa fa-angle-double-right"></i>
			</div>
			<script type="text/javascript">
			try{ace.settings.check('sidebar' , 'collapsed')}catch(e){}
		</script>
		</div>
		<div class="main-content">
			<div class="main-content-inner">
				<div class="breadcrumbs" id="breadcrumbs">
					<script type="text/javascript">
					try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
				</script>
					<ul class="breadcrumb">
						<li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">数据分析</a>
						</li>
						<li class="active">报表统计</li>
					</ul>
				</div>
				<div class="page-content">
					<div class="page-header">
						<h1>项目对比</h1>
					</div>
					<div class="row">
						<div class="col-xs-12">
							<form class="form-horizontal form-addGame" action="<%=request.getContextPath() %>/department/find-department-stas" enctype="multipart/form-data">
								<div class="form-group">
									<label class="col-xs-12 col-sm-3 control-label">开始时间</label>
									<div class="col-xs-12 col-sm-5">
										<span class="block input-icon input-icon-right">
											<div class="input-group">
												<input type="text" name="startDate" value="${startDate }" class="form-control form-start" readonly="readonly">
												<span class="input-group-addon" style="cursor: pointer;">
													<i class="fa fa-calendar bigger-110"></i>
												</span>
											</div>
										</span>
									</div>
								</div>
								<div class="form-group">
									<label class="col-xs-12 col-sm-3 control-label">结束时间</label>
									<div class="col-xs-12 col-sm-5">
										<span class="block input-icon input-icon-right">
											<div class="input-group">
												<input type="text" name="endDate" value="${ endDate}" class="form-control form-end" readonly="readonly">
												<span class="input-group-addon" style="cursor: pointer;">
													<i class="fa fa-calendar bigger-110"></i>
												</span>
											</div>
										</span>
									</div>
								</div>
								<div class="clearfix form-actions">
									<div class="col-md-offset-3 col-md-8">
										<button class="btn btn-info submit" type="button" onclick="departmentForm();">
											<i class="ace-icon fa fa-search bigger-110"></i> 查询
										</button>
									</div>
								</div>
							</form>
							<div class="page-header">
								<h1>IE事业群各项目合计数值</h1>
							</div>
							<form action="" method="post">
								<table class="table table-striped table-bordered table-hover table-gameManager">
									<thead>
										<tr>
											<th>总收入</th>
											<th>独立访问用户</th>
											<th class="hidden-480">点击量</th>
											<th class="hidden-70">新增注册用户数</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${departStasList}" var="department">
											<tr>
												<td>${department.incomeStas}</td>
												<td>${department.viewUserNum}</td>
												<td>${department.clickNum}</td>
												<td>${department.newRegisterUserNum}</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</form>
							<div class="form-group">
								<div class="s_chart_1" class="col-xs-6"
									style="width: 600px; height: 300px; margin: 0 auto; float: left;"></div>
								<div class="s_chart_2" class="col-xs-6"
									style="width: 600px; height: 300px; margin: 0 auto; float: left;"></div>
								<div class="s_chart_3" class="col-xs-6"
									style="width: 600px; height: 300px; margin: 0 auto; float: left;"></div>
								<div class="s_chart_4" class="col-xs-6"
									style="width: 600px; height: 300px; margin: 0 auto; float: left;"></div>
							</div>
							<div class="col-xs-12" style="clear: both;">
								<div class="page-header">
									<h1>IE事业群各项目数值</h1>
								</div>
								<form id="game_form" action="" method="post">
									<table
										class="table table-striped table-bordered table-hover table-gameManager">
										<thead>
											<tr>
												<th>项目名称</th>
												<th>总收入</th>
												<th class="hidden-480">注册用户数</th>
												<th>新增注册用户数</th>
												<th class="hidden-480">独立用户访客</th>
												<th class="hidden-70">浏览量</th>
												<th class="hidden-70">登录用户数</th>
											</tr>
										</thead>
										<tbody>

											<c:forEach items="${departList}" var="department">
												<tr>
													<td>${department.projectName}</td>
													<td>${department.incomeStas}</td>
													<td>${department.registerUserNum}</td>
													<td>${department.newRegisterUserNum}</td>
													<td>${department.viewUserNum}</td>
													<td>${department.viewNum}</td>
													<td>${department.loginUserNum}</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="footer">
			<div class="footer-inner">
				<div class="footer-content">
					<span class="bigger-120"> <span class="blue">Version V1.0.0 &nbsp;&nbsp;2016</span></span>
				</div>
			</div>
		</div>
		<a href="#" id="btn-scroll-up"
			class="btn-scroll-up btn btn-sm btn-inverse"> <i
			class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
		</a>
	</div>
<script src="<%=request.getContextPath() %>/assets/js/jquery.2.1.1.min.js"></script>
<script type="text/javascript">
	window.jQuery || document.write("<script src='<%=request.getContextPath() %>/assets/js/jquery.min.js'>" + "<"+"/script>");
</script>
<script type="text/javascript">
	if('ontouchstart' in document.documentElement) document.write("<script src='<%=request.getContextPath() %>/assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
</script>
<script src="<%=request.getContextPath() %>/assets/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath() %>/assets/js/jquery-ui.custom.min.js"></script>
<script src="<%=request.getContextPath() %>/assets/js/jquery-ui.min.js"></script>
<script src="<%=request.getContextPath() %>/assets/js/jquery.ui.touch-punch.min.js"></script>
<script src="<%=request.getContextPath() %>/assets/js/jquery.easypiechart.min.js"></script>
<script src="<%=request.getContextPath() %>/assets/js/jquery.sparkline.min.js"></script>
<script src="<%=request.getContextPath() %>/assets/js/jquery.flot.min.js"></script>
<script src="<%=request.getContextPath() %>/assets/js/jquery.flot.pie.min.js"></script>
<script src="<%=request.getContextPath() %>/assets/js/jquery.flot.resize.min.js"></script>
<script src="<%=request.getContextPath() %>/assets/js/jquery.datetimepicker.min.js"></script>
<script src="<%=request.getContextPath() %>/assets/js/bootbox.js"></script>
<script src="<%=request.getContextPath() %>/assets/js/highcharts.js"></script>
<script src="<%=request.getContextPath() %>/assets/js/highcharts-more.js"></script>
<script src="<%=request.getContextPath() %>/assets/js/highcharts-3d.js"></script>
<script src="<%=request.getContextPath() %>/assets/js/ace-elements.min.js"></script>
<script src="<%=request.getContextPath() %>/assets/js/ace.min.js"></script>
<script type="text/javascript">
jQuery(function($) {
		//selectmenu
	  
	$(".number").css({
			width : '543px'
		}).selectmenu({
			position : {
				my : "left top",
				at : "left bottom"
			}
		})
		//time
		$('.form-start').datetimepicker({
			minDate : '0',
			"timePicker" : true,
			"startDate" : "",
			"endDate" : "",
		});
		$('.form-start,.form-end').datetimepicker({
			'timepicker' : false,
			'datepicker' : true,
			'format' : 'Y-m-d'
		});
		//饼形统计
		//one
		findDepartment();

	})

	function findDepartment() {
	
		$.ajax({
			dataType : 'json',
			contentType : "application/json;charset=UTF-8",
			url : "/tsied_front/department/find-department-chart-stats?startDate="
					+ $("#startDate").val()
					+ "&endDate="
					+ $("#endDate").val(),
		success : function(data) {
			$('.s_chart_1').highcharts(
				{
					chart : {
						type : 'pie',
						options3d : {
							enabled : true,
							alpha : 45,
							beta : 0
						},
						plotBackgroundColor : null,
						plotBorderWidth : null,
						plotShadow : false
					},
					title : {
						text : 'IE事业群总收入各项目占比'
					},
					tooltip : {
						pointFormat : '{series.name}: <b>{point.percentage:.1f}%</b>'
					},
					plotOptions : {
						pie : {
							allowPointSelect : true,
							cursor : 'pointer',
							depth : 35,
							dataLabels : {
								enabled : true,
								format : '{point.name}'
							},
							dataLabels : {
								enabled : true,
								color : '#000000',
								connectorColor : '#000000',
								format : '<b>{point.name}</b>: {point.percentage:.1f} %'
							},
							showInLegend : true
						}
					},
					series : [ {
						name : "数据百分比",
						colorByPoint : true,
						data : data.incomeStas
					} ]
				})
			//two
			$('.s_chart_2').highcharts({
					chart : {
						type : 'pie',
						options3d : {
							enabled : true,
							alpha : 45,
							beta : 0
						},
						plotBackgroundColor : null,
						plotBorderWidth : null,
						plotShadow : false
					},
					title : {
						text : 'IE事业群点击量PV各项目占比'
					},
					tooltip : {
						pointFormat : '{series.name}: <b>{point.percentage:.1f}%</b>'
					},
					plotOptions : {
						pie : {
							allowPointSelect : true,
							cursor : 'pointer',
							depth : 35,
							dataLabels : {
								enabled : true,
								format : '{point.name}'
							},
							dataLabels : {
								enabled : true,
								color : '#000000',
								connectorColor : '#000000',
								format : '<b>{point.name}</b>: {point.percentage:.1f} %'
							},
							showInLegend : true
						}
					},
					series : [ {
						name : "数据百分比",
						colorByPoint : true,
						data : data.pvStas
					} ]
				})
			//three
			$('.s_chart_3').highcharts(
				{
					chart : {
						type : 'pie',
						options3d : {
							enabled : true,
							alpha : 45,
							beta : 0
						},
						plotBackgroundColor : null,
						plotBorderWidth : null,
						plotShadow : false
					},
					title : {
						text : 'IE事业群独立用户访客各项目占比'
					},
					tooltip : {
						pointFormat : '{series.name}: <b>{point.percentage:.1f}%</b>'
					},
					plotOptions : {
						pie : {
							allowPointSelect : true,
							cursor : 'pointer',
							depth : 35,
							dataLabels : {
								enabled : true,
								format : '{point.name}'
							},
							dataLabels : {
								enabled : true,
								color : '#000000',
								connectorColor : '#000000',
								format : '<b>{point.name}</b>: {point.percentage:.1f} %'
							},
							showInLegend : true
						}
					},
					series : [ {
						name : "数据百分比",
						colorByPoint : true,
						data : data.viewStas
					} ]
				})
			//four
			$('.s_chart_4').highcharts(
				{
					chart : {
						type : 'pie',
						options3d : {
							enabled : true,
							alpha : 45,
							beta : 0
						},
						plotBackgroundColor : null,
						plotBorderWidth : null,
						plotShadow : false
					},
					title : {
						text : 'IE事业群注册用户数各项目占比'
					},
					tooltip : {
						pointFormat : '{series.name}: <b>{point.percentage:.1f}%</b>'
					},
					plotOptions : {
						pie : {
							allowPointSelect : true,
							cursor : 'pointer',
							depth : 35,
							dataLabels : {
								enabled : true,
								format : '{point.name}'
							},
							dataLabels : {
								enabled : true,
								color : '#000000',
								connectorColor : '#000000',
								format : '<b>{point.name}</b>: {point.percentage:.1f} %'
							},
							showInLegend : true
						}
					},
					series : [ {
						name : "数据百分比",
						colorByPoint : true,
						data : data.registerStas
					} ]
				})
			}
		})
	}

	function departmentForm() {
		$("#departmentForm").submit();
		findDepartment();
	}
</script>
<div class="tooltip top in" style="display: none;">
	<div class="tooltip-inner">
	</div>
</div>
</body>
</html>