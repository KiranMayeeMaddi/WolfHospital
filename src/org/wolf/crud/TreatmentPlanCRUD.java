package org.wolf.crud;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.wolf.baseclasses.TreatmentPlan;
import org.wolf.config.DatabaseConnection;

public class TreatmentPlanCRUD {

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
	}
	
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
	}
	
	public static Integer insertTreatmentPlan(String plan){
		try {
			Connection conn = DatabaseConnection.getConnection();
		    Statement st = conn.createStatement();
		    
		    st.executeUpdate("INSERT INTO TreatmentPlan(plan) " +"VALUES ('"+ plan +"')");
		    
		    ResultSet rs = st.executeQuery("SELECT id FROM TreatmentPlan ORDER BY id DESC LIMIT  1");
		    int id = 0;
		    while(rs.next()) {
		    	id = rs.getInt("id");
		    }
		    return id;
	    }
	    catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	    	return null;
	    }
	}
	
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
	}
	
	public static Boolean updateTreatmentPlan(Integer id, String plan){
		try {
			Connection conn = DatabaseConnection.getConnection();
			
		    Statement st = conn.createStatement();
		    st.executeUpdate("UPDATE TreatmentPlan SET plan = '"+ plan + "WHERE id = " + id );
		    
		    return true;
	    }
	    catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	    	return false;
	    }
	}

}
