package it.beije.pascal.jdbc.rubrica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import it.beije.pascal.Contatto;
import it.beije.pascal.jdbc.util.DBUtil;
import it.beije.pascal.jdbc.util.DataSource;

public class RubricaService {

	public static void addContact(Contatto contatto) {
		String sql = "INSERT INTO contatti VALUES(null,?,?,?,?,?)";
		System.err.println(sql);
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DataSource.getInstance().getConnection();
			statement = connection.prepareStatement(sql);
			statement.setString(1, contatto.getCognome());
			statement.setString(2, contatto.getNome());
			statement.setString(3, contatto.getTelefono());
			statement.setString(4, contatto.getEmail());
			statement.setString(5, contatto.getNote());

			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} finally {
			DBUtil.close(statement);
			DBUtil.close(connection);
		}

	}

	public static void addContact(List<Contatto> contatti) {
		// System.out.println(sql);
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DataSource.getInstance().getConnection();
			String sql = "INSERT INTO contatti VALUES(null,?,?,?,?,?)";
			statement = connection.prepareStatement(sql);

			for (Contatto contatto : contatti) {	
				statement.setString(1, contatto.getCognome());
				statement.setString(2, contatto.getNome());
				statement.setString(3, contatto.getTelefono());
				statement.setString(4, contatto.getEmail());
				statement.setString(5, contatto.getNote());
				statement.executeUpdate();
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			
		} finally {
			DBUtil.close(statement);
			DBUtil.close(connection);
		}

	}

	public static List<Contatto> getContactsList() {
		String sql = "SELECT * FROM contatti";
		System.err.println(sql);
		List<Contatto> contatti = new ArrayList<Contatto>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DataSource.getInstance().getConnection();
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Contatto contatto = new Contatto();
				contatto.setId(resultSet.getInt("id"));
				contatto.setCognome(resultSet.getString("cognome"));
				contatto.setNome(resultSet.getString("nome"));
				contatto.setTelefono(resultSet.getString("telefono"));
				contatto.setEmail(resultSet.getString("email"));
				contatto.setNote(resultSet.getString("note"));
				contatti.add(contatto);
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} finally {
			DBUtil.close(resultSet);
			DBUtil.close(statement);
			DBUtil.close(connection);
		}

		return contatti;
	}

	public static Contatto findContact(Contatto contatto) {

		String sql = "SELECT * FROM contatti WHERE cognome = ? AND nome = ? AND telefono = ? AND email = ? AND note = ?";
		System.err.println(sql);
		Contatto c = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DataSource.getInstance().getConnection();
			statement = connection.prepareStatement(sql);
			statement.setString(1, contatto.getCognome());
			statement.setString(2, contatto.getNome());
			statement.setString(3, contatto.getTelefono());
			statement.setString(4, contatto.getEmail());
			statement.setString(5, contatto.getNote());

			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				c = new Contatto();
				c.setId(resultSet.getInt("id"));
				c.setCognome(resultSet.getString("cognome"));
				c.setNome(resultSet.getString("nome"));
				c.setTelefono(resultSet.getString("telefono"));
				c.setEmail(resultSet.getString("email"));
				c.setNote(resultSet.getString("note"));
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} finally {
			DBUtil.close(resultSet);
			DBUtil.close(statement);
			DBUtil.close(connection);
		}

		return c;
	}

	public static void deleteContact(Contatto contatto) {
		String sql = "DELETE FROM contatti WHERE cognome = ? AND nome = ? AND telefono = ? AND email = ? AND note = ?";
		System.err.println(sql);
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DataSource.getInstance().getConnection();
			statement = connection.prepareStatement(sql);
			statement.setString(1, contatto.getCognome());
			statement.setString(2, contatto.getNome());
			statement.setString(3, contatto.getTelefono());
			statement.setString(4, contatto.getEmail());
			statement.setString(5, contatto.getNote());
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} finally {
			DBUtil.close(statement);
			DBUtil.close(connection);
		}

	}

	public static void deleteAll() {
		String sql = "DELETE FROM contatti";
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DataSource.getInstance().getConnection();
			statement = connection.prepareStatement(sql);
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} finally {
			DBUtil.close(statement);
			DBUtil.close(connection);
		}

	}

	public static void updateContact(Contatto contatto) {
		String sql = "UPDATE contatti \nSET cognome = ?,nome = ?,telefono = ?, email = ?, note = ?"
				+ "\nWHERE cognome = ? AND nome = ? AND telefono = ? AND email = ? AND note = ?   ";
		System.err.println(sql);
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DataSource.getInstance().getConnection();
			statement = connection.prepareStatement(sql);
			statement.setString(1, "Modificato");
			statement.setString(2, "Modificato");
			statement.setString(3, "Modificato");
			statement.setString(4, "Modificato");
			statement.setString(5, "Modificato");
			statement.setString(6, contatto.getCognome());
			statement.setString(7, contatto.getNome());
			statement.setString(8, contatto.getTelefono());
			statement.setString(9, contatto.getEmail());
			statement.setString(10, contatto.getNote());
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} finally {
			DBUtil.close(statement);
			DBUtil.close(connection);
		}
	}
	
	public static void findDuplicates() {
		String sql = "SELECT cognome,nome,telefono,email,note,COUNT(telefono) "
				+ "FROM contatti";
	
		String sql2 = "GROUP BY ?,?,?,?,?,? "
				+ "HAVING COUNT(telefono) > 1";		
		
	}

}
