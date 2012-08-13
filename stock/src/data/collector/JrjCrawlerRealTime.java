package data.collector;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class JrjCrawlerRealTime {

	public static void main(String[] args) {

		getStockByID("600000");

	}

	public static void getStockByID(String id) {

		ArrayList<JSONObject> array = new ArrayList<JSONObject>();
		String lastResponse = null;
		while (true) {
			String url = "http://qmx.jrjimg.cn/mx.do?code=" + id
					+ "&page=1&size=3";
			// pageIndex++;

			try {

				URL u = new URL(url);
				byte[] bytes = new byte[256];
				InputStream in = null;
				ByteArrayOutputStream out = new ByteArrayOutputStream();

				try {
					in = u.openStream();
					int size;
					while ((size = in.read(bytes)) != -1) {
						out.write(bytes, 0, size);
					}
					String result = out.toString("gbk");
					String jsonText = result.substring(result.indexOf('{'));

					String compareString = jsonText.substring(jsonText
							.indexOf("DetailData"));
					// if (compareString.equals(lastResponse))
					// break;
					// else
					// lastResponse = compareString;

					JSONObject myjson = new JSONObject(jsonText);
					JSONArray jsonArray = myjson.getJSONArray("DetailData");

					for (int i = jsonArray.length() - 1; i >= 0; i--) {
						JSONObject arrayElement = jsonArray.getJSONObject(i);
						array.add(arrayElement);
					}

					for (JSONObject object : array)
						System.out.println(object);
					System.out.println("----------------------");
					out.reset();
					Thread.sleep(15000);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				} finally {
					if (in != null) {
						in.close();
					}
				}

			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
		}

	}
}
