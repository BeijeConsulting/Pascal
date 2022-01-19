package it.beije.pascal.rubrica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class RubricaJDBC implements DatabaseConnection {

	public static final String SELECT_COGNOME_NOME = "SELECT * FROM contatti WHERE cognome = ? AND nome = ?";
	public static final String SELECT_ALL_PARAMETERS = "SELECT * FROM contatti WHERE cognome = ? AND nome = ? AND telefono = ? AND email = ? AND note = ?";
	public static final String INSERT_INTO_RUBRICA = "INSERT INTO contatti (cognome, nome, telefono, email, note) VALUES (?,?,?,?,?)";
	public static final String UPDATE_WHERE_ID = "UPDATE contatti SET cognome = ?, nome = ?, telefono = ?, email = ?, note = ? WHERE id = ?";
	public static final String DELETE_WHERE_ID = "DELETE FROM contatti WHERE id = ?";
	public static final String SELECT_ORDERBY = "SELECT * FROM contatti ORDER BY %s %s";
	public static final String SELECT_DUPLICATES = "SELECT * FROM  contatti GROUP BY nome, cognome, telefono, email, note HAVING COUNT(id) > 1";
	

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
		RubricaJDBC rubrica = new RubricaJDBC();
		rubrica.listDuplicates();
		//		rubrica.inserisciContatto(contatto );
		//		rubrica.modificaContatto(1, new String[]{"nome", "cognome"}, new String[]{"Andrea" ,"Rossi"});
		//rubrica.cercaContatto("mario", "rossi");
	}

	@Override
	public List<Contatto> cercaContattoNomeCognome(String nome, String cognome){
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rubrica?serverTimezone=CET", "root", "Lobbiani");
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_COGNOME_NOME);
			preparedStatement.setString(1, cognome);
			preparedStatement.setString(2, nome);
	
			ResultSet rs = preparedStatement.executeQuery();
	
			return extractContattiFromResultSet(rs);
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
	
	@Override
	public List<Contatto> cercaContatto(Contatto contatto){
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rubrica?serverTimezone=CET", "root", "Lobbiani");
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PARAMETERS);
			preparedStatement.setString(1, contatto.getCognome());
			preparedStatement.setString(2, contatto.getNome());
			preparedStatement.setString(3, contatto.getTelefono());
			preparedStatement.setString(4, contatto.getEmail());
			preparedStatement.setString(5, contatto.getNote());
	
			ResultSet rs = preparedStatement.executeQuery();
	
			return extractContattiFromResultSet(rs);
	
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

	private List<Contatto> extractContattiFromResultSet(ResultSet rs) throws SQLException {
		Contatto contatto;
		List<Contatto> risultati = new ArrayList<>();
		
		contatto= new Contatto();
		while (rs.next()) {
			contatto= new Contatto();
			contatto.setId(rs.getInt("id"));
			contatto.setCognome(rs.getString("cognome"));
			contatto.setNome( rs.getString("nome"));
			contatto.setTelefono( rs.getString("telefono"));
			contatto.setEmail( rs.getString("email"));
			contatto.setNote( rs.getString("note"));
			risultati.add(contatto);
		}
		return risultati;
	}

	@Override
	public void inserisciContatto(Contatto contatto) {
		try {
			//test
			System.out.println("inserisco contattoin db: " + contatto.toString());
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

	@Override
	public void modificaContatto(int id, Contatto contatto) {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rubrica?serverTimezone=CET", "root", "Lobbiani");
			PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_WHERE_ID);
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
	
	@Override
	public void eliminaContatto(int id) {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rubrica?serverTimezone=CET", "root", "Lobbiani");
			PreparedStatement preparedStatement = connection.prepareStatement(DELETE_WHERE_ID);
			preparedStatement.setInt(1, id);
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
	
	public List<Contatto> listAllOrderedBy(String byWhat, boolean asc){
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rubrica?serverTimezone=CET", "root", "Lobbiani");
			Statement statement = connection.createStatement();
			String sql = String.format(SELECT_ORDERBY, byWhat, asc?"ASC":"DESC");
	
			ResultSet rs = statement.executeQuery(sql);
			return extractContattiFromResultSet(rs);
	
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
	
	public List<Contatto> listDuplicates(){
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rubrica?serverTimezone=CET", "root", "Lobbiani");
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(SELECT_DUPLICATES);
			
			return extractContattiFromResultSet(rs);
	
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
	
	
	//Old methods

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

	@Override
	public void autoDeleteDuplicati() {
		// TODO Auto-generated method stub
		
	}
}


