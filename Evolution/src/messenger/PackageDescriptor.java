package messenger;

import java.util.ArrayList;
import java.util.List;

public class PackageDescriptor {

	public String name;
	public List<Parameter> parameters = new ArrayList<Parameter>();
	public StringBuffer header = new StringBuffer();
	public StringBuffer content = new StringBuffer();;

	public PackageDescriptor(String name) {
		this.name = name;
	}

	public void addParameter(Parameter p) {
		parameters.add(p);
	}

	public List<Parameter> getParameters() {
		return parameters;
	}

	public void appendHeader(String str) {
		header.append(str).append("\r\n");
	}

	public void appendContent(String str) {
		content.append(str);
	}

	public String getPackage(Parameter... parameters) {
		String content = getContent();
		for (Parameter p : parameters) {
			content = content.replaceAll(p.name, p.value);
		}

		String header = getHeader();
		for (Parameter p : parameters) {
			header = header.replaceAll(p.name, p.value);
		}
		header = header.replaceAll("@@length@",
				Integer.toString(content.length()));

		String packageString = header + "\r\n" + content;
		return packageString;
	}

	private String getHeader() {
		return header.toString();
	}

	private String getContent() {
		return content.toString();
	}

}
