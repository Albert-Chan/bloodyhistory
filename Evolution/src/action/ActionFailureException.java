package action;

public class ActionFailureException extends Exception {

	private static final long serialVersionUID = 2097467577707053075L;

	public ActionFailureException() {
		super();
	}

	public ActionFailureException(String message) {
		super(message);
	}

	public ActionFailureException(String message, Throwable cause) {
		super(message, cause);
	}

	public ActionFailureException(Throwable cause) {
		super(cause);
	}
}
