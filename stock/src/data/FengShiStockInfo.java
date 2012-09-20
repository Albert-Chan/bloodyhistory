package data;

import java.util.TreeSet;

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
		for (FengShiData fengShiData : set) {
			deal.add(fengShiData);
		}
		FengShiData[] dealRecords = deal.toArray(new FengShiData[0]);
		return dealRecords;
	}

	TreeSet<FengShiData> set = new TreeSet<FengShiData>();

	public boolean add(String dealData) {
		if (dealData.equals("数据来源:通达信"))
			return true;
		FengShiData data = createDealData();
		if (!data.build(dealData)) {
			System.out.println("Data error on " + stockId + ": " + stockName);
			return false;
		}
		set.add(data);
		return true;
	}

}
