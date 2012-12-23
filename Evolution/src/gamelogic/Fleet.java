package gamelogic;

import java.util.ArrayList;
import java.util.List;

public class Fleet {
	private ArrayList<ShipBuddle> shipList = null;

	/**
	 * Adds a ship type into a fleet.
	 * 
	 */
	public void add(int type, int number) {
		if (shipList == null) {
			shipList = new ArrayList<ShipBuddle>();
		}
		shipList.add(new ShipBuddle(type, number));
	}

	public List<ShipBuddle> getAllShips() {
		return shipList;
	}

	public String serialize() {
		return null;
	}
}

class ShipBuddle {

	// Type Name
	// 202 Small Cargo Ship
	// 203 Large Cargo Ship
	// 204 Light Fighter
	// 205 Heavy Fighter
	// 206 Cruiser
	// 207 Battle Ship
	// 215 Battlecruiser
	// 213 Destroyer
	// 214 Death Star
	// 211 Bomber
	// 209 Recycler
	// 210 Espionage Probe
	// 212 Solar Satellite
	// 208 Colony Ship
	private int number;
	private int type;

	public ShipBuddle(int type, int number) {
		this.type = type;
		this.number = number;
	}

	public int getNumber() {
		return number;
	}

	public int getType() {
		return type;
	}

	// below is not used yet.
	ShipAttribute shipAtt;

	public ShipAttribute getShipAttribute() {
		return shipAtt;
	}

	public int getSpeed() {
		switch (shipAtt.type) {
		case 203:
		case 204:
		case 209:
		case 210:
			return shipAtt.baseSpeed
					* (1 + (int) (TechnologyLevelReader
							.getTechnologyLvl("Combustion Drive") * 0.1));
		case 202:
			return shipAtt.shiftSpeed
					* (1 + (int) (TechnologyLevelReader
							.getTechnologyLvl("Impulse Drive") * 0.2));
		case 206:
			return shipAtt.baseSpeed
					* (1 + (int) (TechnologyLevelReader
							.getTechnologyLvl("Impulse Drive") * 0.2));
		case 207:
			return shipAtt.baseSpeed
					* (1 + (int) (TechnologyLevelReader
							.getTechnologyLvl("Hyperspace Drive") * 0.3));
		default:
			return shipAtt.baseSpeed;
		}
	}

	public int getConsumption() {
		switch (shipAtt.type) {
		case 202:
			return shipAtt.shiftConsumption;

		default:
			return shipAtt.fuelConsumption;
		}
	}

}
