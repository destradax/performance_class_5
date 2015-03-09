package co.com.psl.elitemovie.repository;

import static org.mockito.Mockito.verify;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import co.com.psl.elitemovie.model.Seat;
import co.com.psl.elitemovie.model.SeatAssignmentTransaction;

public class DefaultSeatAssignmentTransactionRepositoryTest {

	private static final Date CURRENT_DATE = Calendar.getInstance().getTime();

	@Mock
	private PersistenceService persistenceService;

	@InjectMocks
	private DefaultSeatAssignmentTransactionRepository repository = new DefaultSeatAssignmentTransactionRepository();

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testAddAndFind() {

		SeatAssignmentTransaction transaction = new SeatAssignmentTransaction();
		transaction.setId(1);
		transaction.setBookedSeats(Collections.<Seat> emptyList());
		transaction.setDate(CURRENT_DATE);
		repository.add(transaction);
		verify(persistenceService).save(transaction);
	}

}
