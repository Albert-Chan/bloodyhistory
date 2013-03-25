package event;

public class EventGenerationException extends Exception {

	private static final long serialVersionUID = -3578274939222563539L;

	public EventGenerationException() {
		super();
	}

	public EventGenerationException(String message) {
		super(message);
	}

	public EventGenerationException(String message, Throwable cause) {
		super(message, cause);
	}

	public EventGenerationException(Throwable cause) {
		super(cause);
	}
}