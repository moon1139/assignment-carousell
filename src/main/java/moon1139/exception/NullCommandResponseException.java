package moon1139.exception;

public class NullCommandResponseException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public NullCommandResponseException (String errorMessage) {
		super(errorMessage);
	}
}
