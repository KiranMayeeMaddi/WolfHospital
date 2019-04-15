/**
 * 
 */
package org.wolf.config;

/**
 * Variables class helps to load MariaDB Connector and allow it to be used as jdbc driver
 * Helps to set user name and password
 */
public class Variables {
	
	private final String url = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/";
	private final String user = "";
	private final String password = "";
	
	public String getUrl() {
		return url;
	}
	public String getUser() {
		return user;
	}
	public String getPassword() {
		return password;
	}

}
