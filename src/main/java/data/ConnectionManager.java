package data;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

public class ConnectionManager {

	public static Connection getConnection(DataSource datasource) {
		try {
			return datasource.getConnection();
		} catch (SQLException e) {
			throw new ConnectionException(e);
		}
	}
}
