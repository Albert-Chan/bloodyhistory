package data.collector;

import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.json.JSONObject;

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
			executor.execute(new GetJrJFengShiStockInfoThread(stockId,
					new PrintWriterPostHandler()));
		}
		executor.shutdown();
	}

	static class PrintWriterPostHandler implements ICrawlerPostHandler {
		public void handle(String stockId, ArrayList<JSONObject> array)
				throws Exception {
			PrintWriter writer = new PrintWriter(baseDir + stockId + ".txt",
					"utf8");
			for (JSONObject object : array)
				writer.println(object);
			writer.close();
		}
	}

}
