package action;

import java.util.ArrayList;

public interface IAction {

	long getWhen();

	void setWhen(long when);
	
	long getPeriod();
	
	void setPeriod(long period);

	void perform();

	public void addPreAction(IAction action);

	public ArrayList<IAction> getPreActions();
}
