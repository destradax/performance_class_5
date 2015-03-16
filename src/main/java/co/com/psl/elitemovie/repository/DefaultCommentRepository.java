package co.com.psl.elitemovie.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.psl.elitemovie.model.Comment;

@Component
public class DefaultCommentRepository implements CommentRepository {

	@Autowired
	private PersistenceService persistenceService;

	@Override
	public void save(Comment comment) {
		persistenceService.save(comment);
	}

	@Override
	public Comment findById(int commentId) {
		return persistenceService.findById(Comment.class, commentId);
	}

	@Override
	public List<Comment> findCommentsForMovie(int movieId) {
		return persistenceService
				.executeQuery(
						Comment.class,
						"SELECT c FROM Comment c WHERE c.movie.id=? AND c.parentComment IS NULL",
						movieId);
	}
}
