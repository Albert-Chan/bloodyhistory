package event;

import java.util.LinkedList;

import gamelogic.Coordinate;
import gamelogic.Mission;
import action.FleetCheckAction;
import action.FleetSendAction;
import action.ResourceCheckAction;
import action.TargetCheckAction;
import action.executor.Scheduler;
import core.Context;

public class EventHandler {
	private Scheduler scheduler;
	private Context context;

	public void onEnemyAttack(EnemyAttackEvent e) {
		scheduler.addEvent(e);
		// decide to ambush or FS

		// FS
		ResourceCheckAction resourceCheckAction = new ResourceCheckAction(
				context, e.getObject());
		scheduler.addAction(resourceCheckAction);
		FleetCheckAction fleetCheckAction = new FleetCheckAction(context,
				e.getObject());
		scheduler.addAction(fleetCheckAction);
		TargetCheckAction targetCheckAction = new TargetCheckAction(context,
				getFSTargets(e));
		scheduler.addAction(targetCheckAction);
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
				context.getCurrentFleet(), e.getObject(), target, mission,
				context.getResource(e.getObject()));
		scheduler.addAction(fleetSendAction);
	}

	private LinkedList<Coordinate> getFSTargets(EnemyAttackEvent e) {
		LinkedList<Coordinate> targets = new LinkedList<Coordinate>();
		int coordType = e.getObject().getType();
		// moon or planet
		if (coordType == Coordinate.TYPE_MOON)
			targets.add(new Coordinate(e.getObject().toString(),
					Coordinate.TYPE_PLANET));
		else if (coordType == Coordinate.TYPE_PLANET)
			targets.add(new Coordinate(e.getObject().toString(),
					Coordinate.TYPE_MOON));
		// DF
		targets.add(new Coordinate(e.getObject().toString(), Coordinate.TYPE_DF));
		// the attacker itself
		targets.add(e.getSubject());
		return targets;
	}
}
