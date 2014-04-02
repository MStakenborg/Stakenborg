package ca.uwo.csd.cs2212.team9;

import static org.junit.Assert.*;

import junit.framework.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/*ToDo: Finish creation tests
 * 		Write edit tests
 */
@SuppressWarnings("unused")
public class StudentTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	/* Student creation tests */
	@Test
	public void testCreationEmptyFirstName() throws InvalidInfoException {
		/*
		 * Attempts to create a student with an empty first name Passes if an
		 * InvalidInfoException is thrown
		 */
		thrown.expect(InvalidInfoException.class);
		thrown.expectMessage("Invalid first name. ");
		Student student = new Student("", "Fisher", "123456789",
				"sfisher@uwo.ca");
	}

	@Test
	public void testCreationInvalidFirstName() throws InvalidInfoException {
		/*
		 * Tries to create a student with a first name containing a
		 * non-alphabetical character. Passes if an InvalidInfoException is
		 * thrown.
		 */
		thrown.expect(InvalidInfoException.class);
		thrown.expectMessage("Invalid first name. ");
		Student student = new Student("1Sa2m3", "Fisher", "123456789",
				"sfisher@uwo.ca");
	}

	@Test
	public void testCreationEmptyLastName() throws InvalidInfoException {
		/*
		 * Attempts to create a student with an empty last name. Passes if an
		 * InvalidInfoException is thrown
		 */
		thrown.expect(InvalidInfoException.class);
		thrown.expectMessage("Invalid last name. ");
		Student student = new Student("Sam", "", "123456789", "sfisher@uwo.ca");
	}

	@Test
	public void testCreationInvalidLastName() throws InvalidInfoException {
		/*
		 * Tries to create a student with a last name containing a
		 * non-alphabetical character. Passes if an InvalidInfoException is
		 * thrown
		 */
		thrown.expect(InvalidInfoException.class);
		thrown.expectMessage("Invalid last name. ");
		Student student = new Student("Sam", "1Fis2her3", "123456789",
				"sfisher@uwo.ca");
	}

	@Test
	public void testCreationEmptyStudentNumber() throws InvalidInfoException {
		/*
		 * Attempts to create a student with an empty student number. Passes if
		 * an InvalidInfoException is thrown
		 */
		thrown.expect(InvalidInfoException.class);
		thrown.expectMessage("Invalid student number. ");
		Student student = new Student("Sam", "Fisher", "", "sfisher@uwo.ca");
	}

	@Test
	public void testCreationInvalidStudentNumber() throws InvalidInfoException {
		/*
		 * Attempts to create a student with an invalid student number (contains
		 * non-integers). Passes if an InvalidInfoException is thrown.
		 */
		thrown.expect(InvalidInfoException.class);
		thrown.expectMessage("Invalid student number. ");
		Student student = new Student("Sam", "Fisher", "13forty32",
				"sfisher@uwo.ca");
	}

	@Test
	public void testCreationLongStudentNumber() throws InvalidInfoException{
		thrown.expect(InvalidInfoException.class);
		thrown.expectMessage("Invalid student number. ");
		Student student = new Student("Sam", "Fisher","1234567890", "sfisher@uwo.ca");
	}
	
	@Test
	public void testCreationEmptyEmail() throws InvalidInfoException {
		/*
		 * Attempts to create a student with an empty email address Passes if an
		 * InvalidInfoException is thrown.
		 */
		thrown.expect(InvalidInfoException.class);
		thrown.expectMessage("Invalid email address. ");
		Student student = new Student("Sam", "Fisher", "123456789", "");
	}

	@Test
	public void testCreationInvalidEmail() throws InvalidInfoException {
		/*
		 * Attempts to create a student with an invalid email address (valid
		 * format: ___@___.___) Passes if InvalidInfoException is thrown.
		 */
		thrown.expect(InvalidInfoException.class);
		thrown.expectMessage("Invalid email address. ");
		Student student = new Student("Sam", "Fisher", "123456789",
				"AnEmailAddress");
	}

	/* Student edit tests */
	@Test
	public void testEditEmptyFirstName() throws InvalidInfoException {
		/*
		 * Attempts to enter a blank first name. Passes if an
		 * InvalidInfoException is thrown
		 */
		thrown.expect(InvalidInfoException.class);
		thrown.expectMessage("The student must have a valid first name.");
		Student student = new Student("Sam", "Fisher", "123456789",
				"sfisher@uwo.ca");
		student.setFirstName("");
	}

	@Test
	public void testEditInvalidFirstName() throws InvalidInfoException {
		/*
		 * Tries to alter a student with a first name containing a
		 * non-alphabetical character. Passes if InvalidInfoException is thrown
		 */
		thrown.expect(InvalidInfoException.class);
		thrown.expectMessage("The student must have a valid first name.");
		Student student = new Student("Sam", "Fisher", "123456789",
				"sfisher@uwo.ca");
		student.setFirstName("1S2am3");
	}

	@Test
	public void testEditEmptyLastName() throws InvalidInfoException {
		/*
		 * Attempts to enter a blank last name Passes if an InvalidInfoException
		 * is thrown
		 */
		thrown.expect(InvalidInfoException.class);
		thrown.expectMessage("The student must have a valid last name.");
		Student student = new Student("Sam", "Fisher", "123456789",
				"sfisher@uwo.ca");
		student.setLastName("");
	}

	@Test
	public void testEditInvalidLastName() throws InvalidInfoException {
		/*
		 * Tries to create a student with a last name containing a
		 * non-alphabetical character. Passes if InvalidInfoException is thrown
		 */
		thrown.expect(InvalidInfoException.class);
		thrown.expectMessage("The student must have a valid last name.");
		Student student = new Student("Sam", "Fisher", "123456789",
				"sfisher@uwo.ca");
		student.setLastName("1Fi2sher3");
	}

	@Test
	public void testEditEmptyStudentNumber() throws InvalidInfoException {
		/*
		 * Attempts to enter a blank student number. Passes if an
		 * InvalidInfoException is thrown
		 */
		thrown.expect(InvalidInfoException.class);
		thrown.expectMessage("The student must have a valid student number.");
		Student student = new Student("Sam", "Fisher", "123456789",
				"sfisher@uwo.ca");
		student.setNumber("");
	}

	@Test
	public void testEditInvalidStudentNumber() throws InvalidInfoException {
		/*
		 * Attempts to create a student with an invalid student number (contains
		 * non-integers) Passes if an InvalidInfoException is thrown.
		 */
		thrown.expect(InvalidInfoException.class);
		thrown.expectMessage("The student must have a valid student number.");
		Student student = new Student("Sam", "Fisher", "123456789",
				"sfisher@uwo.ca");
		student.setNumber("forty4four");
	}

	@Test
	public void testEditEmptyEmail() throws InvalidInfoException {
		/*
		 * Attempts to enter a blank email address. Passes if an
		 * InvalidInfoException is thrown
		 */
		thrown.expect(InvalidInfoException.class);
		thrown.expectMessage("The student must have a valid email address.");
		Student student = new Student("Sam", "Fisher", "123456789",
				"sfisher@uwo.ca");
		student.setEmail("");
	}

	@Test
	public void testEditInvalidEmail() throws InvalidInfoException {
		/*
		 * Attempts to create a student with an invalid email address (valid
		 * format: ___@___.___) Passes if InvalidInfoException is thrown
		 */
		thrown.expect(InvalidInfoException.class);
		thrown.expectMessage("The student must have a valid email address.");
		Student student = new Student("Sam", "Fisher", "123456789",
				"sfisher@uwo.ca");
		student.setEmail("emailaddress.com");
	}
	
	/* Add Deliverables*/ 
	
	@Test
	public void testAddValidDeliverable() throws InvalidInfoException {
		/* Passes if a valid deliverable is successfully added to the course */
		Deliverable del = new Deliverable("asn1", "Assignment", Double.toString(50.0));
		Student student = new Student("Sam", "Fisher", "123456789",
				"sfisher@uwo.ca");
		student.addDeliverable(del);
		
		
		
		assertTrue(student.getDeliverables().contains(del));
	}
	
	@Test
	public void testAddDuplicateDeliverable() throws InvalidInfoException {
		/*
		 * Tries to add duplicate deliverables. Passes if an
		 * InvalidInfoException is thrown.
		 */
		thrown.expect(InvalidInfoException.class);
		thrown.expectMessage("Duplicate deliverable name.");
		Deliverable del = new Deliverable("asn1", "Assignment", Double.toString(50.0));
		Student student = new Student("Sam", "Fisher", "123456789",
				"sfisher@uwo.ca");		
		student.addDeliverable(del);
		student.addDeliverable(del);
	}
	
	
	/*Remove Deliverables*/
	
	@Test
	public void testDeleteValidDeliverable() throws InvalidInfoException, ElementNotFoundException{
		Student student = new Student("Sam", "Fisher", "123456789",
				"sfisher@uwo.ca");			
		student.addDeliverable(new Deliverable("asn1", "Assignment", "50"));
		student.delDeliverable("asn1");
		assertFalse(student.containsDeliverable("asn1"));
	}
	
	@Test
	public void testDeleteNonExistantDeliverable() throws InvalidInfoException, ElementNotFoundException{
		thrown.expect(ElementNotFoundException.class);
		thrown.expectMessage("Could not remove deliverable - name not found.");

		Student student = new Student("Sam", "Fisher", "123456789",
				"sfisher@uwo.ca");		
		student.delDeliverable("asn1");
	}
	
	@Test
	public void testRetrieveValidDeliverable() throws InvalidInfoException, ElementNotFoundException{
		Student student = new Student("Sam", "Fisher", "123456789",
				"sfisher@uwo.ca");		
		Deliverable del = new Deliverable("asn1", "Assignment", "50");
		student.addDeliverable(del);
		assertTrue(student.getDeliverable("asn1").equals(del));
	}
	
	@Test
	public void testRetrieveNonExistantDeliverable() throws InvalidInfoException, ElementNotFoundException{
		thrown.expect(ElementNotFoundException.class);
		thrown.expectMessage("Deliverable not found.");
		Student student = new Student("Sam", "Fisher", "123456789",
				"sfisher@uwo.ca");	
		student.getDeliverable("asn1");
	}
	
	
	/*Calculate student averages*/
	@Test 
	public void testCalculateCourseGrades() throws InvalidInfoException{
		/*Tests a student with an average of 70 in each category with different weights. 
		 * Passes if all averages are equal to 70*/
		Student student = new Student("Courser", "McGrade", "250403230", "cmcgrade@uwo.ca");
		Deliverable del1 = new Deliverable("One", "Assignment", "25");
		del1.setGrade(100);
		Deliverable del2 = new Deliverable("Two", "Assignment", "15");
		del2.setGrade(0);
		Deliverable del3 = new Deliverable("Three", "Assignment", "10");
		del3.setGrade(100);	
		Deliverable del4 = new Deliverable("Four", "Exam", "35");
		del4.setGrade(100);
		Deliverable del5 = new Deliverable("Five", "Exam", "15");
		del5.setGrade(0);
		
		student.addDeliverable(del1);
		student.addDeliverable(del2);
		student.addDeliverable(del3);
		student.addDeliverable(del4);
		student.addDeliverable(del5);
		student.calcCourseGrades();
		
		assertTrue(student.getCourseAvg() == 70 && student.getAssignAvg() == 70 && student.getExamAvg() == 70);
		
	}
	
	/*Deliverable exists*/
	public void testDeliverableExistsTrue() throws InvalidInfoException{
		Student student = new Student("Ryan", "McAdams", "250123456", "rmcadam@uwo.ca");
		student.addDeliverable(new Deliverable("Homework", "Assignment", "50"));
		assertTrue(student.containsDeliverable("Homework"));
	}

}
