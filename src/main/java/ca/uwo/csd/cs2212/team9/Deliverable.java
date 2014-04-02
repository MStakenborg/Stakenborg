package ca.uwo.csd.cs2212.team9;

import java.io.*;

/**
 * @author kmcguin
 */
public class Deliverable implements Serializable {
	String name;
	String type;
	double weight;
	double grade;
	double classAvg;

	/**
	 * @param name
	 *            the deliverable name
	 * @param type
	 *            the type of the deliverable (Assignment, Exam)
	 * @param weight
	 *            the grade weight of the deliverable
	 * @throws InvalidInfoException
	 */
	public Deliverable(String name, String type, String weight)
			throws InvalidInfoException {
		this.name = name;
		this.type = type;
		this.grade = Double.NaN;
		this.classAvg = Double.NaN;
		try {
			this.weight = Double.parseDouble(weight);
		} catch (NumberFormatException nfe) {
			throw new InvalidInfoException(
					"Deliverable weight must be a number.");
		}
		String errorMessage = "";
		if (name.equals(""))
			errorMessage += "Deliverables must have a name. ";
		if (type.equals(""))
			errorMessage += "Deliverables must have a type. ";
		if (this.weight < 0 || this.weight > 100)
			errorMessage += "Deliverables must have a weight in [0, 100].";
		if (!errorMessage.equals(""))
			throw new InvalidInfoException(errorMessage);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 * @throws InvalidInfoException
	 */
	public void setName(String name) throws InvalidInfoException {
		if (name.equals(""))
			throw new InvalidInfoException("Deliverables must have a name.");
		this.name = name;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 * @throws InvalidInfoException
	 */
	public void setType(String type) throws InvalidInfoException {
		if (type.equals(""))
			throw new InvalidInfoException("Deliverables must have a type.");
		this.type = type;
	}

	/**
	 * @return the weight
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * @param weight
	 *            the weight to set
	 * @throws InvalidInfoException
	 */
	public void setWeight(double weight) throws InvalidInfoException {
		if (weight <= 0 || weight > 100)
			throw new InvalidInfoException(
					"Deliverables must have a weight in [0, 100].");
		this.weight = weight;

	}

	/**
	 * @param grade
	 *            return the grade
	 * 
	 */
	public double getGrade() {
		return grade;
	}

	/**
	 * @param name
	 *            the grade to set
	 * @throws InvalidInfoException
	 */
	public void setGrade(double grade) throws InvalidInfoException {
		// if (grade < 0 || grade > 100) throw new InvalidInfoException(
		// "Grades must be a number in [0, 100].");
		this.grade = grade;
	}

	/**
	 * @param grade
	 *            return the grade
	 * 
	 */
	public double getClassAvg() {
		return classAvg;
	}

	/**
	 * @param name
	 *            the grade to set
	 * @throws InvalidInfoException
	 */
	public void setClassAvg(double grade) throws InvalidInfoException {
		// if (grade < 0 || grade > 100) throw new InvalidInfoException(
		// "Grades must be a number in [0, 100].");
		this.classAvg = grade;
	}

	public String toString() {
		return ("<html><nobr>" + this.name + "<br>(" + this.type + ", "
				+ Double.toString(this.weight) + "%)</nobr></html>");
	}
}
