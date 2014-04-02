package ca.uwo.csd.cs2212.team9;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.io.*;
import java.util.*;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.*;
import net.sf.jasperreports.engine.design.*;
import net.sf.jasperreports.engine.xml.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.activation.DataSource;
import javax.activation.URLDataSource;

/**
 * @author kmcguin To-Do: importStudents method, data persistence and tests
 */
public class Course implements Serializable {

	private String code;
	private String title;
	private String term;
	private HashMap<String, Student> students;
	private LinkedList<Deliverable> deliverables;
	private final static String REPORT_FILENAME = "student_report";
	private final static String REPORT_LOGO = "uwo_logo_report.png";

	/**
	 * Construct a course with a valid course code, title and term
	 * 
	 * @param code
	 *            the course code
	 * @param title
	 *            the courses title
	 * @param term
	 *            the term the course occurs in
	 * @throws team9.InvalidInfoException
	 */
	public Course(String code, String title, String term)
			throws InvalidInfoException {
		this.code = code;
		this.title = title;
		this.term = term;
		this.students = new HashMap<String, Student>();
		this.deliverables = new LinkedList<Deliverable>();
		String errorMessage = "";
		if (code.equals("") || code == null)
			errorMessage += "Invalid Code. ";
		if (title.equals("") || title == null)
			errorMessage += "Invalid Title. ";
		if (term.equals("") || term == null)
			errorMessage += "Invalid Term. ";
		if (!errorMessage.equals(""))
			throw new InvalidInfoException(errorMessage);
	}

	/* Getters and setters */
	/**
	 * @return the course code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the course code to set
	 */
	public boolean setCode(String code) throws InvalidInfoException {
		this.code = code;
		if (code == "" || code == null) {
			throw new InvalidInfoException("The course must have a code.");
		}
		return true;
	}

	/**
	 * @return the course title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param studentNumber
	 * @return the student with the specified student number.
	 */
	public Student getStudent(String studentNumber) {
		return students.get(studentNumber);
	}

	/**
	 * @param name
	 * @return the deliverable with the specified name
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
	 * @return the list containing the courses deliverables.
	 */
	public LinkedList<Deliverable> getDeliverables() {
		return deliverables;
	}

	/**
	 * @param title
	 *            the course title to set
	 * @throws InvalidInfoException
	 */
	public void setTitle(String title) throws InvalidInfoException {
		this.title = title;
		if (title == "" || title == null) {
			throw new InvalidInfoException("The course must have a title.");
		}
	}

	/**
	 * @return the course term
	 */
	public String getTerm() {
		return term;
	}

	/**
	 * @param term
	 *            the term to set
	 * @throws InvalidInfoException
	 */
	public void setTerm(String term) throws InvalidInfoException {
		this.term = term;
		if (term == "" || term == null) {
			throw new InvalidInfoException("The course must have a term.");
		}
	}

	/**
	 * @return the set of students
	 */
	public HashMap<String, Student> getStudents() {
		return students;
	}

	/* Add & remove student with helpers */
	/**
	 * Adds a student to the class list, requiring a unique student number and
	 * email address.
	 * 
	 * @param student
	 *            the student to add
	 * @throws InvalidInfoException
	 */
	public void addStudent(Student student) throws InvalidInfoException {
		String studentNum = student.getNumber();
		String studentEmail = student.getEmail();
		String errorMessage = "";
		if (students.containsKey(studentNum)) {
			errorMessage += "Duplicate student number.";
		}
		if (this.containsEmail(studentEmail)) {
			errorMessage += "Duplicate email address.";
		}
		if (!errorMessage.equals("")) {
			throw new InvalidInfoException(errorMessage);
		}
		students.put(studentNum, student);
		for (Deliverable del : deliverables)
			student.addDeliverable(new Deliverable(del.getName(),
					del.getType(), Double.toString(del.getWeight())));
	}

	/**
	 * Edits a students attributes.
	 * 
	 * @param infoStudent
	 *            the Student which stores the desired edits
	 * @param toEdit
	 *            the Student to edit
	 * @throws InvalidInfoException
	 */
	public void editStudent(Student infoStudent, Student toEdit)
			throws InvalidInfoException {
		String studentNum = infoStudent.getNumber();
		String studentEmail = infoStudent.getEmail();
		String firstName = infoStudent.getFirstName();
		String lastName = infoStudent.getLastName();
		String currentNum = toEdit.getNumber();
		String currentEmail = toEdit.getEmail();
		String errorMessage = "";

		if (students.containsKey(studentNum) && !studentNum.equals(currentNum))
			throw new InvalidInfoException("Duplicate student number.");
		if (this.containsEmail(studentEmail)
				&& !studentEmail.equals(currentEmail))
			throw new InvalidInfoException("Duplicate email address.");

		students.remove(toEdit.getNumber());
		toEdit.setFirstName(firstName);
		toEdit.setLastName(lastName);
		toEdit.setNumber(studentNum);
		toEdit.setEmail(studentEmail);
		students.put(studentNum, toEdit);
	}

