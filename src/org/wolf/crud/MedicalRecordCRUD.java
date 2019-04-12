package org.wolf.crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
			
			String query = "insert into MedicalRecords (patient_id, start_date, end_date, diagnosis, prescription, responsible_doctor, process_treatment_plan)"
					+ " values (?,NOW(),?,?,?,?,?)";
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, patient_id);
			if (end_date.isEmpty())
				st.setNull(2, java.sql.Types.VARCHAR);
			else
				st.setString(2, end_date);
			st.setString(3, diagnosis);
			st.setString(4, prescription);
			st.setInt(5, responsible_doctor);
			st.setInt(6, process_treatment_plan);
			
		    st.executeUpdate();
		    
		    ResultSet rs = st.executeQuery("select record_id from MedicalRecords order by record_id desc limit 1");
			int record_id = 0;
		    while (rs.next())
		    	record_id = rs.getInt("record_id");
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
			
			String query = "update into MedicalRecords (patient_id=?, start_date=?, end_date=?, diagnosis=?, prescription=?, responsible_doctor=?, process_treatment_plan=? where record_id=?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, patient_id);
			st.setString(2, start_date);
			if (end_date.isEmpty())
				st.setNull(3, java.sql.Types.VARCHAR);
			else
				st.setString(3, end_date);
			st.setString(4, diagnosis);
			st.setString(5, prescription);
			st.setInt(6, responsible_doctor);
			st.setInt(7, process_treatment_plan);
			st.setInt(8, recordId);
			
		    st.executeUpdate();
		    
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
		
		//String end_date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		
		try {
			Connection conn = DatabaseConnection.getConnection();
			
		    Statement st = conn.createStatement();
		    
		    st.executeUpdate("UPDATE MedicalRecords SET end_date = NOW() WHERE record_id = " + recordId );
		    
		    return true;
	    }
	    catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	    	return false;
	    }
		
		
	}
}
