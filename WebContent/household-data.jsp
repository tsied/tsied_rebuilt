<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
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
<script src="<%=request.getContextPath() %>/assets/js/ace-extra.min.js"></script>
<style type="text/css">
.jqstooltip { position: absolute;left: 0px;top: 0px;visibility: hidden;background: rgb(0, 0, 0) transparent;background-color: rgba(0,0,0,0.6);filter:progid:DXImageTransform.Microsoft.gradient(startColorstr=#99000000, endColorstr=#99000000);-ms-filter: "progid:DXImageTransform.Microsoft.gradient(startColorstr=#99000000, endColorstr=#99000000)";color: white;font: 10px arial, san serif;text-align: left;white-space: nowrap;padding: 5px;border: 1px solid white;z-index: 10000;}.jqsfield { color: white;font: 10px arial, san serif;text-align: left;}
</style>

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
<script src="<%=request.getContextPath() %>/assets/js/highcharts.js"></script>
<script src="<%=request.getContextPath() %>/assets/js/highcharts-more.js"></script>
<script src="<%=request.getContextPath() %>/assets/js/highcharts-3d.js"></script>
<script src="<%=request.getContextPath() %>/assets/js/ace-elements.min.js"></script>
<script src="<%=request.getContextPath() %>/assets/js/ace.min.js"></script>
<script type="text/javascript">
	function householdSubmit(){
		$("#householdForm").submit();
	}

jQuery(function($) {
	if($('#indexFrist').children('option:selected').val()){
		 var firstVal = $('#indexFrist').children('option:selected').val();
		 var secondVal = $('#indexSecond').children('option:selected').val();
		 var firstIndex = firstVal.replace("[","").replace("]","").split(",");
		 var secondIndex= secondVal.replace("[","").replace("]","").split(",");
		
		 var firstList = new Array();
		 var secondList = new Array();
		 for(i=0;i<firstIndex.length;i++){
			 firstList[i] = parseInt(firstIndex[i]);
		 }
		 for(i=0;i<secondIndex.length;i++){
			 secondList[i] = parseInt(secondIndex[i]);
		 }
		houshold_data(firstList,secondList)
	}	
		
	$("#indexFrist").on('change',function(){
		 var firstVal = $('#indexFrist').children('option:selected').val();
		 var secondVal = $('#indexSecond').children('option:selected').val();
		 var firstIndex = firstVal.replace("[","").replace("]","").split(",");
		 var secondIndex= secondVal.replace("[","").replace("]","").split(",");
		
		 var firstList = new Array();
		 var secondList = new Array();
		 for(i=0;i<firstIndex.length;i++){
			 firstList[i] = parseInt(firstIndex[i]);
		 }
		 for(i=0;i<secondIndex.length;i++){
			 secondList[i] = parseInt(secondIndex[i]);
		 }
		houshold_data(firstList,secondList)
	 });	
		
	 $("#indexSecond").on('change',function(){
		 var firstVal = $('#indexFrist').children('option:selected').val();
		 var secondVal = $('#indexSecond').children('option:selected').val();

		 var firstIndex = firstVal.replace("[","").replace("]","").split(",");
		 var secondIndex= secondVal.replace("[","").replace("]","").split(",");
		
		 var firstList = new Array();
		 var secondList = new Array();
		 for(i=0;i<firstIndex.length;i++){
			 firstList[i] = parseInt(firstIndex[i]);
		 }
		 for(i=0;i<secondIndex.length;i++){
			 secondList[i] = parseInt(secondIndex[i]);
		 }
		houshold_data(firstList,secondList)
		
	 });
	 
     //time
      $('.form-start,.form-end').datetimepicker({
		  'timepicker':false,
	        'datepicker':true,
	         'format':'Y-m-d'
     });
})



function houshold_data(a,b){
	 var dateList = new Array();
	 var dateVal = $("#date").val();
	 var dateSplit = dateVal.replace("[","").replace("]","").split(",");
	 for(i=0;i<dateSplit.length;i++){
		 dateList[i] = parseInt(dateSplit[i]);
	 }

	var defaultMonth = dateList, //时间
    averages_A = a, //金额
    averages_B = b, //金额
    dtime = new Date(defaultMonth[0] * 1000)
    Year = dtime.getFullYear(),
    Month = dtime.getMonth() + 1;
	$('#z_chart').highcharts({
	    title: {
	        text: '用户咨询统计'
	    },
	    xAxis: {
	        // title:{
	        //   text:'日期天数'
	        // },
	        type:"datetime",
	        maxPadding : 0.033,
	        minPadding : 0.033,
	        lineWidth :0.5,
	        tickInterval : 24 * 3600 * 1000 * 1,
	        dateTimeLabelFormats:{
	            day: '%e日',
	        }
	    },
	    yAxis: {
	        title: {
	            text: '咨询数据量',
	          	min: 0  
	        },
	        labels: {
	        formatter:function(){
	          if(this.value <= 100) {
	            return this.value;
	          }else if(this.value > 100 && this.value <= 200) {
	            return this.value;
	          }else {
	            return this.value;
	          }
	        }
	      }
	    },
	    credits: {
				text: '内部使用', 
				href: '' 
			},

	    tooltip: {
	        crosshairs: true,
	        shared: true,
	        valueSuffix: '次',
	        xDateFormat: '%Y-%m-%d',
	        shared: true,
	        style: {
	            color: "#000",
	        }
	    },
	     plotOptions: {
	        series: {
	            pointStart: Date.UTC(Year, Month, 1),
	            pointInterval: 24 * 3600 * 1000 * 1
	        }
	    },
	    series: [{
	        name: '',
	        data: averages_A,
	        zIndex: 1,
	        marker: {
	            fillColor: 'white',
	            lineWidth: 3,
	            lineColor: Highcharts.getOptions().colors[0]
	        }
	    },{
	        name: '',
	        data: averages_B,
	        zIndex: 1,
	        marker: {
	            fillColor: 'green',
	            lineWidth: 3,
	            lineColor: Highcharts.getOptions().colors[0]
	        }
	    }]
	  });

}
</script>
</head>
<body class="no-skin">
<div id="navbar" class="navbar navbar-default">
	<script type="text/javascript">
		try{ace.settings.check('navbar' , 'fixed')}catch(e){}
	</script>
		<div class="navbar-container" id="navbar-container">
			<button type="button" class="navbar-toggle menu-toggler pull-left" id="menu-toggler" data-target="#sidebar">
				<span class="sr-only">Toggle sidebar</span> <span class="icon-bar"></span>
				<span class="icon-bar"></span> <span class="icon-bar"></span>
			</button>
			<div class="navbar-header pull-left">
				<a href="index.html" class="navbar-brand"> <small> <i class="fa fa-bar-chart"></i> 项目数据管理分析管理系统
				</small>
				</a>
			</div>
			<div class="navbar-buttons navbar-header pull-right" role="navigation">
				<ul class="nav ace-nav">
					<li class="light-blue">
						<a data-toggle="dropdown" href="#" class="dropdown-toggle"> 
							<img class="nav-user-photo" src="<%=request.getContextPath()%>/assets/avatars/user.jpg" alt="Jason's Photo"> 
							<span class="user-info"> <small>Welcome,</small>
									Admin
							</span> 
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
					<script type="text/javascript"> try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){} </script>
					<ul class="breadcrumb">
						<li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">项目数据</a>
						</li>
						<li class="active">门户咨询统计数据</li>
					</ul>

				</div>
				<div class="page-content">
					<div class="page-header">
						<h1>门户咨询统计数据</h1>
					</div>
					<div class="row">
						<div class="col-xs-12">
							<form class="form-horizontal form-addGame" id="householdForm" name="householdForm" action="<%=request.getContextPath() %>/household/find-household-data" enctype="multipart/form-data">
								<div class="form-group">
									<label class="col-xs-12 col-sm-3 control-label">开始时间</label>
									<div class="col-xs-12 col-sm-5">
										<span class="block input-icon input-icon-right">
											<div class="input-group">
												<input type="text" name="startDate" value="${startDate }" class="form-control form-start" readonly="readonly" /> 
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
												<input type="text" name="endDate" value="${ endDate}" class="form-control form-end" readonly="readonly" /> 
												<span class="input-group-addon" style="cursor: pointer;">
													<i class="fa fa-calendar bigger-110"></i>
												</span>
											</div>
										</span>
									</div>
								</div>
								<div class="clearfix form-actions">
									<div class="col-md-offset-3 col-md-8">
										<button class="btn btn-info submit" type="button" onclick="householdSubmit()">
											<i class="ace-icon fa fa-search bigger-110"></i> 查询
										</button>
									</div>
								</div>
							</form>
							<div class="page-header">
								<h1>查询结果</h1>
							</div>
							<form id="game_form" action="" method="post">
								<table
									class="table table-striped table-bordered table-hover table-gameManager">
									<thead>
										<tr>
											<th>独立用户数UV</th>
											<th>独立IP访问量</th>
											<th class="hidden-480">页面浏览量PV</th>
											<th>会话数</th>
											<th class="hidden-480">跳出率</th>
											<th class="hidden-70">平均会话时长</th>
											<th class="hidden-70">平均每次会话浏览页数</th>
											<th class="hidden-70">结束日期注册用户总数</th>
											<th class="hidden-70">新增注册用户数</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${householdList}" var="household">
											<tr>
												<td>${household.userViewNum}</td>
												<td>${household.ipViewNum}</td>
												<td>${household.pageViewNum}</td>
												<td>${household.sessionNum}</td>
												<td>${household.bounceNum}</td>
												<td>${household.avgSessionTime}</td>
												<td>${household.reqPageNum}</td>
												<td>${household.registerUserNum}</td>
												<td>${household.newRegisterUserNum}</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</form>
							<div class="page-header">
								<h1>报表指标项选择</h1>
							</div>
							<div class="col-xs-12 col-sm-5" style="padding-left: 0;">
								<span class="block input-icon input-icon-right">
									<div class="col-xs-5 col-sm-4">
										<input type="hidden" name="date" id="date"
											value="${indexDate}" />
										<div class="input-group">
											<select id="indexFrist" name="indexFrist" class="number">
												<c:forEach items="${reportIndexList}" var="index">
													<option value="${index.indexValue}">${index.indexName}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div style="float: left; width: 92px; text-align: center; line-height: 32px;">对比</div>
									<div class="col-xs-5 col-sm-4" style="padding: 0;">
										<div class="input-group">
											<select id="indexSecond" name="indexSecond" class="number">
												<c:forEach items="${reportIndexList}" var="index">
													<option value="${index.indexValue}">${index.indexName}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</span>
							</div>
							<div id="z_chart" class="col-xs-6"
								style="min-width: 100%; height: 500px; margin: 0 auto"></div>
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
	<div class="tooltip top in" style="display: none;">
		<div class="tooltip-inner"></div>
	</div>
</body>
</html>