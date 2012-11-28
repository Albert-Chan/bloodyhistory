package action.executor;

import java.util.HashMap;

import action.IAction;

public class Scheduler {
	private HashMap<IAction, Long> schedulerTable = new HashMap<IAction, Long>();

	public void addJob(IAction action, long when) {
		schedulerTable.put(action, when);
	}

	public void cancelJob(IAction action) {
		schedulerTable.remove(action);
	}
	
	public void run() {

	}

}
