package gamelogic;

import java.util.HashMap;

public class Planet {
	int galaxy;

	int system;

	int planet;
	
	/**
	 * planet=1, DF=2, Moon=3
	 */
	int type = 1;

	String belongTo;

	String position;
	
	String planetName;
	
	String cp = null;
	
	public Planet(HashMap<String, String> propMap)
	{
		
	}
	

	public Planet(String position) {
		this.position = position;
		if (null != position) {
			String[] pos = position.split(":");
			if (null != pos && pos.length == 3) {
				galaxy = Integer.parseInt(pos[0]);
				system = Integer.parseInt(pos[1]);
				planet = Integer.parseInt(pos[2]);
			}
		}
	}
	
	public Planet(String position, String player) {
		this(position);
		this.belongTo = player;
	}
	
	public Planet(String position, String cp, String name) {
		this(position);
		this.cp = cp;
		this.planetName = name;
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

	public int getPlanet() {
		return planet;
	}

	public void setPlanet(int planet) {
		this.planet = planet;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public int getSystem() {
		return system;
	}

	public void setSystem(int system) {
		this.system = system;
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
	
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		sb.append(galaxy);
		sb.append(":");
		sb.append(system);
		sb.append(":");
		sb.append(planet);
		return sb.toString();
	}

}
