package co.com.psl.elitemovie.repository;

import java.text.ParseException;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import co.com.psl.elitemovie.model.Comment;
import co.com.psl.elitemovie.model.Movie;

/**
 * This class inserts data in the DB. Only works with the @PostConstruct and @Component
 * annotations enabled. Take care of allowing hbm2ddl option configured as well.
 */
// @Component
public class DataInitialization {

	private static final int MAX_DEEP_LEVEL = 5;

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private ShowTimeRepository showTimeRepository;

	@Autowired
	private SeatRepository seatRepository;

	@Autowired
	private CommentRepository commentRepository;

	@Value("${start_date_for_movies}")
	private String startDateForMoviesString;

	// @PostConstruct
	public void init() throws ParseException {
		Collection<Movie> movies = movieRepository.findAll();

		for (Movie m : movies) {
			createCrazyCommentsStructure(m);
		}

	}

	private void createCrazyCommentsStructure(Movie movie) {
		for (int i = 0; i < 15; i++) {
			Comment rootComment = new Comment("my user",
					"This is a root comment #" + i, null, movie);
			commentRepository.save(rootComment);

			createChildrenComments(rootComment, 1, 2, movie);

		}

	}

	private void createChildrenComments(Comment parentComment, int level,
			int numberOfChildren, Movie movie) {
		for (int i = 0; i < numberOfChildren; i++) {
			Comment childrenComment = new Comment("my user",
					"this is a child comment #" + i + " in level " + level,
					parentComment, movie);
			commentRepository.save(childrenComment);
			if (level < MAX_DEEP_LEVEL) {
				createChildrenComments(childrenComment, level + 1,
						numberOfChildren, movie);
			}
		}
	}

}
