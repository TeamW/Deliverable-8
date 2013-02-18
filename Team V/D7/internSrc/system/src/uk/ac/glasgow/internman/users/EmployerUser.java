package uk.ac.glasgow.internman.users;

import uk.ac.glasgow.internman.Employer;

public class EmployerUser extends User implements Employer {
	
	private static int counter;
	private String name;
	
	public EmployerUser(String ID, String password, String name, String forename,
			String surname, String email) {
		
		super(ID, password, forename, surname, email);
		
		this.name = name;
		this.counter++;
		this.setID(name + "1");
		this.setPassword(Integer.toString(counter*1000));
	}

	public String getUserType(){
		return "employer";
	}
	
	
	public String getName() {
		return this.name;
	}

	public String getEmailAddress() {
		return this.getEmail();
	}

}