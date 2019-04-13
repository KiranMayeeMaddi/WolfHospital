package org.wolf.operations;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.wolf.baseclasses.BillingAccount;
import org.wolf.baseclasses.BillingAccountView;
import org.wolf.baseclasses.Payments;
import org.wolf.baseclasses.Test;
import org.wolf.baseclasses.Ward_Patient;
import org.wolf.config.DatabaseConnection;
import org.wolf.crud.BillingAccountCRUD;
import org.wolf.crud.MedicalRecordCRUD;
import org.wolf.crud.PaymentsCRUD;
import org.wolf.crud.TestCRUD;
import org.wolf.crud.Test_MedicalRecordsCRUD;
import org.wolf.crud.WardCRUD;
import org.wolf.crud.Ward_PatientCRUD;

public class Operations {

	//Return true if successful else false
	//Assuming the bed availability has been checked
	//Add the patient info in ward_patient checkin table
	//Update the availability of the Bed
	public static Boolean checkInPatient(Integer patientId, Integer ward_id,Integer bed_id) throws SQLException,Exception{
		Connection conn = null;
		try {
			// Get database connection
			conn = DatabaseConnection.getConnection();
			
			// set autoCommit to false to make sure no commit happens in case of SQLException
			conn.setAutoCommit(false);
			
			String end_time = "";
			Integer checkinId = Ward_PatientCRUD.insertWardPatient(patientId, end_time, ward_id, bed_id);
			WardCRUD.occupyBed(ward_id, bed_id);
			System.out.println("The new checkin Id is "+ checkinId);
			
			// If successfully performed the operation, commit the changes in the database.
			conn.commit();
			
			return true;
			
		} catch(SQLException ex) {
			// In case of any SQLException print the stack trace.
			ex.printStackTrace();
			if(conn != null) {
				try {
					// In case of any other SQLException than connection establishment, rollback the transaction.
	                System.err.print("Transaction is being rolled back");
	                conn.rollback();
	            } catch(SQLException excep) {
	                excep.printStackTrace();
	            }
			}
			return false;
		} finally {
			//Finally set the auto commit to true
			conn.setAutoCommit(true);
		}
	}
	
	public static Boolean checkoutPatient(Integer checkinId) throws SQLException, Exception{
		Connection conn = null;
		try {
			// Get database connection
			conn = DatabaseConnection.getConnection();
			
			// set autoCommit to false to make sure no commit happens in case of SQLException
			conn.setAutoCommit(false);
			
			//Get the checkInRecord
			Ward_Patient w = Ward_PatientCRUD.viewWardPatients(checkinId);
			//Update the checkoutTime in the record
			Ward_PatientCRUD.updateWardEndtime(checkinId);
			//Release the occupied Bed
			WardCRUD.releaseBed(w.ward_id, w.bed_id);
			//Get the latest unpaid bill for the patientId.
			BillingAccount bill = BillingAccountCRUD.internalGetLatestUnpaidBill(w.patient_id);
			//Calculate the accommodation Fee for the time stayed
			Double accomFee = WardCRUD.calculateAccomCharges(checkinId);
			//Add it the calculated fee to the original
			Double b = (bill.accom_fee==null)?accomFee:(bill.accom_fee+accomFee);
			//Update the bill with this fee
			BillingAccountCRUD.updateBillingAccount(bill.bill_id, bill.patient_id, bill.record_id, bill.payment_status, bill.reg_fee, b,bill.medical_fee);
			
			conn.commit();
			return true;
			
		} catch(SQLException ex) {
			
			// In case of any SQLException print the stack trace.
			ex.printStackTrace();
			
			if(conn != null) {
				try {
					// In case of any other SQLException than connection establishment, rollback the transaction.
	                System.err.print("Transaction is being rolled back");
	                conn.rollback();
	            } catch(SQLException excep) {
	                excep.printStackTrace();
	            }
			}
			return false;
		} finally {
			//Finally set the auto commit to true
			conn.setAutoCommit(true);
		}
	}
	
