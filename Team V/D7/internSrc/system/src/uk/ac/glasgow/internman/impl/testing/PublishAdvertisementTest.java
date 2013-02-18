package uk.ac.glasgow.internman.impl.testing;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import uk.ac.glasgow.internman.Advertisement;
import uk.ac.glasgow.internman.Employer;
import uk.ac.glasgow.internman.InternMan;
import uk.ac.glasgow.internman.impl.Advert;
import uk.ac.glasgow.internman.impl.InternManStub;
import uk.ac.glasgow.internman.users.EmployerUser;

public class PublishAdvertisementTest {

	/*
	 * ISSUE - facade asks for an integer key to retrieve an advert from the
	 * database. There doesn't seem to be any way to get this key for any
	 * specific advert, so publishing an advert isn't possible in the current
	 * version of the system.
	 */

	InternMan s;
	Employer e = new EmployerUser("empl", "1234", "employer", "forename", "surname", "example@example.com");
	Advertisement ad = new Advert("details", e);

	@Before
	public void setUp() throws Exception {
		s = new InternManStub();
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Submit a valid advert as a Course Coordinator.
	 */
	@Test
	public void publishValidAdAsCCViewStudent() {
		s.login("empl", "1234");
		s.createNewAdvertisement("This is a test advertisement");
		s.login("tws", "1234");
		s.publishAdvertisement(1, "Comment");
		s.login("1000000", "1234");
		ad = s.selectAdvertisement(1);
		assertNotNull(ad);
	}

	@Test
	public void publishValidAdAsCCViewCC() {
		s.login("empl", "1234");
		s.createNewAdvertisement("This is a test advertisement");
		s.login("tws", "1234");
		s.publishAdvertisement(1, "Comment");
		ad = s.selectAdvertisement(1);
		assertNotNull(ad);
	}
	
	@Test
	public void publishValidAdAsCCViewOwnerEmployer() {
		s.login("empl", "1234");
		s.createNewAdvertisement("This is a test advertisement");
		s.login("tws", "1234");
		s.publishAdvertisement(1, "Comment");
		s.login("empl", "1234");
		ad = s.selectAdvertisement(1);
		assertNotNull(ad);
	}
	
	@Test
	public void publishInvalidAsCCViewStudent(){
		s.login("tws","1234");
		s.publishAdvertisement(0, "Comment");
		s.login("1000000", "1234");
		ad = s.selectAdvertisement(1);
		assertNull(ad);
	}
	
	@Test
	public void publishInvalidAsCCViewCC(){
		s.login("tws","1234");
		s.publishAdvertisement(0, "Comment");
		ad = s.selectAdvertisement(1);
		assertNull(ad);
	}
	
	@Test
	public void publishInvalidAsCCViewEmployer(){
		s.login("tws","1234");
		s.publishAdvertisement(0, "Comment");
		s.login("empl", "1234");
		ad = s.selectAdvertisement(1);
		assertNull(ad);
	}

}
