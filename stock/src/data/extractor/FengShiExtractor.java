package data.extractor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import theory.validator.CallBackHandler;
import data.FengShiStockInfo;
import data.StockInfo;

public class FengShiExtractor {

	CallBackHandler handler;

	public FengShiExtractor(CallBackHandler handler) {
		this.handler = handler;
	}

	public static void main(String[] args) {
		Properties props = new Properties();
		try {
			FileInputStream in = new FileInputStream(
					"D:/StockAnalysis/config.ini");
			props.load(in);
			in.close();
		} catch (FileNotFoundException e3) {
			System.out.println(e3.getLocalizedMessage());
		} catch (IOException e3) {
			System.out.println(e3.getLocalizedMessage());
		}

		try {

			CallBackHandler handler = null;// new TenPercent();
			String baseDir = props.getProperty("baseDir");
			if (baseDir == null)
				new FengShiExtractor(handler).extract(baseDir + "/data/mx");

		} catch (IOException e) {
			System.out.println(e.getLocalizedMessage());
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
