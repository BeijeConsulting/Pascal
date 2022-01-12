package it.beije.pascal.connectivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.time.LocalTime;

public class ConnectionPool {
	
	private static int counter = 0;

	public void howManyConnections() throws Exception { //ogni volta da un numero diverso, tra 950 e 1100
		try {
			while(true) {
				Class.forName("com.mysql.cj.jdbc.Driver");
				@SuppressWarnings("unused")
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rubrica?serverTimezone=CET", "root", "baba");
				counter++;
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println(counter);
		}
	}
	
	public void checkTimeOut() { // 8 ore
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rubrica?serverTimezone=CET", "root", "baba");
			
			System.out.println(LocalTime.now());
			while(!con.isClosed()) {
				//aspetta che la connessione si chiuda
			}
			System.out.println(LocalTime.now());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception {
		ConnectionPool cp = new ConnectionPool();
		cp.checkTimeOut();
	}
}
