package uk.ac.glasgow.internman.impl.testing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import uk.ac.glasgow.internman.*;
import uk.ac.glasgow.internman.Internship.InternshipStatus;
import uk.ac.glasgow.internman.impl.*;

/**
 * Test revised on 18/2/13 by Ryan, Gordon and David.
 * Tests cannot be run as there is no way to assign an internship.
 * 
 * @author 1002536r
 *
 */
public class ApproveAcceptedOffer {
	InternMan s;

	@Before
	public void setUp() throws Exception {
		s = new InternManStub();
	}

	/**
	 * User: TestCC with password: letmein is logged in. TestCC then approves an
	 * offer that student 1002536r has accepted.
	 */
	@Test
	public void approveValidInternship() {
		s.login("tws", "1234");
		Student temp = s.selectStudent("100000");
		s.approveAcceptedOffer("100000");
		InternshipStatus status = temp.getInternship().getStatus();
		assertEquals(status, InternshipStatus.APPROVED);
	}

	/**
	 * A student is logged in and tries to approve their own internship.
	 */
	@Test
	public void studentTriesToAcceptOwnInternship() {
		s.login("100000", "1234");
		s.approveAcceptedOffer("100000");
		s.login("tws", "1234");
		Student temp = s.selectStudent("100000");
		InternshipStatus status = temp.getInternship().getStatus();
		assertEquals(status, InternshipStatus.ACCEPTED);
	}

	/**
	 * The course coordinator tries to approve an internship of a student who
	 * does not exist.
	 */
	@Test
	public void approveInternshipOfInvalidStudent() {
		s.login("tws", "1234");
		s.approveAcceptedOffer("IDoNotExist");
		// No crash test
		assertEquals(true, true);
	}
}
