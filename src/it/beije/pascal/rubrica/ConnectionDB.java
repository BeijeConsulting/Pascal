package it.beije.pascal.rubrica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ConnectionDB {

	public static final String SELECT_COGNOME = "SELECT * FROM contatti WHERE cognome = ?";
	public static final String SELECT_NOME = "SELECT * FROM contatti WHERE nome = ?";
	public static final String SELECT_ALL = "SELECT * FROM contatti";
	public static final String INSERT_INTO_RUBRICA = "INSERT INTO contatti (cognome, nome, telefono, email, note) VALUES (?,?,?,?,?)";
	public static final String UPDATE = "UPDATE contatti SET cognome = ? WHERE cognome = ?";
	public static final String DELETE = "DELETE FROM contatti WHERE cognome = ?";
	public static final String DUPLICATE_BY_NUMBER = "SELECT id, cognome, nome, telefono, email, note, count(telefono) FROM contatti GROUP BY telefono HAVING COUNT(telefono)>1";
	
	public ConnectionDB() {
		
		
	}
	
	public static Contatto cercaCognomeDB(String where) throws Exception {
		
		
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
			
			PreparedStatement preparedStatement = connection.prepareStatement(ConnectionDB.SELECT_COGNOME);
			preparedStatement.setString(1, where);
			rs = preparedStatement.executeQuery();
			
			// rs = statement.executeQuery("SELECT * FROM contatti WHERE cognome = '" + where + "'");
			
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
	
	public static Contatto cercaNomeDB(String where) throws Exception {
		
		
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
			
			PreparedStatement preparedStatement = connection.prepareStatement(ConnectionDB.SELECT_NOME);
			preparedStatement.setString(1, where);
			rs = preparedStatement.executeQuery();
			
			// rs = statement.executeQuery("SELECT * FROM contatti WHERE cognome = '" + where + "'");
			
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
	
	public static List<Contatto> trovaRubricaDB() throws Exception {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		List<Contatto> rubrica = new ArrayList<Contatto>();
		
		Contatto trovato;
		
		Connection connection = null;
		ResultSet rs = null;
		
		try {
			
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rubrica?serverTimezone=CET", "root", "ardente");
			
			trovato = new Contatto();
			
			System.out.println("Stato connessione "+ !connection.isClosed());
			
			PreparedStatement preparedStatement = connection.prepareStatement(ConnectionDB.SELECT_ALL);
			
			rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				
				trovato = new Contatto();
				trovato.setId(rs.getInt("id"));
				trovato.setCognome(rs.getString("cognome"));
				trovato.setNome(rs.getString("nome"));
				trovato.setTelefono(rs.getString("telefono"));
				trovato.setEmail(rs.getString("email"));
				trovato.setNote(rs.getString("note"));
				
				rubrica.add(trovato);

			}
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			throw e;
			
		} finally {
			
			try {
				
				rs.close();
				connection.close();
				
			} catch (Exception fEx) {
				fEx.printStackTrace();
			}
		}
		
		return rubrica;

	}
	
	public static void addDB(Contatto nuovo) throws Exception {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		
		Connection connection = null;
		Statement statement = null;
		
		try {
			
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rubrica?serverTimezone=CET", "root", "ardente");
			
			
			System.out.println("Stato connessione "+ !connection.isClosed());
			
			
			statement = connection.createStatement();
			
			PreparedStatement psInsert = connection.prepareStatement(ConnectionDB.INSERT_INTO_RUBRICA);
			
			psInsert.setString(1, nuovo.getCognome());
			psInsert.setString(2, nuovo.getNome());
			psInsert.setString(3, nuovo.getTelefono());
			psInsert.setString(4, nuovo.getEmail());
			psInsert.setString(5, nuovo.getNote());
			
			int r = psInsert.executeUpdate();
			System.out.println("Inserimento eseguito? r = " + r);
			
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			throw e;
			
		} finally {
			
			try {
				
				statement.close();
				connection.close();
				
			} catch (Exception fEx) {
				fEx.printStackTrace();
			}
		}
	
		
	}
	
	public static void updateDB(String vecchioCognome, String nuovoCognome) throws Exception {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		
		Connection connection = null;
		Statement statement = null;
		
		try {
			
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rubrica?serverTimezone=CET", "root", "ardente");
			
			System.out.println("Stato connessione "+ !connection.isClosed());
			
			statement = connection.createStatement();
			
			PreparedStatement update = connection.prepareStatement(ConnectionDB.UPDATE);
			update.setString(1, nuovoCognome);
			update.setString(2, vecchioCognome);
			
			int r = update.executeUpdate();
			System.out.println("Modifica Eseguita? r = " + r);
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			throw e;
			
		} finally {
			
			try {
				
				statement.close();
				connection.close();
				
			} catch (Exception fEx) {
				fEx.printStackTrace();
			}
		}
		
		
	}
	
	public static void deleteFromDB(String cognome) throws Exception {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		
		Connection connection = null;
		Statement statement = null;
		
		try {
			
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rubrica?serverTimezone=CET", "root", "ardente");
			
			System.out.println("Stato connessione "+ !connection.isClosed());
			
			statement = connection.createStatement();
			
			PreparedStatement update = connection.prepareStatement(ConnectionDB.DELETE);
			update.setString(1, cognome);
			
			int r = update.executeUpdate();
			System.out.println("Eliminazione Eseguita? r = " + r);
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			throw e;
			
		} finally {
			
			try {
				
				statement.close();
				connection.close();
				
			} catch (Exception fEx) {
				fEx.printStackTrace();
			}
		}
		
	}
	
	public static void addListDB(List<Contatto> rubrica) throws Exception {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		
		Connection connection = null;
		Statement statement = null;
		
		try {
			
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rubrica?serverTimezone=CET", "root", "ardente");
			
			
			System.out.println("Stato connessione "+ !connection.isClosed());
			
			
			statement = connection.createStatement();
			
			PreparedStatement psInsert = connection.prepareStatement(ConnectionDB.INSERT_INTO_RUBRICA);
			
			for(int i=0; i<rubrica.size();i++)
			{
				
				psInsert.setString(1, rubrica.get(i).getCognome());
				psInsert.setString(2, rubrica.get(i).getNome());
				psInsert.setString(3, rubrica.get(i).getTelefono());
				psInsert.setString(4, rubrica.get(i).getEmail());
				psInsert.setString(5, rubrica.get(i).getNote());
				
				int r = psInsert.executeUpdate();
				System.out.println("Inserimento eseguito? r = " + r);
			}
				
			
		} catch (Exception e) {
			
			e.printStackTrace();
			throw e;
			
		} finally {
			
			try {
				
				statement.close();
				connection.close();
				
			} catch (Exception fEx) {
				fEx.printStackTrace();
			}
		}
	
		
	}
	
	public static List<Contatto> searchDuplicateDB() throws Exception {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		List<Contatto> rubrica = new ArrayList<Contatto>();
		
		Contatto trovato;
		
		Connection connection = null;
		ResultSet rs = null;
		
		try {
			
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rubrica?serverTimezone=CET", "root", "ardente");
			
			trovato = new Contatto();
			
			System.out.println("Stato connessione "+ !connection.isClosed());
			
			PreparedStatement preparedStatement = connection.prepareStatement(ConnectionDB.DUPLICATE_BY_NUMBER);
			
			rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				
				trovato = new Contatto();
				trovato.setId(rs.getInt("id"));
				trovato.setCognome(rs.getString("cognome"));
				trovato.setNome(rs.getString("nome"));
				trovato.setTelefono(rs.getString("telefono"));
				trovato.setEmail(rs.getString("email"));
				trovato.setNote(rs.getString("note"));
				
				rubrica.add(trovato);

			}
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			throw e;
			
		} finally {
			
			try {
				
				rs.close();
				connection.close();
				
			} catch (Exception fEx) {
				fEx.printStackTrace();
			}
		}
		
		return rubrica;
	}
	
}
