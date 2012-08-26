package theory.validator;

import stockdata.StockInfo;

public interface ICallBackHandler {
	public void step(StockInfo stock);
}
