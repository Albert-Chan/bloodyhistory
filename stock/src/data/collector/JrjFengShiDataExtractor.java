package data.collector;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import stockdata.FengShiStockInfo;
import stockdata.StockInfo;
import theory.validator.ICallBackHandler;
import theory.validator.TenPercent;

public class JrjFengShiDataExtractor {

	ICallBackHandler handler;

	public JrjFengShiDataExtractor(ICallBackHandler handler) {
		this.handler = handler;
	}

	public static void main(String[] args) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter("D:\\checkRush.txt", "utf8");

			try {
				if (writer != null)
					new JrjFengShiDataExtractor(new TenPercent(writer))
							.extract("D:\\mx\\20120725");
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
	 * extract the data by day
	 * 
	 * @param path
	 */
	public StockInfo extractStock(File stockFile, String stockIdForCheck)
			throws IOException {

		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(stockFile), "GBK"));

			StockInfo stock = new FengShiStockInfo(stockFile.getName(), null);
			String line = null;
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
		return FengShiStockInfo.ERROR_INFO;
	}
}
