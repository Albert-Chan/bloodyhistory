package parser;

import event.EventFactory;
import event.IEvent;
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
			Coordinate planet = new Coordinate(planetPropMap,
					Coordinate.TYPE_PLANET);
			colonies.add(planet);
		}
		return colonies;
	}

	public List<Coordinate> getMoons(String html) {
		ArrayList<HashMap<String, String>> moons = extractor.extract(html,
				"moon");
		List<Coordinate> moonList = new ArrayList<Coordinate>();

		for (HashMap<String, String> planetPropMap : moons) {
			Coordinate moon = new Coordinate(planetPropMap,
					Coordinate.TYPE_MOON);
			moonList.add(moon);
		}
		return moonList;
	}

	public boolean attackAlert(String html) {
		ArrayList<HashMap<String, String>> alert = extractor.extract(html,
				"attackAlert");
		if (alert.isEmpty())
			return false;
		else
			return true;
	}

	public List<IEvent> getEvents(String html) {
		ArrayList<HashMap<String, String>> events = extractor.extract(html,
				"event");
		List<IEvent> eventList = new ArrayList<IEvent>();

		for (HashMap<String, String> e : events) {
			IEvent event = EventFactory.createEvent(e);
			eventList.add(event);
		}
		return eventList;
	}

	public String getAllShipsJson(String html) {
		ArrayList<HashMap<String, String>> json = extractor.extract(html,
				"allShipsJson");
		if (json.size() != 1)
			return null;
		HashMap<String, String> propMap = json.get(0);

		return propMap.get("@@shipsJson@");
	}

	public String getFleetToken(String html) {
		ArrayList<HashMap<String, String>> token = extractor.extract(html,
				"token");
		if (token.size() != 1)
			return null;
		HashMap<String, String> propMap = token.get(0);

		return propMap.get("@@token@");
	}

	public boolean isTargetExistent(String ajxReponse) {
		return "0".equals(ajxReponse) ? true : false;
	}

}
