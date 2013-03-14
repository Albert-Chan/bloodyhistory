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
	private String name;
	private String cp = null;

	public Coordinate(HashMap<String, String> propMap) {
		this(propMap, TYPE_PLANET);
	}

	public Coordinate(HashMap<String, String> propMap, int type) {
		String coordStr = propMap.get("@@coord@");
		parse(coordStr);
		this.type = type;
		this.cp = propMap.get("@@cp@");
		this.name = propMap.get("@@name@");
	}

	public Coordinate(String coord) {
		this(coord, TYPE_PLANET);
	}

	public Coordinate(String coord, int type) {
		parse(coord);
		this.type = type;
	}

	public Coordinate(String coord, String type) {
		parse(coord);
		if ("planet".equalsIgnoreCase(type)) {
			this.type = TYPE_PLANET;
		}
		if ("tf".equalsIgnoreCase(type)) {
			this.type = TYPE_DF;
		}
		if ("moon".equalsIgnoreCase(type)) {
			this.type = TYPE_MOON;
		}
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(galaxy);
		sb.append(":");
		sb.append(system);
		sb.append(":");
		sb.append(position);
		if (type == TYPE_MOON)
			sb.append("M");
		if (type == TYPE_DF)
			sb.append("DF");
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
