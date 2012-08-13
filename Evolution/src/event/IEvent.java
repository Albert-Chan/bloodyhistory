package event;

public interface IEvent {
	
	int UNKNOWN = -1;
	int OWN_ATTACK_FLIGHT = 1;
	int OWN_ATTACK_RETURN = 2;
	
	int getType();
	void setType(int type);
	
	int getWhen();
	void setWhen(int when);
	
	boolean equals(IEvent e);
	int getBatch();
}
