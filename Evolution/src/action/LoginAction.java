package action;

import java.util.TimerTask;

import messenger.IResult;
import messenger.LoginMessenger;

public class LoginAction extends AbstractAction {
	private String userName;
	private String password;

	public LoginAction(String userName, String password) {
		this.userName = userName;
		this.password = password;
		this.messenger = new LoginMessenger();
	}

	public TimerTask prepareTask() {
		return new Login();
	}

	class Login extends TimerTask {

		public void run() {
			messenger.prepareParam( context.loginServer, context.gameServer, userName, password );
			if (messenger.getResponse() != IResult.success)
			{
				
			}
			context.setCookie( ((LoginMessenger)messenger).getCookie() );
		}
	}
}
