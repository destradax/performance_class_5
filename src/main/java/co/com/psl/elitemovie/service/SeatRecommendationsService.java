package co.com.psl.elitemovie.service;

import co.com.psl.elitemovie.model.Seat;
import co.com.psl.elitemovie.model.ShowTime;

public interface SeatRecommendationsService {

	Seat[] recommendSeats(ShowTime showTime, int numberOfSeats);
}
