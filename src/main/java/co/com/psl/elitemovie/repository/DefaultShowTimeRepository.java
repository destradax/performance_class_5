package co.com.psl.elitemovie.repository;

import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

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

	@Autowired
	private CacheManager cacheManager;

	@Override
	public ShowTime findById(int id) {
		Cache cache = cacheManager.getCache("showtimes");
		if (cache.get(id) != null) {
			return (ShowTime) cache.get(id).getObjectValue();
		}
		ShowTime s = persistenceService.findById(ShowTime.class, id);
		cache.put(new Element(id, s));
		return s;
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

	@Override
	public List<ShowTime> findAll() {
		return persistenceService.executeQuery(ShowTime.class,
				"SELECT showTime FROM ShowTime showTime");
	}

}
