<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title><%=request.getParameter("chartName") %></title>
	
	</head>
	<body>
	
		<div id="<%= request.getParameter("divId") %>" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
		
		<input type="hidden" name="access" id="access" value="<%= request.getParameter("access") %>"/>
		<input type="hidden" name="divId" id="divId" value="<%= request.getParameter("divId") %>"/>
		<input type="hidden" name="esIndex" id="esIndex" value="<%= request.getParameter("esIndex") %>"/>
		<input type="hidden" name="scriptName" id="scriptName" value="<%= request.getParameter("scriptName") %>"/>
		<input type="hidden" name="eSType" id="eSType" value="<%= request.getParameter("eSType") %>"/>
		<input type="hidden" name="chartName" id="chartName" value="<%= request.getParameter("chartName") %>"/>
		<input type="hidden" name="chartType" id="chartType" value="<%= request.getParameter("chartType") %>"/>
	</body>
	
		
</html>
