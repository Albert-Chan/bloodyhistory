package event;

public class EventFactory {
	public static AbstractEvent createEvent(String eventType, String rawData ){
		
			if ("flight".equalsIgnoreCase(eventType)) {
				return FlightOwnAttackEvent.parse( );
			} else if ("return".equalsIgnoreCase(eventType)) {
				return new ReturnOwnAttackEvent();
			} else {
				return new UnknownEvent(eventType);
			}
}
