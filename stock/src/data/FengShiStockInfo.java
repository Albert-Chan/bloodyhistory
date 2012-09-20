package data;

public class FengShiStockInfo extends StockInfo {
	public static final FengShiStockInfo ERROR_INFO = new FengShiStockInfo(
			"errorId", "errorName", null);

	private String date = null;

	public FengShiStockInfo(String stockId, String stockName, String date) {
		super(stockId, stockName);
		this.date = date;
	}

	public FengShiData createDealData() {
		return new FengShiData(date);
	}

	public FengShiData[] getDealArray() {
		FengShiData[] dealRecords = deal.toArray(new FengShiData[0]);
		return dealRecords;
	}
	
	public boolean add(String dealData) {
		if (dealData.equals("数据来源:通达信"))
			return true;
		IDealData data = createDealData();
		if (!data.build(dealData)) {
			System.out.println("Data error on " + stockId + ": " + stockName);
			return false;
		}
		deal.add(data);
		return true;
	}
	
}
