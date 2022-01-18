package it.beije.pascal.esercizi.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import it.beije.pascal.Singleton;

public class ConnectionPull {

	private ConnectionPull() {
		connections = new Connection[5];
	}
	
	private static ConnectionPull instance;
	private static Connection[] connections;
	
	public static ConnectionPull getInstance() {
		if (instance == null) {
			instance = new ConnectionPull();
		}
		return instance;
	}

	public static Connection getConnection() {

		for (int i = 0; i < connections.length; i++) {
			try {
				if (connections[i].isClosed() || connections[i] == null) {
					connections[i] = DriverManager.getConnection("jdbc:mysql://localhost:3306/rubrica?serverTimezone=CET", "root", "andrea23596");;
					return connections[i];
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;

	}

}
