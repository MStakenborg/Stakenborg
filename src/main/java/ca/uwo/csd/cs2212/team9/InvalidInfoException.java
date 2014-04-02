package ca.uwo.csd.cs2212.team9;

/**
 * Thrown when the user tries to create or alter a course, student, or
 * deliverable without a requisite field.
 * 
 * @author kmcguin
 * 
 */
@SuppressWarnings("serial")
public class InvalidInfoException extends Exception {
	public InvalidInfoException(String message) {
		super(message);
	}
}
