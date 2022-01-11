package it.beije.pascal.rubrica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;






public class PrepstateConnection {
	
	public static final String SELECT_ALL = "SELECT * FROM contatti ";
	public static final String INSERT_INTO_RUBRICA = "INSERT INTO contatti (cognome, nome, telefono, email, note) VALUES (?,?,?,?,?)";
	
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
	
	public void Insert(Contatto c){
		/*
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
		}*/
		
		PreparedStatement psInsert = null;
		try {
			psInsert = connection.prepareStatement(INSERT_INTO_RUBRICA);
			psInsert.setString(1, "'" + c.getCognome()+ "'");
			psInsert.setString(2, "'" +c.getNome()+ "'");
			psInsert.setString(3,"'" + c.getTelefono()+ "'");
			psInsert.setString(4, "'" +c.getEmail()+ "'");
			psInsert.setString(5, "'" +c.getNote()+ "'");
			int r = psInsert.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
	}
	
	public List<Contatto> selectAll() throws SQLException{
		/*
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
*/
		List<Contatto> listcont = new ArrayList<Contatto>();
		Contatto c= new Contatto();
		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);
		rs = preparedStatement.executeQuery();
		
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
