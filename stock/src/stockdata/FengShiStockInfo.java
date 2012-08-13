package stockdata;

public class FengShiStockInfo extends StockInfo {
	public static final FengShiStockInfo ERROR_INFO = new FengShiStockInfo("errorId",
			"errorName");
	
	public FengShiStockInfo(String stockId, String stockName) {
		super(stockId, stockName);
	}
	
	public IDealData createDealData()
	{
		return new FengShiData();
	}
}
