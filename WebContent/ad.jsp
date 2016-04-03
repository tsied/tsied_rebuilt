<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>项目数据管理分析管理系统</title>
<meta name="description" content="overview &amp; stats">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
<link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/bootstrap.min.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/assets/font-awesome/4.2.0/css/font-awesome.min.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/assets/fonts/fonts.googleapis.com.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style">
<link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/jquery-ui.min.css" />
<link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/jquery.datetimepicker.css" />
<script src="<%= request.getContextPath() %>/assets/js/ace-extra.min.js"></script>
<script src="<%= request.getContextPath() %>/assets/js/jquery.2.1.1.min.js"></script>
<script type="text/javascript">
		window.jQuery || document.write("<script src='<%= request.getContextPath() %>/assets/js/jquery.min.js'>" + "<"+"/script>");
</script>
<script type="text/javascript">
		if('ontouchstart' in document.documentElement) document.write("<script src='<%= request.getContextPath() %>/assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
</script>
<script src="<%=request.getContextPath()%>/assets/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/js/jquery-ui.custom.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/js/jquery-ui.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/js/jquery.ui.touch-punch.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/js/jquery.easypiechart.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/js/jquery.sparkline.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/js/jquery.flot.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/js/jquery.flot.pie.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/js/jquery.flot.resize.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/js/jquery.datetimepicker.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/js/bootbox.js"></script>
<script src="<%=request.getContextPath()%>/assets/js/ace-elements.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/js/ace.min.js"></script>
<style type="text/css">
.jqstooltip {
	position: absolute;
	left: 0px;
	top: 0px;
	visibility: hidden;
	background: rgb(0, 0, 0) transparent;
	background-color: rgba(0, 0, 0, 0.6);
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr=#99000000,
		endColorstr=#99000000);
	-ms-filter:"progid:DXImageTransform.Microsoft.gradient(startColorstr=#99000000, endColorstr=#99000000)";
	color: white;
	font: 10px arial, san serif;
	text-align: left;
	white-space: nowrap;
	padding: 5px;
	border: 1px solid white;
	z-index: 10000;
}

.jqsfield {
	color: white;
	font: 10px arial, san serif;
	text-align: left;
}
</style>
</head>

<script type="text/javascript">
	function findAdvert(){
			$("#adForm").submit();
	}
	
	function addAdvert(){
		if($('.newAdName').eq(1).val()== "" || $('.newAdName').eq(1).val()=="undefined"){
			alert("广告名称不能为空")
			return;s
		}
		
		if($('.newAdAddr').eq(1).val()== "" || $('.newAdAddr').eq(1).val()=="undefined"){
			alert("投放地址不能为空");
			return;
		}
		
		if($('.newAdDomain').eq(1).val()== "" || $('.newAdDomain').eq(1).val()=="undefined"){
			alert("链接域名不能为空");
			return;
		}
		
		if($('.newAdCostBudget').eq(1).val()== "" || $('.newAdCostBudget').eq(1).val()=="undefined"){
			alert("成本预算不能为空");
			return;
		}
		
		if($('.newAdStartTime').eq(1).val()== "" || $('.newAdStartTime').eq(1).val()=="undefined"){
			alert("开始时间不能为空");
			return;
		}
		
		if($('.newAdEndTime').eq(1).val()== "" || $('.newAdEndTime').eq(1).val()=="undefined"){
			alert("结束时间不能为空");
			return;
		}
		
		 $.ajax({  
		       dataType : 'json',
		       contentType:"application/json;charset=UTF-8",
		       url:"/tsied_front/ad/add?adName="+$('.newAdName').eq(1).val()+"&adDomain="+$('.newAdDomain').eq(1).val()+"&adAddr="+$('.newAdAddr').eq(1).val()+"&adProject="+$('.newAdProject').eq(1).val()+"&adType="+$('.newAdType').eq(1).val()+"&socialSoft="+$('.newSocialSoft').eq(1).val()+"&adCostBudget="+$('.newAdCostBudget').eq(1).val()+"&adStatus="+$('.newAdStatus').eq(1).val()+"&adStartTime="+$('.newAdStartTime').eq(1).val()+"&adEndTime="+$('.newAdEndTime').eq(1).val(), 
		       success:function(result){
		    	   window.location.href="<%= request.getContextPath()%>/ad/index";
		    	}
		       }); 
	}
	
	function delAdvert(adId,adName){
		if(confirm('确定要删除['+adName+']吗?')){
			 $.ajax({  
			       dataType : 'json',
			       contentType:"application/json;charset=UTF-8",
			       url:"/tsied_front/ad/del?adId="+adId, 
			       success:function(result){
			    	   window.location.href="<%= request.getContextPath()%>/ad/index";
			    	}
			       }); 
		}
	}
	
	
</script>
<script type="text/javascript">
	jQuery(function($) {
	 $('.modify').on('click',function(){
		  var attrId = $(this).attr('data-id'),html = '';
	      	 $.ajax({  
			       dataType : 'json',
			       contentType:"application/json;charset=UTF-8",
			       url:"/tsied_front/ad/find?adId="+attrId, 
			       success:function(data){
			    	      html+= '<div class="row reset-password-dialog">  ' +
			    	            '<div class="col-md-12"> ' +
			    	            '<form class="col-md-12 form-horizontal"  name="modifyAdvert_form" id="modifyAdvert_form" action="<%=request.getContextPath() %>/ad/modify" enctype="multipart/form-data"> ' +
			    	            '<input type="hidden" id="modifyAdId" name="modifyAdId" value="'+data.advert.adId+'" class="modifyAdId form-control input-md" maxlength="50"/>'+
			    	            '<div class="col-md-12 form-group"> ' +
			    	            '<label class="col-md-4 control-label" for="title">广告名称</label> ' +
			    	            '<div class="col-md-8"> ' +
			    	            '<input id="modifyAdName" name="modifyAdName" type="text" value="'+data.advert.adName+'" class="modifyAdName form-control input-md" maxlength="50">' +
			    	            '</div> ' +
			    	            '<div class="col-md-4"> ' +
			    	            '<span id="label-for-title"></span> ' +
			    	            '</div> ' +
			    	            '</div> ' +
			    	            '<div class="col-md-12 form-group"> ' +
			    	            '<label class="col-md-4 control-label" for="title">所属项目</label> ' +
			    	            '<div class="col-md-8" style="position: relative;"> ' +
			    	            
			    	            '<select id="modifyAdProject" name="modifyAdProject" class="modifyAdProject select">';
			    	            for(var i = 0;i < data.adProject.length;i++){
			    	            	html+= ( data.adProject[i].dicId == data.advert.adProject ? '<option value='+data.advert.adProject+' selected>' + data.adProject[i].dicValue + '</option>':'<option value='+data.adProject[i].dicId+'>' + data.adProject[i].dicValue + '</option>');
			    	            }
								html+='</select>'+
			    				'<i class="fa fa-caret-down carets" style="position: absolute;top: 6px;left: 147px;font-size: 18px;color: #555;"></i>'+
			    	            '</div> ' +
			    	            '<div class="col-md-4"> ' +
			    	            '<span id="label-for-title"></span> ' +
			    	            '</div> ' +
			    	            '</div> ' +
			    	            '<div class="col-md-12 form-group"> ' +
			    	            '<label class="col-md-4 control-label" for="title">广告类型</label> ' +
			    	            '<div class="col-md-8" style="position: relative;"> ' +
			    	            
			    	            '<select id="modifyAdType" name="modifyAdType" class="modifyAdType select">';
								for(var i = 0;i < data.adType.length;i++){
			    	            	html+= ( data.adType[i].dicId == data.advert.adType ? '<option value='+data.advert.adType+' selected>' + data.adType[i].dicValue + '</option>':'<option value='+data.adType[i].dicId+'>' + data.adType[i].dicValue + '</option>');
			    	            }
			    	            
								html+='</select>'+
			    			    '<i class="fa fa-caret-down carets" style="position: absolute;top: 6px;left: 147px;font-size: 18px;color: #555;"></i>'+
			    	            '</div> ' +
			    	            '<div class="col-md-4"> ' +
			    	            '<span id="label-for-title"></span> ' +
			    	            '</div> ' +
			    	            
			    	            
			    	            '<div class="col-md-12 form-group">'+
								'<label class="col-md-4 control-label" for="title">投放地址</label>'+
								'<div class="col-md-8">'+
									'<input name="modifyAdAddr" id="modifyAdAddr" type="text" value="'+data.advert.adAddr+'" class="modifyAdAddr form-control input-md" maxlength="50">'+
								'</div>'+
								'<div class="col-md-4">'+
									'<span id="label-for-title"></span>'+
								'</div>'+
							'</div>'+
							'<div class="col-md-12 form-group">'+
								'<label class="col-md-4 control-label" for="title">链接域名</label>'+
								'<div class="col-md-8">'+
									'<input name="modifyAdDomain" id="modifyAdDomain" type="text" value="'+data.advert.adDomain+'" class="modifyAdDomain form-control input-md" maxlength="50">'+
								'</div>'+
								'<div class="col-md-4">'+
									'<span id="label-for-title"></span>'+
								'</div>'+
							'</div>'+
			    	            
			    	            
			    	            '</div> ' +
			    	            '<div class="col-md-12 form-group"> ' +
			    	            '<label class="col-md-4 control-label" for="title">社交软件</label> ' +
			    	            '<div class="col-md-8" style="position: relative;"> ' +
			    	            
			    	            
			    	            '<select name="modifySocialSoft" id="modifySocialSoft" class="modifySocialSoft select">';
			    	            
								for(var i = 0;i < data.socialSoft.length;i++){
			    	            	html+= ( data.socialSoft[i].dicId == data.advert.adSocialSoftware ? '<option value='+data.advert.adSocialSoftware+' selected>' + data.socialSoft[i].dicValue + '</option>':'<option value='+data.socialSoft[i].dicId+'>' + data.socialSoft[i].dicValue + '</option>');
			    	            }
			    	            
								html+='</select>'+
			    	            '<i class="fa fa-caret-down carets" style="position: absolute;top: 6px;left: 147px;font-size: 18px;color: #555;"></i>'+
			    	            '</div> ' +
			    	            '<div class="col-md-4"> ' +
			    	            '<span id="label-for-title"></span> ' +
			    	            '</div> ' +
			    	            '</div> ' +
			    	            '<div class="col-md-12 form-group"> ' +
			    	            '<label class="col-md-4 control-label" for="title">成本预算</label> ' +
			    	            '<div class="col-md-8"> ' +
			    	            '<input id="modifyAdCostBudget" name="modifyAdCostBudget" type="text" value="'+data.advert.adCostBudget+'" class="modifyAdCostBudget form-control input-md" maxlength="50">' +
			    	            '</div> ' +
			    	            '<div class="col-md-4"> ' +
			    	            '<span id="label-for-title"></span> ' +
			    	            '</div> ' +
			    	            '</div> ' +
			    	            '<div class="col-md-12 form-group"> ' +
			    	            '<label class="col-md-4 control-label" for="title">广告状态</label> ' +
			    	            '<div class="col-md-8" style="position: relative;"> '+
			    	            
			    	            
			    	        	'<select id="modifyAdStatus" name="modifyAdStatus" class="modifyAdStatus select">';
			    	        	for(var i = 0;i < data.adStatus.length;i++){
			    	            	html+= ( data.adStatus[i].dicId == data.advert.adStatus ? '<option value='+data.advert.adStatus+' selected>' + data.adStatus[i].dicValue + '</option>':'<option value='+data.adStatus[i].dicId+'>' + data.adStatus[i].dicValue + '</option>');
			    	            }
			    	        	
								html+='</select>'+
								'<i class="fa fa-caret-down carets" style="position: absolute;top: 6px;left: 147px;font-size: 18px;color: #555;"></i>'+
			    	            '</div> ' +
			    	            '<div class="col-md-4"> ' +
			    	            '<span id="label-for-title"></span> ' +
			    	            '</div> ' +
			    	            '</div> ' +
			    	            '<div class="col-md-12 form-group"> ' +
			    	            '<label class="col-md-4 control-label" for="title">开始时间</label> ' +
			    	            '<div class="col-md-8" style="position: relative;"> ' +
			    	            '<div class="input-group input-select">'+
			    	              '<input type="text" id="modifyAdStartTime" name="modifyAdStartTime" value="'+data.advert.startDate+'" class="modifyAdStartTime form-control form-end" readonly="readonly">'+
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
			    			        '<div class="input-group input-select">'+
			    	              '<input type="text"  id="modifyAdEndTime" name="modifyAdEndTime" value="'+data.advert.endDate+'" class="modifyAdEndTime form-control form-start" readonly="readonly">'+
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
			    	        title : "修改广告",
			    	        message : html,
			    	        buttons : {
			    	          success : {
			    	            label : "提交",
			    	            className : "btn-success",
			    	            callback: function() {
			    	            	modifyAdvert();
			    	            }
			    	          },
			    	           error : {
			    	            label : "取消",
			    	            className : "btn-danger"
			    	          }
			    	        }
			    	      });
			    	      $('.select').css({width:'150px'});
			    	      //time
			    	      $('.input-select').css({width:'150px'})
				    	      $('.form-start').datetimepicker({
				 	     		 minDate: '0',
				 	        "timePicker": true,
				 	        "startDate": "",
				 	        "endDate": "", 
				 	    });
				       $('.form-start,.form-end').datetimepicker({
				 		  'timepicker':false,
				 	        'datepicker':true,
				 	         'format':'Y-m-d'
				     });
			    	}
			       }); 
	  });
	  $( ".number" ).css({
	  	width: '543px'
	  }).selectmenu({ 
	  	position: { 
	  		my : "left top", 
	  		at: "left bottom" 
	  	} 
	  })
	  //time
	  $('.form-start,.form-end').datetimepicker({
		  'timepicker':false,
	        'datepicker':true,
	         'format':'Y-m-d'
    });
		
		
  function modifyAdvert(){
	  if($('#modifyAdName').val()== "" || $('#modifyAdName').val()=="undefined"){
			alert("广告名称不能为空")
			return;
		}
		
		if($('#modifyAdAddr').val()== "" || $('#modifyAdAddr').val()=="undefined"){
			alert("投放地址不能为空");
			return;
		}
		
		if($('#modifyAdDomain').val()== "" || $('#modifyAdDomain').val()=="undefined"){
			alert("链接域名不能为空");
			return;
		}
		
		if($('#modifyAdCostBudget').val()== "" || $('#modifyAdCostBudget').val()=="undefined"){
			alert("成本预算不能为空");
			return;
		}
		
		if($('#modifyAdStartTime').val()== "" || $('#modifyAdStartTime').val()=="undefined"){
			alert("开始时间不能为空");
			return;
		}
		
		if($('#modifyAdEndTime').val()== "" || $('#modifyAdEndTime').val()=="undefined"){
			alert("结束时间不能为空");
			return;
		}
	$.ajax({  
	    dataType : 'json',
	    contentType:"application/json;charset=UTF-8",
	    url:"/tsied_front/ad/modify?adId="+$('#modifyAdId').val()+"&adName="+$('#modifyAdName').val()+"&adProject="+$('#modifyAdProject').val()+"&adType="+$('#modifyAdType').val()+"&socialSoft="+$('#modifySocialSoft').val()+"&adCostBudget="+$('#modifyAdCostBudget').val()+"&adStatus="+$('.modifyAdStatus').val()+"&adAddr="+$('#modifyAdAddr').val()+"&adDomain="+$('#modifyAdDomain').val()+"&adStartTime="+$('#modifyAdStartTime').val()+"&adEndTime="+$('#modifyAdEndTime').val() , 
	    success:function(result){
	 	   window.location.href="<%=request.getContextPath()%>/ad/index";
	 	}
	    }); 
	}	
		
    $('.btn-mk').on('click',function(){
      var html = $('#newAdvert').html()
      bootbox.dialog({
        title : "新建广告",
        message : html,
        buttons : {
          success : {
            label : "提交",
            className : "btn-success",
            callback: function() {
             addAdvert();
            }
          },
           error : {
            label : "取消",
            className : "btn-danger"
          }
        }
      });
     
      $('.select').css({width:'150px'});
      //time
      $('.input-select').css({width:'150px'})
		  $('.form-start').datetimepicker({
	     		 minDate: '0',
	        "timePicker": true,
	        "startDate": "",
	        "endDate": "", 
	    });
      $('.form-start,.form-end').datetimepicker({
		  'timepicker':false,
	        'datepicker':true,
	         'format':'Y-m-d'
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
			<div class="navbar-buttons navbar-header pull-right"
				role="navigation">
				<ul class="nav ace-nav">
					<li class="light-blue">
					<a data-toggle="dropdown" href="#" class="dropdown-toggle"> 
						<img class="nav-user-photo" src="<%=request.getContextPath()%>/assets/avatars/user.jpg" alt="Jason's Photo"> 
						<span class="user-info"> <small>Welcome,</small>
								Admin
						</span> <i class="ace-icon fa fa-caret-down"></i>
					</a>
						<ul
							class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
							<li><a href="#"> <i class="ace-icon fa fa-power-off"></i>
									退出
							</a></li>
						</ul></li>
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
					<li>
						<a href="#" class="dropdown-toggle"> 
							<i class="menu-icon fa fa-gamepad"></i> 
							<span class="menu-text">${menu.menuName}</span>
							<b class="arrow fa fa-angle-down"></b>
						</a>
						 <b class="arrow"></b>
							<ul class="submenu">
								<li class="active"><c:if test="${!empty menu.childMenu}">
										<c:forEach items="${menu.childMenu}" var="childMenu">
											<ul class="submenu">
												<li class=""><a href="${childMenu.menuLink}"
													target="_self"><i class="menu-icon fa fa-caret-right"></i>${childMenu.menuName}</a>
													<b class="arrow"></b></li>
											</ul>
										</c:forEach>
									</c:if> <b class="arrow"></b>
								</li>
							</ul>
						</li>
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
						<li><i class="ace-icon fa fa-home home-icon"></i></li>
					</ul>
				</div>
				<div class="page-content">
					<div class="page-header">
						<h1>广告管理</h1>
					</div>
					<div class="row">
						<div class="col-xs-12">
							<form id="adForm" class="form-horizontal form-addGame" action="<%= request.getContextPath()%>/ad/index" role="form" enctype="multipart/form-data" data-method="form">
								<div class="form-group">
									<label class="col-xs-12 col-sm-3 control-label">广告名称</label>
									<div class="col-xs-12 col-sm-5">
										<span class="block input-icon input-icon-right"> <input
											type="text" name="adName" id="adName" value="${adName}"
											maxlength="12">
										</span>
									</div>
								</div>
								<div class="form-group">
									<label class="col-xs-12 col-sm-3 control-label">广告状态</label>
									<div class="col-xs-12 col-sm-5">
										<span class="block input-icon input-icon-right"> 
											<select name="adSatus" class="number" id="adSatus">
													<c:forEach items="${adStatusList}" var="ad" varStatus="status">
														<c:if test="${adSatus==null}">
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
								<div class="form-group">
									<label class="col-xs-12 col-sm-3 control-label">所属项目</label>
									<div class="col-xs-12 col-sm-5">
										<span class="block input-icon input-icon-right"> 
											<select name="adProject" class="number" id="adProject">
												<c:forEach items="${proList}" var="pro" varStatus="status">
													<c:if test="${adProject==null}">
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
													<c:if test="${adType==null}">
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
									<label class="col-xs-12 col-sm-3 control-label">开始时间</label>
									<div class="col-xs-12 col-sm-5" style="padding-left: 0;">
										<span class="block input-icon input-icon-right">
											<div class="col-xs-5 col-sm-5">
												<div class="input-group">
													<input type="text" name="startTime" id="startTime" class="form-control form-start" readonly="readonly" value="${startTime}"> 
													  <span class="input-group-addon" style="cursor: pointer;">
														<i class="fa fa-calendar bigger-110"></i>
													</span>
												</div>
											</div>
											<div style="float: left; width: 92px; text-align: center; line-height: 32px;">到</div>
											<div class="col-xs-5 col-sm-5" style="padding: 0;">
												<div class="input-group">
													<input type="text" name="startEnd" id="startEnd" class="form-control form-start" readonly="readonly" value="${ startEnd}">
													 <span class="input-group-addon" style="cursor: pointer;">
														<i class="fa fa-calendar bigger-110"></i>
													</span>
												</div>
											</div>
										</span>
									</div>
								</div>
								<div class="form-group">
									<label class="col-xs-12 col-sm-3 control-label">结束时间</label>
									<div class="col-xs-12 col-sm-5" style="padding-left: 0;">
										<span class="block input-icon input-icon-right">
											<div class="col-xs-5 col-sm-5">
												<div class="input-group">
													<input type="text" name="endTime" id="endTime" class="form-control form-end" readonly="readonly" value="${endTime }"> 
													<span class="input-group-addon" style="cursor: pointer;">
														<i class="fa fa-calendar bigger-110"></i>
													</span>
												</div>
											</div>
											<div style="float: left; width: 92px; text-align: center; line-height: 32px;">到</div>
											<div class="col-xs-5 col-sm-5" style="padding: 0;">
												<div class="input-group">
													<input type="text" name="endEnd" id="endEnd" class="form-control form-end" readonly="readonly" value="${endEnd }"> 
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
										<button class="btn btn-info submit" type="button" onclick="findAdvert();">
											<i class="ace-icon fa fa-search bigger-110"></i> 查询
										</button>
										&nbsp; &nbsp; &nbsp;
										<button class="btn btn-mk" type="button">
											<i class="ace-icon fa fa-file-o bigger-110"></i> 新建
										</button>
									</div>
								</div>
							</form>
							<form id="advert_form" action="<%=request.getContextPath() %>/ad/del" method="post">
								<table class="table table-striped table-bordered table-hover table-gameManager">
									<tr>
										<th>广告名称</th>
										<th>所属项目</th>
										<th>广告类型</th>
										<th class="hidden-480">广告地址</th>
										<th>投放域名</th>
										<th>开始时间</th>
										<th class="hidden-480">结束时间</th>
										<th class="hidden-70">广告状态</th>
										<th class="hidden-70">操作</th>
									</tr>

									<c:forEach items="${advertList}" var="advert" varStatus="status">
										<tr>
											<td>${advert.adName }</td>
											<td>${advert.adProject }</td>
											<td>${advert.adType }</td>
											<td class="hidden-480">${advert.adAddr }</td>
											<td>${advert.adDomain }</td>
											<td><fmt:formatDate value="${advert.adStartTime }"
													pattern="yyyy-MM-dd" /></td>
											<td><fmt:formatDate value="${advert.adEndTime }"
													pattern="yyyy-MM-dd" /></td>
											<td>${advert.adStatus}</td>
											<td>
												<div class="hidden-sm hidden-xs action-buttons">
													<a class="btn btn-danger btn-enable" href="javascript:void(0);" onclick="delAdvert(${advert.adId},'${advert.adName}')">删除</a>
													<a class="btn btn-primary btn-disable modify" data-id="${advert.adId}" href="javascript:void(0);">修改</a>
												</div>
											</td>
										</tr>
									</c:forEach>
								</table>
								<ul class="pagination pull-right no-margin col-xs-7">
									<li class="prev disabled"><a href="#">共${page.pageTotal }页</a></li>
									<li class="prev disabled"><a href="#">当前第${page.pageNo }页</a></li>
									<c:if test="${page.haveFirst}">
										<li><a href="<%= request.getContextPath()%>/ad/pageForm?page=pre&offset=${page.first }&adName=${adName}&adSatus=${adSatus}&adProject=${adProject}&adType=${adType}&startTime=${startTime}&startEnd=${startEnd}&endTime=${endTime}&endEnd=${endEnd}">首页</a></li>
									</c:if>
									<c:if test="${!page.haveFirst}">
										<li class="prev disabled"><a href="#">首页</a></li>
									</c:if>
									<c:if test="${page.haveFirst}">
										<li><a href="<%= request.getContextPath()%>/ad/pageForm?page=pre&offset=${page.pre }&adName=${adName}&adSatus=${adSatus}&adProject=${adProject}&adType=${adType}&startTime=${startTime}&startEnd=${startEnd}&endTime=${endTime}&endEnd=${endEnd}">上一页</a></li>
									</c:if>
									<c:if test="${!page.haveFirst}">
										<li class="prev disabled"><a href="#">上一页</a></li>
									</c:if>
									<c:if test="${page.haveLast}">
										<li><a href="<%= request.getContextPath()%>/ad/pageForm?page=next&offset=${page.next }&adName=${adName}&adSatus=${adSatus}&adProject=${adProject}&adType=${adType}&startTime=${startTime}&startEnd=${startEnd}&endTime=${endTime}&endEnd=${endEnd}">下一页</a></li>
									</c:if>
									<c:if test="${!page.haveLast}">
										<li class="prev disabled"><a href="#">下一页</a></li>
									</c:if>
									<c:if test="${page.haveLast}">
										<li><a href="<%= request.getContextPath()%>/ad/pageForm?page=pre&offset=${page.last }&adName=${adName}&adSatus=${adSatus}&adProject=${adProject}&adType=${adType}&startTime=${startTime}&startEnd=${startEnd}&endTime=${endTime}&endEnd=${endEnd}">尾页</a></li>
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
					<span class="bigger-120"> <span class="blue">Version V1.0.0 &nbsp;&nbsp;2016</span></span>
				</div>
			</div>
		</div>
		<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse"> 
			<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
		</a>
	</div>


	<div id="newAdvert" class="row reset-password-dialog"
		style="display: none">
		<div class="col-md-12">
			<form class="col-md-12 form-horizontal" action="" method="post"
				enctype="multipart/form-data">
				<div class="col-md-12 form-group">
					<label class="col-md-4 control-label" for="title">广告名称</label>
					<div class="col-md-8">
						<input name="newAdName" type="text" class="newAdName form-control input-md" maxlength="50">
					</div>
					<div class="col-md-4">
						<span id="label-for-title"></span>
					</div>
				</div>
				<div class="col-md-12 form-group">
					<label class="col-md-4 control-label" for="title">所属项目</label>
					<div class="col-md-8" style="position: relative;">
						<select name="newAdProject" class="newAdProject select">
							<c:forEach items="${proList}" var="pro" varStatus="status">
								<option value="${pro.dicId}">${pro.dicValue }</option>
							</c:forEach>
						</select> 
						<i class="fa fa-caret-down carets" style="position: absolute; top: 6px; left: 147px; font-size: 18px; color: #555;"></i>
					</div>
					<div class="col-md-4">
						<span id="label-for-title"></span>
					</div>
				</div>
				<div class="col-md-12 form-group">
					<label class="col-md-4 control-label" for="title">广告类型</label>
					<div class="col-md-8" style="position: relative;">
						<select name="newAdType" class="newAdType select">
							<c:forEach items="${adTypeList}" var="adType" varStatus="status">
								<option value="${adType.dicId}">${adType.dicValue }</option>
							</c:forEach>
						</select> 
					<i class="fa fa-caret-down carets" style="position: absolute; top: 6px; left: 147px; font-size: 18px; color: #555;"></i>
					</div>
					<div class="col-md-4">
						<span id="label-for-title"></span>
					</div>
				</div>
				<div class="col-md-12 form-group">
					<label class="col-md-4 control-label" for="title">投放地址</label>
					<div class="col-md-8">
						<input name="newAdAddr" type="text" class="newAdAddr form-control input-md" maxlength="50">
					</div>
					<div class="col-md-4">
						<span id="label-for-title"></span>
					</div>
				</div>
				<div class="col-md-12 form-group">
					<label class="col-md-4 control-label" for="title">链接域名</label>
					<div class="col-md-8">
						<input name="newAdDomain" type="text" class="newAdDomain form-control input-md" maxlength="50">
					</div>
					<div class="col-md-4">
						<span id="label-for-title"></span>
					</div>
				</div>

				<div class="col-md-12 form-group">
					<label class="col-md-4 control-label" for="title">社交软件</label>
					<div class="col-md-8" style="position: relative;">
						<select name="newSocialSoft" class="newSocialSoft select">
							<c:forEach items="${socialSoftList}" var="socialSoft" varStatus="status">
								<option value="${socialSoft.dicId}">${socialSoft.dicValue }</option>
							</c:forEach>
						</select> 
						<i class="fa fa-caret-down carets" style="position: absolute; top: 6px; left: 147px; font-size: 18px; color: #555;"></i>
					</div>
					<div class="col-md-4">
						<span id="label-for-title"></span>
					</div>
				</div>
				<div class="col-md-12 form-group">
					<label class="col-md-4 control-label" for="title">成本预算</label>
					<div class="col-md-8">
						<input name="newAdCostBudget" type="text" value="" class="newAdCostBudget form-control input-md" maxlength="50">
					</div>
					<div class="col-md-4">
						<span id="label-for-title"></span>
					</div>
				</div>
				<div class="col-md-12 form-group">
					<label class="col-md-4 control-label" for="title">广告状态</label>
					<div class="col-md-8" style="position: relative;">
						<select name="newAdStatus" class="newAdStatus select">
							<c:forEach items="${adStatusList}" var="adStatus" varStatus="status">
								<option value="${adStatus.dicId}">${adStatus.dicValue }</option>
							</c:forEach>
						</select> 
						<i class="fa fa-caret-down carets" style="position: absolute; top: 6px; left: 147px; font-size: 18px; color: #555;"></i>
					</div>
					<div class="col-md-4">
						<span id="label-for-title"></span>
					</div>
				</div>
				<div class="col-md-12 form-group">
					<label class="col-md-4 control-label" for="title">开始时间</label>
					<div class="col-md-8" style="position: relative;">
						<div class="input-group input-select">
							<input type="text" name="newAdStartTime" class="newAdStartTime form-control form-end" readonly="readonly">
							<span class="input-group-addon" style="cursor: pointer;">
								<i class="fa fa-calendar bigger-110"></i>
							</span>
						</div>
					</div>
					<div class="col-md-4">
						<span id="label-for-title"></span>
					</div>
				</div>
				<div class="col-md-12 form-group">
					<label class="col-md-4 control-label" for="title">结束时间</label>
					<div class="col-md-8" style="position: relative;">
						<div class="input-group input-select">
							<input type="text" name="newAdEndTime" class="newAdEndTime form-control form-end" readonly="readonly">
							<span class="input-group-addon" style="cursor: pointer;">
								<i class="fa fa-calendar bigger-110"></i>
							</span>
						</div>
					</div>
					<div class="col-md-4">
						<span id="label-for-title"></span>
					</div>
				</div>
			</form>
		</div>
	</div>
	
	<div class="tooltip top in" style="display: none;">
		<div class="tooltip-inner"></div>
	</div>
</body>
</html>