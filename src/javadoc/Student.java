package javadoc;

import main.java.ca.uwo.csd.cs2212.team9.InvalidInfoException;

/**
 * Student class is used to create a new student with given student information
 * Allows user to edit and retrieve student information
 * Allows user to calculate course, assignment, and exam averages
 * Allows user to add a new deliverable to the student's record
 *  
 * @author team9
 *
 */
public abstract class Student {


		/*************************************************************************
		 * Instance Variables
		 **************************************************************************/
		
		//assignAvg holds the student's calculated current assignment average
		private double assignAvg;
		//courseAvg holds the student's calculated current course average
		private double courseAvg;
		//email stores the email address to contact current student
		private String email;
		//examAvg holds the student's calculated exam average
		private double examAvg;
		//firstName stores the current student's first name
		private String firstName;
		//lastName stores the current student's last name
		private String lastName;
		//number stores the student number for current student
		private String number;



		
		
		/**
		 * Constructor
		 * 
		 * @param firstName
		 *            the students first name
		 * @param lastName
		 *            the students last name
		 * @param number
		 *            the students unique student number
		 * @param email
		 *            the students unique email address
		 * @throws InvalidInfoException
		 */
		public Student(String firstName, String lastName, String number,
				String email) throws InvalidInfoException {
		}

		
		/**************************************************************************
		 * Accessor Methods
		 **************************************************************************/
		
		/**
		 * getAssignAvg returns the assignment average for current student
		 * @return the students assignment average
		 */
		public double getAssignAvg(){
			return 0;
		}
		
		
		/**
		 * getCourseAvg returns the course average for current student
		 * @return the students course average
		 */
		public double getCourseAvg(){
			return 0;
		}
		
		
		/**
		 * getEmail returns the email address for current student
		 * @return the students email address
		 */
		public String getEmail() {
			return email;
		}
		
		
		/**
		 * getExamAvg returns the exam average for current student
		 * @return the students exam average
		 */
		public double getExamAvg(){
			return examAvg;
		}
		
		
		/**
		 * getFirstName returns the current student's first name
		 * @return the first name of the student
		 */
		public String getFirstName() {
			return firstName;
		}
		

		/**
		 * getLastName returns the current student's last name
		 * @return the last name of the student
		 */
		public String getLastName() {
			return lastName;
		}
		

		/**
		 * getNumber returns the current student's student number
		 * @return the students student number
		 */
		public String getNumber() {
			return number;
		}
		

		/**************************************************************************
		 * Mutator Methods
		 **************************************************************************/
		
		/**
		 * setFirst name sets the first name of the student to given name
		 * checks validity of given name
		 * 
		 * @param firstName
		 *            the firstName to set
		 * @throws InvalidInfoException
		 */
		public void setFirstName(String firstName) throws InvalidInfoException {
		}

		/**
		 * setLastName sets the last name of student to given name
		 * checks validity of given name
		 * @param lastName
		 *            the lastName to set
		 * @throws InvalidInfoException
		 */
		public void setLastName(String lastName) throws InvalidInfoException {
		}

		/**
		 * setNumber sets the student's student number to given number
		 * checks validity of given number
		 * @param number
		 *            the number to set
		 * @throws InvalidInfoException
		 */
		public void setNumber(String number) throws InvalidInfoException {
		}

		/**
		 * setEmail sets the email address of current student to given email 
		 * checks validity of email address given
		 * @param email
		 *            the email to set
		 * @throws InvalidInfoException
		 */
		public void setEmail(String email) throws InvalidInfoException {
		}
		
		
		/***************************************************************
		 * Mutator Methods
		 ***************************************************************/
		
		/**
		 * calcCourseAvg calculates the course average for current student
		 * uses grade information to calculate
		 * 
		 * @param courseAvg
		 * 			calculates course average
		 */
		public double calcCourseAvg(){
			return 0;
		}

		/**
		 * calcAssignAvg calculates the assignment average for current student
		 * uses grade information to calculate
		 * 
		 * @param assignAvg
		 * 			calculates the assignment average
		 */
		public double calcAssignAvg(){
			return 0;
		}
		
		
		/**
		 * calcExamAvg calculates the exam average for current student
		 * uses grade information to calculate
		 * @param examAvg
		 * 			calculates the exam average
		 */
		public double calcExamAvg(){
			return 0;
		}
		
		
		/**
		 * 	AddDeliverable adds a new deliverable to current student
		 */
		public void addDeliverable(){
		}
		
		
		/**
		 *   delDeliverable deletes a deliverable from current student
		 */
		public void delDeliverable(){
		}
}
