package org.wolf.crud;

import java.sql.*;

import java.util.ArrayList;

import org.wolf.baseclasses.Patient;
import org.wolf.baseclasses.PatientInfo;
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
	    } catch (SQLException ex) {
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
	    } catch (SQLException ex) {
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
	    } catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	    	return null;
	    }
	}
	
	//Returns the newly created patient Id
	//If fails returns null
	public static Integer insertPatient(String name, String ssn, String dob, String gender, String pno, String address){
		try {
			Connection conn = DatabaseConnection.getConnection();
			
			String query = "insert into Patient (name, address, phone_no, gender, ssn, date_of_birth)"
					+ " values (?,?,?,?,?,?)";
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, name);
			st.setString(2, address);
			st.setString(3, pno);
			st.setString(4, gender);
			if (ssn.isEmpty())
				st.setNull(5, java.sql.Types.VARCHAR);
			else
				st.setString(5, ssn);
			st.setString(6, dob);
			
		    st.executeUpdate();
		    
		    ResultSet rs = st.executeQuery("select patient_id from Patient order by patient_id desc limit 1");
			int patient_id = 0;
		    while (rs.next())
		    	patient_id = rs.getInt("patient_id");
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
			
			String query = "Update Patient set name=?, address=?, phone_no=?, gender=?, ssn=?, date_of_birth=? where patient_id=?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, name);
			st.setString(2, address);
			st.setString(3, pno);
			st.setString(4, gender);
			if (ssn.isEmpty())
				st.setNull(5, java.sql.Types.VARCHAR);
			else
				st.setString(5, ssn);
			st.setString(6, dob);
			st.setInt(7, id);
			
			st.executeUpdate();
			
			return true;
		}
		catch(SQLException e) {
			System.err.println(e.getMessage());
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
	
	public static PatientInfo getPatientMedicalInfo(Integer patient_id) {
		try {
			Connection conn = DatabaseConnection.getConnection();
		    Statement st = conn.createStatement();

		    ResultSet rs = st.executeQuery("SELECT * FROM Patient WHERE patient_id =" + patient_id);
		    Patient p = null;
		    String in_ward = null;
		    String process_treatment_plan = null;
		    String completing_treatment = null;
		    while(rs.next()) {
		    	p = new Patient(rs.getInt("patient_id"), rs.getString("name"), rs.getString("ssn"), rs.getString("date_of_birth"), 
			    		rs.getString("gender"), rs.getString("phone_no"), rs.getString("address"));
		    }
		    rs = st.executeQuery("SELECT * FROM MedicalRecords WHERE patient_id ="+ patient_id + "AND end_date = IS NULL");
		    try {
			    while(rs.next()) {
			    	process_treatment_plan = rs.getString("process_treatment_plan");
			    	completing_treatment = "No";
			    }
		    } catch(NullPointerException ne) {
		    	rs = st.executeQuery("SELECT * FROM MedicalRecords WHERE patient_id ="+ patient_id + "ORDER BY record_id DESC LIMIT 1");
		    	while(rs.next()) {
			    	process_treatment_plan = rs.getString("process_treatment_plan");
			    	completing_treatment = "No";
			    }
		    }
		    rs = st.executeQuery("SELECT * FROM Ward_Patient_Checks_In WHERE patient_id ="+ patient_id + "AND end_date = IS NULL");
		    try {
			    while(rs.next()) {
			    	in_ward = "Yes";
			    }
		    } catch(NullPointerException ne) {
		    	in_ward = "No";
		    }
		    PatientInfo pi = null;
	    	
			pi = new PatientInfo(p.getId(), p.getName(), p.getSsn(), p.getDob(), 
			    		p.getGender(), p.getPno(), p.getAddress(), process_treatment_plan, in_ward, completing_treatment);
		    return pi;
		} catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	    	return null;
	    }
	}
}
