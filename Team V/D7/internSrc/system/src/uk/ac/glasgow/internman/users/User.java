package uk.ac.glasgow.internman.users;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class User implements Serializable {

	private String ID;
	private String password;
	private String forename;
	private String surname;
	private String email;
		
	public User (String ID, String password, String forename, String surname, String email){
		this.ID = ID;
		this.password = password;
		this.forename = forename;
		this.surname = surname;
		this.email = email;
	}
	
	public String getUserType(){
		return null;
	}
	
	public String getID() {
		return ID;
	}

	public String getPassword() {
		return password;
	}

	public String getForename() {
		return forename;
	}

	public String getSurname() {
		return surname;
	}

	public String getEmail() {
		return email;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setForename(String forename) {
		this.forename = forename;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean authenticate (String password){
		return this.password.equals(password);
	}
}