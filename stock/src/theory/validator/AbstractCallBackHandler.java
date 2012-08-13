package theory.validator;

import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import stockdata.StockInfo;

public abstract class AbstractCallBackHandler implements ICallBackHandler {
	protected PrintWriter writer;

	protected static DecimalFormat df = new DecimalFormat("#.000");
	protected static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy-MM-dd");

	public abstract void operate(StockInfo stock);

	public AbstractCallBackHandler(PrintWriter writer) {
		this.writer = writer;
	}
}
