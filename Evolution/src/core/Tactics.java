package core;

import gamelogic.Coordinate;
import gamelogic.Fleet;

public class Tactics
{
	private Coordinate source;
	private Coordinate target;
	private Fleet fleet;
	
	public Fleet getFleet() {
		return fleet;
	}
	public void setFleet(Fleet fleet) {
		this.fleet = fleet;
	}
	public Coordinate getTarget() {
		return target;
	}
	public void setTarget(Coordinate target) {
		this.target = target;
	}
	public Coordinate getSource() {
		return source;
	}
	public void setSource(Coordinate source) {
		this.source = source;
	}
}
