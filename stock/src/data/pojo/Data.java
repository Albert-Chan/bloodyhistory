package data.pojo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class Data<T> {

	public static final Data<T> EMPTY = new Data();
	private List<T> data;

	private <T> Data() {
		List<T> d = Collections.emptyList();
		data = d;
	}

	public Data(List<T> data) {
		this.data = data;
	}

	public <U> Data<U> collapsedMap(int window, int step) {
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
		return new Data(list);
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
