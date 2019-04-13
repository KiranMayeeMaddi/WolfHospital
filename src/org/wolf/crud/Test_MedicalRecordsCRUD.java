package org.wolf.crud;

import java.sql.*;
import java.util.ArrayList;

import org.wolf.baseclasses.Test_MedicalRecords;
import org.wolf.config.DatabaseConnection;

/**
 * This class is for handling CRUD operations for Test_MedicalRecords table.
 *
 */
public final class Test_MedicalRecordsCRUD {
	
	/**
	 * This function is for obtaining the test results for a given record_id and test_id
	 * 
	 * @param record_id
	 * @param test_id
	 * @return - Test_MedicalRecords object
	 */
	public static Test_MedicalRecords viewTestMedicalRecords(Integer record_id, Integer test_id){
		try {
			Connection conn = DatabaseConnection.getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("select * from Test_MedicalRecords where test_id="+test_id+" and record_id="+record_id);
			Test_MedicalRecords t = new Test_MedicalRecords();
			
			while (rs.next()) {
				t.setRecord_id(rs.getInt("record_id"));
				t.setResult(rs.getString("result"));
				t.setTest_date(rs.getString("datetime"));
				t.setTest_id(rs.getInt("test_id"));
			}
			return t;
		}
		catch (SQLException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}
	
	/**
	 * This function is for obtaining all the records of test performed for a given record_id
	 * 
	 * @param record_id
	 * @return - ArrayList of Test_MedicalRecords objects
	 */
	public static ArrayList<Test_MedicalRecords> viewTestMedicalRecordsByRecordId(Integer record_id){
		try {
			Connection conn = DatabaseConnection.getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("select * from Test_MedicalRecords where record_id="+record_id);
			ArrayList<Test_MedicalRecords> tests = new ArrayList<Test_MedicalRecords>();
			
			while (rs.next()) {
				Test_MedicalRecords t = new Test_MedicalRecords();
				
				t.setRecord_id(rs.getInt("record_id"));
				t.setResult(rs.getString("result"));
				t.setTest_date(rs.getString("datetime"));
				t.setTest_id(rs.getInt("test_id"));
				
				tests.add(t);
			}
			return tests;
		}
		catch (SQLException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}
	
	/**
	 * This function is for getting the results of all the medical records for which a particular test was performed
	 * 
	 * @param test_id
	 * @return - ArrayList of Test_MedicalRecords objects
	 */
	public static ArrayList<Test_MedicalRecords> viewTestMedicalRecordsByTestId(Integer test_id){
		try {
			Connection conn = DatabaseConnection.getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("select * from Test_MedicalRecords where test_id="+test_id);
			ArrayList<Test_MedicalRecords> tests = new ArrayList<Test_MedicalRecords>();
			
			while (rs.next()) {
				Test_MedicalRecords t = new Test_MedicalRecords();
				
				t.setRecord_id(rs.getInt("record_id"));
				t.setResult(rs.getString("result"));
				t.setTest_date(rs.getString("datetime"));
				t.setTest_id(rs.getInt("test_id"));
				
				tests.add(t);
			}
			return tests;
		}
		catch (SQLException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}
	
	/**
	 * This function is for getting all the records of all the tests performed for all patients
	 * 
	 * @return - ArrayList of Test_MedicalRecords objects
	 */
	public static ArrayList<Test_MedicalRecords> viewTestMedicalRecords(){
		try {
			Connection conn = DatabaseConnection.getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("select * from Test_MedicalRecords");
			ArrayList<Test_MedicalRecords> tests = new ArrayList<Test_MedicalRecords>();
			
			while (rs.next()) {
				Test_MedicalRecords t = new Test_MedicalRecords();
				
				t.setRecord_id(rs.getInt("record_id"));
				t.setResult(rs.getString("result"));
				t.setTest_date(rs.getString("datetime"));
				t.setTest_id(rs.getInt("test_id"));
				
				tests.add(t);
			}
			return tests;
		}
		catch (SQLException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}
	
	/**
	 * This function inserts a new record for a test performed, in the Test_MedicalRecords table
	 * 
	 * @param record_id
	 * @param test_id
	 * @param test_date
	 * @param result
	 * @return - true if insert successful, else false 
	 */
	public static Boolean insertTest_MedicalRecords(Integer record_id, Integer test_id, String test_date, String result){
		try {
			Connection conn = DatabaseConnection.getConnection();
			
			String query = "insert into Test_MedicalRecords (record_id, test_id, datetime, result)"
					+ " values (?,?,?,?)";
			PreparedStatement st = conn.prepareStatement(query);
			
			st.setInt(1, record_id);
			st.setInt(2, test_id);
			st.setString(3, test_date);
			st.setString(4, result);
		    st.executeUpdate();
		    
		    return true;
	    }
	    catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	    	return false;
	    }
	}
	
	/**
	 * This function is for updating any field in the Test_MedicalRecords table for a given record_id, test_id pair 
	 * 
	 * @param record_id
	 * @param test_id
	 * @param test_date
	 * @param result
	 * @return - true if update successful, else false
	 */
	public static Boolean updateTest_MedicalRecords(Integer record_id, Integer test_id, String test_date, String result){
		try {
			Connection conn = DatabaseConnection.getConnection();
			String query = "UPDATE Test_MedicalRecords SET datetime=?, result=? WHERE record_id=? and test_id=?";
		    PreparedStatement st = conn.prepareStatement(query);
		    st.setString(1, test_date);
		    st.setString(2, result);
		    st.setInt(3, record_id);
		    st.setInt(4, test_id);
		    st.executeUpdate();
		    
		    return true;
	    }
	    catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	    	return false;
	    }
	}
	
	/**
	 * This function deletes a record from the Test_MedicalRecords table for a given reocrd_id, test_id pair
	 * 
	 * @param record_id
	 * @param test_id
	 * @return - true if delete successful, else false
	 */
	public static Boolean deleteTest_MedicalRecords(Integer record_id, Integer test_id){
		try {
			Connection conn = DatabaseConnection.getConnection();
			
		    Statement st = conn.createStatement();
		    st.executeUpdate("DELETE FROM Test_MedicalRecords WHERE test_id=" + test_id + " and record_id="+record_id);
		    
		    return true;
	    }
	    catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	    	return false;
	    }
	}
}
