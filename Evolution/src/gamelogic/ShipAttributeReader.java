package gamelogic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.regex.Pattern;

public class ShipAttributeReader {
	public static final String attFile = "..\\..\\Ship.attribute";
	private static HashMap<String, ShipAttribute> shipAtts = 
		new HashMap<String, ShipAttribute>();
	
	static {
		try {
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(Class.forName("gameLogic.ShipAttributeReader").getResourceAsStream(
					attFile)));
			String str;
			while (null != (str = reader.readLine())) {
				str = str.trim();
				if (str.indexOf("//") == 0){
					continue;
				}
				
				Pattern p = Pattern.compile("[\f\n\r\t]+");				
				String[] atts = p.split(str);
				ShipAttribute shipAtt = new ShipAttribute();
				shipAtt.shipName = atts[0].trim();
				shipAtt.structuralIntegrity = Integer.parseInt(atts[1].trim());
				shipAtt.shieldPower = Integer.parseInt(atts[2].trim());
				shipAtt.weaponPower = Integer.parseInt(atts[3].trim());
				shipAtt.cargoCapacity = Integer.parseInt(atts[4].trim());
				shipAtt.baseSpeed = Integer.parseInt(atts[5].trim());
				shipAtt.fuelConsumption = Integer.parseInt(atts[6].trim());
				shipAtt.type = Integer.parseInt(atts[7].trim());
				if (atts.length >= 9)
					shipAtt.shiftSpeed = Integer.parseInt(atts[8].trim());
				if (atts.length == 10)
					shipAtt.shiftConsumption = Integer.parseInt(atts[9].trim());
				shipAtts.put(shipAtt.shipName, shipAtt);
			}
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static HashMap<String, ShipAttribute> getShipAttributes() {
		return shipAtts;
	}
	
//	public static void main(String[] args)
//	{
//		HashMap<String, ShipAttribute> atts = new ShipAttributeReader().getShipAttributes();
//		for (int i = 0; i<atts.length; i++)
//		{
//			System.out.println(
//
//			atts[i].shipName + "\t\t\t" +
//			atts[i].structuralIntegrity + "\t\t\t" +
//			atts[i].shieldPower + "\t\t\t" +
//			atts[i].weaponPower + "\t\t\t" +
//			atts[i].cargoCapacity + "\t\t\t" +
//			atts[i].baseSpeed + "\t\t\t" +
//			atts[i].fuelConsumption);
//		}
//	}
//	
}
