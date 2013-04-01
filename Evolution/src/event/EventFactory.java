package event;

import gamelogic.Coordinate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class EventFactory {

	private static final SimpleDateFormat dateTimeFormat = new SimpleDateFormat(
			"dd.MM.yyyy HH:mm:ss");
	private static final SimpleDateFormat timeFormat = new SimpleDateFormat(
			"HH:mm:ss");

	public static IEvent createEvent(HashMap<String, String> eventProperties) {
		// @@eventId@=[0-9]*
		// @@eventType@=.*
		// @@eventTitle@=.*
		// @@eventTime@=[0-9]{2}:[0-9]{2}:[0-9]{2}
		// @@originPlayer@=.*
		// @@originCoordName@=.*
		// @@originType@=.*
		// @@originCoord@=\\[[1-9]{1}:[0-9]{1,3}:[0-9]{1,2}\\]
		// @@destCoordName@=.*
		// @@destType@=.*
		// @@destCoord@=\\[[1-9]{1}:[0-9]{1,3}:[0-9]{1,2}\\]
		// @@fleetTotalNumber@=.*
		// @@fleetDetail@=[\\S\\s]*
		// @@!someChars@=.*
		// @@!QuotedString@=".*"

		IEvent event = null;

		String eventType = eventProperties.get("eventType");
		if (eventType.contains("hostile")) {
			event = new EnemyAttackEvent();
			String originCoord = eventProperties.get("originCoord");
			String originType = eventProperties.get("originType");
			String originPlayer = eventProperties.get("originPlayer");
			Coordinate subject = new Coordinate(originCoord, originType);
			subject.setName(eventProperties.get("originCoordName"));
			subject.setBelongTo(originPlayer);

			String destCoord = eventProperties.get("destCoord");
			String destType = eventProperties.get("destType");
			Coordinate object = new Coordinate(destCoord, destType);
			object.setName(eventProperties.get("destCoordName"));
			((EnemyAttackEvent) event).setSubject(subject);
			((EnemyAttackEvent) event).setObject(object);

		}
		event.setId(eventProperties.get("eventId"));
		String strTime = eventProperties.get("eventTime");
		try {
			Date time = timeFormat.parse(strTime);
			event.setWhen(time.getTime());
		} catch (ParseException e) {
			//throw new EventGenerationException(e);
			//TODO: log this event.
		}
		return event;
	}

	private static String parseString(HashMap<String, String> eventProperties,
			String propertyName) {
		String eventType = eventProperties.get(propertyName);
		return eventType;
	}

}
