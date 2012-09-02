package theory.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import data.DayData;
import data.DayStockInfo;
import data.StockInfo;


public class CopyHistory extends CallBackHandler {
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

	public void exec(StockInfo stock) {
		DayData[] dayData = ((DayStockInfo) stock).getDealArray();

		// 1. rise match
		ArrayList<Integer> candidates = new ArrayList<Integer>();
		for (int i = 0; i < dayData.length; i++) {
			candidates.add(i);
		}

		int currentIndex = dayData.length - 1;
		ArrayList<Integer> matches1 = riseMatch(dayData, currentIndex, 0,
				candidates);
		ArrayList<Integer> matches2 = riseMatch(dayData, currentIndex - 1, -1,
				matches1);
		ArrayList<Integer> matches3 = riseMatch(dayData, currentIndex - 2, -1,
				matches2);
		ArrayList<Integer> matches4 = riseMatch(dayData, currentIndex - 3, -1,
				matches3);
		ArrayList<Integer> matches5 = riseMatch(dayData, currentIndex - 4, -1,
				matches4);

		for (int index : matches5) {
			writer.println(dayData[index + 4].date);
		}

	}

	private ArrayList<Integer> riseMatch(DayData[] dayData, int currentIndex,
			int offset, ArrayList<Integer> candidates) {
		DayData recent = dayData[currentIndex];
		double rise = recent.close - recent.open / recent.open;
		ArrayList<Integer> matches = new ArrayList<Integer>();
		for (int i : candidates) {
			DayData cursor = dayData[i + offset];
			double cursorRise = cursor.close - cursor.open / cursor.open;
			if (cursorRise < rise * 1.2 && cursorRise > rise * 0.8) {
				matches.add(i + offset);
			}
		}
		return matches;
	}

	public void postHandle() {
		writer.println("saleAtNextOpen:\t" + saleAtNextOpen);
		writer.println("saleAtNextHighest:\t" + saleAtNextHighest);
		writer.println("saleAtNextLowest:\t" + saleAtNextLowest);
	}

}