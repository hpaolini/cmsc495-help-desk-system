/*
 * Course:		CMSC 495
 * Project:		Final Project
 * Authors:		Hunter Paolini, Jonathan Horvath
 * Date:		11 December, 2011
 * Platform:	Win XP
 * Compiler:	JDK 1.7
 * IDE:			Eclipse Indigo
 */

package Servlet;

import Service.AuthenticationService;
import Service.ChatService;
import Entity.User;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Handles requests to send messages to real-time chat
 */
@SuppressWarnings("serial")
public class ChatServlet extends HttpServlet {
	private static final Logger log = Logger.getLogger(ChatServlet.class.getName());

    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        User user = AuthenticationService.getCurrentlyLoggedInUser(req.getSession(false));
        String username = user.getUsername();
        
        // parameter 'user' is used by support accounts for multiple chat sessions
        // there is no parameter for customer accounts
        String chatUserSession = (req.getParameter("user") != null) ? req.getParameter("user") : username;
        
        String content = req.getParameter("content");
        ChatService.addMessage(user.getKey(), content, chatUserSession);
        log.info("Added an entry for " + user.getUsername());
        resp.sendRedirect("/chat.jsp?user="
                + chatUserSession);
    }
}