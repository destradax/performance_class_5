package co.com.psl.elitemovie.repository;

import java.util.Collection;

import co.com.psl.elitemovie.model.Movie;

public interface MovieRepository {

	/**
	 * Find all movies.
	 */
	Collection<Movie> findAll();

	Movie findById(int id);

	void save(Movie movie);
}
