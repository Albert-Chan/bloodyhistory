package action;

import java.util.GregorianCalendar;
import java.util.TimeZone;

public class ActionContext {
	String loginServer = "www.ogame.tw";
	String gameServer = "uni13.ogame.tw";
	
	String cookie;

	public String getCookie() {
		return cookie;
	}

	public void setCookie(String cookie) {
		this.cookie = cookie;
	}

	public String getCurrentDateTime() {
		GregorianCalendar calendar = new GregorianCalendar(
				TimeZone.getTimeZone("GMT+8"));
		java.text.SimpleDateFormat f = new java.text.SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		return f.format(calendar.getTime());
	}
}
