package it.beije.pascal.rubrica.JDBC;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.beije.pascal.rubrica.Contatto;
import it.beije.pascal.rubrica.util.DBUtil;

public class ContattoDAO {

	public static void addContact(Contatto contatto) {
		String sql = "INSERT INTO contatti VALUES(null,'" + contatto.getCognome() + "','" + contatto.getNome() + "', '"
				+ contatto.getTelefono() + "', '" + contatto.getEmail() + "', '" + contatto.getNote() + "')";
		System.out.println(sql);
		Connection connection = null;
		Statement statement = null;

		try {
			connection = DataSource.getInstance().getConnection();
			statement = connection.createStatement();
			statement.executeUpdate(sql);

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			DBUtil.close(statement);
			DBUtil.close(connection);
		}

	}

	public static void addContact(List<Contatto> contatti) {
		// System.out.println(sql);
		Connection connection = null;
		Statement statement = null;
		try {
			connection = DataSource.getInstance().getConnection();
			statement = connection.createStatement();
			for (Contatto contatto : contatti) {
				String sql = "INSERT INTO contatti VALUES(null,'" + contatto.getCognome() + "','" + contatto.getNome()
						+ "', '" + contatto.getTelefono() + "', '" + contatto.getEmail() + "', '" + contatto.getNote()
						+ "')";
				statement.executeUpdate(sql);
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			DBUtil.close(statement);
			DBUtil.close(connection);
		}

	}

	public static List<Contatto> getContactsList() {
		String sql = "SELECT * FROM contatti";
		System.out.println(sql);
		List<Contatto> contatti = new ArrayList<Contatto>();
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			connection = DataSource.getInstance().getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);

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
		} finally {
			DBUtil.close(resultSet);
			DBUtil.close(statement);
			DBUtil.close(connection);
		}

		return contatti;
	}

	public static Contatto findContact(Contatto contatto) {
		String sql = "SELECT * FROM contatti WHERE  cognome = '" + contatto.getCognome() + "' AND nome = '"
				+ contatto.getNome() + "' AND telefono = '" + contatto.getTelefono() + "' AND email = '"
				+ contatto.getEmail() + "' AND note = '" + contatto.getNote() + "'";
		System.out.println(sql);
		Contatto c = null;
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			connection = DataSource.getInstance().getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
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
		} finally {
			DBUtil.close(resultSet);
			DBUtil.close(statement);
			DBUtil.close(connection);
		}

		return c;
	}

	public static void updateContact(Contatto contatto) {
		String sql = "DELETE FROM contact WHEERE cognome = '\" + contatto.getCognome() + \"' AND nome = '\"\r\n"
				+ "				+ contatto.getNome() + \"' AND telefono = '\" + contatto.getTelefono() + \"' AND email = '\"\r\n"
				+ "				+ contatto.getEmail() + \"' AND note = '\" + contatto.getNote() + \"'\"";A
	}

}
