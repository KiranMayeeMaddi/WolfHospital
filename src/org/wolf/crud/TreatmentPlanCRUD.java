package org.wolf.crud;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.wolf.baseclasses.TreatmentPlan;
import org.wolf.config.DatabaseConnection;

/**
 * This class has all the functions related to the TreatmentPlan table. 
 * Functionalities include: 
 * 1. Viewing the TreatmentPlan table
 * 2. Viewing the TreatmentPlan table with respect to a given Treatment Id 
 * 3. Inserting values into the table
 * 4. Updating the already existing records
 * 5. Deleting the existing record
 */
public class TreatmentPlanCRUD {

	/**
	 * getAllTreatmentPlans function displays all the records of the treatment plans available
	 * It displays details of id, plan
	 * @return all the treatment plans
	 */
	
	public static ArrayList<TreatmentPlan> getAllTreatmentPlans(){
		try {
			Connection conn = DatabaseConnection.getConnection();
		    Statement st = conn.createStatement();

		    ResultSet rs = st.executeQuery("SELECT * FROM TreatmentPlan");
		    ArrayList<TreatmentPlan> res = new ArrayList<TreatmentPlan>();
		    
		    while(rs.next()) {
		    	TreatmentPlan t = new TreatmentPlan();
				t.setId(rs.getInt("id"));
				t.setPlan(rs.getString("plan"));
				res.add(t);
		    }
		    return res;
	    }
	    catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	    	return null;
	    }
	}//getAllTreatmentPlans
	
	
	
	/**
	 * getAllTreatmentPlan(id) function displays all the records of the treatment plans with respect to id
	 * It displays details of id, plan
	 * @param id
	 * @return treatmentPlan
	 */
	
	public static TreatmentPlan getTreatmentPlan(Integer id){
		try {
			Connection conn = DatabaseConnection.getConnection();
		    Statement st = conn.createStatement();

		    ResultSet rs = st.executeQuery("SELECT * FROM TreatmentPlan WHERE id =" + id);
		    TreatmentPlan t = new TreatmentPlan();
		    while(rs.next()) {
				t.setId(rs.getInt("id"));
				t.setPlan(rs.getString("plan"));
		    }
		    return t;
	    }
	    catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	    	return null;
	    }
	}//getAllTreatmentPlans with treatment id
	
	
	
	/**
	 * insertTreatmentPlan function allows to insert record to the Treatment table
	 * @param plan
	 * @return id
	 */
	
	public static Integer insertTreatmentPlan(String plan){
		try {
			Connection conn = DatabaseConnection.getConnection();
		    Statement st = conn.createStatement();
		    
		    ResultSet rs = st.executeQuery("SELECT MAX(id) as treatmentId FROM TreatmentPlan");
		    int id = 0;
		    if(rs.next()) {
		    	id = rs.getInt("treatmentId");
		    }
		    st.executeUpdate("INSERT INTO TreatmentPlan(id, plan) VALUES (" + (++id) + ", '"+ plan +"')");
		    return id;
	    }
	    catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	    	return null;
	    }
	}//insertTreatmentPlan
	
	
	
	/**
	 * updateTreatmentPlan function allows to update any value in the available record
	 * @param id
	 * @param plan
	 * @return status of update
	 */
	
	public static Boolean updateTreatmentPlan(Integer id, String plan){
		try {
			Connection conn = DatabaseConnection.getConnection();
		    Statement st = conn.createStatement();
		   
		    st.executeUpdate("UPDATE TreatmentPlan SET plan = '"+ plan +"' WHERE id = " + id);
		    
		    return true;
	    }
	    catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	    	return false;
	    }
	}//updateTreatmentPlan
	
	
	
	/**
	 * deleteTreatmentPlan function allows to delete the record with respect to id
	 * @param id
	 * @return status of deletion
	 */
	
	public static Boolean deleteTreatmentPlan(Integer id){
		try {
			Connection conn = DatabaseConnection.getConnection();
			
		    Statement st = conn.createStatement();
		    st.executeUpdate("DELETE FROM TreatmentPlan WHERE id = " + id );
		   
		    return true;
	    }
	    catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	    	return false;
	    }
	}//deleteTreatmentPlan
	
}
