package co.com.psl.elitemovie.repository;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

import org.junit.Test;

import co.com.psl.elitemovie.model.Seat;
import co.com.psl.elitemovie.model.SeatAssignmentTransaction;

public class DefaultSeatAssignmentTransactionRepositoryTest {

	private static final Date CURRENT_DATE = Calendar.getInstance().getTime();
	private static final int TRANSACTION_1_MOVIE_ID = 3;
	private static final int TRANSACTION_1_SHOWTIME_ID = 2;

	@Test
	public void testAddAndFind() {
		DefaultSeatAssignmentTransactionRepository repository = new DefaultSeatAssignmentTransactionRepository();
		repository.init();

		SeatAssignmentTransaction transaction = new SeatAssignmentTransaction();

		int nextId = repository.getNextId();
		assertEquals(1, nextId);
		transaction.setId(nextId);
		transaction.setBookedSeats(Collections.<Seat> emptyList());
		transaction.setDate(CURRENT_DATE);
		transaction.setMovieId(TRANSACTION_1_MOVIE_ID);
		transaction.setShowTimeId(TRANSACTION_1_SHOWTIME_ID);

		repository.add(transaction);

		SeatAssignmentTransaction savedTransaction = repository
				.findById(nextId);

		assertEquals(1, savedTransaction.getId());
		assertEquals(CURRENT_DATE, savedTransaction.getDate());
		assertEquals(TRANSACTION_1_MOVIE_ID, savedTransaction.getMovieId());
		assertEquals(TRANSACTION_1_SHOWTIME_ID,
				savedTransaction.getShowTimeId());
	}

}
