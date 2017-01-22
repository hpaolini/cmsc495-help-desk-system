/*
 * Course:		CMSC 495
 * Project:		Final Project
 * Authors:		Hunter Paolini, Jonathan Horvath
 * Date:		11 December, 2011
 * Platform:	Win XP
 * Compiler:	JDK 1.7
 * IDE:			Eclipse Indigo
 */

package cmsc495;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entity.Password;
import Entity.User;
import Service.AuthenticationService;
import Service.AuthenticationService.AccountStatus;

@SuppressWarnings("serial")
public class Authentication extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		try {
			AccountStatus currentAccountStatus = AuthenticationService.logon(req.getSession(true), req.getParameter("username"), (new Password(req.getParameter("password"))).getHashedPassword());
			if (currentAccountStatus == AccountStatus.VALID) {
				User user = AuthenticationService.getCurrentlyLoggedInUser(req.getSession(true));
				System.out.println(user.getExpirationDate() + "\n" + new Date());
				resp.sendRedirect("index.jsp");
			} else if (currentAccountStatus == AccountStatus.EXPIRED) {
				resp.sendError(402); // expired
			} else {
				resp.sendError(401); // not authenticated
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
