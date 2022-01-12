package it.beije.pascal.rubrica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RubricaJDBCManager {
	public static final String SELECT_COGNOME_NOME = "SELECT * FROM contatti WHERE cognome = ? AND nome = ?";
	public static final String INSERT_INTO_RUBRICA = "INSERT INTO contatti (cognome, nome, telefono, email, note) VALUES (?,?,?,?,?)";
	public static final String UPDATE_WHERE_ID = "UPDATE contatti SET cognome = ?, nome = ?, telefono = ?, email = ?, note = ? WHERE id = ?";
	public static final String SELECT_CONTATTI = "SELECT * FROM contatti";
	public static final String SELECT_CONTATTO = "SELECT * FROM contatti WHERE cognome = ? AND nome = ? AND telefono = ? AND email = ? AND note = ?;";
	public static final String SELECT_ORDER_COGNOME = "SELECT * FROM rubrica.contatti ORDER BY cognome;";
	public static final String SELECT_ORDER_NOME = "SELECT * FROM rubrica.contatti ORDER BY nome;";
	public static final String DELETE_CONTATTO = "DELETE FROM contatti WHERE cognome = ? AND nome = ? AND telefono = ? AND email = ? AND note = ?;";
	
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
	
	private static List<Contatto> getRubricaFromDB() throws Exception{
		List<Contatto> rows = new ArrayList<>();
		
		Connection connection = null;
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = connectDB();
			preparedStatement = connection.prepareStatement(SELECT_CONTATTI);
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
	
	public List<Contatto> contattiInDB() throws Exception{
		List<Contatto> contatti = new ArrayList<>();
		
		contatti = getRubricaFromDB();
		
		return contatti;
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
		PreparedStatement psUpdate = null;
		
		try {
			connection = connectDB();
			psUpdate = connection.prepareStatement(UPDATE_WHERE_ID);
			psUpdate.setString(1, c.getCognome());
			psUpdate.setString(2, c.getNome());
			psUpdate.setString(3, c.getTelefono());
			psUpdate.setString(4, c.getEmail());
			psUpdate.setString(5, c.getNote());
			psUpdate.setInt(6, Id);
			int r = psUpdate.executeUpdate();
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
	
	public void cancellaContatto(Contatto c) throws Exception {
		Connection connection = null;
		PreparedStatement psDelete = null;
		
		try {
			connection = connectDB();
			psDelete = connection.prepareStatement(DELETE_CONTATTO);
			psDelete.setString(1, c.getCognome());
			psDelete.setString(2, c.getNome());
			psDelete.setString(3, c.getTelefono());
			psDelete.setString(4, c.getEmail());
			psDelete.setString(5, c.getNote());
			int r = psDelete.executeUpdate();
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
	
	public void trovaContattiDup(Contatto c) {
		
	}
	
	public void unisciContattiDup() {
		
	}
	
}
