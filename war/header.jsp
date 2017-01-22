<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="cmsc495.SessionListener" %>
<%@ page import="Service.AuthenticationService" %>
<%@ page import="Entity.User" %>

<div class="ui-corner-all box">
  <table border="0" width="100%">
    <tbody>
      <tr>
        <td>
          <h1><img src="http://cmsc495dev.appspot.com/images/computer.png" style="vertical-align:middle;margin-right:1em;"><a href="/index.jsp">Joe's Computer Service</a></h1>
        </td>
        <td style="text-align:right">
			<%
				if (AuthenticationService.isLoggedIn(session)) {
					User user = AuthenticationService.getCurrentlyLoggedInUser(session);
			%>
				<p>Welcome back, <b><%= user.getFirstName() %>!</b> [<a href="/logout.jsp">logout</a>]</p>
			<%
				}
			%>
        </td>
      </tr>
    </tbody>
  </table>
</div>