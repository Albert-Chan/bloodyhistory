package event;

import gamelogic.Coordinate;
import gamelogic.Mission;

import java.util.LinkedList;

import action.FleetCheckAction;
import action.FleetSendAction;
import action.IAction;
import action.ResourceCheckAction;
import action.TargetCheckAction;
import core.Context;

public class EnemyAttackEvent extends MinaryEvent {

	private IAction
	
	@Override
	public IAction trigger(Context context) {
		// decide to ambush or FS

		// FS
		ResourceCheckAction resourceCheckAction = new ResourceCheckAction(
				context, getObject());
		attachAction(resourceCheckAction);
		FleetCheckAction fleetCheckAction = new FleetCheckAction(context,
				getObject());
		attachAction(fleetCheckAction);
		TargetCheckAction targetCheckAction = new TargetCheckAction(context,
				getFSTargets());
		attachAction(targetCheckAction);
		Coordinate target = context.getCurrentFleetTarget();
		Mission mission = null;
		if (context.getMyCoordinates().contains(target))
			mission = new Mission(Mission.MISSION_DEPLOY,
					Mission.SPEED_10_PERCENT);
		else if (target.getType() == Coordinate.TYPE_DF)
			mission = new Mission(Mission.MISSION_RECYCLE,
					Mission.SPEED_10_PERCENT);
		else
			mission = new Mission(Mission.MISSION_ATTACK,
					Mission.SPEED_10_PERCENT);

		FleetSendAction fleetSendAction = new FleetSendAction(context,
				context.getCurrentFleet(), getObject(), target, mission,
				context.getResource(getObject()));
		attachAction(fleetSendAction);
		return fleetSendAction;
	}

	private LinkedList<Coordinate> getFSTargets() {
		LinkedList<Coordinate> targets = new LinkedList<Coordinate>();
		int coordType = getObject().getType();
		// moon or planet
		if (coordType == Coordinate.TYPE_MOON)
			targets.add(new Coordinate(getObject().toString(),
					Coordinate.TYPE_PLANET));
		else if (coordType == Coordinate.TYPE_PLANET)
			targets.add(new Coordinate(getObject().toString(),
					Coordinate.TYPE_MOON));
		// DF
		targets.add(new Coordinate(getObject().toString(), Coordinate.TYPE_DF));
		// the attacker itself
		targets.add(getSubject());
		return targets;
	}
}
