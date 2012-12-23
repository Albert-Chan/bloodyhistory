package action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

import core.Context;

public abstract class AbstractAction implements IAction {
	private static final ReentrantLock lock = new ReentrantLock();

	protected Context context;

	private ArrayList<IAction> preActions = new ArrayList<IAction>();

	public AbstractAction(Context context) {
		this.context = context;
		setWhen(System.currentTimeMillis());
	}

	private long when;

	public long getWhen() {
		return this.when;
	}

	public void setWhen(long when) {
		this.when = when;
	}

	public void addPreAction(IAction action) {
		preActions.add(action);
	}

	public ArrayList<IAction> getPreActions() {
		return preActions;
	}

	public void perform() {
		lock.lock();
		try {
			exec();
		} catch (IOException e) {
		} finally {
			lock.unlock();
		}
	}

	protected abstract void exec() throws IOException;

}
