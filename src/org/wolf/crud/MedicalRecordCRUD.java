package org.wolf.crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.wolf.baseclasses.MedicalRecord;
import org.wolf.config.DatabaseConnection;


/**
 * Basic Operations for the MedicalRecords table such as viewing, inserting, updating and deleting.
 */
public final class MedicalRecordCRUD {
	
	/**
	 * @return All the medical records information
	 */
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
	    	ex.printStackTrace();
	    	return null;
	    }
	}
	
	/**
	 * @param recordId
	 * @return All the medical records information for the given recordId
	 */
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
		    if(mr == null){
		    	System.out.println("No such Medical Record");
		    }
		    return mr;
		    
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    	return null;
	    }
	}
	
	/**
	 * @param patientId
	 * @return All the medical records information for a given patient
	 */
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
		    if(recordList.size()==0){
		    	System.out.println("No such medical records");
		    }
		    return recordList;
		    
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    	return null;
	    }
	}
	
	/**
	 * @param doctor_id
	 * @return All the medical records information for a given responsible doctor Id
	 */
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
		    if(recordList.size()==0){
		    	System.out.println("No such medical records");
		    }
		    return recordList;
		    
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    	return null;
	    }
		
	}
	
	/**
	 * Inserts a medical record for the given parameters in the MedicalRecords table
	 * @param patient_id
	 * @param start_date
	 * @param end_date
	 * @param diagnosis
	 * @param prescription
	 * @param responsible_doctor
	 * @param process_treatment_plan
	 * @return latest auto generated id for medical record
	 */
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
	    	ex.printStackTrace();
	    	return null;
	    }
	}
	
	/**Updates a medical record with the given data
	 * @param recordId
	 * @param patient_id
	 * @param start_date
	 * @param end_date
	 * @param diagnosis
	 * @param prescription
	 * @param responsible_doctor
	 * @param process_treatment_plan
	 * @return true if successful else false
	 */
	public static Boolean updateMedicalRecord(Integer recordId, Integer patient_id, String start_date,String end_date, String diagnosis, String prescription, Integer responsible_doctor, Integer process_treatment_plan){
		try {
			Connection conn = DatabaseConnection.getConnection();
			
			String query = "update MedicalRecords set patient_id=?, start_date=?, end_date=?, diagnosis=?, prescription=?, responsible_doctor=?, process_treatment_plan=? where record_id=?";
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
	    	ex.printStackTrace();
	    	return false;
	    }
	}
	
	/**
	 * Deletes a medical record with the given recordId
	 * @param record_id
	 * @return true if successful else false
	 */
	public static Boolean deleteMedicalRecord(Integer record_id){
		try {
			Connection conn = DatabaseConnection.getConnection();
			
		    Statement st = conn.createStatement();
		    st.executeUpdate("DELETE FROM MedicalRecords WHERE record_id = " + record_id );
		    
		    return true;
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    	return false;
	    }
	}

	/**
	 * Updates the end time of a medical record and sets it to the current time
	 * This functionality is used to end a treatment
	 * @param recordId
	 * @return true if successful else false
	 */
	public static Boolean updateMedicalRecordEndTime(Integer recordId) {
		// Update the end time to the current time stamp through SQL
		
		try {
			Connection conn = DatabaseConnection.getConnection();
			
		    Statement st = conn.createStatement();
		    
		    st.executeUpdate("UPDATE MedicalRecords SET end_date = NOW() WHERE record_id = " + recordId );
		    
		    return true;
	    }
	    catch (SQLException ex) {
	    	ex.printStackTrace();
	    	return false;
	    }
		
		
	}
}
