package co.com.psl.elitemovie.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.psl.elitemovie.model.SeatAssignmentTransaction;

@Component
public class DefaultSeatAssignmentTransactionRepository implements
		SeatAssignmentTransactionRepository {

	@Autowired
	private PersistenceService persistenceService;

	@Override
	public SeatAssignmentTransaction findById(int id) {
		return persistenceService.findById(SeatAssignmentTransaction.class, id);
	}

	@Override
	public void add(SeatAssignmentTransaction transaction) {
		persistenceService.save(transaction);
	}
}
