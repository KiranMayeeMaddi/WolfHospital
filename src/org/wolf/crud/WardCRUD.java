package org.wolf.crud;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.wolf.baseclasses.Bed;
import org.wolf.baseclasses.MedicalRecord;
import org.wolf.baseclasses.Ward;
import org.wolf.baseclasses.Ward_Patient;
import org.wolf.config.DatabaseConnection;


public final class WardCRUD {
	
	//Get wardInfo by Id
	public static Ward viewWard(Integer ward_id){
		try {
			Connection conn = DatabaseConnection.getConnection();
			
		    Statement st = conn.createStatement();

		    ResultSet rs = st.executeQuery("SELECT * FROM Ward WHERE ward_id =" + ward_id);
		    
		    Ward w = null;
		    while(rs.next()) {
		    	
		    	w = new Ward(rs.getInt("ward_id"), rs.getInt("capacity"), rs.getDouble("charges_per_day"));
		    }
		    return w;
		    
	    } catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	    	return null;
	    }
	} 
	
	//Get all wardInfo
	public static ArrayList<Ward> viewWard(){
		try {
			Connection conn = DatabaseConnection.getConnection();
			
		    Statement st = conn.createStatement();

		    ResultSet rs = st.executeQuery("SELECT * FROM Ward");
		    
		    ArrayList <Ward> wardList = new ArrayList <> ();
		    Ward w = null;
		    while(rs.next()) {
		    	
		    	w = new Ward(rs.getInt("ward_id"), rs.getInt("capacity"), rs.getDouble("charges_per_day"));
				
		    	wardList.add(w);
		    }
		    return wardList;
		    
	    } catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	    	return null;
	    }
	}
	
	//Get all beds info
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
	public static Bed viewBedById(Integer ward_id, Integer bed_id){
		try {
			Connection conn = DatabaseConnection.getConnection();
			
		    Statement st = conn.createStatement();

		    ResultSet rs = st.executeQuery("SELECT * FROM Ward WHERE ward_id =" + ward_id + "AND bed_id = " + bed_id);
		    
		    Bed b = null;
		    while(rs.next()) {
		    	
		    	b = new Bed(rs.getInt("ward_id"), rs.getInt("bed_id"), rs.getString("is_available"));
		    }
		    return b;
		    
	    } catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	    	return null;
	    }
	}
	
	// List of info of all available beds
	public static ArrayList<Bed> checkAvailableBeds(){
		try {
			Connection conn = DatabaseConnection.getConnection();
			
		    Statement st = conn.createStatement();

		    ResultSet rs = st.executeQuery("SELECT * FROM Bed WHERE is_available = 1");
		    
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
	
	//List of available beds in the given ward size
	public static ArrayList<Bed> checkAvailableBeds(Integer ward_size){
		try {
			Connection conn = DatabaseConnection.getConnection();
			
		    Statement st = conn.createStatement();

		    ResultSet rs = st.executeQuery("SELECT W.* , B.bed_id from Ward W, Bed B where W.ward_id = B.ward_id and B.is_available = 'Y' AND  W.capacity =" + ward_size);
		    
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
		
	//Inserts new Ward
	//Returns the new WardId
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
			    st.executeUpdate("INSERT INTO Bed(ward_id) " +
			                       "VALUES ("+ ward_id +")");
			    rs = st.executeQuery("SELECT bed_id FROM Bed ORDER BY bed_id DESC LIMIT  1");
			    int bed_id = 0;
			    while(rs.next()) {
			    	bed_id = rs.getInt("bed_id");
			    }
			    
			    return bed_id;
		    }
		    
		    return null;
		    
	    } catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	    	return null;
	    }
	}
	
	//Update the ward with the given values
	//Return true if successful else false
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
	public static Boolean releaseBed(Integer ward_id, Integer bed_id){
		try {
			Connection conn = DatabaseConnection.getConnection();
			
		    Statement st = conn.createStatement();
		    
		    st.executeUpdate("UPDATE Bed SET is_available = 'Y' WHERE ward_id = " + ward_id + " AND bed_id =" + bed_id);
		    
		    return true;
		    
	    } catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	    	return false;
	    }
	}
	
	//Set the availability of the bed to N
	public static Boolean occupyBed(Integer ward_id, Integer bed_id){
		try {
			Connection conn = DatabaseConnection.getConnection();
			
		    Statement st = conn.createStatement();
		    st.executeUpdate("UPDATE Bed SET is_available = 'N' WHERE ward_id = " + ward_id + " AND bed_id =" + bed_id);
		    
		    return true;
		    
	    } catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	    	return false;
	    }
	}
	
	public static Double calculateAccomCharges(Integer checkinId){
		//Based on the rate sheet in the Wards table and the days(end time - start time)
		//Calculate the amount and return it
		
		try {
			Connection conn = DatabaseConnection.getConnection();
			
		    Statement st = conn.createStatement();
		    int stay_days = 0;
		    int charges_per_day = 0;
		    ResultSet rs = st.executeQuery("SELECT DATEDIFF(end_time, start_time) + 1 AS stay_days, charges_per_day FROM Ward_Patient_Checks_In INNER JOIN Ward ON "
		    		+ "Ward_Patient_Checks_In.ward_id = Ward.ward_id "
		    		+ "WHERE checkin_id = " + checkinId);
		    while(rs.next()) {
		    	stay_days = rs.getInt("stay_days");
		    	charges_per_day = rs.getInt("ward_id");
		    }
		    
		    return (double)(charges_per_day * stay_days);
		    
	    } catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	    	return null;
	    }
	} 
	
	//Return true if successful else false
	//Assuming the bed availability has been checked
	public static Boolean reserveBed(Integer patientId, Integer ward_id,Integer bed_id, String endTime){
		try {
			Connection conn = DatabaseConnection.getConnection();
			
		    Statement st = conn.createStatement();
		    
		    st.executeUpdate("UPDATE Bed SET is_available = 'N', start_time = NOW(), end_time = '"+ endTime +"' WHERE ward_id = " + ward_id + " AND bed_id =" + bed_id );
		    
		    return true;
		    
	    } catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	    	return false;
	    }
	}
}
