/**
 * 
 */
package org.wolf.config;

/**
 * Variables class helps to load MariaDB Connector and allow it to be used as jdbc driver
 * Helps to set user name and password
 */
public class Variables {
	
	// MySql url
	//private final String url = "jdbc:mysql://localhost/<dbname>";
	
	// MariaDB url
	// enter url
	private final String url = "jdbc:mariadb://<url>";
	// enter username
	private final String user = "";
	// enter password
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
