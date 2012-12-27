package action.executor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import action.FleetSendAction;
import action.IAction;
import action.LoginAction;
import core.Context;
import event.IEvent;
import gamelogic.Coordinate;
import gamelogic.Fleet;
import gamelogic.Resource;

public class Scheduler {

	private LinkedList<IEvent> eventList = new LinkedList<IEvent>();

	private HashMap<IAction, ActionThread> actionQueue = new HashMap<IAction, ActionThread>();

	private Context context;

	private Timer timer = new Timer();

	public Scheduler(Context context) {
		this.context = context;
	}

	public void addEvent(IEvent event) {
		eventList.add(event);
		IAction action = event.handle(context);
		addAction(action);
	}

	public void removeEvent(IEvent event) {
		eventList.remove(event);
		List<IAction> actions = event.removeActions();
		for (IAction action : actions) {
			removeAction(action);
		}
	}

	public void addAction(IAction action) {
		if (!checkRestriction())
			return;
		
		ArrayList<IAction> preActions = action.getPreActions();
		for (IAction preAction : preActions) {
			addAction(preAction);
		}

		ActionThread actionThread = new ActionThread(action);
		actionQueue.put(action, actionThread);
		
		long delay = action.getWhen() - System.currentTimeMillis();
		timer.schedule(actionThread, delay < 0 ? 0 : delay);
	}
	
	public void removeAction(IAction action) {
		ArrayList<IAction> preActions = action.getPreActions();
		for (IAction preAction : preActions) {
			removeAction(preAction);
		}
		ActionThread actionThread = actionQueue.remove(action);
		actionThread.cancel();
	}
	
	class ActionThread extends TimerTask {

		IAction action;

		public ActionThread(IAction action) {
			this.action = action;
		}

		public void run() {
			action.perform();
		}
	}

	// TODO
	private boolean checkRestriction() {
		return true;
	}

	private ArrayList<RouteOccupancy> routes = new ArrayList<RouteOccupancy>();
	ArrayList<Coordinate> sheepList = null;
	int cursor = 0;

	public void run() {
		LoginAction login = new LoginAction(context);
		addAction(login);
		context.waitForLogin();
		
		List<Coordinate> myCoordinates = context.getMyCoordinates();
		Coordinate source = myCoordinates.get(0);
		Coordinate target = new Coordinate("[1:271:10]");
		Fleet fleet = new Fleet();
		fleet.add(203, 1);
		FleetSendAction fleetSend = new FleetSendAction(context, source,
				target, Resource.NO_RESOURCE, fleet,
				FleetSendAction.MISSION_ATTACK,
				FleetSendAction.SPEED_100_PERCENT);
		addAction(fleetSend);

//		retrevieRouteNumber();
//
//		// checkOverviewEvent();
//
//		loadSheepList();
//
//		for (int i = 0; i < context.routeLimit; i++) {
//			RouteOccupancy occupancy = new RouteOccupancy();
//			occupancy.setType(RouteOccupancy.TYPE_SHEEP_HUNTING);
//			Coordinate dest = sheepList.get(cursor++);
//			// occupancy need not dest?
//			// occupancy.setDest(dest);
//
//			// Coordinate source = chooseSource(dest);
//			// FleetSendAction fleetSend = new FleetSendAction(context, source,
//			// dest, resource, fleet);
//			// addAction(fleetSend);
//			//
//			// occupancy.setFreeTimePoint(freeTimePoint);
//			//
//			// routes.add(occupancy);
//		}

	}

	private void loadSheepList() {
		sheepList = new ArrayList<Coordinate>();
	}

	private void retrevieRouteNumber() {
		context.routeLimit = 5;
	}

	// public void old_run() {
	// try {
	// new UnLoginState().process();
	// } catch (IOException e) {
	// // log the excpetion
	// new UnLoginState().process();
	// }
	// }

	// class UnLoginState {
	// public void process() throws IOException {
	// LoginAction login = new LoginAction(context);
	// login.act();
	// new OverViewRunRobinState().process();
	// }
	// }
	//
	// class OverViewRunRobinState {
	// public void process() throws IOException {
	// CheckEventAction checkEventAction = new CheckEventAction(context);
	// checkEventAction.act();
	// EventGroup events = checkEventAction.getOutput();
	// if (events.getHostile().isEmpty()) {
	// new EatSheepState().process();
	// } else {
	// new FSState().process();
	// }
	// }
	// }

}
