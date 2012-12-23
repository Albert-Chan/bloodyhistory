package action;

import gamelogic.Coordinate;
import gamelogic.Fleet;
import gamelogic.Resource;

import java.util.HashMap;

import messenger.PackageGenerator;
import messenger.Parameter;
import network.HttpClient;
import network.Response;
import parser.Inspector;
import core.Context;

public class FleetSendAction extends AbstractAction {

	public static final int MISSION_ATTACK = 1;
	
	public static final int SPEED_10_PERCENT = 1;
	public static final int SPEED_20_PERCENT = 2;
	public static final int SPEED_30_PERCENT = 3;
	public static final int SPEED_40_PERCENT = 4;
	public static final int SPEED_50_PERCENT = 5;
	public static final int SPEED_60_PERCENT = 6;
	public static final int SPEED_70_PERCENT = 7;
	public static final int SPEED_80_PERCENT = 8;
	public static final int SPEED_90_PERCENT = 9;
	public static final int SPEED_100_PERCENT = 10;

	private Coordinate source;
	private Coordinate target;
	private Resource resource;
	private Fleet fleet;
	private int speed;

	public FleetSendAction(Context context, Coordinate source,
			Coordinate target, Resource resource, Fleet fleet, int missionType,
			int speed) {
		super(context);
		this.source = source;
		this.target = target;
		this.resource = resource;
		this.fleet = fleet;
		this.speed = speed;
	}

	protected void exec() {
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
		// TODO handle(response);

		// [fleet2]
		// @@GameServer@
		// @@cookie@
		// @@g@
		// @@s@
		// @@p@
		// @@t@
		// @@mission@
		// @@speed@
		// @@ships@
		// @@length@
		Parameter g = new Parameter("@@g@",
				Integer.toString(target.getGalaxy()));
		Parameter s = new Parameter("@@s@",
				Integer.toString(target.getSystem()));
		Parameter p = new Parameter("@@p@", Integer.toString(target
				.getPosition()));
		Parameter t = new Parameter("@@t@", Integer.toString(target.getType()));
		Parameter mission = new Parameter("@@mission@", context.getCookie());
		Parameter speed = new Parameter("@@speed@", context.getCookie());
		Parameter ships = new Parameter("@@ships@", context.getCookie());

		String fleet2 = generator.generate("fleet2", server, cookie, g, s, p,
				t, mission, speed, ships);
		response = client.send(fleet2);
		// TODO handle(response);

		// [fleetcheck]
		// @@GameServer@
		// @@cookie@
		// @@g@
		// @@s@
		// @@p@
		// @@t@
		// @@length@
		String fleetcheck = generator.generate("fleetcheck", server, cookie, g,
				s, p, t);
		response = client.send(fleetcheck);
		// TODO handle(response);

		// [fleet3]
		// @@GameServer@
		// @@cookie@
		// @@g@
		// @@s@
		// @@p@
		// @@t@
		// @@mission@
		// @@union@
		// @@speed@
		// @@ships@
		// @@length@
		Parameter union = new Parameter("@@union@", union_);
		String fleet3 = generator.generate("fleet3", server, cookie, g, s, p,
				t, mission, union, speed, ships);
		response = client.send(fleet3);
		// TODO handle(response);

		// [movement]
		// @@GameServer@
		// @@cookie@
		// @@holding@
		// @@expedition@
		// @@token@
		// @@g@
		// @@s@
		// @@p@
		// @@t@
		// @@mission@
		// @@union2@
		// @@holdOrExp@
		// @@speed@
		// @@ships@
		// @@m@
		// @@c@
		// @@d@
		// @@length@
		Parameter holding = new Parameter("@@holding@", holding_);
		Parameter expedition = new Parameter("@@expedition@", expedition_);
		Parameter token = new Parameter("@@token@", strToken);
		Parameter union2 = new Parameter("@@union2@", union2_);
		Parameter holdOrExp = new Parameter("@@holdOrExp@", holdOrExp_);
		Parameter m = new Parameter("@@m@", Integer.toString(resource
				.getMetal()));
		Parameter c = new Parameter("@@c@", Integer.toString(resource
				.getCrystal()));
		Parameter d = new Parameter("@@d@", Integer.toString(resource
				.getDeuterium()));

		String movement = generator.generate("movement", server, cookie,
				holding, expedition, token, g, s, p, t, mission, union2,
				holdOrExp, speed, ships, m, c, d);
		response = client.send(movement);
		// TODO handle(response);
	}

}
