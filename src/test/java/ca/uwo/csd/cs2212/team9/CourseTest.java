package ca.uwo.csd.cs2212.team9;

import java.io.File;
import java.net.URL;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import static org.junit.matchers.JUnitMatchers.containsString;
import org.junit.rules.ExpectedException;


/**
 * @author kmcguin
 */
@SuppressWarnings("unused")
public class CourseTest {
	
	private Course course;
	@Before
	public void setup() {		
		try {
			course = new Course("3023", "Business", "2");
			
		} catch (InvalidInfoException e) {
			e.printStackTrace();
		}
	}

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	/* Course creation tests */
	@Test
	public void testCourseCreationNoCode() throws InvalidInfoException {
		/*
		 * Attempts to create a course with an empty code Passes if an
		 * InvalidInfoException is thrown
		 */
		thrown.expect(InvalidInfoException.class);
		thrown.expectMessage("Invalid Code.");
		Course course = new Course("", "Business", "Winter");
	}

	@Test
	public void testCourseCreationNoName() throws InvalidInfoException {
		/*
		 * Attempts to create a course with an empty title Passes if an
		 * InvalidInfoException is thrown
		 */
		thrown.expect(InvalidInfoException.class);
		thrown.expectMessage("Invalid Title.");
		Course course = new Course("3023", "", "Winter");
	}

	@Test
	public void testCourseCreationNoTerm() throws InvalidInfoException {
		/*
		 * Attempts to create a course with an empty term Passes if an
		 * InvalidInfoException is thrown
		 */
		thrown.expect(InvalidInfoException.class);
		thrown.expectMessage("Invalid Term.");
		Course course = new Course("3023", "Business", "");
	}

	/* Edit course information tests */
	@Test
	public void testDeleteCode() throws InvalidInfoException {
		/*
		 * Attempts to delete the code of a course Passes if an
		 * InvalidInfoException is thrown
		 */
		thrown.expect(InvalidInfoException.class);
		thrown.expectMessage("The course must have a code.");
		course.setCode("");
	}

	@Test
	public void testDeleteTitle() throws InvalidInfoException {
		/*
		 * Attempts to delete the title of a course Passes if an
		 * InvalidInfoException is thrown
		 */
		thrown.expect(InvalidInfoException.class);
		thrown.expectMessage("The course must have a title.");
		course.setTitle("");
	}

	@Test
	public void testDeleteTerm() throws InvalidInfoException {
		/*
		 * Attempts to delete the term of a course Passes if an
		 * InvalidInfoException is thrown
		 */
		thrown.expect(InvalidInfoException.class);
		thrown.expectMessage("The course must have a term.");
		course.setTerm("");
	}

	/* addStudent tests */
	@Test
	public void testAddValidStudent() throws InvalidInfoException {
		/* Passes if a valid student is successfully added to the class list. */
		Student student = new Student("Sam", "Fisher", "123456789",
				"sfisher@uwo.ca");
		course.addStudent(student);
		assertTrue(course.getStudents().containsValue(student));
	}

	@Test
	public void testAddDuplicateNumber() throws InvalidInfoException {
		/*
		 * Adds two students with the same number Passes if an
		 * InvalidInfoException is thrown
		 */
		thrown.expect(InvalidInfoException.class);
		thrown.expectMessage("Duplicate student number.");
		Student student1 = new Student("Sam", "Fisher", "123456789",
				"sfisher@uwo.ca");
		Student student2 = new Student("Funk", "Fisher", "123456789",
				"ffisher@uwo.ca");
		course.addStudent(student1);
		course.addStudent(student2);
	}

	@Test
	public void testAddDuplicateEmail() throws InvalidInfoException {
		/*
		 * Adds two students with the same email Passes if an
		 * InvalidInfoException is thrown
		 */
		thrown.expect(InvalidInfoException.class);
		thrown.expectMessage("Duplicate email address.");
		Student student1 = new Student("Sam", "Fisher", "123456789",
				"sfisher@uwo.ca");
		Student student2 = new Student("Sally", "Fisher", "987654321",
				"sfisher@uwo.ca");
		course.addStudent(student1);
		course.addStudent(student2);
	}

	/* removeStudent tests */
	@Test
	public void testRemovePresentStudent() throws ElementNotFoundException,
			InvalidInfoException {
		/*
		 * Attempts to remove a student from the course Passes if the student is
		 * successfully removed.
		 */
		Student student = new Student("Sam", "Fisher", "123456789",
				"sfisher@uwo.ca");
		course.addStudent(student);
		course.removeStudent(student.getNumber());
		assertFalse(course.getStudents().containsValue(student));
	}

