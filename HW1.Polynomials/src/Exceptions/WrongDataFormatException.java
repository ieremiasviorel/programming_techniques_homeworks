package Exceptions;

public class WrongDataFormatException extends Exception {

	private static final long serialVersionUID = 1L;

	public WrongDataFormatException(String message) {
		super(message);
	}
}