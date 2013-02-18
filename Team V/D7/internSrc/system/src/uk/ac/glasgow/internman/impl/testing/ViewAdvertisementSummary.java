package uk.ac.glasgow.internman.impl.testing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import uk.ac.glasgow.internman.Advertisement;
import uk.ac.glasgow.internman.Employer;
import uk.ac.glasgow.internman.InternMan;
import uk.ac.glasgow.internman.impl.Advert;
import uk.ac.glasgow.internman.impl.InternManStub;
import uk.ac.glasgow.internman.users.EmployerUser;

public class ViewAdvertisementSummary {

	InternMan s;
	Employer e = new EmployerUser("empl", "1234", "Employer", "forename",
			"surname", "Employer@employer.com");
	Advertisement a = new Advert("APPLY FOR ME", e);

	@Before
	public void setUp() throws Exception {
		s = new InternManStub();
	}

	@Test
	public void ViewApprovedAdAsStudent() {
		s.login("tws", "1234");
		s.publishAdvertisement(1, "bloody good, ol' chap");
		s.login("100000", "letmein");
		a = s.selectAdvertisement(1);
		assertEquals(a.getApplicationDetails(), "APPLY FOR ME");
	}
	
	@Test
	public void ViewApprovedAdAsCC(){
		s.login("tws","1234");
		s.publishAdvertisement(1,"bloody good, ol' chap");
		a = s.selectAdvertisement(1);
		assertEquals(a.getApplicationDetails(),"APPLY FOR ME");
	}
	
	@Test
	public void ViewApprovedAdAsOwnerCompany(){
		s.login("CC", "letmein");
		s.publishAdvertisement(1,"bloody good, ol' chap");
		s.login("Company","letmein");
		a = s.selectAdvertisement(1);
		assertEquals(a.getApplicationDetails(),"APPLY FOR ME");
	}
	
	@Test
	public void ViewApprovedAdAsOtherCompany(){
		s.login("CC", "letmein");
		s.publishAdvertisement(1, "bloody good, ol' chap");
		e = s.registerNewEmployer("OtherCompany", "Other@Company.com");
		assertNotNull(e);
		s.login("OtherCompany","letmein");
		a = s.selectAdvertisement(1);
		assertEquals(a,null);
	}
	
	@Test
	public void ViewPendingAdAsCC(){
		s.login("CC", "letmein");
		a = s.selectAdvertisement(1);
		assertEquals(a.getApplicationDetails(),"APPLY FOR ME");
	}
	
	@Test
	public void ViewPendingAdAsStudent(){
		s.login("1002253w","letmein");
		a = s.selectAdvertisement(1);
		assertEquals(a,null);
	}
	
	@Test
	public void ViewPendingAdAsOwnerCompany(){
		s.login("Company", "letmein");
		a = s.selectAdvertisement(1);
		assertEquals(a.getApplicationDetails(),"APPLY FOR ME");
	}
	
	@Test
	public void ViewPendingAsAsOtherCompany(){
		s.login("CC", "letmein");
		e = s.registerNewEmployer("Other", "Other@Company.com");
		s.login("Other","letmein");
		a = s.selectAdvertisement(1);
		assertEquals(a,null);
	}

}
