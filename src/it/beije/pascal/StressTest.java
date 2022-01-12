package it.beije.pascal;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;

public class StressTest {
	public static void main(String[] args) throws SQLException {
		
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		
		Connection con = null;
		for (int i = 0; i < 10; i++) {
			con = connectionPool.openConnection();
		}

		Connection conFail = connectionPool.openConnection();
		connectionPool.closeConnection(con);
		
	    Connection conNew = connectionPool.openConnection();
	
	}
}
