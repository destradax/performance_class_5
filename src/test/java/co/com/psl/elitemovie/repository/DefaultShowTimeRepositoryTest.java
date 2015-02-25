package co.com.psl.elitemovie.repository;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import co.com.psl.elitemovie.model.ShowTime;

public class DefaultShowTimeRepositoryTest {

	private static final int SHOWTIME_1_ID = 1;
	private static final Date SHOWTIME_1_DATE = Calendar.getInstance()
			.getTime();

	private static final int SHOWTIME_2_ID = 1;
	private static final Date SHOWTIME_2_DATE = Calendar.getInstance()
			.getTime();

	@Test
	public void testAddAndFind() {
		DefaultShowTimeRepository repository = new DefaultShowTimeRepository();

		ShowTime showTime = new ShowTime(SHOWTIME_1_ID, SHOWTIME_1_DATE);
		repository.add(showTime);

		ShowTime savedShowTime = repository.findById(SHOWTIME_1_ID);

		assertEquals(SHOWTIME_1_ID, savedShowTime.getId());
		assertEquals(SHOWTIME_1_DATE, savedShowTime.getDate());
	}

	@Test
	public void testFindById() {
		DefaultShowTimeRepository repository = new DefaultShowTimeRepository();

		ShowTime showTime1 = new ShowTime(SHOWTIME_1_ID, SHOWTIME_1_DATE);
		ShowTime showTime2 = new ShowTime(SHOWTIME_2_ID, SHOWTIME_2_DATE);

		repository.add(showTime1);
		repository.add(showTime2);

		ShowTime savedShowTime1 = repository.findById(SHOWTIME_1_ID);
		assertEquals(SHOWTIME_1_ID, savedShowTime1.getId());
		assertEquals(SHOWTIME_1_DATE, savedShowTime1.getDate());

		ShowTime savedShowTime2 = repository.findById(SHOWTIME_2_ID);
		assertEquals(SHOWTIME_2_ID, savedShowTime2.getId());
		assertEquals(SHOWTIME_2_DATE, savedShowTime2.getDate());
	}

	@Test
	public void testUpdate() {
		DefaultShowTimeRepository repository = new DefaultShowTimeRepository();

		ShowTime showTime1 = new ShowTime(SHOWTIME_1_ID, SHOWTIME_1_DATE);
		repository.add(showTime1);

		ShowTime savedShowTime = repository.findById(SHOWTIME_1_ID);

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		Date newDate = calendar.getTime();

		savedShowTime.setDate(newDate);

		repository.update(savedShowTime);

		ShowTime updatedShowTime1 = repository.findById(SHOWTIME_1_ID);
		assertEquals(SHOWTIME_1_ID, updatedShowTime1.getId());
		assertEquals(newDate, updatedShowTime1.getDate());
	}
}
