package co.com.psl.elitemovie.controller.dto;

import java.util.ArrayList;
import java.util.List;

public class MovieDto {

	private int id;

	private String name;

	private List<ShowTimeLightWeightDto> showTimes;

	private String releaseDateString;

	private String restrictions;

	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ShowTimeLightWeightDto> getShowTimes() {
		return showTimes;
	}

	public void setShowTimes(List<ShowTimeLightWeightDto> showTimes) {
		this.showTimes = showTimes;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public void addShowTime(ShowTimeLightWeightDto showTime) {
		if (showTimes == null) {
			showTimes = new ArrayList<ShowTimeLightWeightDto>();
		}
		showTimes.add(showTime);
	}

	public String getRestrictions() {
		return restrictions;
	}

	public void setRestrictions(String restrictions) {
		this.restrictions = restrictions;
	}
}
