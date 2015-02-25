package co.com.psl.elitemovie.repository;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import co.com.psl.elitemovie.model.Movie;
import co.com.psl.elitemovie.model.ShowTime;

@Component
public class DataInitialization {

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private ShowTimeRepository showTimeRepository;

	@Value("${start_date_for_movies}")
	private String startDateForMoviesString;

	private DateFormat dateFormatForMovies = new SimpleDateFormat(
			"dd/MM/yyyy HH:mm");

	private int showTimesCount = 1;

	@PostConstruct
	public void init() throws ParseException {

		Movie movie1 = new Movie(
				1,
				"El Violinista del Diablo",
				"Jueves, Diciembre 18, 2014",
				"RECOMENDADA PARA MAYORES DE 15 AÑOS",
				"La historia de Niccolo Paganini, el más grande violinista y compositor del Siglo XIX. Apostó todo por la música, "
						+ "incluso su alma. Un viaje a través del delirio del compositor, la lujuria de sus pasiones, y el escándalo "
						+ "asociado a su fama. Protagonizada por el prodigioso músico David Garrett.");
		addDefaultShowTimes(movie1, CinemaTemplateType.ALL_AVAILABLE);

		Movie movie2 = new Movie(
				2,
				"El libro de la vida",
				"Jueves, Octubre 16, 2014",
				"PARA TODO EL PÚBLICO",
				"Cuenta el viaje de Manolo, un joven torero que se debate entre cumplir con las expectativas de su familia o seguir su corazón y dedicarse a su verdadera pasión: la música. Antes de escoger el camino que seguirá,"
						+ " se embarca en una aventura por tres mundos fantásticos donde deberá hacer frente a sus mayores miedos.");
		addDefaultShowTimes(movie2, CinemaTemplateType.FULL_SCENARIO);

		Movie movie3 = new Movie(
				3,
				"Donde se esconde el diablo",
				"Jueves, Diciembre 11, 2014",
				"RECOMENDADA PARA MAYORES DE 15 AÑOS",
				"Cuando de diferentes madres nacen seis niñas, en el sexto día, del sexto mes, una antigua profecía se pone en marcha en un pequeño pueblo. "
						+ "Sin embargo, el terror se apoderará de la comunidad cuando al cumplir los 18 años una de estas niñas se convierte en 'La mano del Diablo'. Así muchas jóvenes comenzarán a desaparecer y una sucesión de asesinatos causará el pánico en toda la comunidad.");
		addDefaultShowTimes(movie3, CinemaTemplateType.TWO_CONSECUTIVE_SCENARIO);

		Movie movie4 = new Movie(4, "Primicia Mortal",
				"Jueves, Diciembre 15, 2014",
				"RECOMENDADA PARA MAYORES DE 18 AÑOS", "Primicia Mortal");
		addDefaultShowTimes(movie4,
				CinemaTemplateType.THREE_CONSECUTIVE_SCENARIO);

		Movie movie5 = new Movie(5, "Relatos Salvajes",
				"Jueves, Diciembre 15, 2014",
				"RECOMENDADA PARA MAYORES DE 12 AÑOS", "Relatos Salvaje");
		addDefaultShowTimes(movie5,
				CinemaTemplateType.FOUR_CONSECUTIVE_SCENARIO);

		Movie movie6 = new Movie(6, "Éxodo, Dioses y Reyes",
				"Jueves, Diciembre 15, 2014",
				"RECOMENDADA PARA MAYORES DE 18 AÑOS", "Éxodo, Dioses y Reyes");
		addDefaultShowTimes(movie6, CinemaTemplateType.SIX_CONSECUTIVE_SCENARIO);

		Movie movie7 = new Movie(7, "Pancho, el perro millonario",
				"Jueves, Diciembre 15, 2014", "RECOMENDADA PARA TODO PÚBLICO",
				"Pancho, el perro millonario");
		addDefaultShowTimes(movie7, CinemaTemplateType.TWO_RANDOM_SCENARIO);

		Movie movie8 = new Movie(8, "Petecuy, la película",
				"Jueves, Diciembre 15, 2014", "RECOMENDADA PARA TODO PÚBLICO",
				"Petecuy, la película");
		addDefaultShowTimes(movie8, CinemaTemplateType.FOUR_RANDOM_SCENARIO);

		Movie movie9 = new Movie(9, "Stockholm", "Jueves, Diciembre 15, 2014",
				"RECOMENDADA PARA TODO PÚBLICO", "Stockholm");
		addDefaultShowTimes(movie9, CinemaTemplateType.FIVE_RANDOM_SCENARIO);

		Movie movie10 = new Movie(10, "Los Juegos del Hambre: Sinsajo Parte 1",
				"Jueves, Diciembre 15, 2014", "RECOMENDADA PARA TODO PÚBLICO",
				"Los Juegos del Hambre: Sinsajo Parte 1");
		addDefaultShowTimes(movie10, CinemaTemplateType.SIX_RANDOM_SCENARIO);

		addToRepositories(movie1);
		addToRepositories(movie2);
		addToRepositories(movie3);
		addToRepositories(movie4);
		addToRepositories(movie5);
		addToRepositories(movie6);
		addToRepositories(movie7);
		addToRepositories(movie8);
		addToRepositories(movie9);
		addToRepositories(movie10);
	}

	private void addToRepositories(Movie movie) {
		movieRepository.add(movie);
		for (ShowTime showTime : movie.getShowTimes()) {
			showTimeRepository.add(showTime);
		}
	}

	private void addDefaultShowTimes(Movie movie, CinemaTemplateType template)
			throws ParseException {
		ShowTime showTime1 = new ShowTime(showTimesCount++,
				dateFormatForMovies.parse(startDateForMoviesString + " 18:00"));
		showTime1.setSeats(CinemaTemplateGeneratorUtil
				.getByTemplateType(template));
		ShowTime showTime2 = new ShowTime(showTimesCount++,
				dateFormatForMovies.parse(startDateForMoviesString + " 20:00"));
		showTime2.setSeats(CinemaTemplateGeneratorUtil
				.getByTemplateType(template));
		ShowTime showTime3 = new ShowTime(showTimesCount++,
				dateFormatForMovies.parse(startDateForMoviesString + " 22:00"));
		showTime3.setSeats(CinemaTemplateGeneratorUtil
				.getByTemplateType(template));
		movie.addShowTime(showTime1);
		movie.addShowTime(showTime2);
		movie.addShowTime(showTime3);
	}
}
