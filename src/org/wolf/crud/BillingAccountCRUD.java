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


/**
 * Basic Operations for the BillingAccount table such as viewing, inserting, updating and deleting.
 */
public final class BillingAccountCRUD {
	
	//Return All the bills
	/**
	 * @return information for all the billing accounts
	 */
	public static ArrayList<BillingAccountView> viewBillingAccounts(){
		try {
			Connection conn = DatabaseConnection.getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT BillingAccounts.record_id, Payments.payment_id,BillingAccounts.bill_id, MedicalRecords.patient_id, payer_ssn, billing_address, card_no, policy_no, reg_fee, accom_fee, medical_fee, start_date, prescription" + 
					" FROM MedicalRecords" + 
					" INNER JOIN BillingAccounts ON BillingAccounts.record_id = MedicalRecords.record_id" + 
					" LEFT OUTER JOIN (select p.bill_id,max(p.payment_id) as payment_id from Payments as p group by p.bill_id) as T ON T.bill_id = BillingAccounts.bill_id" + 
					" LEFT OUTER JOIN Payments on T.payment_id = Payments.payment_id");
			
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
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * @param billId
	 * @return Return bill for a  given  billId
	 */
	public static BillingAccountView viewBillingAccountsByBill(Integer billId){
		try {
			Connection conn = DatabaseConnection.getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT BillingAccounts.record_id, Payments.payment_id,BillingAccounts.bill_id, MedicalRecords.patient_id, payer_ssn, billing_address, card_no, policy_no, reg_fee, accom_fee, medical_fee, start_date, prescription" + 
					" FROM MedicalRecords" + 
					" INNER JOIN BillingAccounts ON BillingAccounts.record_id = MedicalRecords.record_id" + 
					" LEFT OUTER JOIN (select p.bill_id,max(p.payment_id) as payment_id from Payments as p group by p.bill_id) as T ON T.bill_id = BillingAccounts.bill_id" + 
					" LEFT OUTER JOIN Payments on T.payment_id = Payments.payment_id" + 
					" WHERE BillingAccounts.bill_id = " + billId);
			
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
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * @param recordId
	 * @return Return bill for a  given  recordId
	 */
	public static BillingAccountView viewBillingAccountsByRecord(Integer recordId){
		try {
			Connection conn = DatabaseConnection.getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT BillingAccounts.record_id, Payments.payment_id,BillingAccounts.bill_id, MedicalRecords.patient_id, payer_ssn, billing_address, card_no, policy_no, reg_fee, accom_fee, medical_fee, start_date, prescription" + 
					" FROM MedicalRecords" + 
					" INNER JOIN BillingAccounts ON BillingAccounts.record_id = MedicalRecords.record_id" + 
					" LEFT OUTER JOIN (select p.bill_id,max(p.payment_id) as payment_id from Payments as p group by p.bill_id) as T ON T.bill_id = BillingAccounts.bill_id" + 
					" LEFT OUTER JOIN Payments on T.payment_id = Payments.payment_id" +
					" WHERE MedicalRecords.record_id = "+ recordId);
			
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
			e.printStackTrace();
			return null;
		}	
	}
	
	/**
	 * @param patientId
	 * @return Returns all the bills for a given PatientId
	 */
	public static ArrayList<BillingAccountView> getBillingAccountsForPatient(Integer patientId){
		try {
			Connection conn = DatabaseConnection.getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT BillingAccounts.record_id, Payments.payment_id,BillingAccounts.bill_id, MedicalRecords.patient_id, payer_ssn, billing_address, card_no, policy_no, reg_fee, accom_fee, medical_fee, start_date, prescription" + 
					" FROM MedicalRecords" + 
					" INNER JOIN BillingAccounts ON BillingAccounts.record_id = MedicalRecords.record_id" + 
					" LEFT OUTER JOIN (select p.bill_id,max(p.payment_id) as payment_id from Payments as p group by p.bill_id) as T ON T.bill_id = BillingAccounts.bill_id" + 
					" LEFT OUTER JOIN Payments on T.payment_id = Payments.payment_id" +
					" WHERE MedicalRecords.patient_id = " + patientId);
			
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
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * @param patientId
	 * @return Returns all the bills for a given PatientId which are unpaid
	 */
	public static ArrayList<BillingAccountView> getUnpaidBillingAccountsForPatient(Integer patientId){
		try {
			Connection conn = DatabaseConnection.getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT BillingAccounts.record_id, Payments.payment_id,BillingAccounts.bill_id, MedicalRecords.patient_id, payer_ssn, billing_address, card_no, policy_no, reg_fee, accom_fee, medical_fee, start_date, prescription" + 
					" FROM MedicalRecords" + 
					" INNER JOIN BillingAccounts ON BillingAccounts.record_id = MedicalRecords.record_id" + 
					" LEFT OUTER JOIN (select p.bill_id,max(p.payment_id) as payment_id from Payments as p group by p.bill_id) as T ON T.bill_id = BillingAccounts.bill_id" + 
					" LEFT OUTER JOIN Payments on T.payment_id = Payments.payment_id" +
					" WHERE MedicalRecords.patient_id = "+patientId+" AND BillingAccounts.payment_status = 'N'");
			
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
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * @param patientId
	 * @return Get the latest unpaid bill for the given patient_id
	 * @throws SQLException
	 */
	public static BillingAccountView getLatestUnpaidBill(Integer patientId) throws SQLException {
		  
		Connection conn = DatabaseConnection.getConnection();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("SELECT BillingAccounts.record_id, Payments.payment_id,BillingAccounts.bill_id, MedicalRecords.patient_id, payer_ssn, billing_address, card_no, policy_no, reg_fee, accom_fee, medical_fee, start_date, prescription" + 
				" FROM MedicalRecords" + 
				" INNER JOIN BillingAccounts ON BillingAccounts.record_id = MedicalRecords.record_id" + 
				" LEFT OUTER JOIN (select p.bill_id,max(p.payment_id) as payment_id from Payments as p group by p.bill_id) as T ON T.bill_id = BillingAccounts.bill_id" + 
				" LEFT OUTER JOIN Payments on T.payment_id = Payments.payment_id" +
				" WHERE MedicalRecords.patient_id = "+patientId+" AND BillingAccounts.payment_status = 'N' ORDER BY BillingAccounts.bill_id DESC LIMIT 1");
		
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
	
	
		//Get the latest unpaid bill for the given patient_id
		public static BillingAccount internalGetLatestUnpaidBill(Integer patientId) throws SQLException {
			  
			Connection conn = DatabaseConnection.getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("select * from BillingAccounts where patient_id="+patientId
					+ " and payment_status='N' order by bill_id desc limit 1");
			BillingAccount b = null;
			
			while (rs.next()) {
				b = new BillingAccount();
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
	
	/**
	 * @param recordID
	 * @param fee
	 * @return Add fee to the current medical fee in BillingAccount table
	 * @throws SQLException 
	 */
	public static Boolean updateMedicalFee(Integer recordID, Double fee) throws SQLException{
			Connection conn = DatabaseConnection.getConnection();
			Statement ms = conn.createStatement();
			ResultSet rs = ms.executeQuery("Select medical_fee from BillingAccounts where record_id="+recordID);
			
			Double medical_fee = 0.0;
			while (rs.next())
				medical_fee = rs.getDouble("medical_fee");
			
			medical_fee += fee;
			
			String query = "update BillingAccounts set medical_fee=? where record_id=?"; 
		    PreparedStatement st = conn.prepareStatement(query);
		    st.setDouble(1, medical_fee);
		    st.setInt(2, recordID);
		    st.executeUpdate();
		    
		    return true;
	}
	
	/**
	 * Insert a bill based on the given data
	 * @param patient_id
	 * @param record_id
	 * @param payment_status
	 * @param reg_fee
	 * @param accom_fee
	 * @param medical_fee
	 * @return true if successful else false
	 * @throws SQLException 
	 */
	public static Boolean insertBillingAccount(Integer patient_id, Integer record_id,String payment_status, Double reg_fee, Double accom_fee, Double medical_fee) throws SQLException{
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
	
	/**
	 * Updates a medical bill with the given data
	 * @param billId
	 * @param patient_id
	 * @param record_id
	 * @param payment_status
	 * @param reg_fee
	 * @param accom_fee
	 * @param medical_fee
	 * @return true if successful else false
	 * @throws SQLException
	 */
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
	
	/**
	 * Deletes a medical bill with the given billId
	 * @param bill_id
	 * @return true if successful else false
	 */
	public static Boolean deleteBillingAccount(Integer bill_id){
		try {
			Connection conn = DatabaseConnection.getConnection();
			
		    Statement st = conn.createStatement();
		    st.executeUpdate("DELETE FROM BillingAccounts WHERE bill_id = " + bill_id);
		    
		    return true;
	    }
	    catch (SQLException ex) {
	    	ex.printStackTrace();
	    	return false;
	    }
	}
}