	/**
	 * Check if the class list already contains a student number.
	 * 
	 * @param email
	 *            the email to search for
	 * @return true if the email is already in the class list
	 */
	private boolean containsEmail(String email) {
		for (Student value : students.values()) {
			if (value.getEmail().equals(email)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Removes the specified student from the class list
	 * 
	 * @param number
	 *            the student number of the student to remove
	 */
	public void removeStudent(String studentNumber)
			throws ElementNotFoundException {
		if (!students.containsKey(studentNumber)) {
			throw new ElementNotFoundException(
					"Could not remove student - number not found.");
		}
		students.remove(studentNumber);
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
			if (del.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}

	/* Add deliverable and helpers */
	/**
	 * Adds a deliverable to the course by adding it to each student, requiring
	 * a unique deliverable name.
	 * 
	 * @param del
	 *            the deliverable to add
	 * @throws InvalidInfoException
	 */
	public void addDeliverable(Deliverable del) throws InvalidInfoException {
		for (Student value : students.values()) {
			if (value.containsDeliverable(del.getName())) {
				throw new InvalidInfoException("Duplicate deliverable name.");
			}
			value.addDeliverable(new Deliverable(del.getName(), del.getType(),
					Double.toString(del.getWeight())));
		}
		deliverables.add(del);
	}

	/**
	 * Edits a deliverables attributes.
	 * 
	 * @param infoDeliverable
	 *            the Deliverable that stores the desired changes.
	 * @param toEdit
	 *            the Deliverable to edit.
	 * @throws InvalidInfoException
	 * @throws ElementNotFoundException
	 */
	public void editDeliverable(Deliverable infoDeliverable, Deliverable toEdit)
			throws InvalidInfoException, ElementNotFoundException {
		String currentName = toEdit.getName();
		String name = infoDeliverable.getName();
		String type = infoDeliverable.getType();
		double weight = infoDeliverable.getWeight();
		Deliverable del;

		for (Student student : students.values()) {
			if (student.containsDeliverable(name) && !name.equals(currentName))
				throw new InvalidInfoException("Duplicate deliverable name.");
			del = student.getDeliverable(currentName);
			del.setName(name);
			del.setType(type);
			del.setWeight(weight);
			student.calcCourseGrades();
		}
		if (this.containsDeliverable(name) && !name.equals(currentName))
			throw new InvalidInfoException("Duplicate deliverable name.");
		del = this.getDeliverable(currentName);
		del.setName(name);
		del.setType(type);
		del.setWeight(weight);

	}

	/**
	 * Removes the deliverable specified by name from the course.
	 * 
	 * @param name
	 *            the name of the deliverable to remove.
	 * @throws ElementNotFoundException
	 */
	public void removeDeliverable(String name) throws ElementNotFoundException {

		for (Student value : students.values()) {
			if (!value.containsDeliverable(name)) {
				throw new ElementNotFoundException(
						"Could not remove deliverable - name not found.");
			}
			value.delDeliverable(name);
		}
		for (Deliverable del : deliverables) {
			if (del.getName().equals(name)) {
				deliverables.remove(del);
				return;
			}
		}
		throw new ElementNotFoundException(
				"Could not remove deliverable - name not found.");

	}

	/**
	 * Calculates a students weighted average of the deliverables.
	 * 
	 * @param studentNumber
	 * @throws InvalidInfoException
	 * @throws ElementNotFoundException
	 */
	public void calculateDeliverableAverages(String studentNumber)
			throws InvalidInfoException, ElementNotFoundException {
		Student studentDelAvg = students.get(studentNumber);
		Deliverable delAvg;
		for (Deliverable del : deliverables) {
			int n = 0;
			double totalMarks = 0;

			for (Student student : students.values()) {
				Deliverable studentDel = student.getDeliverable(del.getName());
				if (!Double.isNaN(studentDel.getGrade())) {
					n++;
					totalMarks += studentDel.getGrade();
				}
			}
			del.setClassAvg(totalMarks / n);
			delAvg = studentDelAvg.getDeliverable(del.getName());
			delAvg.setClassAvg(totalMarks / n);
		}
	}

	/**
	 * Generates a pdf grade report for a student using JasperReports
	 * 
	 * @param number
	 * @throws Exception
	 */
	public void genReport(String number) throws Exception {
		Student student = getStudent(number);
		calculateDeliverableAverages(number);
		InputStream reportStream = loadResource(REPORT_FILENAME + ".jrxml");
		InputStream logoStream = loadResource(REPORT_LOGO);
		BufferedImage logo = ImageIO.read(logoStream);

		Collection<Deliverable> deliverables = student.getDeliverables();

		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
				deliverables);

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("logo", logo);
		parameters.put("student", student);
		parameters.put("course", this);

		JasperDesign jasperDesign = JRXmlLoader.load(reportStream);
		JasperReport jasperReport = JasperCompileManager
				.compileReport(jasperDesign);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
				parameters, beanColDataSource);
		JasperExportManager.exportReportToPdfFile(
				jasperPrint,
				"src/main/resources/"
						+ student.getEmail().substring(0,
								student.getEmail().indexOf("@")) + ".pdf");
	}

	private static InputStream loadResource(String filename) {
		return Course.class.getClassLoader().getResourceAsStream(filename);
	}

	/**
	 * Sends an email to students. Email addresses, attachments, and email
	 * configuration is used as input.
	 * 
	 * @param emails
	 * @param attachments
	 * @param active
	 * @param emailBody
	 * @param hostname
	 * @param username
	 * @param password
	 * @param port
	 * @param auth
	 * @param tls
	 * @param senderName
	 * @param senderEmail
	 * @param subject
	 * @throws Exception
	 */
	public void email(String[] emails, String[] attachments, Course active,
			String emailBody, String hostname, String username,
			String password, String port, String auth, String tls,
			String senderName, String senderEmail, String subject)
			throws Exception {
		if (hostname == null || username == null || password == null
				|| port == null || auth == null || tls == null
				|| senderName == null || senderEmail == null || subject == null) {
			throw new InvalidInfoException(
					"Invalid email settings - please check configuration in email menu and try again");
		}
		if (emails == null || emails.length == 0) {
			throw new InvalidInfoException("No email addresses specified");
		} else {
			Mail mail = new Mail(emails, attachments);
			mail.email(active, emailBody, hostname, username, password, port,
					auth, tls, senderName, senderEmail, subject);
		}
	}

	/**
	 * Gathers student email addresses according to student ID in order to email
	 * the correct grade reports.
	 * 
	 * @param students
	 * @return An array of the emails of students in the course
	 * @throws InvalidInfoException
	 */
	public String[] getEmails(HashMap<String, Student> students)
			throws InvalidInfoException {
		int size = students.size();
		String[] emails = new String[size];
		// search for student number and return email addresses for emails array
		// then pass to email method
		int i = 0;
		// iterating over values only
		for (Student value : students.values()) {
			emails[i] = value.getEmail();
			i++;
		}
		return emails;
	}

	/* Import class list from CSV */
	public String importStudents(File csvfile) throws Exception {

            String errors = "";
            String line;
            String cvsSplitBy = ",";
            BufferedReader br = null;

            try {
                br = new BufferedReader(new FileReader(csvfile));

                int i = 0;
                while ((line = br.readLine()) != null) {
                    i++;
                    String[] studentline = line.split(cvsSplitBy);

                    try {
                        String firstName = studentline[10].replace("\"", "");
                        String lastName = studentline[9].replace("\"", "");
                        String studentNr = studentline[8].replace("\"", "");
                        String studentEmail = studentline[13].replace("\"", "");

                        Student newstudent = new Student(firstName, lastName, studentNr, studentEmail);
                        try {
                            team9_Gradebook.getGradebook().getActiveCourse().addStudent(newstudent);
                        } catch (InvalidInfoException ex) {
                            errors += "Line " + i + ": " + ex.getMessage() + "\n";
                        }
                    } catch (InvalidInfoException ex) {
                        errors += "Line " + i + ": " + ex.getMessage() + "\n";
                    } catch (ArrayIndexOutOfBoundsException ex) {                        
                        throw new Exception(csvfile.getName() + " is not a CSV file or is in the incorrect format!");
                    }

                }
            } catch (IOException e) {
                throw new Exception(e.getMessage());
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        throw new Exception(e.getMessage());
                    }
                }
            }

            if (!errors.equals("")) {
                throw new NoSuchMethodException(errors);
            }
            
            return csvfile.getName() + " has been imported successfully!";

	}

