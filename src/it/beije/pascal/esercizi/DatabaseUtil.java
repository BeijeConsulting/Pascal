package it.beije.pascal.esercizi;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.beije.pascal.rubrica.Contatto;

public class DatabaseUtil {
	
	public static void leggiDatabase() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rubrica?serverTimezone=CET", "root", "andrea23596");
		
		statement = connection.createStatement();
		//SELECT
		rs = statement.executeQuery("select * from contatti");
		
		while(rs.next()) {
			System.out.println("id : " + rs.getInt("idcontatti"));
			System.out.println("cognome : " + rs.getString("cognome"));
			System.out.println("nome : " + rs.getString("nome"));
			System.out.println("telefono : " + rs.getString("telefono"));
			System.out.println("email : " + rs.getString("email"));
			System.out.println("note : " + rs.getString("note"));
			System.out.println("\n");
		}
	}
	
	public static void scriviDatabase(List<Contatto> contatti) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		
		try {
			//creo una connessione con il mio db
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rubrica?serverTimezone=CET", "root", "andrea23596");

			System.out.println(!connection.isClosed());
			//creo una statement da usare per i suoi metodi
			statement = connection.createStatement();
			
			//INSERT
			int r = 0;
			for(Contatto c : contatti) {
				statement.executeUpdate("INSERT INTO contatti  VALUES ('" + c.getId() + "', '" + c.getCognome() + "', '" + c.getNome() + "', '" + c.getTelefono() + "', '" + c.getEmail() + "', '" + c.getNote() + "')");
				r++;
			}
			
			System.out.println("contatti aggiunti = " + r);
			
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
	
	public static void eliminaContattiPerCognome(String cognome) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		
		try {
			//creo una connessione con il mio db
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rubrica?serverTimezone=CET", "root", "andrea23596");

			System.out.println(!connection.isClosed());
			//creo una statement da usare per i suoi metodi
			statement = connection.createStatement();

			//DELETE
			int r = statement.executeUpdate("DELETE FROM contatti WHERE cognome = '" + cognome + "'");
			System.out.println("contatti eliminati = " + r);
			
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
	
	public static void modificaNomeContatto(String nome) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		
		try {
			//creo una connessione con il mio db
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rubrica?serverTimezone=CET", "root", "andrea23596");

			System.out.println(!connection.isClosed());
			//creo una statement da usare per i suoi metodi
			statement = connection.createStatement();
			
			//UPDATE
			int r = statement.executeUpdate("UPDATE contatti SET nome = '" + nome + "' WHERE nome = 'andrea'");
			System.out.println("contatti modificati = " + r);
			
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
	
	public static void esportaCSV() throws ClassNotFoundException, SQLException, FileNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		PrintWriter pw = null;
		
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rubrica?serverTimezone=CET", "root", "andrea23596");
		
		statement = connection.createStatement();
		
		//SELECT
		rs = statement.executeQuery("select * from contatti");
		pw = new PrintWriter(new FileOutputStream("rubrica.csv"));
		while(rs.next()) {
			pw.print(rs.getString("cognome") + ";");
			pw.print(rs.getString("nome") + ";");
			pw.print(rs.getString("telefono") + ";");
			pw.print(rs.getString("email") + ";");
			pw.print(rs.getString("note"));
			pw.println();
		}
		pw.close();
		System.out.println("File sovrascritto");
	}

	public static void main(String[] args) throws Exception {
		
//		List<Contatto> contatti = new ArrayList<>();
//		contatti = new IOBeanUtil().loadRubricaFromCSV("rubrica.csv", "\t");
//		
//		scriviDatabase(contatti);
		
//		leggiDatabase();
		
//		eliminaContattiPerCognome("rossi");
		
//		modificaNomeContatto("Alessia");
		
		esportaCSV();

	}

}
