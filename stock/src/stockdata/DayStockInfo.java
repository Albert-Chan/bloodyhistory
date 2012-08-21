package stockdata;

public class DayStockInfo extends StockInfo {

	public static final DayStockInfo ERROR_INFO = new DayStockInfo("errorId",
			"errorName");

	public DayStockInfo(String stockId, String stockName) {
		super(stockId, stockName);
	}

	public DayData createDealData() {
		return new DayData();
	}
	
	public DayData[] getDealArray() {
		DayData[] dealRecords = deal.toArray(new DayData[0]);
		return dealRecords;
	}
}
