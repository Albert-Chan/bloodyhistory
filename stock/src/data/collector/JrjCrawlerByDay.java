package data.collector;

import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.json.JSONArray;
import org.json.JSONObject;

import connection.HttpConnection;

public class JrjCrawlerByDay {

	protected static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(
			"yyyy-MM-dd");

	private static final int MAX_CONNECTION = 16;

	private static String baseDir = null;

	public static void main(String[] args) {

		File base = new File("D:/StockAnalysis/data/mx",
				DATE_FORMAT.format(new Date()));
		if (base.exists()) {
			System.out.print("The base directory already exists!");
			return;
		} else {
			base.mkdir();
		}
		baseDir = base.getAbsolutePath() + "\\";
		if (baseDir == null) {
			System.out.print("Base directory is not created!");
			return;
		}
		String path = "D:/StockAnalysis/data/day";
		File dir = new File(path);
		if (!dir.isDirectory()) {
			System.out.println(path + " is not a directory.");
		}

		ExecutorService executor = Executors.newFixedThreadPool(MAX_CONNECTION);

		for (File file : dir.listFiles()) {
			String stockId = file.getName();
			stockId = stockId.substring(2, 8);
			try {
				executor.execute(new GetStockInfoThread(stockId));
			} catch (Exception e) {
				System.out.println(e.getMessage());
				System.out.println("Downloading " + stockId + " failed.");
			}
		}
		executor.shutdown();
	}

	static class GetStockInfoThread extends Thread {
		String stockId;

		public GetStockInfoThread(String stockId) {
			this.stockId = stockId;
		}

		public void run() {
			try {
				tryGetStockByID(stockId);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				System.out.println("Downloading " + stockId + " failed.");
			}
		}

		private void tryGetStockByID(String stockId) throws Exception {
			try {
				getStockByID(stockId);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				System.out.println("Problem on Stock " + stockId);
				getStockByID(stockId);
				System.out.println("Retrying " + stockId + " succeed.");
			}
		}

		private void getStockByID(String id) throws Exception {

			ArrayList<JSONObject> array = new ArrayList<JSONObject>();

			String lastResponse = null;
			int pageIndex = 1;
			while (true) {

				String url = "http://qmx.jrjimg.cn/mx.do?code=" + id + "&page="
						+ pageIndex + "&size=120";
				pageIndex++;

				String result = HttpConnection.getData(url, "gbk");

				String jsonText = result.substring(result.indexOf('{'));

				String compareString = jsonText.substring(jsonText
						.indexOf("DetailData"));
				if (compareString.equals(lastResponse))
					break;
				else
					lastResponse = compareString;

				JSONObject json = new JSONObject(jsonText);
				JSONArray jsonArray = json.getJSONArray("DetailData");

				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject arrayElement = jsonArray.getJSONObject(i);
					array.add(arrayElement);
				}
			}

			PrintWriter writer = new PrintWriter(baseDir + id + ".txt", "utf8");
			for (JSONObject object : array)
				writer.println(object);
			writer.close();

		}
	}

}
