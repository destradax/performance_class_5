package co.com.psl.elitemovie.controller.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SeatAssignmentTransactionDto {

	private int id;

	private Date date;

	private int showTimeId;

	private int movieId;

	private List<SeatDto> bookedSeats;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getShowTimeId() {
		return showTimeId;
	}

	public void setShowTimeId(int showTimeId) {
		this.showTimeId = showTimeId;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public List<SeatDto> getBookedSeats() {
		return bookedSeats;
	}

	public void setBookedSeats(List<SeatDto> bookedSeats) {
		this.bookedSeats = bookedSeats;
	}

	public boolean addBookedSeat(SeatDto e) {
		if (bookedSeats == null) {
			bookedSeats = new ArrayList<SeatDto>();
		}
		return bookedSeats.add(e);
	}

}
