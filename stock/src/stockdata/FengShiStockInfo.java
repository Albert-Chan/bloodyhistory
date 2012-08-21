package stockdata;

public class FengShiStockInfo extends StockInfo {
	public static final FengShiStockInfo ERROR_INFO = new FengShiStockInfo(
			"errorId", "errorName");

	public FengShiStockInfo(String stockId, String stockName) {
		super(stockId, stockName);
	}

	public FengShiData createDealData() {
		return new FengShiData();
	}
	
	public FengShiData[] getDealArray() {
		FengShiData[] dealRecords = deal.toArray(new FengShiData[0]);
		return dealRecords;
	}
}
