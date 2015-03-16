package co.com.psl.elitemovie.repository;

import java.util.List;

import co.com.psl.elitemovie.model.Comment;

public interface CommentRepository {

	void save(Comment comment);

	Comment findById(int commentId);

	List<Comment> findCommentsForMovie(int movieId);

}
