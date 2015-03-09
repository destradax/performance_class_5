package co.com.psl.elitemovie.repository;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

public interface PersistenceService {

	@Transactional
	<T> T save(T entity);

	@Transactional
	<T> T update(T entity);

	@Transactional
	<T> void remove(T entity);

	<T> T findById(Class<T> entityClass, int id);

	/**
	 * The annotation @Transactional create the factory, initialize the entity
	 * manager, then initialize the transaction, do the persist, and in the end 
	 * close the connection with the database.
	 * @param resultClass
	 * @param query
	 * @param parameters
	 * @return List with all the results.
	 */
	@Transactional
	<T> List<T> executeQuery(Class<T> resultClass, String query,
			String... parameters);
	
	@Transactional
	<T> List<T> executeQuery(Class<T> resultClass, String query,
			List<Object> parameters);
	
	@Transactional
	<T> T executeQueryWithSingleResult(Class<T> resultClass, String query,
			String... parameters);

	
	/**
	 * Execute and <b>UPDATE</b> or<b>DELETE</b> query, return the number of rows afected.
	 * @param query
	 * @param parameters
	 * @return int, the number of rows afected in the deleted or update 
	 * query
	 */
	@Transactional
	int executeDeleteOrUpdateQuery(String query, List<Object> parameters);

}