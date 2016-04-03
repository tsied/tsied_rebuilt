<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
<%   
          java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd"); 
          java.util.Date currentTime = new java.util.Date();//得到当前系统时间 
          
          String startDate =(String) request.getAttribute("startDate");
          String endDate = (String) request.getAttribute("endDate"); //将日期时间格式化 
          
          if(startDate==null || startDate=="undefined"){
        	  startDate =  formatter.format(currentTime);
        	  java.util.Date  d = formatter.parse(startDate);     
              java.util.Calendar cal= java.util.Calendar.getInstance();
              cal.setTime(d);
              cal.add(java.util.Calendar.DATE, -15);  //减1天
              startDate = formatter.format(cal.getTime());
          }
          
          if(endDate==null || endDate=="undefined"){
        	  endDate =  formatter.format(currentTime);
          }
          
          String access = (String)request.getAttribute("access");
          
 %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>经分系统</title>
	
    <link href="<%= request.getContextPath() %>/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%= request.getContextPath() %>/css/metisMenu.min.css" rel="stylesheet">
    <link href="<%= request.getContextPath() %>/css/sb-admin-2.css" rel="stylesheet">   
    <link href="<%= request.getContextPath() %>/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/css.css" />
    <!-- jQuery -->
    <script src="<%= request.getContextPath() %>/js/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="<%= request.getContextPath() %>/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="<%= request.getContextPath() %>/js/metisMenu.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="<%= request.getContextPath() %>/js/sb-admin-2.js"></script>
    
	<script src="<%= request.getContextPath() %>/js/time/WdatePicker.js"></script>
	
	<script src="<%= request.getContextPath() %>/js/highcharts.js"></script>
	<script src="<%= request.getContextPath() %>/js/json2.js"></script>
	<script src="<%= request.getContextPath() %>/js/json_parse_state.js"></script>
	<script src="<%= request.getContextPath() %>/js/json_parse.js"></script>
	<style type="text/css">
	${
		demo.css
	}
	</style>
	
	<script type="text/javascript">
	function query(){
		$("#form").submit();
	}
	</script>
	
</head>


<body>

		
    <div id="wrapper">
        <!-- Navigation -->
        <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" ></a>
            </div>
            <!-- /.navbar-header -->

            <ul class="nav navbar-top-links navbar-right">

                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-bell fa-fw"></i>  <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-alerts">
                    </ul>
                    <!-- /.dropdown-alerts -->
                </li>
                <!-- /.dropdown -->
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-user fa-fw"></i>  <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-user">
                       
                    </ul>
                    <!-- /.dropdown-user -->
                </li>
                <!-- /.dropdown -->
            </ul>
            <!-- /.navbar-top-links -->

            <div class="navbar-default sidebar">
            
            
            <div class="sidebar-nav navbar-collapse">
                    <ul class="nav" id="side-menu">
                        <li>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="<%=request.getContextPath() %>/uploadFile.jsp">上传模板</a>
                                </li>
                            </ul>
                        </li>
                    </ul>
              </div>
            
            <div class="sidebar-nav navbar-collapse">
                    <ul class="nav" id="side-menu">
                        <li>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="<%=request.getContextPath() %>/front/initScriptConfig">配置脚本</a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
                
                
                <div class="sidebar-nav navbar-collapse">
                    <ul class="nav" id="side-menu">
                        <li>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="<%=request.getContextPath() %>/index.jsp">游戏指标</a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
                
                <!-- /.sidebar-collapse -->
            </div>
            
            <!-- /.navbar-static-side -->
            
            <form  id = "form" action="<%=request.getContextPath() %>/front/index"  method="post">
			<input id="startDate" name="startDate" type="text" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDate\')}'})" value="<%= startDate%>"  readonly="readonly"  class="Wdate"/>
			<input id="endDate" name="endDate" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startDate\')}'})" value="<%= endDate %>" readonly="readonly" type="text"  class="Wdate"/>
			<input type="button" name = "button" value="搜索" onclick="query();" class="Wdate1"/>
		</form>
        </nav>

        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">游戏指标</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
            <!-- /.col-lg-12 -->
            
            
            
            <c:forEach items="${scriptList}"  var="script" varStatus="status">
									
                <div class="col-lg-6">
                    <div class="panel panel-default">
                     <div class="panel-heading">
                            	${script.chartName}
                        </div>
                        <div class="panel-body">
                            <div class="flot-chart">
                                <div class="flot-chart-content" id="flot-pie-chart">
                                      <jsp:include page="${script.chartType}">
	                                      <jsp:param value="<%= access %>" name="access"/>
	                                      <jsp:param value="${script.divId}" name="divId" />
	                                      <jsp:param value="${script.scriptName}" name="scriptName"/>
	                                      <jsp:param value="${script.esIndex}" name="esIndex"/>
	                                      <jsp:param value="${script.eSType}" name="eSType" />
	                                      <jsp:param value="${script.chartName}" name="chartName" />
	                                      <jsp:param value="${script.chartType}" name="chartType" />
                                      </jsp:include>
                                </div>
                            </div>
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
			</c:forEach>
                
           
                
            </div>
            <!-- /.row -->
        </div>

    </div>
    
    
