<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="cmsc495.SessionListener" %>
<%@ page import="Service.AuthenticationService" %>
<%@ page import="Service.ChatService" %>
<%@ page import="Entity.User" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" media="screen" href="/css/jquery-theme.css" />
		<link rel="stylesheet" type="text/css" media="screen" href="/css/main.css" />
		<link rel="stylesheet" type="text/css" media="screen" href="/css/data-table-jui.css" />
	<title>Joe's Computer Service</title>
	</head>
<body>
	<div class="main">

		<jsp:include page="/header.jsp"/>

		<div class="ui-corner-all box">
			<%
				if (AuthenticationService.isLoggedIn(session)) {
					User user = AuthenticationService.getCurrentlyLoggedInUser(session);

					if (AuthenticationService.isPrivilegeUser(session)) {
			%>
						<h3>Admin actions</h3>
						<ul>
							<li><a href="/admin/user">Manage User List</a></li>
						</ul>
						<h3>Customer actions</h3>
						<ul>
							<li>Click on a username to start real-time chat support:
								<ul>
									<span id="OnlineStatus"><jsp:include page="/jsp/AvailableUserCount.jsp"/></span>
								</ul>
							</li>
				<%
					} else {
				%>
						<h4>Store Hours are: 8AM - 7PM Monday thru Friday</h4>
						
						<h3>Customer actions</h3>

						<ul>
							<li>Support number: <i>123-456-7890</i></li>
							<li><a href="chat.jsp">Real-time Chat Support</a>
								<ul>
									<li>Technicians online: <span id="OnlineStatus"><jsp:include page="/jsp/AvailableUserCount.jsp"/></span></li>
								</ul>
							</li>
				<%
					}
				%>
				
					<li><p class="action"><a href="help.jsp">Help Page</a></p></li>
				</ul>
			<%
			    } else {
			%>
				<p>
					Please login with you account credentials for technical support.
				</p>
				<form name="login" action="/login" method="post">
					Username:<br /><input type="text" name="username" /><br />
					Password:<br /><input type="password" name="password" /><br/>
					<input type="submit" value="Login" />
				</form>
			<%
			    }
			%>
			</div>
		</div>
		
		<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.6.4/jquery.min.js"></script>
		<script type="text/javascript" src="/js/third/jquery.timers.js"></script>
        <script type="text/javascript" src="/js/index.js"></script>
	</body>
</html>