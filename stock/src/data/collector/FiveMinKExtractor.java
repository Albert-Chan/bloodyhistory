package data.collector;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import stockdata.DayStockInfo;
import stockdata.FiveMinStockInfo;
import stockdata.StockInfo;
import theory.validator.IStepExecutor;
import theory.validator.Rush;

public class FiveMinKExtractor {

	IStepExecutor handler;

	public FiveMinKExtractor(IStepExecutor handler) {
		this.handler = handler;
	}

	public static void main(String[] args) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter("D:/StockAnalysis/RisingRush.txt", "utf8");

			try {
				if (writer != null)
					new FiveMinKExtractor(new Rush(writer))
							.extract("D:/StockAnalysis/data/5min");
			} catch (IOException e) {
				System.out.println(e.getLocalizedMessage());
			}
			writer.close();
		} catch (FileNotFoundException e1) {
			System.out.println(e1.getLocalizedMessage());
		} catch (UnsupportedEncodingException e2) {
			System.out.println(e2.getLocalizedMessage());
		}
	}

	public boolean extract(String path) throws IOException {
		File dir = new File(path);
		if (!dir.isDirectory()) {
			System.out.println(path + " is not a directory.");
		}

		for (File file : dir.listFiles()) {
			String stockIdForCheck = file.getName();
			// createTable ...
			StockInfo stock = extractStock(file, stockIdForCheck);
			if (null != stock && null != handler) {
				stock.setHandlers(handler);
				stock.analysize();
			}
		}
		return true;
	}

	/**
	 * extract the data by 5min
	 * 
	 * @param path
	 */
	public StockInfo extractStock(File stockFile, String stockIdForCheck)
			throws IOException {

		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(stockFile), "GBK"));
			// the first line
			String line = reader.readLine();
			String[] data1 = line.split(" ");

			String stockId = data1[0];
			String stockName = data1[1];
			StockInfo stock = new FiveMinStockInfo(stockId, stockName);

			stockIdForCheck = stockIdForCheck.substring(2, 8);
			if (!stockId.equals(stockIdForCheck)) {
				System.out.println("Need check " + stockId + " != "
						+ stockIdForCheck);
				return DayStockInfo.ERROR_INFO;
			}

			// insert this stock into stock table ...

			// the second line, ignore.
			reader.readLine();

			while (null != (line = reader.readLine())) {
				// insert the data for the stock of this date
				stock.add(line);
			}
			return stock;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			reader.close();
		}
		return DayStockInfo.ERROR_INFO;
	}
}
