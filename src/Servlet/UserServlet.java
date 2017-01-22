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

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import Entity.User;
import DataAccess.UserRepository;
import DataAccess.IRepository;

/**
 * Handles requests to manage user accounts
 */
@SuppressWarnings("serial")
public class UserServlet extends HttpServlet {
 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException, NoSuchAlgorithmException {
        String action = request.getParameter("action");
    	IRepository<User> repository = new UserRepository();
    	SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    	Date expirationDate = new Date();
        if (action != null && action.equalsIgnoreCase("insert")) {
        	String firstName = request.getParameter("firstName");
        	String lastName = request.getParameter("lastName");
        	Boolean hasPrivileges = request.getParameter("hasPrivileges") != null; // only support tech has privileges
        	String username = request.getParameter("username");
        	String password = request.getParameter("password");
        	try {
				expirationDate = formatter.parse(request.getParameter("expirationDate"));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	User insertUser = new User(firstName, lastName, hasPrivileges, username, password, expirationDate);
        	repository.insert(insertUser);
        	
        	// Send the key as a response
        	response.setContentType("text/plain");
        	PrintWriter out = response.getWriter();
        	out.print(KeyFactory.keyToString(insertUser.getKey()));
        	out.flush();
        }
        else if (action != null && action.equalsIgnoreCase("update")) {
        	Key key = KeyFactory.stringToKey(request.getParameter("key"));
        	String firstName = request.getParameter("firstName");
        	String lastName = request.getParameter("lastName");
        	Boolean hasPrivileges = request.getParameter("hasPrivileges") != null;
        	String username = request.getParameter("username");
        	String password = request.getParameter("password");
        	try {
				expirationDate = formatter.parse(request.getParameter("expirationDate"));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	User updateUser = new User(firstName, lastName, hasPrivileges, username, password, expirationDate);
        	repository.update(updateUser, key);
        }
        else if (action != null && action.equalsIgnoreCase("delete")) {
        	Key key = KeyFactory.stringToKey(request.getParameter("key"));
        	repository.delete(key);
        }
        else
        {
        	getServletConfig().getServletContext().getRequestDispatcher("/admin/user.jsp").forward(request,response);
        }
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
			processRequest(request, response);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
			processRequest(request, response);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
 