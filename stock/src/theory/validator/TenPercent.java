package theory.validator;

import java.io.PrintWriter;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import stockdata.DayData;
import stockdata.StockInfo;

public class TenPercent extends AbstractCallBackHandler {
	private static final SimpleDateFormat df = new SimpleDateFormat(
			"MM/dd/yyyy");
	private static Date baseline = null;
	static {
		try {
			baseline = df.parse("06/23/2012");
		} catch (ParseException e) {
			System.out.println(e.getMessage());
		}
	}

	public TenPercent(PrintWriter writer) {
		super(writer);
	}

	public void operate(StockInfo stock) {
		boolean printTitle = true;
		DayData[] dealRecords = (DayData[]) stock.getDealArray();

		for (int i = 1; i < dealRecords.length; i++) {

			if (i + 1 == dealRecords.length)
				break;
			if (dealRecords[i].date.before(baseline))
				continue;

			double raiseRate = (dealRecords[i].closing - dealRecords[i - 1].closing)
					/ dealRecords[i - 1].closing;

			if (raiseRate > 0.09) {
				if (printTitle) {
					writer.println(stock.getStockId() + ":"
							+ stock.getStockName()
							+ "------------------------------------------");
					writer.println("openVsLastClose\tcloseVsLastClose\tcloseVsOpen\tcloseVsHighest\tcloseVsLowest\topenVsHighest\topenVsLowest");
					printTitle = false;
				}

				AnlaysisData anlaysis = new AnlaysisData();
				anlaysis.openVsLastClosing = (dealRecords[i + 1].opening - dealRecords[i].closing)
						/ dealRecords[i].closing;
				anlaysis.closeVsLastClosing = (dealRecords[i + 1].closing - dealRecords[i].closing)
						/ dealRecords[i].closing;

				anlaysis.closeVsOpening = (dealRecords[i + 1].closing - dealRecords[i + 1].opening)
						/ dealRecords[i + 1].closing;
				anlaysis.closeVsHighest = (dealRecords[i + 1].closing - dealRecords[i + 1].highest)
						/ dealRecords[i + 1].closing;
				anlaysis.closeVsLowest = (dealRecords[i + 1].closing - dealRecords[i + 1].lowest)
						/ dealRecords[i + 1].closing;
				anlaysis.openVsHighest = (dealRecords[i + 1].opening - dealRecords[i + 1].highest)
						/ dealRecords[i + 1].opening;
				anlaysis.openVsLowest = (dealRecords[i + 1].opening - dealRecords[i + 1].lowest)
						/ dealRecords[i + 1].opening;

				writer.print(df.format(anlaysis.openVsLastClosing * 100) + "%"
						+ "\t\t\t\t");
				writer.print(df.format(anlaysis.closeVsLastClosing * 100) + "%"
						+ "\t\t\t\t");
				writer.print(df.format(anlaysis.closeVsOpening * 100) + "%"
						+ "\t\t\t");
				writer.print(df.format(anlaysis.closeVsHighest * 100) + "%"
						+ "\t\t\t");
				writer.print(df.format(anlaysis.closeVsLowest * 100) + "%"
						+ "\t\t\t");
				writer.print(df.format(anlaysis.openVsHighest * 100) + "%"
						+ "\t\t\t");
				writer.print(df.format(anlaysis.openVsLowest * 100) + "%"
						+ "\t\t\t");
				writer.println();

			}

		}

	}
}

class AnlaysisData {

	double openVsLastClosing;
	double closeVsLastClosing;
	double closeVsOpening;
	double closeVsHighest;
	double closeVsLowest;
	double openVsHighest;
	double openVsLowest;

}
