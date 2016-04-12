<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta charset="utf-8">
<title>项目数据管理分析管理系统</title>
<meta name="description" content="overview &amp; stats">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
<link rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/assets/font-awesome/4.2.0/css/font-awesome.min.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/assets/fonts/fonts.googleapis.com.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style">
<link rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/jquery-ui.min.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/jquery.datetimepicker.css"/>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/jquery.dataTables.min.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/buttons.dataTables.min.css">
<style type="text/css">
.jqstooltip { position: absolute;left: 0px;top: 0px;visibility: hidden;background: rgb(0, 0, 0) transparent;background-color: rgba(0,0,0,0.6);filter:progid:DXImageTransform.Microsoft.gradient(startColorstr=#99000000, endColorstr=#99000000);-ms-filter: "progid:DXImageTransform.Microsoft.gradient(startColorstr=#99000000, endColorstr=#99000000)";color: white;font: 10px arial, san serif;text-align: left;white-space: nowrap;padding: 5px;border: 1px solid white;z-index: 10000;}.jqsfield { color: white;font: 10px arial, san serif;text-align: left;}
</style>
<script src="<%=request.getContextPath() %>/assets/js/ace-extra.min.js"></script>
<script src="<%=request.getContextPath() %>/assets/js/jquery.2.1.1.min.js"></script>
<script type="text/javascript">
	window.jQuery || document.write("<script src='<%=request.getContextPath() %>/assets/js/jquery.min.js'>"+"<"+"/script>");
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
<script src="<%=request.getContextPath() %>/assets/js/ace-elements.min.js"></script>
<script src="<%=request.getContextPath() %>/assets/js/ace.min.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery.battatech.excelexport.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery.battatech.excelexport.min.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery.dataTables.min.js"></script>
<script src="<%=request.getContextPath()%>/js/dataTables.buttons.min.js"></script>
<script src="<%=request.getContextPath()%>/js/buttons.flash.min.js"></script>
<script src="<%=request.getContextPath()%>/js/jszip.min.js"></script>
<script src="<%=request.getContextPath()%>/js/pdfmake.min.js"></script>
<script src="<%=request.getContextPath()%>/js/vfs_fonts.js"></script>
<script src="<%=request.getContextPath()%>/js/buttons.html5.min.js"></script>
<script src="<%=request.getContextPath()%>/js/buttons.print.min.js"></script>
<script type="text/javascript">

	function submitConsumptionForm() {
		$("#consumptionForm").submit();
	}

	jQuery(function($) {
		$('select').css({
			width : '543px'
		}).selectmenu({
			position : {
				my : "left top",
				at : "left bottom"
			}
		})

		$('.form-start,.form-end').datetimepicker({
			'timepicker' : false,
			'datepicker' : true,
			'format' : 'Y-m-d'
		});

		$('#consumTab').DataTable({
			dom : 'Bfrtip',
			buttons : [ 'csv', 'excel']
		});
	})(window, jQuery);
</script>


</head>


<body class="no-skin">
<div id="navbar" class="navbar navbar-default">
	<script type="text/javascript">
				try{ace.settings.check('navbar' , 'fixed')}catch(e){}
			</script>
	<div class="navbar-container" id="navbar-container">
		<button type="button" class="navbar-toggle menu-toggler pull-left" id="menu-toggler" data-target="#sidebar">
		<span class="sr-only">Toggle sidebar</span>
		<span class="icon-bar"></span>
		<span class="icon-bar"></span>
		<span class="icon-bar"></span>
		</button>
		<div class="navbar-header pull-left">
			<a href="index.html" class="navbar-brand">
			<small>
			<i class="fa fa-bar-chart"></i>
							项目数据管理分析管理系统
			</small>
			</a>
		</div>
		<div class="navbar-buttons navbar-header pull-right" role="navigation">
				<ul class="nav ace-nav">
					<li class="light-blue">
						<a data-toggle="dropdown" href="#" class="dropdown-toggle"> 
							<img class="nav-user-photo" src="<%=request.getContextPath()%>/assets/avatars/user.jpg" alt="Jason's Photo"> 
							<span class="user-info"> <small>Welcome,</small>Admin</span> 
							<i class="ace-icon fa fa-caret-down"></i>
						</a>
						<ul
							class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
							<li><a href="#"> <i class="ace-icon fa fa-power-off"></i>
									退出
							</a></li>
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
			<!-- /.sidebar-shortcuts -->
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
						<li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">广告数据分析</a>
						</li>
						<li class="active">消费分析</li>
					</ul>

				</div>
				<div class="page-content">
					<div class="page-header">
						<h1>消费分析</h1>
					</div>
					<div class="row">
						<div class="col-xs-12">
							<form class="form-horizontal form-addGame" action="<%=request.getContextPath() %>/consumption/findAdvert" id="consumptionForm" name="consumptionForm" enctype="multipart/form-data" data-method="form">
								<div class="form-group">
									<label class="col-xs-12 col-sm-3 control-label">广告名称</label>
									<div class="col-xs-12 col-sm-5">
										<span class="block input-icon input-icon-right"> 
											<input type="text" name="adName" id="adName" value="${adName}" maxlength="12">
										</span>
									</div>
								</div>
								<div class="form-group">
									<label class="col-xs-12 col-sm-3 control-label">所属项目</label>
									<div class="col-xs-12 col-sm-5">
										<span class="block input-icon input-icon-right"> <select
											name="adProject" class="number" id="adProject">
												<c:forEach items="${proList}" var="pro" varStatus="status">
													<c:if test="${adProject==null }">
														<option value="${pro.dicId}">${pro.dicValue }</option>
													</c:if>
													<c:if test="${adProject!=null }">
														<option <c:if test="${ adProject == pro.dicId}">selected</c:if> value="${pro.dicId}">${pro.dicValue }</option>
													</c:if>
												</c:forEach>
										</select>
										</span>
									</div>
								</div>
								<div class="form-group">
									<label class="col-xs-12 col-sm-3 control-label">广告类型</label>
									<div class="col-xs-12 col-sm-5">
										<span class="block input-icon input-icon-right"> 
										<select name="adType" class="number" id="adType">
											<c:forEach items="${adTypeList}" var="advertType" varStatus="status">
												<c:if test="${adType==null }">
													<option value="${advertType.dicId}">${advertType.dicValue }</option>
												</c:if>
												<c:if test="${adType!=null }">
													<option <c:if test="${ adType == advertType.dicId}">selected</c:if> value="${advertType.dicId}">${advertType.dicValue }</option>
												</c:if>
											</c:forEach>
										</select>
										</span>
									</div>
								</div>
								<div class="form-group">
									<label class="col-xs-12 col-sm-3 control-label">广告状态</label>
									<div class="col-xs-12 col-sm-5">
										<span class="block input-icon input-icon-right"> 
										<select name="adSatus" class="number" id="adSatus">
											<c:forEach items="${adStatusList}" var="ad" varStatus="status">
												<c:if test="${adSatus==null }">
													<option value="${ad.dicId}">${ad.dicValue }</option>
												</c:if>
												<c:if test="${adSatus!=null }">
													<option <c:if test="${ adSatus == ad.dicId}">selected</c:if> value="${ad.dicId}">${ad.dicValue }</option>
												</c:if>
											</c:forEach>
										</select>
										</span>
									</div>
								</div>
								<%-- <div class="form-group">
									<label class="col-xs-12 col-sm-3 control-label">开始时间</label>
									<div class="col-xs-12 col-sm-5" style="padding-left: 0;">
										<span class="block input-icon input-icon-right">
											<div class="col-xs-5 col-sm-4">
												<div class="input-group">
													<input type="text" name="startDay" value="${startDay }" class="form-control" maxlength="2">
												</div>
											</div>
											<div style="float: left; width: 92px; text-align: center; line-height: 32px;">到</div>
											<div class="col-xs-5 col-sm-4" style="padding: 0;">
												<div class="input-group">
													<input type="text" name="endDay" value="${endDay }" class="form-control" maxlength="2">
												</div>
											</div>
											<div class="col-xs-5 col-sm-2" style="line-height: 32px; height: 30px;">天</div>
										</span>
									</div>
								</div> --%>
								<div class="form-group">
									<label class="col-xs-12 col-sm-3 control-label">时间周期 从</label>
									<div class="col-xs-12 col-sm-5" style="padding-left: 0;">
										<span class="block input-icon input-icon-right">
											<div class="col-xs-5 col-sm-5">
												<div class="input-group">
													<input type="text" id="adStartTime" name="adStartTime" value="${adStartTime }" class="form-control form-start" readonly="readonly">
													<span class="input-group-addon" style="cursor: pointer;">
														<i class="fa fa-calendar bigger-110"></i>
													</span>
												</div>
											</div>
											<div
												style="float: left; width: 92px; text-align: center; line-height: 32px;">到</div>
											<div class="col-xs-5 col-sm-5" style="padding: 0;">
												<div class="input-group">
													<input type="text" id="adEndTime" name="adEndTime" value="${adEndTime}" class="form-control form-end" readonly="readonly">
													<span class="input-group-addon" style="cursor: pointer;">
														<i class="fa fa-calendar bigger-110"></i>
													</span>
												</div>
											</div>
										</span>
									</div>
								</div>
								<div class="clearfix form-actions">
									<div class="col-md-offset-3 col-md-8">
										<button class="btn btn-info submit" type="button" onclick="submitConsumptionForm();">
											<i class="ace-icon fa fa-search bigger-110"></i> 查询
										</button>
									</div>
								</div>
							</form>

							<div class="page-header">
								<h1>合计及平均数值</h1>
							</div>
							<table
								class="table table-striped table-bordered table-hover table-gameManager">
								<thead>
									<tr>
										<th>注册用户消费金额</th>
										<th>登录用户消费金额</th>
										<th class="hidden-480">付费用户消费合计</th>
										<th>付费用户日均付费金额</th>
										<th class="hidden-480">付费用户平均付费金额(ARPPU)</th>
										<th class="hidden-70">登录用户平均付费金额(ARPU)</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${advertNumList}" var="advert">
										<tr>
											<td>${advert.registerUsrConsumeAmount}</td>
											<td>${advert.loginUsrConsumeAmount}</td>
											<td>${advert.consumeUsrAmount}</td>
											<td>${advert.consumeUsrDayAmount}</td>
											<td>${advert.consumeUsrAvgAmount}</td>
											<td>${advert.longinUsrAvgAmount}</td>
										</tr>

									</c:forEach>
								</tbody>
							</table>

							<form id="game_form" action="" method="post">
								<input type="hidden" id="gid" name="gid" value=""> <input
									type="hidden" id="status" name="status" value="">
								<table id="consumTab"
									class="table table-striped table-bordered table-hover table-gameManager">
									<thead>
										<tr>
											<th>广告名称</th>
											<th>注册用户消费金额(占比)</th>
											<th class="hidden-480">注册用户日均消费金额</th>
											<th>登录用户消费金额(占比)</th>
											<th class="hidden-480">登录用户日均消费金额(占比)</th>
											<th class="hidden-70">付费用户消费合计（前两项和占比）</th>
											<th class="hidden-70">付费用户消费日均消费</th>
											<th class="hidden-70">付费用户平均付费金额（ARPPU）</th>
											<th class="hidden-70">登录用户平均付费金（ARPU）</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${advertStatsList}" var="ssortAdvert">
											<tr>
												<td>${ssortAdvert.adName}</td>
												<td>${ssortAdvert.registerUsrConsumeAmount}</td>
												<td class="hidden-480">${ssortAdvert.registerUsrDayConsumeAmount}</td>
												<td>${ssortAdvert.loginUsrConsumeAmount}</td>
												<td>${ssortAdvert.loginUsrDayConsumeAmount}</td>
												<td>${ssortAdvert.consumeUsrAmount}</td>
												<td>${ssortAdvert.consumeUsrDayAmount}</td>
												<td>${ssortAdvert.consumeUsrAvgAmount}</td>
												<td>${ssortAdvert.longinUsrAvgAmount}</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
									<ul class="pagination pull-right no-margin col-xs-7">
										<li class="prev disabled"><a href="#">共${page.pageTotal }页</a></li>
										<li class="prev disabled"><a href="#">当前第${page.pageNo }页</a></li>
										<c:if test="${page.haveFirst}">
											<li><a href="<%= request.getContextPath()%>/consumption/pageForm?page=pre&offset=${page.first }&adName=${adName}&adSatus=${adSatus}&adProject=${adProject}&adType=${adType}&adStartTime=${adStartTime}&adEndTime=${adEndTime}&startDay=${startDay}&endDay=${endDay}">首页</a></li>
										</c:if>
										<c:if test="${!page.haveFirst}">
											<li class="prev disabled"><a href="#">首页</a></li>
										</c:if>
										<c:if test="${page.haveFirst}">
											<li><a href="<%= request.getContextPath()%>/consumption/pageForm?page=pre&offset=${page.pre }&adName=${adName}&adSatus=${adSatus}&adProject=${adProject}&adType=${adType}&adStartTime=${adStartTime}&adEndTime=${adEndTime}&startDay=${startDay}&endDay=${endDay}">上一页</a></li>
										</c:if>
										<c:if test="${!page.haveFirst}">
											<li class="prev disabled"><a href="#">上一页</a></li>
										</c:if>
										<c:if test="${page.haveLast}">
											<li><a href="<%= request.getContextPath()%>/consumption/pageForm?page=next&offset=${page.next }&adName=${adName}&adSatus=${adSatus}&adProject=${adProject}&adType=${adType}&adStartTime=${adStartTime}&adEndTime=${adEndTime}&startDay=${startDay}&endDay=${endDay}">下一页</a></li>
										</c:if>
										<c:if test="${!page.haveLast}">
											<li class="prev disabled"><a href="#">下一页</a></li>
										</c:if>
										<c:if test="${page.haveLast}">
											<li><a href="<%= request.getContextPath()%>/consumption/pageForm?page=pre&offset=${page.last }&adName=${adName}&adSatus=${adSatus}&adProject=${adProject}&adType=${adType}&adStartTime=${adStartTime}&adEndTime=${adEndTime}&startDay=${startDay}&endDay=${endDay}">尾页</a></li>
										</c:if>
										<c:if test="${!page.haveLast}">
											<li class="prev disabled"><a href="#">尾页</a></li>
										</c:if>
									</ul>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="footer">
			<div class="footer-inner">
				<div class="footer-content">
					<span class="bigger-120"> <span class="blue">Version
							V1.0.0 &nbsp;&nbsp;2016</span></span>
				</div>
			</div>
		</div>
		<a href="#" id="btn-scroll-up"
			class="btn-scroll-up btn btn-sm btn-inverse"> <i
			class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
		</a>
	</div>

	<div class="tooltip top in" style="display: none;">
	<div class="tooltip-inner">
	</div>
</div>
</body>
</html>