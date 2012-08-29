package theory.validator;

import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import stockdata.DayStockInfo;

public class CopyHistory extends AbstractCallBackHandler implements IStepExecutor<DayStockInfo>{
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat(
			"MM/dd/yyyy");
	private static Date baseline = null;
	static {
		try {
			baseline = dateFormat.parse("06/23/2012");
		} catch (ParseException e) {
			System.out.println(e.getMessage());
		}
	}
	
	double saleAtNextOpen = 1;
	double saleAtNextHighest = 1;
	double saleAtNextLowest = 1;

	public CopyHistory(PrintWriter writer) {
		super(writer);
	}

	public void step(DayStockInfo stock) {
		// if (dealRecords[i].date.before(baseline))
		// continue;
		double rise = stock.close() - stock.open() / stock.open();

	}
	
	public void postHandle() {
		writer.println("saleAtNextOpen:\t" + saleAtNextOpen);
		writer.println("saleAtNextHighest:\t" + saleAtNextHighest);
		writer.println("saleAtNextLowest:\t" + saleAtNextLowest);
	}

}