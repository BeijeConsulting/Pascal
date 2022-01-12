package it.beije.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DatabaseStressTest {
    
    public DatabaseStressTest(){}
    
    public static void main(String[] args) {
        DatabaseStressTest instance = new DatabaseStressTest();
        // instance.connectionStressTest();
        instance.testTimeout();
    }
    
    private void connectionStressTest(){
        int numConnections =0;
        List<Connection> connections = new ArrayList<>();
        while(true){
            try{
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rubrica?serverTimezone=CET", "root", "Lobbiani");
                connections.add(connection);
                numConnections++;
            }
            catch(SQLException s ){
                System.out.println("sql Exception at number: " + numConnections);
                for (Connection connection : connections) {
                    try {
                        if (connection != null)
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                break;
            }
        }
    }
    
    private void testTimeout(){
        LocalDateTime start = LocalDateTime.now();
        System.out.println("starting...");
        LocalDateTime end = null;
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rubrica?serverTimezone=CET", "root", "Lobbiani");
            System.out.println(connection.getMetaData().getURL());
            
            while(!(connection == null ||connection.isClosed() )){
                
            }
        }catch (SQLException e) {
            e.printStackTrace();
            end = LocalDateTime.now();
        }
        Duration time = Duration.between(start, end);
        System.out.println("time: " +  time);
    }
}

