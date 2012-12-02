package event;

import java.util.LinkedList;
import java.util.List;

import action.IAction;

public abstract class AbstractEvent implements IEvent {

	private LinkedList<IAction> actions = new LinkedList<IAction>();

	public boolean equals(IEvent e) {
		return false;
	}

	private long when;

	public long getWhen() {
		return this.when;
	}

	public void setWhen(long when) {
		this.when = when;
	}

	public List<IAction> removeActions() {
		return actions;
	}

	public boolean attachAction(IAction action) {
		return this.actions.add(action);
	}

	public boolean attachActions(List<IAction> actions) {
		return this.actions.addAll(actions);
	}

}
