package co.com.psl.elitemovie.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.psl.elitemovie.model.Movie;

@Component
public class DefaultMovieRepository implements MovieRepository {

	@Autowired
	private PersistenceService persistenceService;

	@Override
	public List<Movie> findAll() {
		return persistenceService.executeQuery(Movie.class,
				"SELECT m FROM Movie m");
	}

	@Override
	public Movie findById(int id) {
		return persistenceService.findById(Movie.class, id);
	}

	@Override
	public void add(Movie movie) {
		persistenceService.save(movie);
	}
}
