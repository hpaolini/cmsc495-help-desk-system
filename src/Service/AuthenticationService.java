/*
 * Course:		CMSC 495
 * Project:		Final Project
 * Authors:		Hunter Paolini, Jonathan Horvath
 * Date:		11 December, 2011
 * Platform:	Win XP
 * Compiler:	JDK 1.7
 * IDE:			Eclipse Indigo
 */

package Service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import DataAccess.UserRepository;
import Entity.User;

/**
 * Utility for handling authentication operations 
 */
public class AuthenticationService {
	
	/**
	 * Helper class can't be instantiated
	 */
	private AuthenticationService() {}
	
	/**
	 * Is a user currently logged into the web site
	 * @param session	Session data for the user
	 * @return	True if there is an user logged into the site
	 */
	public static boolean isLoggedIn(HttpSession session) {
		User user = (User) session.getAttribute("loggedInUser");

		return user != null;
	}
	
	/**
	 * Obtain the user name of the user that is currently logged into the site
	 * @param session	Session data for the user
	 * @return	The user name of the currently logged in user, null if there is no user currently logged into the site
	 */
	public static String currentlyLoggedInUsername(HttpSession session) {
		User user = (User) session.getAttribute("loggedInUser");
		
		if (user == null)
			return null;
		
		return user.getUsername();
	}
	
	public static User getCurrentlyLoggedInUser(HttpSession session) {
		return (User) session.getAttribute("loggedInUser");
	}
	
	/**
	 * Does the currently logged in user have privilege access to the site
	 * @param session	Session data for the user
	 * @return	True if the currently logged in user has privilege access
	 */
	public static boolean isPrivilegeUser(HttpSession session) {
		
		User user = (User) session.getAttribute("loggedInUser");

	    return user != null && user.getHasPrivileges();
	}
	
	/**
	 * Determine if the customer has an expired support contract
	 * @param session	Session data for the user
	 * @return	True if the contract is expired
	 */
	public static boolean isExpiredUser(HttpSession session) {
		
		User user = (User) session.getAttribute("loggedInUser");
		
	    return user != null && user.getExpirationDate().before(new Date());
	}
	
	/**
	 * Log into the web application
	 * @param session	Session data for the user
	 * @param username	Username that was entered by the user
	 * @param password	Encrypted password entered by the user
	 * @return Result of the attempt to log into the web application
	 */
	public static AccountStatus logon(HttpSession session, String username, String password) {
		List<User> users = new UserRepository().getFiltered("username == '" + username + "' && password == '" + password + "'");
	
		if (users.size() == 0) 
			return AccountStatus.INVALID;	
		
		if (users.get(0).getHasPrivileges() == false && users.get(0).getExpirationDate().before(new Date()))
			return AccountStatus.EXPIRED;
		
		// Set the user presence before writing to the session data
		ChatService.signInUser(users.get(0));
		
		session.setAttribute("loggedInUser", users.get(0));
		return AccountStatus.VALID;
	}
	
	/**
	 * Logout the currently logged in user
	 * @param session	Session data for the user
	 */
	public static void logout(HttpSession session) {
		session.invalidate();
	}
	
	/**
	 * Status of the account
	 */
	public enum AccountStatus {VALID, INVALID, EXPIRED};
}
