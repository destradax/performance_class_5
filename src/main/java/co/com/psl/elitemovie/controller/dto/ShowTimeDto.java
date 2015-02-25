package co.com.psl.elitemovie.controller.dto;

public class ShowTimeDto {

	private int id;

	private SeatDto[][] seats;

	private int movieId;

	private long timeInMilliseconds;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public SeatDto[][] getSeats() {
		return seats;
	}

	public void setSeats(SeatDto[][] seats) {
		this.seats = seats;
	}

	public void addSeat(SeatDto seatDto) {
		if (seats == null) {
			seats = new SeatDto[10][26];
		}
		seats[seatDto.getRow()][seatDto.getColumn()] = seatDto;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public long getTimeInMilliseconds() {
		return timeInMilliseconds;
	}

	public void setTimeInMilliseconds(long timeInMilliseconds) {
		this.timeInMilliseconds = timeInMilliseconds;
	}
}
