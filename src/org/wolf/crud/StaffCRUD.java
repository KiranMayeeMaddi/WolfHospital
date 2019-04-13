package org.wolf.crud;

import java.sql.*;
import java.util.ArrayList;
import org.wolf.baseclasses.Staff;
import org.wolf.config.DatabaseConnection;


/**
 * This class is for handling CRUD operations for Staff table
 *
 */
public class StaffCRUD {
	
	//Returns the  information for the staff with given Id
	/**
	 * This function is for getting the staff record for a given record_id
	 * 
	 * @param id - record_id
	 * @return - Staff object
	 */
	public static Staff viewStaff(Integer id){
		try {
			Connection conn = DatabaseConnection.getConnection();
		    Statement st = conn.createStatement();

		    ResultSet rs = st.executeQuery("SELECT * FROM Staff WHERE staff_id =" + id);
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
	/**
	 * This function is for obtaining all the records in the Staff table
	 * 
	 * @return - ArrayList of Staff objects
	 */
	public static ArrayList<Staff> viewStaff(){
		try {
			Connection conn = DatabaseConnection.getConnection();
		    Statement st = conn.createStatement();

		    ResultSet rs = st.executeQuery("SELECT * FROM Staff");
		    ArrayList<Staff> sList = new ArrayList<Staff>();
		    
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
				sList.add(s);
		    }
		    return sList;
	    }
	    catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	    	return null;
	    }
	}
		
