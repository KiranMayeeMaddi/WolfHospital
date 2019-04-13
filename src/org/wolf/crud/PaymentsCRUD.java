package org.wolf.crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.wolf.baseclasses.Payments;
import java.time.LocalDateTime;
import org.wolf.config.DatabaseConnection;


/**
 * This class is for handling CRUD operations for Payments table
 *
 */
public final class PaymentsCRUD {
	
	
	/**
	 * This function is for obtaining all the payment records.
	 * 
	 * @return - ArrayList of Payments objects
	 */
	public static ArrayList<Payments> getAllPayments(){
		try {
			Connection conn = DatabaseConnection.getConnection();
		    Statement st = conn.createStatement();

		    ResultSet rs = st.executeQuery("SELECT * FROM Payments");

		    ArrayList <Payments> paymentList = new ArrayList <> ();
		    while(rs.next()) {
		    	Payments p = new Payments();
		    	p.setAmountPaid(rs.getDouble("amount_paid"));
				p.setBill_address(rs.getString("billing_address"));
				p.setBill_id(rs.getInt("bill_id"));
				p.setCard_no(rs.getString("card_no"));
				p.setPayer_ssn(rs.getString("payer_ssn"));
				p.setPayment_id(rs.getInt("payment_id"));
				p.setPaymentDate(rs.getString("payment_date"));
				p.setPolicy_no(rs.getString("policy_no"));
				paymentList.add(p);
		    }
		    return paymentList;
	    }
	    catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	    	return null;
	    }
	}
	
	/**
	 * This function is used for getting all the payment records generated (records of all the 
	 * payment methods utilized for payment of a bill) for a given bill_id. 
	 * 
	 * @param billId
	 * @return - ArrayList of Payments objects
	 */
	public static ArrayList<Payments> getPaymentsForBill(Integer billId){
		try {
			Connection conn = DatabaseConnection.getConnection();
		    Statement st = conn.createStatement();

		    ResultSet rs = st.executeQuery("SELECT * FROM Payments where bill_id =" + billId);

		    ArrayList <Payments> paymentList = new ArrayList <> ();
		    while(rs.next()) {
		    	Payments p = new Payments();
		    	p.setAmountPaid(rs.getDouble("amount_paid"));
				p.setBill_address(rs.getString("billing_address"));
				p.setBill_id(rs.getInt("bill_id"));
				p.setCard_no(rs.getString("card_no"));
				p.setPayer_ssn(rs.getString("payer_ssn"));
				p.setPayment_id(rs.getInt("payment_id"));
				p.setPaymentDate(rs.getString("payment_date"));
				p.setPolicy_no(rs.getString("policy_no"));
				paymentList.add(p);
		    }
		    return paymentList;
	    }
	    catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	    	return null;
	    }
	}
	
	/**
	 * This function is for getting the record for a given payment_id
	 * 
	 * @param paymentId
	 * @return - Payments object
	 */
	public static Payments getPaymentsForPayment(Integer paymentId){
		try {
			Connection conn = DatabaseConnection.getConnection();
		    Statement st = conn.createStatement();

		    ResultSet rs = st.executeQuery("SELECT * FROM Payments where payment_id =" + paymentId);
		    Payments p = new Payments();
		    
		    while(rs.next()) {
		    	p.setAmountPaid(rs.getDouble("amount_paid"));
				p.setBill_address(rs.getString("billing_address"));
				p.setBill_id(rs.getInt("bill_id"));
				p.setCard_no(rs.getString("card_no"));
				p.setPayer_ssn(rs.getString("payer_ssn"));
				p.setPayment_id(rs.getInt("payment_id"));
				p.setPaymentDate(rs.getString("payment_date"));
				p.setPolicy_no(rs.getString("policy_no"));
		    }
		    return p;
	    }
	    catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	    	return null;
	    }
	}
	
	/**
	 * This function is for inserting a new record in the Payments table
	 * 
	 * @param bill_id
	 * @param payer_ssn
	 * @param bill_address
	 * @param policy_no
	 * @param card_no
	 * @param amountPaid
	 * @return - payment_id of the latest generated payment record
	 */
	public static Integer insertPayment(Integer bill_id, String payer_ssn, String bill_address, String policy_no, String card_no, Double amountPaid){
		//Put endDate as current  time stamp
		try {
			Connection conn = DatabaseConnection.getConnection();
			
			String query = "insert into Payments (bill_id, payer_ssn, billing_address, policy_no, card_no, amount_paid, payment_date)"
					+ " values (?,?,?,?,?,?,NOW())";
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, bill_id);
			if (payer_ssn.isEmpty())
				st.setNull(2, java.sql.Types.VARCHAR);
			else 
				st.setString(2, payer_ssn);
			st.setString(3, bill_address);
			if (policy_no.isEmpty())
				st.setNull(4, java.sql.Types.VARCHAR);
			else
				st.setString(4, policy_no);
			if (card_no.isEmpty())
				st.setNull(5, java.sql.Types.VARCHAR);
			else
				st.setString(5, card_no);
			st.setDouble(6, amountPaid);
			
		    st.executeUpdate();
		    
		    ResultSet rs = st.executeQuery("select payment_id from Payments order by payment_id desc limit 1");
			int payment_id = 0;
		    while (rs.next())
		    	payment_id = rs.getInt("payment_id");
		    return payment_id;
	    }
	    catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	    	return null;
	    }
	}
	
	/**
	 * This function is for updating any values of a payment record for a given payment_id
	 * 
	 * @param payment_id
	 * @param bill_id
	 * @param payer_ssn
	 * @param billing_address
	 * @param policy_no
	 * @param card_no
	 * @param amount_paid
	 * @param payment_date
	 * @return - true if update successful, else false
	 */
	public static Boolean updatePayment(Integer payment_id, Integer bill_id, String payer_ssn, String billing_address, String policy_no, String card_no, Double amount_paid, String payment_date) {
		try {
			Connection conn = DatabaseConnection.getConnection();
			
			String query = "update Payments set bill_id=?, payer_ssn=?, billing_address=?, policy_no=?, card_no=?, amount_paid=?, payment_date=?"
					+ " where payment_id=?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, bill_id);
			if (payer_ssn.isEmpty())
				st.setNull(2, java.sql.Types.VARCHAR);
			else 
				st.setString(2, payer_ssn);
			st.setString(3, billing_address);
			if (policy_no.isEmpty())
				st.setNull(4, java.sql.Types.VARCHAR);
			else
				st.setString(4, policy_no);
			if (card_no.isEmpty())
				st.setNull(5, java.sql.Types.VARCHAR);
			else
				st.setString(5, card_no);
			st.setDouble(6, amount_paid);
			st.setString(7, payment_date);
			st.setInt(8, payment_id);
			
		    st.executeUpdate();
		    
		    return true;
	    }
	    catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	    	return false;
	    }
	}
	
	/**
	 * This function is for deleting a payment records for a given payment_id
	 * 
	 * @param payment_id
	 * @return - true if delete successful, else false
	 */
	public static Boolean deletePayment(Integer payment_id){
		try {
			Connection conn = DatabaseConnection.getConnection();
			
		    Statement st = conn.createStatement();
		    st.executeUpdate("DELETE FROM Payments WHERE payment_id = " + payment_id);
		    return true;
	    }
	    catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	    	return false;
	    }
	}
}
