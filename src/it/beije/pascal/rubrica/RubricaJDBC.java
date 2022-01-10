package it.beije.pascal.rubrica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class RubricaJDBC {

	Connection connection;
	
	public RubricaJDBC() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connection = null;
		
	}
	
	public static void main(String[] args) throws Exception {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rubrica?serverTimezone=CET", "root", "Lobbiani");

			System.out.println(!connection.isClosed());
			
			statement = connection.createStatement();
			
			//SELECT
			rs = statement.executeQuery("SELECT * FROM contatti WHERE cognome = '" + args[0] + "'");
			
			while (rs.next()) {
				System.out.println("id : " + rs.getInt("id"));
				System.out.println("cognome : " + rs.getString("cognome"));
				System.out.println("nome : " + rs.getString("nome"));
				System.out.println("telefono : " + rs.getString("telefono"));
				System.out.println("email : " + rs.getString("email"));
				System.out.println("note : " + rs.getString("note"));
				System.out.println("\n");
			}
			
			
			//INSERT
//			int r = statement.executeUpdate("INSERT INTO contatti VALUES (null, 'Verdi', 'Mauro', '3474646467', 'verdi.mauro@beije.it', 'sono un nuovo contatto')");
//			System.out.println("r = " + r);
			
			//UPDATE
//			int r = statement.executeUpdate("UPDATE contatti SET cognome = 'rossi' WHERE cognome = 'rosa'");
//			System.out.println("r = " + r);

			//DELETE
//			int r = statement.executeUpdate("DELETE FROM contatti WHERE cognome = 'verdi'");
//			System.out.println("r = " + r);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				//rs.close();
				statement.close();
				connection.close();
			} catch (Exception fEx) {
				fEx.printStackTrace();
			}
		}
		
	}
	
	
	
	public void inserisciContatto(Contatto contatto) {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rubrica?serverTimezone=CET", "root", "Lobbiani");
			Statement statement = connection.createStatement();
			
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("INSERT INTO contatti (nome, cognome, telefonoemail, note) VALUES");
			
			
			
			ResultSet rs = statement.executeQuery("INSERT INTO contatti (nome, cognome, telefonoemail, note) VALUES (" 
					+ contatto.getNome() + ", " 
					+ contatto.getCognome() + ", " 
					+ contatto.getTelefono() + ", " 
					+ contatto.getEmail() + ", " 
					+ contatto.getNote() + ")");
			
			
			System.out.println("risultato della query di inserimento: " + rs.next());
			rs.close();
			
		//TODO
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		try {
			if (!connection.isClosed()) {
				connection.close();
			}
		} catch (SQLException e) {
			System.out.println("errore nella chiusura della connessione");
			e.printStackTrace();
		}
	}
		
	}
	
	public Contatto cercaContatto(String[] campi, String[] parametri) {
		//TODO
		return null;
	}
	
	public void modificaContatto(int id, String[]campi, String[] parametri) {
		//TODO
	}
}
