package org.wolf.crud;

import java.sql.*;
import java.util.ArrayList;

import org.wolf.baseclasses.BillingAccount;
import org.wolf.config.DatabaseConnection;

public final class BillingAccountCRUD {
	
	//Return All the bills
	public static ArrayList<BillingAccount> viewBillingAccounts(){
		try {
			Connection conn = DatabaseConnection.getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("select * from BillingAccounts");
			ArrayList<BillingAccount> bills = new ArrayList<BillingAccount>();
			
			while (rs.next()) {
				BillingAccount b = new BillingAccount();
				
				b.setAccom_fee(rs.getDouble("accom_fee"));
				b.setBill_id(rs.getInt("bill_id"));
				b.setMedical_fee(rs.getDouble("medical_fee"));
				b.setPatient_id(rs.getInt("patient_id"));
				b.setPayment_status(rs.getString("payment_status"));
				b.setRecord_id(rs.getInt("record_id"));
				b.setReg_fee(rs.getDouble("reg_fee"));
				
				bills.add(b);
			}
			return bills;
		}
		catch (SQLException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}
	
	//Return bill for a  given  billId
	public static BillingAccount viewBillingAccountsByBill(Integer billId){
		try {
			Connection conn = DatabaseConnection.getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("select * from BillingAccounts where bill_id="+billId);
			BillingAccount b = new BillingAccount();
			
			while (rs.next()) {
				b.setAccom_fee(rs.getDouble("accom_fee"));
				b.setBill_id(rs.getInt("bill_id"));
				b.setMedical_fee(rs.getDouble("medical_fee"));
				b.setPatient_id(rs.getInt("patient_id"));
				b.setPayment_status(rs.getString("payment_status"));
				b.setRecord_id(rs.getInt("record_id"));
				b.setReg_fee(rs.getDouble("reg_fee"));
			}
			return b;
		}
		catch (SQLException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}
	
	//Return bill for a given recordId
	public static BillingAccount viewBillingAccountsByRecord(Integer recordId){
		try {
			Connection conn = DatabaseConnection.getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("select * from BillingAccounts where record_id="+recordId);
			BillingAccount b = new BillingAccount();
			
			while (rs.next()) {
				b.setAccom_fee(rs.getDouble("accom_fee"));
				b.setBill_id(rs.getInt("bill_id"));
				b.setMedical_fee(rs.getDouble("medical_fee"));
				b.setPatient_id(rs.getInt("patient_id"));
				b.setPayment_status(rs.getString("payment_status"));
				b.setRecord_id(rs.getInt("record_id"));
				b.setReg_fee(rs.getDouble("reg_fee"));
			}
			return b;
		}
		catch (SQLException e) {
			System.err.println(e.getMessage());
			return null;
		}	
	}
	
	//Returns all the bills for a given PatientId
	public static ArrayList<BillingAccount> getBillingAccountsForPatient(Integer patientId){
		try {
			Connection conn = DatabaseConnection.getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("select * from BillingAccounts where patient_id="+patientId);
			ArrayList<BillingAccount> bills = new ArrayList<BillingAccount>();
			
			while (rs.next()) {
				BillingAccount b = new BillingAccount();
				
				b.setAccom_fee(rs.getDouble("accom_fee"));
				b.setBill_id(rs.getInt("bill_id"));
				b.setMedical_fee(rs.getDouble("medical_fee"));
				b.setPatient_id(rs.getInt("patient_id"));
				b.setPayment_status(rs.getString("payment_status"));
				b.setRecord_id(rs.getInt("record_id"));
				b.setReg_fee(rs.getDouble("reg_fee"));
				
				bills.add(b);
			}
			return bills;
		}
		catch (SQLException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}
	
	//Returns all the bills for a given PatientId which are unpaid
	public static ArrayList<BillingAccount> getUnpaidBillingAccountsForPatient(Integer patientId){
		try {
			Connection conn = DatabaseConnection.getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("select * from BillingAccounts where patient_id="+patientId
					+" and payment_status='N'");
			ArrayList<BillingAccount> bills = new ArrayList<BillingAccount>();
			
			while (rs.next()) {
				BillingAccount b = new BillingAccount();
				
				b.setAccom_fee(rs.getDouble("accom_fee"));
				b.setBill_id(rs.getInt("bill_id"));
				b.setMedical_fee(rs.getDouble("medical_fee"));
				b.setPatient_id(rs.getInt("patient_id"));
				b.setPayment_status(rs.getString("payment_status"));
				b.setRecord_id(rs.getInt("record_id"));
				b.setReg_fee(rs.getDouble("reg_fee"));
				
				bills.add(b);
			}
			return bills;
		}
		catch (SQLException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}
	
	//Get the latest unpaid bill for the given patient_id
	public static BillingAccount getLatestUnpaidBill(Integer patientId) throws SQLException {
		  
		Connection conn = DatabaseConnection.getConnection();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("select * from BillingAccounts where patient_id="+patientId
				+ " and payment_status='N' order by bill_id desc limit 1");
		BillingAccount b = new BillingAccount();
		
		while (rs.next()) {
			b.setAccom_fee(rs.getDouble("accom_fee"));
			b.setBill_id(rs.getInt("bill_id"));
			b.setMedical_fee(rs.getDouble("medical_fee"));
			b.setPatient_id(rs.getInt("patient_id"));
			b.setPayment_status(rs.getString("payment_status"));
			b.setRecord_id(rs.getInt("record_id"));
			b.setReg_fee(rs.getDouble("reg_fee"));
		}
		return b;
	}
	
	//Add fee to the current medical fee in BillingAccount table
	public static Boolean updateMedicalFee(Integer recordID, Double fee){
		try {
			Connection conn = DatabaseConnection.getConnection();
			
			String query = "update BillingAccounts set medical_fee=? where record_id=?"; 
		    PreparedStatement st = conn.prepareStatement(query);
		    st.setDouble(1, fee);
		    st.setInt(2, recordID);
		    st.executeUpdate();
		    
		    return true;
	    }
	    catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	    	return false;
	    }
	}
	
	//Insert a bill
	//Return true if successful else false
	public static Boolean insertBillingAccount(Integer patient_id, Integer record_id,String payment_status, Double reg_fee, Double accom_fee, Double medical_fee){
		try {
			Connection conn = DatabaseConnection.getConnection();
			
			String query = "insert into BillingAccounts (patient_id, record_id, payment_status, reg_fee, accom_fee, medical_fee)"
					+ " values (?,?,?,?,?,?)";
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, patient_id);
			st.setInt(2, record_id);
			st.setString(3, payment_status);
			st.setDouble(4, reg_fee);
			st.setDouble(5, accom_fee);
			st.setDouble(6, medical_fee);
			
		    st.executeUpdate();
		    return true;
	    }
	    catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	    	return false;
	    }
	}
	
	//Updates a medical bill with the given data
	//Return true if successful else false
	public static Boolean updateBillingAccount(Integer billId, Integer patient_id, Integer record_id,String payment_status, Double reg_fee, Double accom_fee, Double medical_fee) throws SQLException {
		
		Connection conn = DatabaseConnection.getConnection();
		String query = "UPDATE BillingAccounts SET patient_id=?, record_id=?, payment_status=?,"
				+ "reg_fee=?, accom_fee=?, medical_fee=? WHERE bill_id=?";
	    PreparedStatement st = conn.prepareStatement(query);
	    st.setInt(1, patient_id);
	    st.setInt(2, record_id);
	    st.setString(3, payment_status);
	    st.setDouble(4, reg_fee);
	    st.setDouble(5, accom_fee);
	    st.setDouble(6, medical_fee);
	    st.setInt(7, billId);
	    st.executeUpdate();
	    
	    return true;
	}
	
	//Deletes a medical bill with the given billId
	//Return true if successful else false
	public static Boolean deleteBillingAccount(Integer bill_id){
		try {
			Connection conn = DatabaseConnection.getConnection();
			
		    Statement st = conn.createStatement();
		    st.executeUpdate("DELETE FROM BillingAccounts WHERE bill_id = " + bill_id);
		    
		    return true;
	    }
	    catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	    	return false;
	    }
	}
}
