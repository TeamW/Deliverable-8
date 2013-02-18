package uk.ac.glasgow.internman.impl.testing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import uk.ac.glasgow.internman.InternMan;
import uk.ac.glasgow.internman.Student;
import uk.ac.glasgow.internman.impl.InternManStub;

public class ViewStudentDetail {

	InternMan s;

	@Before
	public void setUp() throws Exception {
		s = new InternManStub();
	}

	/**
	 * Checks that the Course Coordinator can view an existing students details.
	 */
	/*
	 * Waste of time since we cannot set our own student info
	 */
	@Test
	public void ViewStudentDetailAsCC() {
		s.login("TestCC", "letmein");
		Student stud = s.selectStudent("100000");
		assertEquals(stud.getEmail() + stud.getForename() + stud.getSurname()
				+ stud.getMatriculation(),
				"example@example.comstu100000");
	}

	/**
	 * Test for checking that a Course Coordinator cannont view a student that
	 * does not exist
	 */
	@Test
	public void ViewNonExistentStudentDetailAsCC() {
		s.login("tws", "1234");
		Student stud = s.selectStudent("100000a");
		assertEquals(stud, null);
	}

	/**
	 * Checks that a company cannot view a student.
	 */
	@Test
	public void ViewStudentDetailAsEmployer() {
		s.login("empl", "1234");
		Student stud = s.selectStudent("100000");
		assertEquals(stud, null);
	}

	/**
	 * Checks that a student cannot view a different student
	 */
	@Test
	public void ViewStudentDetailAsStudent() {
		s.login("100000", "1234");
		Student stud = s.selectStudent("100000");
		assertEquals(stud, null);
	}
}
