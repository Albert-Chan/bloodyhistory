package action;

import gamelogic.Coordinate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import messenger.Parameter;
import network.Response;
import core.Context;

/**
 * Login to initialize cookie and get colonies list.
 * 
 * @author Albert
 * 
 */
public class LoginAction extends AbstractAction {

	public LoginAction(Context context) {
		super(context);
	}

	protected void exec() throws IOException {
		Parameter server = new Parameter("@@GameServer@",
				context.getGameServer());
		Parameter user = new Parameter("@@user@", context.getUser());
		Parameter pass = new Parameter("@@password@", context.getPass());

		String login1 = context.generator
				.generate("login1", server, user, pass);
		Response response = context.client.send(login1);
		parseCookie(response);

		Parameter phpSession = new Parameter("@@PHPSESSID@",
				context.getPhpSession());
		Parameter cookie = new Parameter("@@cookie@", context.getCookie());

		String login2 = context.generator.generate("login2", server,
				phpSession, cookie);
		response = context.client.send(login2);
		String respString = new String(response.getHttpContent(), "utf-8");

		List<Coordinate> colonies = context.inspector.getColonies(respString);
		List<Coordinate> moons = context.inspector.getMoons(respString);
		List<Coordinate> myCoordinates = new ArrayList<Coordinate>();
		myCoordinates.addAll(colonies);
		myCoordinates.addAll(moons);
		context.setMyCoordinates(myCoordinates);
		
		long serverTime = context.inspector.getServerTime(respString);
		context.setServerTime(serverTime);

		context.loginSucceed();
	}

	private void parseCookie(Response response) {
		Map<String, String> cookies = response.getCookies();
		if (null == cookies)
			return;

		StringBuffer strCookie = new StringBuffer();
		String PHPSESSID = null;
		for (String cookieKey : cookies.keySet()) {
			if (cookieKey.startsWith("PHPSESSID")) {
				PHPSESSID = cookies.get(cookieKey);
			}
			if (cookieKey.startsWith("PHPSESSID")
					|| cookieKey.startsWith("prsess")
					|| cookieKey.startsWith("login")) {
				strCookie.append(cookieKey).append("=")
						.append(cookies.get(cookieKey)).append("; ");
			}
		}
		context.setCookie(strCookie.toString());
		context.setPhpSession(PHPSESSID);
	}
}
