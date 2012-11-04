package network;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Response {

	private byte[] httpContent;

	private String httpHeader;

	private HashMap<String, List<String>> requestProperties = new HashMap<String, List<String>>();

	private HashMap<String, String> cookie = new HashMap<String, String>();

	public byte[] getHttpContent() {
		return httpContent;
	}

	public void setHttpContent(byte[] httpContent) {
		this.httpContent = httpContent;
	}

	public String getHttpHeader() {
		return httpHeader;
	}

	public HashMap<String, List<String>> getRequestProperties() {
		return requestProperties;
	}

	public HashMap<String, String> getCookies() {
		return cookie;
	}

	public void setHttpHeader(String httpHeader) {
		this.httpHeader = httpHeader;
		parseRequestProperty(httpHeader);
	}

	private void parseRequestProperty(String httpHeader) {

		String[] lines = httpHeader.split("\r\n");

		if (lines != null && lines.length > 0) {

			for (String line : lines) {
				String[] keyValuePair = line.split(": ");
				if (keyValuePair.length != 2)
					continue;
				String key = keyValuePair[0];
				String value = keyValuePair[1];
				String[] values = value.split("; ");

				List<String> valuesInMap = null;
				if (requestProperties.containsKey(key)) {
					valuesInMap = requestProperties.get(key);
				} else {
					valuesInMap = new ArrayList<String>();
					requestProperties.put(key, valuesInMap);
				}
				for (String v : values) {
					if (!valuesInMap.contains(v)) {
						valuesInMap.add(v);
					}
				}
			}
		}
		extractCookie();
	}

	private void extractCookie() {
		List<String> cookies = requestProperties.get("Set-Cookie");
		if (null == cookies)
			return;

		for (String cookieString : cookies) {
			String[] pair = cookieString.split("=");
			if (pair.length == 2) {
				String key = pair[0];
				String value = pair[1];
				cookie.put(key, value);
			}
		}
	}

}
