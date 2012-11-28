package parser;

import gamelogic.Planet;

import java.util.ArrayList;
import java.util.HashMap;

public class Inspector {
	
	private PatternExtractor extractor = new PatternExtractor();
	public HashMap<String, Planet> getCPMap(String html) {
		ArrayList<HashMap<String, String>> planets = extractor.extract(html, "planet");
		HashMap<String, Planet> planetsMap = new HashMap<String, Planet>();
		
		for ( HashMap<String, String> planetPropMap : planets)
		{
			String coordinate = planetPropMap.get("coordinate");
			Planet planet = new Planet(planetPropMap);
			planetsMap.put(coordinate, planet);
		}
		return planetsMap;
	}

	public boolean attackAlert(String html)
	{
		ArrayList<HashMap<String, String>> alert = extractor.extract(html, "attackAlert");
		if (alert.isEmpty())
			return false;
		else
			return true;
	}
	
	public void getEvents(String html) {
		extractor.extract(html, "event");
	}
}
