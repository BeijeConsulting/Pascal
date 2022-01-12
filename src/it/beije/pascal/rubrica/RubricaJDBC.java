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
	
	public static void main(String[] args) throws Exception {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rubrica?serverTimezone=CET", "root", "beije");

			System.out.println(!connection.isClosed());
			
			statement = connection.createStatement();
			
			/*SELECT
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
			*/
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
}
