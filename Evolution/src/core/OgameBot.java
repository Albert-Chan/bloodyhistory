package core;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import network.HttpClient;
import network.Response;
import network.UngzipHandler;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import parser.HTMLTextParser;

public class OgameBot {

	private HttpClient client;

	public OgameBot(HttpClient client) {
		this.client = client;
	}

	/**
	 * 1.make connection to server 2.login 3.read from tactics 4.execute the
	 * tactics
	 */
	public static void main(String[] args) {
		HttpClient client = new HttpClient("uni108.ogame.tw", 80);
		client.registerResponseHandler(new UngzipHandler());

		OgameBot bot = new OgameBot(client);
		bot.login();
	}

	public void login() {
		Map<String, String> cookies = null;

		String msg1 = "GET http://uni108.ogame.tw/game/reg/login2.php?login=albert&pass=11111111&kid=&v=2 HTTP/1.1\r\n"
				+ "Host: uni108.ogame.tw\r\n"
				+ "Connection: keep-alive\r\n"
				+ "User-Agent: Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.1 Safari/537.11\r\n"
				+ "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\r\n"
				+ "Accept-Encoding: gzip,deflate,sdch\r\n"
				+ "Accept-Language: en-US,en;q=0.8,zh-CN;q=0.6,zh;q=0.4,zh-TW;q=0.2\r\n"
				+ "Accept-Charset: ISO-8859-1,utf-8;q=0.7,*;q=0.3\r\n\r\n";

		try {
			Response response = client.send(msg1);
			cookies = response.getCookies();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (null == cookies)
			return;

		StringBuffer strCookie = new StringBuffer();
		for (String cookieKey : cookies.keySet()) {
			if (cookieKey.startsWith("PHPSESSID")
					|| cookieKey.startsWith("prsess")
					|| cookieKey.startsWith("login")) {
				strCookie.append(cookieKey).append("=")
						.append(cookies.get(cookieKey)).append("; ");
			}
		}

		String msg2 = "GET http://uni108.ogame.tw/game/index.php?page=overview&kid=&PHPSESSID=03ed273f5985 HTTP/1.1\r\n"
				+ "Host: uni108.ogame.tw\r\n"
				+ "Connection: keep-alive\r\n"
				+ "User-Agent: Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.1 Safari/537.11\r\n"
				+ "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\r\n"
				+ "Accept-Encoding: gzip,deflate,sdch\r\n"
				+ "Accept-Language: en-US,en;q=0.8,zh-CN;q=0.6,zh;q=0.4,zh-TW;q=0.2\r\n"
				+ "Accept-Charset: ISO-8859-1,utf-8;q=0.7,*;q=0.3\r\n"
				+ "Cookie: " + strCookie.toString() + "\r\n\r\n";

		try {
			Response response = client.send(msg2);
			System.out.println(response.getHttpHeader());
			System.out.println(new String(response.getHttpContent(), "utf-8"));
			getHostileAndCPMap(response.getHttpContent());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private HTMLTextParser htmlParser = new HTMLTextParser();

	private HashMap<String, CP> cpMap = new HashMap<String, CP>();

	class CP {
		String coordinate;
		String cp;
		String name;
	}

	private void getHostileAndCPMap(String html) {
		String patternString = null;
		Pattern pattern = Pattern.compile("d.{1}e");
		Matcher matcher = pattern.matcher(html);
		while (matcher.find())
			System.out.println(matcher.group(0));

	}

	private String getAttribute(Node node, String attributeName) {
		if (null == node)
			return null;
		NamedNodeMap atts = node.getAttributes();
		if (null != atts) {
			Node attribute = atts.getNamedItem(attributeName);
			if (null != attribute)
				return attribute.getNodeValue();
		}
		return null;
	}

	public void overView() {
		String msg1 = "POST http://uni108.ogame.tw/game/index.php?page=fleet2 HTTP/1.1\r\n"
				+ "Host: uni108.ogame.tw\r\n"
				+ "Connection: keep-alive\r\n"
				+ "Content-Length: 71\r\n"
				+ "Cache-Control: max-age=0\r\n"
				+ "Origin: http://uni108.ogame.tw\r\n"
				+ "User-Agent: Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.1 Safari/537.11\r\n"
				+ "Content-Type: application/x-www-form-urlencoded\r\n"
				+ "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\r\n"
				+ "Referer: http://uni108.ogame.tw/game/index.php?page=fleet1\r\n"
				+ "Accept-Encoding: gzip,deflate,sdch\r\n"
				+ "Accept-Language: en-US,en;q=0.8,zh-CN;q=0.6,zh;q=0.4,zh-TW;q=0.2\r\n"
				+ "Accept-Charset: ISO-8859-1,utf-8;q=0.7,*;q=0.3\r\n"
				+ "Cookie: 227d0aa9d08a3965aeb4d713d46d5633=1; 45df8d87a75681ae6af16b74b5bfe7ec=1; 6212de00d827c779eb2a38d5451b8318=1; 6db104d34eabb16657a10df483ecd18a=1; 6f2b8ecc1b68c947b093c9632b54390e=1; 61db9e1620a111dd5e6ae2d74d6423af=1; tabBox_410109=1; tabBox_410908=1; tabBox_410373=1; OG_lastServer=uni108.ogame.tw; PHPSESSID=59791be5db2d; login_102602=U_tw108%3Aalbert%3Aacd393a47239facaadd31f7b0d9f3638; prsess_102602=b30570fdc93f11e2522bc693120e088a\r\n\r\n"
				+ "galaxy=1&system=262&position=9&type=1&mission=0&speed=10&am203=1&am209="
				+ "\r\n\r\n";
		try {
			Response response = client.send(msg1);
			System.out.println(response.getHttpHeader());
			System.out.println(new String(response.getHttpContent(), "utf-8"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void sendFleet() {
		String msg1 = "POST http://uni108.ogame.tw/game/index.php?page=fleet2 HTTP/1.1\r\n"
				+ "Host: uni108.ogame.tw\r\n"
				+ "Connection: keep-alive\r\n"
				+ "Content-Length: 71\r\n"
				+ "Cache-Control: max-age=0\r\n"
				+ "Origin: http://uni108.ogame.tw\r\n"
				+ "User-Agent: Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.1 Safari/537.11\r\n"
				+ "Content-Type: application/x-www-form-urlencoded\r\n"
				+ "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\r\n"
				+ "Referer: http://uni108.ogame.tw/game/index.php?page=fleet1\r\n"
				+ "Accept-Encoding: gzip,deflate,sdch\r\n"
				+ "Accept-Language: en-US,en;q=0.8,zh-CN;q=0.6,zh;q=0.4,zh-TW;q=0.2\r\n"
				+ "Accept-Charset: ISO-8859-1,utf-8;q=0.7,*;q=0.3\r\n"
				+ "Cookie: 227d0aa9d08a3965aeb4d713d46d5633=1; 45df8d87a75681ae6af16b74b5bfe7ec=1; 6212de00d827c779eb2a38d5451b8318=1; 6db104d34eabb16657a10df483ecd18a=1; 6f2b8ecc1b68c947b093c9632b54390e=1; 61db9e1620a111dd5e6ae2d74d6423af=1; tabBox_410109=1; tabBox_410908=1; tabBox_410373=1; OG_lastServer=uni108.ogame.tw; PHPSESSID=59791be5db2d; login_102602=U_tw108%3Aalbert%3Aacd393a47239facaadd31f7b0d9f3638; prsess_102602=b30570fdc93f11e2522bc693120e088a\r\n\r\n"
				+ "galaxy=1&system=262&position=9&type=1&mission=0&speed=10&am203=1&am209="
				+ "\r\n\r\n";
		try {
			Response response = client.send(msg1);
			System.out.println(response.getHttpHeader());
			System.out.println(new String(response.getHttpContent(), "utf-8"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
