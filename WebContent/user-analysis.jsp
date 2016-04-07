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
<link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/bootstrap.min.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/assets/font-awesome/4.2.0/css/font-awesome.min.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/assets/fonts/fonts.googleapis.com.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style">
<link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/jquery-ui.min.css" />
<link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/jquery.datetimepicker.css"/>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/jquery.dataTables.min.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/buttons.dataTables.min.css">
<script src="<%= request.getContextPath() %>/assets/js/ace-extra.min.js"></script>
<script src="<%= request.getContextPath() %>/assets/js/jquery.2.1.1.min.js"></script>
<script type="text/javascript">
	window.jQuery || document.write("<script src='<%= request.getContextPath() %>/assets/js/jquery.min.js'>"+"<"+"/script>");
</script>
<script type="text/javascript">
	if('ontouchstart' in document.documentElement) document.write("<script src='<%= request.getContextPath() %>/assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
</script>
<script src="<%= request.getContextPath() %>/assets/js/bootstrap.min.js"></script>
<script src="<%= request.getContextPath() %>/assets/js/jquery-ui.custom.min.js"></script>
<script src="<%= request.getContextPath() %>/assets/js/jquery-ui.min.js"></script>
<script src="<%= request.getContextPath() %>/assets/js/jquery.ui.touch-punch.min.js"></script>
<script src="<%= request.getContextPath() %>/assets/js/jquery.easypiechart.min.js"></script>
<script src="<%= request.getContextPath() %>/assets/js/jquery.sparkline.min.js"></script>
<script src="<%= request.getContextPath() %>/assets/js/jquery.flot.min.js"></script>
<script src="<%= request.getContextPath() %>/assets/js/jquery.flot.pie.min.js"></script>
<script src="<%= request.getContextPath() %>/assets/js/jquery.flot.resize.min.js"></script>
<script src="<%= request.getContextPath() %>/assets/js/jquery.datetimepicker.min.js"></script>
<script src="<%= request.getContextPath() %>/assets/js/bootbox.js"></script>
<script src="<%= request.getContextPath() %>/assets/js/ace-elements.min.js"></script>
<script src="<%= request.getContextPath() %>/assets/js/ace.min.js"></script>
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
function findCurxIndex(){
	$("#cruxIndexForm").submit();
	
}
function findWeekIndex(){
	$("#weekIndexForm").submit();
}
function findMonthIndex(){
	$("#monthIndexForm").submit();
}

jQuery(function($) {
	
		$(".number").css({
			width : '543px'
		}).selectmenu({
			position : {
				my : "left top",
				at : "left bottom"
			}
		})
		$('.input-select').css({
			width : '150px'
		})
		$('.form-start,.form-end').datetimepicker({
			'timepicker' : false,
			'datepicker' : true,
			'format' : 'Y-m-d'
		});

		$('#cruxTab').DataTable({
			dom : 'Bfrtip',
			buttons : [ 'csv', 'excel', 'pdf' ]
		});

	});
(window, jQuery);
</script>
<style type="text/css">
.jqstooltip { position: absolute;left: 0px;top: 0px;visibility: hidden;background: rgb(0, 0, 0) transparent;background-color: rgba(0,0,0,0.6);filter:progid:DXImageTransform.Microsoft.gradient(startColorstr=#99000000, endColorstr=#99000000);-ms-filter: "progid:DXImageTransform.Microsoft.gradient(startColorstr=#99000000, endColorstr=#99000000)";color: white;font: 10px arial, san serif;text-align: left;white-space: nowrap;padding: 5px;border: 1px solid white;z-index: 10000;}.jqsfield { color: white;font: 10px arial, san serif;text-align: left;}
</style>

</head>
<body class="no-skin">
	<div id="navbar" class="navbar navbar-default">
		<script type="text/javascript">
			try {
				ace.settings.check('navbar', 'fixed')
			} catch (e) {
			}
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
			<div class="navbar-buttons navbar-header pull-right"
				role="navigation">
				<ul class="nav ace-nav">
					<li class="light-blue">
						<a data-toggle="dropdown" href="#" class="dropdown-toggle"> 
							<img class="nav-user-photo" src="<%=request.getContextPath()%>/assets/avatars/user.jpg" alt="Jason's Photo"> 
							<span class="user-info"> 
								<small>Welcome,</small>
								Admin
							</span> 
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
	<div id="sidebar" class="sidebar responsive" data-sidebar="true" data-sidebar-scroll="true" data-sidebar-hover="true">
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
				<span class="btn btn-success"></span>
				<span class="btn btn-info"></span>
				<span class="btn btn-warning"></span>
				<span class="btn btn-danger"></span>
			</div>
		</div>
		<ul class="nav nav-list">
			<c:forEach items="${responsMenuList}" var="menu" varStatus="status">
					<li>
						<a href="#" class="dropdown-toggle"> 
							<i class="menu-icon fa fa-gamepad"></i> 
							<span class="menu-text">${menu.menuName}</span>
							<b class="arrow fa fa-angle-down"></b>
						</a> 
						<b class="arrow"></b>
						<ul class="submenu">
							<li class="active">
							<c:if test="${!empty menu.childMenu}">
								<c:forEach items="${menu.childMenu}" var="childMenu">
									<ul class="submenu">
										<li class="">
											<a href="${childMenu.menuLink}" target="_self"><i class="menu-icon fa fa-caret-right"></i>${childMenu.menuName}</a>
											<b class="arrow"></b>
										</li>
									</ul>
								</c:forEach>
								</c:if> <b class="arrow"></b></li>
						</ul></li>
				</c:forEach>
		</ul>
		<div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
			<i class="ace-icon fa fa-angle-double-left" data-icon1="ace-icon fa fa-angle-double-left" data-icon2="ace-icon fa fa-angle-double-right"></i>
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
					<li>
					<i class="ace-icon fa fa-home home-icon"></i>
					<a href="#">广告数据分析</a>
					</li>
					<li class="active">流量分析</li>
				</ul>
			</div>
			<div class="page-content">
				<!-- /.ace-settings-container -->
				<div class="page-header">
              <h1>
                广告数据分析
              </h1>
				</div>
				<div class="row">
					<div class="col-xs-12">
						<div class="tabbable">
							<ul class="nav nav-tabs padding-12 tab-color-blue background-blue" id="myTab4">
								<li class="active">
									<a data-toggle="tab" href="#one" aria-expanded="true">关键指标查询</a>
								</li>
								<li class="">
									<a data-toggle="tab" href="#two" aria-expanded="false">周数据指标项</a>
								</li>
								<li class="">
									<a data-toggle="tab" href="#three" aria-expanded="false">月数据指标项</a>
								</li>
							</ul>

							<div class="tab-content">
									<div id="one" class="tab-pane active">
										<div class="row">
											<div class="col-xs-12">
												<form class="form-horizontal form-addGame" action="<%=request.getContextPath()%>/user/findCruxIndex" name="cruxIndexForm" id="cruxIndexForm" enctype="multipart/form-data">
													<div class="form-group">
														<label class="col-xs-12 col-sm-3 control-label">广告名称</label>
														<div class="col-xs-12 col-sm-5">
															<span class="block input-icon input-icon-right"> <input
																type="text" name="cruxAdName" id="cruxAdName"
																value="${cruxAdName}" maxlength="12">
															</span>
														</div>
													</div>
													<div class="form-group">
														<label class="col-xs-12 col-sm-3 control-label">所属项目</label>
														<div class="col-xs-12 col-sm-5">
															<span class="block input-icon input-icon-right"> 
															<select id="cruxAdProject" name="cruxAdProject" class="number"
																id="cruxAdProject">
																	<c:forEach items="${proList}" var="pro" varStatus="status">
																		<c:if test="${cruxAdProject==null }">
																			<option value="${pro.dicId}">${pro.dicValue }</option>
																		</c:if>
																		<c:if test="${cruxAdProject!=null }">
																			<option <c:if test="${ cruxAdProject == pro.dicId}">selected</c:if> value="${pro.dicId}">${pro.dicValue }</option>
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
															<select id="cruxAdType" name="cruxAdType" class="number">
																<c:forEach items="${adTypeList}" var="advertType" varStatus="status">
																	<c:if test="${cruxAdType==null }">
																		<option value="${advertType.dicId}">${advertType.dicValue }</option>
																	</c:if>
																	<c:if test="${cruxAdType!=null }">
																		<option <c:if test="${ cruxAdType == advertType.dicId}">selected</c:if> value="${advertType.dicId}">${advertType.dicValue }</option>
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
															<select id="cruxAdSatus" name="cruxAdSatus" class="number">
																<c:forEach items="${adStatusList}" var="ad" varStatus="status">
																	<c:if test="${cruxAdSatus==null }">
																		<option value="${ad.dicId}">${ad.dicValue }</option>
																	</c:if>
																	<c:if test="${cruxAdSatus!=null }">
																		<option <c:if test="${ cruxAdSatus == ad.dicId}">selected</c:if> value="${ad.dicId}">${ad.dicValue }</option>
																	</c:if>
																</c:forEach>
															</select>
															</span>
														</div>
													</div>


													<%-- <div class="form-group">
														<label class="col-xs-12 col-sm-3 control-label">开始后时间
															从</label>
														<div class="col-xs-12 col-sm-5" style="padding-left: 0;">
															<span class="block input-icon input-icon-right">
																<div class="col-xs-5 col-sm-4">
																	<div class="input-group">
																		<input type="text" id="cruxStartDay" name="cruxStartDay" value="${cruxStartDay }" class="form-control" maxlength="2">
																	</div>
																</div>
																<div style="float: left; width: 92px; text-align: center; line-height: 32px;">到</div>
																<div class="col-xs-5 col-sm-5" style="padding: 0;">
																	<div class="input-group">
																		<input type="text" id="cruxEndDay" name="cruxEndDay" value="${cruxEndDay }" class="form-control" maxlength="2">
																	</div>
																</div>
																<div style="float: left; width: 92px; text-align: center; line-height: 32px;">天</div>
															</span>
														</div>
													</div> --%>

													<div class="form-group">
														<label class="col-xs-12 col-sm-3 control-label">开始时间</label>
														<div class="col-xs-12 col-sm-5" style="padding-left: 0;">
															<span class="block input-icon input-icon-right">
																<div class="input-group">
																	<input type="text" id="cruxStartDate" name="cruxStartDate" value="${cruxStartDate }" class="form-control form-end" readonly="readonly">
																	<span class="input-group-addon" style="cursor: pointer;"> 
																		<i class="fa fa-calendar bigger-110"></i>
																	</span>
																</div>
															</span>
														</div>
													</div>
													<div class="form-group">
														<label class="col-xs-12 col-sm-3 control-label">结束时间</label>
														<div class="col-xs-12 col-sm-5" style="padding-left: 0;">
															<span class="block input-icon input-icon-right">
																<div class="input-group">
																	<input type="text" id="cruxEndDate" name="cruxEndDate" value="${cruxEndDate}" class="form-control form-end" readonly="readonly"> 
																	<span class="input-group-addon" style="cursor: pointer;">
																		<i class="fa fa-calendar bigger-110"></i>
																	</span>
																</div>
															</span>
														</div>
													</div>

													<div class="clearfix form-actions">
														<div class="col-md-offset-3 col-md-8">
															<button class="btn btn-info submit" type="button" onclick="findCurxIndex();">
																<i class="ace-icon fa fa-search bigger-110"></i> 查询
															</button>
														</div>
													</div>
												</form>


												<div class="page-header">
													<h1>合计数值</h1>
												</div>
												<table
													class="table table-striped table-bordered table-hover table-gameManager">
													<thead>
														<tr>
															<th>注册用户</th>
															<th>登录用户数</th>
															<th class="hidden-480">消费用户数</th>
															<th>用户消费次数</th>
															<th class="hidden-480">次日留存率</th>
															<th class="hidden-70">3日留存率</th>
															<th class="hidden-70">7日留存率</th>
														</tr>
													</thead>
													<tbody>
														<c:forEach items="${cruxIndexStatsList}" var="crux">
															<tr>
																<td>${crux.registerUser}</td>
																<td>${crux.loginUserNum}</td>
																<td>${crux.consumeUserNum}</td>
																<td>${crux.consumeUserCount}</td>
																<td>${crux.nextDayRetention}</td>
																<td>${crux.thrDaysRetention}</td>
																<td>${crux.sevenDaysRetention}</td>
															</tr>

														</c:forEach>
													</tbody>
												</table>



												<form id="game_form" action="<%=request.getContextPath()%>/user/findCruxIndex" method="post">
													<table id="cruxTab" class="table table-striped table-bordered table-hover table-gameManager">
														<thead>
															<tr>
																<th>广告名称</th>
																<th>广告开始时间</th>
																<th class="hidden-480">所属项目</th>
																<th>注册用户数（占比)</th>
																<th class="hidden-480">登录用户数（占比）</th>
																<th class="hidden-70">消费用户数（占比)</th>
																<th class="hidden-70">用户消费次数</th>
																<th class="hidden-70">次日留存率</th>
																<th class="hidden-70">3日留存率</th>
																<th class="hidden-70">7日留存率</th>
															</tr>
														</thead>
														<tbody>
															<c:forEach items="${cruxIndexList}" var="crux">
																<tr>
																	<td>${crux.adName}</td>
																	<td><fmt:formatDate value="${crux.adStartTime}" pattern="yyyy-MM-dd"/></td>
																	<td class="hidden-480">${crux.adProject}</td>
																	<td>${crux.registerUser}</td>
																	<td>${crux.loginUserNum}</td>
																	<td>${crux.consumeUserNum}</td>
																	<td>${crux.consumeUserCount}</td>
																	<td>${crux.nextDayRetention}</td>
																	<td>${crux.thrDaysRetention}</td>
																	<td>${crux.sevenDaysRetention}</td>
																</tr>
															</c:forEach>
														</tbody>
													</table>
													<ul class="pagination pull-right no-margin col-xs-7">
														<li class="prev disabled"><a href="#">共${page.pageTotal }页</a></li>
														<li class="prev disabled"><a href="#">当前第${page.pageNo }页</a></li>
														<c:if test="${page.haveFirst}">
															<li><a href="<%= request.getContextPath()%>/user/pageForm?page=pre&offset=${page.first }&cruxAdName=${cruxAdName}&cruxAdProject=${cruxAdProject}&cruxAdType=${cruxAdType}&cruxAdSatus=${cruxAdSatus}&cruxStartDay=${cruxStartDay}&cruxEndDay=${cruxEndDay}&cruxStartDate=${cruxStartDate}&cruxEndDate=${cruxEndDate}">首页</a></li>
														</c:if>
														<c:if test="${!page.haveFirst}">
															<li class="prev disabled"><a href="#">首页</a></li>
														</c:if>
														<c:if test="${page.haveFirst}">
															<li><a href="<%= request.getContextPath()%>/flow/pageForm?page=pre&offset=${page.pre }&cruxAdName=${cruxAdName}&cruxAdProject=${cruxAdProject}&cruxAdType=${cruxAdType}&cruxAdSatus=${cruxAdSatus}&cruxStartDay=${cruxStartDay}&cruxEndDay=${cruxEndDay}&cruxStartDate=${cruxStartDate}&cruxEndDate=${cruxEndDate}">上一页</a></li>
														</c:if>
														<c:if test="${!page.haveFirst}">
															<li class="prev disabled"><a href="#">上一页</a></li>
														</c:if>
														<c:if test="${page.haveLast}">
															<li><a href="<%= request.getContextPath()%>/flow/pageForm?page=next&offset=${page.next }&cruxAdName=${cruxAdName}&cruxAdProject=${cruxAdProject}&cruxAdType=${cruxAdType}&cruxAdSatus=${cruxAdSatus}&cruxStartDay=${cruxStartDay}&cruxEndDay=${cruxEndDay}&cruxStartDate=${cruxStartDate}&cruxEndDate=${cruxEndDate}">下一页</a></li>
														</c:if>
														<c:if test="${!page.haveLast}">
															<li class="prev disabled"><a href="#">下一页</a></li>
														</c:if>
														<c:if test="${page.haveLast}">
															<li><a href="<%= request.getContextPath()%>/flow/pageForm?page=pre&offset=${page.last }&cruxAdName=${cruxAdName}&cruxAdProject=${cruxAdProject}&cruxAdType=${cruxAdType}&cruxAdSatus=${cruxAdSatus}&cruxStartDay=${cruxStartDay}&cruxEndDay=${cruxEndDay}&cruxStartDate=${cruxStartDate}&cruxEndDate=${cruxEndDate}">尾页</a></li>
														</c:if>
														<c:if test="${!page.haveLast}">
															<li class="prev disabled"><a href="#">尾页</a></li>
														</c:if>
													</ul>
												</form>
											</div>
										</div>
									</div>
									<div id="two" class="tab-pane">
										<div class="row">
											<div class="col-xs-12">
												<form class="form-horizontal form-addGame" action="<%=request.getContextPath()%>/user/findWeekIndex" name="weekIndexForm" id="weekIndexForm" enctype="multipart/form-data">
													<div class="form-group">
														<label class="col-xs-12 col-sm-3 control-label">广告名称</label>
														<div class="col-xs-12 col-sm-5">
															<span class="block input-icon input-icon-right"> 
																<input type="text" name="weekAdName" id="weekAdName" value="${weekAdName}" maxlength="12">
															</span>
														</div>
													</div>
													<div class="form-group">
														<label class="col-xs-12 col-sm-3 control-label">所属项目</label>
														<div class="col-xs-12 col-sm-5">
															<span class="block input-icon input-icon-right"> 
															<select name="weekAdProject" class="number" id="adProject">
																<c:forEach items="${proList}" var="pro" varStatus="status">
																	<c:if test="${weekAdProject==null }">
																		<option value="${pro.dicId}">${pro.dicValue }</option>
																	</c:if>
																	<c:if test="${weekAdProject!=null }">
																		<option <c:if test="${ weekAdProject == pro.dicId}">selected</c:if> value="${pro.dicId}">${pro.dicValue }</option>
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
															<select name="weekAdType" class="number" id="adType">
																<c:forEach items="${adTypeList}" var="advertType" varStatus="status">
																	<c:if test="${weekAdType==null }">
																		<option value="${advertType.dicId}">${advertType.dicValue }</option>
																	</c:if>
																	<c:if test="${weekAdType!=null }">
																		<option <c:if test="${ weekAdType == advertType.dicId}">selected</c:if> value="${advertType.dicId}">${advertType.dicValue }</option>
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
															<select name="weekAdSatus" class="number" id="adSatus">
																<c:forEach items="${adStatusList}" var="ad" varStatus="status">
																	<c:if test="${weekAdSatus==null }">
																		<option value="${ad.dicId}">${ad.dicValue }</option>
																	</c:if>
																	<c:if test="${weekAdSatus!=null }">
																		<option <c:if test="${ weekAdSatus == ad.dicId}">selected</c:if> value="${ad.dicId}">${ad.dicValue }</option>
																	</c:if>
																</c:forEach>
															</select>
															</span>
														</div>
													</div>
													<%-- <div class="form-group">
														<label class="col-xs-12 col-sm-3 control-label">开始后时间
															从</label>
														<div class="col-xs-12 col-sm-5" style="padding-left: 0;">
															<span class="block input-icon input-icon-right">
																<div class="col-xs-5 col-sm-4">
																	<div class="input-group">
																		<input type="text" name="weekStartDay" value="${weekStartDay }" class="form-control" maxlength="2">
																	</div>
																</div>
																<div
																	style="float: left; width: 92px; text-align: center; line-height: 32px;">到</div>
																<div class="col-xs-5 col-sm-4" style="padding: 0;">
																	<div class="input-group">
																		<input type="text" name="weekEndDay" value="${weekEndDay }" class="form-control" maxlength="2">
																	</div>
																</div>
																<div class="col-xs-5 col-sm-2" style="line-height: 32px; height: 30px;">天</div>
															</span>
														</div>
													</div> --%>

													<div class="form-group">
														<label class="col-xs-12 col-sm-3 control-label">开始时间</label>
														<div class="col-xs-12 col-sm-5" style="padding-left: 0;">
															<span class="block input-icon input-icon-right">
																<div class="input-group">
																	<input type="text" name="weekStartDate" value="${weekStartDate }" class="form-control form-end" readonly="readonly">
																	<span class="input-group-addon" style="cursor: pointer;"> 
																		<i class="fa fa-calendar bigger-110"></i>
																	</span>
																</div>
															</span>
														</div>
													</div>
													<div class="form-group">
														<label class="col-xs-12 col-sm-3 control-label">结束时间</label>
														<div class="col-xs-12 col-sm-5" style="padding-left: 0;">
															<span class="block input-icon input-icon-right">
																<div class="input-group">
																	<input type="text" name="weekEndDate" value="${ weekEndDate}" class="form-control form-end" readonly="readonly"> 
																	<span class="input-group-addon" style="cursor: pointer;">
																		<i class="fa fa-calendar bigger-110"></i>
																	</span>
																</div>
															</span>
														</div>
													</div>
													<div class="clearfix form-actions">
														<div class="col-md-offset-3 col-md-8">
															<button class="btn btn-info submit" type="button" onclick="findWeekIndex();">
																<i class="ace-icon fa fa-search bigger-110"></i> 查询
															</button>
														</div>
													</div>
												</form>

												<h1>合计数值</h1>
												<table
													class="table table-striped table-bordered table-hover table-gameManager">
													<thead>
														<tr>
															<th>周登录用户数</th>
															<th>周消费用户数</th>
														</tr>
													</thead>
													<tbody>
														<c:forEach items="${weekIndexList}" var="week">
															<tr>
																<td>${week.loginUsrWeekCount}</td>
																<td>${week.consumeUsrWeekCount}</td>
															</tr>

														</c:forEach>
													</tbody>
												</table>

												<form id="game_form"
													action="<%=request.getContextPath()%>/user/findWeekIndex"
													method="post">
													<table id="weekTab"
														class="table table-striped table-bordered table-hover table-gameManager">
														<thead>
															<tr>
																<th>广告名称</th>
																<th>广告开始时间</th>
																<th class="hidden-480">所属项目</th>
																<th class="hidden-70">周登录用户数（占比）</th>
																<th class="hidden-70">周消费用户数</th>
															</tr>
														</thead>
														<tbody>
															<c:forEach items="${weekIndexAssortList }"
																var="weekAssort">
																<tr>
																	<td>${weekAssort.adName}</td>
																	<td>${weekAssort.adStartDate}</td>
																	<td class="hidden-480">${weekAssort.adProject}</td>
																	<td class="hidden-70">${weekAssort.loginUsrWeekCount}</td>
																	<td class="hidden-70">${weekAssort.consumeUsrWeekCount}</td>
																</tr>
															</c:forEach>
														</tbody>
													</table>
													<ul class="pagination pull-right no-margin col-xs-7">
														<li class="prev disabled"><a href="#">共${page.pageTotal }页</a></li>
														<li class="prev disabled"><a href="#">当前第${page.pageNo }页</a></li>
														<c:if test="${page.haveFirst}">
															<li><a href="<%= request.getContextPath()%>/user/pageForm?page=pre&offset=${page.first }&weekAdName=${weekAdName}&weekAdProject=${weekAdProject}&weekAdType=${weekAdType}&weekAdSatus=${weekAdSatus}&weekStartDay=${weekStartDay}&weekEndDay=${weekEndDay}&weekStartDate=${weekStartDate}&weekEndDate=${weekEndDate}">首页</a></li>
														</c:if>
														<c:if test="${!page.haveFirst}">
															<li class="prev disabled"><a href="#">首页</a></li>
														</c:if>
														<c:if test="${page.haveFirst}">
															<li><a href="<%= request.getContextPath()%>/flow/pageForm?page=pre&offset=${page.pre }&weekAdName=${weekAdName}&weekAdProject=${weekAdProject}&weekAdType=${weekAdType}&weekAdSatus=${weekAdSatus}&weekStartDay=${weekStartDay}&weekEndDay=${weekEndDay}&weekStartDate=${weekStartDate}&weekEndDate=${weekEndDate}">上一页</a></li>
														</c:if>
														<c:if test="${!page.haveFirst}">
															<li class="prev disabled"><a href="#">上一页</a></li>
														</c:if>
														<c:if test="${page.haveLast}">
															<li><a href="<%= request.getContextPath()%>/flow/pageForm?page=next&offset=${page.next }&weekAdName=${weekAdName}&weekAdProject=${weekAdProject}&weekAdType=${weekAdType}&weekAdSatus=${weekAdSatus}&weekStartDay=${weekStartDay}&weekEndDay=${weekEndDay}&weekStartDate=${weekStartDate}&weekEndDate=${weekEndDate}">下一页</a></li>
														</c:if>
														<c:if test="${!page.haveLast}">
															<li class="prev disabled"><a href="#">下一页</a></li>
														</c:if>
														<c:if test="${page.haveLast}">
															<li><a href="<%= request.getContextPath()%>/flow/pageForm?page=pre&offset=${page.last }&weekAdName=${weekAdName}&weekAdProject=${weekAdProject}&weekAdType=${weekAdType}&weekAdSatus=${weekAdSatus}&weekStartDay=${weekStartDay}&weekEndDay=${weekEndDay}&weekStartDate=${weekStartDate}&weekEndDate=${weekEndDate}">尾页</a></li>
														</c:if>
														<c:if test="${!page.haveLast}">
															<li class="prev disabled"><a href="#">尾页</a></li>
														</c:if>
													</ul>
												</form>
											</div>
										</div>
									</div>

									<div id="three" class="tab-pane">
										<div class="row">
											<div class="col-xs-12">
												<form class="form-horizontal form-addGame" action="<%=request.getContextPath()%>/user/findMonthIndex" name="monthIndexForm" id="monthIndexForm" enctype="multipart/form-data">
													<div class="form-group">
														<label class="col-xs-12 col-sm-3 control-label">广告名称</label>
														<div class="col-xs-12 col-sm-5">
															<span class="block input-icon input-icon-right"> 
																<input type="text" name="monthAdName" id="monthAdName" value="${monthAdName}" maxlength="12">
															</span>
														</div>
													</div>
													<div class="form-group">
														<label class="col-xs-12 col-sm-3 control-label">所属项目</label>
														<div class="col-xs-12 col-sm-5">
															<span class="block input-icon input-icon-right"> 
															<select name="monthAdProject" class="number" id="adProject">
																<c:forEach items="${proList}" var="pro" varStatus="status">
																	<c:if test="${monthAdProject==null }">
																		<option value="${pro.dicId}">${pro.dicValue }</option>
																	</c:if>
																	<c:if test="${monthAdProject!=null }">
																		<option <c:if test="${ monthAdProject == pro.dicId}">selected</c:if> value="${pro.dicId}">${pro.dicValue }</option>
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
															<select name="monthAdType" class="number" id="adType">
																<c:forEach items="${adTypeList}" var="advertType" varStatus="status">
																	<c:if test="${monthAdType==null }">
																		<option value="${advertType.dicId}">${advertType.dicValue }</option>
																	</c:if>
																	<c:if test="${monthAdType!=null }">
																		<option <c:if test="${ monthAdType == advertType.dicId}">selected</c:if>value="${advertType.dicId}">${advertType.dicValue }</option>
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
															<select name="monthAdSatus" class="number" id="monthAdSatus">
																<c:forEach items="${adStatusList}" var="ad" varStatus="status">
																	<c:if test="${monthAdSatus==null }">
																		<option value="${ad.dicId}">${ad.dicValue }</option>
																	</c:if>
																	<c:if test="${monthAdSatus!=null }">
																		<option <c:if test="${ monthAdSatus == ad.dicId}">selected</c:if> value="${ad.dicId}">${ad.dicValue }</option>
																	</c:if>
																</c:forEach>
															</select>
															</span>
														</div>
													</div>

													<div class="form-group">
														<label class="col-xs-12 col-sm-3 control-label">开始后时间
															从</label>
														<div class="col-xs-12 col-sm-5" style="padding-left: 0;">
															<span class="block input-icon input-icon-right">
																<div class="col-xs-5 col-sm-4">
																	<div class="input-group">
																		<input type="text" name="monthStartDay" value="${monthStartDay }" class="form-control" maxlength="2">
																	</div>
																</div>
																<div style="float: left; width: 92px; text-align: center; line-height: 32px;">到</div>
																<div class="col-xs-5 col-sm-4" style="padding: 0;">
																	<div class="input-group">
																		<input type="text" name="monthEndDay" value="${monthEndDay }" class="form-control" maxlength="2">
																	</div>
																</div>
																<div class="col-xs-5 col-sm-2" style="line-height: 32px; height: 30px;">天</div>
															</span>
														</div>
													</div>

													<div class="form-group">
														<label class="col-xs-12 col-sm-3 control-label">开始时间</label>
														<div class="col-xs-12 col-sm-5" style="padding-left: 0;">
															<span class="block input-icon input-icon-right">
																<div class="input-group">
																	<input type="text" name="monthStartDate" value="${monthStartDate }" class="form-control form-end" readonly="readonly">
																	<span class="input-group-addon" style="cursor: pointer;"> 
																		<i class="fa fa-calendar bigger-110"></i>
																	</span>
																</div>
															</span>
														</div>
													</div>
													<div class="form-group">
														<label class="col-xs-12 col-sm-3 control-label">结束时间</label>
														<div class="col-xs-12 col-sm-5" style="padding-left: 0;">
															<span class="block input-icon input-icon-right">
																<div class="input-group">
																	<input type="text" name="monthEndDate" value="${ monthEndDate}" class="form-control form-end" readonly="readonly"> 
																		<span class="input-group-addon" style="cursor: pointer;">
																			<i class="fa fa-calendar bigger-110"></i>
																		</span>
																</div>
															</span>
														</div>
													</div>
													<div class="clearfix form-actions">
														<div class="col-md-offset-3 col-md-8">
															<button class="btn btn-info submit" type="button" onclick="findMonthIndex();">
																<i class="ace-icon fa fa-search bigger-110"></i> 查询
															</button>
														</div>
													</div>
												</form>

												<table
													class="table table-striped table-bordered table-hover table-gameManager">
													<thead>
														<tr>
															<th>月登录用户数</th>
															<th>月消费用户数</th>
														</tr>
													</thead>
													<tbody>
														<c:forEach items="${monthIndexList}" var="month">
															<tr>
																<td>${month.loginMonthCount}</td>
																<td>${month.consumeMonthCount}</td>
															</tr>

														</c:forEach>
													</tbody>
												</table>

												<form id="game_form"
													action="<%=request.getContextPath()%>/user/findWeekIndex"
													method="post">
													<table id="monthTab"
														class="table table-striped table-bordered table-hover table-gameManager">
														<thead>
															<tr>
																<th>广告名称</th>
																<th>广告开始时间</th>
																<th class="hidden-480">所属项目</th>
																<th class="hidden-70">月登录用户数（占比）</th>
																<th class="hidden-70">月消费用户数</th>
															</tr>
														</thead>
														<tbody>
															<c:forEach items="${monthIndexAssortList }" var="month">
																<tr>
																	<td>${month.adName}</td>
																	<td>${month.adStartDate}</td>
																	<td class="hidden-480">${month.adProject}</td>
																	<td class="hidden-70">${month.loginMonthCount}</td>
																	<td class="hidden-70">${month.consumeMonthCount}</td>
																</tr>
															</c:forEach>
														</tbody>
													</table>
													<ul class="pagination pull-right no-margin col-xs-7">
														<li class="prev disabled"><a href="#">共${page.pageTotal }页</a></li>
														<li class="prev disabled"><a href="#">当前第${page.pageNo }页</a></li>
														<c:if test="${page.haveFirst}">
															<li><a href="<%= request.getContextPath()%>/user/pageForm?page=pre&offset=${page.first }&monthAdName=${monthAdName}&monthAdProject=${monthAdProject}&monthAdType=${monthAdType}&monthAdSatus=${monthAdSatus}&monthStartDay=${monthStartDay}&monthEndDay=${monthEndDay}&monthStartDate=${monthStartDate}&monthEndDate=${monthEndDate}">首页</a></li>
														</c:if>
														<c:if test="${!page.haveFirst}">
															<li class="prev disabled"><a href="#">首页</a></li>
														</c:if>
														<c:if test="${page.haveFirst}">
															<li><a href="<%= request.getContextPath()%>/flow/pageForm?page=pre&offset=${page.pre }&monthAdName=${monthAdName}&monthAdProject=${monthAdProject}&monthAdType=${monthAdType}&monthAdSatus=${monthAdSatus}&monthStartDay=${monthStartDay}&monthEndDay=${monthEndDay}&monthStartDate=${monthStartDate}&monthEndDate=${monthEndDate}">上一页</a></li>
														</c:if>
														<c:if test="${!page.haveFirst}">
															<li class="prev disabled"><a href="#">上一页</a></li>
														</c:if>
														<c:if test="${page.haveLast}">
															<li><a href="<%= request.getContextPath()%>/flow/pageForm?page=next&offset=${page.next }&monthAdName=${monthAdName}&monthAdProject=${monthAdProject}&monthAdType=${monthAdType}&monthAdSatus=${monthAdSatus}&monthStartDay=${monthStartDay}&monthEndDay=${monthEndDay}&monthStartDate=${monthStartDate}&monthEndDate=${monthEndDate}">下一页</a></li>
														</c:if>
														<c:if test="${!page.haveLast}">
															<li class="prev disabled"><a href="#">下一页</a></li>
														</c:if>
														<c:if test="${page.haveLast}">
															<li><a href="<%= request.getContextPath()%>/flow/pageForm?page=pre&offset=${page.last }&monthAdName=${monthAdName}&monthAdProject=${monthAdProject}&monthAdType=${monthAdType}&monthAdSatus=${monthAdSatus}&monthStartDay=${monthStartDay}&monthEndDay=${monthEndDay}&monthStartDate=${monthStartDate}&monthEndDate=${monthEndDate}">尾页</a></li>
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
	<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
	<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
	</a>

<div class="tooltip top in" style="display: none;">
	<div class="tooltip-inner">
	</div>
</div>
</body>
</html>