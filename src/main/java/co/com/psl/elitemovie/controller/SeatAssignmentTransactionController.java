package co.com.psl.elitemovie.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.com.psl.elitemovie.controller.dto.DtoTransformer;
import co.com.psl.elitemovie.controller.dto.SeatAssignmentTransactionDto;
import co.com.psl.elitemovie.controller.dto.SeatDto;
import co.com.psl.elitemovie.controller.dto.SeatSelectionRequestDto;
import co.com.psl.elitemovie.model.Seat;
import co.com.psl.elitemovie.model.SeatAssignmentTransaction;
import co.com.psl.elitemovie.model.ShowTime;
import co.com.psl.elitemovie.repository.SeatAssignmentTransactionRepository;
import co.com.psl.elitemovie.repository.ShowTimeRepository;

@RestController
@RequestMapping("/rest")
public class SeatAssignmentTransactionController {

	@Autowired
	private SeatAssignmentTransactionRepository seatAssignmentTransactionRepository;

	@Autowired
	private ShowTimeRepository showTimeRepository;

	@RequestMapping("/transaction/{transactionId}")
	public SeatAssignmentTransactionDto findById(@PathVariable int transactionId) {
		SeatAssignmentTransaction transaction = seatAssignmentTransactionRepository
				.findById(transactionId);
		SeatAssignmentTransactionDto transactionDto = DtoTransformer.toDto(
				transaction, SeatAssignmentTransactionDto.class);
		transactionDto.setBookedSeats(null);
		for (Seat seat : transaction.getBookedSeats()) {
			transactionDto.addBookedSeat(DtoTransformer.toDto(seat,
					SeatDto.class));
		}
		return transactionDto;
	}

	@RequestMapping(value = "/transaction/{showTimeId}", method = RequestMethod.POST)
	public int submit(@RequestBody List<SeatSelectionRequestDto> request,
			@PathVariable int showTimeId) {
		ShowTime showTime = showTimeRepository.findById(showTimeId);
		List<Seat> bookedSeats = new ArrayList<Seat>();
		for (SeatSelectionRequestDto dto : request) {
			Seat seat = showTime.getSeats()[dto.getRow()][dto.getColumn()];
			if (seat.isBooked() == false) {
				seat.setBooked(true);
				bookedSeats.add(seat);
			} else {
				throw new RuntimeException("The seat was already booked");
			}
		}
		showTimeRepository.update(showTime);

		SeatAssignmentTransaction transaction = new SeatAssignmentTransaction();
		transaction.setDate(Calendar.getInstance().getTime());
		transaction.setBookedSeats(bookedSeats);
		transaction.setId(seatAssignmentTransactionRepository.getNextId());
		transaction.setMovieId(showTime.getMovieId());
		transaction.setShowTimeId(showTimeId);

		seatAssignmentTransactionRepository.add(transaction);

		return transaction.getId();
	}
}
