package core;

import gamelogic.Planet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import messenger.PackageGenerator;
import messenger.Parameter;
import network.HttpClient;
import network.Response;
import network.UngzipHandler;
import parser.Inspector;

public class OgameBot {

	private HttpClient client;
	
	private PackageGenerator generator = new PackageGenerator();
	private Inspector inspector = new Inspector();

	public OgameBot(HttpClient client) {
		this.client = client;
	}

	public static void main(String[] args) {
		HttpClient client = new HttpClient("uni108.ogame.tw", 80);
		client.registerResponseHandler(new UngzipHandler());

		OgameBot bot = new OgameBot(client);

		bot.login();
	}
	
	public void login()
	{
		Map<String, String> cookies = null;
		
		Parameter server = new Parameter("@@GameServer@", "uni108.ogame.tw");
		Parameter user = new Parameter("@@user@", "albert");
		Parameter pass = new Parameter("@@password@", "11111111");
		
		String msg1 = generator.generate("login1", server, user, pass);
		
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
		String PHPSESSID = null;
		for (String cookieKey : cookies.keySet()) {
			if (cookieKey.startsWith("PHPSESSID") ){
				PHPSESSID = cookies.get(cookieKey);
			}
			if (cookieKey.startsWith("PHPSESSID")
					|| cookieKey.startsWith("prsess")
					|| cookieKey.startsWith("login")) {
				strCookie.append(cookieKey).append("=")
						.append(cookies.get(cookieKey)).append("; ");
			}
		}

		Parameter phpSession = new Parameter("@@PHPSESSID@", PHPSESSID);
		Parameter cookie = new Parameter("@@cookie@", strCookie.toString());
		
		String msg2 = generator.generate("login2", server, phpSession, cookie);

		try {
			Response response = client.send(msg2);
			System.out.println(response.getHttpHeader());
			System.out.println(new String(response.getHttpContent(), "utf-8"));
			
			HashMap<String, Planet> cpMap = inspector.getCPMap(new String(response.getHttpContent(), "utf-8"));
			System.out.println("end");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendFleet()
	{
		
	}
	
	
	

	public void oldLogin() {
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
			Inspector inspector = new Inspector();
			HashMap<String, Planet> cpMap = inspector.getCPMap(new String(response.getHttpContent(), "utf-8"));
			System.out.println("end");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
