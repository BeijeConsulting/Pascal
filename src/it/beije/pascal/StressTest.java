package it.beije.pascal;

import java.sql.Connection;
import java.sql.DriverManager;

//Max connessioni ad un database: 513

public class StressTest {
static int  count =0;
	public static void main(String[] args) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = null;
		int i = 0;
		try {
			connection = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/rubrica?serverTimezone=CET", "root", "Soos1234");
			while(!connection.isClosed()) {
			System.out.println(!connection.isClosed());
			//System.out.println(connection);
			//count++;
			//System.out.println(count);
			//statement = connection.createStatement();
			System.out.println(java.time.LocalTime.now());
			}
			} catch (Exception e) {
			e.printStackTrace();
			throw e;
				}
	}
		
	}
