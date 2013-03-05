package event;

import java.util.HashMap;

public class EventFactory {
	public static IEvent createEvent(HashMap<String, String> eventProperties) {
		return new EnemyAttackEvent();
	}
}
