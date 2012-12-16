package core;

import gamelogic.Coordinate;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.TimeZone;

import messenger.PackageGenerator;
import network.HttpClient;
import parser.Inspector;

public class Context {

	private HttpClient client;

	private String gameServer;

	private String cookie;
	private String phpSession;

	private String user;
	private String pass;

	private PackageGenerator generator = new PackageGenerator();
	private Inspector inspector = new Inspector();

	private HashMap<String, Coordinate> cpMap;

	public int routeLimit;

	public HttpClient getClient() {
		return client;
	}

	public void setClient(HttpClient client) {
		this.client = client;
	}

	public String getGameServer() {
		return gameServer;
	}

	public void setGameServer(String gameServer) {
		this.gameServer = gameServer;
	}

	public String getCookie() {
		return cookie;
	}

	public void setCookie(String cookie) {
		this.cookie = cookie;
	}

	public String getPhpSession() {
		return phpSession;
	}

	public void setPhpSession(String phpSession) {
		this.phpSession = phpSession;
	}

	public PackageGenerator getGenerator() {
		return generator;
	}

	public Inspector getInspector() {
		return inspector;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public HashMap<String, Coordinate> getCpMap() {
		return cpMap;
	}

	public void setCpMap(HashMap<String, Coordinate> cpMap) {
		this.cpMap = cpMap;
	}

	public String getCurrentDateTime() {
		GregorianCalendar calendar = new GregorianCalendar(
				TimeZone.getTimeZone("GMT+8"));
		java.text.SimpleDateFormat f = new java.text.SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		return f.format(calendar.getTime());
	}
}
