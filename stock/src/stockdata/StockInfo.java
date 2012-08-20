package stockdata;

import java.util.ArrayList;

import theory.validator.ICallBackHandler;

public abstract class StockInfo {

	ArrayList<ICallBackHandler> handlers = new ArrayList<ICallBackHandler>();

	String stockId;
	String stockName;
	ArrayList<IDealData> deal = new ArrayList<IDealData>();
	ArrayList<IExtendedData> extended = null;

	public StockInfo(String stockId, String stockName) {
		this.stockId = stockId;
		this.stockName = stockName;
	}

	public abstract IDealData createDealData();

	public boolean add(String dealData) {
		IDealData data = createDealData();
		if (!data.build(dealData)) {
			System.out.println("Data error on " + stockId + ": " + stockName);
			return false;
		}
		deal.add(data);
		return true;
	}
	
	public void attach(ArrayList<IExtendedData> extended){
		this.extended = extended;
	}

	public String getStockId() {
		return stockId;
	}

	public String getStockName() {
		return stockName;
	}

	public IDealData[] getDealArray() {
		IDealData[] dealRecords = deal.toArray(new IDealData[0]);
		return dealRecords;
	}

	public void setHandlers(ICallBackHandler... handlers) {
		for (ICallBackHandler handler : handlers)
			this.handlers.add(handler);
	}

	public void analysize() {
		for (ICallBackHandler handler : handlers) {
			if (null != handler)
				handler.operate(this);
		}
	}
}
