package co.com.psl.elitemovie.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import co.com.psl.elitemovie.model.ShowTime;

@Component
public class DefaultShowTimeRepository implements ShowTimeRepository {

	private Map<Integer, ShowTime> showTimesById;

	@Override
	public ShowTime findById(int id) {
		return showTimesById.get(id);
	}

	@Override
	public void add(ShowTime showTime) {
		if (showTimesById == null) {
			showTimesById = new HashMap<Integer, ShowTime>();
		}
		showTimesById.put(showTime.getId(), showTime);
	}

	@Override
	public void update(ShowTime showTime) {
		if (showTimesById == null) {
			showTimesById = new HashMap<Integer, ShowTime>();
		}
		showTimesById.put(showTime.getId(), showTime);
	}

}
