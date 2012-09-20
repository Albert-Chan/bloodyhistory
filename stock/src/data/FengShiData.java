package data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

public class FengShiData implements IDealData {
	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"HH:mm:ss");

	public Date date;
	public double price;
	public long volume;
	public double amount;
	public long A4;
	public boolean raise;

	public boolean build(String raw) {

		try {
			JSONObject obj = new JSONObject(raw);
			String a1 = obj.getString("A1");
			String a2 = obj.getString("A2");
			String a3 = obj.getString("A3");
			String a4 = obj.getString("A4");
			String a5 = obj.getString("A5");
			String a6 = obj.getString("A6");
			
			price = Double.valueOf(a1);
			volume = Long.valueOf(a2);
			amount = Double.valueOf(a3);
			A4 = Long.valueOf(a4);
			date = dateFormat.parse(a5);
			raise = Long.valueOf(a6) == 1 ? true: false;

		} catch (ParseException e) {
			System.out.println(e.getMessage());
			return false;
		} catch (JSONException e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}
}