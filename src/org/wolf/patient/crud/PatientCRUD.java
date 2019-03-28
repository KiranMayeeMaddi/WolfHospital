package org.wolf.patient.crud;

import java.util.ArrayList;

import org.wolf.baseclasses.Patient;

public class PatientCRUD {

	//Returns the  information for the patient with given Id
	Patient viewPatient(Integer id){
		return null;
	}
	
	//Returns information of all patients
	Patient viewPatient(){
		return null;
	}
	
	//Returns list of patient information searched by given name
	//String matching LIKE query 
	ArrayList<Patient> getPatientIdByName(String name){
		return null;	
	}
	
	//Patient Info. search by ssn
	Patient getPatientIdBySSN(String ssn){
		return null;	
	}
	
	//Returns the newly created patient Id
	//If fails returns null
	Integer insertPatient(String name, String ssn, String dob, String gender, String pno, String address){
		return null;
	}
	
	//Returns true if the update was successful else false
	//Updates the record with this new information using the ID.
	Boolean updatePatient(Integer id, String name, String ssn, String dob, String gender, String pno, String address){
		return null;
	}
	
	// Deletes the patient information  using the patient Id
	//Returns true if successful else false
	Boolean deletePatient(Integer id){
		return null;
	}
}
