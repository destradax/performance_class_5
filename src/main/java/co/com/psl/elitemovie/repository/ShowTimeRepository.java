package co.com.psl.elitemovie.repository;

import co.com.psl.elitemovie.model.ShowTime;

public interface ShowTimeRepository {

	ShowTime findById(int id);

	void add(ShowTime showTime);

	void update(ShowTime showTime);
}
