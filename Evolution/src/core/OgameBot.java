package core;

import network.HttpClient;
import network.UngzipHandler;
import action.executor.Scheduler;

public class OgameBot {

	private Context context;
	private Scheduler scheduler;

	public void initialize() {
		initializeContext();
		context.setClient(createHttpClient());
		scheduler = createScheduler();
	}

	private void initializeContext() {
		context = new Context();
		context.setGameServer("uni13.ogame.tw");
		context.setUser("albert");
		context.setPass("fuckfuck");
	}

	private HttpClient createHttpClient() {
		HttpClient client = new HttpClient(context.getGameServer(), 80);
		client.registerResponseHandler(new UngzipHandler());
		return client;
	}

	private Scheduler createScheduler() {
		Scheduler scheduler = new Scheduler();
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