	/* Export Grades */
	public String exportGrades(File csvfile) throws Exception {

		FileWriter writer;
		String filename = csvfile.getPath() + ".csv";

		GradebookTableModel exportgradebook = new GradebookTableModel(team9_Gradebook.getGradebook().getActiveCourse());

                if (exportgradebook.getRowCount() > 0) {
                    try {
                        writer = new FileWriter(filename);

                        writer.append("\"First Name\",\"Last Name\",\"Student Number\",\"Email\"");

                        // Do the follow for each deliverable...
                        int ideliverables = team9_Gradebook.getGradebook().getActiveCourse().getDeliverables().size();
                        
                        for (int i = 0; i < ideliverables; i++) {
                            writer.append(",\"" + team9_Gradebook.getGradebook().getActiveCourse().getDeliverables().get(i).getName() + "\"");
                        }

                        writer.append('\n');

                        for (int i = 0; i < exportgradebook.getStudents().size(); i++) {
                            writer.append("\"" + exportgradebook.getStudents().get(i).getFirstName() + "\",");
                            writer.append("\"" + exportgradebook.getStudents().get(i).getLastName() + "\",");
                            writer.append("\"" + exportgradebook.getStudents().get(i).getNumber() + "\",");
                            writer.append("\"" + exportgradebook.getStudents().get(i).getEmail() + "\"");

                            for (int d = 0; d < ideliverables; d++) {
                                
                                double grade = exportgradebook.getStudents().get(i).getDeliverables().get(d).getGrade();
                                
                                if (Double.isNaN(grade)) {
                                    grade = 0;
                                } else {
                                    grade = grade / 100;
                                }
                                
                                writer.append("," + grade);
                            }

                            writer.append('\n');
                        }

                        writer.flush();
                        writer.close();

                        return "Grades were successfully exported!";

                        } catch (IOException e) {
                            throw new Exception(e.getMessage());
                        }
                    
                } else {
                    throw new NoSuchMethodException("There are no students to export!");
                }
                
	}

