package co.com.psl.elitemovie.repository;

import co.com.psl.elitemovie.model.SeatAssignmentTransaction;

public interface SeatAssignmentTransactionRepository {

	SeatAssignmentTransaction findById(int id);

	void add(SeatAssignmentTransaction showTime);
}
