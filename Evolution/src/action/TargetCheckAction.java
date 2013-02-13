package action;

import gamelogic.Coordinate;

import java.io.IOException;
import java.util.LinkedList;

import messenger.Parameter;
import network.Response;
import core.Context;

public class TargetCheckAction extends AbstractAction {

	// The target should be a list. This Action will check through the list one
	// by one if the previous target is not available.
	private LinkedList<Coordinate> targets;

	public TargetCheckAction(Context context, LinkedList<Coordinate> targets) {
		super(context);
		this.targets = targets;
	}

	public void exec() throws IOException, ActionFailureException {
		// [fleetcheck]
		// this is check the target existence
		// @@GameServer@
		// @@cookie@
		// @@target_g@
		// @@target_s@
		// @@target_p@
		// @@target_t@
		// @@length@

		Parameter server = new Parameter("@@GameServer@",
				context.getGameServer());
		Parameter cookie = new Parameter("@@cookie@", context.getCookie());
		boolean isTargetExistent = false;
		Parameter target_g = null;
		Parameter target_s = null;
		Parameter target_p = null;
		Parameter target_t = null;
		for (Coordinate target : targets) {
			target_g = new Parameter("@@target_g@", Integer.toString(target
					.getGalaxy()));
			target_s = new Parameter("@@target_s@", Integer.toString(target
					.getSystem()));
			target_p = new Parameter("@@target_p@", Integer.toString(target
					.getPosition()));
			target_t = new Parameter("@@target_t@", Integer.toString(target
					.getType()));
			String fleetcheck = context.generator.generate("fleetcheck", server,
					cookie, target_g, target_s, target_p, target_t);
			Response response = context.client.send(fleetcheck);
			isTargetExistent = context.inspector.isTargetExistent(new String(response
					.getHttpContent(), "utf-8"));
			if (isTargetExistent) {
				context.setCurrentFleetTarget(target);
				break;
			}
		}
	}
}
