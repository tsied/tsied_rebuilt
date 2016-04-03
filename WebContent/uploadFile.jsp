<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上传ElasticSearch模板</title>

<link href="<%=request.getContextPath()%>/css/bootstrap.min.css"
	rel="stylesheet">
<link href="<%=request.getContextPath()%>/css/metisMenu.min.css"
	rel="stylesheet">
<link href="<%=request.getContextPath()%>/css/sb-admin-2.css"
	rel="stylesheet">
<link href="<%=request.getContextPath()%>/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="css/css.css" />

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
					<h1 class="page-header">上传模板</h1>
				</div>
			</div>
			<!-- /.row -->
			<div class="row">
				<form id="scriptForm" action="<%=request.getContextPath() %>/UploadTemplate" method="post" enctype="multipart/form-data">
					<table>
						<tr>
							<td>模板名称：<input type="text"  name="indexName"></td>
							<td><input type="file" id="file" name="file" title="上传ElasticSearch模板"></td>
							<td><input type="submit" name="上传"></td>
						</tr>
					</table>
				</form>




			</div>
		</div>

	</div>
</body>

</html>
