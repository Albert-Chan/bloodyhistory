package parser;
import event.EventFactory;
import event.IMinaryEvent;
import gamelogic.Constants;
import gamelogic.Planet;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import messenger.Operation;
import network.Response;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;




public class OverViewManager {
	public static final String EVENTS = "事件";
	
	public OverViewManager(Operation conv, String session, String cookie, String PHPSESSID) {
//		if (null == cpMap)
//		{
//			cpMap = new Coordinate[Constants.MAX_COLONY_NUM];
//			new ColoniesMapLoader().load();
//		}
		
		Response response = conv.overView(session, cookie, PHPSESSID);
		System.out.println(new String (response.getHttpContent() ));
		ByteArrayInputStream bats;
		try {
			bats = new ByteArrayInputStream(new String(response
					.getHttpContent()).getBytes("utf8"));
			parse(bats);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	private int metal;
	private int crystal;
	private int deuterium;
	private int darkMatter;
	private int energy;
	private int energyGap;
	
	private static Planet[] cpMap = null;
	
		
	public List getEvents(){
		return new ArrayList();
	}
	
	private String getProperty( Node node, String propertyName )
	{
		if ( null == node )
			return null;
		NamedNodeMap atts = node.getAttributes( );
		if ( null != atts )
		{
			Node property = atts.getNamedItem( propertyName );
			if ( null != property )
				return property.getNodeValue( );
		}
		return null;
	}
	
	private void parse(InputStream in) {
		HTMLTextParser parser = new HTMLTextParser();
		Document doc = parser.parseHTML(in);
		Element documentElement = doc.getDocumentElement();

		NodeList tables = documentElement.getElementsByTagName("table");
		for (int i = 0; i < tables.getLength(); i++) {
			Node table = tables.item(i);
			if ("resources".equalsIgnoreCase(getProperty(table, "id"))) {
				// ================+++++++ pictures ++++++++ headers +++++++
				// content
				Node resourcesRow = table.getFirstChild().getNextSibling()
						.getNextSibling();

				Node metal = resourcesRow.getFirstChild();
				setMetal(Integer.parseInt(metal.getTextContent().replace(".",
						"")));
				Node crystal = metal.getNextSibling();
				setCrystal (Integer.parseInt(crystal.getTextContent().replace(".", "")));
				Node deuterium = crystal.getNextSibling();
				setDeuterium (Integer.parseInt(deuterium.getTextContent().replace(".", "")));
				
				Node darkMatter = deuterium.getNextSibling();
				setDarkMatter(Integer.parseInt(darkMatter.getTextContent().replace(".", "")));
				//String[] energyStr = energy.getTextContent().split("/");
				//setEnergy(Integer.parseInt(energyStr[1]));
				//setEnergyGap(Integer.parseInt(energyStr[0]));
			}
			if ("519".equalsIgnoreCase(getProperty(table, "width"))) {
				boolean eventsCheck = false;
				Node row = table.getFirstChild();
				while (null != (row = row.getNextSibling())) {
					if (EVENTS.equals(row.getTextContent().trim())) {
						eventsCheck = true;
						continue;
					}
					if (eventsCheck) {
						String eventClass = getProperty(row, "class");
						IMinaryEvent event = EventFactory.createEvent(eventClass);
						row.getFirstChild();
						int leftSeconds = Integer.parseInt(getProperty(row
								.getFirstChild(), "title"));
						event.setActionTime(leftSeconds*1000);
						String eventString = row.getTextContent();
						event.parseEventString(eventString);
					}
				}
			}
		}
	}

	public int getCrystal() {
		return crystal;
	}

	public void setCrystal(int crystal) {
		this.crystal = crystal;
	}

	public int getDeuterium() {
		return deuterium;
	}

	public void setDeuterium(int deuterium) {
		this.deuterium = deuterium;
	}

	public int getMetal() {
		return metal;
	}

	public void setMetal(int metal) {
		this.metal = metal;
	}

	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
	}

	public int getEnergyGap() {
		return energyGap;
	}

	public void setEnergyGap(int energyGap) {
		this.energyGap = energyGap;
	}
	
	class ColoniesMapLoader {
		private static final String tacticsFile = "..\\..\\SimpleTactics.txt";
		public void load() {
			try {
				
				BufferedReader reader = new BufferedReader(new InputStreamReader(
						getClass().getResourceAsStream(tacticsFile)));
				String line;
				int counter = 0;
				while ((line = reader.readLine()) != null) {
					Pattern p = Pattern.compile("\t+");				
					String[] subString = p.split(line);
					
					assert (subString.length == 3);
					String cp  = subString[0];
					String planetName = subString[1];
					String coordinate = subString[2];
					if (counter < Constants.MAX_COLONY_NUM) {
						OverViewManager.cpMap[counter++] = new Planet(
								coordinate, cp, planetName);
					}
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		private String getEnvirVar(String key) throws IOException {
			
			if ( key == null || key.length() < 1) {
				return null;
			} else {
				
				String value = System.getProperty(key);
				if ( value == null ) {
					String OS = System.getProperty("os.name").toLowerCase();
					Process p = null;
					if (OS.indexOf("windows") > -1) {
						p = Runtime.getRuntime().exec("cmd /c set"); 
					} else if (OS.indexOf("linux") > -1 || OS.indexOf("aix") > -1
							|| OS.indexOf("unix") > -1) {
						p = Runtime.getRuntime().exec("/bin/sh -c set");
					}
					BufferedReader br = new BufferedReader(new InputStreamReader(p
							.getInputStream()));
					String line;
					while ((line = br.readLine()) != null) {
						int i = line.indexOf("=");
						if (i > -1) {
							if (key.equalsIgnoreCase(line.substring(0, i))) {
								value = line.substring(i + 1);
								break;
							}
						}
					}
				}
				return value;
			}
			
		}
	}

	public int getDarkMatter() {
		return darkMatter;
	}

	public void setDarkMatter(int darkMatter) {
		this.darkMatter = darkMatter;
	}

}

