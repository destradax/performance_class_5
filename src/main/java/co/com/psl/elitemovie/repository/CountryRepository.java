package co.com.psl.elitemovie.repository;

import java.util.Collection;

import co.com.psl.elitemovie.model.Country;

public interface CountryRepository {

	Collection<Country> findAll();

	Country findById(int id);

	void save(Country country);
	
	Collection<Country> findAll(int offset, int limit);
	
	Country findByName(String name);
}
