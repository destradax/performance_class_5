package co.com.psl.elitemovie.examples;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class BatchInsertingExamples {

	public static void main(String[] args) throws SQLException {
		insertingWithStatementObject();
		insertingWithPreparedStatementObject();
	}

	public static void insertingWithStatementObject() throws SQLException {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUsername("postgres");
		dataSource.setPassword("footprints");
		dataSource.setUrl("jdbc:postgresql://localhost:5432/elitemovie");

		Connection conn = null;

		try {

			conn = dataSource.getConnection();
			Statement statement = conn.createStatement();

			// Set auto-commit to false
			conn.setAutoCommit(false);

			String sqlQuery = "INSERT INTO comment(id, comment, author, parent_comment_id, movie_id)"
					+ "VALUES(1,'MyComment1', 'Sebastian', NULL, 1)";
			statement.addBatch(sqlQuery);

			sqlQuery = "INSERT INTO comment(id, comment, author, parent_comment_id, movie_id)"
					+ "VALUES(2,'MyComment2', 'Sebastian', 1, 1)";
			statement.addBatch(sqlQuery);

			sqlQuery = "INSERT INTO comment(id, comment, author, parent_comment_id, movie_id)"
					+ "VALUES(3,'MyComment3', 'Sebastian', 1, 1)";
			statement.addBatch(sqlQuery);

			sqlQuery = "INSERT INTO comment(id, comment, author, parent_comment_id, movie_id)"
					+ "VALUES(4,'MyComment4', 'Sebastian', 1, 1)";
			statement.addBatch(sqlQuery);

			// Create an int[] to hold returned values
			int[] count = statement.executeBatch();

			// Explicitly commit statements to apply changes
			conn.commit();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	public static void insertingWithPreparedStatementObject()
			throws SQLException {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUsername("postgres");
		dataSource.setPassword("footprints");
		dataSource.setUrl("jdbc:postgresql://localhost:5432/elitemovie");

		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			// Create SQL statement
			String SQL = "INSERT INTO comment(id, comment, author, parent_comment_id, movie_id) "
					+ "VALUES(?, ?, ?, ?, ?)";

			// Create PrepareStatement object
			PreparedStatement preparedStatement = conn.prepareStatement(SQL);

			// Set auto-commit to false
			conn.setAutoCommit(false);

			// Set the variables
			preparedStatement.setInt(1, 5);
			preparedStatement.setString(2, "MyComment5");
			preparedStatement.setString(3, "Sebastian");
			preparedStatement.setInt(4, 1);
			preparedStatement.setInt(5, 1);
			// Add it to the batch
			preparedStatement.addBatch();

			// Set the variables
			preparedStatement.setInt(1, 6);
			preparedStatement.setString(2, "MyComment6");
			preparedStatement.setString(3, "Sebastian");
			preparedStatement.setInt(4, 1);
			preparedStatement.setInt(5, 1);
			preparedStatement.addBatch();
			preparedStatement.setInt(1, 7);
			preparedStatement.setString(2, "MyComment7");
			preparedStatement.setString(3, "Sebastian");
			preparedStatement.setInt(4, 1);
			preparedStatement.setInt(5, 1);
			preparedStatement.addBatch();

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
