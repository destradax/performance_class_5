package co.com.psl.elitemovie.controller;

import java.util.HashSet;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.psl.elitemovie.controller.dto.CommentDto;
import co.com.psl.elitemovie.model.Comment;
import co.com.psl.elitemovie.model.Movie;
import co.com.psl.elitemovie.repository.CommentRepository;
import co.com.psl.elitemovie.repository.MovieRepository;

import com.google.common.collect.Lists;

@RestController
@RequestMapping("/rest")
public class CommentController {

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private MovieRepository movieRepository;

	@RequestMapping("/comment/for_movie/{movieId}")
	public List<CommentDto> findCommentsForMovie(@PathVariable int movieId) {
		List<Comment> comments = commentRepository
				.findCommentsForMovie(movieId);
		List<CommentDto> commentDtos = Lists.newArrayList();
		for (Comment comment : comments) {
			commentDtos.add(transformToDto(comment, 0));
		}
		return commentDtos;
	}

	@RequestMapping("/comment/{movieId}/{parentCommentId}")
	public void saveNewComment(@PathVariable Integer parentCommentId,
			@PathVariable Integer movieId,
			@RequestBody CommentDto commentToSubmitDto) {
		Comment comment = new Comment();
		comment.setComment(commentToSubmitDto.getComment());
		comment.setAuthor(commentToSubmitDto.getAuthor());

		Movie movie = movieRepository.findById(movieId);
		comment.setMovie(movie);

		if (parentCommentId != null && parentCommentId > 0) {
			Comment parentComment = commentRepository.findById(parentCommentId);
			comment.setParentComment(parentComment);
		}
		commentRepository.save(comment);
	}

	private CommentDto transformToDto(Comment comment, int level) {
		CommentDto commentDto = new CommentDto();
		commentDto.setId(comment.getId());
		commentDto.setMovieId(comment.getMovie().getId());
		commentDto.setAuthor(comment.getAuthor());
		commentDto.setLevel(level);
		commentDto.setComment(comment.getComment());
		if (CollectionUtils.isNotEmpty(comment.getChildrenComments())) {
			commentDto.setChildrenComments(new HashSet<CommentDto>());
			for (Comment childComment : comment.getChildrenComments()) {
				commentDto.getChildrenComments().add(
						transformToDto(childComment, level + 1));
			}
		}
		return commentDto;
	}

}
