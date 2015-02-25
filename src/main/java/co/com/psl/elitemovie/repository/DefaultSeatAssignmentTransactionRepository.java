package co.com.psl.elitemovie.repository;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import co.com.psl.elitemovie.model.SeatAssignmentTransaction;

@Component
public class DefaultSeatAssignmentTransactionRepository implements
		SeatAssignmentTransactionRepository {

	private Map<Integer, SeatAssignmentTransaction> transactionsById;

	@Override
	public SeatAssignmentTransaction findById(int id) {
		return transactionsById.get(id);
	}

	@Override
	public void add(SeatAssignmentTransaction transaction) {
		transactionsById.put(transaction.getId(), transaction);
	}

	@Override
	public int getNextId() {
		return transactionsById.size() + 1;
	}

	@PostConstruct
	public void init() {
		transactionsById = new HashMap<Integer, SeatAssignmentTransaction>();
	}
}
