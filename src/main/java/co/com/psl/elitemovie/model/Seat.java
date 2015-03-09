package co.com.psl.elitemovie.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "seat")
public class Seat {

	@Id
	@SequenceGenerator(initialValue = 1, sequenceName = "db_seat_sequence", name = "seatSequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seatSequence")
	private int id;

	@Column(name = "row")
	private int row;

	@Column(name = "seat_column")
	private int column;

	@Column(name = "is_aisle")
	private boolean isAisle; // Pasillo

	@Column(name = "is_booked")
	private boolean isBooked;

	@Column(name = "preference_points")
	private int preferencePoints;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "showtime_id")
	private ShowTime showTime;

	@ManyToOne(fetch = FetchType.EAGER, optional = true)
	@JoinColumn(name = "assigned_on_transaction_id", nullable = true)
	private SeatAssignmentTransaction bookedOnTransaction;

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public boolean isAisle() {
		return isAisle;
	}

	public void setAisle(boolean isAisle) {
		this.isAisle = isAisle;
	}

	public boolean isBooked() {
		return isBooked;
	}

	public void setBooked(boolean isBooked) {
		this.isBooked = isBooked;
	}

	public int getPreferencePoints() {
		return preferencePoints;
	}

	public void setPreferencePoints(int points) {
		this.preferencePoints = points;
	}

	public Seat() {
	}

	public Seat(int row, int column, boolean isAisle, boolean isBooked,
			int preferencePoints) {
		super();
		this.row = row;
		this.column = column;
		this.isAisle = isAisle;
		this.isBooked = isBooked;
		this.preferencePoints = preferencePoints;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ShowTime getShowTime() {
		return showTime;
	}

	public void setShowTime(ShowTime showTime) {
		this.showTime = showTime;
	}

	public SeatAssignmentTransaction getBookedOnTransaction() {
		return bookedOnTransaction;
	}

	public void setBookedOnTransaction(
			SeatAssignmentTransaction bookedOnTransaction) {
		this.bookedOnTransaction = bookedOnTransaction;
	}

}
