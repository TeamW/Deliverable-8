package uk.ac.glasgow.internman.stores;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import uk.ac.glasgow.internman.impl.Advert;
import uk.ac.glasgow.internman.users.User;
import uk.ac.glasgow.internman.impl.Offer;

public class OfferStore {
	
	private Map<Integer,Offer> offers;
	private File storeFile;
	
	/**
	 * Constructs a new store in the specified file location.
	 * @param fileName
	 */
	public OfferStore(String fileName){
		storeFile = new File(fileName);
		if (storeFile.exists()){
			try {
				ObjectInputStream ois = 
					new ObjectInputStream(new FileInputStream(storeFile));
				offers = (Map<Integer,Offer>)(ois.readObject());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		else
			this.offers = new HashMap<Integer,Offer>();
	}
	
	public void addOffer(Offer a) {
		offers.put(a.getID(), a);
		
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(storeFile));
			oos.writeObject(offers);
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Offer getOffer(Integer ID) {
		if (offerExists(ID))
			return offers.get(ID);
		else
			return null;
	}
		
	public void removeOffer(Integer ID){
		this.offers.remove(ID);
	}
	
	
	public boolean offerExists(Integer ID){
		Offer offer = this.offers.get(ID);
		return !(offer == null);
	}
	
	public Map<Integer,Offer> getOffers() {
		return this.offers;
	}

}