	//Returns list of staff info searched by given name
	// String Matching LIKE query 
	/**
	 * This function is for obtaining all Staff records that matches a given name
	 * 
	 * @param name
	 * @return - ArrayList of Staff objects
	 */
	public static ArrayList<Staff> getStaffByName(String name){
		
		try {
			Connection conn = DatabaseConnection.getConnection();
		    Statement st = conn.createStatement();

		    ResultSet rs = st.executeQuery("SELECT * FROM Staff WHERE LOWER(name) LIKE '%" + name.toLowerCase() + "%'");
		    
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
	/**
	 * This function is for getting all the Staff with a given professional title
	 * 
	 * @param profTitle
	 * @return - ArrayList of Staff objects
	 */
	public static ArrayList<Staff> getStaffIdprofTitle(String profTitle){
		try {
			Connection conn = DatabaseConnection.getConnection();
		    Statement st = conn.createStatement();
		    ResultSet rs = st.executeQuery("SELECT * FROM Staff WHERE LOWER(prof_title) ='" + profTitle.toLowerCase()+"'");
		    
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
	
	//Returns list of staff info with given job title
	/**
	 * This function is for getting all the Staff records for a given job tile
	 * 
	 * @param jobTitle
	 * @return - ArrayList of Staff objects
	 */
	public static ArrayList<Staff> getStaffIdjobTitle(String jobTitle){
		try {
			Connection conn = DatabaseConnection.getConnection();
		    Statement st = conn.createStatement();
		    ResultSet rs = st.executeQuery("SELECT * FROM Staff WHERE LOWER(job_title) ='"+jobTitle.toLowerCase()+"'" );
		    
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
	/**
	 * This function is for getting all the Staff records belonging to a department
	 * 
	 * @param dept
	 * @return - ArrayList of Staff objects
	 */
	public static ArrayList<Staff> getStaffIddept(String dept){
		try {
			Connection conn = DatabaseConnection.getConnection();
		    Statement st = conn.createStatement();

		    ResultSet rs = st.executeQuery("SELECT * FROM Staff WHERE LOWER(dept) ='" + dept.toLowerCase()+"'");
		    
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
	/**
	 * This function inserts a new Staff record in the Staff table
	 * The function also inserts staff_id in doctor, nurse or recptionist table based on job_title
	 * 
	 * @param name
	 * @param jobTitle
	 * @param profTitle
	 * @param dob
	 * @param gender
	 * @param pno
	 * @param address
	 * @param dept
	 * @param sal
	 * @return - Latest staff_id value after insert
	 */
	public static Integer insertStaff(String name, String jobTitle, String profTitle, String dob, String gender, String pno, String address, String dept, Double sal){
		try {
			Connection conn = DatabaseConnection.getConnection();
			
			String query = "insert into Staff (name, job_title, prof_title, date_of_birth, gender, phone_no, address, dept, salary)"
					+ " values (?,?,?,?,?,?,?,?,?)";
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, name);
			st.setString(2, jobTitle);
			if (profTitle.isEmpty())
				st.setNull(3, java.sql.Types.VARCHAR);
			else
				st.setString(3, profTitle);
			st.setString(4, dob);
			st.setString(5, gender);
			st.setString(6, pno);
			st.setString(7, address);
			st.setString(8, dept);
			st.setDouble(9, sal);
			
		    st.executeUpdate();
		    
		    ResultSet rs = st.executeQuery("select staff_id from Staff order by staff_id desc limit 1");
			int staff_id = 0;
		    while (rs.next())
		    	staff_id = rs.getInt("staff_id");
		    
		    if (jobTitle.equalsIgnoreCase("doctor"))
		    	st.executeUpdate("insert into Doctor (staff_id) values ("+staff_id+")");
		    else if (jobTitle.equalsIgnoreCase("nurse"))
		    	st.executeUpdate("insert into Nurse (staff_id) values ("+staff_id+")");
		    else 
		    	st.executeUpdate("insert into Receptionist (staff_id) values ("+staff_id+")");
		    
		    return staff_id;
	    }
	    catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	    	return null;
	    }
	}
	
	//Returns true if the update was successful else false
	//Updates the record with this new information using the ID.
	/**
	 * This function is for updating a Staff record's attribute(s) for a given staff_id
	 * If job_title is updated, then staff entry changes in doctor, nurse or receptionist table are also reflected
	 * 
	 * @param id - staff_id
	 * @param name
	 * @param jobTitle
	 * @param profTitle
	 * @param dob
	 * @param gender
	 * @param pno
	 * @param address
	 * @param dept
	 * @param sal
	 * @return - true if update successful, else false
	 */
	public static Boolean updateStaff(Integer id, String name, String jobTitle, String profTitle, String dob, String gender, String pno, String address, String dept, Double sal){
		try {
			Connection conn = DatabaseConnection.getConnection();
			
			Statement js = conn.createStatement();
			ResultSet rs = js.executeQuery("select job_title from Staff where staff_id="+id);
			String job_title_old = "";
			
			while (rs.next())
				job_title_old = rs.getString("job_title");
			
			// if job_title is updated
			if (!job_title_old.toLowerCase().equals(jobTitle)) { 
				
				// delete record from the doctor, nurse or receptionist table based on job_title_old
				if (job_title_old.equalsIgnoreCase("doctor"))
					js.executeUpdate("delete from Doctor where staff_id="+id);
				else if (job_title_old.equalsIgnoreCase("nurse"))
					js.executeUpdate("delete from Nurse where staff_id="+id);
				else
					js.executeUpdate("delete from Receptionist where staff_id="+id);
				
				// insert into doctor, nurse or receptionist table based on new jobTitle
				if (jobTitle.equalsIgnoreCase("doctor"))
					js.executeUpdate("insert into Doctor (staff_id) values ("+id+")");
				else if (jobTitle.equalsIgnoreCase("nurse"))
					js.executeUpdate("insert into Nurse (staff_id) values ("+id+")");
				else
					js.executeUpdate("insert into Receptionist (staff_id) values ("+id+")");
			}
			
			String query = "update Staff set name=?, job_title=?, prof_title=?, date_of_birth=?, gender=?, phone_no=?, address=?, dept=?, salary=?"
					+ " where staff_id=?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, name);
			st.setString(2, jobTitle);
			if (profTitle.isEmpty())
				st.setNull(3, java.sql.Types.VARCHAR);
			else
				st.setString(3, profTitle);
			st.setString(4, dob);
			st.setString(5, gender);
			st.setString(6, pno);
			st.setString(7, address);
			st.setString(8, dept);
			st.setDouble(9, sal);
			st.setInt(10, id);
			
		    st.executeUpdate();
		    
		    return true;
	    }
	    catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	    	return false;
	    }
	}
	
	// Deletes the staff information  using the staff Id
	//Returns true if successful else false
	/**
	 * This function delete a record from the Staff table for a given staff_id
	 * 
	 * @param id - staff_id
	 * @return - true if delete successful, else false
	 */
	public static Boolean deleteStaff(Integer id){
		try {
			Connection conn = DatabaseConnection.getConnection();
			
		    Statement st = conn.createStatement();
		    st.executeUpdate("DELETE FROM Staff WHERE staff_id = " + id);
		    return true;
	    }
	    catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	    	return false;
	    }
	}
}
