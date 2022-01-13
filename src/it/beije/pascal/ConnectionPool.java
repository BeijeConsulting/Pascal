package it.beije.pascal;
//parser XML ,senza usare DocumentBuilder,  che generi i vari oggetti e noti i vari errorri(Chiusura sbagliata di campi, etc...
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPool {
	public final static int MAX_CONN = 10;
	static int currentConn = 0;
	static List<Connection> connections = new ArrayList<Connection>(MAX_CONN);
	static List<Connection> usedConnections = new ArrayList<Connection>(MAX_CONN);

	
	public static boolean isFull() {
		if(currentConn == MAX_CONN) return true;
		else return false;
	}
	
	public static void releaseConnection() {
		
	}
	
	public static void createNewConnection() {
		if(!isFull()) {
		try {
			Connection c = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/rubrica?serverTimezone=CET", "root", "Soos1234");
		} catch (SQLException e) {
			System.out.println("New Connection Sooos");
			e.printStackTrace();
		}
		}
	}
	
	public static void main(String[] args) throws ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		
		
	}

}
