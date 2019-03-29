package org.wolf.staff.crud;

import java.util.ArrayList;

import org.wolf.baseclasses.Staff;

public class StaffCRUD {
	
	//Returns the  information for the staff with given Id
	public Staff viewStaff(Integer id){
		return null;
	}
	
	//Returns information of all staff members
	public Staff viewStaff(){
		return null;
	}
		
	//Returns list of staff info searched by given name
	// String Matching LIKE query 
	public ArrayList<Staff> getStaffByName(String name){
		return null;	
	}
	
	//Returns list of staff info with given professional title
	public ArrayList<Staff> getStaffIdprofTitle(String profTitle){
		return null;	
	}
	
	//Returns list of staff info in given dept
	public ArrayList<Staff> getStaffIddept(String dept){
		return null;	
	}
	
	//Returns the newly created staff Id
	//If fails returns null
	public Integer insertStaff(String name, String profTitle, String dob, String gender, String pno, String address, String dept, Double sal){
		return null;
	}
	
	//Returns true if the update was successful else false
	//Updates the record with this new information using the ID.
	public Boolean updateStaff(Integer id, String name, String profTitle, String dob, String gender, String pno, String address, String dept, Double sal){
		return null;
	}
	
	// Deletes the staff information  using the staff Id
	//Returns true if successful else false
	public Boolean deleteStaff(Integer id){
		return null;
	}
}
