/*
 * Course:		CMSC 495
 * Project:		Final Project
 * Authors:		Hunter Paolini, Jonathan Horvath
 * Date:		11 December, 2011
 * Platform:	Win XP
 * Compiler:	JDK 1.7
 * IDE:			Eclipse Indigo
 */

package Entity;

import java.util.Date;
import java.io.Serializable;

import com.google.appengine.api.datastore.Key;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * Tracks the presence of a user
 */
@PersistenceCapable(detachable="true")
public class UserPresence implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;
    
    @Persistent(mappedBy = "currentPresence")
    private User user;
    
	@Persistent
    private PresenceStatus currentPresenceStatus;
    
    @Persistent
    private Date lastUpdate;
    
    public UserPresence(PresenceStatus currentPresenceStatus) {
    	setCurrentPresenceStatus(currentPresenceStatus);
    }
    
    public PresenceStatus getCurrentPresenceStatus() {
		return currentPresenceStatus;
	}


	public void setCurrentPresenceStatus(PresenceStatus currentPresenceStatus) {
		this.lastUpdate = new Date();
		this.currentPresenceStatus = currentPresenceStatus;
	}


	public Key getKey() {
		return key;
	}


	public Date getLastUpdate() {
		return lastUpdate;
	}
    
    
    public User getUser() {
		return user;
	}

	public enum PresenceStatus {OFFLINE, ONLINE, CHAT};
}
