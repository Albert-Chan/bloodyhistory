package data;

import java.util.ArrayList;

import theory.validator.CallBackHandler;

public abstract class StockInfo {

	ArrayList<CallBackHandler> handlers = new ArrayList<CallBackHandler>();

	String stockId;
	String stockName;
	ArrayList<IDealData> deal = new ArrayList<IDealData>();
	ArrayList<IExtendedData> extended = null;

	public StockInfo(String stockId, String stockName) {
		this.stockId = stockId;
		this.stockName = stockName;
	}

	public abstract IDealData createDealData();

	public abstract IDealData[] getDealArray();

	public boolean add(String dealData) {
		IDealData data = createDealData();
		if (!data.build(dealData)) {
			System.out.println("Data error on " + stockId + ": " + stockName);
			return false;
		}
		deal.add(data);
		return true;
	}

	public void attach(ArrayList<IExtendedData> extended) {
		this.extended = extended;
	}

	public String getStockId() {
		return stockId;
	}

	public String getStockName() {
		return stockName;
	}

	public void setHandlers(CallBackHandler... handlers) {
		for (CallBackHandler handler : handlers)
			this.handlers.add(handler);
	}

	public void analysize() {
		for (CallBackHandler handler : handlers) {
			if (null != handler)
				handler.exec(this);
		}
	}
}
