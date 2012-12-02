package gamelogic;

import java.util.ArrayList;

public class Fleet {
	ArrayList<ShipBuddle> shipList = null;

	int speed;

	/**
	 * Adds a ship type to a fleet.
	 * 
	 */
	public void add(String shipName, int number) {
		if (shipList == null) {
			shipList = new ArrayList<ShipBuddle>();
		}
		shipList.add(new ShipBuddle(shipName, number));
	}

	public ArrayList<ShipBuddle> getAllships() {
		return shipList;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

}
