package theory.validator;

import stockdata.DayData;
import stockdata.StockInfo;

public class VolumeVSPrice implements ICallBackHandler {
	public void operate(StockInfo stock) {
		DayData[] dealRecords = (DayData[]) stock.getDealArray();
		long subTotalVolume = 0;

		int currentPositive = 0;
		int currentNegative = 0;

		int nextPositive = 0;
		int nextNegative = 0;

		for (int i = 0; i < dealRecords.length; i++) {
			if (i < 4) {
				continue;
			}

			subTotalVolume = dealRecords[i].volume + dealRecords[i - 1].volume
					+ dealRecords[i - 2].volume + dealRecords[i - 3].volume
					+ dealRecords[i - 4].volume;

			long averageTotalVolume = subTotalVolume / 5;

			if (dealRecords[i].volume > 1.5 * averageTotalVolume) {

				if (dealRecords[i].closing >= dealRecords[i].opening)
					currentPositive++;
				else
					currentNegative++;

				if (i + 1 < dealRecords.length) {
					if (dealRecords[i + 1].closing >= dealRecords[i + 1].opening)
						nextPositive++;
					else
						nextNegative++;
				}
			}
		}
		System.out.println(stock.getStockId() + ": " + stock.getStockName());
		System.out.println("+:" + currentPositive);
		System.out.println("-:" + currentNegative);
		System.out.println(">+:" + nextPositive);
		System.out.println(">-:" + nextNegative);
	}
}
