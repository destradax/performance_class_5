package co.com.psl.elitemovie.repository;

import java.util.List;

import co.com.psl.elitemovie.model.MovieAssistance;

public interface MovieAssistanceRepository {

	MovieAssistance findById(int id);

	List<MovieAssistance> findAll();

}
