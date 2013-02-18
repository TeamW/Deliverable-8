package uk.ac.glasgow.internman.users;

public class CCUser extends User {

	public CCUser(String ID, String password, String forename,
			String surname, String email) {
		
		super(ID, password, forename, surname, email);
	}

	public String getUserType(){
		return "coordinator";
	}

}