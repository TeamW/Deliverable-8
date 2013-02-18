package uk.ac.glasgow.internman.impl;

import static org.junit.Assert.*;

import java.util.*;
import java.util.Map.Entry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import uk.ac.glasgow.internman.Advertisement;
import uk.ac.glasgow.internman.Advertisement.AdvertisementStatus;
import uk.ac.glasgow.internman.Employer;
import uk.ac.glasgow.internman.Role;
import uk.ac.glasgow.internman.Student;
import uk.ac.glasgow.internman.UoGGrade;
import uk.ac.glasgow.internman.users.CCUser;
import uk.ac.glasgow.internman.users.EmployerUser;
import uk.ac.glasgow.internman.users.User;

/*In this set of tests it is assume that there exists:
 * 1) A user with Course Coordinator priviledges with
 * 			username: tws
 * 			password: 1234
 * 2) A user with student priviledges with
 * 			username: stu
 * 			password: 1234
 * 3) A user with employer priviledges with
 * 			username: empl
 * 			password: 1234
 * 4) At least one advert from each category:
 * 			Pending
 * 			Published
 * 
 * If the above pre-requisites are not possible 
 * the testing team should be informed as soon as possible
 * so that the testing scenarios can be changed.
 */

public class InternManStubTest {
	private String ccUsername;
	private String ccPassword;
	private String studentUsername;
	private String studentPassword;
	private String emplUsername;
	private String emplPassword;
	private String ID;
	private String forename;
	private String surname;
	private String email;
	private String ccuser;
	private String ccpassword;
	InternManStub interfc;

	@Before
	public void setUp() throws Exception {
		ccUsername = "tws";
		ccPassword = "1234";
		studentUsername = "100000";
		studentPassword = "1234";
		emplUsername = "empl";
		emplPassword = "1234";
		email = "test@test.com";
		forename = "Daniel";
		surname = "Gonzales";
		interfc = new InternManStub();
	}

	@After
	public void tearDown() throws Exception {
		ccUsername = null;
		ccPassword = null;
		studentUsername = null;
		studentPassword = null;
		emplUsername = null;
		emplPassword = null;
		interfc = null;
		email = null;
		forename = null;
		surname = null;
		ID = null;
		ccuser = null;
		ccpassword = null;
	}

	@Test
	public void loginTest() {
		assertTrue(interfc.login(ccUsername, ccPassword));
	}

	@Test
	public void getCurrentUserTest() {
		interfc.login(ccUsername, ccPassword);
		assertTrue(interfc.getCurrentUser().getID() == ccUsername);
	}

	/*
	 * Testing 'getAdvertisements' method for course coordinator All adverts
	 * should be visible including unpublished ones.
	 */
	@Test
	public void getAdvertisementsForCCTest() {
		interfc.login(ccUsername, ccPassword);
		Map<Integer, Advertisement> adds = interfc.getAdvertisements();
		boolean result = false;
		for (Entry<Integer, Advertisement> entry : adds.entrySet()) {
			if (entry.getValue().getStatus()
					.equals(AdvertisementStatus.PENDING))
				result = true;// the list returned contains at least one pending
								// advert
		}
		assertTrue(result);
	}

	/*
	 * Testing 'getAdvertisements' method for Student. Only published adverts
	 * should be visible.
	 */
	@Test
	public void getAdvertisementsForStudentTest() {
		interfc.login(studentUsername, studentPassword);
		Map<Integer, Advertisement> adds = interfc.getAdvertisements();
		boolean result = true;
		for (Entry<Integer, Advertisement> entry : adds.entrySet()) {
			if (entry.getValue().getStatus()
					.equals(AdvertisementStatus.PENDING))
				result = false;// the list returned contains at least one
								// pending advert
		}
		assertTrue(result);
	}

	/*
	 * Testing 'getAdvertisements' method for Employer Only adverts the employer
	 * has posted should be visible.
	 */
	@Test
	public void getAdvertisementsForEmployerTest() {
		interfc.login(emplUsername, emplPassword);
		Map<Integer, Advertisement> adds = interfc.getAdvertisements();
		boolean result = true;
		for (Entry<Integer, Advertisement> entry : adds.entrySet()) {
			if (entry.getValue().getEmployer().getName().equals(emplUsername)) {
			} else {// the list returned contains an advert from another
					// employer
				result = false;
			}
		}
		assertTrue(result);
	}

	/*
	 * According to the facade's API getStudents() 
	 * should return a map of students who are eligible to 
	 * apply for an internship. The key of each entry should be the GUID 
	 * of each student.
	 */
	@Test
	public void getStudentsTest() {
		interfc.login(ccUsername, ccPassword);
		Map<String, Student> students = interfc.getStudents();
		boolean result = true;
		for (Entry<String, Student> student : students.entrySet()) {
			if (student.getKey().equals(student.getValue().getMatriculation())) {
			} else {//the key for the current student is not equal to its GUID
				result = false;
			}
		}
		assertTrue(result);
	}

	@Test
	public void RegisterNewEmployerTest() {
		interfc.login(ccUsername, ccPassword);
		Employer p = interfc.registerNewEmployer("testEmployer", "tester@test.com");
		assertTrue(p != null && p.getEmailAddress() == email
				&& p.getName() == "testEmployer");
	}
	
	@Test
	public void getCurrentEmployerTest(){
		interfc.login(ccUsername, ccPassword);
		Employer emp=interfc.getCurrentEmployer();
		assertTrue(emp!=null&&emp.getName().equals("ccUsername"));
	}

	@Test
	public void createNewAdvertisementTest() {
		interfc.login(ccUsername, ccPassword);
		Advertisement ad = interfc.createNewAdvertisement("Testing Data");
		assertTrue(ad.getApplicationDetails().equals("Testing Data"));
	}

	@Test
	public void selectAdvertisementTest() {
		interfc.login(ccUsername, ccPassword);
		assertTrue(interfc.selectAdvertisement(1) instanceof Advertisement);
	}

	@Test
	public void selectRoleTest() {
		interfc.login(ccUsername, ccPassword);
		assertTrue(interfc.selectRole(1, 1) instanceof Role);
	}

	@Test
	public void selectStudentTest() {
		interfc.login(ccUsername, ccPassword);
		Student student=interfc.selectStudent(studentUsername);
		assertEquals(student.getMatriculation(), studentUsername);
	}


	@Test
	public void createNewSelfSourcedRoleTest() {
		String title = "Test Engineer";
		String location = "Glasgow";
		Calendar cal = Calendar.getInstance();
		cal.set(2013, 6, 15);
		Date start = cal.getTime();
		cal.set(2013, 9, 15);
		Date end = cal.getTime();
		String description = "Test description";
		Double salary = 100.00;
		Role test = interfc.createNewSelfSourcedRole(title, location, start,
				end, description, salary);
		assertTrue(test.getTitle().equals(title)&&test.getSalary().equals(salary));
	}

}
