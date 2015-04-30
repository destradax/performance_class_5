package co.com.psl.elitemovie.model;

public class MovieAssistance {
	
	private int id;
	private int showtimeId;
	private int userId;
	private int score;
	
	public MovieAssistance(){}

	public MovieAssistance(int id, int showtimeId, int userId, int score) {
		super();
		this.id = id;
		this.showtimeId = showtimeId;
		this.userId = userId;
		this.score = score;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getShowtimeId() {
		return showtimeId;
	}

	public void setShowtimeId(int showtimeId) {
		this.showtimeId = showtimeId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	};
}