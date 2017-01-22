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

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;

import DataAccess.UserRepository;
import Entity.User;
import Entity.UserPresence;

/**
 * Utility for handling real-time chat
 */
public class ChatService {
	/**
	 * Helper class can't be instantiated
	 */
	private ChatService() {}
	
	/**
	 * Sign in user to the web application
	 * @param user	User to be signed in
	 */
	public static void signInUser(User user) {
		if (user != null) {
			if (user.getCurrentPresence() != null)
				user.getCurrentPresence().setCurrentPresenceStatus(UserPresence.PresenceStatus.ONLINE);
			new UserRepository().update(user, user.getKey());
		}
	}
	
	/**
	 * Sign out user to the web application
	 * @param user	User to be signed out
	 */
	public static void signOutUser(User user) {
		if (user != null) {
			if (user.getCurrentPresence() != null)
				user.getCurrentPresence().setCurrentPresenceStatus(UserPresence.PresenceStatus.OFFLINE);
			new UserRepository().update(user, user.getKey());	
		}
	}
	
	/**
	 * Get all the users that are signed into the web application
	 * @return A unordered list of customer that are signed into the web application
	 */
	public static ArrayList<User> getOnlineCustomers() {
		ArrayList<User> onlineCustomers = new ArrayList<User>();
		List<User> customerUsers =  new UserRepository().getFiltered("hasPrivileges == false");
		for (User customerUser : customerUsers) {
			if (customerUser.getCurrentPresence() != null && 
					customerUser.getCurrentPresence().getCurrentPresenceStatus() == UserPresence.PresenceStatus.ONLINE)
				onlineCustomers.add(customerUser);
		}
		return onlineCustomers;
	}
	
	/**
	 * Create a HTML formated string that displays the list of customers that are signed into the web application
	 * @return The HTML formated string of customers that are signed into the web application
	 */
	public static String getCustomerChatLinks() {
		String formattedHTML = "";
		ArrayList<User> onlineCustomers = getOnlineCustomers();
		if (onlineCustomers.size() == 0) {
			formattedHTML = "<li><i>no customers online</i></li>"; 
		} else {
			for (User customerUser : onlineCustomers) {
				String username = customerUser.getUsername();
				formattedHTML += "<li><a href='chat.jsp?user=" + username + "'>" + username + "</a></li>";
			}
		}
		
		return formattedHTML;
	}
	
	/**
	 * Calculates the number of customers that are that are signed into the web application
	 * @return A count of customers that are that are signed into the web application
	 */
	public static int getNumberOfOnlineCustomers() {
		int count = 0;
		List<User> customerUsers = new UserRepository().getFiltered("hasPrivileges == false");
		for (User customerUser : customerUsers) {
			if (customerUser.getCurrentPresence() != null && 
					customerUser.getCurrentPresence().getCurrentPresenceStatus() == UserPresence.PresenceStatus.ONLINE)
				count++;
		}
		return count;
	}
	
	/**
	 * Calculates the number of support technicians that are that are signed into the web application
	 * @return A count of support technicians that are that are signed into the web application
	 */
	public static int getNumberOfOnlineTechs() {
		int count = 0;
		List<User> techUsers =  new UserRepository().getFiltered("hasPrivileges == true");
		for (User techUser : techUsers) {
			if (techUser.getCurrentPresence() != null && 
					techUser.getCurrentPresence().getCurrentPresenceStatus() == UserPresence.PresenceStatus.ONLINE)
				count++;
		}
		return count;
	}
	
	/**
	 * Add a message to real-time chat
	 * @param user				User who sent the message
	 * @param content			The content of the message
	 * @param chatUserSession	The chat session identifier
	 */
	public static void addMessage(Key user, String content, String chatUserSession) {
		Key chatKey = KeyFactory.createKey("Chat", chatUserSession);
        Entity message = new Entity("Message", chatKey);
        message.setProperty("user", user);
        message.setProperty("date", new Date());
        message.setProperty("content", content);

        DatastoreService datastore =
                DatastoreServiceFactory.getDatastoreService();
        datastore.put(message);
	}
	
	/**
	 * Create a HTML formatted string to display the chat history
	 * @param chatUserSession	The chat session identifier
	 * @return The HTML formatted string for displaying the chat history
	 */
	public static String displayMessages(String chatUserSession) {
		StringBuilder returnString = new StringBuilder();
	    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();		
	    Key chatKey = KeyFactory.createKey("Chat", chatUserSession);		
			
	    Query query = new Query("Message", chatKey).addSort("date", Query.SortDirection.ASCENDING);		
	    List<Entity> messages = datastore.prepare(query).asList(FetchOptions.Builder.withLimit(99));		

		if (!messages.isEmpty()) {		
		    for (Iterator<Entity> it = messages.iterator(); it.hasNext();) {
			    Entity message = it.next();
		        User user = new UserRepository().get((Key) message.getProperty("user"));

				if (!it.hasNext()) {
					returnString.append("<div id='last-message'>");
				} else {
					returnString.append("<div>");
				}

		        returnString.append("<p><span style='color:#aaa;'>&raquo;&nbsp;");
		        returnString.append(message.getProperty("date"));
		        returnString.append("</span>&nbsp;&nbsp;<b>");		        
		        returnString.append(user.getFirstName() + " " + user.getLastName());
		        returnString.append("</b> wrote:</p>");		
		        returnString.append("<blockquote>");
		        returnString.append(message.getProperty("content"));
		        returnString.append("</blockquote></div>");
			}	
	    }		

		return returnString.toString();
	}
}
