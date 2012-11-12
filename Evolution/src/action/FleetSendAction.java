package action;

import gamelogic.Planet;
import gamelogic.Fleet;

import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.TimerTask;

import action.LoginAction.Login;

import messenger.IResult;
import messenger.LoginMessenger;
import messenger.Operation;
import network.Response;
import parser.LoginManager;
import core.SimpleTacticsGenerator;
import core.Tactics;

public class FleetSendAction extends AbstractAction {

	public FleetSendAction(String userName, String password) {
		this.userName = userName;
		this.password = password;
		this.messenger = new FleetSendMessenger();
	}
	
	public TimerTask prepareTask() {
		return new FleetSender();
	}

	class FleetSender extends TimerTask {
		public void run() {
			messenger.prepareParam(context.loginServer, context.gameServer,
					userName, password);
			if (messenger.getResponse() != IResult.success) {

			}
			if (respStr.indexOf("舰队出发") != -1) {
				System.out.println(getCurrentDateTime() + " Fleet to " + dest
						+ " was sent successfully!");
			} else if (respStr.indexOf("舰队无法被派遣！") != -1
					&& respStr.indexOf("没有选择任何船舰！") != -1) {
				System.out.println("No enough ships!");
			} else if (respStr.indexOf("舰队无法被派遣！") != -1
					&& respStr.indexOf("舰队数已达上限！") != -1) {
				System.out.println("No free fleet lines!");
			} else if (respStr.indexOf("舰队无法被派遣！") != -1
					&& respStr.indexOf("星球还没有人居住或是可以被殖民！") != -1) {
				System.out.println("The player of that planet has left.");
			} else if (respStr.indexOf("舰队无法被派遣！") != -1
					&& respStr.indexOf("该星球由于新手保护的原因不能被攻击！") != -1) {
				System.out.println("Newbie protection.");
			} else {
				System.out.println(respStr);
			}
		}
	}
}
