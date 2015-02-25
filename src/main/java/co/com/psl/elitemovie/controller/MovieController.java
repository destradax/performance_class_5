package co.com.psl.elitemovie.controller;

import java.util.Calendar;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.psl.elitemovie.controller.dto.DtoTransformer;
import co.com.psl.elitemovie.controller.dto.MovieDto;
import co.com.psl.elitemovie.controller.dto.MovieLightWeightDto;
import co.com.psl.elitemovie.controller.dto.ShowTimeLightWeightDto;
import co.com.psl.elitemovie.model.Movie;
import co.com.psl.elitemovie.model.ShowTime;
import co.com.psl.elitemovie.repository.MovieRepository;

@RestController
@RequestMapping("/rest")
public class MovieController {

	@Autowired
	private MovieRepository movieRepository;

	@RequestMapping("/movie/")
	public List<MovieLightWeightDto> findAll() {
		return DtoTransformer.toDto(movieRepository.findAll(),
				MovieLightWeightDto.class);
	}

	@RequestMapping("/movie/{movieId}")
	public MovieDto findById(@PathVariable int movieId) {
		Movie movie = movieRepository.findById(movieId);
		MovieDto movieDto = DtoTransformer.toDto(movie, MovieDto.class);
		movieDto.setShowTimes(null);
		if (CollectionUtils.isNotEmpty(movie.getShowTimes())) {
			for (ShowTime showTime : movie.getShowTimes()) {

				if (showTime.getDate().before(Calendar.getInstance().getTime())) {
					// Is a past event, continue.
					continue;
				}

				ShowTimeLightWeightDto showTimeDto = DtoTransformer.toDto(
						showTime, ShowTimeLightWeightDto.class);
				showTimeDto.setTimeInMilliseconds(showTime.getDate().getTime());
				movieDto.addShowTime(showTimeDto);
			}
		}
		return movieDto;
	}

}
