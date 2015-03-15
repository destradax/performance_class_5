package co.com.psl.elitemovie.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "movie")
public class Movie {

	@Id
	@SequenceGenerator(initialValue = 1, sequenceName = "db_movie_sequence", name = "movieSequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "movieSequence")
	private int id;

	@Column
	private String name;

	@OneToMany(mappedBy = "movie", fetch = FetchType.EAGER)
	private Set<ShowTime> showTimes;

	@Column(length = 4000)
	private String releaseDateString;

	@Column(length = 4000)
	private String restrictions;

	@Column(length = 4000)
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<ShowTime> getShowTimes() {
		return showTimes;
	}

	public void setShowTimes(Set<ShowTime> showTimes) {
		this.showTimes = showTimes;
	}

	public Movie() {
	}

	public Movie(String name, String releaseDateString, String restrictions,
			String description) {
		super();
		this.name = name;
		this.releaseDateString = releaseDateString;
		this.restrictions = restrictions;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void addShowTime(ShowTime showTime) {
		if (showTimes == null) {
			showTimes = new HashSet<ShowTime>();
		}
		showTimes.add(showTime);
	}

	public String getReleaseDateString() {
		return releaseDateString;
	}

	public void setReleaseDateString(String releaseDateString) {
		this.releaseDateString = releaseDateString;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRestrictions() {
		return restrictions;
	}

	public void setRestrictions(String restrictions) {
		this.restrictions = restrictions;
	}
}
