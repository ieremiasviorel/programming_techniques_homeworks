package Exceptions;

public class NoUserInputException  extends Exception {
	
	private static final long serialVersionUID = 1L;

	public NoUserInputException(String message) {
		super(message);
	}
}