package uk.ac.glasgow.internman.impl.testing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import uk.ac.glasgow.internman.*;
import uk.ac.glasgow.internman.impl.*;

/**
 * 
 * Tested on 18/02/13 Fails due to inability to add users to the system and the
 * provided "in house" login details do not work.
 * 
 * @author 1002536r
 * 
 */
public class LoginTest {

	InternMan s;

	@Before
	public void setUp() throws Exception {
		s = new InternManStub();
	}

	/**
	 ** Test case for Correct student Login - should Return true
	 * */

	@Test
	public void studentLoginCorrect() {
		assertEquals(s.login("100000", "1234"), true);
	}

	/**
	 ** Test case 1 for InCorrect student Login - Password is Incorrect should
	 * Return false
	 * */

	@Test
	public void studentLoginIncorrectPassword() {
		assertEquals(s.login("100000", "notmypassword"), false);
	}

	/**
	 ** Test case 2 for InCorrect student Login Username is incorrect - should
	 * Return false
	 * */

	@Test
	public void studentLoginIncorrectUsername() {
		assertEquals(s.login("000000a", "notmypassword"), false);
	}

	/**
	 ** Test case for Correct employer Login - should Return true
	 * */

	@Test
	public void employerLoginCorrect() {
		assertEquals(s.login("empl", "1234"), true);
	}

	/**
	 ** Test case 1 for InCorrect employer Login - Password is Incorrect should
	 * Return false
	 * */

	@Test
	public void employerLoginIncorrectPassword() {
		assertEquals(s.login("empl", "notmypassword"), false);
	}

	/**
	 ** Test case 2 for InCorrect employer Login - User is Incorrect should
	 * Return false
	 * */

	@Test
	public void employerLoginIncorrectUsername() {
		assertEquals(s.login("notAnEmployer", "notmypassword"), false);
	}

	/**
	 ** Test case for Correct Course Coordinator Login - should Return true
	 * */

	@Test
	public void ccLoginCorrect() {
		assertEquals(s.login("tws", "1234"), true);
	}

	/**
	 ** Test case for 1 InCorrect Course Coordinator Login - password is
	 * incorrect should Return false
	 * */

	@Test
	public void ccLoginIncorrectPassword() {
		assertEquals(s.login("tws", "notmypassword"), false);
	}

	/**
	 ** Test case for 2 InCorrect Course Coordinator Login - Username is
	 * incorrect should Return false
	 * */

	@Test
	public void ccLoginIncorrectUsername() {
		assertEquals(s.login("NotTheCC", "notmypassword"), false);
	}
}
