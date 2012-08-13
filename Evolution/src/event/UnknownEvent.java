package event;

public class UnknownEvent extends AbstractEvent {
	private String rawData;

	public String getRawData() {
		return rawData;
	}

	public UnknownEvent parse(String rawData) {
		return new UnknownEvent();
	}
	
}
