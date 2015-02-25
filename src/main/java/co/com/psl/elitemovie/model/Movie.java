package co.com.psl.elitemovie.model;

import java.util.ArrayList;
import java.util.List;

public class Movie {

	private int id;

	private String name;

	private List<ShowTime> showTimes;

	private String releaseDateString;

	private String restrictions;

	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ShowTime> getShowTimes() {
		return showTimes;
	}

	public void setShowTimes(List<ShowTime> showTimes) {
		this.showTimes = showTimes;
	}

	public Movie(int id, String name, String releaseDateString,
			String restrictions, String description) {
		super();
		this.id = id;
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
			showTimes = new ArrayList<ShowTime>();
		}
		showTimes.add(showTime);
		showTime.setMovieId(id);
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
