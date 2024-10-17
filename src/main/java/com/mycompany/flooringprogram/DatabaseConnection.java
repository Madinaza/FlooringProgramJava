package com.mycompany.flooringprogram;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnection {
      private static Connection connection = null; 

    public static Connection setCon() {
        if (connection == null) {
            try {
                
                String url = "jdbc:mysql://localhost:3306/flooring_mastery"; 
                String user = "root"; 
                String password = "Agzde.19"; 

                
                connection = DriverManager.getConnection(url, user, password);
                System.out.println("Connected to the database.");
            } catch (SQLException e) {
              
                System.err.println("Connection failed: " + e.getMessage());
                e.printStackTrace(); 
            }
        }
        return connection;
    }
    
}
