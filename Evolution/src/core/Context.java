package core;

import event.IEvent;
import gamelogic.Coordinate;
import gamelogic.Fleet;
import gamelogic.Resource;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.Semaphore;

import messenger.PackageGenerator;
import network.HttpClient;

import org.json.JSONException;
import org.json.JSONObject;

import parser.Inspector;

public class Context {

	public HttpClient client;
	public PackageGenerator generator = new PackageGenerator();
	public Inspector inspector = new Inspector();

	private String gameServer;
	private String cookie;
	private String phpSession;

	private String user;
	private String pass;

	private List<Coordinate> myCoordinates;

	private long serverTime;

	/**
	 * max ships
	 */
	public HashMap<Coordinate, HashMap<Integer, Integer>> coordinate2MaxShipsMap;

	/**
	 * resources
	 */
	public HashMap<Coordinate, Resource> resources;

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

	private Semaphore logined = new Semaphore(0);

	public void loginSucceed() {
		logined.release();
	}

	public void waitForLogin() {
		try {
			logined.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public List<Coordinate> getMyCoordinates() {
		return myCoordinates;
	}

	public void setMyCoordinates(List<Coordinate> myCoordinates) {
		this.myCoordinates = myCoordinates;
	}

	public String getCurrentDateTime() {
		GregorianCalendar calendar = new GregorianCalendar(
				TimeZone.getTimeZone("GMT+8"));
		java.text.SimpleDateFormat f = new java.text.SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		return f.format(calendar.getTime());
	}

	/**
	 * The "events" is the eventList displayed on the game's overview. They are
	 * the currently existing events.
	 */
	private HashMap<String, IEvent> events = new HashMap<String, IEvent>();
	/**
	 * The "newEvents" is used with the events. They are the events spring out
	 * from the last web page extraction.
	 */
	private List<IEvent> newEvents = new ArrayList<IEvent>();

	/**
	 * The "vanishedEvents" are the events disappeared from the last web page
	 * extraction. The cause may be the time is just running out or the
	 * user/enemy canceled the action.
	 */
	private List<IEvent> vanishedEvents = new ArrayList<IEvent>();

	public void updateEvents(List<IEvent> eventList) {
		newEvents.clear();
		vanishedEvents.clear();
		HashMap<String, IEvent> tmpEvents = new HashMap<String, IEvent>();
		for (IEvent event : eventList) {
			// TODO need to check when a fleet joins a union attack will a new
			// id be used or not.
			if (!events.containsKey(event.getId())) {
				newEvents.add(event);
			}
			tmpEvents.put(event.getId(), event);
		}

		for (IEvent event : events.values()) {
			if (!tmpEvents.containsKey(event.getId())) {
				vanishedEvents.add(event);
			}
		}
		events = tmpEvents;
	}

	public List<IEvent> getNewEvents() {
		return this.newEvents;
	}

	public List<IEvent> getVanishedEvents() {
		return this.vanishedEvents;
	}

	public boolean updateMaxShips(Coordinate coord, String maxShipsJson) {
		HashMap<Integer, Integer> maxShips = new HashMap<Integer, Integer>();
		try {
			JSONObject element = new JSONObject(maxShipsJson);
			String[] shipTypes = JSONObject.getNames(element);
			for (String type : shipTypes) {
				int nType = Integer.parseInt(type);
				int number = element.getInt(type);
				maxShips.put(nType, number);
			}
			coordinate2MaxShipsMap.put(coord, maxShips);
		} catch (NumberFormatException e) {
			// logger?
			return false;
		} catch (JSONException e) {
			// logger?
			return false;
		}
		return true;
	}

	public void updateResource(Coordinate coord, Resource resource) {
		resources.put(coord, resource);
	}

	public Resource getResource(Coordinate coord) {
		return resources.get(coord);
	}

	private Fleet currentFleet;

	public Fleet getCurrentFleet() {
		return currentFleet;
	}

	public void setCurrentFleet(Fleet currentFleet) {
		this.currentFleet = currentFleet;
	}

	private Coordinate currentFleetTarget;

	public Coordinate getCurrentFleetTarget() {
		return currentFleetTarget;
	}

	public void setCurrentFleetTarget(Coordinate currentFleetTarget) {
		this.currentFleetTarget = currentFleetTarget;
	}

	public long getServerTime() {
		return serverTime;
	}

	public void setServerTime(long serverTime) {
		this.serverTime = serverTime;
	}

}
