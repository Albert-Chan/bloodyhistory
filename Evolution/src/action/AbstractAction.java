package action;

import core.Context;

public abstract class AbstractAction implements IAction {
	protected Context context;

	public AbstractAction(Context context) {
		this.context = context;
	}

}
