package action;

import gamelogic.Coordinate;
import gamelogic.Mission;

import java.io.IOException;

import messenger.Parameter;
import network.Response;
import core.Context;

public class OverviewAction extends AbstractAction {
	private Coordinate coordinate;

	public OverviewAction(Context context, Coordinate coordinate,
			Coordinate target, Mission mission) {
		super(context);
		this.coordinate = coordinate;
	}

	protected void exec() throws IOException, ActionFailureException {
		// [fleet1]
		// @@GameServer@
		// @@cp@
		// @@cookie@
		Parameter server = new Parameter("@@GameServer@",
				context.getGameServer());
		Parameter cp = new Parameter("@@cp@", coordinate.getCp());
		Parameter cookie = new Parameter("@@cookie@", context.getCookie());

		String fleet1 = context.generator.generate("fleet1", server, cp, cookie);
		Response response = context.client.send(fleet1);
		String allShipsJson = context.inspector.getAllShipsJson(new String(response
				.getHttpContent(), "utf-8"));

		context.updateMaxShips(coordinate, allShipsJson);

	}

}
