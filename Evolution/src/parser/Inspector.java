package parser;

import gamelogic.Coordinate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Inspector {

	private PatternExtractor extractor = new PatternExtractor();

	public List<Coordinate> getColonies(String html) {
		ArrayList<HashMap<String, String>> planets = extractor.extract(html,
				"planet");
		List<Coordinate> colonies = new ArrayList<Coordinate>();

		for (HashMap<String, String> planetPropMap : planets) {
			Coordinate planet = new Coordinate(planetPropMap);
			colonies.add(planet);
		}
		return colonies;
	}

	public boolean attackAlert(String html) {
		ArrayList<HashMap<String, String>> alert = extractor.extract(html,
				"attackAlert");
		if (alert.isEmpty())
			return false;
		else
			return true;
	}

	public void getEvents(String html) {
		extractor.extract(html, "event");
	}
}
