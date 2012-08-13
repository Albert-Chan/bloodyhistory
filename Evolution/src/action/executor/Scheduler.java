package action.executor;

import java.util.HashMap;
import java.util.Timer;

import action.IAction;

public class Scheduler {
	private HashMap<IAction, Timer> timerTable = new HashMap<IAction, Timer>();

	public void addJob(IAction action) {
		timerTable.put(action, action.schedule());
	}

	public void cancelJob(IAction action) {
		Timer timer = timerTable.get(action);
		timer.cancel();
	}

}
