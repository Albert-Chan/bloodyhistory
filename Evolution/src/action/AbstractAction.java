package action;

import java.util.ArrayList;

import core.Context;

public abstract class AbstractAction implements IAction {
	protected Context context;

	private ArrayList<IAction> preActions;

	public AbstractAction(Context context) {
		this.context = context;
	}
	
	private long when;

	public long getWhen() {
		return this.when;
	}

	public void setWhen(long when) {
		this.when = when;
	}

	public void addPreAction(IAction action) {
		preActions.add(action);
	}

	public ArrayList<IAction> getPreActions() {
		return preActions;
	}

}
