package org.wolf.crud;

import java.sql.*;
import java.util.ArrayList;

import org.wolf.baseclasses.Patient;
import org.wolf.config.*;

public class PatientCRUD {

	//Returns the  information for the patient with given Id
	public static Patient viewPatient(Integer id){
		try {
			Connection conn = DatabaseConnection.getConnection();
		    Statement st = conn.createStatement();

		    ResultSet rs = st.executeQuery("SELECT * FROM Patient WHERE patient_id =" + id);
		    Patient p = null;
		    while(rs.next()) {
			    p = new Patient(rs.getInt("patient_id"), rs.getString("name"), rs.getString("ssn"), rs.getString("date_of_birth"), 
			    		rs.getString("gender"), rs.getString("phone_no"), rs.getString("address"));
		    }
		    return p;
	    }
	    catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	    	return null;
	    }
	}
	
	//Returns information of all patients
	public static ArrayList<Patient> viewPatients(){
		try {
			Connection conn = DatabaseConnection.getConnection();
		    Statement st = conn.createStatement();

		    ResultSet rs = st.executeQuery("SELECT * FROM Patient");
		    
		    ArrayList <Patient> patientList = new ArrayList <> ();
		    Patient p = null;
		    while(rs.next()) {
		    	p = new Patient(rs.getInt("patient_id"), rs.getString("name"), rs.getString("ssn"), rs.getString("date_of_birth"), 
			    		rs.getString("gender"), rs.getString("phone_no"), rs.getString("address"));
				
				patientList.add(p);
		    }
		    return patientList;
	    } catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	    	return null;
	    }
	}
	
	//Returns list of patient information searched by given name
	//String matching LIKE query 
	public static ArrayList<Patient> getPatientIdByName(String name){
		try {
			Connection conn = DatabaseConnection.getConnection();
		    Statement st = conn.createStatement();

		    ResultSet rs = st.executeQuery("SELECT * FROM Patient WHERE name LIKE '%" + name + "%'");
		    
		    ArrayList <Patient> patientList = new ArrayList <> ();
		    Patient p = null;
		    while(rs.next()) {
		    	p = new Patient(rs.getInt("patient_id"), rs.getString("name"), rs.getString("ssn"), rs.getString("date_of_birth"), 
			    		rs.getString("gender"), rs.getString("phone_no"), rs.getString("address"));
				
				patientList.add(p);
		    }
		    return patientList;
	    }
	    catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	    	return null;
	    }
	}
	
	//Patient Info. search by ssn
	public static Patient getPatientIdBySSN(String ssn){
		try {
			Connection conn = DatabaseConnection.getConnection();
		    Statement st = conn.createStatement();

		    ResultSet rs = st.executeQuery("SELECT * FROM Patient WHERE ssn ='" + ssn + "'");
		    Patient p = null;
		    while(rs.next()) {
		    	p = new Patient(rs.getInt("patient_id"), rs.getString("name"), rs.getString("ssn"), rs.getString("date_of_birth"), 
			    		rs.getString("gender"), rs.getString("phone_no"), rs.getString("address"));
		    }
		    return p;
	    }
	    catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	    	return null;
	    }
	}
	
	//Returns the newly created patient Id
	//If fails returns null
	public static Integer insertPatient(String name, String ssn, String dob, String gender, String pno, String address){
		try {
			Connection conn = DatabaseConnection.getConnection();
			
		    Statement st = conn.createStatement();
		    st.executeUpdate("INSERT INTO Patient(name, address, phone_no, gender, ssn, date_of_birth) " +
		                       "VALUES ('"+ name +"', '"+ address+"', '"+pno+"', '"+gender+"', '"+ssn+"', '"+dob+"')");
		    ResultSet rs = st.executeQuery("SELECT patient_id FROM Patient ORDER BY patient_id DESC LIMIT  1");
		    int patient_id = 0;
		    while(rs.next()) {
		    	patient_id = rs.getInt("patient_id");
		    }
		    
		    return patient_id;
	    }
	    catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	    	return null;
	    }
	}
	
	//Returns true if the update was successful else false
	//Updates the record with this new information using the ID.
	public static Boolean updatePatient(Integer id, String name, String ssn, String dob, String gender, String pno, String address){
		try {
			Connection conn = DatabaseConnection.getConnection();
			
		    Statement st = conn.createStatement();
		    st.executeUpdate("UPDATE Patient SET name = '"+ name +"', address = '"+ address +"', phone_no = '"+ pno +"', gender = '"+ gender 
		    					+"', ssn = '"+ ssn +"', date_of_birth = '"+ dob +"' WHERE patient_id = " + id );
		    
		    return true;
	    }
	    catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	    	return false;
	    }
	}
	
	// Deletes the patient information  using the patient Id
	//Returns true if successful else false
	public static Boolean deletePatient(Integer id){
		try {
			Connection conn = DatabaseConnection.getConnection();
			
		    Statement st = conn.createStatement();
		    st.executeUpdate("DELETE FROM Patient WHERE patient_id = " + id );
		    
		    return true;
	    } catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	    	return false;
	    }
	}
}
