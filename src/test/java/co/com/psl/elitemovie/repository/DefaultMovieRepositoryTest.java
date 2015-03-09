package co.com.psl.elitemovie.repository;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import co.com.psl.elitemovie.model.Movie;

import com.google.common.collect.Lists;

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

	@Mock
	private PersistenceService persistenceService;

	@InjectMocks
	DefaultMovieRepository repository = new DefaultMovieRepository();

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testAddAndFind() {
		Movie movie = new Movie(MOVIE_1_NAME, MOVIE_1_RELEASE_DATE,
				MOVIE_1_RESTRICTIONS, MOVIE_1_DESCRIPTION);
		movie.setId(MOVIE_1_ID);
		repository.add(movie);
		verify(persistenceService).save(movie);
	}

	@Test
	public void testFindById() {

		Movie movie1 = new Movie(MOVIE_1_NAME, MOVIE_1_RELEASE_DATE,
				MOVIE_1_RESTRICTIONS, MOVIE_1_DESCRIPTION);
		movie1.setId(MOVIE_1_ID);
		Movie movie2 = new Movie(MOVIE_2_NAME, MOVIE_2_RELEASE_DATE,
				MOVIE_2_RESTRICTIONS, MOVIE_2_DESCRIPTION);
		movie2.setId(MOVIE_2_ID);

		when(persistenceService.findById(Movie.class, MOVIE_1_ID)).thenReturn(
				movie1);
		when(persistenceService.findById(Movie.class, MOVIE_2_ID)).thenReturn(
				movie2);

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
		Movie movie1 = new Movie(MOVIE_1_NAME, MOVIE_1_RELEASE_DATE,
				MOVIE_1_RESTRICTIONS, MOVIE_1_DESCRIPTION);
		movie1.setId(MOVIE_1_ID);
		Movie movie2 = new Movie(MOVIE_2_NAME, MOVIE_2_RELEASE_DATE,
				MOVIE_2_RESTRICTIONS, MOVIE_2_DESCRIPTION);
		movie2.setId(MOVIE_2_ID);

		when(
				persistenceService.executeQuery(Movie.class,
						"SELECT m FROM Movie m")).thenReturn(
				Lists.newArrayList(movie1, movie2));

		Collection<Movie> movies = repository.findAll();
		assertEquals(2, movies.size());

	}
}
