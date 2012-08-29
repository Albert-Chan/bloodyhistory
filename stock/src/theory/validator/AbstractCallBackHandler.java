package theory.validator;

import java.io.PrintWriter;
import java.text.DecimalFormat;

public abstract class AbstractCallBackHandler {
	protected PrintWriter writer;

	protected static final DecimalFormat decimalFormat = new DecimalFormat("#.000");
//	protected static SimpleDateFormat dateFormat = new SimpleDateFormat(
//			"yyyy-MM-dd");

	//public abstract void step(StockInfo stock);

	public AbstractCallBackHandler(PrintWriter writer) {
		this.writer = writer;
	}
	
	public void setDecimalFormat()
	{
		
	}
	
	public void printPercentage( double number )
	{
		writer.print( decimalFormat.format(number * 100) + "%" );
	}
}
