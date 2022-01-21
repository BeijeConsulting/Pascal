package it.beije.pascal.jdbc.esercizi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.beije.pascal.jdbc.util.DBUtil;
import it.beije.pascal.jdbc.util.DataSource;
import it.beije.pascal.jpa.rubrica.bean.Contatto;

public class LimiteConnessioni {
	private static final String URL = "jdbc:mysql://localhost:3306/rubrica?serverTimezone=CET";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "admin";

	public static void main(String[] args) {

//		boolean value = true;
//		try {
//			Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//			getContactsList(connection);
//
//			while (value) {
//
//				if (connection.isClosed()) {
//					System.out.println("Chiusa");
//					break;
//				} else {
//					DriverManager.getConnection(URL, USERNAME, PASSWORD);
//					DriverManager.getConnection(URL, USERNAME, PASSWORD);
//					DriverManager.getConnection(URL, USERNAME, PASSWORD);
//				}
//
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

		limiteConnessioni();
	}

	private static void limiteConnessioni() {
		int i = 0;
		boolean value = true;

		try {
			while (value) {
				Connection connection1 = DataSource.getInstance().getConnection();
				DataSource.getInstance().getConnection();
				i++;
				DataSource.getInstance().getConnection();
				i++;
				DataSource.getInstance().getConnection();
				i++;
				DataSource.getInstance().getConnection();
				i++;

			}
		} catch (Exception e) {
			value = false;
			System.out.println(i);
			e.printStackTrace();
		}
	}

	public static List<Contatto> getContactsList(Connection connection) {
		String sql = "SELECT * FROM contatti";
		System.err.println(sql);
		List<Contatto> contatti = new ArrayList<Contatto>();
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
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
		}
		return contatti;
	}

}
