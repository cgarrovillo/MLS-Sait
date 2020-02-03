package sait.mls.persistence;

import java.io.IOException;
import java.util.List;

import sait.mls.problemdomain.clientale.Client;

/**
 * Class description: 
 * @author Christian Garrovillo
 *
 */
public interface Broker
{
	/**
	 * Method to release resources allocated to the broker and save all modified data if needed.
	 */
	public void closeBroker();
	
	/**
	 * Saves a new object to the database or modifies an existing object in the database
	 * @param o
	 * @return true if operation is successful
	 */
	public boolean persist(Object o);
	
	/**
	 * Removes the supplied object from the database
	 * @param o 
	 */
	public boolean remove(Object o);
	
	/**
	 * Returns a list of matching objects based on the populated attribute of the object being supplied
	 * @param o
	 * @return the list of found objects
	 * @throws IOException 
	 */
	public List search(Object o) throws IOException;

}
