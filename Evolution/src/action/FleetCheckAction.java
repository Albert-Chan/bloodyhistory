package action;

import gamelogic.Coordinate;

import java.io.IOException;

import messenger.Parameter;
import network.Response;
import core.Context;

/**
 * 
 * This action will updates the maxships on the "source" coordinate. The
 * subsequent action can use<code> fleet = new
 * Fleet(context.coordinate2MaxShipsMap.get(source)) </code> to get the max fleet
 * object of that coordinate.
 * 
 */

public class FleetCheckAction extends AbstractAction {

	private Coordinate source;

	public FleetCheckAction(Context context, Coordinate source) {
		super(context);
		this.source = source;
	}

	protected void exec() throws IOException, ActionFailureException {

		// [fleet1]
		// @@GameServer@
		// @@cp@
		// @@cookie@
		Parameter server = new Parameter("@@GameServer@",
				context.getGameServer());
		Parameter cp = new Parameter("@@cp@", source.getCp());
		Parameter cookie = new Parameter("@@cookie@", context.getCookie());

		String fleet1 = context.generator.generate("fleet1", server, cp, cookie);
		Response response = context.client.send(fleet1);
		String allShipsJson = context.inspector.getAllShipsJson(new String(response
				.getHttpContent(), "utf-8"));

		// Updates the map of coordinate - maxShips in context.
		context.updateMaxShips(source, allShipsJson);
	}
}
