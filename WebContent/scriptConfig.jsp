<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>配置脚本</title>

<link href="<%=request.getContextPath()%>/css/bootstrap.min.css"
	rel="stylesheet">
<link href="<%=request.getContextPath()%>/css/metisMenu.min.css"
	rel="stylesheet">
<link href="<%=request.getContextPath()%>/css/sb-admin-2.css"
	rel="stylesheet">
<link href="<%=request.getContextPath()%>/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="css/css.css" />
<!-- jQuery -->
<script src="<%=request.getContextPath()%>/js/jquery.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="<%=request.getContextPath()%>/js/metisMenu.min.js"></script>

<!-- Custom Theme JavaScript -->
<script src="<%=request.getContextPath()%>/js/sb-admin-2.js"></script>

<script src="<%=request.getContextPath()%>/js/time/WdatePicker.js"></script>

<script type="text/javascript">
	function submitForm() {
		
		$("#scriptForm").submit();
	}

	
</script>
</head>


<body>


	<div id="wrapper">
		<!-- Navigation -->
		<nav class="navbar navbar-default navbar-static-top" role="navigation"
			style="margin-bottom: 0">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand"></a>
			</div>
			<!-- /.navbar-header -->

			<ul class="nav navbar-top-links navbar-right">

				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#"> <i class="fa fa-bell fa-fw"></i>
						<i class="fa fa-caret-down"></i>
				</a>
					<ul class="dropdown-menu dropdown-alerts">

					</ul> <!-- /.dropdown-alerts --></li>
				<!-- /.dropdown -->
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#"> <i class="fa fa-user fa-fw"></i>
						<i class="fa fa-caret-down"></i>
				</a>
					<ul class="dropdown-menu dropdown-user">

						<li><a href="login.html"><i class="fa fa-sign-out fa-fw"></i>
								Logout</a></li>
					</ul> <!-- /.dropdown-user --></li>
				<!-- /.dropdown -->
			</ul>
			<!-- /.navbar-top-links -->

			<div class="navbar-default sidebar">
			
			
			<div class="sidebar-nav navbar-collapse">
					<ul class="nav" id="side-menu">
						<li>
							<ul class="nav nav-second-level">
								<li><a
									href="<%=request.getContextPath()%>/uploadFile.jsp">上传模板</a>
								</li>
							</ul>
						</li>
					</ul>
				</div>
				
				<div class="sidebar-nav navbar-collapse">
					<ul class="nav" id="side-menu">
						<li>
							<ul class="nav nav-second-level">
								<li><a
									href="<%=request.getContextPath()%>/front/initScriptConfig">配置脚本</a>
								</li>
							</ul>
						</li>
					</ul>
				</div>
				
				
				<div class="sidebar-nav navbar-collapse">
					<ul class="nav" id="side-menu">
						<li>
							<ul class="nav nav-second-level">
								<li><a href="<%=request.getContextPath()%>/index.jsp">游戏指标</a>
								</li>
							</ul>
						</li>
					</ul>
				</div>

				<!-- /.sidebar-collapse -->
			</div>

			<!-- /.navbar-static-side -->


		</nav>

		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">配置脚本</h1>
				</div>
			</div>
			<!-- /.row -->
			<div class="row">
				<form id="scriptForm" action="<%=request.getContextPath() %>/front/scriptConfig" method="post">
				<table>
					<tr>
						<td>ElasticSearch Index:<input type="text" id = "esIndex" name="esIndex" title="ElasticSearch Index"></td>
						<td>ElasticSearch Types:<input type="text" id = "eSType" name = "eSType" title="ElasticSearch Type"/></td>
					</tr>
					<tr>
						<td>选择ElasticSearch模板： <select id="scriptName"
							style="width: 80px" name="scriptName" title="选择ElasticSearch模板">
								<c:forEach items="${templateList}" var="esTemplate"
									varStatus="status">
									<option value="${esTemplate.templateSource}">${esTemplate.templateName }</option>
								</c:forEach>
						</select>
						</td>
						<td>选择报表模板： <select id="chartType" style="width: 80px"
							name="chartType" title="选择报表模板">
								<c:forEach items="${chartTypeList}"
									var="chart" varStatus="status">
									<option value="${chart.chartJsp}">${chart.chatType }</option>
								</c:forEach>
						</select>
						</td>
					</tr>
					<tr>
						<td colspan="2">指标名称：<input type="text" id="chartName"
							name="chartName"  title="指标名称">
						</td>
						
						
					</tr>
					<tr>
						<td colspan="2"><input type="button" id="button"
							name="button" value="提交" title="提交" onclick="submitForm();">
						</td>

					</tr>
				</table>

				</form>




			</div>
		</div>

	</div>
</body>

</html>
