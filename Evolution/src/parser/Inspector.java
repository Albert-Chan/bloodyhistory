package parser;

import gamelogic.Planet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Inspector {
	
	private PatternExtractor extractor = new PatternExtractor();
	public HashMap<String, Planet> getCPMap(String html) {
		ArrayList<HashMap<String, String>> planets = extractor.extract(html, "planet");
		HashMap<String, Planet> planetsMap = new HashMap<String, Planet>();
		if (planets.isEmpty())
		{
			return planetsMap;
		}
		for ( HashMap<String, String> planetPropMap : planets)
		{
			String coordinate = planetPropMap.get("coordinate");
			Planet planet = new Planet(planetPropMap);
			planetsMap.put(coordinate, planet);
		}
		
	}

	public void getEvents(String html) {
		extractor.extract(html, "event");
	}
}
