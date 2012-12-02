package event;

import java.util.List;

import action.IAction;
import core.Context;

public interface IEvent {

	boolean equals(IEvent e);
	/**
	 * the difference, measured in milliseconds, between the incident time and
	 * midnight, January 1, 1970 UTC.
	 */
	long getWhen();

	void setWhen(long when);

	List<IAction> removeActions();
	
	boolean attachAction(IAction action);
	boolean attachActions(List<IAction> actions);

	IAction handle(Context context);
}