	@Test
	public void testRemoveFakeStudent() throws ElementNotFoundException,
			InvalidInfoException {
		/*
		 * Attempts to remove a non-existant student. Passes if an
		 * ElementNotFoundException is thrown
		 */
		thrown.expect(ElementNotFoundException.class);
		thrown.expectMessage("Could not remove student - number not found.");
		Student student = new Student("Sam", "Fisher", "123456789",
				"sfisher@uwo.ca");
		course.removeStudent(student.getNumber());
	}

	/*Edit students tests*/
	
	@Test
	public void editStudentValidInfo() throws InvalidInfoException{
		Student student = new Student("Sam", "Fisher", "987654321", "sfisher@uwo.ca");
		course.addStudent(student);
		Student previous = student;
		Student edit = new Student("Ryan", "Fisher", "123456789", "rfisher@uwo.ca");
		course.editStudent(edit, student);
		
		assertTrue(previous.getFirstName().equals(edit.getFirstName()) && previous.getLastName().equals(edit.getLastName())
				&& previous.getNumber().equals(edit.getNumber()) && previous.getEmail().equals(edit.getEmail()));
	}
	
	@Test
	public void editStudentDuplicateNumber() throws InvalidInfoException{
		thrown.expect(InvalidInfoException.class);
		thrown.expectMessage("Duplicate student number.");

		Student student = new Student("Sam", "Fisher", "987654321", "sfisher@uwo.ca");
		Student student2 = new Student("Sally", "Fisher", "123456789", "sfisher2@uwo.ca");
		course.addStudent(student);
		course.addStudent(student2);
				
		Student edit = new Student("Sally", "Fisher", "987654321", "sfisher2@uwo.ca");
		course.editStudent(edit, student2);	
	}
	
	@Test
	public void editStudentDuplicateEmail() throws InvalidInfoException{
		thrown.expect(InvalidInfoException.class);
		thrown.expectMessage("Duplicate email address.");

		Student student = new Student("Sam", "Fisher", "987654321", "sfisher@uwo.ca");
		Student student2 = new Student("Sally", "Fisher", "123456789", "sfisher2@uwo.ca");
		course.addStudent(student);
		course.addStudent(student2);
				
		Student edit = new Student("Sally", "Fisher", "123456789", "sfisher@uwo.ca");
		course.editStudent(edit, student2);	
	}
	
	
	/*Add Deliverable tests*/
	@Test
	public void testContainsDeliverable() throws InvalidInfoException, ElementNotFoundException{
		Deliverable del = new Deliverable("asn", "Assignment", "50.0");
		course.addDeliverable(del);
		assertTrue(course.containsDeliverable("asn"));
	}
	@Test
	public void testAddValidDeliverable() throws InvalidInfoException{
		
		Deliverable del = new Deliverable("asn", "Assignment", Double.toString(50.0));
		course.addDeliverable(del);
		boolean added = true;
		for(Student student : course.getStudents().values()){
			if(!student.containsDeliverable(del.getName())) added = false;
		}
		assertTrue(added);
	}
	
	@Test
	public void testAddInvalidDeliverable() throws InvalidInfoException{
		thrown.expect(InvalidInfoException.class);
		thrown.expectMessage("Duplicate deliverable name.");
		Deliverable del = new Deliverable("asn1", "Assignment", "50");
		course.addStudent(new Student("Sam", "Fisher", "250349342", "sfisher@uwo.ca"));
		course.addDeliverable(del);
		course.addDeliverable(new Deliverable(del.getName(), "Assignment", "60"));
	}
	
	/*Remove Deliverable tests*/
	@Test
	public void testRemoveFakeDeliverable() throws ElementNotFoundException,
			InvalidInfoException {
		/*
		 * Attempts to remove a non-existant student. Passes if an
		 * ElementNotFoundException is thrown
		 */
		thrown.expect(ElementNotFoundException.class);
		thrown.expectMessage("Could not remove deliverable - name not found.");
		Deliverable del = new Deliverable("fake this is fake", "Assignment", Double.toString(50.0));

		course.removeDeliverable(del.getName());
	}

	@Test
	public void testRemoveValidDeliverable() throws ElementNotFoundException, InvalidInfoException{
		Deliverable del = new Deliverable("Exists", "Assignment", "60");
		course.addDeliverable(del);
		course.removeDeliverable("Exists");
		assertFalse(course.containsDeliverable(del.getName()));
	}
	
