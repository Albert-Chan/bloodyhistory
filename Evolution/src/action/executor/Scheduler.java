package action.executor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import action.IAction;
import action.LoginAction;
import core.Context;
import event.IEvent;

public class Scheduler {

	private LinkedList<IEvent> eventList = new LinkedList<IEvent>();

	private LinkedHashMap<Long, IAction> actionQueue = new LinkedHashMap<Long, IAction>();

	private Context context;

	public Scheduler(Context context) {
		this.context = context;
	}

	public void addEvent(IEvent event) {
		eventList.add(event);
		IAction action = event.handle(context);
		addAction(action.getWhen(), action);
	}

	public void removeEvent(IEvent event) {
		eventList.remove(event);
		List<IAction> actions = event.removeActions();
		for (IAction action : actions) {
			removeAction(action);
		}
	}

	public void addAction(long when, IAction action) {
		actionQueue.put(when, action);
		checkRestriction();
	}

	public void removeAction(IAction action) {
		actionQueue.remove(action);
		ArrayList<IAction> preActions = action.getPreActions();
		for (IAction preAction : preActions) {
			removeAction(preAction);
		}

	}
	
	//TODO
	private void checkRestriction(){
		
	}

	public void run() {

//		try {
//			new UnLoginState().process();
//		} catch (IOException e) {
//			// log the excpetion
//			new UnLoginState().process();
//		}

	}

//	class UnLoginState {
//		public void process() throws IOException {
//			LoginAction login = new LoginAction(context);
//			login.act();
//			new OverViewRunRobinState().process();
//		}
//	}
//
//	class OverViewRunRobinState {
//		public void process() throws IOException {
//			CheckEventAction checkEventAction = new CheckEventAction(context);
//			checkEventAction.act();
//			EventGroup events = checkEventAction.getOutput();
//			if (events.getHostile().isEmpty()) {
//				new EatSheepState().process();
//			} else {
//				new FSState().process();
//			}
//		}
//	}

}
