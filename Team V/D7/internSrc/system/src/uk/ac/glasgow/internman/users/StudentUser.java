package uk.ac.glasgow.internman.users;

import uk.ac.glasgow.internman.Internship;
import uk.ac.glasgow.internman.Student;

public class StudentUser extends User implements Student {
	
	private Internship internship;

	public StudentUser(String ID, String password, String forename,
			String surname, String email) {
		
		super(ID, password, forename, surname, email);
	}

	public String getUserType(){
		return "student";
	}
	
	
	public Internship getInternship() {
		return this.internship;
	}

	public String getSurname() {
		return this.getSurname();
	}

	public String getMatriculation() {
		return this.getID();
	}

	public String getForename() {
		return this.getForename();
	}

	public String getEmail() {
		return this.getEmail();
	}

	public Programme getProgramme() {
		return null;
	}
}