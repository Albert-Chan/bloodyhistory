package functions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import data.pojo.Data;
import data.pojo.Datum;

public class FunctionUtil {
	public static Data MA(Data data, String aggPropertyName, int avgPeriod) {
		return data.collapsedMap(avgPeriod, 1, l -> {
			double sum = 0.0;
			Datum lastDatum = null;
			for (Datum datum : l) {
				sum += (double) datum.get(aggPropertyName);
				lastDatum = datum;
			}
			if (lastDatum != null) {
				lastDatum.setProperty("MA" + avgPeriod, sum / l.size());
			}
			return lastDatum;
		});
	}

	public static void Signal() {
		
	}

	public static void EMA(ArrayList<Datum> data, int avgPeriod) {
//		double sum = 0d;
//		for (int j = 0; j < data.size(); j++) {
//			for (int i = 0; i < avgPeriod; i++) {
//				double value = data.get(i).get(" ");
//				sum += value;
//			}
//		}
//		return sum;

		// double smoothRating = 2d/(avgPeriod+1);
		// double todayAvgIndex = smoothRating *
		// (previousClosing-previusAvgIndex) + previusAvgIndex;
		// return smoothRating *
	}

}
