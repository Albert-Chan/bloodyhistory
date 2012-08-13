package messenger;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class HttpPackageReader {

	public HashMap<String, Package> read(URL url) throws IOException,
			ParserConfigurationException, SAXException {
		InputStream input = url.openStream();
		try {
			HashMap<String, Package> packages = new HashMap<String, Package>();
			InputSource source = new InputSource(url.openStream());
			SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
			parser.parse(source, new HttpPackageHandler(packages));
			return packages;
		} finally {
			input.close();
		}
	}
	
	public static void main(String[] args)
	{
		HttpPackageReader reader = new HttpPackageReader();
		try {
			reader.read(HttpPackageReader.class.getResource("http_packages.xml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
