package moon1139.exception;

public class CommandNotFoundException extends Exception{

	private static final long serialVersionUID = 1L;

	public CommandNotFoundException(String errorMessage) {
		super(errorMessage);
	}
}
