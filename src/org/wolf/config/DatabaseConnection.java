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
	static Connection conn = null;
	
	private DatabaseConnection() {
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
	}
	
    public static Connection getConnection() throws SQLException {
    	if(conn==null) {
    		new DatabaseConnection();
    	}
    	
        return conn;
    }
}
