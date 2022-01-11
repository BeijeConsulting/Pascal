package it.beije.pascal.rubrica;

import java.sql.Connection;
import java.sql.DriverManager;
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
	
	 Connection connection = null;
	 Statement statement = null;
	 ResultSet rs = null;
	
	public void Connection() throws Exception{
	Class.forName("com.mysql.cj.jdbc.Driver");
	
	
	try {
		connection = DriverManager
				.getConnection("jdbc:mysql://localhost:3306/rubrica?serverTimezone=CET", "root", "Soos1234");

		System.out.println(!connection.isClosed());
		
		statement = connection.createStatement();

		} catch (Exception e) {
		e.printStackTrace();
		throw e;
			}
	}
	
	public void Insert(Contatto contatto) {
		try {
			int r = statement.
					executeUpdate("INSERT INTO contatti VALUES (null, " 
			+ "'"+contatto.getCognome()+"'" + ", " 
							+ "'"+contatto.getNome()+"'" + ", " 
			+ "'"+contatto.getTelefono()+"'" +", " 
							+ "'"+ contatto.getEmail()+"'" + ", " 
			+ "'"+contatto.getNote()+"'" + " )");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Contatto> selectAll() throws SQLException{
		List<Contatto> listcont = new ArrayList<Contatto>();
		Contatto c= new Contatto();
		rs = statement.executeQuery("SELECT * FROM contatti");
		while (rs.next()) {
			 rs.getInt("id");
			 c.setCognome(rs.getString("cognome"));
			 c.setNome( rs.getString("nome"));
			 c.setTelefono( rs.getString("telefono"));
			 c.setEmail(rs.getString("email"));
			 c.setNote( rs.getString("note"));
			 listcont.add(c);
		}

		return listcont;
		
	}
	
}

