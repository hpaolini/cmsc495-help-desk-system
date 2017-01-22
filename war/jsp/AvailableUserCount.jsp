<%@ page import="Service.AuthenticationService" %>
<%@ page import="Service.ChatService" %>
<%
	if (AuthenticationService.isLoggedIn(session)) {
		if (AuthenticationService.isPrivilegeUser(session)) {
			out.println(ChatService.getCustomerChatLinks());
		} else {
			out.println(ChatService.getNumberOfOnlineTechs());
		}
	}
%>