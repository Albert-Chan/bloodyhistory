package gamelogic;
import java.util.HashMap;

public class ShipBuddle {

	// TODO to hack a ship type map.
	static HashMap<String, ShipAttribute> shipTypeMap = 
		ShipAttributeReader.getShipAttributes();
	
	int number;

	ShipAttribute shipAtt;
	
	public ShipBuddle(String shipName, int number)
	{
		this.shipAtt = shipTypeMap.get(shipName);
		this.number = number;
	}

	public int getNumber() {
		return number;
	}

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
