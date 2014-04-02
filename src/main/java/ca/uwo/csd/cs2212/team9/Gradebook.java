package ca.uwo.csd.cs2212.team9;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author mstakenb
 */
public class Gradebook implements Serializable {

	private String name;
	private Course actCourse;
	private LinkedList<Course> courseList;
	private MailParameters mailParam;

	/**
	 * Creates the gradebook object to work with with the specified name.
	 * @param name
	 */
	public Gradebook(String name){
		this.courseList = new LinkedList<Course>();
		mailParam = new MailParameters();
		this.name = name;
	}
	
	public Gradebook(){
		this.courseList = new LinkedList<Course>();
		mailParam = new MailParameters();
	}
	
	/**
	 * @param ActiveCourse
	 *            the active course to set
	 * @throws InvalidInfoException
	 */
	public void setActiveCourse(Course course) throws ElementNotFoundException {
		// make sure course exists in courseList if it doesn't throw an
		// exception
		if (courseExists(course, null) == "exists") {
			this.actCourse = course;
		} else {
			throw new ElementNotFoundException(
					"Course does not currently exist");
		}
	}

	/**
	 * @return the active course
	 */
	public Course getActiveCourse() {
		return actCourse;
	}

	/**
	 * 
	 * @return the mail parameters
	 */
	public MailParameters getMailParam() {
		return mailParam;
	}

	/**
	 * Edits the mail parameters.
	 * 
	 * @param host
	 * @param username
	 * @param password
	 * @param senderName
	 * @param senderEmail
	 * @param port
	 */
	public void editMailParameters(String host, String username,
			String password, String senderName, String senderEmail, String port) {
		mailParam.setHostname(host);
		mailParam.setUsername(username);
		mailParam.setPassword(password);
		mailParam.setSenderName(senderName);
		mailParam.setSenderEmail(senderEmail);
		mailParam.setPort(port);
	}

	/**
	 * Adds a new course to the gradebook, making sure to only add valid courses
	 * with no repeats.
	 * 
	 * @param addCourse
	 * @throws InvalidInfoException
	 */
	public void addCourse(String code, String title, String term)
			throws InvalidInfoException {
		Course newCourse = new Course(code, title, term);
		String status = courseExists(newCourse, null);
		if (status.equals("exists")) {
			throw new InvalidInfoException(
					"A course already exists with this code, title, and term.");
		} else if (status.equals("warning")) {
			courseList.add(newCourse);
			// can change this later after GUI development -- popup message
			// warning user and can cancel or continue to add
			throw new InvalidInfoException("WARNING");
		} else if (status.equals("absent")) { // use this to test then can
												// simplify
			courseList.add(newCourse);
		}
	}

	/**
	 * Edits course to have the specified code, title, and term.
	 * 
	 * @param course
	 * @param code
	 * @param title
	 * @param term
	 * @throws InvalidInfoException
	 */
	public void editCourse(Course ignoreCourse, String code, String title,
			String term) throws InvalidInfoException {
		Course course = new Course(code, title, term);
		String status = courseExists(course, ignoreCourse);
		switch (status) {
		case "exists":
			throw new InvalidInfoException(
					"A course already exists with this code, title, and term.");
		case "warning":
			ignoreCourse.setCode(code);
			ignoreCourse.setTitle(title);
			ignoreCourse.setTerm(term);
			throw new InvalidInfoException("WARNING");
		default:
			ignoreCourse.setCode(code);
			ignoreCourse.setTitle(title);
			ignoreCourse.setTerm(term);
		}
	}

	/**
	 * Attempts to delete the course with the specified code, title, and term,
	 * throwing and ElementNotFoundException if the course does not exist.
	 * 
	 * @param delCourse
	 * @throws ElementNotFoundException
	 */
	public void delCourse(String code, String title, String term)
			throws ElementNotFoundException {
		Course toDelete;
		try {
			toDelete = new Course(code, title, term);
			boolean result = false;
			if (courseList.isEmpty()) {
				throw new ElementNotFoundException(
						"Cannot delete course - does not exist");
			}
			Iterator<Course> iter = courseList.iterator();
			Course found;
			while (iter.hasNext()) {
				found = iter.next();
				if (found.getCode().equals(toDelete.getCode())
						&& found.getTitle().equals(toDelete.getTitle())
						&& found.getTerm().equals(toDelete.getTerm())) {
					if (found.equals(actCourse)) {
						actCourse = null;
					}
					iter.remove();
					result = true;
				}
			}
			if (result == false) {
				throw new ElementNotFoundException(
						"Cannot delete course - does not exist");
			}
		} catch (InvalidInfoException e) {

			e.printStackTrace();
		}

	}

	/**
	 * @param courseExists
	 * @return a string letting us know the status of the course:
	 * 			"exists": Exact same code, title, term
	 * 			"warning": Duplicate codes exist, with different title, or vice versa.
	 * 			"absent": Code and title with different terms OR course does not exist
	 */
	private String courseExists(Course course, Course ignoreCourse) {
		String result = "";
		if (courseList.isEmpty()) {
			result = "absent";
			return result;
		}
		String courseTerm = course.getTerm();
		String courseCode = course.getCode();
		String courseTitle = course.getTitle();

		Iterator<Course> iter = courseList.iterator();
		Course next;
		while (iter.hasNext()) {
			next = iter.next();
			if (next.equals(ignoreCourse)) {
				continue;
			}
			String term = next.getTerm();
			String code = next.getCode();
			String title = next.getTitle();
			if (code.equals(courseCode) && title.equals(courseTitle)
					&& term.equals(courseTerm)) {
				result = "exists";
				break;
			}
			if (code.equals(courseCode) && term.equals(courseTerm)
					|| title.equals(courseTitle) && term.equals(courseTerm)) {
				result = "warning";
			} else {
				result = "absent";
			}
		}
		return result;
	}

	/**
	 * @param courseExists
	 *            to be used to check if a course has valid input (no blank
	 *            attributes)
	 */
	/*Think this might be unused.
	 * public String validCourse(Course newCourse) {
		String status = "";
		if (newCourse.getCode().equals("")) {
			status += "Invalid Code. ";
		}
		if (newCourse.getTitle().equals("")) {
			status += "Invalid Title. ";
		}
		if (newCourse.getTerm().equals("")) {
			status += "Invalid Term. ";
		}
		return status;
	}*/

	/**
	 * @return the course list.
	 */
	public LinkedList<Course> getCourseList() {
		return this.courseList;
	}

	/**
	 * Generates a string representation of the gradebook.
	 */
	public String toString() {
		String res = "";
		for (Course course : courseList) {
			res += course.getTerm() + " - " + course.getTitle() + "\n";
		}
		return res;
	}

}
