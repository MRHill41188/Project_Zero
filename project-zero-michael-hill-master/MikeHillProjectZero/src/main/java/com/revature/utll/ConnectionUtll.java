package com.revature.utll;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtll {

	public static Connection getConnection() {
		/*
		 * Values stored in System environment variables.  Note: Spring tools suite
		 * will not have access to new environment variables until you restart it.
		 */
		String url = System.getenv("JDBC_URL");
		String user = System.getenv("JDBC_ROLE");
		String password = System.getenv("JDBC_PASSWORD");
		// jdbc:postgresql://host:port/database_name
		
		try {
			return DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
