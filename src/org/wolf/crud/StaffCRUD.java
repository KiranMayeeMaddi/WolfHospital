package org.wolf.crud;

import java.sql.*;
import java.util.ArrayList;
import org.wolf.baseclasses.Staff;
import org.wolf.config.DatabaseConnection;


public class StaffCRUD {
	
	//Returns the  information for the staff with given Id
	public static Staff viewStaff(Integer id){
		try {
			Connection conn = DatabaseConnection.getConnection();
		    Statement st = conn.createStatement();

		    ResultSet rs = st.executeQuery("SELECT * FROM staff WHERE staff_id =" + id);
		    Staff s = new Staff();
		    while(rs.next()) {
		    	s.setAddress(rs.getString("address"));
		    	s.setDept(rs.getString("dept"));
				s.setDob(rs.getString("date_of_birth"));
				s.setGender(rs.getString("gender"));
				s.setId(rs.getInt("staff_id"));
				s.setName(rs.getString("name"));
				s.setPno(rs.getString("phone_no"));
				s.setProfTitle(rs.getString("prof_title"));
				s.setSal(rs.getDouble("salary"));
				s.setJobTitle(rs.getString("job_title"));
		    }
		    return s;
	    }
	    catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	    	return null;
	    }
	}
	
	//Returns information of all staff members
	public static Staff viewStaff(){
		try {
			Connection conn = DatabaseConnection.getConnection();
		    Statement st = conn.createStatement();

		    ResultSet rs = st.executeQuery("SELECT * FROM staff");
		    Staff s = new Staff();
		    while(rs.next()) {
		    	s.setAddress(rs.getString("address"));
		    	s.setDept(rs.getString("dept"));
				s.setDob(rs.getString("date_of_birth"));
				s.setGender(rs.getString("gender"));
				s.setId(rs.getInt("staff_id"));
				s.setName(rs.getString("name"));
				s.setPno(rs.getString("phone_no"));
				s.setProfTitle(rs.getString("prof_title"));
				s.setSal(rs.getDouble("salary"));
				s.setJobTitle(rs.getString("job_title"));
		    }
		    return s;
	    }
	    catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	    	return null;
	    }
	}
		
	//Returns list of staff info searched by given name
	// String Matching LIKE query 
	public static ArrayList<Staff> getStaffByName(String name){
		
		try {
			Connection conn = DatabaseConnection.getConnection();
		    Statement st = conn.createStatement();

		    ResultSet rs = st.executeQuery("SELECT * FROM Staff WHERE name =" + name);
		    
		    ArrayList <Staff> staffList = new ArrayList <> ();
		    while(rs.next()) {
			    Staff s = new Staff();
			    s.setAddress(rs.getString("address"));
		    	s.setDept(rs.getString("dept"));
				s.setDob(rs.getString("date_of_birth"));
				s.setGender(rs.getString("gender"));
				s.setId(rs.getInt("staff_id"));
				s.setName(rs.getString("name"));
				s.setPno(rs.getString("phone_no"));
				s.setProfTitle(rs.getString("prof_title"));
				s.setSal(rs.getDouble("salary"));
				s.setJobTitle(rs.getString("job_title"));
				staffList.add(s);
		    }
		    return staffList;
	    }
	    catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	    	return null;
	    }	
	}
	
	//Returns list of staff info with given professional title
	public static ArrayList<Staff> getStaffIdprofTitle(String profTitle){
		try {
			Connection conn = DatabaseConnection.getConnection();
		    Statement st = conn.createStatement();

		    ResultSet rs = st.executeQuery("SELECT * FROM Staff WHERE prof_title =" + profTitle);
		    
		    ArrayList <Staff> staffList = new ArrayList <> ();
		    while(rs.next()) {
			    Staff s = new Staff();
			    s.setAddress(rs.getString("address"));
		    	s.setDept(rs.getString("dept"));
				s.setDob(rs.getString("date_of_birth"));
				s.setGender(rs.getString("gender"));
				s.setId(rs.getInt("staff_id"));
				s.setName(rs.getString("name"));
				s.setPno(rs.getString("phone_no"));
				s.setProfTitle(rs.getString("prof_title"));
				s.setSal(rs.getDouble("salary"));
				s.setJobTitle(rs.getString("job_title"));
				staffList.add(s);
		    }
		    return staffList;
	    }
	    catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	    	return null;
	    }	
	}
	
	//Returns list of staff info in given dept
	public static ArrayList<Staff> getStaffIddept(String dept){
		try {
			Connection conn = DatabaseConnection.getConnection();
		    Statement st = conn.createStatement();

		    ResultSet rs = st.executeQuery("SELECT * FROM Staff WHERE dept =" + dept);
		    
		    ArrayList <Staff> staffList = new ArrayList <> ();
		    while(rs.next()) {
			    Staff s = new Staff();
			    s.setAddress(rs.getString("address"));
		    	s.setDept(rs.getString("dept"));
				s.setDob(rs.getString("date_of_birth"));
				s.setGender(rs.getString("gender"));
				s.setId(rs.getInt("staff_id"));
				s.setName(rs.getString("name"));
				s.setPno(rs.getString("phone_no"));
				s.setProfTitle(rs.getString("prof_title"));
				s.setSal(rs.getDouble("salary"));
				s.setJobTitle(rs.getString("job_title"));
				staffList.add(s);
		    }
		    return staffList;
	    }
	    catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	    	return null;
	    }	
	}
	
	//Returns the newly created staff Id
	//If fails returns null
	public static Integer insertStaff(String name, String jobTitle, String profTitle, String dob, String gender, String pno, String address, String dept, Double sal){
		try {
			Connection conn = DatabaseConnection.getConnection();
			
		    Statement st = conn.createStatement();
		    st.executeUpdate("INSERT INTO Staff(name, job_title, prof_title, date_of_birth, gender, phone_no, address, dept, salary) " +
		                       "VALUES ('"+ name +"', '"+ jobTitle +"', '"+ profTitle +"', '"+ dob +"', '"+ gender +"', '"+ pno +"', '"+ address +"', '"+ dept +"', '"+ sal +"')");
		    
		    ResultSet rs = st.executeQuery("SELECT staff_id FROM staff ORDER BY staff_id DESC LIMIT  1");
		    int staff_id = 0;
		    while(rs.next()) {
		    	staff_id = rs.getInt("staff_id");
		    }
		    return staff_id;
	    }
	    catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	    	return null;
	    }
	}
	
	//Returns true if the update was successful else false
	//Updates the record with this new information using the ID.
	public static Boolean updateStaff(Integer id, String name, String jobTitle, String profTitle, String dob, String gender, String pno, String address, String dept, Double sal){
		try {
			Connection conn = DatabaseConnection.getConnection();
			
		    Statement st = conn.createStatement();
		    st.executeUpdate("UPDATE Staff SET name = '"+ name +"', job_title = '"+ jobTitle +"', prof_title = '"+ profTitle +"', date_of_birth = '"+ dob 
		    					+"', gender = '"+ gender +"', phone_no = '"+ pno +"', address = '"+ address +"', dept = '"+ dept +"', salary = '"+ sal +"' WHERE staff_id = " + id);
		    
		    return true;
	    }
	    catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	    	return false;
	    }
	}
	
	// Deletes the staff information  using the staff Id
	//Returns true if successful else false
	public static Boolean deleteStaff(Integer id){
		try {
			Connection conn = DatabaseConnection.getConnection();
			
		    Statement st = conn.createStatement();
		    st.executeUpdate("DELETE FROM Staff WHERE staff_id = " + id );
		    return true;
	    }
	    catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	    	return false;
	    }
	}
}
