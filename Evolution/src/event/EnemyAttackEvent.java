package event;

import gamelogic.Coordinate;
import gamelogic.Mission;
import action.FleetSendAction;
import action.IAction;
import core.Context;

public class EnemyAttackEvent extends MinaryEvent {

	@Override
	public IAction handle(Context context) {
		// decide to ambush or FS

		// FS
		FleetSendAction fleetSendAction = new FleetSendAction(context,
				getObject(), getFSTarget(), new Mission(
						Mission.TYPE_FLEET_SAVING));
		attachAction(fleetSendAction);
		return fleetSendAction;
	}

	private Coordinate getFSTarget() {
		
		int coordType = getObject().getType();
		if (coordType == Coordinate.TYPE_MOON)
			return new Coordinate(getObject().toString(), Coordinate.TYPE_PLANET);
		else if (coordType == Coordinate.TYPE_PLANET)
		{
			// check if has Moon
			if (hasMoon)
				return new Coordinate(getObject().toString(), Coordinate.TYPE_MOON);
			else
			// check if has DF
				return new Coordinate(getObject().toString(), Coordinate.TYPE_DF);
		}
	}

}
