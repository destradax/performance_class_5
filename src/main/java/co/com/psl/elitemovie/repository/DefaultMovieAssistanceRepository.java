package co.com.psl.elitemovie.repository;

import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.com.psl.elitemovie.model.MovieAssistance;

@Repository
@Transactional
public class DefaultMovieAssistanceRepository implements MovieAssistanceRepository {

	@Autowired
	private PersistenceService persistenceService;

	@Autowired
	private CacheManager cacheManager;

	@Override
	public MovieAssistance findById(int id) {
		Cache cache = cacheManager.getCache("movie_assistance");
		if (cache.get(id) != null) {
			return (MovieAssistance) cache.get(id).getObjectValue();
		}
		MovieAssistance movieAssistance = persistenceService.findById(MovieAssistance.class, id);
		cache.put(new Element(id, movieAssistance));
		return movieAssistance;
	}


	@Override
	public List<MovieAssistance> findAll() {
		return persistenceService.executeQuery(MovieAssistance.class, "SELECT ma FROM MovieAssistance ma");
	}

}
