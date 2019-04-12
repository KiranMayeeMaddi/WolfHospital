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
	public static ArrayList<Staff> viewStaff(){
		try {
			Connection conn = DatabaseConnection.getConnection();
		    Statement st = conn.createStatement();

		    ResultSet rs = st.executeQuery("SELECT * FROM staff");
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
	public static ArrayList<Staff> getStaffIdprofTitle(String profTitle){
		try {
			Connection conn = DatabaseConnection.getConnection();
		    Statement st = conn.createStatement();
		    ResultSet rs = st.executeQuery("SELECT * FROM Staff WHERE prof_title ='" + profTitle+"'");
		    
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
		public static ArrayList<Staff> getStaffIdjobTitle(String jobTitle){
			try {
				Connection conn = DatabaseConnection.getConnection();
			    Statement st = conn.createStatement();
			    ResultSet rs = st.executeQuery("SELECT * FROM Staff WHERE job_title ='"+jobTitle+"'" );
			    
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
