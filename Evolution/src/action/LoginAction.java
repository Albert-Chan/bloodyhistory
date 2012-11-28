package action;

import gamelogic.Planet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import messenger.PackageGenerator;
import messenger.Parameter;
import network.HttpClient;
import network.Response;
import parser.Inspector;
import core.Context;

public class LoginAction extends AbstractAction {

	public LoginAction(Context context) {
		super(context);
	}

	public void login() throws IOException {
		HttpClient client = context.getClient();
		if (null == client)
			return;

		PackageGenerator generator = context.getGenerator();
		if (null == generator)
			return;

		Inspector inspector = context.getInspector();
		if (null == inspector)
			return;

		Parameter server = new Parameter("@@GameServer@",
				context.getGameServer());
		Parameter user = new Parameter("@@user@", context.getUser());
		Parameter pass = new Parameter("@@password@", context.getPass());

		String msg1 = generator.generate("login1", server, user, pass);
		Response response = client.send(msg1);
		parseCookie(response);

		Parameter phpSession = new Parameter("@@PHPSESSID@",
				context.getPhpSession());
		Parameter cookie = new Parameter("@@cookie@", context.getCookie());

		String msg2 = generator.generate("login2", server, phpSession, cookie);
		response = client.send(msg2);
		// System.out.println(response.getHttpHeader());
		// System.out.println(new String(response.getHttpContent(), "utf-8"));

		HashMap<String, Planet> cpMap = inspector.getCPMap(new String(response
				.getHttpContent(), "utf-8"));
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
