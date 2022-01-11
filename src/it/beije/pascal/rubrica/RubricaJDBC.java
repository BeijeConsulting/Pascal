package it.beije.pascal.rubrica;

import java.sql.CallableStatement;
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
	public static final String UPDATE_WHERE_ID = "UPDATE contatti SET cognome = ?, nome = ?, telefono = ?, email = ?, note = ? WHERE id = ?";

	Connection connection;

	public RubricaJDBC() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connection = null;

	}

	public static void main(String[] args) throws Exception {
		Contatto contatto = new Contatto();
		contatto = RubricaCSV.loadRubricaFromCSV().get(0);
		RubricaJDBC rubrica = new RubricaJDBC();
		//		rubrica.inserisciContatto(contatto );
		//		rubrica.modificaContatto(1, new String[]{"nome", "cognome"}, new String[]{"Andrea" ,"Rossi"});
		//rubrica.cercaContatto("mario", "rossi");
	}

	public List<Contatto> cercaContatto(String nome, String cognome){
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rubrica?serverTimezone=CET", "root", "Lobbiani");
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_COGNOME_NOME);
			preparedStatement.setString(1, cognome);
			preparedStatement.setString(2, nome);
	
			ResultSet rs = preparedStatement.executeQuery();
	
			List<Contatto> risultati = new ArrayList<>();
			Contatto contatto = new Contatto();
			while (rs.next()) {
				contatto.setId(rs.getInt("id"));
				contatto.setCognome(rs.getString("cognome"));
				contatto.setNome( rs.getString("nome"));
				contatto.setTelefono( rs.getString("telefono"));
				contatto.setEmail( rs.getString("email"));
				contatto.setNote( rs.getString("note"));
				risultati.add(contatto);
	
				//control
				System.out.println("from database : "+ contatto.toString());
			}
			return risultati;
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if (!connection.isClosed()) {
					connection.close();
				}
			} catch (SQLException e) {
				System.out.println("errore nella chiusura della connessione");
				e.printStackTrace();
			}
		}
		return null;
	}

	public void inserisciContatto(Contatto contatto) {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rubrica?serverTimezone=CET", "root", "Lobbiani");
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_RUBRICA);
			preparedStatement.setString(1, contatto.getCognome());
			preparedStatement.setString(2, contatto.getNome());
			preparedStatement.setString(3, contatto.getTelefono());
			preparedStatement.setString(4, contatto.getEmail());
			preparedStatement.setString(5, contatto.getNote());

			int result = preparedStatement.executeUpdate();
			System.out.println("risultato dell'inserimento " + result);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if (!connection.isClosed()) {
					connection.close();
				}
			} catch (SQLException e) {
				System.out.println("errore nella chiusura della connessione");
				e.printStackTrace();
			}
		}
	}

	//	public void inserisciContatto(Contatto contatto) {
	//		String query = "INSERT INTO contatti (nome, cognome, telefono, email, note) VALUES (" 
	//				+"'"+ contatto.getNome() 	+"'"+ ", "  
	//				+"'"+ contatto.getCognome() +"'"+ ", " 
	//				+"'"+ contatto.getTelefono()+"'" + ", " 
	//				+"'"+ contatto.getEmail() 	+"'"+ ", " 
	//				+"'"+ contatto.getNote() 	+"'"+ ")";
	//
	//		executeStatement(query);
	//
	//	}

	/*
	public List<Contatto> cercaContatto(String[] campi, String[] parametri) {
		//TODO controlli su . lunghezza di contatti e campi uguale
		List<Contatto> risultati = new ArrayList<Contatto>();

		//build query
		StringBuilder queryBuilder = new StringBuilder().append("SELECT * ").append("FROM contatti WHERE ");
		for (int i = 0; i < campi.length; i++) {
			queryBuilder.append(campi[i] + " = '" + parametri[i] +"' AND ");
		}
		queryBuilder.delete(queryBuilder.lastIndexOf("AND"), queryBuilder.lastIndexOf("AND")+3);

		//		test
		System.out.println("query : "+queryBuilder.toString());
		//execute
		risultati = extractContattiFromQuery(queryBuilder.toString());
		return risultati;
	}
	 */

	public void modificaContatto(int id, Contatto contatto) {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rubrica?serverTimezone=CET", "root", "Lobbiani");
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_RUBRICA);
			preparedStatement.setString(1, contatto.getCognome());
			preparedStatement.setString(2, contatto.getNome());
			preparedStatement.setString(3, contatto.getTelefono());
			preparedStatement.setString(4, contatto.getEmail());
			preparedStatement.setString(5, contatto.getNote());
			preparedStatement.setInt(6, contatto.getId());

			int result = preparedStatement.executeUpdate();
			System.out.println("risultato della modifica " + result);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if (!connection.isClosed()) {
					connection.close();
				}
			} catch (SQLException e) {
				System.out.println("errore nella chiusura della connessione");
				e.printStackTrace();
			}
		}
	}
	
	public void modificaContatto(int id, String[]campi, String[] parametri) {

		//build the query statement
		StringBuilder queryBuilder = new StringBuilder().append("UPDATE contatti SET  ");
		for (int i = 0; i < campi.length; i++) {
			queryBuilder.append(campi[i] + " = '" + parametri[i] +"',");
		}
		queryBuilder.deleteCharAt(queryBuilder.lastIndexOf(","));
		queryBuilder.append(" WHERE id = " + id);

		//execute
		executeStatement(queryBuilder.toString());

	}

	private void executeStatement(String query) {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rubrica?serverTimezone=CET", "root", "Lobbiani");
			Statement statement = connection.createStatement();

			boolean rs = statement.execute(query);
			System.out.println("risultato della query di inserimento: " + rs);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if (!connection.isClosed()) {
					connection.close();
				}
			} catch (SQLException e) {
				System.out.println("errore nella chiusura della connessione");
				e.printStackTrace();
			}
		}
	}

	private ResultSet executeQuery(String query) throws Exception {
		Statement statement = null;
		ResultSet rs = null;

		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rubrica?serverTimezone=CET", "root", "Lobbiani");

			System.out.println(!connection.isClosed());

			statement = connection.createStatement();
			rs = statement.executeQuery(query);

			return rs;
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


