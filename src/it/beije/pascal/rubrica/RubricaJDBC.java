package it.beije.pascal.rubrica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class RubricaJDBC {
	
	public static final String SELECT_COGNOME_NOME = "SELECT * FROM contatti WHERE cognome = ? AND nome = ?";
	public static final String INSERT_INTO_RUBRICA = "INSERT INTO contatti (cognome, nome, telefono, email, note) VALUES (?,?,?,?,?)";

	public static void main(String[] args) throws Exception  {
		//dico alla JVM di usare questa classe
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		
		try {
			//dico di ottenere una connessione con un certo database
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rubrica?serverTimezone=CET", "root", "andrea23596");

			System.out.println(!connection.isClosed());
			//creo uno statement su cui ci sono molti metodi per fare le query
			statement = connection.createStatement();

			//SELECT
			//eseguo una query che mi rende una ResultSet che è un'insieme di dati in base alla query che ho fatto che devo ciclare 
			rs = statement.executeQuery("SELECT * FROM contatti WHERE cognome = 'gliori'");

			//SELECT
			//rs = statement.executeQuery("SELECT * FROM contatti WHERE cognome = '" + args[0] + "'");
			//uso la PreparedStatement per fare la query in modo più comodo
			PreparedStatement preparedStatement = connection.prepareStatement(RubricaJDBC.SELECT_COGNOME_NOME);
			preparedStatement.setString(1, args[0]);
			preparedStatement.setString(2, args[1]);
			rs = preparedStatement.executeQuery();

			
			while (rs.next()) {
				System.out.println("id : " + rs.getInt("idcontatti"));
				System.out.println("cognome : " + rs.getString("cognome"));
				System.out.println("nome : " + rs.getString("nome"));
				System.out.println("telefono : " + rs.getString("telefono"));
				System.out.println("email : " + rs.getString("email"));
				System.out.println("note : " + rs.getString("note"));
				System.out.println("\n");
			}
			

			//solito procedimento con le altre query usando l'opportuno linguaggio di mysql

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
//			PreparedStatement psInsert = connection.prepareStatement(INSERT_INTO_RUBRICA);
//			psInsert.setString(1, "Verdi");
//			psInsert.setString(2, "Luisa");
//			psInsert.setString(3, "24341412");
//			psInsert.setString(4, null);
//			psInsert.setString(5, "");
//			int r = psInsert.executeUpdate();
//			System.out.println("r = " + r);
			
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
			
			//è importante fare la close() soprattutto della Connection per evitare di terminare gli slot di connessioni libere
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
