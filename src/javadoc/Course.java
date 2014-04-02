package javadoc;
import java.util.LinkedList;

import main.java.ca.uwo.csd.cs2212.team9.Deliverable;
import main.java.ca.uwo.csd.cs2212.team9.ElementNotFoundException;
import main.java.ca.uwo.csd.cs2212.team9.InvalidInfoException;
import main.java.ca.uwo.csd.cs2212.team9.Student;

/**
 * Course is a class that will be used to create a new Course within the Gradebook program
 * 
 * @author group 9
 * @param <BinaryTree>
 */
public abstract class Course<BinaryTree>{
	/*************************************************************************
	 * Instance Variables
	 **************************************************************************/
	
	//the active course the user can select to view details
	private boolean active;
	//the course code
	private String code;
	//The list containing the deliverables for each course
	private LinkedList<Deliverable> deliverables; 
	//the term the course will be in
	private String term;
	//the title for the course
	private String title;
	//The tree containing students information
	private BinaryTree studentList = null;


    
	/**
	 * Constructor
	 * @param code
	 * @param title
	 * @param term
	 * @throws InvalidInfoException
	 */
	 public Course(String code, String title, String term) throws InvalidInfoException {
	 }
		
	/**************************************************************************
	 * Accessor Methods
	 **************************************************************************/

	/**
	 * Gets the currently selected course
	* @return the courses status
	*/
	public boolean isActive() {
		return false;
	}
	
	
	/**
	* Gets the course code for specified course
	* @return the course code
	*/
	public String getCode() {
		return code;
	}
	

	/**
	 * Returns a list of the deliverables currently assigned to the student
	 * @return the deliverables
	 */
	public LinkedList<Deliverable> getDeliverables() {
		return null;
	}
	
	
	/**
	 * Gets the term for specified course
	 * @return the course term
	 */
	public String getTerm() {
		return term;
	}
	
	
	/**
	 * Gets the course title for specified course
	 * @return the course title
	 */
	public String getTitle() {
		return title;
	}
		
	/**
	 * Gets the list of students currently in active course
	 * @return the class list
	 */
	public BinaryTree getStudentList() {
		return null;
	}
	
	
	/***************************************************************
	 * Mutator Methods
	 ***************************************************************/
	
	/**
	 * @param active
	 *            the activity status to set
	 */
	public void setActive(boolean active) {
	}
	
	
	 /**
	 * Changes course code for specific course to given string
     * @param code
     * @param InvalidInfoException
	 */
	public void setCode(String code) throws InvalidInfoException {
	}
		
		
	 /**
	  * Changes the term the course is in 
	  * @param term
	  * @throws InvalidInfoException
	  */
	public void setTerm(String term) throws InvalidInfoException {
	}


	/**
	 * Changes the title for particular course
	 * @param title
	 * @throws InvalidInfoException
	 */
	public void setTitle(String title) throws InvalidInfoException {
	}

	
	/*****************************************************************
	 * Helper Methods
	 *****************************************************************/

	/**
	* Adds a delivrable to the course, requiring a unique deliverable name.
	* @param del
	*            the deliverable to add
	* @throws InvalidInfoException
	*/
	public void addDeliverable(Deliverable del) throws InvalidInfoException {
	}
	
	
	/**
	* Adds give student to the class list, requiring a unique student number and
	* email address.
	* 
	* @param student
	* 			the student to add to the list
	* @throws InvalidInfoException
	*/
	public void addStudent(Student student) throws InvalidInfoException {
	}

	
	/**
	* Checks if the course already contains a deliverable with the parameter
	* name
	* @param name
	*            the name to search for
	* @return true if there is already a deliverable with that name associated
	*  with the course.
		 */
	private boolean containsDeliverable(String name) {
		return false;
	}
	
	
	/**
	* Check if the class list already contains a student number.
	* 
	* @param email
	*            the email to search for
	* @return true if the email is already in the class list
	*/
	private boolean containsEmail(String email) {
		return false;
	}
	
	
	/**
	* Check if the class list already contains a student number
	* 
	* @param studentNumber 
	* 			the student number to search for
	* @return true if the student number is already in the class list
	*/
	private boolean containsNum(String studentNumber) {
		return false;
	}
	
	
	/**
	 * Email Student allows user to email a student with generated report attached */
	public void email(){
	}
	
	/**
	 * Export Grades allows user to export a list of the grades for current course
	 **/
	public void exportGrades(){
	}
	
	
	/**
	 * Generates a Report for a student with grade average information
	 * @param student 
	 * 		Given student to generate report for
	 **/
	public void genReport(Student student){
	}
	
	
	/**
	 * Import Grades allows user to import a list of grades for a current course
	 **/
	public void importGrades(){
	}
	
	
	/**
	 * Imports class list from a CSV file
	 **/
	public void importStudents() {
	}
	
	
	/**
	* Removes the deliverable specified by name from the course.
	* @param name
	*            the name of the deliverable to remove.
	* @throws ElementNotFoundException
	*/
	public void removeDeliverable(String name) throws ElementNotFoundException {
	}
	
	
	/**
	* Removes the specified student from the class list
	* @param number
	*            the student number of the student to remove
	*/
	public void removeStudent(String studentNumber) {
	}

}
