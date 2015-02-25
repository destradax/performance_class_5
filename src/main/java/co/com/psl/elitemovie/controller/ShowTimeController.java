package co.com.psl.elitemovie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.psl.elitemovie.controller.dto.DtoTransformer;
import co.com.psl.elitemovie.controller.dto.SeatDto;
import co.com.psl.elitemovie.controller.dto.ShowTimeDto;
import co.com.psl.elitemovie.model.Seat;
import co.com.psl.elitemovie.model.ShowTime;
import co.com.psl.elitemovie.repository.ShowTimeRepository;

@RestController
@RequestMapping("/rest")
public class ShowTimeController {

	@Autowired
	private ShowTimeRepository showTimeRepository;

	@RequestMapping("/showtime/{showTimeId}")
	public ShowTimeDto findById(@PathVariable int showTimeId) {
		ShowTime showTime = showTimeRepository.findById(showTimeId);
		ShowTimeDto showTimeDto = DtoTransformer.toDto(showTime,
				ShowTimeDto.class);
		showTimeDto.setTimeInMilliseconds(showTime.getDate().getTime());
		showTimeDto.setSeats(null);
		for (Seat[] row : showTime.getSeats()) {
			for (Seat seat : row) {
				showTimeDto.addSeat(DtoTransformer.toDto(seat, SeatDto.class));
			}
		}
		return showTimeDto;
	}

	@RequestMapping("/showtime/available/{showTimeId}")
	public int findAvailableSeats(@PathVariable int showTimeId) {
		ShowTime showTime = showTimeRepository.findById(showTimeId);
		return showTime.countAvailableSeats();
	}
}
