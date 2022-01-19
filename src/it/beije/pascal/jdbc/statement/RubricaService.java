package it.beije.pascal.jdbc.statement;

import java.sql.Connection;
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
		String sql = "INSERT INTO contatti VALUES(null,'" + contatto.getCognome() + "','" + contatto.getNome() + "', '"
				+ contatto.getTelefono() + "', '" + contatto.getEmail() + "', '" + contatto.getNote() + "')";
		System.err.println(sql);
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
			int i = 0;

			statement = connection.createStatement();

			for (Contatto contatto : contatti) {
				String sql2 = "INSERT INTO contatti \nVALUES(null,'" + contatto.getCognome() + "','"
						+ contatto.getNome() + "', '" + contatto.getTelefono() + "', '" + contatto.getEmail() + "', '"
						+ contatto.getNote() + "')";
				statement.executeUpdate(sql2);
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
		System.err.println(sql);
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
		String sql = "SELECT * FROM contatti \nWHERE  cognome = '" + contatto.getCognome() + "' AND nome = '"
				+ contatto.getNome() + "' AND telefono = '" + contatto.getTelefono() + "' AND email = '"
				+ contatto.getEmail() + "' AND note = '" + contatto.getNote() + "'";
		System.err.println(sql);
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

	public static void deleteContact(Contatto contatto) {
		String sql = "DELETE FROM contatti \nWHERE cognome = '" + contatto.getCognome() + "' AND nome = '"
				+ contatto.getNome() + "'" + " AND telefono = '" + contatto.getTelefono() + "' AND email = '"
				+ contatto.getEmail() + "'" + " AND note = '" + contatto.getNote() + "'";
		System.err.println(sql);
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

	public static void deleteAll() {
		String sql = "DELETE FROM contatti";
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

	public static void updateContact(Contatto contatto) {
		String sql = "UPDATE contatti"
				+ "\nSET nome ='modificato', cognome = 'modificato', telefono = 'modificato', email ='modificato', note = 'modificato'"
				+ " \nWHERE nome = '" + contatto.getNome() + "' AND cognome = '" + contatto.getCognome() + "'"
				+ "AND telefono = '" + contatto.getTelefono() + "'" + "AND  email = '" + contatto.getEmail()
				+ "' AND note = '" + contatto.getNote() + "'";
		System.err.println(sql);
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

	public static List<Contatto> findDuplicates(List<Contatto> contatti) {
		List<Contatto> contattiDuplicati = new ArrayList<Contatto>();
		contactOrder(contatti);
		int tmp = 0;
		for (int i = 0; i < contatti.size(); i++) {
			for (int j = i + 1; j < contatti.size(); j++) {
				// Overload metodo equals della classe contatto
				if (contatti.get(i).equals(contatti.get(j))) {
					tmp = j;
					System.out.println("tmp: " + tmp);
				}
			}
			if (tmp > 0) {
				contattiDuplicati.add(contatti.get(tmp));
				i = tmp + 1;
				tmp = 0;
			}
		}
		return contattiDuplicati;
	}

	public static void contactOrder(List<Contatto> contatti) {
		contatti.sort(new Comparator<Contatto>() {
			public int compare(Contatto c1, Contatto c2) {
				return c1.getNome().compareTo(c2.getNome());
			}

		});
	}

}
