package co.com.psl.elitemovie.repository;

import java.util.List;

import co.com.psl.elitemovie.model.Seat;
import co.com.psl.elitemovie.model.ShowTime;

public interface ShowTimeRepository {

	ShowTime findById(int id);

	List<ShowTime> findAll();

	void add(ShowTime showTime, List<Seat> seatList);

	void update(ShowTime showTime);
}