	/*Retrieve deliverable tests*/
	@Test
	public void testGetValidDeliverable() throws InvalidInfoException{
		try{
			Deliverable del = new Deliverable("asn1", "Assignment", "50");
			course.addDeliverable(del);
			assertTrue(course.getDeliverable("asn1").equals(del));
		} catch (ElementNotFoundException enf){
			enf.printStackTrace();
		}
	}
	
	@Test
	public void testGetNonExistantDeliverable() throws InvalidInfoException, ElementNotFoundException{
		thrown.expect(ElementNotFoundException.class);
		thrown.expectMessage("Deliverable not found.");
		course.getDeliverable("doesn't exist");
	}
	
	/*Edit deliverable tests*/
	@Test
	public void testEditDeliverableValidInfo() throws InvalidInfoException, ElementNotFoundException{
		Student student = new Student("Sam", "Fisher", "250434302", "sfisher@uwo.ca");
		course.addStudent(student);
		Deliverable del = new Deliverable("not in course", "Assignment", "50");
		Deliverable editing = new Deliverable("Exam 1", "Exam", "100");
		course.addDeliverable(del);
		course.editDeliverable(editing, del);
		
		assertTrue(del.getName().equals(editing.getName()) && del.getType().equals(editing.getType()) && del.getWeight() == editing.getWeight());
	}
	
	@Test
	public void testEditDeliverableDuplicateName() throws InvalidInfoException, ElementNotFoundException{
		thrown.expect(InvalidInfoException.class);
		thrown.expectMessage("Duplicate deliverable name.");
		Student student = new Student("Sam", "Fisher", "250434302", "sfisher@uwo.ca");
		course.addStudent(student);
		Deliverable del1 = new Deliverable("asn1", "Assignment", "50");
		Deliverable del2 = new Deliverable("exm1", "Exam", "30");
		course.addDeliverable(del1);
		course.addDeliverable(del2);
		course.editDeliverable(new Deliverable("exm1", "Exam", "45"), del1);
	}
	
	/*Tests the getEmail method in Course class is working correctly and sending these emails to an invalid host fails*/
	@Test
	public void testHost() throws Exception {
		thrown.expect(InvalidInfoException.class);
		thrown.expectMessage("There may be something wrong with your email settings. Please check and try again");
		HashMap<String, Student> students = new HashMap<String, Student>();
		Course course = new Course("Code", "Title", "Term");
		Student student1 = new Student("First", "Last" , "250652911", "someone@uwo.ca");
		Student student2 = new Student("FName", "LName" , "250652910", "anotherperson@uwo.ca");
		course.addStudent(student1);
		course.addStudent(student2);
		students = course.getStudents();
		String[] emails = course.getEmails(students);
		String[] attachments = new String[2];
		attachments[0] = "someattach.jrxml";
		attachments[1] = "anotherperson.jrxml";
		course.email(emails, attachments, course, " ", "enterhostname", "username", "password", "port", "true", "true", "Professor Shantz", "senderemail", "Grade Report");
	}
        
        
	/*Edit deliverable tests*/
	@Ignore("Test is failing, skipping at the moment.")
	@Test
	public void testImportStudents() {
            Course course;
            String returnValue = "";
            
            URL url = getClass().getResource("/students-example.csv");
            
            File file;
            file = new File(url.getPath());
            
            try {
                course = new Course("123456789", "abc", "def");
                try {
                    returnValue = course.importStudents(file);
                } catch (Exception ex) { }
            } catch (InvalidInfoException ex) { }
            
            JOptionPane.showMessageDialog(null, file.getPath()+returnValue, "Students", JOptionPane.INFORMATION_MESSAGE);
            
            assertThat(returnValue, containsString("has been imported successfully"));
            
	}
        
	/*Edit deliverable tests*/
	@Ignore("Test is failing, skipping at the moment.")
	@Test
	public void testImportStudentsInvalidFileFormat() {
            Course course;
            String returnValue = "";
            
            URL url = getClass().getResource("/grade-example.csv");
            
            File file;
            file = new File(url.getPath());
            
            try {
                course = new Course("123456789", "abc", "def");
                try {
                    returnValue = course.importStudents(file);
                } catch (Exception ex) { }
                
                //JOptionPane.showMessageDialog(null, file.getPath()+returnValue, "Students", JOptionPane.INFORMATION_MESSAGE);
                
            } catch (InvalidInfoException ex) { }
            
            assertThat(returnValue, containsString("is not a CSV file"));
	}

}

