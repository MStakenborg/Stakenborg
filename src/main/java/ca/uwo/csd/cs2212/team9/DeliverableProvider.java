/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.uwo.csd.cs2212.team9;

import java.util.LinkedList;
import java.util.Collection;
import java.util.HashMap;

/**
 * 
 * @author rdolatab
 */
public class DeliverableProvider {
	public static Collection<Deliverable> loadDeliverables() throws Exception {
		Collection<Deliverable> deliverables = new LinkedList<Deliverable>();

		/* still working on this */
		Deliverable a1 = new Deliverable("Assignment 1", "Assignment", "25");
		Deliverable a2 = new Deliverable("Assignment 2", "Assignment", "25");
		Deliverable e1 = new Deliverable("Midterm", "Exam", "20");
		Deliverable e2 = new Deliverable("Final", "Exam", "30");
		a1.setGrade(80);
		a1.setClassAvg(75);
		a2.setGrade(70);
		a2.setClassAvg(80);
		e1.setGrade(90);
		e1.setClassAvg(75);
		e2.setGrade(95);
		e2.setClassAvg(80);
		deliverables.add(a1);
		deliverables.add(a2);
		deliverables.add(e1);
		deliverables.add(e2);
		return deliverables;
	}
}