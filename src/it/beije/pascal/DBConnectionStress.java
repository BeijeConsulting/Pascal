package it.beije.pascal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DatabaseMetaData;


public class DBConnectionStress {

	public static final String SELECT = "SELECT * FROM Contatti";
	
	
	
	public static void main(String[] args) throws Exception {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection connection = null;
		
		int x = 0;
			
		try {
			
			
			while(true) {
				
			
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rubrica?serverTimezone=CET", "root", "ardente");
				
				PreparedStatement preparedStatement = connection.prepareStatement(DBConnectionStress.SELECT);
				preparedStatement.executeQuery();
				
				DatabaseMetaData metaData = connection.getMetaData();
				int max = metaData.getMaxConnections();
				
				x++;
				
				System.out.println("Connessione numero "+x+" aperta, massimo numero connessioni "+max);
			}
			
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			throw e;
			
		} finally {
			
			try {
				
				/*
				rs.close();
				statement.close();
				connection.close();
				*/
				
			} catch (Exception fEx) {
				fEx.printStackTrace();
			}
		}
		
		
		
	}

}
