/*
 * Course:		CMSC 495
 * Project:		Final Project
 * Authors:		Hunter Paolini, Jonathan Horvath
 * Date:		11 December, 2011
 * Platform:	Win XP
 * Compiler:	JDK 1.7
 * IDE:			Eclipse Indigo
 */

package DataAccess;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.google.appengine.api.datastore.Key;

import Entity.User;
import Entity.UserPresence;

/**
 * Manages the persistence of the User entity object
 */
public class UserRepository implements IRepository<User> {
	
	private PersistenceManager pm;
	
	public UserRepository() {
			pm = PMF.get().getPersistenceManager();
	}

	@Override
	public User get(Key key) {
		User detachedCopy=null, user=null;
	    try{
	    	user = pm.getObjectById(User.class, key);
	    	user.setCurrentPresence(pm.detachCopy(user.getCurrentPresence()));
	        detachedCopy = pm.detachCopy(user);
	    }catch (JDOObjectNotFoundException e) {
	        return null;
	    } 
	    finally {
	        pm.close();
	    }
	    return detachedCopy;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAll() {
		List<User> detachedList=null, list=null;
	    try {
	    	Query query = pm.newQuery(User.class);
	        list = (List<User>)pm.newQuery(query).execute();            
	        detachedList = new ArrayList<User>();
	        for(User obj : list){
	        	obj.setCurrentPresence(pm.detachCopy(obj.getCurrentPresence()));
	            detachedList.add(pm.detachCopy(obj));
	        }
	    } finally {
	        pm.close();
	    }
	    return detachedList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getFiltered(String filter) {
		List<User> detachedList=null, list=null;
	    try {
	    	Query query = pm.newQuery(User.class);
	    	query.setFilter(filter);
	        list = (List<User>)pm.newQuery(query).execute();            
	        detachedList = new ArrayList<User>();
	        for(User obj : list){
	        	obj.setCurrentPresence(pm.detachCopy(obj.getCurrentPresence()));
	            detachedList.add(pm.detachCopy(obj));
	        }
	    } finally {
	        pm.close();
	    }
	    return detachedList;
	}

	@Override
	public void insert(User entity) {
		try {
			pm.makePersistent(entity);
		} finally {
			pm.close();
		}
	}
	
	@Override
	public void update(User entity, Key key) {
		User user;
		try {
			user = pm.getObjectById(User.class, key);
			user.setFirstName(entity.getFirstName());
			user.setLastName(entity.getLastName());
			user.setHasPrivileges(entity.getHasPrivileges());
			user.setUsername(entity.getUsername());
			user.setUnhashedPassword(entity.getPassword());
			user.setExpirationDate(entity.getExpirationDate());
			user.setCurrentPresence(entity.getCurrentPresence() == null ? new UserPresence(UserPresence.PresenceStatus.OFFLINE) : entity.getCurrentPresence());
		} finally {
			pm.close();
		}
	}
	
	public void delete(Key key) {
		try {
			User user = pm.getObjectById(User.class, key);
			pm.deletePersistent(user);
		} finally {
			pm.close();
		}
	}
}
