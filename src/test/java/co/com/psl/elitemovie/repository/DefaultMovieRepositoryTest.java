package co.com.psl.elitemovie.repository;

import static org.junit.Assert.assertEquals;

import java.util.Collection;

import org.junit.Test;

import co.com.psl.elitemovie.model.Movie;

public class DefaultMovieRepositoryTest {

	private static final int MOVIE_1_ID = 1;
	private static final String MOVIE_1_NAME = "MOVIE1";
	private static final String MOVIE_1_RELEASE_DATE = "releaseDate";
	private static final String MOVIE_1_RESTRICTIONS = "restrictions";
	private static final String MOVIE_1_DESCRIPTION = "description";

	private static final int MOVIE_2_ID = 2;
	private static final String MOVIE_2_NAME = "MOVIE2";
	private static final String MOVIE_2_RELEASE_DATE = "releaseDate2";
	private static final String MOVIE_2_RESTRICTIONS = "restrictions2";
	private static final String MOVIE_2_DESCRIPTION = "description2";

	@Test
	public void testAddAndFind() {
		DefaultMovieRepository repository = new DefaultMovieRepository();

		Movie movie = new Movie(MOVIE_1_ID, MOVIE_1_NAME, MOVIE_1_RELEASE_DATE,
				MOVIE_1_RESTRICTIONS, MOVIE_1_DESCRIPTION);

		repository.add(movie);

		Movie savedMovie = repository.findById(MOVIE_1_ID);

		assertEquals(MOVIE_1_ID, savedMovie.getId());
		assertEquals(MOVIE_1_NAME, savedMovie.getName());
		assertEquals(MOVIE_1_RELEASE_DATE, savedMovie.getReleaseDateString());
		assertEquals(MOVIE_1_RESTRICTIONS, savedMovie.getRestrictions());
		assertEquals(MOVIE_1_DESCRIPTION, savedMovie.getDescription());
	}

	@Test
	public void testFindById() {
		DefaultMovieRepository repository = new DefaultMovieRepository();

		Movie movie1 = new Movie(MOVIE_1_ID, MOVIE_1_NAME,
				MOVIE_1_RELEASE_DATE, MOVIE_1_RESTRICTIONS, MOVIE_1_DESCRIPTION);
		Movie movie2 = new Movie(MOVIE_2_ID, MOVIE_2_NAME,
				MOVIE_2_RELEASE_DATE, MOVIE_2_RESTRICTIONS, MOVIE_2_DESCRIPTION);

		repository.add(movie1);
		repository.add(movie2);

		Movie savedMovie1 = repository.findById(MOVIE_1_ID);
		assertEquals(MOVIE_1_ID, savedMovie1.getId());
		assertEquals(MOVIE_1_NAME, savedMovie1.getName());
		assertEquals(MOVIE_1_RELEASE_DATE, savedMovie1.getReleaseDateString());
		assertEquals(MOVIE_1_RESTRICTIONS, savedMovie1.getRestrictions());
		assertEquals(MOVIE_1_DESCRIPTION, savedMovie1.getDescription());

		Movie savedMovie2 = repository.findById(MOVIE_2_ID);
		assertEquals(MOVIE_2_ID, savedMovie2.getId());
		assertEquals(MOVIE_2_NAME, savedMovie2.getName());
		assertEquals(MOVIE_2_RELEASE_DATE, savedMovie2.getReleaseDateString());
		assertEquals(MOVIE_2_RESTRICTIONS, savedMovie2.getRestrictions());
		assertEquals(MOVIE_2_DESCRIPTION, savedMovie2.getDescription());

	}

	@Test
	public void testFindAll() {
		DefaultMovieRepository repository = new DefaultMovieRepository();

		Movie movie1 = new Movie(MOVIE_1_ID, MOVIE_1_NAME,
				MOVIE_1_RELEASE_DATE, MOVIE_1_RESTRICTIONS, MOVIE_1_DESCRIPTION);
		Movie movie2 = new Movie(MOVIE_2_ID, MOVIE_2_NAME,
				MOVIE_2_RELEASE_DATE, MOVIE_2_RESTRICTIONS, MOVIE_2_DESCRIPTION);

		repository.add(movie1);
		repository.add(movie2);

		Collection<Movie> movies = repository.findAll();
		assertEquals(2, movies.size());

	}
}
