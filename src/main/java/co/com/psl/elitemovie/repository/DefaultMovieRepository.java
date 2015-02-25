package co.com.psl.elitemovie.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import co.com.psl.elitemovie.model.Movie;

@Component
public class DefaultMovieRepository implements MovieRepository {

	private Map<Integer, Movie> moviesById;

	@Override
	public Collection<Movie> findAll() {
		return moviesById.values();
	}

	@Override
	public Movie findById(int id) {
		return moviesById.get(id);
	}

	@Override
	public void add(Movie movie) {
		if (moviesById == null) {
			moviesById = new HashMap<Integer, Movie>();
		}
		moviesById.put(movie.getId(), movie);
	}
}
