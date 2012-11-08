package parser;

import java.util.ArrayList;
import java.util.List;

public class Pattern {

	public String name;
	public List<Parameter> pList;
	public StringBuffer patternContent;

	public Pattern(String name) {
		this.name = name;
	}

	public void addParam(Parameter p) {
		if (pList == null) {
			pList = new ArrayList<Parameter>();
		}
		pList.add(p);
	}
	
	public List<Parameter> getParams()
	{
		return pList;
	}
	
	public void appendContent(String str)
	{
		str = str.trim();
		if (patternContent == null)
		{
			patternContent = new StringBuffer();
		}
		patternContent.append(str);
	}
	
	public String getContent()
	{
		return patternContent.toString();
	}

}

class Parameter {
	public Parameter(String name, String value) {
		this.name = name;
		this.value = value;
	}

	String name;
	String value;
}