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

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import Service.AuthenticationService;
import Service.ChatService;

public class SessionListener implements HttpSessionListener {
	public void sessionCreated(HttpSessionEvent event) {
		event.getSession().setMaxInactiveInterval(600);
	}
	
	public void sessionDestroyed(HttpSessionEvent event) {
		ChatService.signOutUser(AuthenticationService.getCurrentlyLoggedInUser(event.getSession()));
	}
}