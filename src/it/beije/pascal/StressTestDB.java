package it.beije.pascal;

import java.sql.Connection;
import java.time.LocalTime;

public class StressTestDB {
	public static void limiteConnessioni() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
;		int i = 0;
		try {
			while(true) {
				Connection connect = SingletonConnDB.getInstance().getConnection();
			    i++;
			}
		}catch(Exception e) {
			System.out.println(i);
			e.printStackTrace();
		}
	}
	
	public static void waitCloseConnection() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection connect = SingletonConnDB.getInstance().getConnection();
		
		System.out.print(LocalTime.now());
		while(!connect.isClosed()) {
			//Aspetta che si chiude
		}
		System.out.print(LocalTime.now());
	}

	public static void main(String[] args) throws Exception {
		limiteConnessioni();
		waitCloseConnection();
	}	

}
