package co.com.psl.elitemovie.service;

import org.springframework.stereotype.Service;

import co.com.psl.elitemovie.model.Seat;
import co.com.psl.elitemovie.model.ShowTime;

@Service
public class DefaultSeatRecommendationsService implements
		SeatRecommendationsService {

	@Override
	public Seat[] recommendSeats(ShowTime showTime, int numberOfSeats) {
		return new Seat[0];
	}

}
