package parser;

import java.util.ArrayList;
import java.util.HashMap;

public class Inspector {
	
	private PatternExtractor extractor = new PatternExtractor();
	public void getCPMap(String html) {
		ArrayList<HashMap<String, String>> planets = extractor.extract(html, "planet");
	}

	public void getEvents(String html) {
		extractor.extract(html, "event");
	}
}
