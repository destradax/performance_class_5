package co.com.psl.elitemovie.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;

@Component("jpaPersistenceService")
public class JpaPersistenceService implements PersistenceService {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public <T> T save(T entity) {
		entityManager.persist(entity);
		entityManager.flush();
		return entity;
	}

	@Override
	public <T> T update(T entity) {
		return entityManager.merge(entity);
	}

	@Override
	public <T> void remove(T entity) {
		entityManager.remove(entityManager.merge(entity));
	}

	@Override
	public <T> T findById(Class<T> entityClass, int id) {
		return entityManager.find(entityClass, id);
	}

	@Override
	public <T> List<T> executeQuery(Class<T> resultClass, String query,
			String... parameters) {
		TypedQuery<T> typedQuery = entityManager
				.createQuery(query, resultClass);
		if (parameters != null) {
			for (int i = 0; i < parameters.length; i++) {
				typedQuery.setParameter(i + 1, parameters[i]);
			}
		}
		return typedQuery.getResultList();
	}

	@Override
	public <T> List<T> executeQuery(Class<T> resultClass, String query,
			List<Object> parameters) {
		TypedQuery<T> typedQuery = entityManager
				.createQuery(query, resultClass);
		if (parameters != null) {
			for (int i = 0; i < parameters.size(); i++) {
				typedQuery.setParameter(i + 1, parameters.get(i));
			}
		}
		return typedQuery.getResultList();
	}

	@Override
	public int executeDeleteOrUpdateQuery(String query, List<Object> parameters) {
		Query namedQuery = entityManager.createQuery(query);
		if (parameters != null) {
			for (int i = 0; i < parameters.size(); i++) {
				namedQuery.setParameter(i + 1, parameters.get(i));
			}
		}
		return namedQuery.executeUpdate();
	}

	@Override
	public <T> T executeQueryWithSingleResult(Class<T> resultClass,
			String query, String... parameters) {
		TypedQuery<T> typedQuery = entityManager
				.createQuery(query, resultClass);
		if (parameters != null) {
			for (int i = 0; i < parameters.length; i++) {
				typedQuery.setParameter(i + 1, parameters[i]);
			}
		}
		try {
			return typedQuery.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}