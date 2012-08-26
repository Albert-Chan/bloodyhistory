package theory.validator;

import java.io.PrintWriter;
import java.util.ArrayList;

import stockdata.ExtendedData;
import stockdata.FiveMinData;
import stockdata.IExtendedData;
import stockdata.StockInfo;

public class Rush extends AbstractCallBackHandler {

	public Rush(PrintWriter writer) {
		super(writer);
	}

	public void step(StockInfo stock) {
		FiveMinData[] dealRecords = (FiveMinData[]) stock.getDealArray();
		ArrayList<IExtendedData> extended = new ArrayList<IExtendedData>();
		stock.attach(extended);
			
		// 4h * 12 = 48, Every day has 48 5minKs
		// calculate MA96 volume
		for (int i = 0; i < 96 - 1; i++) {
			extended.add(ExtendedData.EMPTY_DATA);
		}
		
		for (int i = 96 - 1; i < dealRecords.length; i++) {
			long total = 0;
			for (int j = i - 95; j <= i; j++) {
				total = total + dealRecords[i].volume;
			}
			long ma = total/96;
			ExtendedData ed = new ExtendedData();
			ed.averageVolume = ma;
			extended.add(ed);
		}
		
		// Rising rate
//		for (int i )
	}


}
