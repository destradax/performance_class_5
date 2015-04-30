package co.com.psl.elitemovie.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.psl.elitemovie.controller.dto.DtoTransformer;
import co.com.psl.elitemovie.controller.dto.UserDto;
import co.com.psl.elitemovie.model.User;
import co.com.psl.elitemovie.repository.UserRepository;

@RestController
@RequestMapping("/rest")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@RequestMapping("/users/{id}")
	public UserDto findById(@PathVariable int id) {
		UserDto userDto = null; 
		User user = userRepository.findById(id);
		if (user != null) {
			userDto = DtoTransformer.toDto(user, UserDto.class);
		}
		return userDto;
	}

	@RequestMapping("/users")
	public List<UserDto> findAllUsers() {
		List<User> users = new ArrayList<User>();
		List<UserDto> userDtos = new ArrayList<UserDto>();
		
		users = userRepository.findAll();
		
		userDtos.addAll(DtoTransformer.toDto(users, UserDto.class));
		
		return userDtos;
	}
}
