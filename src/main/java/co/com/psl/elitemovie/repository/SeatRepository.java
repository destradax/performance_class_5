package co.com.psl.elitemovie.repository;

import co.com.psl.elitemovie.model.Seat;

public interface SeatRepository {

	void save(Seat seat);

	void update(Seat seat);
}
