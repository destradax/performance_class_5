package co.com.psl.elitemovie.examples;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class InsertUsers {

	public static void main(String[] args) throws SQLException {
		insertingWithPreparedStatementObject();
	}

	public static void insertingWithPreparedStatementObject() throws SQLException {

		Random random = new Random(System.currentTimeMillis());

		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUsername("postgres");
		dataSource.setPassword("Admin");
		dataSource.setUrl("jdbc:postgresql://localhost:5432/elitemovie");

		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			// Create SQL statement
			String SQL = "INSERT INTO application_user(id, age, firstname, lastname, male, premiumuser) VALUES(?, ?, ?, ?, ?, ?)";

			// Create PrepareStatement object
			PreparedStatement preparedStatement = conn.prepareStatement(SQL);

			// Set auto-commit to false
			conn.setAutoCommit(false);

			for (int i = 0; i < 500; i++) {
				// Set the variables
				preparedStatement.setInt(1, i);
				preparedStatement.setInt(2, random.nextInt(60));
				preparedStatement.setString(3, "UserName" + i);
				preparedStatement.setString(4, "UserLastName" + i);
				preparedStatement.setBoolean(5, random.nextBoolean());
				preparedStatement.setBoolean(6, random.nextBoolean());
				// Add it to the batch
				preparedStatement.addBatch();
			}

			// Create an int[] to hold returned values
			int[] count = preparedStatement.executeBatch();

			// Explicitly commit statements to apply changes
			conn.commit();

		} finally {
			if (conn != null) {
				conn.close();
			}
		}

	}
}
