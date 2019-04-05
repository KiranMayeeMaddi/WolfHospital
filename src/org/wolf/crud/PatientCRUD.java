package org.wolf.crud;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.jdbc.core.RowMapper;
import org.wolf.baseclasses.Patient;

class PatientMapper implements RowMapper {
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		Patient p = new Patient();
		p.setAddress(rs.getString("address"));
		p.setDob(rs.getString("date_of_birth"));
		p.setGender(rs.getString("gender"));
		p.setId(rs.getInt("patient_id"));
		p.setName(rs.getString("name"));
		p.setPno(rs.getString("phone_no"));
		p.setSsn(rs.getString("ssn"));
		return p;
	}
}

public class PatientCRUD {

	//Returns the  information for the patient with given Id
	public static Patient viewPatient(Integer id){
		return null;
	}
	
	//Returns information of all patients
	public static ArrayList<Patient> viewPatient(){
		return null;
	}
	
	//Returns list of patient information searched by given name
	//String matching LIKE query 
	public static ArrayList<Patient> getPatientIdByName(String name){
		return null;	
	}
	
	//Patient Info. search by ssn
	public static Patient getPatientIdBySSN(String ssn){
		return null;	
	}
	
	//Returns the newly created patient Id
	//If fails returns null
	public static Integer insertPatient(String name, String ssn, String dob, String gender, String pno, String address){
		return null;
	}
	
	//Returns true if the update was successful else false
	//Updates the record with this new information using the ID.
	public static Boolean updatePatient(Integer id, String name, String ssn, String dob, String gender, String pno, String address){
		return null;
	}
	
	// Deletes the patient information  using the patient Id
	//Returns true if successful else false
	public static Boolean deletePatient(Integer id){
		return null;
	}
}
