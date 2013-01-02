package action;

import gamelogic.Coordinate;
import gamelogic.Mission;

import java.io.IOException;

import messenger.PackageGenerator;
import messenger.Parameter;
import network.HttpClient;
import network.Response;
import parser.Inspector;
import core.Context;

public class OverviewAction extends AbstractAction {
	private Coordinate coordinate;

	public OverviewAction(Context context, Coordinate coordinate,
			Coordinate target, Mission mission) {
		super(context);
		this.coordinate = coordinate;
	}

	protected void exec() throws IOException, ActionFailureException {
		HttpClient client = context.getClient();
		if (null == client)
			return;

		PackageGenerator generator = context.getGenerator();
		if (null == generator)
			return;

		Inspector inspector = context.getInspector();
		if (null == inspector)
			return;

		// [fleet1]
		// @@GameServer@
		// @@cp@
		// @@cookie@
		Parameter server = new Parameter("@@GameServer@",
				context.getGameServer());
		Parameter cp = new Parameter("@@cp@", coordinate.getCp());
		Parameter cookie = new Parameter("@@cookie@", context.getCookie());

		String fleet1 = generator.generate("fleet1", server, cp, cookie);
		Response response = client.send(fleet1);
		String allShipsJson = inspector.getAllShipsJson(new String(response
				.getHttpContent(), "utf-8"));

		context.parseMaxShipsJSON(coordinate, allShipsJson);

	}

}
