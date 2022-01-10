package it.beije.pascal.rubrica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class RubricaJDBC {

	public static void main(String[] args) throws Exception {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rubrica?serverTimezone=CET", "root", "Chinetti");

			System.out.println(!connection.isClosed());
			
			statement = connection.createStatement();
			rs = statement.executeQuery("SELECT * FROM rubrica.contatti");
			
			while (rs.next()) {
				System.out.println("id : " + rs.getInt("id"));
				System.out.println("cognome : " + rs.getString("cognome"));
				System.out.println("nome : " + rs.getString("nome"));
//				System.out.println("telefono : " + rs.getString(4));
//				System.out.println("email : " + rs.getString(5));
//				System.out.println("note : " + rs.getString(6));
				System.out.println("\n");
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
		
	}

}
