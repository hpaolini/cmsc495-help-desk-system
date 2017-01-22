<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" media="screen" href="/css/jquery-theme.css" />
		<link rel="stylesheet" type="text/css" media="screen" href="/css/main.css" />
		<link rel="stylesheet" type="text/css" media="screen" href="/css/data-table-jui.css" />
		<title>User Administration</title>
	</head>
	<body>
		<div class="main">
			<jsp:include page="/header.jsp"/>
		
			<div id="DataTable" class="ui-corner-all box">
				<jsp:include page="/admin/user/table.jsp"/>
				<div>
					<button id="InsertButton">Insert</button>
					<button id="UpdateButton">Update</button>
					<button id="DeleteButton">Delete</button>
				</div>
			</div> 
			
			<div id="ActionDialog" style="display: none;">
				<form id="UserAction">
					<input type="hidden" id="CurrentUserKey" name="key"/>
					<fieldset>
						<ol>
							<li>
								<label for="CurrentUserFirstName">First Name:</label>
								<input type="text" id="CurrentUserFirstName" name="firstName"/>
							</li>
							<li>
								<label for="CurrentUserLastName">Last Name:</label>
								<input type="text" id="CurrentUserLastName" name="lastName"/>
							</li>
							<li>
								<label for="CurrentUserUsername">Username:</label>
								<input type="text" id="CurrentUserUsername" name="username"/>
							</li>
							<li>
								<label for="CurrentUserPassword">Password:</label>
								<input type="password" id="CurrentUserPassword" name="password"/>
							</li>
							<li>
								<label for="CurrentUserHasPrivileges">Has Privileges:</label>
								<input type="checkbox" id="CurrentUserHasPrivileges" name="hasPrivileges"/>
							</li>
							<li>
								<label for="CurrentUserExpirationDate">Expiration Date:</label>
								<input type="text" id="CurrentUserExpirationDate" name="expirationDate"/>
							</li>
						</ol>
					</fieldset>
				</form>
				<button id="CancelButton">Cancel</button>
				<button id="CommitButton">Commit</button>
			</div>
		</div>

        <script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.6.4/jquery.min.js"></script>
        <script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jqueryui/1.8.16/jquery-ui.min.js"></script>
        <script type="text/javascript" src="/js/third/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="/js/user.js"></script>
	</body>
</html>
