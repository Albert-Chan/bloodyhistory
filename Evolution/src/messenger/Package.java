package messenger;

public class Package {
	private String name;
	private String[] params;
	private String content;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String[] getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params.split(",");
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
