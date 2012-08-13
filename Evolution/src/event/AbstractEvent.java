package event;

public abstract class AbstractEvent implements IEvent {

	// if two events has the same property and exist in the same batch ID, they
	// will be regarded as two independent events; Otherwise, the later one will
	// be suppressed.
	public boolean equals(IEvent e) {
		return false;
	}

	private int batch = -1;

	public int getBatch() {
		return this.batch;
	}

	private int type = UNKNOWN;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	// The interval from the event being caught to the occurrence.
	private int when;

	public int getWhen() {
		return this.when;
	}

	public void setWhen(int when) {
		this.when = when;
	}

	public abstract AbstractEvent parse(String rawData);

}
