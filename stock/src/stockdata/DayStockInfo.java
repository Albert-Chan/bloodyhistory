package stockdata;

import java.util.Date;

public class DayStockInfo extends StockInfo {

	public static final DayStockInfo ERROR_INFO = new DayStockInfo("errorId",
			"errorName");

	private DayData[] dealRecords = null;
	private int index = 0;

	public DayStockInfo(String stockId, String stockName) {
		super(stockId, stockName);
	}

	public DayData createDealData() {
		return new DayData();
	}

	public DayData[] getDealArray() {
		dealRecords = deal.toArray(new DayData[0]);
		return dealRecords;
	}

	public void exec(StepExecutor executor) {
		if (dealRecords == null)
			return;
		for (index = 0; index < dealRecords.length; index++) {
			executor.exec();
		}
	}
	
	public Date date() {
		return date(0);
	}

	public double open() {
		return open(0);
	}

	public double high() {
		return high(0);
	}

	public double low() {
		return low(0);
	}

	public double close() {
		return close(0);
	}

	public long volume() {
		return volume(0);
	}

	public double turnover() {
		return turnover(0);
	}

	public Date date(int offset) {
		if (validate(offset)) {
			return dealRecords[index - offset].date;
		}
		return null;
	}

	public double open(int offset) {
		if (validate(offset)) {
			return dealRecords[index - offset].opening;
		}
		return -1;
	}

	public double high(int offset) {
		if (validate(offset)) {
			return dealRecords[index - offset].highest;
		}
		return -1;
	}

	public double low(int offset) {
		if (validate(offset)) {
			return dealRecords[index - offset].lowest;
		}
		return -1;
	}

	public double close(int offset) {
		if (validate(offset)) {
			return dealRecords[index - offset].closing;
		}
		return -1;
	}

	public long volume(int offset) {
		if (validate(offset)) {
			return dealRecords[index - offset].volume;
		}
		return -1;
	}

	public double turnover(int offset) {
		if (validate(offset)) {
			return dealRecords[index - offset].turnover;
		}
		return -1;
	}

	private boolean validate(int offset) {
		if (dealRecords == null || index - offset < 0
				|| index - offset > dealRecords.length - 1)
			return false;
		return true;
	}
}
