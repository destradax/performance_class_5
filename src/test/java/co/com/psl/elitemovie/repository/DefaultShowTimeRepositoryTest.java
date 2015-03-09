package co.com.psl.elitemovie.repository;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import co.com.psl.elitemovie.model.ShowTime;

public class DefaultShowTimeRepositoryTest {

	private static final int SHOWTIME_1_ID = 1;
	private static final Date SHOWTIME_1_DATE = Calendar.getInstance()
			.getTime();

	private static final int SHOWTIME_2_ID = 1;
	private static final Date SHOWTIME_2_DATE = Calendar.getInstance()
			.getTime();

	@Mock
	private PersistenceService persistenceService;

	@InjectMocks
	DefaultShowTimeRepository repository = new DefaultShowTimeRepository();

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testAddAndFind() {

		ShowTime showTime = new ShowTime(SHOWTIME_1_DATE);
		showTime.setId(1);
		when(persistenceService.findById(ShowTime.class, SHOWTIME_1_ID))
				.thenReturn(showTime);

		ShowTime savedShowTime = repository.findById(SHOWTIME_1_ID);

		assertEquals(SHOWTIME_1_ID, savedShowTime.getId());
		assertEquals(SHOWTIME_1_DATE, savedShowTime.getDate());

		verify(persistenceService).findById(ShowTime.class, SHOWTIME_1_ID);
	}

	@Test
	public void testFindById() {

		ShowTime showTime1 = new ShowTime(SHOWTIME_1_DATE);
		showTime1.setId(SHOWTIME_1_ID);
		ShowTime showTime2 = new ShowTime(SHOWTIME_2_DATE);
		showTime2.setId(SHOWTIME_2_ID);

		Mockito.when(persistenceService.findById(ShowTime.class, SHOWTIME_1_ID))
				.thenReturn(showTime1);
		Mockito.when(persistenceService.findById(ShowTime.class, SHOWTIME_2_ID))
				.thenReturn(showTime2);

		ShowTime savedShowTime1 = repository.findById(SHOWTIME_1_ID);
		assertEquals(SHOWTIME_1_ID, savedShowTime1.getId());
		assertEquals(SHOWTIME_1_DATE, savedShowTime1.getDate());

		ShowTime savedShowTime2 = repository.findById(SHOWTIME_2_ID);
		assertEquals(SHOWTIME_2_ID, savedShowTime2.getId());
		assertEquals(SHOWTIME_2_DATE, savedShowTime2.getDate());
	}

}
