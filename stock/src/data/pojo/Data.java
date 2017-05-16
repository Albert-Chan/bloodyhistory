package data.pojo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class Data {

	public static final Data EMPTY = new Data();
	private List<Datum> data;

	private Data() {
		List<Datum> d = Collections.emptyList();
		data = d;
	}

	public Data(List<Datum> data) {
		this.data = data;
	}

	public Data collapsedMap(int window, int step, Function<List<Datum>, Datum> f) {
		if (data.size() < window) {
			return EMPTY;
		}

		List<List<Datum>> list = new ArrayList<>();
		for (int i = window - 1; i < data.size(); i++) {
			List<Datum> windowData = new ArrayList<>();
			for (int j = i - (window - 1); j <= i; j++) {
				windowData.add(data.get(j));
			}
			list.add(windowData);
		}

		List<Datum> result = new ArrayList<Datum>();
		for (List<Datum> l : list) {
			result.add(f.apply(l));
		}
		return new Data(result);
	}

}
