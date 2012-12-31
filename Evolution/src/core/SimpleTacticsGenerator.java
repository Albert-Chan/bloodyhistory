package core;

import gamelogic.Coordinate;
import gamelogic.Fleet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;


public class SimpleTacticsGenerator {
	private static HashMap<String, Coordinate> nameCpMap = new HashMap<String, Coordinate>();
	
	private static final String tacticsFile = "..\\..\\SimpleTactics.txt";
	
	LinkedList<Tactics> tacticsList = new LinkedList<Tactics>();
	public SimpleTacticsGenerator() {
		parse();
	}

	private static Coordinate getColonyCoordinateByName(String name) {
		return nameCpMap.get(name);
	}
	
	private void parse() {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					getClass().getResourceAsStream(tacticsFile)));
			String str;
			String srcName = null;
			
			while (null != (str = reader.readLine())) {
				str = str.trim();
				if (str.indexOf("//") == 0) {
					continue;
				}
				// this is a source coordinate
				if (str.startsWith("[") && str.endsWith("]")) {
					srcName = str.substring(1, str.length() - 1);
				} else {
					// add a target and the fleet to this target
					String[] strArray = str.split(";");
					Tactics tactics = new Tactics();
					tactics.setSource(getColonyCoordinateByName(srcName));
					tactics.setTarget(new Coordinate(strArray[0]));
					if (strArray.length == 1) {
						Fleet fleet = new Fleet();
						//fleet.add("Small Cargo Ship", 10);
						tactics.setFleet(fleet);
					} else {
						strArray = strArray[1].split(",");
						Fleet fleet = new Fleet();
						for (int i = 0; i < strArray.length; i++) {
							String[] fleetInfo = strArray[i].split("=");
//							fleet.add(fleetInfo[0], Integer
//									.parseInt(fleetInfo[1]));
						}
						tactics.setFleet(fleet);
					}
					tacticsList.add(tactics);
				}
			} 	
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static int index = -1;
	public Tactics getNextTactics(){
		int size = tacticsList.size();
		index++;
		index = index%size;
		return tacticsList.get(index);
	}
	
	public Tactics getCurrentTactics(){
		return tacticsList.get(index);
	}
}


