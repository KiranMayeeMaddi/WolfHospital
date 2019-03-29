package org.wolf.medicalrecord.crud;

import java.util.ArrayList;

import org.wolf.baseclasses.MedicalRecord;

public class MedicalRecordCRUD {

	//Return All the medical records
	public ArrayList<MedicalRecord> viewMedicalRecords(){
		return null;
	}
	
	//Return medical Record for a  given  recordId
	public MedicalRecord viewMedicalRecords(Integer recordId){
		return null;
	}
	
	//Returns all the medical Records for a given PatientId
	public ArrayList<MedicalRecord> getMedicalRecordsForPatient(Integer patientId){
		return null;
	}
	
	//Returns all the medical Records for a given responsible doctor Id
	public ArrayList<MedicalRecord> getMedicalRecordsForDoctor(Integer doctor_id){
		return null;
		
	}
	
	//Insert a medical record for the given data
	//Return true if successful else false
	public Boolean insertMedicalRecord(Integer patient_id, String start_date,String end_date, String diagnosis, String prescription, String responsible_doctor){
		return null;
	}
	
	//Updates a medical record with the given data
	//Return true if successful else false
	public Boolean updateMedicalRecord(Integer recordId, Integer patient_id, String start_date,String end_date, String diagnosis, String prescription, String responsible_doctor){
		return null;
	}
	
	//Deletes a medical record with the given recordId
	//Return true if successful else false
	public Boolean deleteMedicalRecord(Integer record_id){
		return null;
	}
}
