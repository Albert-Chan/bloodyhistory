package core;

import gamelogic.Planet;
import gamelogic.Fleet;

public class Tactics
{
	private Planet source;
	private Planet target;
	private Fleet fleet;
	
	public Fleet getFleet() {
		return fleet;
	}
	public void setFleet(Fleet fleet) {
		this.fleet = fleet;
	}
	public Planet getTarget() {
		return target;
	}
	public void setTarget(Planet target) {
		this.target = target;
	}
	public Planet getSource() {
		return source;
	}
	public void setSource(Planet source) {
		this.source = source;
	}
}
