<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="Service.AuthenticationService" %>
<%
	AuthenticationService.logout(session);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<link rel="stylesheet" type="text/css" media="screen" href="/css/jquery-theme.css" />
		<link rel="stylesheet" type="text/css" media="screen" href="/css/main.css" />
		<link rel="stylesheet" type="text/css" media="screen" href="/css/data-table-jui.css" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Joe's Computer Service</title>
	</head>
<body>
	<div class="main">
		<jsp:include page="/header.jsp"/>
		
		<div class="ui-corner-all box">
			<p>You have been successfully logged out! Click <a href="/">here</a> to return to the login page.</p>
		</div>
	</div>
</body>
