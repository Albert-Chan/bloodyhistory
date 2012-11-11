package parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternDescriptor {

	public String name;
	public List<Parameter> parameters = new ArrayList<Parameter>();
	public StringBuffer patternContent = new StringBuffer();;
	public List<String> orderedParameters = new ArrayList<String>();

	public PatternDescriptor(String name) {
		this.name = name;
	}

	public void addParameter(Parameter p) {
		parameters.add(p);
	}

	public List<Parameter> getParameters() {
		return parameters;
	}

	/**
	 * only calls this method when appendContent() is finished.
	 * 
	 * @return
	 */
	public List<String> getOrderedParameterNames() {
		return orderedParameters;
	}

	public void appendContent(String str) {
		str = str.trim();
		patternContent.append(str);
		
		extractOrderedParameters(str);
	}
	
	public String getPattern() {
		String patternString = getContent();

		for (Parameter p : parameters) {
			patternString.replace(p.name, "(" + p.value + ")");
		}
		return patternString;
	}

	private String getContent() {
		return patternContent.toString();
	}
	
	private void extractOrderedParameters(String str)
	{
		Pattern pattern = Pattern.compile("@@.*@");
		Matcher matcher = pattern.matcher(str);
		while (matcher.find())
			orderedParameters.add(matcher.group(0));
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