package org.wolf.crud;

import java.sql.Connection;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.wolf.baseclasses.Payments;
import java.time.LocalDateTime;
import org.wolf.config.DatabaseConnection;


public final class PaymentsCRUD {

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
	
	public static ArrayList<Payments> getPaymentsForBill(Integer billId){
		try {
			Connection conn = DatabaseConnection.getConnection();
		    Statement st = conn.createStatement();

		    ResultSet rs = st.executeQuery("SELECT * FROM Payments where billId =" + billId);

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
	
	public static Integer insertPayment(Integer bill_id, String payer_ssn, String bill_address, String policy_no, String card_no, Double amountPaid){
		//Put endDate as current  time stamp
		try {
			Connection conn = DatabaseConnection.getConnection();
			
		    Statement st = conn.createStatement();
		    //LocalDateTime paymentDate = java.time.LocalDateTime.now();
		    
		    st.executeUpdate("INSERT INTO Patient(bill_id, payer_ssn, billing_address, policy_no, card_no, amount_paid, payment_date) " +
		                       "VALUES ('"+ bill_id +"', '"+ payer_ssn+"', '"+ bill_address +"', '"+ policy_no +"', '"+ card_no +"', '"+amountPaid+"',  NOW())");
		    ResultSet rs = st.executeQuery("SELECT payment_id FROM Payments ORDER BY payment_id DESC LIMIT  1");
		    int payment_id = 0;
		    while(rs.next()) {
		    	payment_id = rs.getInt("patient_id");
		    }
		    
		    return payment_id;
	    }
	    catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	    	return null;
	    }
	}
	
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
