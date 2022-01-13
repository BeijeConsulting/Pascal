package it.beije.pascal.rubrica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/*
//SELECT
rs = statement.executeQuery("SELECT * FROM contatti WHERE cognome = '" + "rossi" + "'");

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
//System.out.println("r = " + r);

//UPDATE
//int r = statement.executeUpdate("UPDATE contatti SET cognome = 'rossi' WHERE cognome = 'rosa'");
//System.out.println("r = " + r);

//DELETE
//int r = statement.executeUpdate("DELETE FROM contatti WHERE cognome = 'verdi'");
//System.out.println("r = " + r);


*/
public class DBConnection {
	public static final String INSERT_INTO_RUBRICA = "INSERT INTO contatti (cognome, nome, telefono, email, note) VALUES (?,?,?,?,?)";
	public static final String SELECT_DUPLICATES = "SELECT * FROM contatti C1, contatti C2"
			+ " WHERE C1.nome = C2.nome AND C1.cognome = C2.cognome;  ";
	public static final String DELETE_CONTACT = "DELETE FROM contatti WHERE (id = ?)";
	 Connection connection = null;
	 Statement statement = null;
	 ResultSet rs = null;
	
	public void Connection() throws Exception{
	Class.forName("com.mysql.cj.jdbc.Driver");
	
	
	try {
		connection = DriverManager
				.getConnection("jdbc:mysql://localhost:3306/rubrica?serverTimezone=CET", "root", "Soos1234");

		//System.out.println(!connection.isClosed());
		
		statement = connection.createStatement();

		} catch (Exception e) {
		e.printStackTrace();
		throw e;
			}
	}
	
	public void Insert(Contatto c) {
		PreparedStatement psInsert = null;
		try {
			psInsert = connection.prepareStatement(INSERT_INTO_RUBRICA);
			psInsert.setString(1,  c.getCognome());
			psInsert.setString(2, c.getNome());
			psInsert.setString(3,c.getTelefono());
			psInsert.setString(4, c.getEmail());
			psInsert.setString(5, c.getNote());
			int r = psInsert.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Insert Soos");
			e.printStackTrace();
		}
	}
	
	public List<Contatto> selectAll() throws SQLException{
		List<Contatto> listcont = new ArrayList<Contatto>();
		//Contatto c= new Contatto();
		rs = statement.executeQuery("SELECT * FROM contatti");
		while (rs.next()) {
			 Contatto c= new Contatto();
			 c.setId(rs.getInt("id"));
			 c.setCognome(rs.getString("cognome"));
			 c.setNome( rs.getString("nome"));
			 c.setTelefono( rs.getString("telefono"));
			 c.setEmail(rs.getString("email"));
			 c.setNote( rs.getString("note"));
			 listcont.add(c);
		}
		return listcont;
		
	}
	
	public List<Contatto> findDuplicateContatti(List<Contatto> listcont) {
		List<Contatto> duplicatecont = new ArrayList<Contatto>();
		//int cont = 0;
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DUPLICATES);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Contatto dupc = new Contatto();
				dupc.setId(rs.getInt("id"));
				dupc.setCognome(rs.getString("cognome"));
				dupc.setNome( rs.getString("nome"));
				dupc.setTelefono( rs.getString("telefono"));
				dupc.setEmail(rs.getString("email"));
				dupc.setNote( rs.getString("note"));
				duplicatecont.add(dupc);
			}
		} catch (SQLException e) {
			System.out.println("DBConnection SOoos");
			e.printStackTrace();
		}
		
		if(duplicatecont.isEmpty()) return duplicatecont;
		else return duplicatecont;
 	}
	
	public void removeContatto(List<Contatto> listcont, String cognome) {	
		boolean exists = XMLCSVmanager.isContatto(listcont, cognome);
		Contatto cont = XMLCSVmanager.searchContatto(listcont, cognome);
		if(exists) {
			listcont.remove(cognome);
			PreparedStatement preparedStatement;
			try {
				preparedStatement = connection.prepareStatement(DELETE_CONTACT);
				preparedStatement.setInt(1, Integer.parseInt(cont.getId()));
				rs = preparedStatement.executeQuery();
				
			} catch (SQLException e) {
				System.out.println("Elimina Sooos");
				e.printStackTrace();
			}


			
		}

	}
	
}

