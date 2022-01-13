package it.beije.pascal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionPool {

	private ConnectionPool() {
	}

	private static ConnectionPool instance;
	
	private int maxConnection = 10;

	public static ConnectionPool getInstance() {
		if (instance == null) {
			instance = new ConnectionPool();
		}

		return instance;
	}

	public Connection openConnection() throws SQLException {
		if (maxConnection > 0) {
			maxConnection--;
			Connection connection = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/rubrica?serverTimezone=CET", "root", "Chinetti");
			System.out.println("connessione aperta restano " + maxConnection + " connessioni");
			return connection;
		}
		System.out.println("connessione non aperta");
		return null;
	}

	public void closeConnection(Connection connection) throws SQLException {
		maxConnection++;
		System.out.println("connessione chiusa");
		connection.close();
	}

	public int getMaxConnection() {
		return maxConnection;
	}

}
