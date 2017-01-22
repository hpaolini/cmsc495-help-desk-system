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

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;

import com.google.appengine.api.datastore.Key;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import Entity.Password;

/**
 * User accounts that can access the support site
 */
@PersistenceCapable(detachable="true")
public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;

    @Persistent
    private String firstName;

    @Persistent
    private String lastName;
    
    @Persistent
    private Boolean hasPrivileges;
    
    @Persistent
    private String username;
    
    @Persistent
    private String password;
    
    @Persistent
    private Date expirationDate;
    
    @Persistent
    private UserPresence currentPresence;

    public User(String firstName, String lastName, Boolean hasPrivileges, String username, String password, Date expirationDate) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        this.firstName = firstName;
        this.lastName = lastName;
        this.hasPrivileges = hasPrivileges;
        this.username = username;
        this.password = (new Password(password)).getHashedPassword();
        this.expirationDate = expirationDate;
        this.currentPresence = new UserPresence(UserPresence.PresenceStatus.OFFLINE);
    }

    // Accessors for the fields. JDO doesn't use these, but your application does.
    public Key getKey() {
        return key;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public Boolean getHasPrivileges() {
    	return hasPrivileges;
    }
    
    public void setHasPrivileges(Boolean hasPrivileges) {
    	this.hasPrivileges = hasPrivileges;
    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		this.password = (new Password(password)).getHashedPassword();
	}
	
	public void setUnhashedPassword(String password) {
		this.password = password;
	}
	
	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public UserPresence getCurrentPresence() {
		return currentPresence;
	}

	public void setCurrentPresence(UserPresence currentPresense) {
		this.currentPresence = currentPresense;
	}
}