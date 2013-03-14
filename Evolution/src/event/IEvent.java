package event;

import java.util.List;

import action.IAction;
import core.Context;

public interface IEvent {

	String getId();

	void setId(String id);

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

	IAction trigger(Context context);

//	void dismiss(Context context);
}
