package it.beije.pascal.esercizi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class EsercizioConnessioniForzate {

	public static void main(String[] args){
		
		testMaxConnectionsOpened();
		
		
		//*************** da finire calcolo tempo di time out ***************
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		try {
//			Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/rubrica?serverTimezone=CET", "root", "andrea23596");
//			System.out.println(LocalTime.now());
//			while(true) {
//				if(c.isClosed()) {
//					System.out.println("orario di chiusura" + LocalTime.now());
//				}
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
	}
	
	
	public static void testMaxConnectionsOpened() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Connection> connections = new ArrayList<>();
		int count = 0;
		for(int i=0; i<200; i++) {
			Connection c;
			try {
				c = DriverManager.getConnection("jdbc:mysql://localhost:3306/rubrica?serverTimezone=CET", "root", "andrea23596");
				connections.add(c);
				count++;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(count);
				break;
			}
		}
		
	}

}
