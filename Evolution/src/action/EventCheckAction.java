package action;

import event.IEvent;
import gamelogic.Resource;

import java.io.IOException;
import java.util.List;

import messenger.Parameter;
import network.Response;
import core.Context;

public class EventCheckAction extends AbstractAction {

	public EventCheckAction(Context context) {
		super(context);
	}

	protected void exec() throws IOException, ActionFailureException {
		// [eventList]
		// @@GameServer@
		// @@cookie@
		Parameter server = new Parameter("@@GameServer@",
				context.getGameServer());
		Parameter cookie = new Parameter("@@cookie@", context.getCookie());

		String checkEvent = context.generator.generate("eventList", server,
				cookie);
		Response response = context.client.send(checkEvent);
		List<IEvent> eventList = context.inspector.getEvents(new String(
				response.getHttpContent(), "utf-8"));

		context.updateEvents(eventList);
		List<IEvent> newEvents = context.getNewEvents();
		for (IEvent event : newEvents) {
			event.trigger(context);
		}
		
		List<IEvent> vanishedEvents = context.getVanishedEvents();
		for (IEvent event : vanishedEvents) {
			event.dismiss(context);
		}
	}
}
