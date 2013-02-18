package uk.ac.glasgow.internman.impl;

import java.util.HashMap;
import java.util.Map;

import uk.ac.glasgow.internman.Internship.InternshipStatus;
import uk.ac.glasgow.internman.Employer;
import uk.ac.glasgow.internman.Role;
import uk.ac.glasgow.internman.stores.AdvertStore;
import uk.ac.glasgow.internman.stores.UserStore;
import uk.ac.glasgow.internman.stores.UserStoreImpl;
import uk.ac.glasgow.internman.users.EmployerUser;
import uk.ac.glasgow.internman.users.User;
import uk.ac.glasgow.internman.stores.OfferStore;

public class OfferManagement {
	
	private OfferStore OS;
	
	public OfferManagement(){
		this.OS = new OfferStore("offer_store.txt");
	}
	
	public Offer getOffer(Integer ID){
		return OS.getOffer(ID);
	}
	
	public void acceptOffer(Integer ID){
		OS.getOffer(ID).setStatus(InternshipStatus.ACCEPTED);
	}
	
	public void approveAcceptedOffer(Integer ID){
		OS.getOffer(ID).setStatus(InternshipStatus.APPROVED);
	}
	
	
}
