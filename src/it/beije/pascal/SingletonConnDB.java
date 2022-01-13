package it.beije.pascal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonConnDB {
	private static final String URL = "jdbc:mysql://localhost:3306/rubrica?serverTimezone=CET";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "rosario";
	
	private SingletonConnDB(){}
	
	private static SingletonConnDB instance;

	public static SingletonConnDB getInstance() {
		if(instance == null) {
			instance = new SingletonConnDB();
		}
		return instance;
	}
	
	public Connection getConnection() throws SQLException {		
		return DriverManager.getConnection(URL,USERNAME,PASSWORD);
		
	}

}
