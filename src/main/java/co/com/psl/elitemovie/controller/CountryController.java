package co.com.psl.elitemovie.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.com.psl.elitemovie.controller.dto.CountryDto;
import co.com.psl.elitemovie.repository.CountryRepository;

@RestController
@RequestMapping("/rest")
public class CountryController {

	@Autowired(required = false)
	private CountryRepository countryRepository;

	@RequestMapping(value="/country/", method=RequestMethod.GET)
	public List<CountryDto> findAll() {
		return null;
	}
	
	@RequestMapping(value="/country/name/{name}", method=RequestMethod.GET)
	public CountryDto findByName(@PathVariable String name) {
		return null;
	}

	@RequestMapping(value="/country/{id}", method=RequestMethod.GET)
	public CountryDto findByName(@PathVariable int id ) {
		return null;
	}

	@RequestMapping(value="/country/", method=RequestMethod.POST)
	public CountryDto save(@RequestBody CountryDto countryDto) {
		return null;
	}

	@RequestMapping(value="/country/{id}", method=RequestMethod.PUT)
	public CountryDto update(@RequestBody CountryDto countryDto, @PathVariable int id) {
		return null;
	}

}
