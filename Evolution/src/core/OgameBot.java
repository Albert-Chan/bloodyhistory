package core;

import network.HttpClient;
import network.UngzipHandler;
import action.executor.Scheduler;

public class OgameBot {

	private Context context;
	private Scheduler scheduler;

	public void initialize() {
		initializeContext();
		createHttpClient();
		scheduler = createScheduler();
	}

	private void initializeContext() {
		context = new Context();
		context.setGameServer("uni108.ogame.tw");
		context.setUser("bazinga");
		context.setPass("11111111");
//		context.setUser("albert");
//		context.setPass("fuckfuck");
	}

	private HttpClient createHttpClient() {
		HttpClient client = new HttpClient(context.getGameServer(), 80);
		client.registerResponseHandler(new UngzipHandler());
		context.setClient(client);
		return client;
	}

	private Scheduler createScheduler() {
		Scheduler scheduler = new Scheduler(context);
		return scheduler;
	}

	public void start() {
		scheduler.run();
	}

	public static void main(String[] args) {
		OgameBot bot = new OgameBot();
		bot.initialize();

		bot.start();
	}

}
