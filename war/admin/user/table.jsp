<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="java.util.List" %>
 <%@ page import="java.text.SimpleDateFormat" %>
 <%@ page import="com.google.appengine.api.datastore.KeyFactory" %>
 <%@ page import="Entity.User" %>
 <%@ page import="DataAccess.UserRepository" %>
<%@ page import="Service.AuthenticationService" %>
<%
	// Check that user is logged in and have privilege access
    if (!AuthenticationService.isLoggedIn(session)) {
    	response.sendError(403); // not authenticated
    }
    if (!AuthenticationService.isPrivilegeUser(session)) {
    	response.sendError(401); // not authorized
    }
%>
<table id="UserTable" class="display" cellspacing="0" cellpadding="0" border="0">
	<thead>
	    <tr>
	    	<th>Key</th>
	        <th>First Name</th>
	        <th>Last Name</th>
	        <th>Has Privileges</th>
	        <th>Username</th>
	        <th>Password</th>
	        <th>Expiration Date</th>
	    </tr>
	</thead>
	<tbody>
		<%
			List<User> users = new UserRepository().getAll();
			for (User user : users)
			{
           		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		%>
		<tr>
			<td><%=KeyFactory.keyToString(user.getKey())%></td>
       		<td><%=user.getFirstName()%></td>
           	<td><%=user.getLastName()%></td>
           	<td><%=user.getHasPrivileges()%></td>
           	<td><%=user.getUsername() %></td>
           	<td><%=user.getPassword() %></td>
           	<td><%=formatter.format(user.getExpirationDate()) %></td>
		</tr>
  		<%	} %>
	</tbody>
</table>