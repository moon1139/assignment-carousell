package moon1139.exception;

public class IllegalCommandArgumentException extends IllegalArgumentException{

	private static final long serialVersionUID = 1L;

	public IllegalCommandArgumentException(String errorMessage) {
		super(errorMessage);
	}
}
