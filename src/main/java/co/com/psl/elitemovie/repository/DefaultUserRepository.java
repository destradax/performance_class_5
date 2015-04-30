package co.com.psl.elitemovie.repository;

import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.com.psl.elitemovie.model.User;

@Repository
@Transactional
public class DefaultUserRepository implements UserRepository {

	@Autowired
	private PersistenceService persistenceService;

	@Autowired
	private CacheManager cacheManager;

	@Override
	public User findById(int id) {
		Cache cache = cacheManager.getCache("users");
		if (cache.get(id) != null) {
			return (User) cache.get(id).getObjectValue();
		}
		User user = persistenceService.findById(User.class, id);
		cache.put(new Element(id, user));
		return user;
	}


	@Override
	public List<User> findAll() {
		return persistenceService.executeQuery(User.class, "SELECT user FROM User user");
	}

}
