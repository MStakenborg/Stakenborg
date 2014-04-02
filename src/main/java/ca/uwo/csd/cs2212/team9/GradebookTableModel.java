/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.uwo.csd.cs2212.team9;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * 
 * @author Tyler
 */
public class GradebookTableModel extends AbstractTableModel {

	private final static int COLUMN_COUNT = 5;
	private final static int IDX_NAME = 0;
	private final static int IDX_NUMBER = 1;
	private final static int IDX_COURSE_GRADE = 2;
	private final static int IDX_EXAM_AVERAGE = 3;
	private final static int IDX_ASSIGNMENT_AVERAGE = 4;
	private final List<Student> students;
	private final List<Deliverable> deliverables;

	/**
	 * 
	 * @param activeCourse
	 */
	public GradebookTableModel(Course activeCourse) {
		this.students = new ArrayList<>(activeCourse.getStudents().values());
		deliverables = activeCourse.getDeliverables();
	}

	public List<Student> getStudents() {
		return students;
	}

	@Override
	public int getRowCount() {
		return students.size();
	}

	@Override
	public int getColumnCount() {
		return COLUMN_COUNT + deliverables.size();
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return (columnIndex >= IDX_COURSE_GRADE ? Double.class : String.class);
	}

	@Override
	public String getColumnName(int columnIndex) {
		switch (columnIndex) {
		case IDX_NAME:
			return "<html> <center> Student <br> Name <center> </html>";
		case IDX_NUMBER:
			return "<html> <center> Student <br> Number <center> </html>";
		case IDX_COURSE_GRADE:
			return "<html> <center> Course <br> Grade <center> </html>";
		case IDX_EXAM_AVERAGE:
			return "<html> <center> Exam <br> Average <center> </html>";
		case IDX_ASSIGNMENT_AVERAGE:
			return "<html> <center> Assignment <br> Average </center> </html>";
		default:
			if (columnIndex >= COLUMN_COUNT
					&& columnIndex < COLUMN_COUNT + deliverables.size()) {
				return deliverables.get(columnIndex - COLUMN_COUNT).toString();
			}
			return null;
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if ((rowIndex < 0) || (rowIndex >= students.size())) {
			return null;
		}
		Student student = students.get(rowIndex);
		List<Deliverable> studentDeliverables = student.getDeliverables();
		switch (columnIndex) {
		case IDX_NAME:
			return student.getFirstName() + " " + student.getLastName();
		case IDX_NUMBER:
			return student.getNumber();
		case IDX_COURSE_GRADE:
			double courseAvg = student.getCourseAvg();
			if (Double.isNaN(courseAvg))
				return null;
			return courseAvg;
		case IDX_EXAM_AVERAGE:
			double examAvg = student.getExamAvg();
			if (Double.isNaN(examAvg))
				return null;
			return examAvg;
		case IDX_ASSIGNMENT_AVERAGE:
			double assignAvg = student.getAssignAvg();
			if (Double.isNaN(assignAvg))
				return null;
			return assignAvg;
		default:
			if (columnIndex >= COLUMN_COUNT
					&& columnIndex < COLUMN_COUNT + studentDeliverables.size()) {
				double grade = studentDeliverables.get(
						columnIndex - COLUMN_COUNT).getGrade();
				if (Double.isNaN(grade))
					return null;
				return grade;
			}
			return null;
		}
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if ((rowIndex < 0) || (rowIndex >= students.size())
				|| columnIndex <= IDX_ASSIGNMENT_AVERAGE) {
			return;
		}
		Student student = students.get(rowIndex);
		List<Deliverable> studentDeliverables = student.getDeliverables();
		Deliverable deliverable = studentDeliverables.get(columnIndex
				- COLUMN_COUNT);
		try {
			deliverable.setGrade((double) aValue);
		} catch (InvalidInfoException e) {
			e.printStackTrace();
		}
		student.calcCourseGrades();
		fireTableCellUpdated(rowIndex, IDX_COURSE_GRADE);
		fireTableCellUpdated(rowIndex, IDX_ASSIGNMENT_AVERAGE);
		fireTableCellUpdated(rowIndex, IDX_EXAM_AVERAGE);
		fireTableCellUpdated(rowIndex, columnIndex);
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if (columnIndex > IDX_ASSIGNMENT_AVERAGE) {
			return true;
		} else {
			return false;
		}
	}

}
