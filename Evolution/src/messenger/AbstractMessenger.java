package messenger;

import network.HttpClient;

public abstract class AbstractMessenger {
	protected HttpClient client = new HttpClient();
	
	public abstract boolean prepareParam(String... args);

	public abstract int getResponse();
}
