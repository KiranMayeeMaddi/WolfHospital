package org.wolf.crud;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.wolf.baseclasses.Test;
import org.wolf.config.DatabaseConnection;

public final class TestCRUD {

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
				t.setTest_name(rs.getString("name"));
		    }
		    return t;
	    }
	    catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	    	return null;
	    }
	}
	
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
				t.setTest_name(rs.getString("name"));
				testList.add(t);
		    }
		    return testList;
	    }
	    catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	    	return null;
	    }
	}
	
	public static Integer insertTest(String test_name, String lab_name, Integer specialized_doctor, Double fees){
		try {
			Connection conn = DatabaseConnection.getConnection();
			
		    Statement st = conn.createStatement();
		    st.executeUpdate("INSERT INTO Test(name, lab_name, specialized_doctor, fees) " +
		                       "VALUES ('"+ test_name +"', '"+ lab_name +"', '"+ specialized_doctor +"', '"+ fees +"')");
		    
		    ResultSet rs = st.executeQuery("SELECT test_id FROM Test ORDER BY test_id DESC LIMIT  1");
		    int test_id = 0;
		    while(rs.next()) {
		    	test_id = rs.getInt("test_id");
		    }
		    return test_id;
	    }
	    catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	    	return null;
	    }
	}
	
	public static Boolean updateTest(Integer test_id, String test_name, String lab_name, Integer specialized_doctor, Double fees){
		try {
			Connection conn = DatabaseConnection.getConnection();
			
		    Statement st = conn.createStatement();
		    st.executeUpdate("UPDATE Test SET name = '"+ test_name +"', lab_name = '"+ lab_name +"', specialized_doctor = '"+ specialized_doctor +"', fees = '"+ fees +"' WHERE test_id = " + test_id );
		    
		    return true;
	    }
	    catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	    	return false;
	    }
	}
	
	public static Boolean deleteTest(Integer test_id){
		try {
			Connection conn = DatabaseConnection.getConnection();
			
		    Statement st = conn.createStatement();
		    st.executeUpdate("DELETE FROM Test WHERE test_id = " + test_id );
		    
		    return true;
	    }
	    catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	    	return false;
	    }
	}
}
