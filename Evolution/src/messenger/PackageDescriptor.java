package messenger;

import java.util.ArrayList;
import java.util.List;

public class PackageDescriptor {

	public String name;
	public List<Parameter> parameters = new ArrayList<Parameter>();
	public StringBuffer packageContent = new StringBuffer();;

	public PackageDescriptor(String name) {
		this.name = name;
	}

	public void addParameter(Parameter p) {
		parameters.add(p);
	}

	public List<Parameter> getParameters() {
		return parameters;
	}

	public void appendContent(String str) {
		packageContent.append(str).append("\r\n");
	}

	public String getPackage(Parameter... parameters) {
		String packageString = getContent();
		for (Parameter p : parameters) {
			packageString = packageString.replaceAll(p.name, p.value);
		}
		return packageString;
	}

	private String getContent() {
		return packageContent.toString();
	}

}

