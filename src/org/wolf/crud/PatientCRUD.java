package org.wolf.crud;

import java.util.ArrayList;

import org.wolf.baseclasses.Patient;

public class PatientCRUD {

	//Returns the  information for the patient with given Id
	public Patient viewPatient(Integer id){
		return null;
	}
	
	//Returns information of all patients
	public Patient viewPatient(){
		return null;
	}
	
	//Returns list of patient information searched by given name
	//String matching LIKE query 
	public ArrayList<Patient> getPatientIdByName(String name){
		return null;	
	}
	
	//Patient Info. search by ssn
	public Patient getPatientIdBySSN(String ssn){
		return null;	
	}
	
	//Returns the newly created patient Id
	//If fails returns null
	public Integer insertPatient(String name, String ssn, String dob, String gender, String pno, String address){
		return null;
	}
	
	//Returns true if the update was successful else false
	//Updates the record with this new information using the ID.
	public Boolean updatePatient(Integer id, String name, String ssn, String dob, String gender, String pno, String address){
		return null;
	}
	
	// Deletes the patient information  using the patient Id
	//Returns true if successful else false
	public Boolean deletePatient(Integer id){
		return null;
	}
}
