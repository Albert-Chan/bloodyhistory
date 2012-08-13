package parser;


import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import messenger.Operation;
import network.Response;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;





public class BuildingInfoCollector {

	public static final String LEVEL = "(等级 ";
	public static final String RIGHT_BRACKET = ")";
	
	public ArrayList buildings = new ArrayList();
	
	public BuildingInfoCollector(Operation conv, String session, String PHPSESSID) {
		Response response = conv.goBuild(session, PHPSESSID);

		try {
			String str = new String(response.getHttpContent(),"gb2312");
			ByteArrayInputStream bats = new ByteArrayInputStream(str.getBytes("UTF-8"));
			parse(bats);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
			if ("530".equalsIgnoreCase(getProperty(table, "width")))
			{
				NodeList rows = table.getChildNodes();
				// Gets the column 2
				for( int j = 0; j< rows.getLength(); j++){
					Node row = rows.item(j);
					
					Node buildingIndex = row.getFirstChild();
					String indexStr = getProperty(buildingIndex.getFirstChild(), "href");
					int index = Integer.parseInt(indexStr.substring( indexStr.indexOf("gid=")+ 4, indexStr.length()));
					
					String buildingName = buildingIndex.getNextSibling().getFirstChild().getTextContent();
					
					String buildInfo = row.getTextContent();
					
					int buildLevel = getIntBetween(buildInfo, LEVEL, RIGHT_BRACKET);
					
				}
				
			}
								
		}
	}
	
	private int getIntBetween(String original, String prefix, String subfix)
	{
		String intStr = getStringBetween( original, prefix, subfix);
		intStr = intStr.replace(".", "");
		return Integer.parseInt(intStr);
	}
	
	
	private String getStringBetween(String original, String prefix, String subfix)
	{
		int beginIndex = original.indexOf(prefix)+prefix.length();
		if( -1 == beginIndex)
		{
			return null;
		}
		int endIndex = original.indexOf(subfix);
		if (-1 == endIndex)
		{
			return null;
		}
		if (endIndex <= beginIndex)
		{
			return null;
		}
		return original.substring(beginIndex, endIndex);
	}
}
