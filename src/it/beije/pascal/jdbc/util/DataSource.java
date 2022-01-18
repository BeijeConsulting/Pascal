package it.beije.pascal.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {
	private static final String URL = "jdbc:mysql://localhost:3306/rubrica?serverTimezone=CET";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "admin";
	
	private static DataSource instance;

	private DataSource() {
		
	}
	
	public static DataSource getInstance() {
		if(instance == null) {
			instance = new DataSource();
		}
		return instance;
	}
	
	public Connection getConnection() throws SQLException {		
		return DriverManager.getConnection(URL,USERNAME,PASSWORD);
		
	}
}
