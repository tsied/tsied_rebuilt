
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
<!DOCTYPE html>
<html lang="en">
<!--@(window)--><!--@(document)-->
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>项目数据管理分析管理系统</title>
<meta name="description" content="overview &amp; stats">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
<!-- bootstrap & fontawesome -->
<link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/bootstrap.min.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/assets/font-awesome/4.2.0/css/font-awesome.min.css">
<!-- page specific plugin styles -->
<!-- text fonts -->
<link rel="stylesheet" href="<%= request.getContextPath() %>/assets/fonts/fonts.googleapis.com.css">
<!-- ace styles -->
<link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style">
<link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/jquery-ui.min.css" />
<link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/jquery.datetimepicker.css"/>

<!--[if lte IE 9]>
<link rel="stylesheet" href="assets/css/ace-part2.min.css" class="ace-main-stylesheet"/>
<![endif]-->
<!--[if lte IE 9]>
<link rel="stylesheet" href="assets/css/ace-ie.min.css"/>
<![endif]-->
<!-- inline styles related to this page -->
<!-- ace settings handler -->
<script src="<%= request.getContextPath() %>/assets/js/ace-extra.min.js"></script>
<!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->
<!--[if lte IE 8]>
<script src="assets/js/html5shiv.min.js"></script>
<script src="assets/js/respond.min.js"></script>
<![endif]-->
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
				<img class="nav-user-photo" src="<%= request.getContextPath() %>/assets/avatars/user.jpg" alt="Jason's Photo">
				<span class="user-info">
				<small>Welcome,</small>
									Admin
				</span>
				<i class="ace-icon fa fa-caret-down"></i>
				</a>
				<ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
					<li>
					<a href="#">
					<i class="ace-icon fa fa-power-off"></i>
									退出
					</a>
					</li>
				</ul>
				</li>
			</ul>
		</div>
	</div>
	<!-- /.navbar-container -->
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
		<!-- /.nav-list -->
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
					</li>
				</ul>
				<!-- /.breadcrumb -->
				<div class="nav-search" id="nav-search">
					<form class="form-search">
						<span class="input-icon">
						<input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off">
						<i class="ace-icon fa fa-search nav-search-icon"></i>
						</span>
					</form>
				</div>
				<!-- /.nav-search -->
			</div>
			<div class="page-content">
				<!-- /.ace-settings-container -->
				<div class="page-header">
          <h1>
            广告管理
          </h1>
				</div>
				<!-- /.page-header -->
				<div class="row">
					<div class="col-xs-12">
						欢迎使用
					</div>
					<!-- /.col -->
				</div>
				<!-- /.row -->
			</div>
			<!-- /.page-content -->
		</div>
	</div>
	<!-- /.main-content -->
	<div class="footer">
		<div class="footer-inner">
			<div class="footer-content">
				<span class="bigger-120">
				<span class="blue">Version V1.0.0 &nbsp;&nbsp;2016</span>
			</div>
		</div>
	</div>
	<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
	<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
	</a>
</div>
<!-- /.main-container -->
<!-- basic scripts -->
<!--[if !IE]> -->
<script src="<%= request.getContextPath() %>/assets/js/jquery.2.1.1.min.js"></script>
<!-- <![endif]-->
<!--[if IE]>
<script src="assets/js/jquery.1.11.1.min.js"></script>
<![endif]-->
<!--[if !IE]> -->
<script type="text/javascript">
			window.jQuery || document.write("<script src='<%= request.getContextPath() %>/assets/js/jquery.min.js'>"+"<"+"/script>");
		</script>
<!-- <![endif]-->
<!--[if IE]>
<script type="text/javascript">
 window.jQuery || document.write("<script src='assets/js/jquery1x.min.js'>"+"<"+"/script>");
