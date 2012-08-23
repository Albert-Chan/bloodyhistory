package stockdata;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DayData implements IDealData {
	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"MM/dd/yyyy");

	public Date date;
	public double opening;
	public double highest;
	public double lowest;
	public double closing;
	public long volume;
	public double turnover;

	public boolean build(String raw) {
		if (raw.equals("数据来源:通达信"))
			return true;
		String[] data = raw.split("\t");
		if (data.length != 7) {
			return false;
		}

		try {
			date = dateFormat.parse(data[0]);
		} catch (ParseException e) {
			System.out.print(e.getMessage());
			return false;
		}
		opening = Double.valueOf(data[1]);
		highest = Double.valueOf(data[2]);
		lowest = Double.valueOf(data[3]);
		closing = Double.valueOf(data[4]);
		volume = Long.valueOf(data[5]);
		turnover = Double.valueOf(data[6]);
		return true;
	}

}