package data.pojo;

import java.util.HashMap;

public class Datum {
	private HashMap<String, Object> properties = new HashMap<>();

	public void setProperty(String name, Object value) {
		properties.put(name, value);
	}

	public Object get(String name) {
		return properties.get(name);
	}
}
