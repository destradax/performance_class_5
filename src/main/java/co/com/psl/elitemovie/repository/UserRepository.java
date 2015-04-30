package co.com.psl.elitemovie.repository;

import java.util.List;

import co.com.psl.elitemovie.model.User;

public interface UserRepository {

	User findById(int id);

	List<User> findAll();

}
