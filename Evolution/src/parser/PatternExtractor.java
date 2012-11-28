package parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternExtractor {
	private HashMap<String, PatternDescriptor> map = new HashMap<String, PatternDescriptor>();

	public PatternExtractor() {

		try {
			PatternDescriptorReader reader = new PatternDescriptorReader(
					"htmlPatterns.txt");
			map = reader.getPatternMap();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<HashMap<String, String>> extract(String input,
			String patternName) {
		PatternDescriptor pDescriptor = map.get(patternName);
		List<String> parameterNames = pDescriptor.getOrderedParameterNames();

		Pattern pattern = Pattern.compile(pDescriptor.getPattern());
		String pstr = "<div class=\"smallplanet\" id=\"planet-([0-9]{8})\">\n                        <a href=\"http://(uni\\d*\\.ogame\\.tw)/game/index\\.php\\?page=overview&cp=([0-9]{8})\"\n";
		pattern = Pattern.compile(pstr);
		Matcher matcher = pattern.matcher(input);

		ArrayList<HashMap<String, String>> extractedObjects = new ArrayList<>();

		while (matcher.find()) {
			HashMap<String, String> keyValueOfParams = new HashMap<>();
			String matched = matcher.group(0);
			for (int i = 1; i < matcher.groupCount(); i++) {
				String paramName = parameterNames.get(i - 1);
				if (!paramName.startsWith("@@!")) {
					keyValueOfParams.put(paramName, matcher.group(i));
				}
			}
			extractedObjects.add(keyValueOfParams);
		}
		return extractedObjects;
	}

}
