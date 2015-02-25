package co.com.psl.elitemovie.controller;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.psl.elitemovie.controller.dto.DtoTransformer;
import co.com.psl.elitemovie.controller.dto.SeatDto;
import co.com.psl.elitemovie.model.Seat;
import co.com.psl.elitemovie.model.ShowTime;
import co.com.psl.elitemovie.repository.ShowTimeRepository;
import co.com.psl.elitemovie.service.SeatRecommendationsService;

@RestController
@RequestMapping("/rest")
public class SeatRecommendationController {

	@Autowired
	private ShowTimeRepository showTimeRepository;

	@Autowired
	private SeatRecommendationsService seatRecommendationsService;

	@RequestMapping("/seatrecommendation/{showTimeId}/{numberOfSeats}")
	public SeatDto[] recommendSeats(@PathVariable int showTimeId,
			@PathVariable int numberOfSeats) {
		ShowTime showTime = showTimeRepository.findById(showTimeId);
		Seat[] recommendations = seatRecommendationsService.recommendSeats(
				showTime, numberOfSeats);
		if (ArrayUtils.isEmpty(recommendations)) {
			return new SeatDto[0];
		}
		SeatDto[] response = new SeatDto[recommendations.length];
		int count = 0;
		for (Seat seat : recommendations) {
			SeatDto dto = DtoTransformer.toDto(seat, SeatDto.class);
			response[count++] = dto;
		}
		return response;
	}

}
