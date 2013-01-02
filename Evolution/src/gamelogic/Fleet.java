package gamelogic;

import java.util.HashMap;

public class Fleet {
	private HashMap<Integer, Integer> ships = new HashMap<Integer, Integer>();
	private HashMap<Integer, Integer> maxShips = null;

	public Fleet(HashMap<Integer, Integer> maxShips) {
		this.maxShips = maxShips;
		for (int type : maxShips.keySet()) {
			ships.put(type, 0);
		}
	}

	/**
	 * Adds a ship type into a fleet.
	 * 
	 */
	public boolean add(int type, int number) {
		// if (ships.containsKey(type)) {
		// int existNumber = ships.get(type);
		// number += existNumber;
		// }
		if (!maxShips.containsKey(type) || maxShips.get(type) < number)
			return false;
		ships.put(type, number);
		return true;
	}

	public String allShips(String json) {
		return serialize(maxShips);
	}

	public String toString() {
		return serialize(ships);
	}

	private String serialize(HashMap<Integer, Integer> shipMap) {
		// am213=&am203=221&am209=
		StringBuilder sb = new StringBuilder();
		int counter = 0;
		for (int type : shipMap.keySet()) {
			int number = shipMap.get(type);
			sb.append("am").append(type);
			sb.append("=");
			if (number > 0) {
				sb.append(number);
			}
			counter++;
			if (counter != shipMap.size() - 1) {
				sb.append("&");
			}
		}
		return sb.toString();
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
