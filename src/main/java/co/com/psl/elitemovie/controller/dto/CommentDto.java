package co.com.psl.elitemovie.controller.dto;

import java.util.Set;

public class CommentDto {

	private int id;

	private String author;

	private String comment;

	private CommentDto parentComment;

	private Set<CommentDto> childrenComments;

	private Integer movieId;

	private Integer level;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public CommentDto getParentComment() {
		return parentComment;
	}

	public void setParentComment(CommentDto parentComment) {
		this.parentComment = parentComment;
	}

	public Set<CommentDto> getChildrenComments() {
		return childrenComments;
	}

	public void setChildrenComments(Set<CommentDto> childrenComments) {
		this.childrenComments = childrenComments;
	}

	public Integer getMovieId() {
		return movieId;
	}

	public void setMovieId(Integer movieId) {
		this.movieId = movieId;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

}
