package in.harshalshewale.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class DatabaseUtil {

	private static final Logger LOGGER = Logger.getLogger(DatabaseUtil.class);

	public static Connection getConnection() throws IOException, SQLException, ClassNotFoundException {
		String DB_URL = FileUtil.readPropertyFile("database", "DB_URL");
		String DB_USER_NAME = FileUtil.readPropertyFile("database", "DB_USER_NAME");
		String DB_PASSWORD = FileUtil.readPropertyFile("database", "DB_PASSWORD");
		String driver = FileUtil.readPropertyFile("database", "DB_DRIVER");
		Class.forName(driver);
		System.out.println("db properties " + DB_URL + " " + DB_USER_NAME + " " + DB_PASSWORD);
		return DriverManager.getConnection(DB_URL, DB_USER_NAME, DB_PASSWORD);
	}

	public static void closeConnection(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				LOGGER.error("Error occured while closing connection " + e.getMessage());
			}
		}
	}

	public static void closePreparedStatement(PreparedStatement preparedStatement) {
		if (preparedStatement != null) {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				LOGGER.error("Error occured while closing prepared statement" + e.getMessage());
			}
		}

	}

	public static void insertTestResult(String testSuiteName, int testId, String testCaseName, String result,
			String comment) throws IOException {

		String environment = FileUtil.readPropertyFile("test", "environment");
		String browser = FileUtil.readPropertyFile("test", "browser");

		Connection connection = null;
		PreparedStatement preparedStmt = null;

		try {
			LOGGER.info("Writting API Requests Responses to Database");
			String query = "insert into webapp (environment,browser,testid,testsuite,testcase,result,comment)"
					+ " values (?, ?, ?, ?, ?, ?, ?)";
			connection = getConnection();
			preparedStmt = connection.prepareStatement(query);
			preparedStmt.setString(1, environment);
			preparedStmt.setString(2, browser);
			preparedStmt.setInt(3, testId);
			preparedStmt.setString(4, testSuiteName);
			preparedStmt.setString(5, testCaseName);
			preparedStmt.setString(6, result);
			preparedStmt.setString(7, comment);
			preparedStmt.execute();
		} catch (ClassNotFoundException | SQLException exception) {
			LOGGER.error("Error occured while writing data to Database " + exception.getMessage());
		} finally {
			closePreparedStatement(preparedStmt);
			closeConnection(connection);
		}

	}

}
