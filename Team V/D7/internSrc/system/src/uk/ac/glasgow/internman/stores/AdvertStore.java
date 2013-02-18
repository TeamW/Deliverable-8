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

/** A simple file based implementation of a store for adverts. */
public class AdvertStore {

	private Map<Integer,Advert> adverts;
	private File storeFile;
	
	/**
	 * Constructs a new store in the specified file location.
	 * @param fileName
	 */
	public AdvertStore(String fileName){
		storeFile = new File(fileName);
		if (storeFile.exists()){
			try {
				ObjectInputStream ois = 
					new ObjectInputStream(new FileInputStream(storeFile));
				adverts = (Map<Integer,Advert>)(ois.readObject());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		else
			this.adverts = new HashMap<Integer,Advert>();
	}
	
	public void addAdvert(Advert a) {
		adverts.put(a.getID(), a);
		
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(storeFile));
			oos.writeObject(adverts);
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Advert getAdvert(Integer ID) {
		if (advertExists(ID))
			return adverts.get(ID);
		else
			return null;
	}
		
	public void removeAdvert(Integer ID){
		this.adverts.remove(ID);
	}
	
	
	public boolean advertExists(Integer ID){
		Advert advert = this.adverts.get(ID);
		return !(advert == null);
	}
	
	public Map<Integer,Advert> getAdverts() {
		return this.adverts;
	}
}
