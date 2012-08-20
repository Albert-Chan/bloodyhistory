package stockdata;

public class FiveMinStockInfo extends StockInfo {

	public static final FiveMinStockInfo ERROR_INFO = new FiveMinStockInfo("errorId",
			"errorName");

	public FiveMinStockInfo(String stockId, String stockName) {
		super(stockId, stockName);
	}

	public IDealData createDealData() {
		return new FiveMinData();
	}
}
