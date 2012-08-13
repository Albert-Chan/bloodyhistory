package action;

import java.util.Timer;
import java.util.TimerTask;

import messenger.AbstractMessenger;

public abstract class AbstractAction implements IAction {
	protected ActionContext context;
	
	protected AbstractMessenger messenger;
	
	private int delay;

	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}
	
	public Timer schedule() {
		Timer timer = new Timer();
		timer.schedule(prepareTask(), getDelay());
		return timer;
	}

	public abstract TimerTask prepareTask(); 

}
