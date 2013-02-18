package uk.ac.glasgow.internman.stores;

import uk.ac.glasgow.internman.users.User;

public interface UserStore {
	
	/**
	 * Adds a new user to the store.
	 * @param surname
	 * @param ID
	 * @param forename
	 * @param password
	 */
	public void addUser(
			String ID, 
			String password,
			String forename, 
			String surname,
			String email);
	
	public void addUser(User u);
		
	/**
	 * Returns a user specified by the ID, if authentication is
	 * successful.
	 * 
	 * @param GUID
	 *            the user's ID
	 * @param password
	 *            the user's password
	 * @return the user specified by the ID, if the password
	 * matches that of
	 *         the user.
	 */
	public User getUser(String ID, String password);
	
}
