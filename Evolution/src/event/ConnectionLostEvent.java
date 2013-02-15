package event;

import action.IAction;
import action.LoginAction;
import core.Context;

public class ConnectionLostEvent extends AbstractEvent {

	@Override
	public IAction trigger(Context context) {
		LoginAction login = new LoginAction(context);
		login.setWhen(System.currentTimeMillis());
		return login;
	}

}
