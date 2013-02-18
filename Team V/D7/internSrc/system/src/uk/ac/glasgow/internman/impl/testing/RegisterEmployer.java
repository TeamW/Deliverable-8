package uk.ac.glasgow.internman.impl.testing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import uk.ac.glasgow.internman.Employer;
import uk.ac.glasgow.internman.InternMan;
import uk.ac.glasgow.internman.impl.InternManStub;
import uk.ac.glasgow.internman.users.EmployerUser;

public class RegisterEmployer {

	InternMan s;
	Employer e = new EmployerUser("empl", "1234", "Employer", "forename", "surname", "Employer@employer.com");

	@Before
	public void setUp() throws Exception {
		s = new InternManStub();
	}

	@Test
	public void AddEmployerAsCC() {
		s.login("tws", "1234");
		e = s.registerNewEmployer("Employer", "Employer@employer.com");
		assertNotNull(e);
	}
	
	@Test
	public void AddEmployerAsStudent(){
		s.login("1000000", "1234");
		e = s.registerNewEmployer("Employer", "Employer@employer.com");
		assertNull(e);
	}
	
	@Test
	public void AddEmployerAsEmployer(){
		s.login("empl", "1234");
		e = s.registerNewEmployer("Employer", "Employer@employer.com");
		assertNull(e);
	}

}
