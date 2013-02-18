package uk.ac.glasgow.internman.stores;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import uk.ac.glasgow.internman.impl.Advert;
import uk.ac.glasgow.internman.users.User;

/** A simple file based implementation of a store for users. */
public class UserStoreImpl implements UserStore {

	private Map<String,User> users;
	private File storeFile;
	
	/**
	 * Constructs a new store in the specified file location.
	 * @param fileName
	 */
	public UserStoreImpl(String fileName){
		storeFile = new File(fileName);
		if (storeFile.exists()){
			try {
				ObjectInputStream ois = 
					new ObjectInputStream(new FileInputStream(storeFile));
				users = (Map<String,User>)(ois.readObject());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		else
			this.users = new HashMap<String,User>();
	}
	
	@Override
	public void addUser(String ID, String password, String forename, String surname, String email) {
		User user = new User(ID,password,forename,surname, email);
		users.put(ID,user);
		
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(storeFile));
			oos.writeObject(users);
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void addUser(User u) {
		users.put(u.getID(), u);
		
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(storeFile));
			oos.writeObject(users);
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public User getUser(String ID, String password) {
		User user = users.get(ID);
		if (userExists(ID) && user.authenticate(password)) return user;
		else return null;
	}
	
	public User getUser(String ID) {
		User user = users.get(ID);
		if (userExists(ID)) return user;
		else return null;
	}
	
	public Map<String,User> getUsers() {
		return this.users;
	}

	public boolean userExists(String ID){
		User user = this.users.get(ID);
		return !(user == null);
	}
	
	
}
