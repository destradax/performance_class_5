package co.com.psl.elitemovie.controller.dto;

public class MovieLightWeightDto {

	private int id;

	private String name;

	private String genre;

	private String releaseDateString;

	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
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
}
