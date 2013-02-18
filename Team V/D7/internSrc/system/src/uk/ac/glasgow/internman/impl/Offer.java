package uk.ac.glasgow.internman.impl;

import uk.ac.glasgow.internman.Employer;
import uk.ac.glasgow.internman.Internship;
import uk.ac.glasgow.internman.Role;
import uk.ac.glasgow.internman.Visit;
import uk.ac.glasgow.internman.Internship.InternshipStatus;

public class Offer implements Internship {
	
	private static int IDCount = 0;
	
	private int ID;
	private String manager;
	private String managerEmail;
	private Employer employer;
	private InternshipStatus status = InternshipStatus.APPLYING;
	private Role role;
	private Visit visit;
	
	public Offer(){
		this.IDCount++;
		this.ID = this.IDCount;
	}
	
	public Integer getID(){
		return this.ID;
	}
	
	public String getManager(){
		return this.manager;
	}

	public String getManagerEmail(){
		return this.managerEmail;
	}

	public boolean isVisitComplete(){
		return true;
	}

	public Employer getEmployer(){
		return this.employer;
	}

	public Role getRole(){
		return this.role;
	}

	public InternshipStatus getStatus(){
		return this.status;
	}
	
	public void setStatus(InternshipStatus status){
		this.status = status;
	}

	public Visit getVisit(){
		return null;
	}

}
