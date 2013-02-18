package uk.ac.glasgow.internman.impl;

import java.util.Date;
import java.util.Map;

import javax.management.relation.RoleStatus;

import uk.ac.glasgow.internman.Advertisement;
import uk.ac.glasgow.internman.Employer;
import uk.ac.glasgow.internman.Role;
import uk.ac.glasgow.internman.Advertisement.AdvertisementStatus;

import uk.ac.glasgow.internman.Role;

public class RoleImpl implements Role {
	
	private static int idCount =0;
	
	private Integer ID;
	private String title;
	private Date startDate;
	private Date endDate;
	private Double salary;
	private boolean isApproved;
	private String location;
	
	public RoleImpl(){
		this.idCount++;
		this.ID = idCount;
	}
	
	public Integer getID(){
		return this.ID;
	}
	
	public String getTitle(){
		return this.title;
	}

	public Date getStart(){
		return this.startDate;
	}

	public Date getEnd(){
		return this.endDate;
	}

	public Double getSalary(){
		return this.salary;
	}
	
	public boolean isApproved(){
		return this.isApproved;
	}

	public String getLocation(){
		return this.location;
	}

}
