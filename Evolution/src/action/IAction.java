package action;

import java.io.IOException;
import java.util.ArrayList;

public interface IAction {
	int LOGIN = 0;
	int SENDFLEET = 1;
	int OVERVIEW = 2;
	int BUILDING = 3;
	int RESEARCH = 4;

	long getWhen();

	void setWhen(long when);

	void act() throws IOException;

	public void addPreAction(IAction action);

	public ArrayList<IAction> getPreActions();
}
