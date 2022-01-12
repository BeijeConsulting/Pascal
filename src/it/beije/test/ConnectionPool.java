package it.beije.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionPool {

    public static final int MAX_CONNECTIONS = 10;

    private static ConnectionPool instance;

    private Connection[] connections;

    private ConnectionPool() {
        connections = new Connection[MAX_CONNECTIONS];
    }

    public static ConnectionPool getInstance() {
        if (instance == null)
            instance = new ConnectionPool();
        return instance;
    }

    public static void main(String[] args) {
        // just for testing
        try {
            Connection newConnection;
            while((newConnection = ConnectionPool.getInstance().getConnectionIfAvailable()) !=null){
                System.out.println(newConnection.createStatement().executeQuery("select * from contatti").getMetaData().getColumnCount());
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 
     * @return an open Connection or null if there are none available
     */
    public Connection getConnectionIfAvailable() {

        for (int i = 0; i < connections.length; i++) {
            try {
                if (connections[i] == null || connections[i].isClosed()) {
                    connections[i] = DriverManager.getConnection("jdbc:mysql://localhost:3306/rubrica?serverTimezone=CET", "root", "Lobbiani");
                    return connections[i];
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
        return null;
    }
    // maybe object time and connection

}
