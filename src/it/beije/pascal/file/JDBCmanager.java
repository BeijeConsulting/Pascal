package it.beije.pascal.file;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import it.beije.pascal.rubrica.Contatto;

public class JDBCmanager {
	public static final String SELECT_ALL = "SELECT * FROM contatti";
	public static final String INSERT_INTO_RUBRICA = "INSERT INTO contatti (cognome, nome, telefono, email, note) VALUES (?,?,?,?,?)";
	public static final String DELETE_FROM_RUBRICA = "DELETE FROM contatti WHERE cognome = ? AND nome = ? AND telefono = ? AND email = ? AND note = ?";

	public static Statement createStatement() {
		Statement statement = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/rubrica?serverTimezone=CET", "root", "Chinetti");
			statement = connection.createStatement();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		if (statement != null)
			return statement;
		else
			return null;

	}

	public static Connection createConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rubrica?serverTimezone=CET", "root",
					"Chinetti");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		if (connection != null)
			return connection;
		else
			return null;
	}

	
	public static List<Contatto> sortCategoriaJDBC(String categoria) throws SQLException, ClassNotFoundException {
		List<Contatto> trovatijdbc = new ArrayList<Contatto>();

		ResultSet rs = JDBCmanager.createStatement()
				.executeQuery("SELECT * FROM contatti ORDER BY " + categoria + " ASC");

		while (rs.next()) {
			trovatijdbc.add(new Contatto(rs.getString("cognome"), rs.getString("nome"), rs.getString("telefono"),
					rs.getString("email"), rs.getString("note")));
		}

		return trovatijdbc;
	} 
	
	public static List<Contatto> sortCategoriaJDBCPrepareStamtement(String categoria) throws SQLException, ClassNotFoundException {
		List<Contatto> trovatijdbc = new ArrayList<Contatto>();

		PreparedStatement pstmt = createConnection().prepareStatement(SELECT_ALL + " ORDER BY " + categoria + " ASC");
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			trovatijdbc.add(new Contatto(rs.getString("cognome"), rs.getString("nome"), rs.getString("telefono"),
					rs.getString("email"), rs.getString("note")));
		}

		return trovatijdbc;
	} 
	
	public static List<Contatto> findJDBC(String s, String categoria) throws Exception {
		List<Contatto> trovatijdbc = new ArrayList<Contatto>();
		Class<?> c = Class.forName("it.beije.pascal.rubrica.Contatto");

		ResultSet rs = JDBCmanager.createStatement()
				.executeQuery("SELECT * FROM contatti WHERE " + categoria + " = '" + s + "';");

		while (rs.next()) {
			trovatijdbc.add(new Contatto(rs.getString("cognome"), rs.getString("nome"), rs.getString("telefono"),
					rs.getString("email"), rs.getString("note")));
		}

		return trovatijdbc;
	}

	public static List<Contatto> findJDBCPrepareStamtement(String s, String categoria) throws Exception {
		PreparedStatement pstmt = createConnection().prepareStatement("SELECT * FROM contatti WHERE "+ categoria + " = ?");
		List<Contatto> trovatijdbc = new ArrayList<Contatto>();
		pstmt.setString(1, s);

		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			trovatijdbc.add(new Contatto(rs.getString("cognome"), rs.getString("nome"), rs.getString("telefono"),
					rs.getString("email"), rs.getString("note")));
		}
		
		return trovatijdbc;
	}

	public static void insertJDBC(Contatto c) throws Exception {
		int r = createStatement().executeUpdate("INSERT INTO contatti VALUES (null, '" + c.getCognome() + "', '"
				+ c.getNome() + "', '" + c.getTelefono() + "', '" + c.getEmail() + "', '" + c.getNote() + "')");
		if (r == 0)
			System.out.println("Non inserito");
	}

	public static void insertJDBCPrepareStamtement(Contatto c) throws Exception {
		PreparedStatement psInsert = createConnection().prepareStatement(INSERT_INTO_RUBRICA);
		psInsert.setString(1, c.getCognome());
		psInsert.setString(2, c.getNome());
		psInsert.setString(3, c.getTelefono());
		psInsert.setString(4, c.getEmail());
		psInsert.setString(5, c.getNote());
		int r = psInsert.executeUpdate();
		if (r == 0)
			System.out.println("Non inserito");
	}

	public static void updateContattoJDBC(int id, String categoria, String val) throws SQLException {		
		int r = createStatement().executeUpdate("UPDATE contatti SET "+ categoria + " = '" + val + "' WHERE id = " + id);
		
		if (r == 0)
			System.out.println("Non modificato");
	}

	public static void updateContattoJDBCPrepareStamtement(int id, String categoria, String val) throws SQLException {
		PreparedStatement psInsert = createConnection().prepareStatement("UPDATE contatti SET "+ categoria + " = ? WHERE id = ?");
		psInsert.setString(1, val);
		psInsert.setInt(2, id);
		int r = psInsert.executeUpdate();
		if (r == 0)
			System.out.println("Non inserito");
	}
	
	public static void deleteContattoJDBC(Contatto c) throws Exception {
		// JDBC
		int r = JDBCmanager.createStatement()
				.executeUpdate("DELETE FROM contatti WHERE cognome = '" + c.getCognome() + "' " + "AND nome = '"
						+ c.getNome() + "' AND telefono = '" + c.getTelefono() + "'" + "AND email = '" + c.getEmail()
						+ "'AND note = '" + c.getNote() + "'; ");

		if (r == 0)
			System.out.println("nessun contatto trovato JDBC");
	}

	public static void deleteContattoJDBCPrepareStamtement(Contatto c) throws Exception {
		// JDBC prepare statement
		PreparedStatement psInsert = JDBCmanager.createConnection().prepareStatement(DELETE_FROM_RUBRICA);
		psInsert.setString(1, c.getCognome());
		psInsert.setString(2, c.getNome());
		psInsert.setString(3, c.getTelefono());
		psInsert.setString(4, c.getEmail());
		psInsert.setString(5, c.getNote());
		int r2 = psInsert.executeUpdate();

		if (r2 == 0)
			System.out.println("nessun contatto trovato JDBC prepareStatement");
	}

	public static Set<Contatto> trovaContattiDuplicatiJDBC() throws Exception {
		ResultSet rs = JDBCmanager.createStatement().executeQuery(SELECT_ALL);
		List<Contatto> contatti = new ArrayList<Contatto>();
        Set<Contatto> items = new HashSet<>();
        
        while (rs.next()) {
        	contatti.add(new Contatto(rs.getString("cognome"), rs.getString("nome"), rs.getString("telefono"),
					rs.getString("email"), rs.getString("note")));
		}
        
        return contatti.stream()
            .filter(n -> !items.add(n))
            .collect(Collectors.toSet());		
	}
	
	public static Set<Contatto> unisciContattiDuplicatiJDBC() throws Exception {
		ResultSet rs = JDBCmanager.createStatement().executeQuery(SELECT_ALL);
		List<Contatto> contatti = new ArrayList<Contatto>();
        Set<Contatto> items = new HashSet<>();
        
        while (rs.next()) {
        	contatti.add(new Contatto(rs.getString("cognome"), rs.getString("nome"), rs.getString("telefono"),
					rs.getString("email"), rs.getString("note")));
		}
        
        return contatti.stream()
            .filter(n -> !items.add(n))
            .collect(Collectors.toSet());		
	}


}
