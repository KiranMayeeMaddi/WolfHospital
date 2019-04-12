package org.wolf.crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.wolf.baseclasses.BillingAccount;
import org.wolf.baseclasses.BillingAccountView;
import org.wolf.config.DatabaseConnection;


public final class BillingAccountCRUD {
	
	//Return All the bills
	public static ArrayList<BillingAccountView> viewBillingAccounts(){
		try {
			Connection conn = DatabaseConnection.getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT billingaccounts.record_id, payments.payment_id,billingaccounts.bill_id, medicalrecords.patient_id, payer_ssn, billing_address, card_no, policy_no, reg_fee, accom_fee, medical_fee, start_date, prescription" + 
					"FROM medicalrecords" + 
					"INNER JOIN billingaccounts ON billingaccounts.record_id = medicalrecords.record_id" + 
					"INNER JOIN (select p.bill_id,max(p.payment_id) as payment_id from payments as p group by p.bill_id) as T ON T.bill_id = billingaccounts.bill_id" + 
					"INNER JOIN payments on T.payment_id = payments.payment_id");
			
			ArrayList<BillingAccountView> bills = new ArrayList<BillingAccountView>();
			String payment_method = null;
			String prescribed_meds = "No";
			while (rs.next()) {
				
				if(rs.getString("card_no") != null) {
					payment_method = "Credit Card";
					
				} else if(rs.getString("policy_no") != null) {
					payment_method = "Insurance";
				} else {
					payment_method = "Check";
				}
				
				if(rs.getString("prescription") != null) {
					prescribed_meds = "Yes";
				}
				BillingAccountView b = new BillingAccountView(rs.getInt("bill_id"), rs.getInt("patient_id"), rs.getInt("record_id"), rs.getString("payer_ssn"), rs.getString("billing_address"),
						payment_method, rs.getString("card_no"), rs.getString("policy_no"), rs.getDouble("reg_fee"), rs.getDouble("accom_fee"),
						rs.getDouble("medical_fee"), rs.getString("start_date"), prescribed_meds);
				
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
	public static BillingAccountView viewBillingAccountsByBill(Integer billId){
		try {
			Connection conn = DatabaseConnection.getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT billingaccounts.record_id, payments.payment_id,billingaccounts.bill_id, medicalrecords.patient_id, payer_ssn, billing_address, card_no, policy_no, reg_fee, accom_fee, medical_fee, start_date, prescription" + 
					"FROM medicalrecords" + 
					"INNER JOIN billingaccounts ON billingaccounts.record_id = medicalrecords.record_id" + 
					"INNER JOIN (select p.bill_id,max(p.payment_id) as payment_id from payments as p group by p.bill_id) as T ON T.bill_id = billingaccounts.bill_id" + 
					"INNER JOIN payments on T.payment_id = payments.payment_id" + 
					"WHERE billingaccounts.bill_id = " + billId);
			
			String payment_method = null;
			String prescribed_meds = "No";
			BillingAccountView b = null;
			while (rs.next()) {
				
				if(rs.getString("card_no") != null) {
					payment_method = "Credit Card";
					
				} else if(rs.getString("policy_no") != null) {
					payment_method = "Insurance";
				} else {
					payment_method = "Check";
				}
				
				if(rs.getString("prescription") != null) {
					prescribed_meds = "Yes";
				}
				b = new BillingAccountView(rs.getInt("bill_id"), rs.getInt("patient_id"), rs.getInt("record_id"), rs.getString("payer_ssn"), rs.getString("billing_address"),
						payment_method, rs.getString("card_no"), rs.getString("policy_no"), rs.getDouble("reg_fee"), rs.getDouble("accom_fee"),
						rs.getDouble("medical_fee"), rs.getString("start_date"), prescribed_meds);
			}
			return b;
		}
		catch (SQLException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}
	
	//Return bill for a given recordId
	public static BillingAccountView viewBillingAccountsByRecord(Integer recordId){
		try {
			Connection conn = DatabaseConnection.getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT billingaccounts.record_id, payments.payment_id,billingaccounts.bill_id, medicalrecords.patient_id, payer_ssn, billing_address, card_no, policy_no, reg_fee, accom_fee, medical_fee, start_date, prescription" + 
					"FROM medicalrecords" + 
					"INNER JOIN billingaccounts ON billingaccounts.record_id = medicalrecords.record_id" + 
					"INNER JOIN (select p.bill_id,max(p.payment_id) as payment_id from payments as p group by p.bill_id) as T ON T.bill_id = billingaccounts.bill_id" + 
					"INNER JOIN payments on T.payment_id = payments.payment_id" + 
					"WHERE medicalrecords.record_id = "+ recordId);
			
			String payment_method = null;
			String prescribed_meds = "No";
			BillingAccountView b = null;
			while (rs.next()) {
				
				if(rs.getString("card_no") != null) {
					payment_method = "Credit Card";
					
				} else if(rs.getString("policy_no") != null) {
					payment_method = "Insurance";
				} else {
					payment_method = "Check";
				}
				
				if(rs.getString("prescription") != null) {
					prescribed_meds = "Yes";
				}
				b = new BillingAccountView(rs.getInt("bill_id"), rs.getInt("patient_id"), rs.getInt("record_id"), rs.getString("payer_ssn"), rs.getString("billing_address"),
						payment_method, rs.getString("card_no"), rs.getString("policy_no"), rs.getDouble("reg_fee"), rs.getDouble("accom_fee"),
						rs.getDouble("medical_fee"), rs.getString("start_date"), prescribed_meds);
			}
			return b;
		}
		catch (SQLException e) {
			System.err.println(e.getMessage());
			return null;
		}	
	}
	
	//Returns all the bills for a given PatientId
	public static ArrayList<BillingAccountView> getBillingAccountsForPatient(Integer patientId){
		try {
			Connection conn = DatabaseConnection.getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT billingaccounts.record_id, payments.payment_id,billingaccounts.bill_id, medicalrecords.patient_id, payer_ssn, billing_address, card_no, policy_no, reg_fee, accom_fee, medical_fee, start_date, prescription" + 
					"FROM medicalrecords" + 
					"INNER JOIN billingaccounts ON billingaccounts.record_id = medicalrecords.record_id" + 
					"INNER JOIN (select p.bill_id,max(p.payment_id) as payment_id from payments as p group by p.bill_id) as T ON T.bill_id = billingaccounts.bill_id" + 
					"INNER JOIN payments on T.payment_id = payments.payment_id" + 
					"WHERE medicalrecords.patient_id = " + patientId);
			
			ArrayList<BillingAccountView> bills = new ArrayList<BillingAccountView>();
			String payment_method = null;
			String prescribed_meds = "No";
			while (rs.next()) {
				
				if(rs.getString("card_no") != null) {
					payment_method = "Credit Card";
					
				} else if(rs.getString("policy_no") != null) {
					payment_method = "Insurance";
				} else {
					payment_method = "Check";
				}
				
				if(rs.getString("prescription") != null) {
					prescribed_meds = "Yes";
				}
				BillingAccountView b = new BillingAccountView(rs.getInt("bill_id"), rs.getInt("patient_id"), rs.getInt("record_id"), rs.getString("payer_ssn"), rs.getString("billing_address"),
						payment_method, rs.getString("card_no"), rs.getString("policy_no"), rs.getDouble("reg_fee"), rs.getDouble("accom_fee"),
						rs.getDouble("medical_fee"), rs.getString("start_date"), prescribed_meds);
				
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
	public static ArrayList<BillingAccountView> getUnpaidBillingAccountsForPatient(Integer patientId){
		try {
			Connection conn = DatabaseConnection.getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT billingaccounts.record_id, payments.payment_id,billingaccounts.bill_id, medicalrecords.patient_id, payer_ssn, billing_address, card_no, policy_no, reg_fee, accom_fee, medical_fee, start_date, prescription" + 
					"FROM medicalrecords" + 
					"INNER JOIN billingaccounts ON billingaccounts.record_id = medicalrecords.record_id" + 
					"INNER JOIN (select p.bill_id,max(p.payment_id) as payment_id from payments as p group by p.bill_id) as T ON T.bill_id = billingaccounts.bill_id" + 
					"INNER JOIN payments on T.payment_id = payments.payment_id" + 
					"WHERE medicalrecords.patient_id = "+patientId+" AND billingaccounts.payment_status = 'N'");
			
			ArrayList<BillingAccountView> bills = new ArrayList<BillingAccountView>();
			String payment_method = null;
			String prescribed_meds = "No";
			while (rs.next()) {
				
				if(rs.getString("card_no") != null) {
					payment_method = "Credit Card";
					
				} else if(rs.getString("policy_no") != null) {
					payment_method = "Insurance";
				} else {
					payment_method = "Check";
				}
				
				if(rs.getString("prescription") != null) {
					prescribed_meds = "Yes";
				}
				BillingAccountView b = new BillingAccountView(rs.getInt("bill_id"), rs.getInt("patient_id"), rs.getInt("record_id"), rs.getString("payer_ssn"), rs.getString("billing_address"),
						payment_method, rs.getString("card_no"), rs.getString("policy_no"), rs.getDouble("reg_fee"), rs.getDouble("accom_fee"),
						rs.getDouble("medical_fee"), rs.getString("start_date"), prescribed_meds);
				
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
	public static BillingAccountView getLatestUnpaidBill(Integer patientId) throws SQLException {
		  
		Connection conn = DatabaseConnection.getConnection();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("SELECT billingaccounts.record_id, payments.payment_id,billingaccounts.bill_id, medicalrecords.patient_id, payer_ssn, billing_address, card_no, policy_no, reg_fee, accom_fee, medical_fee, start_date, prescription" + 
				"FROM medicalrecords" + 
				"INNER JOIN billingaccounts ON billingaccounts.record_id = medicalrecords.record_id" + 
				"INNER JOIN (select p.bill_id,max(p.payment_id) as payment_id from payments as p group by p.bill_id) as T ON T.bill_id = billingaccounts.bill_id" + 
				"INNER JOIN payments on T.payment_id = payments.payment_id" + 
				"WHERE medicalrecords.patient_id = "+patientId+" AND billingaccounts.payment_status = 'N' ORDER BY billingaccounts.bill_id DESC LIMIT 1");
		
		String payment_method = null;
		String prescribed_meds = "No";
		BillingAccountView b = null;
		while (rs.next()) {
			
			if(rs.getString("card_no") != null) {
				payment_method = "Credit Card";
				
			} else if(rs.getString("policy_no") != null) {
				payment_method = "Insurance";
			} else {
				payment_method = "Check";
			}
			
			if(rs.getString("prescription") != null) {
				prescribed_meds = "Yes";
			}
			b = new BillingAccountView(rs.getInt("bill_id"), rs.getInt("patient_id"), rs.getInt("record_id"), rs.getString("payer_ssn"), rs.getString("billing_address"),
					payment_method, rs.getString("card_no"), rs.getString("policy_no"), rs.getDouble("reg_fee"), rs.getDouble("accom_fee"),
					rs.getDouble("medical_fee"), rs.getString("start_date"), prescribed_meds);
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
