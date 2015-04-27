package co.com.psl.elitemovie.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "showtime")
public class ShowTime implements Serializable {

	/**
	 * Serialization id.
	 */
	private static final long serialVersionUID = -8148182743736780137L;

	@Id
	@SequenceGenerator(initialValue = 1, sequenceName = "db_showtime_sequence", name = "showtimeSequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "showtimeSequence")
	private int id;

	@OneToMany(mappedBy = "showTime", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Seat> seatList;

	@Transient
	private Seat[][] seatsArray;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "movie_id")
	private Movie movie;

	@Column(name = "showtime_date")
	private Date date;

	public ShowTime() {
	}

	public ShowTime(Date date) {
		setDate(date);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int countAvailableSeats() {
		int available = 0;
		for (Seat[] row : getSeatsArray()) {
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

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Seat[][] getSeatsArray() {
		if (seatsArray == null) {
			setSeatsArray(toArray(getSeatList()), false);
		}
		return seatsArray;
	}

	public void setSeatsArray(Seat[][] seatsArray) {
		setSeatsArray(seatsArray, true);
	}

	public void setSeatsArray(Seat[][] seatsArray, boolean updateCollections) {
		this.seatsArray = seatsArray;
		if (updateCollections) {
			setSeatList(toList(seatsArray), false);
		}
	}

	public List<Seat> getSeatList() {
		return seatList;
	}

	public void setSeatList(List<Seat> seatList) {
		if (seatList == null) {
			return;
		}
		setSeatList(seatList, true);
	}

	public void setSeatList(List<Seat> seatList, boolean updateCollections) {
		this.seatList = seatList;
		if (updateCollections) {
			setSeatsArray(toArray(seatList), false);
		}

	}

	private List<Seat> toList(Seat[][] seatsArray) {
		List<Seat> list = new ArrayList<Seat>();
		for (Seat[] row : seatsArray) {
			for (Seat seat : row) {
				list.add(seat);
			}
		}
		return list;
	}

	private Seat[][] toArray(List<Seat> seatsList) {
		Seat[][] array = new Seat[10][26];
		for (Seat seat : seatsList) {
			array[seat.getRow()][seat.getColumn()] = seat;
		}
		return array;
	}

}
