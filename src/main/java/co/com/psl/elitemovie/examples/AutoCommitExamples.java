package co.com.psl.elitemovie.examples;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class AutoCommitExamples {

	public static void main(String[] args) throws SQLException {
		autocommitOnExample();
		autocommitOffExample();
	}

	public static void autocommitOnExample() throws SQLException {
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

			// Set the variables
			preparedStatement.setInt(1, 1);
			preparedStatement.setString(2, "MyComment1");
			preparedStatement.setString(3, "Sebastian");
			preparedStatement.setInt(4, 1);
			preparedStatement.setInt(5, 1);
			// Commit
			preparedStatement.executeUpdate();

			// Set the variables
			preparedStatement.setInt(1, 2);
			preparedStatement.setString(2, "MyComment2");
			preparedStatement.setString(3, "Sebastian");
			preparedStatement.setInt(4, 1);
			preparedStatement.setInt(5, 1);
			// Commit
			preparedStatement.executeUpdate();

			preparedStatement.setInt(1, 3);
			preparedStatement.setString(2, "MyComment3");
			preparedStatement.setString(3, "Sebastian");
			preparedStatement.setInt(4, 1);
			preparedStatement.setInt(5, 1);
			// Commit
			preparedStatement.executeUpdate();

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	public static void autocommitOffExample() throws SQLException {
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

			conn.setAutoCommit(false);

			// Create PrepareStatement object
			PreparedStatement preparedStatement = conn.prepareStatement(SQL);

			// Set the variables
			preparedStatement.setInt(1, 5);
			preparedStatement.setString(2, "MyComment5");
			preparedStatement.setString(3, "Sebastian");
			preparedStatement.setInt(4, 1);
			preparedStatement.setInt(5, 1);
			preparedStatement.executeUpdate();

			// Set the variables
			preparedStatement.setInt(1, 6);
			preparedStatement.setString(2, "MyComment6");
			preparedStatement.setString(3, "Sebastian");
			preparedStatement.setInt(4, 1);
			preparedStatement.setInt(5, 1);
			preparedStatement.executeUpdate();

			preparedStatement.setInt(1, 7);
			preparedStatement.setString(2, "MyComment7");
			preparedStatement.setString(3, "Sebastian");
			preparedStatement.setInt(4, 1);
			preparedStatement.setInt(5, 1);
			preparedStatement.executeUpdate();

			// Explicitly commit statements to apply changes
			conn.commit();

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

}
