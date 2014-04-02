package ca.uwo.csd.cs2212.team9;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

@SuppressWarnings("unused")
public class DeliverableTest {

	private Deliverable deliverable;

	@Before
	public void setup() {
		try {
			deliverable = new Deliverable("asn1", "Assignment", Double.toString(50.0));
		} catch (InvalidInfoException e) {
			e.printStackTrace();
		}
	}

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	/* Creation tests */
	@Test
	public void testCreationEmptyName() throws InvalidInfoException {
		/*
		 * Attempts to create a deliverable with a blank name. Passes if an
		 * InvalidInfoException is thrown
		 */
		thrown.expect(InvalidInfoException.class);
		thrown.expectMessage("Deliverables must have a name.");
		Deliverable del = new Deliverable("", "Assignment", Double.toString(50.0));
	}
	
		
	@Test
	public void testCreationEmptyType() throws InvalidInfoException {
		/*
		 * Attempts to create a deliverable with a blank type. Passes if an
		 * InvalidInfoException is thrown
		 */
		thrown.expect(InvalidInfoException.class);
		thrown.expectMessage("Deliverables must have a type.");
		Deliverable del = new Deliverable("asn1", "", Double.toString(50.0));
	}

	@Test
	public void testCreationOutOfBoundsWeight() throws InvalidInfoException {
		/*
		 * Attempts to create a deliverable with a weight of -1. Passes if an
		 * InvalidInfoException is thrown
		 */
		thrown.expect(InvalidInfoException.class);
		thrown.expectMessage("Deliverables must have a weight in [0, 100].");
		Deliverable del = new Deliverable("asn1", "Assignment", Double.toString(-1.0));
	}
	
	@Test
	public void testCreationNaNWeight() throws InvalidInfoException{
	/*
	 * Attempts to create a deliverable with a non-number weight.
	 */
		thrown.expect(InvalidInfoException.class);
		thrown.expectMessage("Deliverable weight must be a number.");
		Deliverable del = new Deliverable("asn1", "Assignment", "letters");
	}

	/* Editing tests */
	@Test
	public void testSetEmptyName() throws InvalidInfoException {
		/*
		 * Attempts to delete the name of a deliverable. Passes if an
		 * InvalidInfoException is thrown
		 */
		thrown.expect(InvalidInfoException.class);
		thrown.expectMessage("Deliverables must have a name.");
		deliverable.setName("");
	}

	@Test
	public void testSetValidName() throws InvalidInfoException{
	/*
	 * Sets a valid name.
	 */
	 deliverable.setName("assignment");
	 Assert.assertTrue(deliverable.getName().equals("assignment"));
	}
	
	@Test
	public void testSetEmptyType() throws InvalidInfoException {
		/*
		 * Attempts to delete the type of a deliverable. Passes if an
		 * InvalidInfoException is thrown
		 */
		thrown.expect(InvalidInfoException.class);
		thrown.expectMessage("Deliverables must have a type.");
		deliverable.setType("");
	}

	@Test
	public void testSetValidType() throws InvalidInfoException{
	/*
	 * Sets a valid name.
	 */
	 deliverable.setType("assignment");
	 Assert.assertTrue(deliverable.getType().equals("assignment"));
	}
	
	@Test
	public void testSetNegativeOutOfBoundsWeight() throws InvalidInfoException {
		/*
		 * Attempts to set the weight of a deliverable to -1. Passes if an
		 * InvalidInfoException is thrown
		 */
		thrown.expect(InvalidInfoException.class);
		thrown.expectMessage("Deliverables must have a weight in [0, 100].");
		deliverable.setWeight(-1);
	}
	
	@Test
	public void testSetPositiveOutOfBoundsWeight() throws InvalidInfoException {
		/*
		 * Attempts to set the weight of a deliverable to -1. Passes if an
		 * InvalidInfoException is thrown
		 */
		thrown.expect(InvalidInfoException.class);
		thrown.expectMessage("Deliverables must have a weight in [0, 100].");
		deliverable.setWeight(101);
	}
	
	@Test
	public void testSetValidWeight() throws InvalidInfoException{
	/*
	 * Sets a valid name.
	 */
	 deliverable.setWeight(32.0);
	 Assert.assertTrue(deliverable.getWeight() == 32.0);
	}
	
	/*Grade tests*/
	
	@Test
	public void testSetValidGrade() throws InvalidInfoException{
		deliverable.setGrade(50.0);
		Assert.assertTrue(deliverable.getGrade() == 50.0);
	}
	
}
