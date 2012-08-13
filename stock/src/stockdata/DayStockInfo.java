package stockdata;

public class DayStockInfo extends StockInfo {

	public static final DayStockInfo ERROR_INFO = new DayStockInfo("errorId",
			"errorName");

	public DayStockInfo(String stockId, String stockName) {
		super(stockId, stockName);
	}

	public IDealData createDealData() {
		return new DayData();
	}
}