</script>
<![endif]-->
<script type="text/javascript">
			if('ontouchstart' in document.documentElement) document.write("<script src='assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>
<script src="<%= request.getContextPath() %>/assets/js/bootstrap.min.js"></script>
<!-- page specific plugin scripts -->
<!--[if lte IE 8]>
<script src="assets/js/excanvas.min.js"></script>
<![endif]-->
<script src="<%= request.getContextPath() %>/assets/js/jquery-ui.custom.min.js"></script>
<!-- page specific plugin scripts -->
<script src="<%= request.getContextPath() %>/assets/js/jquery-ui.min.js"></script>
<script src="<%= request.getContextPath() %>/assets/js/jquery.ui.touch-punch.min.js"></script>
<script src="<%= request.getContextPath() %>/assets/js/jquery.easypiechart.min.js"></script>
<script src="<%= request.getContextPath() %>/assets/js/jquery.sparkline.min.js"></script>
<script src="<%= request.getContextPath() %>/assets/js/jquery.flot.min.js"></script>
<script src="<%= request.getContextPath() %>/assets/js/jquery.flot.pie.min.js"></script>
<script src="<%= request.getContextPath() %>/assets/js/jquery.flot.resize.min.js"></script>
<script src="<%= request.getContextPath() %>/assets/js/jquery.datetimepicker.min.js"></script>
<script src="<%= request.getContextPath() %>/assets/js/bootbox.js"></script>

<!-- ace scripts -->
<script src="<%= request.getContextPath() %>/assets/js/ace-elements.min.js"></script>
<script src="<%= request.getContextPath() %>/assets/js/ace.min.js"></script>
<!-- inline scripts related to this page -->
<script type="text/javascript">
jQuery(function($) {
		//selectmenu
	  $( ".number" ).css({
	  	width: '543px'
	  }).selectmenu({ 
	  	position: { 
	  		my : "left top", 
	  		at: "left bottom" 
	  	} 
	  })
	  //time
	  $('.form-start').datetimepicker({
     		 minDate: '0',
        "timePicker": true,
        "startDate": "",
        "endDate": "", 
    });
    $('.form-end').datetimepicker({
      minDate: '0'
    });
    $('.btn-mk').on('click',function(){
      var html = '<div class="row reset-password-dialog">  ' +
            '<div class="col-md-12"> ' +
            '<form class="col-md-12 form-horizontal" id="edit_category_form" action="?ct=recomm&ac=lunbo&even=saveedit" method="post" enctype="multipart/form-data"> ' +
            ' <input type=hidden name=order value="">' +
            '<input type=hidden name=type value="">' +
            '<div class="col-md-12 form-group"> ' +
            '<label class="col-md-4 control-label" for="title">广告名称</label> ' +
            '<div class="col-md-8"> ' +
            '<input id="title" name="title" type="text" value="" class="form-control input-md" maxlength="50">' +
            '</div> ' +
            '<div class="col-md-4"> ' +
            '<span id="label-for-title"></span> ' +
            '</div> ' +
            '</div> ' +
            '<div class="col-md-12 form-group"> ' +
            '<label class="col-md-4 control-label" for="title">所属项目</label> ' +
            '<div class="col-md-8" style="position: relative;"> ' +
            '<select name="number" class="number">'+
							'<option selected="selected">过期</option>'+
              '<option>投放中</option>'+
              '<option>新建</option>'+
              '<option>即将到期</option>'+
             '<option>全部</option>'+
						'</select>' +
						'<i class="fa fa-caret-down carets" style="position: absolute;top: 6px;left: 147px;font-size: 18px;color: #555;"></i>'+
            '</div> ' +
            '<div class="col-md-4"> ' +
            '<span id="label-for-title"></span> ' +
            '</div> ' +
            '</div> ' +
            '<div class="col-md-12 form-group"> ' +
            '<label class="col-md-4 control-label" for="title">广告类型</label> ' +
            '<div class="col-md-8" style="position: relative;"> ' +
            '<select name="number" class="number">'+
							'<option value="外部推广">外部推广</option>' +
              '<option selected="" value="公司内部">公司内部</option>' +
              '<option value="其他类型">其他类型</option>' +
              '<option value="社交推广">社交推广</option>' +
						'</select>' +
						'<i class="fa fa-caret-down carets" style="position: absolute;top: 6px;left: 147px;font-size: 18px;color: #555;"></i>'+
            '</div> ' +
            '<div class="col-md-4"> ' +
            '<span id="label-for-title"></span> ' +
            '</div> ' +
            '</div> ' +
            '<div class="col-md-12 form-group"> ' +
            '<label class="col-md-4 control-label" for="title">社交软件</label> ' +
            '<div class="col-md-8" style="position: relative;"> ' +
            '<select name="number" class="number">'+
							'<option value="QQ">QQ</option>' +
              '<option selected="" value="微信">微信</option>' +
              '<option value="其他">其他</option>' +
              '<option value="陌陌">陌陌</option>' +
              '<option value="不区分">不区分</option>' +
						'</select>' +
						'<i class="fa fa-caret-down carets" style="position: absolute;top: 6px;left: 147px;font-size: 18px;color: #555;"></i>'+
            '</div> ' +
            '<div class="col-md-4"> ' +
            '<span id="label-for-title"></span> ' +
            '</div> ' +
            '</div> ' +
            '<div class="col-md-12 form-group"> ' +
            '<label class="col-md-4 control-label" for="title">成本预算</label> ' +
            '<div class="col-md-8"> ' +
            '<input id="title" name="title" type="text" value="" class="form-control input-md" maxlength="50">' +
            '</div> ' +
            '<div class="col-md-4"> ' +
            '<span id="label-for-title"></span> ' +
            '</div> ' +
            '</div> ' +
            '<div class="col-md-12 form-group"> ' +
            '<label class="col-md-4 control-label" for="title">广告状态</label> ' +
            '<div class="col-md-8" style="position: relative;"> ' +
            '<select name="number" class="number">'+
							'<option selected="" value="新建">新建</option>' +
              '<option value="过期">过期</option>' +
              '<option value="即将过期">即将过期</option>' +
              '<option value="投放中">投放中</option>' +
						'</select>' +
						'<i class="fa fa-caret-down carets" style="position: absolute;top: 6px;left: 147px;font-size: 18px;color: #555;"></i>'+
            '</div> ' +
            '<div class="col-md-4"> ' +
            '<span id="label-for-title"></span> ' +
            '</div> ' +
            '</div> ' +
            '<div class="col-md-12 form-group"> ' +
            '<label class="col-md-4 control-label" for="title">开始时间</label> ' +
            '<div class="col-md-8" style="position: relative;"> ' +
            '<div class="input-group">'+
              '<input type="text" name="ptime" class="form-control form-end">'+
              '<span class="input-group-addon" style="cursor: pointer;">'+
                '<i class="fa fa-calendar bigger-110"></i>'+
              '</span>'+
            '</div>'+
            '</div> ' +
            '<div class="col-md-4"> ' +
            '<span id="label-for-title"></span> ' +
            '</div> ' +
            '</div> ' +
            '<div class="col-md-12 form-group"> ' +
            '<label class="col-md-4 control-label" for="title">结束时间</label> ' +
            '<div class="col-md-8" style="position: relative;"> ' +
		        '<div class="input-group">'+
              '<input type="text" name="ptime" class="form-control form-end">'+
              '<span class="input-group-addon" style="cursor: pointer;">'+
                '<i class="fa fa-calendar bigger-110"></i>'+
              '</span>'+
            '</div>'+
            '</div> ' +
            '<div class="col-md-4"> ' +
            '<span id="label-for-title"></span> ' +
            '</div> ' +
            '</div> ' +
            '</form> </div>  </div>';
      bootbox.dialog({
        title : "新建广告",
        message : html,
        buttons : {
          success : {
            label : "提交",
            className : "btn-success",
            callback: function() {
             alert(45)
              // $("#edit_category_form").submit();
            }
          },
           error : {
            label : "取消",
            className : "btn-danger"
          }
        }
      });
      $('select').css({width:'150px'});
      //time
      $('.input-group').css({width:'150px'})
		  $('.form-start').datetimepicker({
	     		 minDate: '0',
	        "timePicker": true,
	        "startDate": "",
	        "endDate": "", 
	    });
	    $('.form-end').datetimepicker({
	      minDate: '0'
	    });
    })
    
})
;(function(root, $) {
    root.openConfirm = function(title, message, successCB, failCB) {
        bootbox.dialog({
            message: message,
            title: title,
            buttons: {
                success: {
                    label: "确认",
                    className: "btn-success btn-lg",
                    callback: function() {
                        successCB.apply(this, arguments);
                    }
                },
                danger: {
                    label: "取消",
                    className: "btn-danger btn-lg",
                    callback: function() {
                        failCB.apply(this, arguments);
                    }
                }
            }
        });
    };

    root.openAlert = function(title, message, successCB) {
        bootbox.dialog({
            message: message,
            title: title,
            buttons: {
                danger: {
                    label: "确认",
                    className: "btn-danger btn-lg",
                    callback: function() {
                    	successCB && successCB.apply(this, arguments);
                    }
                }
            }
        });
    }
})(window, jQuery);
</script>
<div class="tooltip top in" style="display: none;">
	<div class="tooltip-inner">
	</div>
</div>
</body>
</html>