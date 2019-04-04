package org.wolf.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection  {
	
	/**
     * Get database connection
     *
     * @return a Connection object
     * @throws SQLException
     */
	
    public static Connection getConnection() throws SQLException {
        Connection conn = null;
        
        Variables var = new Variables();
 
        // assign db parameters
        String url = var.getUrl();
        String user = var.getUser();
        String password = var.getPassword();
        
        // create a connection to the database
        try {
        	conn = DriverManager.getConnection(url, user, password);
            if(conn != null) {
//                System.out.println("Success!");
            }
        } catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        
        return conn;
    }
}
