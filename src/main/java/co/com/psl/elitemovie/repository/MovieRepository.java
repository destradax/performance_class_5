package co.com.psl.elitemovie.repository;

import java.util.Collection;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import co.com.psl.elitemovie.model.Movie;

public interface MovieRepository {

	/**
	 * Find all movies.
	 */
	@Cacheable("movies")
	Collection<Movie> findAll();

	Movie findById(int id);

	@CacheEvict("movies")
	void save(Movie movie);
}
