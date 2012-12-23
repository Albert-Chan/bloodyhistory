package gamelogic;

import java.util.HashMap;

public class Coordinate {
	public static final int TYPE_PLANET = 1;
	public static final int TYPE_DF = 2;
	public static final int TYPE_MOON = 3;

	private int galaxy;
	private int system;
	private int position;
	/**
	 * planet=1, DF=2, Moon=3
	 */
	private int type = TYPE_PLANET;

	private String belongTo;
	private String planetName;
	private String cp = null;

	public Coordinate(HashMap<String, String> propMap) {
		String coordStr = propMap.get("@@coord@");
		parse(coordStr);
		this.cp = propMap.get("@@cp@");
		this.planetName = propMap.get("@@planetName@");
	}

	public Coordinate(String planetCoord) {
		this(planetCoord, TYPE_PLANET);
	}

	public Coordinate(String planetCoord, int type) {
		parse(planetCoord);
		this.type = type;
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

	private void parse(String coordString) {
		if (null != coordString) {
			// remove [] quote.
			if (coordString.startsWith("[") && coordString.endsWith("]"))
				coordString = coordString
						.substring(1, coordString.length() - 1);
			String[] pos = coordString.split(":");
			if (null != pos && pos.length == 3) {
				galaxy = Integer.parseInt(pos[0]);
				system = Integer.parseInt(pos[1]);
				position = Integer.parseInt(pos[2]);
			}
		}
	}

}
