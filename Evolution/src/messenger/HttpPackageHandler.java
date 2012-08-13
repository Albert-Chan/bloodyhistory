package messenger;

import java.util.HashMap;
import java.util.Stack;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class HttpPackageHandler extends DefaultHandler {

	private HashMap<String, Package> packages = null;

	private Stack<ParseState> states = new Stack<ParseState>();

	public HttpPackageHandler(HashMap<String, Package> packages) {
		this.packages = packages;
		states.push(new RootState());
	}

	public void startElement(String uri, String localName, String rawName,
			Attributes attrs) throws SAXException {
		ParseState state = (ParseState) states.peek();
		state = state.startElement(rawName);
		state.parseAttrs(attrs);
		states.push(state);
	}

	public void endElement(String uri, String localName, String rawName)
			throws SAXException {
		ParseState elementState = (ParseState) states.pop();
		elementState.processText();
		ParseState state = (ParseState) states.peek();
		state.endElement(elementState);
	}

	public void characters(char[] ch, int start, int length)
			throws SAXException {
		if (!states.isEmpty()) {
			states.peek().text.append(ch, start, length);
		}
	}

	private static class ParseState {

		protected StringBuffer text = new StringBuffer();

		public void parseAttrs(Attributes attrs) {
		}
		
		public void processText(){
			
		}

		public ParseState startElement(String tagName) {
			return AnyElementState.instance;
		}

		public void endElement(ParseState state) {
		}

		private static class AnyElementState extends ParseState {

			private static AnyElementState instance = new AnyElementState();

			public ParseState startElement(String tagName) {
				return instance;
			}
		}
	}

	private final static String TAG_PACKAGES = "packages";
	private final static String TAG_PACKAGE = "package";
	private final static String ATTR_NAME = "name";
	private final static String ATTR_PARAM = "param";

	private class RootState extends ParseState {

		public ParseState startElement(String tagName) {
			String tagValue = tagName.toLowerCase();
			if (TAG_PACKAGES.equals(tagValue)) {
				return new PackagesState();
			}
			return super.startElement(tagName);
		}
	}

	private class PackagesState extends ParseState {

		public ParseState startElement(String tagName) {
			String tagValue = tagName.toLowerCase();
			if (TAG_PACKAGE.equals(tagValue)) {
				return new PackageState();
			}
			return super.startElement(tagName);
		}
	}

	private class PackageState extends ParseState {

		private Package pack = null;

		public void parseAttrs(Attributes attrs) {
			String name = getStringValue(attrs, ATTR_NAME);
			if (name != null) {
				pack = new Package();
				pack.setName(name);
				packages.put(name, pack);
			}
			String params = getStringValue(attrs, ATTR_PARAM);
			if (params != null && pack != null) {
				pack.setParams(params);
			}
		}

		public void processText(){
			pack.setContent(text.toString());
		}
	}

	private String getStringValue(Attributes attrs, String name) {
		String value = attrs.getValue(name);
		if (value != null) {
			value = value.trim();
			if (value.length() != 0) {
				return value;
			}
		}
		return null;
	}

	private int getIntValue(Attributes attrs, String attrName, int defaultValue) {
		String value = attrs.getValue(attrName);
		if (value == null) {
			return defaultValue;
		}
		return Integer.parseInt(value);
	}

	private int getHexValue(Attributes attrs, String attrName, int defaultValue) {
		String value = attrs.getValue(attrName);
		if (value == null) {
			return defaultValue;
		}
		return Integer.parseInt(value, 16);
	}

	private int getCharValue(String value) {
		if (value.length() == 1) {
			return value.charAt(0);
		} else if (value.matches("\\\\u\\p{XDigit}{4}")) //$NON-NLS-1$
		{
			String unicode = value.substring(2);
			return Integer.parseInt(unicode, 16);
		}
		return -1;
	}
}