package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	public static Connection getConnection(String ip, int port, String dbName, String user, String password) throws SQLException {
		return DriverManager.getConnection
				(
					"jdbc:mysql://"+ip+":"+port+"/"+dbName,
					user,
					password
				);
	}

}
