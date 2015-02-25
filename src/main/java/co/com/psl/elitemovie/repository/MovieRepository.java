package co.com.psl.elitemovie.repository;

import java.util.Collection;

import co.com.psl.elitemovie.model.Movie;

public interface MovieRepository {

	/**
	 * Find all movies.
	 */
	Collection<Movie> findAll();

	/**
	 * Find a movie by id.
	 * 
	 * @param id
	 *            the id.
	 * @return the movie.
	 */
	Movie findById(int id);

	void add(Movie movie);
}
