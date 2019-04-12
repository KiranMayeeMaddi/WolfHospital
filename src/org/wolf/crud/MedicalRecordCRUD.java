package org.wolf.crud;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.wolf.baseclasses.MedicalRecord;
import org.wolf.baseclasses.Patient;
import org.wolf.config.DatabaseConnection;


public final class MedicalRecordCRUD {

	//Return All the medical records
	public static ArrayList<MedicalRecord> viewMedicalRecords(){
		try {
			Connection conn = DatabaseConnection.getConnection();
		    Statement st = conn.createStatement();

		    ResultSet rs = st.executeQuery("SELECT * FROM MedicalRecords");
		    
		    ArrayList <MedicalRecord> recordList = new ArrayList <> ();
		    MedicalRecord mr = null;
		    while(rs.next()) {
		    	
		    	mr = new MedicalRecord(rs.getInt("record_id"), rs.getInt("patient_id"), rs.getString("start_date"), rs.getString("end_date"), 
		    			rs.getString("diagnosis"), rs.getString("prescription"), rs.getInt("responsible_doctor"), rs.getInt("process_treatment_plan"));
				
		    	recordList.add(mr);
		    }
		    return recordList;
		    
	    } catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	    	return null;
	    }
	}
	
	//Return medical Record for a  given  recordId
	public static MedicalRecord viewMedicalRecords(Integer recordId){
		try {
			Connection conn = DatabaseConnection.getConnection();
		    Statement st = conn.createStatement();

		    ResultSet rs = st.executeQuery("SELECT * FROM MedicalRecords WHERE record_id =" +recordId);
		    
		    MedicalRecord mr = null;
		    while(rs.next()) {
		    	
		    	mr = new MedicalRecord(rs.getInt("record_id"), rs.getInt("patient_id"), rs.getString("start_date"), rs.getString("end_date"), 
		    			rs.getString("diagnosis"), rs.getString("prescription"), rs.getInt("responsible_doctor"), rs.getInt("process_treatment_plan"));
				
		    }
		    return mr;
		    
	    } catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	    	return null;
	    }
	}
	
	//Returns all the medical Records for a given PatientId
	public static ArrayList<MedicalRecord> getMedicalRecordsForPatient(Integer patientId){
		try {
			Connection conn = DatabaseConnection.getConnection();
		    Statement st = conn.createStatement();

		    ResultSet rs = st.executeQuery("SELECT * FROM MedicalRecords WHERE patient_id =" + patientId);
		    
		    ArrayList <MedicalRecord> recordList = new ArrayList <> ();
		    MedicalRecord mr = null;
		    while(rs.next()) {
		    	
		    	mr = new MedicalRecord(rs.getInt("record_id"), rs.getInt("patient_id"), rs.getString("start_date"), rs.getString("end_date"), 
		    			rs.getString("diagnosis"), rs.getString("prescription"), rs.getInt("responsible_doctor"), rs.getInt("process_treatment_plan"));
				
		    	recordList.add(mr);
		    }
		    return recordList;
		    
	    } catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	    	return null;
	    }
	}
	
	//Returns all the medical Records for a given responsible doctor Id
	public static ArrayList<MedicalRecord> getMedicalRecordsForDoctor(Integer doctor_id){
		try {
			Connection conn = DatabaseConnection.getConnection();
		    Statement st = conn.createStatement();

		    ResultSet rs = st.executeQuery("SELECT * FROM MedicalRecords WHERE responsible_doctor = " + doctor_id);
		    
		    ArrayList <MedicalRecord> recordList = new ArrayList <> ();
		    MedicalRecord mr = null;
		    while(rs.next()) {
		    	
		    	mr = new MedicalRecord(rs.getInt("record_id"), rs.getInt("patient_id"), rs.getString("start_date"), rs.getString("end_date"), 
		    			rs.getString("diagnosis"), rs.getString("prescription"), rs.getInt("responsible_doctor"), rs.getInt("process_treatment_plan"));
				
		    	recordList.add(mr);
		    }
		    return recordList;
		    
	    } catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	    	return null;
	    }
		
	}
	
	//Insert a medical record for the given data
	//Return latest auto generated id
	public static Integer insertMedicalRecord(Integer patient_id, String start_date,String end_date, String diagnosis, String prescription, Integer responsible_doctor, Integer process_treatment_plan){
		try {
			Connection conn = DatabaseConnection.getConnection();
			
		    Statement st = conn.createStatement();
		    
//		    System.out.println("INSERT INTO MedicalRecords(patient_id, start_date, end_date, diagnosis, prescription, responsible_doctor) " +
//                    "VALUES ("+ patient_id +", '"+ start_date+"', '"+end_date+"', '"+diagnosis+"', '"+prescription+"', "+responsible_doctor+")");
		   
//		    start_date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		    
		    st.executeUpdate("INSERT INTO MedicalRecords(patient_id, start_date, end_date, diagnosis, prescription, responsible_doctor, process_treatment_plan) " +
		                       "VALUES ("+ patient_id +", '"+ start_date+"', '"+end_date+"', '"+diagnosis+"', '"+prescription+"', "+responsible_doctor+", " + process_treatment_plan + ")");
		    ResultSet rs = st.executeQuery("SELECT record_id FROM MedicalRecords ORDER BY record_id DESC LIMIT  1");
		    int record_id = 0;
		    
		    while(rs.next()) {
		    	record_id = rs.getInt("record_id");
		    }
		    
		    return record_id;
	    }
	    catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	    	return null;
	    }
	}
	
	//Updates a medical record with the given data
	//Return true if successful else false
	public static Boolean updateMedicalRecord(Integer recordId, Integer patient_id, String start_date,String end_date, String diagnosis, String prescription, Integer responsible_doctor, Integer process_treatment_plan){
		try {
			Connection conn = DatabaseConnection.getConnection();
			
		    Statement st = conn.createStatement();
		    
		    st.executeUpdate("UPDATE MedicalRecords SET patient_id = "+ patient_id +", start_date = '"+ start_date +"', end_date = '"+ end_date +"', diagnosis = '"+ diagnosis 
		    					+"', prescription = '"+ prescription +"', responsible_doctor = "+ responsible_doctor +", process_treatment_plan = "+process_treatment_plan+" WHERE record_id = " + recordId );
		    
		    return true;
	    } catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	    	return false;
	    }
	}
	
	//Deletes a medical record with the given recordId
	//Return true if successful else false
	public static Boolean deleteMedicalRecord(Integer record_id){
		try {
			Connection conn = DatabaseConnection.getConnection();
			
		    Statement st = conn.createStatement();
		    st.executeUpdate("DELETE FROM MedicalRecords WHERE record_id = " + record_id );
		    
		    return true;
	    } catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	    	return false;
	    }
	}

	public static Boolean updateMedicalRecordEndTime(Integer recordId) {
		// Update the end time to the current time stamp through SQL
		
		String end_date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		
		try {
			Connection conn = DatabaseConnection.getConnection();
			
		    Statement st = conn.createStatement();
		    
		    st.executeUpdate("UPDATE MedicalRecords SET end_date = '"+ end_date +"' WHERE record_id = " + recordId );
		    
		    return true;
	    }
	    catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	    	return false;
	    }
		
		
	}
}
