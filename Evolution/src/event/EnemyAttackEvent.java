package event;

import action.FleetSendAction;
import action.IAction;
import core.Context;

public class EnemyAttackEvent extends MinaryEvent {

	@Override
	public IAction handle(Context context) {
		// decide to ambush or FS

		// FS
		FleetSendAction fleetSendAction = new FleetSendAction(context);
		attachAction(fleetSendAction);
		return fleetSendAction;
	}

}
