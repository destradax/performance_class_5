package co.com.psl.elitemovie.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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

@Entity
@Table(name = "assignment_transaction")
public class SeatAssignmentTransaction implements Serializable {

	/**
	 * Serialization id.
	 */
	private static final long serialVersionUID = 2757113423515971448L;

	@Id
	@SequenceGenerator(initialValue = 1, sequenceName = "db_seat_assignment_transaction_sequence", name = "seatAssignmentTransactionSequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seatAssignmentTransactionSequence")
	private int id;

	@Column(name = "assignment_transaction_date")
	private Date date;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "showtime_id", nullable = true)
	private ShowTime showTime;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "movie_id", nullable = true)
	private Movie movie;

	@OneToMany(mappedBy = "bookedOnTransaction", fetch = FetchType.EAGER)
	private List<Seat> bookedSeats;

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

	public List<Seat> getBookedSeats() {
		return bookedSeats;
	}

	public void setBookedSeats(List<Seat> bookedSeats) {
		this.bookedSeats = bookedSeats;
	}

	public ShowTime getShowTime() {
		return showTime;
	}

	public void setShowTime(ShowTime showTime) {
		this.showTime = showTime;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

}
