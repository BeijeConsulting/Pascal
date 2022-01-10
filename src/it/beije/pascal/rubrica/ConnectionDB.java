package it.beije.pascal.rubrica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectionDB {

	public ConnectionDB() {
		
		
	}
	
	public Contatto cercaCognome(String where) throws Exception {
		
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Contatto trovato;
		
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		
		try {
			
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rubrica?serverTimezone=CET", "root", "ardente");
			
			trovato = new Contatto();
			
			System.out.println("Stato connessione "+ !connection.isClosed());
			
			
			statement = connection.createStatement();
			
			
			rs = statement.executeQuery("SELECT * FROM contatti WHERE cognome = '" + where + "'");
			
			while (rs.next()) {
				
				trovato.setId(rs.getInt("id"));
				trovato.setCognome(rs.getString("cognome"));
				trovato.setNome(rs.getString("nome"));
				trovato.setTelefono(rs.getString("telefono"));
				trovato.setEmail(rs.getString("email"));
				trovato.setNote(rs.getString("note"));
				
				
			}
			
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			throw e;
			
		} finally {
			
			try {
				
				rs.close();
				statement.close();
				connection.close();
				
			} catch (Exception fEx) {
				fEx.printStackTrace();
			}
		}
	
		
		return trovato;
		
	}
	
	
}
