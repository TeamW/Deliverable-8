package uk.ac.glasgow.internman.impl;

import java.util.HashMap;
import java.util.Map;

import uk.ac.glasgow.internman.Advertisement;
import uk.ac.glasgow.internman.Advertisement.AdvertisementStatus;
import uk.ac.glasgow.internman.Employer;
import uk.ac.glasgow.internman.Role;
import uk.ac.glasgow.internman.stores.AdvertStore;
import uk.ac.glasgow.internman.stores.UserStore;
import uk.ac.glasgow.internman.stores.UserStoreImpl;
import uk.ac.glasgow.internman.users.EmployerUser;
import uk.ac.glasgow.internman.users.User;

public class AdvertManagement {
	
	private AdvertStore AS;
	
	public AdvertManagement(){
		this.AS = new AdvertStore("advert_store.txt");
	}
	
	public Advert getAdvert(Integer ID){
		return AS.getAdvert(ID);
	}
	
	public Map getAdverts(Map<String, String> filter){
		
		String userType = filter.get("userType");
		String employerName = filter.get("employerName");
		
		Map<Integer, Advert> filteredAdverts = new HashMap<Integer,Advert>();
		
		for (Integer ID : AS.getAdverts().keySet()){
			
			if (userType == "student" && AS.getAdvert(ID).getStatus() == AdvertisementStatus.PUBLISHED){
				filteredAdverts.put(ID,AS.getAdvert(ID));
			}
			if (userType == "coordinator"){
				filteredAdverts.put(ID, AS.getAdvert(ID));
			}
			if (userType == "employer" && AS.getAdvert(ID).getEmployer().getName() == employerName){
				filteredAdverts.put(ID, AS.getAdvert(ID));
			}				
		}		
		return filteredAdverts;
	}
	
	public Advertisement createAdvert(String appDetails, Employer emp){
		
		Advert advert = new Advert(appDetails, emp);
		this.storeAdvert(advert);
		return advert;
	}
	
	public void removeAdvert(Integer ID){
		AS.removeAdvert(ID);
	}
	
	public void storeAdvert(Advert a){
		AS.addAdvert(a);
	}
	
	public void publishAdvert(Integer ID, String comment){
		Advert advert = AS.getAdvert(ID);
		advert.setStatus(AdvertisementStatus.PUBLISHED);
		advert.setComment(comment);
		AS.addAdvert(advert);
	}
	
	public Role selectRole(Integer ID, Integer roleIndex){
		return null;
	}	

}
