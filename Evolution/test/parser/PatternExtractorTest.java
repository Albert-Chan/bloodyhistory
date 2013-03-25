package parser;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import junit.framework.TestCase;
import event.EventFactory;
import event.IEvent;

public class PatternExtractorTest extends TestCase {
	public void testGetEvents() {

		String html = null;
		char[] cbuf = new char[1024];
		try {
			FileReader reader = new FileReader("attackedEvent2.html");
			StringBuilder sb = new StringBuilder();
			while (reader.read(cbuf) != -1) {
				sb.append(cbuf);
			}
			html = sb.toString();
			reader.close();
		} catch (IOException e) {
		}

		ArrayList<HashMap<String, String>> events = new PatternExtractor()
				.extract(html, "event");
		List<IEvent> eventList = new ArrayList<IEvent>();
	}
}