	/**
	 * This function creates a medical record and also creates a corresponding bill for it
	 * @param patient_id - The patient should exist from before
	 * @param start_date
	 * @param diagnosis
	 * @param prescription
	 * @param responsible_doctor
	 * @param process_treatment_plan
	 * @param reg_fee - initial registration fee
	 * @param medical_fee - initial medical  fee
	 * @return true when treatment is created successfully else false
	 */
	public static Boolean createTreatment(Integer patient_id, String start_date, String diagnosis, String prescription, Integer responsible_doctor, Integer process_treatment_plan, Double reg_fee, Double medical_fee){
		String endDate = null;
		String payment_status = "N";
		Double accom_fee = null;
		Integer record_id = MedicalRecordCRUD.insertMedicalRecord(patient_id, start_date, endDate, diagnosis, prescription, responsible_doctor,process_treatment_plan);
		BillingAccountCRUD.insertBillingAccount(patient_id, record_id, payment_status, reg_fee, accom_fee, medical_fee);
		return true;
	}
	
	public static Boolean addTestToTreatment(Integer recordId, Integer testId, String test_date, String result){
		Test_MedicalRecordsCRUD.insertTest_MedicalRecords(recordId, testId, test_date, result);
		Test t = TestCRUD.viewTest(testId);
		BillingAccountCRUD.updateMedicalFee(recordId, t.fees);
		return null;
	}
	
	public static Boolean endTreatment(Integer recordId, Double treatmentFee){
		//Add the treatment fee to the existing medical fee
		BillingAccountCRUD.updateMedicalFee(recordId, treatmentFee);
		MedicalRecordCRUD.updateMedicalRecordEndTime(recordId);
		return null;
	}
	
	public static Boolean payCash(Integer billId, String payer_ssn, String bill_address, Double amountPaid) {
		try {
		
			BillingAccountView bill = BillingAccountCRUD.viewBillingAccountsByBill(billId);
			PaymentsCRUD.insertPayment(billId, payer_ssn, bill_address, null,null, amountPaid);
			
			ArrayList<Payments> p = PaymentsCRUD.getPaymentsForBill(billId);
			Double paid = (double) 0;
			
			for (Payments payment:p){
				paid += payment.amountPaid;
			}
				
			Double amountDue = (bill.accom_fee+bill.reg_fee+bill.medical_fee - paid);
			
			//Bill completely Paid
			if(amountDue - amountPaid <= 0){
				BillingAccountCRUD.updateBillingAccount(billId, bill.patient_id, bill.record_id, "Y", bill.reg_fee, bill.accom_fee, bill.medical_fee);
			}
			
			return true;
			
		} catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	    	return false;
	    }
	}
	
	public static Boolean payInsurance(Integer billId, String payer_ssn, String bill_address, Double amountPaid, String policy_no) {
		try {
			BillingAccountView bill = BillingAccountCRUD.viewBillingAccountsByBill(billId);
			PaymentsCRUD.insertPayment(billId, payer_ssn, bill_address, policy_no,null, amountPaid);
			
			ArrayList<Payments> p = PaymentsCRUD.getPaymentsForBill(billId);
			Double paid = (double) 0;
			
			for (Payments payment:p){
				paid += payment.amountPaid;
			}
				
			Double amountDue = (bill.accom_fee+bill.reg_fee+bill.medical_fee - paid);
			
			//Bill completely Paid
			if(amountDue - amountPaid <= 0){
				BillingAccountCRUD.updateBillingAccount(billId, bill.patient_id, bill.record_id, "Y", bill.reg_fee, bill.accom_fee, bill.medical_fee);
			}
			
			return true;
			
		} catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	    	return false;
	    }
	}
	
	public static Boolean payCard(Integer billId, String payer_ssn, String bill_address, Double amountPaid, String card_no) {
		try {
			BillingAccountView bill = BillingAccountCRUD.viewBillingAccountsByBill(billId);
			PaymentsCRUD.insertPayment(billId, payer_ssn, bill_address, null,card_no, amountPaid);
			
			ArrayList<Payments> p = PaymentsCRUD.getPaymentsForBill(billId);
			Double paid = (double) 0;
			
			for (Payments payment:p){
				paid += payment.amountPaid;
			}
				
			Double amountDue = (bill.accom_fee+bill.reg_fee+bill.medical_fee - paid);
			
			//Bill completely Paid
			if(amountDue - amountPaid <= 0){
				BillingAccountCRUD.updateBillingAccount(billId, bill.patient_id, bill.record_id, "Y", bill.reg_fee, bill.accom_fee, bill.medical_fee);
			}
			
			return true;
			
		} catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	    	return false;
	    }
	}
}
