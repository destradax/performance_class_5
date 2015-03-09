package co.com.psl.elitemovie.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.com.psl.elitemovie.model.Seat;
import co.com.psl.elitemovie.model.ShowTime;

@Repository
@Transactional
public class DefaultShowTimeRepository implements ShowTimeRepository {

	@Autowired
	private PersistenceService persistenceService;

	@Override
	public ShowTime findById(int id) {
		return persistenceService.findById(ShowTime.class, id);
	}

	@Override
	public void add(ShowTime showTime, List<Seat> seatList) {
		showTime.setSeatList(null);
		persistenceService.save(showTime);
		for (Seat seat : seatList) {
			seat.setBookedOnTransaction(null);
			seat.setShowTime(showTime);
			persistenceService.save(seat);
		}
		showTime.setSeatList(seatList);
		persistenceService.update(showTime);
	}

	@Override
	public void update(ShowTime showTime) {
		persistenceService.update(showTime);
	}

}
