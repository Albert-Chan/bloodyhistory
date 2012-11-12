package event;

import gamelogic.Planet;
import gamelogic.Fleet;

public abstract class MinaryEvent extends AbstractEvent {

	private Planet subject;
	private Planet object;
	private Fleet fleet;

	/**
	 * Gets the coordinate of the subject.
	 * 
	 * @return the coordinate of the subject.
	 */
	public Planet getSubject() {
		return subject;
	}

	public void setSubject(Planet subject) {
		this.subject = subject;
	}

	/**
	 * Gets the coordinate of the object.
	 * 
	 * @return the coordinate of the object.
	 */
	public Planet getObject() {
		return object;
	}

	public void setObject(Planet object) {
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