	/* Import Grades */
	public String importGrades(File csvfile) throws Exception {

            String errors = "";
            String line;
            String cvsSplitBy = ",";
            BufferedReader br = null;

            List deliverableslist = new ArrayList();

            try {
                br = new BufferedReader(new FileReader(csvfile));

                int i = 0;
                while ((line = br.readLine()) != null) {
                    i++;
                    String[] gradeline = line.split(cvsSplitBy);

                    if (i == 1) {

                        for (int g = 0; g < gradeline.length; g++) {

                            if (g == 0) {
                                if (!gradeline[0].replace("\"", "").trim().equals("Student Number")) {
                                    throw new Exception("The file you are importing is in an incorrect format.");
                                }
                            } else {

                                String del = gradeline[g].replace("\"", "").trim();
                                deliverableslist.add(del);

                                try {
                                    team9_Gradebook.getGradebook().getActiveCourse().getDeliverable(del);
                                } catch (ElementNotFoundException ex) {
                                    try {
                                        String type = "Exam";
                                        if (del.contains("ssign")) { type = "Assignment"; }

                                        Deliverable newdel = new Deliverable(del, type, "100");
                                        team9_Gradebook.getGradebook().getActiveCourse().addDeliverable(newdel);
                                    } catch (InvalidInfoException ex1) {
                                        errors += "Error while creating deliverable "+ del + "!\n";
                                    }
                                }

                            }

                        }

                    } else {

                        String studentnumber = gradeline[0].replace("\"", "").trim();

                        for (int d = 0; d < deliverableslist.size(); d++) {

                            Deliverable deliverableexists;
                            Student student;

                            student = team9_Gradebook.getGradebook().getActiveCourse().getStudent(studentnumber);

                            if (student == null) {
                                errors += "Student " + studentnumber + ": Student not found! Grades for that student will not be imported!\n";
                            } else {

                                try {
                                    deliverableexists = student.getDeliverable(deliverableslist.get(d).toString());

                                    if (deliverableexists.getGrade() > 0) {
                                        errors += "Student " + studentnumber + ": Deliverable " + deliverableslist.get(d).toString() + " already has grade! Requested grade was not imported!\n";
                                    } else {
                                        double gradevalue;

                                        try {
                                            gradevalue = Double.parseDouble(gradeline[d + 1]);
                                            gradevalue = gradevalue * 100;

                                            try {
                                                team9_Gradebook.getGradebook().getActiveCourse().getStudent(studentnumber).getDeliverable(deliverableslist.get(d).toString()).setGrade(gradevalue);
                                            } catch (InvalidInfoException ex) {
                                                errors += "Student " + studentnumber + ": Deliverable " + deliverableslist.get(d).toString() + " grade is in an invalid format. Requested grade will not be imported! \n";
                                            }
                                        } catch (Exception e) {
                                            // Deliverable without grade... Do not display error!
                                        }

                                    }

                                } catch (ElementNotFoundException ex) {
                                    errors += "Student " + studentnumber + ": Deliverable " + deliverableslist.get(d).toString() + " was not found and could not be created. Invalid format. \n";
                                }

                            }
                        }

                    }

                }

            } catch (IOException e) {
                throw new Exception(e.getMessage());
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        throw new Exception(e.getMessage());
                    }
                }
            }

            if (!errors.equals("")) {
                throw new NoSuchMethodException(errors);
            } else {
                return csvfile.getName() + " has been imported successfully!";
            }

	}

	/**
	 * Generates a string representation of a course.
	 */
	public String toString() {
		String s = "";
		s += this.code + ", " + this.term;
		return s;
	}
}