package co.com.psl.elitemovie.model;

import java.util.Date;

import co.com.psl.elitemovie.repository.CinemaTemplateGeneratorUtil;

public class ShowTime {

	private int id;

	private Seat[][] seats;

	private int movieId;

	private Date date;

	public Seat[][] getSeats() {
		return seats;
	}

	public void setSeats(Seat[][] seats) {
		this.seats = seats;
	}

	public ShowTime(int id, Date date) {
		super();
		this.id = id;
		this.date = date;
		seats = CinemaTemplateGeneratorUtil.getEmptyScenario();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public int countAvailableSeats() {
		int available = 0;
		for (Seat[] row : getSeats()) {
			for (Seat seat : row) {
				if (seat.isAisle() == false && seat.isBooked() == false) {
					available++;
				}
			}
		}
		return available;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
