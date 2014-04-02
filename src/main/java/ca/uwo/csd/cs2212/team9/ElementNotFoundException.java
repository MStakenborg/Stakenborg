package ca.uwo.csd.cs2212.team9;

/**
 * Thrown when we try to remove an item not present in a list.
 * 
 * @author kmcguin
 * 
 */
@SuppressWarnings("serial")
public class ElementNotFoundException extends Exception {

	public ElementNotFoundException(String message) {
		super(message);
	}

}
