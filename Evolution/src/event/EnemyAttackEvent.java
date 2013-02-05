package event;

import gamelogic.Coordinate;
import gamelogic.Mission;

import java.util.HashMap;

import action.FleetSendAction;
import action.IAction;
import core.Context;

public class EnemyAttackEvent extends MinaryEvent {

	@Override
	public IAction handle(Context context) {
		// decide to ambush or FS

		// FS
		FleetSendAction fleetSendAction = new FleetSendAction(context,
				getObject(), getFSTarget(), new Mission(Mission.MISSION_DEPLOY,
						Mission.SPEED_10_PERCENT));
		attachAction(fleetSendAction);
		return fleetSendAction;
	}

	private Coordinate getFSTarget() {
		int coordType = getObject().getType();
		if (coordType == Coordinate.TYPE_MOON)
			return new Coordinate(getObject().toString(),
					Coordinate.TYPE_PLANET);
		else if (coordType == Coordinate.TYPE_PLANET) {
			// check if has Moon
			if (hasMoon)
				return new Coordinate(getObject().toString(),
						Coordinate.TYPE_MOON);
			else
			// check if has DF
			if (hasDF)
				return new Coordinate(getObject().toString(),
						Coordinate.TYPE_DF);

		}
	}

}
