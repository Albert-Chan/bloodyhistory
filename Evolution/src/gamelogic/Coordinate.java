package gamelogic;

import java.util.HashMap;

public class Coordinate {
	int galaxy;

	int system;

	int position;

	/**
	 * planet=1, DF=2, Moon=3
	 */
	int type = 1;

	String belongTo;

	String planetName;

	String cp = null;

	public Coordinate(String coordString) {
		if (null != coordString) {
			String[] pos = coordString.split(":");
			if (null != pos && pos.length == 3) {
				galaxy = Integer.parseInt(pos[0]);
				system = Integer.parseInt(pos[1]);
				position = Integer.parseInt(pos[2]);
			}
		}
	}

	public Coordinate(HashMap<String, String> propMap) {

	}

	public String getBelongTo() {
		return belongTo;
	}

	public void setBelongTo(String belongTo) {
		this.belongTo = belongTo;
	}

	public int getGalaxy() {
		return galaxy;
	}

	public void setGalaxy(int galaxy) {
		this.galaxy = galaxy;
	}

	public int getSystem() {
		return system;
	}

	public void setSystem(int system) {
		this.system = system;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public String getPlanetName() {
		return planetName;
	}

	public void setPlanetName(String planetName) {
		this.planetName = planetName;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(galaxy);
		sb.append(":");
		sb.append(system);
		sb.append(":");
		sb.append(position);
		return sb.toString();
	}

}
