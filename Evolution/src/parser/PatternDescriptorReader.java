package parser;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class PatternDescriptorReader {
	
	private HashMap<String, PatternDescriptor> map = new HashMap<String, PatternDescriptor>();

	public PatternDescriptorReader(String path) throws IOException{
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(
					path), "UTF-8"));

			Visitor visitor = new Visitor();
			String line = null;
			while ((line = br.readLine()) != null) {
				visitor.visit(line);
			}
		} finally {
			if (null != br) {
				br.close();
			}
		}
	}
	
	public HashMap<String, PatternDescriptor> getPatternMap()
	{
		return map;
	}
	
	class Visitor {
		public void visit(String line) {
			if (line.startsWith("[")) {
				startPattern(line);
			} else if (line.startsWith("/[")) {
				endPattern(line);
			} else if (line.startsWith("@@")) {
				handleParam(line);
			} else {
				handlePatternString(line);
			}
		}

		

		private String currentPatternName = null;
		private PatternDescriptor currentPattern = null;

		private void startPattern(String line) {
			String patternName = line.substring(1, line.length() - 2);
			if (map.containsKey(patternName)) {
				System.err.println("duplicate pattern name.");
			} else {
				currentPattern = new PatternDescriptor(patternName);
				map.put(patternName, currentPattern);
				currentPatternName = patternName;
			}
		}

		private void endPattern(String line) {
			String patternName = line.substring(2, line.length() - 2);
			if (currentPatternName != null
					&& currentPatternName.equals(patternName)) {
				currentPatternName = null;
				currentPattern = null;
			} else {
				System.err.println("pattern name does not match.");
			}
		}

		private void handleParam(String line) {
			if (currentPattern == null) {
				System.err.println("Not in a pattern.");
			}
			String[] keyValue = line.split("=");
			Parameter p = new Parameter(keyValue[0], keyValue[1]);
			currentPattern.addParameter(p);
		}

		private void handlePatternString(String line) {
			if (currentPattern == null) {
				System.err.println("Not in a pattern.");
			}
			currentPattern.appendContent(line);
		}

}



}
