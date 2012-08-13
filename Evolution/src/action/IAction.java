package action;

import java.util.Timer;

public interface IAction {
	int LOGIN = 0;
	int SENDFLEET = 1;
	int OVERVIEW = 2;
	int BUILDING = 3;
	int RESEARCH = 4;

	int getDelay();

	void setDelay(int delay);

	Timer schedule();

	// TimerTask prepareTask();
}