</body>

<script type="text/javascript">

		$(function(){
				var esIndexList = document.getElementsByName("esIndex");
				var scriptNameList = document.getElementsByName("scriptName");
				var divIdList = document.getElementsByName("divId");
				var typeNameList = document.getElementsByName("eSType");
				var chartNameList = document.getElementsByName("chartName");
				var chartTypeList = document.getElementsByName("chartType");
				var url ;
				for(var i=0;i<divIdList.length;i++)
				{
		   		   	if($("#access").val() == "null"){
		   		    	   url= "front/report?templateName="+scriptNameList[i].value+"&chartName="+chartNameList[i].value+"&chartType="+chartTypeList[i].value+"&esType="+typeNameList[i].value+"&esIndex="+esIndexList[i].value+"&divId="+divIdList[i].value+"&startDate="+$("#startDate").val()+"&endDate="+$("#endDate").val();
		   		       }
		   		  	else{
		   		  		url = "report?templateName="+scriptNameList[i].value+"&chartName="+chartNameList[i].value+"&chartType="+chartTypeList[i].value+"&esType="+typeNameList[i].value+"&esIndex="+esIndexList[i].value+"&divId="+divIdList[i].value+"&startDate="+$("#startDate").val()+"&endDate="+$("#endDate").val();
		   		  	} 
		   		
		   			$.ajax({  
		   			
		   			       type:"POST",  
		   			       dataType : 'json',
		   			       contentType:"application/json;charset=UTF-8",
		   			       url:url, 
		   			       success:function(json){
		   			    	   if(json.chartType == "lineChat.jsp"){
			   			    		$("#"+json.divId).highcharts({
			   	            	        title: {
			   	            	            text: '',
			   	            	            x: -20 //center
			   	            	        },
			   	            	        subtitle: {
			   	            	            text: '',
			   	            	            x: -20
			   	            	        },
			   	            	        xAxis: {
			   	            				 
			   	            	             categories:json.categories
			   	            	        },
			   	            	        yAxis: {
			   	            	        	min:0,
			   	            	        	allowDecimals:true,
			   	            	            plotLines: [{
			   	            	                value: 0,
			   	            	                width: 1,
			   	            	                color: '#808080'
			   	            	            }]
			   	            	        },
			   	            	        
			   	            	        legend: {
			   	            	            layout: 'vertical',
			   	            	            align: 'right',
			   	            	            verticalAlign: 'middle',
			   	            	            borderWidth: 0
			   	            	        },
			   	            	        series: json.series
			   	            	    });
		   			    	   }
		   			    	   else if(json.chartType == "barChat.jsp"){
		   			    		$("#"+json.divId).highcharts({
		   			    	        chart: {
		   			    	            type: 'column'
		   			    	        },
		   			    	        title: {
		   			    	            text: json.chartName
		   			    	        },
		   			    	        xAxis: {
		   			    	            categories: json.categories,
		   			    	            crosshair: true
		   			    	        },
		   			    	        yAxis: {
		   			    	            min: 0,
		   			    	            /* title: {
		   			    	                text: '钻石数'
		   			    	            } */
		   			    	        },
		   			    	        tooltip: {
		   			    	            headerFormat: '<span style="font-size:20px">{point.key}</span><table>',
		   			    	            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
		   			    	                '<td style="padding:0"><b>{point.y:.2f}</b></td></tr>',
		   			    	            footerFormat: '</table>',
		   			    	            shared: true,
		   			    	            useHTML: true
		   			    	        },
		   			    	        plotOptions: {
		   			    	            column: {
		   			    	                pointPadding: 0.2,
		   			    	                borderWidth: 0
		   			    	            }
		   			    	        },
		   			    	        series: json.series
		   			    	    });
		   			    	   } 	 
		   			    	
		   			    	
		   			             
		   			       }  
		   			 });  
			}
		});
		
	
	</script>

</html>
