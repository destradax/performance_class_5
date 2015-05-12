package co.com.psl.elitemovie.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "movie_assistance")
public class MovieAssistance {

	@Id
	@SequenceGenerator(initialValue = 1, sequenceName = "db_movie_assistance_sequence", name = "movieAssistanceSequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "movieAssistanceSequence")
	private int id;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "showtime_id")
	private ShowTime showTime;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;

	private int score;

	public MovieAssistance() {
	}

	public MovieAssistance(int id, ShowTime showTime, User user, int score) {
		super();
		this.id = id;
		this.showTime = showTime;
		this.user = user;
		this.score = score;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

}