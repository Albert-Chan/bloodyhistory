package event;

import java.util.LinkedList;

public class EventSuppressor {
	LinkedList<IEvent> eventList = new LinkedList<IEvent>();
	public boolean check( IEvent event )
	{
		for( IEvent e: eventList)
		{
			if( e.equals(event))
				return false;
		}
		return true;
	}
}
