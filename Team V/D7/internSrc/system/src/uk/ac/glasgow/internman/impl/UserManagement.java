package uk.ac.glasgow.internman.impl;

import java.util.HashMap;
import java.util.Map;

import uk.ac.glasgow.internman.Employer;
import uk.ac.glasgow.internman.Role;
import uk.ac.glasgow.internman.stores.UserStore;
import uk.ac.glasgow.internman.stores.UserStoreImpl;
import uk.ac.glasgow.internman.users.*;

public class UserManagement {

	private UserStoreImpl US;
	
	public UserManagement()
	{
		this.US = new UserStoreImpl("user_store1.txt");
		CCUser ccu = new CCUser("tws", "1234", "Tim", "Storer", "timothy@storer.gla.ac.uk");
		US.addUser(ccu);
	}
	
	public boolean login(String userName, String password) {
		
		if (US.getUser(userName, password) != null)
			return true;
		else
			return false;
	}
	
	public User getUser(String ID) {
		
		return US.getUser(ID);
	}
	
	public Map getUsers(String userType) {
		
		Map<String, User> filteredUsers = new HashMap<String,User>();
		
		for (String ID : US.getUsers().keySet()) {
			
			if (US.getUser(ID).getUserType() == userType)
				filteredUsers.put(ID, US.getUser(ID));
		}
		return filteredUsers;
	}
	
	  Employer registerNewEmployer(String name, String emailAddress) {
		  
		  Employer employer = new EmployerUser(null, null, name, null, null,  emailAddress);
		  US.addUser((User)employer);
		  return employer;
	  }
	
	  
	  public void notifyAcceptedOffer(Role role, String managerName, String managerEmail){
		  System.out.println("Notification email sent to " + managerEmail);
	  }
	
	
}
