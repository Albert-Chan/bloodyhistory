package network;
public class Response {

	private byte[] httpContent;

	private String httpHeader;

	public byte[] getHttpContent() {
		return httpContent;
	}

	public void setHttpContent(byte[] httpContent) {
		this.httpContent = httpContent;
	}

	public String getHttpHeader() {
		return httpHeader;
	}

	public void setHttpHeader(String httpHeader) {
		this.httpHeader = httpHeader;
	}

}
