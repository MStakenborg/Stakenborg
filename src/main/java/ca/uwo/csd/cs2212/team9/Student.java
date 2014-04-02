package ca.uwo.csd.cs2212.team9;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * @author kmcguin
 */

public class Student implements Serializable {
	private final String EMAIL_FORMAT = "^[^@]+@[^@]+\\.[^@]+$";
	private String firstName;
	private String lastName;
	private String number;
	private String email;
	private double courseAvg;
	private double assignAvg;
	private double examAvg;
	private LinkedList<Deliverable> deliverables;

	/**
	 * Creates a student with the students first and last names, student number,
	 * and email address. It also ensures that the student number and email
	 * address are an appropriate format.
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
		this.firstName = firstName;
		this.lastName = lastName;
		this.number = number;
		this.email = email;
		this.assignAvg = Double.NaN;
		this.examAvg = Double.NaN;
		this.courseAvg = Double.NaN;
		this.deliverables = new LinkedList<Deliverable>();
		String errorMessage = "";
		if (firstName.equals("") || firstName.matches("(.*[0-9].*)"))
			errorMessage += "Invalid first name. ";
		if (lastName.equals("") || lastName.matches("(.*[0-9].*)"))
			errorMessage += "Invalid last name. ";
		if (number.equals("") || !number.matches("([0-9]*)") || number.length() != 9)
			errorMessage += "Invalid student number. ";
		if (email.equals("") || !email.matches(EMAIL_FORMAT))
			errorMessage += "Invalid email address. ";
		if (!errorMessage.equals(""))
			throw new InvalidInfoException(errorMessage);
	}

	/**
	 * @return the first name of the student
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @return the last name of the student
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @return the students student number
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * @return the students email address
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return the students course average
	 */
	public double getCourseAvg() {
		return courseAvg;
	}

	/**
	 * @return the students assignment average
	 */
	public double getAssignAvg() {
		return assignAvg;
	}

	/**
	 * @return the students exam average
	 */
	public double getExamAvg() {
		return examAvg;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 * @throws InvalidInfoException
	 */
	public void setFirstName(String firstName) throws InvalidInfoException {
		this.firstName = firstName;
		if (firstName.equals("") || firstName.matches("(.*[0-9].*)"))
			throw new InvalidInfoException(
					"The student must have a valid first name.");
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 * @throws InvalidInfoException
	 */
	public void setLastName(String lastName) throws InvalidInfoException {
		this.lastName = lastName;
		if (lastName.equals("")|| lastName.matches("(.*[0-9].*)"))
			throw new InvalidInfoException(
					"The student must have a valid last name.");

	}

	/**
	 * @param number
	 *            the number to set
	 * @throws InvalidInfoException
	 */
	public void setNumber(String number) throws InvalidInfoException {
		this.number = number;
		if (number.equals("") || !number.matches("([0-9]*)"))
			throw new InvalidInfoException(
					"The student must have a valid student number.");
	}

	/**
	 * @param email
	 *            the email to set
	 * @throws InvalidInfoException
	 */
	public void setEmail(String email) throws InvalidInfoException {
		this.email = email;
		if (email.equals("") || !email.matches("^[^@]+@[^@]+\\.[^@]+$"))
			throw new InvalidInfoException(
					"The student must have a valid email address.");
	}

	/**
	 * calculates course average
	 */
	public void calcCourseGrades() {
		double courseAvgTotal = 0;
		double courseAvgWeight = 0;
		double assignAvgTotal = 0;
		double assignAvgWeight = 0;
		double examAvgTotal = 0;
		double examAvgWeight = 0;
		for (Deliverable del : this.deliverables) {
			double grade = del.getGrade();
			double weight = del.getWeight();
			String type = del.getType();
			if (Double.isNaN(grade))
				continue;
			weight /= 100;
			if (type.equals("Assignment")) {
				assignAvgTotal += grade * weight;
				assignAvgWeight += weight;
			} else {
				examAvgTotal += grade * weight;
				examAvgWeight += weight;
			}
			courseAvgTotal += grade * weight;
			courseAvgWeight += weight;
		}

		this.assignAvg = (assignAvgTotal / assignAvgWeight);
		this.examAvg = (examAvgTotal / examAvgWeight);
		this.courseAvg = (courseAvgTotal / courseAvgWeight);
	}

	/**
	 * Add deliverable to student
	 * 
	 * @throws InvalidInfoException
	 */
	public void addDeliverable(Deliverable del) throws InvalidInfoException {
		if (deliverables.contains(del)) {
			throw new InvalidInfoException("Duplicate deliverable name.");
		}
		deliverables.add(del);
	}

	/**
	 * Delete deliverable from student
	 * 
	 * @throws ElementNotFoundException
	 */
	public void delDeliverable(String name) throws ElementNotFoundException {
		for (Deliverable del : deliverables) {
			if (del.getName().equals(name)) {
				deliverables.remove(del);
				calcCourseGrades();
				return;
			}
		}
		throw new ElementNotFoundException(
				"Could not remove deliverable - name not found.");
	}


	/**
	 * @param name
	 * @return the deliverable with the same name if it exists.
	 * @throws ElementNotFoundException
	 */
	public Deliverable getDeliverable(String name)
			throws ElementNotFoundException {
		for (Deliverable del : deliverables)
			if (del.getName().equals(name))
				return del;
		throw new ElementNotFoundException("Deliverable not found.");
	}

	/**
	 * @return the list of deliverables
	 */
	public LinkedList<Deliverable> getDeliverables() {
		return deliverables;
	}

	/**
	 * Checks if the course already contains a deliverable with the parameter
	 * name
	 * 
	 * @param name
	 *            the name to search for
	 * @return true if there is already a deliverable with that name associated
	 *         with the course.
	 */
	public boolean containsDeliverable(String name) {
		for (Deliverable del : deliverables) {
			if (del.getName().equals(name))
				return true;
		}
		return false;
	}
}
