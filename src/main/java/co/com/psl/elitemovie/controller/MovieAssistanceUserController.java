package co.com.psl.elitemovie.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.psl.elitemovie.controller.dto.MovieAssistanceDto;
import co.com.psl.elitemovie.model.MovieAssistance;
import co.com.psl.elitemovie.repository.MovieAssistanceRepository;

@RestController
@RequestMapping("/rest")
public class MovieAssistanceUserController {

	@Autowired
	private MovieAssistanceRepository movieAssistanceRepository;

	@RequestMapping("/assistance/{id}")
	public MovieAssistanceDto findById(@PathVariable int id) {
		MovieAssistanceDto dto = null; 
		MovieAssistance movieAssistance = movieAssistanceRepository.findById(id);
		if (movieAssistance != null) {
			dto = transformToDto(movieAssistance);
		}
		return dto;
	}

	@RequestMapping("/assistance")
	public List<MovieAssistanceDto> findAll() {
		List<MovieAssistance> movieAssistances = new ArrayList<MovieAssistance>();
		List<MovieAssistanceDto> dtos = new ArrayList<MovieAssistanceDto>();
		
		movieAssistances = movieAssistanceRepository.findAll();
		
		for (MovieAssistance movieAssistance : movieAssistances) {
			dtos.add(transformToDto(movieAssistance));
		}
		
		
		return dtos;
	}
	
	private MovieAssistanceDto transformToDto(MovieAssistance movieAssistance) {
		MovieAssistanceDto dto = new MovieAssistanceDto();
		dto.setId(movieAssistance.getId());
		dto.setScore(movieAssistance.getScore());
		dto.setUserId(movieAssistance.getUser().getId());
		dto.setShowtimeId(movieAssistance.getShowTime().getId());
		
		return dto;
	}
}
