package messenger;

public class Parameter {
	public Parameter(String name) {
		this.name = name;
	}
	
	public Parameter(String name, String value) {
		this.name = name;
		this.value = value;
	}

	String name;
	String value;
}