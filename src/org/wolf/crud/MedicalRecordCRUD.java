package org.wolf.crud;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.jdbc.core.RowMapper;
import org.wolf.baseclasses.MedicalRecord;

class MedicalRecordMapper implements RowMapper {
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		MedicalRecord m = new MedicalRecord();
		m.setDiagnosis(rs.getString("diagnosis"));
		m.setEnd_date(rs.getString("end_date"));
		m.setPatient_id(rs.getInt("patient_id"));
		m.setPrescription(rs.getString("prescription"));
		m.setRecord_id(rs.getInt("record_id"));
		m.setResponsible_doctor_id(rs.getInt("responsible_doctor"));
		m.setStart_date(rs.getString("start_date"));
		return m;
	}
}

public final class MedicalRecordCRUD {

	//Return All the medical records
	public static ArrayList<MedicalRecord> viewMedicalRecords(){
		return null;
	}
	
	//Return medical Record for a  given  recordId
	public static MedicalRecord viewMedicalRecords(Integer recordId){
		return null;
	}
	
	//Returns all the medical Records for a given PatientId
	public static ArrayList<MedicalRecord> getMedicalRecordsForPatient(Integer patientId){
		return null;
	}
	
	//Returns all the medical Records for a given responsible doctor Id
	public static ArrayList<MedicalRecord> getMedicalRecordsForDoctor(Integer doctor_id){
		return null;
		
	}
	
	//Insert a medical record for the given data
	//Return latest auto generated id
	public static Integer insertMedicalRecord(Integer patient_id, String start_date,String end_date, String diagnosis, String prescription, Integer responsible_doctor){
		return null;
	}
	
	//Updates a medical record with the given data
	//Return true if successful else false
	public static Boolean updateMedicalRecord(Integer recordId, Integer patient_id, String start_date,String end_date, String diagnosis, String prescription, Integer responsible_doctor){
		return null;
	}
	
	//Deletes a medical record with the given recordId
	//Return true if successful else false
	public static Boolean deleteMedicalRecord(Integer record_id){
		return null;
	}

	public static Boolean updateMedicalRecordEndTime(Integer recordId) {
		// Update the end time to the current time stamp through SQL
		return null;
	}
}
