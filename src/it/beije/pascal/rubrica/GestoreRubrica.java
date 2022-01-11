package it.beije.pascal.rubrica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestoreRubrica {
	public static final String INSERT_INTO_RUBRICA = "INSERT INTO contatti (cognome, nome, telefono, email, note) VALUES (?,?,?,?,?)";
	public static final String SELECT_CONTATTI = "SELECT * FROM contatti";
	public static final String SELECT_CONTATTO = "SELECT * FROM contatti WHERE cognome = ? AND nome = ? AND telefono = ? AND email = ? AND note = ?;";
	public static final String SELECT_ORDER_COGNOME = "SELECT * FROM rubrica.contatti ORDER BY cognome;";
	public static final String SELECT_ORDER_NOME = "SELECT * FROM rubrica.contatti ORDER BY nome;";
	
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
	
	public static List<Contatto> getRubricaFromDB(String select) throws Exception{
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
	
    public static List<Contatto> ordinaPerNome() throws Exception{
		List<Contatto> ordinaN = new ArrayList<>();
		
		ordinaN = getRubricaFromDB(GestoreRubrica.SELECT_ORDER_NOME);
		
		return ordinaN;
	}
	
    public static List<Contatto> ordinaPerCognome() throws Exception{
    	List<Contatto> ordinaC = new ArrayList<>();
		
    	ordinaC = getRubricaFromDB(GestoreRubrica.SELECT_ORDER_COGNOME);
		
    	return ordinaC;
	}
	
	public static Contatto cercaContatto(Contatto c) throws Exception {
		Connection connection = null;
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		
		Contatto contatto = new Contatto();
		
		try {
			connection = connectDB();
			preparedStatement = connection.prepareStatement(GestoreRubrica.SELECT_CONTATTO);
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
	
	public static void inserisciContatto(String cognome, String nome, String telefono, String email, String note) throws Exception {
		Connection connection = null;
		PreparedStatement psInsert = null;
		
		try {
			connection = connectDB();
			psInsert = connection.prepareStatement(GestoreRubrica.INSERT_INTO_RUBRICA);
			psInsert.setString(1, cognome);
			psInsert.setString(2, nome);
			psInsert.setString(3, telefono);
			psInsert.setString(4, email);
			psInsert.setString(5, note);
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
	
	public static void modificaContatto(Contatto c) {
		
		
	}
	
	public static void cancellaContatto() {
		
	}
	
	public static void trovaContattiDup() {
		
	}
	
	public static void unisciContattiDup() {
		
	}
	
	public static void importCSVInDB() {
		
	}
	
	public static void importXMLInDB() {
		
	}
	
	public static void exportDBInCSV() {
		
	}
	
	public static void exportDBInXML() {
		
	}
	
	public static void stampaRubrica(List<Contatto> rubrica)
	{
		for(Contatto c : rubrica) {		
			System.out.println(c.toString());
		}
		
	}
	
	public static void stampaContatto(Contatto c) {
		System.out.println(c.toString());	
	}

	public static void main(String[] args) throws Exception {
		List<Contatto> rubrica = new ArrayList<Contatto>();
		String cognome;
		String nome;
		String telefono;
		String email;
		String note;
		Contatto appoggio = new Contatto();
		Scanner in = new Scanner(System.in);
		System.out.println("-Gestore Rubrica-");
		System.out.println("Menu:");
		System.out.println("1 -Vedi lista contatti ordinata per Nome");
		System.out.println("2 -Vedi lista contatti ordinata per Cognome");
		System.out.println("3 -Cerca Contatto");
		System.out.println("4 -Inserisci Contatto");
		System.out.println("5 -Modifica Contatto");
		System.out.println("6 -Cancella Contatto");
		System.out.println("7 -Trova Contatti Duplicati");
		System.out.println("8 -Unisci Contatti Duplicati");
		System.out.println("9 -Importa Rubrica da CSV a DB");
		System.out.println("10 -Importa Rubrica da XML a DB");
		System.out.println("11 -Esporta Rubrica da DB a CSV");
		System.out.println("12 -Esporta Rubrica da DB a XML");
		System.out.println("0 -Esci");
		System.out.print("Inserisci il numero della funzione da eseguire: ");
		String scelta = in.next();
		switch(scelta) {
		case "0":
			break;
		case "1":
			rubrica = ordinaPerNome();
			stampaRubrica(rubrica);
			break;
		case "2":
			rubrica = ordinaPerCognome();
			stampaRubrica(rubrica);
			break;
		case "3":
			System.out.println("Inserisci dati del contatto da cercare:");
			System.out.print("Cognome: ");
			appoggio.setCognome(in.next()); 
			System.out.print("Nome: ");
			appoggio.setNome(in.next());
			System.out.print("Telefono: ");
			appoggio.setTelefono(in.next());
			System.out.print("Email: ");
			appoggio.setEmail(in.next());
			System.out.print("Note: ");
			appoggio.setNote(in.next());
			stampaContatto(cercaContatto(appoggio));
			
			break;
		case "4":
			System.out.println("Inserisci dati del contatto da inserire:");
			System.out.print("Cognome: ");
			cognome = in.next();
			System.out.print("Nome: ");
			nome = in.next();
			System.out.print("Telefono: ");
			telefono = in.next();
			System.out.print("Email: ");
			email = in.next();
			System.out.print("Note: ");
			note = in.nextLine();
			inserisciContatto(cognome, nome, telefono, email, note);
			break;
		case "5":
			break;
		case "6":
			break;
		case "7":
			break;
		case "8":
			break;
		case "9":
			break;
		case "10":
			break;
		case "11":
			break;
		case "12":
			break;
		}
		in.close();
	}

}
