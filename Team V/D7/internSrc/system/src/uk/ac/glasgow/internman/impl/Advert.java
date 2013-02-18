package uk.ac.glasgow.internman.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import uk.ac.glasgow.internman.Advertisement;
import uk.ac.glasgow.internman.Employer;
import uk.ac.glasgow.internman.Role;
import uk.ac.glasgow.internman.Advertisement.AdvertisementStatus;

public class Advert implements Advertisement, Serializable {
	
	private static int IDCount = 0;
	
	private int ID;
	private Map<Integer, Role> roles = null;
	private Employer employer = null;
	private AdvertisementStatus status = AdvertisementStatus.PENDING;
	private String details = null;
	private String comments = null;
	
	public Advert (String details, Employer emp){
		this.details = details;
		this.employer = emp;
		this.IDCount++;
		this.ID = this.IDCount;		
	}
	
	public Integer getID() {
		return this.ID;
	}
	
	public Map<Integer, Role> getRoles(){
		return this.roles;
	}

	public Employer getEmployer(){
		return this.employer;
	}

	public String getApplicationDetails(){
		return this.details;
	}

	public String getComments(){
		return this.comments;
	}

	public AdvertisementStatus getStatus(){
		return this.status;
	}
	
	public void setStatus(AdvertisementStatus status){
		this.status = status;
	}
	
	public void setComment(String comment){
		this.comments = comment;
	}

	public Role addNewRole(String title, String location, Date start, Date end, String description, Double salary) {
		return null;
	}
}
