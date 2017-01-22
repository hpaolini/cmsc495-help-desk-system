<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Service.AuthenticationService" %>
<%@ page import="Service.ChatService" %>   
<%@ page import="Entity.User" %>
<%
	// Check that user is logged in
	if (!AuthenticationService.isLoggedIn(session)) {
	 response.sendError(403); // not authenticated
	}
	      
    User user = AuthenticationService.getCurrentlyLoggedInUser(session);
    String username = user.getUsername();
    String chatUserSession = (request.getParameter("user") != null) ? request.getParameter("user") : username;
	
	if (!(username.equals(chatUserSession) || AuthenticationService.isPrivilegeUser(session))) {
    	response.sendError(401); // not authorized
    }
%>
<html>
  <head>
  <meta http-equiv="refresh" content="5">
    <link type="text/css" rel="stylesheet" href="/css/main.css" />
  </head>
  <body>
	<%= ChatService.displayMessages(chatUserSession) %>
  </body>
</html>