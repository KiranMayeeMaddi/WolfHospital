

package org.wolf.crud;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.wolf.baseclasses.Test;
import org.wolf.config.DatabaseConnection;

/**
 * This class has all the functions related to the Tests table. 
 * Functionalities include: 
 * 1. Viewing the Test table
 * 2. Viewing the Test table with respect to a given Test Id 
 * 3. Inserting values into the table
 * 4. Updating the already existing records
 * 5. Deleting the existing record
 */


public final class TestCRUD {
	
	/**
	 * viewTest function lists all the records of the tests available in different labs
	 * It displays details of test_id, test_name, lab_name, specialized_doctor, fees
	 * @return
	 */
	
	public static ArrayList<Test> viewTest(){
		try {
			Connection conn = DatabaseConnection.getConnection();
		    Statement st = conn.createStatement();

		    ResultSet rs = st.executeQuery("SELECT * FROM Test");
		    ArrayList <Test> testList = new ArrayList <> ();
		    
		    while(rs.next()) {
			    Test t = new Test();
			    t.setFees(rs.getDouble("fees"));
				t.setLabName(rs.getString("lab_name"));
				t.setSpecialized_doctor(rs.getInt("specialized_doctor"));
				t.setTest_Id(rs.getInt("test_id"));
				t.setTest_name(rs.getString("test_name"));
				testList.add(t);
		    }
		    
		    return testList;
	    } catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	    	return null;
	    }
	}//viewTest
	
	
	
	/**
	 * viewTest(test_id) function displays the details of the test with respect to the test_id
	 * It displays details of test_id, test_name, lab_name, specialized_doctor, fees
	 * @param test_id
	 * @return
	 */
	
	public static Test viewTest(Integer test_id){
		
		try {
			Connection conn = DatabaseConnection.getConnection();
		    Statement st = conn.createStatement();

		    ResultSet rs = st.executeQuery("SELECT * FROM Test WHERE test_id =" + test_id);
		    Test t = new Test();
		    
		    while(rs.next()) {
		    	t.setFees(rs.getDouble("fees"));
				t.setLabName(rs.getString("lab_name"));
				t.setSpecialized_doctor(rs.getInt("specialized_doctor"));
				t.setTest_Id(rs.getInt("test_id"));
				t.setTest_name(rs.getString("test_name"));
		    }
		    
		    return t;
	    } catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	    	return null;
	    }
	}//viewTest with test id
	
	
	
	/**
	 * insertTest function allows to insert record to the Test table
	 * @param test_name
	 * @param lab_name
	 * @param specialized_doctor
	 * @param fees
	 * @return
	 */
	
	public static Integer insertTest(String test_name, String lab_name, Integer specialized_doctor, Double fees){
		try {
			Connection conn = DatabaseConnection.getConnection();
		    Statement st = conn.createStatement();
		    
		    st.executeUpdate("INSERT INTO Test(test_name, lab_name, specialized_doctor, fees) " +
		                       "VALUES ('"+ test_name +"', '"+ lab_name +"', '"+ specialized_doctor +"', '"+ fees +"')");
		    
		    ResultSet rs = st.executeQuery("SELECT test_id FROM Test ORDER BY test_id DESC LIMIT  1");
		    int test_id = 0;
		    
		    while(rs.next()) {
		    	test_id = rs.getInt("test_id");
		    }
		    
		    return test_id;
	    } catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	    	return null;
	    }
	}//insertTest
	
	
	
	/**
	 * updateTest function allows to update any value in the available record
	 * @param test_id
	 * @param test_name
	 * @param lab_name
	 * @param specialized_doctor
	 * @param fees
	 * @return
	 */
	
	public static Boolean updateTest(Integer test_id, String test_name, String lab_name, Integer specialized_doctor, Double fees){
		try {
			Connection conn = DatabaseConnection.getConnection();
			
		    Statement st = conn.createStatement();
		    st.executeUpdate("UPDATE Test SET test_name = '"+ test_name +"', lab_name = '"+ lab_name +"', specialized_doctor = '"+ specialized_doctor +"', fees = '"+ fees +"' WHERE test_id = " + test_id );
		    
		    return true;
	    } catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	    	return false;
	    }
	}//updateTest
	
	
	
	/**
	 * deleteTest function allows to delete the record with respect to id
	 * @param test_id
	 * @return
	 */
	
	public static Boolean deleteTest(Integer test_id){
		try {
			Connection conn = DatabaseConnection.getConnection();
			
		    Statement st = conn.createStatement();
		    st.executeUpdate("DELETE FROM Test WHERE test_id = " + test_id );
		    
		    return true;
	    } catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	    	return false;
	    }
	}//deleteTest
	
}
