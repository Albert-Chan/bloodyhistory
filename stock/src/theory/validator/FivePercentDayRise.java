package theory.validator;

import java.io.PrintWriter;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import stockdata.DayData;
import stockdata.StockInfo;

public class FivePercentDayRise extends AbstractCallBackHandler {
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

	public FivePercentDayRise(PrintWriter writer) {
		super(writer);
	}

	public void step(StockInfo stock) {
		boolean printTitle = true;
		DayData[] dealRecords = (DayData[]) stock.getDealArray();

		for (int i = 1; i < dealRecords.length; i++) {

			if (i + 1 == dealRecords.length)
				break;
			if (dealRecords[i].date.before(baseline))
				continue;
			// close at the highest
			boolean closeAtHighest = ((dealRecords[i].highest - dealRecords[i].closing) / dealRecords[i].opening) < 0.002;
			if (!closeAtHighest) {
				continue;
			}
			// 3% < raiseRate < 5%
			double raiseRate = (dealRecords[i].closing - dealRecords[i].opening)
					/ dealRecords[i].opening;

			if (raiseRate > 0.03 && raiseRate < 0.05 &&
					i >= 4
							&& dealRecords[i - 4].closing > dealRecords[i - 3].closing
							&& dealRecords[i - 3].closing > dealRecords[i - 2].closing
					&& dealRecords[i - 2].closing < dealRecords[i - 1].closing
					&& dealRecords[i -1 ].closing < dealRecords[i].closing) {
		
				if (printTitle) {
					writer.println(stock.getStockId() + ":"
							+ stock.getStockName()
							+ "------------------------------------------");
					writer.println("Date\t\t\t\t\t" +
							"closeVsOpen\thighestVsOpen\t" +
							"lowestVsOpen\tnextOpenVsClose\tnextHighestVsClose\tnextLowestVsClose\tnextCloseVsClose");
					printTitle = false;
				}

				AnlaysisData3 anlaysis = new AnlaysisData3();

				anlaysis.closeVsOpen = (dealRecords[i].closing - dealRecords[i].opening)
						/ dealRecords[i].opening;
				anlaysis.highestVsOpen= (dealRecords[i].highest - dealRecords[i].opening)
						/ dealRecords[i].opening;
				anlaysis.lowestVsOpen= (dealRecords[i].lowest - dealRecords[i].opening)
						/ dealRecords[i].opening;
				anlaysis.nextOpenVsClose= (dealRecords[i+1].opening - dealRecords[i].closing)
						/ dealRecords[i].closing;
				anlaysis.nextHighestVsClose= (dealRecords[i+1].highest - dealRecords[i].closing)
						/ dealRecords[i].closing;
				anlaysis.nextLowestVsClose= (dealRecords[i+1].lowest - dealRecords[i].closing)
						/ dealRecords[i].closing;
				anlaysis.nextCloseVsClose= (dealRecords[i+1].closing - dealRecords[i].closing)
						/ dealRecords[i].closing;
				
				writer.print(dateFormat.format(dealRecords[i].date) + "\t\t\t\t");

				writer.print(decimalFormat.format(anlaysis.closeVsOpen * 100) + "%"
						+ "\t\t\t\t");
				writer.print(decimalFormat.format(anlaysis.highestVsOpen * 100) + "%"
						+ "\t\t\t");
				writer.print(decimalFormat.format(anlaysis.lowestVsOpen * 100) + "%"
						+ "\t\t\t");
				writer.print(decimalFormat.format(anlaysis.nextOpenVsClose * 100) + "%"
						+ "\t\t\t");
				writer.print(decimalFormat.format(anlaysis.nextHighestVsClose * 100) + "%"
						+ "\t\t\t");
				writer.print(decimalFormat.format(anlaysis.nextLowestVsClose * 100) + "%"
						+ "\t\t\t");
				writer.print(decimalFormat.format(anlaysis.nextCloseVsClose * 100) + "%"
						+ "\t\t\t");
				writer.println();

				saleAtNextOpen *= (1 + anlaysis.nextOpenVsClose);
				saleAtNextHighest *= (1 + anlaysis.nextHighestVsClose);
				saleAtNextLowest *= (1 + anlaysis.nextLowestVsClose);
			}

		}
	}
	
	public void postHandle() {
		writer.println("saleAtNextOpen:\t" + saleAtNextOpen);
		writer.println("saleAtNextHighest:\t" + saleAtNextHighest);
		writer.println("saleAtNextLowest:\t" + saleAtNextLowest);
	}

}

class AnlaysisData3 {
	double closeVsOpen;
	double highestVsOpen;
	double lowestVsOpen;
	double nextOpenVsClose;
	double nextHighestVsClose;
	double nextLowestVsClose;
	double nextCloseVsClose;
}
