package ca.uwo.csd.cs2212.team9;

import static org.junit.Assert.*;

import java.util.LinkedList;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * @author mstakenb
 */
@SuppressWarnings({ "unused", "deprecation" })

public class GradebookTest {
	private Gradebook gradebook;
	private Course addedCourse;
	private Course simCodeOne;
	private Course simCodeTwo;
	private Course simTitleOne;
	private Course simTitleTwo;
	private Course ghostCourse;
	private Course course;
	
	@Before
	public void setup() throws InvalidInfoException {
			gradebook = new Gradebook("UWO Gradebook");
	}

	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	/* Gradebook invalid active course tests */
	@Test
	public void testGradebookSetInvalidActive() throws ElementNotFoundException, InvalidInfoException {
		/*
		 * Attempts to make an invalid/non existent course the active course
		 */
		thrown.expect(ElementNotFoundException.class);
		thrown.expectMessage("Course does not currently exist");
		gradebook.setActiveCourse(ghostCourse);
	}

	/* Gradebook adding invalid/similar courses tests*/
	@Test
	public void testGradebookAddExistingCourse() throws InvalidInfoException {
		/*
		 * Attempts to add a course to the gradebook that already exists
		 * 
		 */
		thrown.expect(InvalidInfoException.class);
		thrown.expectMessage("A course already exists with this code, title, and term.");
		gradebook.addCourse("test321", "validTest", "Term 2");
		gradebook.addCourse("test321", "validTest", "Term 2");
	}

	@Test
	public void testGradebookAddSimilarCourse() throws InvalidInfoException {
		/*
		 * Attempts to add a course with the same course code in the same term
		 */
		thrown.expect(InvalidInfoException.class);
		thrown.expectMessage("WARNING");
		gradebook.addCourse("uwo2212", "Software Engineering", "Term 1");
		gradebook.addCourse("uwo2212", "Logic", "Term 1");
	}


	@Test
	public void testGradebookAddSimilarTitle() throws InvalidInfoException {
		/*
		 * Attempts to add a course with the same course code in the same term
		 */
		thrown.expect(InvalidInfoException.class);
		thrown.expectMessage("WARNING");
		gradebook.addCourse("2209uwo", "Software Engineering", "Term 1");
		gradebook.addCourse("2210", "Software Engineering", "Term 1");
	}

	
	/* Gradebook delete courses from gradebook tests */
	
	@Test
	public void testGradebookDeleteNonExistingCourse() throws ElementNotFoundException {
		/*
		 * Attempts to add a course with the same course code in the same term
		 */
		thrown.expect(ElementNotFoundException.class);
		thrown.expectMessage("Cannot delete course - does not exist");
		gradebook.delCourse("not", "added" , "Term 1");
	}
	
	@Test
	public void testGradebookDeleteCourseTwice() throws ElementNotFoundException, InvalidInfoException {
		/*
		 * Attempts to add a course with the same course code in the same term
		 */
		thrown.expect(ElementNotFoundException.class);
		thrown.expectMessage("Cannot delete course - does not exist");
		gradebook.addCourse("not", "added" , "Term 1");
		gradebook.delCourse("not", "added" , "Term 1");
		gradebook.delCourse("not", "added" , "Term 1");
	}
	
	@Test
	public void testGradebookEditCourseExistsCase() throws InvalidInfoException{
		thrown.expect(InvalidInfoException.class);
		thrown.expectMessage("A course already exists with this code, title, and term.");
		gradebook.addCourse("2212", "Software", "Winter");
		gradebook.addCourse("3340", "Algorithms", "Winter");
		gradebook.editCourse(gradebook.getCourseList().getFirst(), "3340", "Algorithms", "Winter");
	}
	
	@Test
	public void testGradebookEditCourseWarningCase() throws InvalidInfoException{
		thrown.expect(InvalidInfoException.class);
		thrown.expectMessage("WARNING");
		gradebook.addCourse("2212", "Software", "Winter");
		gradebook.addCourse("3340", "Algorithms", "Winter");
		gradebook.editCourse(gradebook.getCourseList().getFirst(), "2212", "Algorithms", "Winter");
	}
	
	@Test
	public void testGradebookEditCourseDefaultCase() throws InvalidInfoException{
		gradebook.addCourse("2212", "Software", "Winter");
		gradebook.addCourse("3340", "Algorithms", "Winter");
		Course edit = gradebook.getCourseList().getFirst();
		gradebook.editCourse(edit, "4012", "AFakeCourse", "Spring");
		assertTrue(edit.getCode().equals("4012") && edit.getTitle().equals("AFakeCourse") && edit.getTerm().equals("Spring"));
	}
	
	
}
