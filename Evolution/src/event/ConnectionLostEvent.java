package event;

import action.IAction;
import action.LoginAction;
import core.Context;

public class ConnectionLostEvent extends AbstractEvent {

	
	public IAction trigger(Context context) {
		LoginAction login = new LoginAction(context);
		login.setWhen(System.currentTimeMillis());
		return login;
	}

	public void dismiss(Context context) {
		throw new UnsupportedOperationException();
	}

}
