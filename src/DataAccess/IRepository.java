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

import java.util.List;

import com.google.appengine.api.datastore.Key;

/**
 * Interface for persisting entity objects to a datastore
 * @param <AnyType>	Entity of the object that will be persisted to the repository
 */
public interface IRepository<AnyType> {
	public AnyType get(Key key);
	public List<AnyType> getAll();
	public List<AnyType> getFiltered(String filter);
	public void insert(AnyType entity);
	public void update(AnyType entity, Key key);
	public void delete(Key key);
}
