package co.com.psl.elitemovie.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import co.com.psl.elitemovie.model.Seat;

@Component
@Transactional
public class DefaultSeatRepository implements SeatRepository {

	@Autowired
	private PersistenceService persistenceService;

	@Override
	public void save(Seat seat) {
		persistenceService.save(seat);
	}

	@Override
	public void update(Seat seat) {
		persistenceService.update(seat);
	}
}
