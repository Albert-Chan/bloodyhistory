package event;

import gamelogic.Coordinate;
import gamelogic.Fleet;

public abstract class MinaryEvent extends AbstractEvent {

	private Coordinate subject;
	private Coordinate object;
	private Fleet fleet;

	/**
	 * Gets the coordinate of the subject.
	 * 
	 * @return the coordinate of the subject.
	 */
	public Coordinate getSubject() {
		return subject;
	}

	public void setSubject(Coordinate subject) {
		this.subject = subject;
	}

	/**
	 * Gets the coordinate of the object.
	 * 
	 * @return the coordinate of the object.
	 */
	public Coordinate getObject() {
		return object;
	}

	public void setObject(Coordinate object) {
		this.object = object;
	}

	/**
	 * Gets the fleet relative to the event.
	 * 
	 * @return the fleet relative to the event.
	 */
	public Fleet getFleet() {
		return fleet;
	}

	public void setFleet(Fleet fleet) {
		this.fleet = fleet;
	}

}
