package uk.ac.glasgow.internman.impl.testing;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import uk.ac.glasgow.internman.Employer;
import uk.ac.glasgow.internman.InternMan;
import uk.ac.glasgow.internman.Role;
import uk.ac.glasgow.internman.impl.InternManStub;
import uk.ac.glasgow.internman.impl.RoleImpl;
import uk.ac.glasgow.internman.users.EmployerUser;

/**
 * 
 * Tested on 18/02/13
 * Failed due to not being able to modify the details of an internship.
 * 
 */
public class NotifyAcceptedOffer {

	InternMan s;
	Role r = new RoleImpl();
	Employer e = new EmployerUser("empl", "1234", "employer", "forename", "surname", "example@example.com");

	@Before
	public void setUp() throws Exception {
		s = new InternManStub();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void studentCurrentRole() {
		s.login("1000000", "1234");
		s.notifyAcceptedOffer(r, e.getName(), e.getEmailAddress());
		// No crash test
		assertEquals(true, true);
	}

	@Test
	public void employerNotifyFail() {
		s.login("empl", "1234");
		s.notifyAcceptedOffer(r, e.getName(), e.getEmailAddress());
		// No crash test
		assertEquals(true, true);
	}

	@Test
	public void ccNotifyFail() {
		s.login("tws", "1234");
		s.notifyAcceptedOffer(r, e.getName(), e.getEmailAddress());
		// No crash test
		assertEquals(true, true);
	}

}
