package parser;

import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Inspector {
	private HashMap<String, parser.Pattern> map = new HashMap<String, parser.Pattern>();

	public Inspector() {

		try {
			PatternReader reader = new PatternReader("./htmlPatterns.txt");
			map = reader.getPatternMap();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void inspect(String patternName, String input) {
		parser.Pattern pattern = map.get("planet");
		Pattern pattern = Pattern.compile("d.{1}e");
		Matcher matcher = pattern.matcher(html);
		while (matcher.find())
			System.out.println(matcher.group(0));

	}

	public void getCPMap(String html) {
		parser.Pattern pattern = map.get("planet");
		Pattern pattern = Pattern.compile("d.{1}e");
		Matcher matcher = pattern.matcher(html);
		while (matcher.find())
			System.out.println(matcher.group(0));

	}

	public void getEvents(String html) {

	}

}
