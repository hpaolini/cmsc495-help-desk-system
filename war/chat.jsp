<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Service.AuthenticationService" %>   
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
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" media="screen" href="/css/jquery-theme.css" />
		<link rel="stylesheet" type="text/css" media="screen" href="/css/main.css" />
		<link rel="stylesheet" type="text/css" media="screen" href="/css/data-table-jui.css" />
	<title>Joe's Computer Service</title>
  </head>
  <body onload="setInterval('frames[0].scrollTo(0,9999999)',2000)">
	<div class="main">

		<jsp:include page="/header.jsp"/>

		<div class="ui-corner-all box">
		<table border="0" style="width:100%">
		<tr>
		  	<td colspan="2">
		  		<p>Please type your question in the textbox and press the "Send" button when you are ready. A technician will be right with you as soon as possible.</p>
				<IFRAME src="messages.jsp?user=<%= chatUserSession %>#last-message" width="100%" height="200"
			             scrolling="auto" frameborder="1">
			  [Your user agent does not support frames or is currently configured
			  not to display frames. However, you may visit
			  <A href="messages.jsp?user=<%= chatUserSession %>">the related document.</A>]
			  </IFRAME>
			</td>
		</tr>
		<tr>
			<form action="/sendmessage" method="post">
		  <td><textarea name="content" rows="3" style="width:100%"></textarea></td>
		  <td><input type="submit" value="Send" style="width:100%; height:100%"/></td>
		  <input type="hidden" name="user" value="<%= chatUserSession %>"/>
		  </form>
		</tr>
		</table>    
		</div>
	</div>
  </body>
</html>