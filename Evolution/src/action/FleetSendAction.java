package action;

import gamelogic.Coordinate;
import gamelogic.Fleet;
import gamelogic.Mission;
import gamelogic.Resource;

import java.io.IOException;

import messenger.PackageGenerator;
import messenger.Parameter;
import network.HttpClient;
import network.Response;
import parser.Inspector;
import core.Context;

public class FleetSendAction extends AbstractAction {
	private Coordinate source;
	private Coordinate target;
	private Mission mission;
	
	private Fleet fleet;
	private Resource resource;
	

	public FleetSendAction(Context context, Coordinate source,
			Coordinate target, Mission mission) {
		super(context);
		this.source = source;
		this.target = target;
		this.mission = mission;
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
		Parameter cp = new Parameter("@@cp@", source.getCp());
		Parameter cookie = new Parameter("@@cookie@", context.getCookie());

		String fleet1 = generator.generate("fleet1", server, cp, cookie);
		Response response = client.send(fleet1);
		String allShipsJson = inspector.getAllShipsJson(new String(response
				.getHttpContent(), "utf-8"));
		
		// Updates the map of coordinate - maxShips in context.
		context.parseMaxShipsJSON(source, allShipsJson);
		
		fleet = new Fleet(context.coordinate2MaxShipsMap.get(source));

		// [fleet2]
		// source coodinate and type
		// mission is always 0
		// speed is always 10
		// @@GameServer@
		// @@cookie@
		// @@source_g@
		// @@source_s@
		// @@source_p@
		// @@source_t@
		// @@mission@
		// @@speed@
		// @@ships@
		// @@length@
		Parameter source_g = new Parameter("@@source_g@",
				Integer.toString(source.getGalaxy()));
		Parameter source_s = new Parameter("@@source_s@",
				Integer.toString(source.getSystem()));
		Parameter source_p = new Parameter("@@source_p@",
				Integer.toString(source.getPosition()));
		Parameter source_t = new Parameter("@@source_t@",
				Integer.toString(source.getType()));
		Parameter missionType = new Parameter("@@mission@", mission.getMission());
		Parameter speed = new Parameter("@@speed@", mission.getSpeed());
		Parameter ships = new Parameter("@@ships@", fleet.toString());

		String fleet2 = generator.generate("fleet2", server, cookie, source_g,
				source_s, source_p, source_t, ships);
		response = client.send(fleet2);

		// [fleetcheck]
		// this is check the target existence
		// @@GameServer@
		// @@cookie@
		// @@target_g@
		// @@target_s@
		// @@target_p@
		// @@target_t@
		// @@length@
		Parameter target_g = new Parameter("@@target_g@",
				Integer.toString(target.getGalaxy()));
		Parameter target_s = new Parameter("@@target_s@",
				Integer.toString(target.getSystem()));
		Parameter target_p = new Parameter("@@target_p@",
				Integer.toString(target.getPosition()));
		Parameter target_t = new Parameter("@@target_t@",
				Integer.toString(target.getType()));
		String fleetcheck = generator.generate("fleetcheck", server, cookie,
				target_g, target_s, target_p, target_t);
		response = client.send(fleetcheck);
		boolean isTargetExistent = inspector.isTargetExistent(new String(response
				.getHttpContent(), "utf-8"));
		if (!isTargetExistent)
			throw new ActionFailureException();

		// [fleet3]
		// mission is always 0
		// @@GameServer@
		// @@cookie@
		// @@target_g@
		// @@target_s@
		// @@target_p@
		// @@target_t@
		// @@mission@
		// @@union@
		// @@speed@
		// @@ships@
		// @@length@
		Parameter union = new Parameter("@@union@", mission.getUnion());
		String fleet3 = generator.generate("fleet3", server, cookie, target_g,
				target_s, target_p, target_t, union, speed, ships);
		response = client.send(fleet3);
		String strToken = inspector.getFleetToken(new String(response
				.getHttpContent(), "utf-8"));
		if (strToken == null)
			throw new ActionFailureException();

		// [movement]
		// @@GameServer@
		// @@cookie@
		// @@holding@
		// @@expedition@
		// @@token@
		// @@target_g@
		// @@target_s@
		// @@target_p@
		// @@target_t@
		// @@mission@
		// @@union2@
		// @@holdOrExp@
		// @@speed@
		// @@ships@
		// @@m@
		// @@c@
		// @@d@
		// @@length@
		Parameter holding = new Parameter("@@holding@",
				mission.getHoldingtime());
		Parameter expedition = new Parameter("@@expedition@",
				mission.getExpeditiontime());
		Parameter token = new Parameter("@@token@", strToken);
		Parameter union2 = new Parameter("@@union2@", mission.getUnion2());
		Parameter holdOrExp = new Parameter("@@holdOrExp@",
				mission.getHoldingOrExpTime());
		Parameter m = new Parameter("@@m@", Integer.toString(resource
				.getMetal()));
		Parameter c = new Parameter("@@c@", Integer.toString(resource
				.getCrystal()));
		Parameter d = new Parameter("@@d@", Integer.toString(resource
				.getDeuterium()));

		String movement = generator
				.generate("movement", server, cookie, holding, expedition,
						token, target_g, target_s, target_p, target_t,
						missionType, union2, holdOrExp, speed, ships, m, c, d);
		response = client.send(movement);
		// TODO handle(response);
		System.out.println(new String(response
				.getHttpContent(), "utf-8"));
	}

}
