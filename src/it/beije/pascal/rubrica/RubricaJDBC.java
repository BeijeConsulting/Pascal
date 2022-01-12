package it.beije.pascal.rubrica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class RubricaJDBC {
	
	public static final String SELECT_COGNOME_NOME = "SELECT * FROM contatti WHERE cognome = ? AND nome = ?";
	public static final String INSERT_INTO_RUBRICA = "INSERT INTO contatti (cognome, nome, telefono, email, note) VALUES (?,?,?,?,?)";
	public static final String UPDATE_WHERE_ID = "UPDATE contatti SET cognome = ?, nome = ?, telefono = ?, email = ?, note = ? WHERE id = ?";
	public static final String SELECT_CONTATTI = "SELECT * FROM contatti";
	public static final String SELECT_CONTATTO = "SELECT * FROM contatti WHERE cognome = ? AND nome = ? AND telefono = ? AND email = ? AND note = ?;";
	public static final String SELECT_ORDER_COGNOME = "SELECT * FROM rubrica.contatti ORDER BY cognome;";
	public static final String SELECT_ORDER_NOME = "SELECT * FROM rubrica.contatti ORDER BY nome;";
	
	private static Connection connectDB() throws Exception {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rubrica?serverTimezone=CET", "root", "rosario");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} 
		
		return connection;	
	}
	
	private static List<Contatto> getRubricaFromDB(String select) throws Exception{
		List<Contatto> rows = new ArrayList<>();
		
		Connection connection = null;
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = connectDB();
			preparedStatement = connection.prepareStatement(select);
			rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				Contatto appoggio = new Contatto();
				appoggio.setId(rs.getInt("id"));
				appoggio.setNome(rs.getString("nome"));
				appoggio.setCognome(rs.getString("cognome"));
				appoggio.setTelefono(rs.getString("telefono"));
				appoggio.setEmail(rs.getString("email"));
				appoggio.setNote(rs.getString("note"));
				rows.add(appoggio);
			}
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			try {
				rs.close();
				connection.close();
			} catch (Exception fEx) {
				fEx.printStackTrace();
			}
		}
		return rows;
	}
	
	public List<Contatto> ordinaPerNome() throws Exception{
		List<Contatto> ordinaN = new ArrayList<>();
		
		ordinaN = getRubricaFromDB(SELECT_ORDER_NOME);
		
		return ordinaN;
	}
	
	public List<Contatto> ordinaPerCognome() throws Exception{
    	List<Contatto> ordinaC = new ArrayList<>();
		
    	ordinaC = getRubricaFromDB(SELECT_ORDER_COGNOME);
		
    	return ordinaC;
	}
	
	public Contatto cercaContatto(Contatto c) throws Exception {
		Connection connection = null;
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		
		Contatto contatto = new Contatto();
		
		try {
			connection = connectDB();
			preparedStatement = connection.prepareStatement(SELECT_CONTATTO);
			preparedStatement.setString(1, c.getCognome());
			preparedStatement.setString(2, c.getNome());
			preparedStatement.setString(3, c.getTelefono());
			preparedStatement.setString(4, c.getEmail());
			preparedStatement.setString(5, c.getNote());
			rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				contatto.setId(rs.getInt("id"));
				contatto.setNome(rs.getString("nome"));
				contatto.setCognome(rs.getString("cognome"));
				contatto.setTelefono(rs.getString("telefono"));
				contatto.setEmail(rs.getString("email"));
				contatto.setNote(rs.getString("note"));
			}
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			try {
				rs.close();
				connection.close();
			} catch (Exception fEx) {
				fEx.printStackTrace();
			}
		}
		return contatto;
	}
	
	public void inserisciContatto(Contatto c) throws Exception {
		Connection connection = null;
		PreparedStatement psInsert = null;
		
		try {
			connection = connectDB();
			psInsert = connection.prepareStatement(INSERT_INTO_RUBRICA);
			psInsert.setString(1, c.getCognome());
			psInsert.setString(2, c.getNome());
			psInsert.setString(3, c.getTelefono());
			psInsert.setString(4, c.getEmail());
			psInsert.setString(5, c.getNote());
			int r = psInsert.executeUpdate();
			System.out.println("r = " + r);
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			try {
				connection.close();
			} catch (Exception fEx) {
				fEx.printStackTrace();
			}
		}
	}
	
	public void modificaContatto(int Id, Contatto c) throws Exception {
		Connection connection = null;
		PreparedStatement psInsert = null;
		
		try {
			connection = connectDB();
			psInsert = connection.prepareStatement(UPDATE_WHERE_ID);
			psInsert.setString(1, c.getCognome());
			psInsert.setString(2, c.getNome());
			psInsert.setString(3, c.getTelefono());
			psInsert.setString(4, c.getEmail());
			psInsert.setString(5, c.getNote());
			psInsert.setInt(6, Id);
			int r = psInsert.executeUpdate();
			System.out.println("r = " + r);
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			try {
				connection.close();
			} catch (Exception fEx) {
				fEx.printStackTrace();
			}
		}
	}
	
	public static void cancellaContatto() {
		
	}
	
	public static void trovaContattiDup() {
		
	}
	
	public static void unisciContattiDup() {
		
	}
	
	/*
	public static void main(String[] args) throws Exception {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rubrica?serverTimezone=CET", "root", "beije");

			System.out.println(!connection.isClosed());
			
			statement = connection.createStatement();
			
			/SELECT
			//rs = statement.executeQuery("SELECT * FROM contatti WHERE cognome = '" + args[0] + "'");
			PreparedStatement preparedStatement = connection.prepareStatement(RubricaJDBC.SELECT_COGNOME_NOME);
			preparedStatement.setString(1, args[0]);
			preparedStatement.setString(2, args[1]);
			rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				System.out.println("id : " + rs.getInt("id"));
				System.out.println("cognome : " + rs.getString("cognome"));
				System.out.println("nome : " + rs.getString("nome"));
				System.out.println("telefono : " + rs.getString("telefono"));
				System.out.println("email : " + rs.getString("email"));
				System.out.println("note : " + rs.getString("note"));
				System.out.println("\n");
			}
			
			preparedStatement.setString(1, "bianchi");
			preparedStatement.setString(2, "mario");
			rs = preparedStatement.executeQuery();
			
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
			//int r = statement.executeUpdate("INSERT INTO contatti VALUES (null, 'Verdi', 'Mauro', '3474646467', 'verdi.mauro@beije.it', 'sono un nuovo contatto')");
			PreparedStatement psInsert = connection.prepareStatement(INSERT_INTO_RUBRICA);
			psInsert.setString(1, "Verdi");
			psInsert.setString(2, "Luisa");
			psInsert.setString(3, "24341412");
			psInsert.setString(4, null);
			psInsert.setString(5, "");
			int r = psInsert.executeUpdate();
			System.out.println("r = " + r);
			
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
	*/

}
