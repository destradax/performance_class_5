package co.com.psl.elitemovie.config;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import co.com.psl.elitemovie.model.Movie;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * This class configure hibernate, jpa and all the required params for the
 * database connection and the ORM used in the spring boot application.
 */
@Configuration
public class DatabaseConfiguration implements EnvironmentAware {

	private final Logger log = LoggerFactory
			.getLogger(DatabaseConfiguration.class);

	private RelaxedPropertyResolver propertyResolver;

	@Autowired
	Environment env;

	@Override
	public void setEnvironment(Environment environment) {
		this.propertyResolver = new RelaxedPropertyResolver(environment);
	}

	/**
	 * This bean scan all the entities in the project and mapp then into the
	 * database, all the connections properties are passed in the data source
	 * object.
	 * 
	 * @param dataSource
	 * @return
	 */
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(
			DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean localContainerFactory = new LocalContainerEntityManagerFactoryBean();
		localContainerFactory.setDataSource(dataSource);
		localContainerFactory.setPackagesToScan(Movie.class.getPackage()
				.getName());
		localContainerFactory
				.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		localContainerFactory.setJpaProperties(additionalProperties());
		return localContainerFactory;

	}

	// @Bean
	// public DataSource dataSource() throws URISyntaxException {
	// DriverManagerDataSource dataSource = new DriverManagerDataSource();
	// dataSource.setUsername("postgres");
	// dataSource.setPassword("footprints");
	// dataSource.setUrl(propertyResolver.getProperty("url"));
	// return dataSource;
	// }

	/**
	 * DataSource including connection pooling
	 */
	@Bean
	public ComboPooledDataSource dataSource() throws PropertyVetoException {
		ComboPooledDataSource c3p0DataSource = new ComboPooledDataSource();
		c3p0DataSource.setUser("postgres");
		c3p0DataSource.setPassword("footprints");
		c3p0DataSource.setDriverClass(propertyResolver
				.getProperty("driverClassName"));
		c3p0DataSource.setJdbcUrl(propertyResolver.getProperty("url"));
		c3p0DataSource.setMinPoolSize(Integer.parseInt(propertyResolver
				.getProperty("minPoolSize")));
		c3p0DataSource.setMaxPoolSize(Integer.parseInt(propertyResolver
				.getProperty("maxPoolSize")));
		c3p0DataSource.setAcquireIncrement(Integer.parseInt(propertyResolver
				.getProperty("aquireIncrement")));
		c3p0DataSource
				.setIdleConnectionTestPeriod(Integer.parseInt(propertyResolver
						.getProperty("idleConnectionPeriod")));

		return c3p0DataSource;
	}

	@Bean
	public PlatformTransactionManager transactionManager(
			EntityManagerFactory emf) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);
		return transactionManager;
	}

	Properties additionalProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect",
				"org.hibernate.dialect.PostgreSQLDialect");
		properties.setProperty("hibernate.show_sql", "true");
		properties.setProperty("hibernate.hbm2ddl.auto", "update");
		log.info("hibernate properties: " + properties.toString());

		return properties;
	}

}
