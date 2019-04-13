package org.wolf.crud;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.wolf.baseclasses.Bed;
import org.wolf.config.DatabaseConnection;


public final class WardCRUD {
	
	//Get wardInfo by Id
	/**
	 * @param int ward_id
	 * This function fetches ward_id, capacity, charges per day and all the patients staying in that ward of a given ward id
	 * @return String ward_details
	 */
	public static String viewWard(Integer ward_id){
		try {
			Connection conn = DatabaseConnection.getConnection();
			
		    Statement st = conn.createStatement();
		    
		    String out = "";

		    ResultSet rs = st.executeQuery("SELECT Ward.ward_id, capacity, charges_per_day, GROUP_CONCAT(patient_id) AS patients FROM Ward "
		    		+ "LEFT JOIN Ward_Patient_Checks_In ON Ward.ward_id = Ward_Patient_Checks_In.ward_id WHERE Ward.ward_id = "+ward_id+" GROUP BY Ward.ward_id ");
		    if(rs.isBeforeFirst()) {
			    while(rs.next()) {
			    	out += "ward_id = " + rs.getInt("ward_id");
			    	out += " capacity = " + rs.getInt("capacity");
			    	out += " charges_per_day = " + rs.getDouble("charges_per_day");
				    out += " Patients = " + rs.getString("patients");
			    }
		    } else {
		    	System.out.println("Data not found for given ward id");
		    }
		    return out;
		    
	    } catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	    	return null;
	    }
	} 
	
	//Get all wardInfo
	/**
	 * @param
	 * This function fetches ward_id, capacity, charges per day and all the patients staying for all wards in Ward table
	 * @return ArrayList of String ward_details
	 */
	public static ArrayList<String> viewWard(){
		try {
			Connection conn = DatabaseConnection.getConnection();
			
		    Statement st = conn.createStatement();

		    ArrayList <String> wardList = new ArrayList <> ();
		    String out = null;
		    
		    ResultSet rs = st.executeQuery("SELECT Ward.ward_id, capacity, charges_per_day, GROUP_CONCAT(patient_id) AS patients FROM Ward "
		    		+ "LEFT JOIN Ward_Patient_Checks_In ON Ward.ward_id = Ward_Patient_Checks_In.ward_id  GROUP BY Ward.ward_id ");
		    while(rs.next()) {
		    	out = "";
		    	out += "ward_id = " + rs.getInt("ward_id");
		    	out += " capacity = " + rs.getInt("capacity");
		    	out += " charges_per_day = " + rs.getDouble("charges_per_day");
			    out += " Patients = " + rs.getString("patients");
			    
		    	wardList.add(out);
		    }
		    return wardList;
		    
	    } catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	    	return null;
	    }
	}
	
	//Get all beds info
	/**
	 * @returns the array list of all beds present in Bed table
	 */
	public static ArrayList<Bed> viewBeds(){
		try {
			Connection conn = DatabaseConnection.getConnection();
			
		    Statement st = conn.createStatement();

		    ResultSet rs = st.executeQuery("SELECT * FROM Bed");
		    
		    ArrayList <Bed> bedList = new ArrayList <> ();
		    Bed b = null;
		    while(rs.next()) {
		    	
		    	b = new Bed(rs.getInt("ward_id"), rs.getInt("bed_id"), rs.getString("is_available"));
				
		    	bedList.add(b);
		    }
		    return bedList;
		    
	    } catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	    	return null;
	    }
	}
	
	//Get bed info by Id
	/**
	 * @param Integer ward_id
	 * @param Integer bed_id
	 * @return Bed details for the given bed id and ward id
	 */
	public static Bed viewBedById(Integer ward_id, Integer bed_id){
		try {
			Connection conn = DatabaseConnection.getConnection();
			
		    Statement st = conn.createStatement();
		    ResultSet rs = st.executeQuery("SELECT * FROM Bed WHERE ward_id =" + ward_id + " AND bed_id = " + bed_id);
		    
		    Bed b = null;
		    if(rs.isBeforeFirst()) {
			    while(rs.next()) {
			    	
			    	b = new Bed(rs.getInt("ward_id"), rs.getInt("bed_id"), rs.getString("is_available"));
			    }
		    } else {
		    	System.out.println("Data not found for given bed id");
		    }
		    return b;
	    } catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	    	return null;
	    }
	}
	
	// List of info of all available beds
	/**
	 * @return Array list of available Beds from Bed table where is_avaialable is 'Y'
	 */
	public static ArrayList<Bed> checkAvailableBeds(){
		try {
			Connection conn = DatabaseConnection.getConnection();
			
		    Statement st = conn.createStatement();

		    ResultSet rs = st.executeQuery("SELECT * FROM Bed WHERE is_available = 'Y'");
		    
		    ArrayList <Bed> bedList = new ArrayList <> ();
		    Bed b = null;
		    if(rs.isBeforeFirst()) {
			    while(rs.next()) {
			    	
			    	b = new Bed(rs.getInt("ward_id"), rs.getInt("bed_id"), rs.getString("is_available"));
					
			    	bedList.add(b);
			    }
		    } else {
		    	System.out.println("No beds available");
		    }		  
		    return bedList;
		    
	    } catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	    	return null;
	    }
	}
	
	//List of available beds in the given ward size
	/**
	 * @param Integer ward_size
	 * @return Array list of available Beds from Bed table where is_avaialable is 'Y'
	 */
	public static ArrayList<Bed> checkAvailableBeds(Integer ward_size){
		try {
			Connection conn = DatabaseConnection.getConnection();
			
		    Statement st = conn.createStatement();

		    ResultSet rs = st.executeQuery("SELECT W.* , B.bed_id, is_available from Ward W, Bed B where W.ward_id = B.ward_id and B.is_available = 'Y' AND  W.capacity =" + ward_size);
		    
		    ArrayList <Bed> bedList = new ArrayList <> ();
		    Bed b = null;
		    if(rs.isBeforeFirst()) {
			    while(rs.next()) {
			    	
			    	b = new Bed(rs.getInt("ward_id"), rs.getInt("bed_id"), rs.getString("is_available"));
					
			    	bedList.add(b);
			    }
		    } else {
		    	System.out.println("No beds available in the given ward size.");
		    }
		    return bedList;
		    
	    } catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	    	return null;
	    }
	}
		
	//Inserts new Ward
	//Returns the new WardId
	/**
	 * @param Integer capacity
	 * @param Double chargesPerDay
	 * @return the newly inserted ward id in the Ward table
	 */
	public static Integer insertWard(Integer capacity,Double chargesPerDay){
		try {
			Connection conn = DatabaseConnection.getConnection();
			
		    Statement st = conn.createStatement();
		    
		    st.executeUpdate("INSERT INTO Ward(capacity, charges_per_day) " +
		                       "VALUES ("+ capacity +", "+ chargesPerDay+")");
		    ResultSet rs = st.executeQuery("SELECT ward_id FROM Ward ORDER BY ward_id DESC LIMIT  1");
		    int ward_id = 0;
		    while(rs.next()) {
		    	ward_id = rs.getInt("ward_id");
		    }
		    
		    return ward_id;
	    } catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	    	return null;
	    }
	}
	
	//Inserts a Bed in the given ward
	//Check for the capacity of the ward before inserting
	//Return BedId of the newly inserted Bed
	/**
	 * @param Integer ward_id
	 * @return the newly inserted bed id in the Bed table
	 */
	public static Integer insertBedInWard(Integer ward_id){
		try {
			Connection conn = DatabaseConnection.getConnection();
			
		    Statement st = conn.createStatement();
		    
		    ResultSet rs = st.executeQuery("SELECT * FROM Ward WHERE ward_id =" + ward_id);
		    
		    int ward_size = 0;  
		    while(rs.next()) {
		    	
		    	ward_size = rs.getInt("capacity");
		    }
		    rs = st.executeQuery("SELECT COUNT(*) AS beds FROM Bed WHERE ward_id =" + ward_id);
		    int ward_bed_count = 0;
		    while(rs.next()) {
		    	
		    	ward_bed_count = rs.getInt("beds");
		    }
		    if(ward_size > ward_bed_count) {
			    st.executeUpdate("INSERT INTO Bed(bed_id, ward_id, is_available) VALUES ("+ (ward_bed_count +1)+ ","+ ward_id +", 'Y')");

			    rs = st.executeQuery("SELECT * FROM Bed WHERE ward_id = "+ward_id+" ORDER BY bed_id DESC LIMIT  1");
			    Integer bed_id = 0;
			    while(rs.next()) {
			    	bed_id = rs.getInt("bed_id");
			    }
			    return bed_id;
		    } else {
		    	System.out.println("Ward full");
		    }
		    
		    return null;
		    
	    } catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	    	return null;
	    }
	}
	
	//Update the ward with the given values
	//Return true if successful else false
	/**
	 * @param Integer ward_id
	 * @param Integer capacity
	 * @param Double chargesPerDay
	 * @return true is successfully updated the bed else false
	 */
	public static Boolean updateWard(Integer ward_id, Integer capacity,Double chargesPerDay){
		try {
			Connection conn = DatabaseConnection.getConnection();
			
		    Statement st = conn.createStatement();
		    
		    st.executeUpdate("UPDATE Ward SET capacity = "+ capacity +", charges_per_day = "+ chargesPerDay +" WHERE ward_id = " + ward_id );
		    
		    return true;
		    
	    } catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	    	return false;
	    }
	}
	
	
	//Remove bed from the given ward
	//Return true if successful else false
	/**
	 * @param Integer ward_id
	 * @param Integer bed_id
	 * @return true is successfully removed the bed else false
	 */
	public static Boolean removeBed(Integer ward_id,Integer bed_id){
		try {
			Connection conn = DatabaseConnection.getConnection();
			
		    Statement st = conn.createStatement();
		    st.executeUpdate("DELETE FROM Bed WHERE ward_id = "+ ward_id +" AND bed_id = " + bed_id );
		    return true;
		    
	    } catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	    	return false;
	    }
	}
	
	//Remove the ward
	//Also remove all the beds associated with it
	//Return true if successful else false
	/**
	 * @param Integer ward_id
	 * @return true is successfully removed the ward else false
	 */
	public static Boolean removeWard(Integer ward_id){
		try {
			Connection conn = DatabaseConnection.getConnection();
			
		    Statement st = conn.createStatement();
		    st.executeUpdate("DELETE FROM Bed WHERE ward_id = "+ ward_id );
		    st.executeUpdate("DELETE FROM Ward WHERE ward_id = "+ ward_id );
		    
		    return true;
		    
	    } catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	    	return false;
	    }
	}
	
	//Set the availability of the bed to Y
	/**
	 * @param Integer ward_id
	 * @param Integer bed_id
	 * @return true is successfully released the bed else false
	 * @throws SQLException
	 */
	public static Boolean releaseBed(Integer ward_id, Integer bed_id) throws SQLException{
			Connection conn = DatabaseConnection.getConnection();
			
		    Statement st = conn.createStatement();
		    
		    st.executeUpdate("UPDATE Bed SET is_available = 'Y' WHERE ward_id = " + ward_id + " AND bed_id =" + bed_id);
		    
		    return true;
	}
	
	//Set the availability of the bed to N
	/**
	 * @param Integer ward_id
	 * @param Integer bed_id
	 * @return true is successfully marked is_available 'N' else false
	 * @throws SQLException
	 */
	public static Boolean occupyBed(Integer ward_id, Integer bed_id) throws SQLException {
		Connection conn = DatabaseConnection.getConnection();
		
	    Statement st = conn.createStatement();
	    st.executeUpdate("UPDATE Bed SET is_available = 'N' WHERE ward_id = " + ward_id + " AND bed_id =" + bed_id);
	    
	    return true;
	}
	
	/**
	 * @param Integer checkinId
	 * @return calculated accommodation charges
	 * @throws SQLException
	 */
	public static Double calculateAccomCharges(Integer checkinId) throws SQLException {
		//Based on the rate sheet in the Wards table and the days(end time - start time)
		//Calculate the amount and return it
		
		Connection conn = DatabaseConnection.getConnection();
		
	    Statement st = conn.createStatement();
	    int stay_days = 0;
	    Double charges_per_day = 0.0;
	    ResultSet rs = st.executeQuery("SELECT DATEDIFF(A.et, A.start_time) + 1 as stay_days, A.charges_per_day "
	    		+ "FROM ( SELECT CASE  WHEN end_time IS NULL THEN NOW() ELSE end_time END as et, end_time, start_time, charges_per_day "
	    		+ "FROM Ward_Patient_Checks_In "
	    		+ "INNER JOIN Ward ON Ward_Patient_Checks_In.ward_id = Ward.ward_id "
	    		+ "WHERE checkin_id = " + checkinId + ") A");
	    while(rs.next()) {
	    	stay_days = rs.getInt("stay_days");
	    	charges_per_day = rs.getDouble("charges_per_day");
	    }
	    return (double)(charges_per_day * stay_days);
	} 
	
	//Return true if successful else false
	//Assuming the bed availability has been checked
	/**
	 * @param Integer patientId
	 * @param Integer ward_id
	 * @param Integer bed_id
	 * @param String endTime
	 * @return true is successfully reserved the bed else false
	 */
	public static Boolean reserveBed(Integer patientId, Integer ward_id,Integer bed_id, String endTime){
		try {
			Connection conn = DatabaseConnection.getConnection();
			
		    Statement st = conn.createStatement();
		    st.executeUpdate("INSERT INTO Ward_Patient_Checks_In(patient_id, ward_id, bed_id, start_time, end_time) "
		    		+ "VALUES("+patientId+", "+ward_id+","+bed_id+",NOW(),'"+endTime+"')");
		    
		    st.executeUpdate("UPDATE Bed SET is_available = 'N' WHERE ward_id = " + ward_id + " AND bed_id =" + bed_id );
		    
		    return true;
		    
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    	return false;
	    }
	}
}
