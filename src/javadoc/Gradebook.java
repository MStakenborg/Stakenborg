package javadoc;
import java.util.LinkedList;

import main.java.ca.uwo.csd.cs2212.team9.ElementNotFoundException;
import main.java.ca.uwo.csd.cs2212.team9.InvalidInfoException;

/**
 * Gradebook class provides methods to create the initial gradebook to store courses 
 * User interface will use gradebook to set the active course and add/delete courses 
 * 
 * @author group9
 */

public abstract class Gradebook {

	/*************************************************************************
	 * Instance Variables
	 **************************************************************************/
	
	//actCourse variable stores the currently active course
	Course actCourse;
	//The list containing any/all the courses added to the gradebook
	private LinkedList<Course> courseList;

	/**
	 * Constructor
	 * @param gbName
	 * @throws InvalidInfoException 
	 * 
	 */
	public Gradebook(String gbName){
	}

	
	/**************************************************************************
	 * Mutator Methods
	 **************************************************************************/
	
	/**
	 * setActiveCourse sets the course the user wants to work with
	 * @param ActiveCourse
	 *            the active course to set
	 * @throws InvalidInfoException
	 */
	public void setActiveCourse(Course course) throws ElementNotFoundException{
	}
	
	
	/**************************************************************************
	 * Helper Methods
	 **************************************************************************/

	/**
	 * addCourse method allows user to add a new course to the gradebook
	 * @param addCourse
	 * @throws InvalidInfoException 
	 */
	public void addCourse(Course newCourse) throws InvalidInfoException{
	}
	
	
	/**
	 * courseExists checks to see if given course is a valid course in the course list
	 * @param course
	 * 			course to look for in list
	 */
	public String courseExists(Course course){
		return null; 
	}
	
	/**
	 * delCourse allows user to delete a course currently in the course list
	 * @param toDelete
	 * 			course to delete from list
	 * @throws ElementNotFoundException 
	 */
	public void delCourse(Course toDelete) throws ElementNotFoundException{
	}

}

