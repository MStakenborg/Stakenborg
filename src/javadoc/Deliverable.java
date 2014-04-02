package javadoc;
import main.java.ca.uwo.csd.cs2212.team9.InvalidInfoException;

/**
 * Class Deliverable contains all the methods needed to generate a new deliverable for a student
 * Allows user to edit, update, and access deliverable names, types and grade weight/grade
 * 
 * @author team9
 *
 */
public abstract class Deliverable {

		
		/*************************************************************************
		 * Instance Variables
		 **************************************************************************/
		
		//name controls the name of the current deliverable
		String name;
		//type controls whether a deliverable is an exam, assignment, etc
		String type;
		//weight controls the percentage of the final grade the deliverable is worth
		double weight;
		//grade controls the grade the student achieved for current deliverable
		double grade;

		
		/**
		 * Constructor
		 * @param name
		 *            the deliverable name
		 * @param type
		 *            the type of the deliverable (Assignment, Exam)
		 * @param weight
		 *            the grade weight of the deliverable
		 * @throws InvalidInfoException
		 */
		public Deliverable(String name, String type, double weight){
		}

		
		/**************************************************************************
		 * Accessor Methods
		 **************************************************************************/
		
		/**
		 * getGrade returns the grade stored for the current student/deliverable
		 * @param grade
		 *           return the grade
		 */
		public double getGrade() {
			return grade;
		}
		
		/**
		 * getName returns the name of the current deliverable
		 * @return the name
		 */
		public String getName() {
			return name;
		}

		/**
		 * getType returns the type of the current deliverable
		 * @return the type
		 */
		public String getType() {
			return name;
		}
		
		/**
		 * getWeight returns the percentage of final mark current deliverable is worth
		 * @return the weight
		 */
		public double getWeight() {
			return grade;
		}

		
		/**************************************************************************
		 * Mutator Methods
		 **************************************************************************/
		
		/**
		 * setGrade sets the grade for the student for current deliverable
		 * @param name
		 *            the grade to set
		 */
		public void setGrade(double grade) {
		}
		
		/**
		 * setName sets the name of the current deliverable to given name
		 * @param name
		 *            the name to set
		 * @throws InvalidInfoException
		 */
		public void setName(String name) throws InvalidInfoException {
		}
		
		/**
		 * setType sets the type of the current deliverable to given type
		 * @param type
		 *            the type to set
		 * @throws InvalidInfoException
		 */
		public void setType(String type) throws InvalidInfoException {
		}


		/**
		 * setWeight sets the weight of the current deliverable to given weight
		 * @param weight
		 *            the weight to set
		 * @throws InvalidInfoException
		 */
		public void setWeight(double weight) throws InvalidInfoException {
		}

}
