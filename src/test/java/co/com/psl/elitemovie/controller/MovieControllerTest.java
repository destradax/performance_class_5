package co.com.psl.elitemovie.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import co.com.psl.elitemovie.controller.dto.MovieDto;
import co.com.psl.elitemovie.controller.dto.MovieLightWeightDto;
import co.com.psl.elitemovie.model.Movie;
import co.com.psl.elitemovie.model.ShowTime;
import co.com.psl.elitemovie.repository.MovieRepository;

public class MovieControllerTest {

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

	private static final int SHOWTIME_1_ID = 3;
	private static final int SHOWTIME_2_ID = 4;

	@Mock
	private MovieRepository movieRepository;

	@InjectMocks
	private MovieController movieController = new MovieController();

	private DateFormat simpleDateFormat = new SimpleDateFormat(
			"dd/MM/yyyy HH:mm");

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testFindAll() {
		Collection<Movie> movies = new ArrayList<Movie>();
		Movie movie1 = new Movie(MOVIE_1_NAME, MOVIE_1_RELEASE_DATE,
				MOVIE_1_RESTRICTIONS, MOVIE_1_DESCRIPTION);
		movie1.setId(MOVIE_1_ID);
		Movie movie2 = new Movie(MOVIE_2_NAME, MOVIE_2_RELEASE_DATE,
				MOVIE_2_RESTRICTIONS, MOVIE_2_DESCRIPTION);
		movie2.setId(MOVIE_2_ID);
		movies.add(movie1);
		movies.add(movie2);

		when(movieRepository.findAll()).thenReturn(movies);

		List<MovieLightWeightDto> result = movieController.findAll();

		assertEquals(2, result.size());

		assertEquals(MOVIE_1_ID, result.get(0).getId());
		assertEquals(MOVIE_1_NAME, result.get(0).getName());
		assertEquals(MOVIE_1_RELEASE_DATE, result.get(0).getReleaseDateString());
		assertEquals(MOVIE_1_DESCRIPTION, result.get(0).getDescription());

		assertEquals(MOVIE_2_ID, result.get(1).getId());
		assertEquals(MOVIE_2_NAME, result.get(1).getName());
		assertEquals(MOVIE_2_RELEASE_DATE, result.get(1).getReleaseDateString());
		assertEquals(MOVIE_2_DESCRIPTION, result.get(1).getDescription());

		verify(movieRepository).findAll();

	}

	@Test
	public void testFindByIdWithNoShowTimes() {
		Movie movie1 = new Movie(MOVIE_1_NAME, MOVIE_1_RELEASE_DATE,
				MOVIE_1_RESTRICTIONS, MOVIE_1_DESCRIPTION);
		movie1.setId(MOVIE_1_ID);
		when(movieRepository.findById(MOVIE_1_ID)).thenReturn(movie1);

		MovieDto movieDto = movieController.findById(MOVIE_1_ID);

		assertEquals(MOVIE_1_ID, movieDto.getId());
		assertEquals(MOVIE_1_NAME, movieDto.getName());
		assertEquals(MOVIE_1_RELEASE_DATE, movieDto.getReleaseDateString());
		assertEquals(MOVIE_1_RESTRICTIONS, movieDto.getRestrictions());
		assertEquals(MOVIE_1_DESCRIPTION, movieDto.getDescription());

		verify(movieRepository).findById(MOVIE_1_ID);

	}

	@Test
	public void testFindByIdWithPastShowTimes() throws ParseException {
		Movie movie1 = new Movie(MOVIE_1_NAME, MOVIE_1_RELEASE_DATE,
				MOVIE_1_RESTRICTIONS, MOVIE_1_DESCRIPTION);
		movie1.setId(MOVIE_1_ID);
		ShowTime showTime1 = new ShowTime(
				simpleDateFormat.parse("05/01/1980 18:00")); // PAST EVENT 1
		ShowTime showTime2 = new ShowTime(
				simpleDateFormat.parse("05/01/1980 20:00")); // PAST EVENT 2

		movie1.addShowTime(showTime1);
		movie1.addShowTime(showTime2);

		when(movieRepository.findById(MOVIE_1_ID)).thenReturn(movie1);

		MovieDto movieDto = movieController.findById(MOVIE_1_ID);

		assertEquals(MOVIE_1_ID, movieDto.getId());
		assertEquals(MOVIE_1_NAME, movieDto.getName());
		assertEquals(MOVIE_1_RELEASE_DATE, movieDto.getReleaseDateString());
		assertEquals(MOVIE_1_RESTRICTIONS, movieDto.getRestrictions());
		assertEquals(MOVIE_1_DESCRIPTION, movieDto.getDescription());

		assertTrue(CollectionUtils.isEmpty(movieDto.getShowTimes()));

		verify(movieRepository).findById(MOVIE_1_ID);
	}

	@Test
	public void testFindByIdWithFutureShowTimes() throws ParseException {
		Movie movie1 = new Movie(MOVIE_1_NAME, MOVIE_1_RELEASE_DATE,
				MOVIE_1_RESTRICTIONS, MOVIE_1_DESCRIPTION);
		movie1.setId(MOVIE_1_ID);
		Date showTime1Date = simpleDateFormat.parse("05/01/2016 18:00");
		ShowTime showTime1 = new ShowTime(showTime1Date); // FUTURE
															// EVENT
															// 1
		showTime1.setId(SHOWTIME_1_ID);
		Date showTime2Date = simpleDateFormat.parse("05/01/2016 18:00");
		ShowTime showTime2 = new ShowTime(showTime2Date);
		showTime2.setId(SHOWTIME_2_ID);

		movie1.addShowTime(showTime1);
		movie1.addShowTime(showTime2);

		when(movieRepository.findById(MOVIE_1_ID)).thenReturn(movie1);

		MovieDto movieDto = movieController.findById(MOVIE_1_ID);

		assertEquals(MOVIE_1_ID, movieDto.getId());
		assertEquals(MOVIE_1_NAME, movieDto.getName());
		assertEquals(MOVIE_1_RELEASE_DATE, movieDto.getReleaseDateString());
		assertEquals(MOVIE_1_RESTRICTIONS, movieDto.getRestrictions());
		assertEquals(MOVIE_1_DESCRIPTION, movieDto.getDescription());

		assertTrue(movieDto.getShowTimes().size() == 2);

		verify(movieRepository).findById(MOVIE_1_ID);

		// Verify showtimes
		assertEquals(SHOWTIME_1_ID, movieDto.getShowTimes().get(0).getId());
		assertEquals(showTime1Date.getTime(), movieDto.getShowTimes().get(0)
				.getTimeInMilliseconds());

		assertEquals(SHOWTIME_2_ID, movieDto.getShowTimes().get(1).getId());
		assertEquals(showTime2Date.getTime(), movieDto.getShowTimes().get(0)
				.getTimeInMilliseconds());
	}
}
