package uk.ac.glasgow.internman.impl.testing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import uk.ac.glasgow.internman.Employer;
import uk.ac.glasgow.internman.InternMan;
import uk.ac.glasgow.internman.impl.Advert;
import uk.ac.glasgow.internman.impl.InternManStub;
import uk.ac.glasgow.internman.users.EmployerUser;

public class SubmitAdvertisementTest {

	InternMan s;
	Employer e = new EmployerUser("empl", "1234", "Employer", "forename",
			"surname", "Employer@employer.com");

	@Before
	public void setUp() throws Exception {
		s = new InternManStub();
	}

	/*
	 * Tests - submit as employer, submit as CC submit ad as student
	 */

	/**
	 * Submit a valid advert as an employer.
	 */
	@Test
	public void submitValidAdAsEmployer() {
		String adString = "This is a test advertisement";
		// login as employer
		s.login("empl", "1234");
		s.createNewAdvertisement(adString);
		Advert testAd = (Advert) s.createNewAdvertisement(adString);
		System.out.println(testAd.getApplicationDetails());
		assertEquals(adString, testAd.getApplicationDetails());
	}

	/**
	 * Submit a valid advert as a Course Coordinator.
	 */
	@Test
	public void submitValidAdAsCC() {
		String adString = "This is a test advertisement";
		// login as CC
		s.login("tws", "1234");
		s.createNewAdvertisement(adString);
		Advert testAd = (Advert) s.createNewAdvertisement(adString);
		System.out.println(testAd.getApplicationDetails());
		assertEquals(adString, testAd.getApplicationDetails());
	}

	/**
	 * Submit a valid advert as a Student.
	 */
	@Test
	public void submitValidAdAsStudent() {
		String adString = "This is a test advertisement";
		// login as student
		s.login("stu", "1234");
		s.createNewAdvertisement(adString);
		Advert testAd = (Advert) s.createNewAdvertisement(adString);
		assertEquals(null, testAd);
	}

	/**
	 * Attempt a submission while not logged in
	 */
	@Test
	public void submitValidAdNotLoggedIn() {
		String adString = "This is a test advertisement";
		s.createNewAdvertisement(adString);
		Advert testAd = (Advert) s.createNewAdvertisement(adString);
		assertEquals(null, testAd);
	}

}
