package gamelogic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.regex.Pattern;

public class TechnologyLevelReader {
	public static final String tLvlFile = "..\\..\\technology.level";
	private static HashMap<String, Integer> technologyLvl = 
		new HashMap<String, Integer>();
	
	static{
		try {
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(Class.forName("gameLogic.TechnologyLevelReader").getResourceAsStream(
					tLvlFile)));
			String str;
			while (null != (str = reader.readLine())) {
				str = str.trim();
				if (str.indexOf("//") == 0){
					continue;
				}
				
				Pattern p = Pattern.compile("=");				
				String[] atts = p.split(str);
				
				String tName = atts[0].trim();
				int tLevel = Integer.parseInt(atts[1]);

				technologyLvl.put(tName, new Integer(tLevel));
			}
			
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
			
		}
		catch (IOException e) {
			e.printStackTrace();
			
		}
	}
	
	public static int getTechnologyLvl(String tName) {
		return technologyLvl.get(tName).intValue();
	}
	
}
