package parser;

import messenger.Operation;
import network.Response;

public class LoginManager {
	//private static final String PHPSESSID_TAG = "Set-Cookie: PHPSESSID=";

	//private static final String COOKIE_TAG = "Set-Cookie: U_";
	
	private static final String COOKIE_TAG = "Set-Cookie: ";
	
	//private static final String PRSESS_TAG_SUBFIX = "_prsess=";

	private static final String SESSION_TAG = "session=";

	private String PHPSESSID;

	private String cookie;

	private String prsess;
	
	private String session;
	
	private String userName;

	public String getCookie() {
		return cookie;
	}

	public void setCookie(String cookie) {
		this.cookie = cookie;
	}

	public String getPHPSESSID() {
		return PHPSESSID;
	}

	public void setPHPSESSID(String phpsessid) {
		this.PHPSESSID = phpsessid;
	}

	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}

	public LoginManager(Operation conv, String userName, String pass) {
		this.userName = userName;
		Response response = conv.login(userName, pass);
		if (null == response) {
			// Log this error and try again.
		}
		else{
			parseCookie(response.getHttpHeader());
			parseSession(response.getHttpContent());	
		}
	}

/*
HTTP/1.1 200 OK
Date: Sat, 12 Jan 2008 04:57:45 GMT
Server: Apache
Set-Cookie: PHPSESSID=8be2d9e907c04ed33a8c152cac466665; path=/
Expires: Thu, 19 Nov 1981 08:52:00 GMT
Cache-Control: no-store, no-cache, must-revalidate, post-check=0, pre-check=0
Pragma: no-cache
Set-Cookie: chaordic_prsess=9691c9d09a20cfa8a6cb123e4151e4f3; expires=Sun, 13 Jan 2008 04:57:46 GMT; path=/
Set-Cookie: U_cn5:chaordic:024143dd21d1b3148710383fb27aabf6=U_cn5%3Achaordic%3A024143dd21d1b3148710383fb27aabf6; expires=Sun, 13 Jan 2008 04:57:46 GMT; path=/
Vary: Accept-Encoding
Content-Encoding: gzip
Content-Length: 119
Connection: close
Content-Type: text/html; charset=GB2312
 */
	
	private void parseCookie(String httpHeader) {
		int startCookie = httpHeader.indexOf(COOKIE_TAG);
		if (startCookie == -1)
			return;
		int endCookie = httpHeader.indexOf("; ", startCookie);
		if (endCookie == -1)
			return;
		String cookieSection = httpHeader.substring(startCookie + COOKIE_TAG.length(),
				endCookie);
		StringBuffer sb = new StringBuffer(cookieSection);
		while (true)
		{
			startCookie = httpHeader.indexOf(COOKIE_TAG, endCookie);
			if (startCookie == -1)
				break;
			endCookie = httpHeader.indexOf("; ", startCookie);
			if (endCookie == -1)
				break;
			sb.append("; ");
			cookieSection = httpHeader.substring(startCookie + COOKIE_TAG.length(),
					endCookie);
			sb.append(cookieSection);
		}
		cookie = sb.toString();
	}

	private void parseSession(byte[] input) {
		String str = new String(input);
		int startSession = str.indexOf(SESSION_TAG);
		int endSession = str.indexOf("&", startSession);
		session = str.substring(startSession + SESSION_TAG.length(), endSession);
	}

	public String getPrsess() {
		return prsess;
	}

	public void setPrsess(String prsess) {
		this.prsess = prsess;
	}
}
