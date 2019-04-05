package org.wolf.operations;

import java.util.ArrayList;

import org.wolf.baseclasses.BillingAccount;
import org.wolf.baseclasses.Payments;
import org.wolf.baseclasses.Test;
import org.wolf.baseclasses.Ward_Patient;
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
	public static Boolean checkInPatient(Integer patientId, Integer ward_id,Integer bed_id){
		String end_time = null;
		Ward_PatientCRUD.insertWardPatient(patientId, ward_id, bed_id, end_time);
		WardCRUD.occupyBed(ward_id, bed_id);
		return null;
	}
	
	public static Boolean checkoutPatient(Integer checkinId){
		//Get the checkInRecord
		Ward_Patient w = Ward_PatientCRUD.viewWardPatients(checkinId);
		//Update the checkoutTime in the record
		Ward_PatientCRUD.updateWardEndtime(checkinId);
		//Release the occupied Bed
		WardCRUD.releaseBed(w.ward_id, w.bed_id);
		//Get the latest unpaid bill for the patientId.
		BillingAccount bill = BillingAccountCRUD.getLatestUnpaidBill(w.patient_id);
		//Calculate the accommodation Fee for the time stayed
		Double accomFee = WardCRUD.calculateAccomCharges(checkinId);
		//Add it the calculated fee to the original
		Double b = (bill.accom_fee==null)?accomFee:(bill.accom_fee+accomFee);
		//Update the bill with this fee
		BillingAccountCRUD.updateBillingAccount(bill.bill_id, bill.patient_id, bill.record_id, bill.payment_status, bill.reg_fee, b,bill.medical_fee);
		return null;
	}
	
	public static Boolean createTreatment(Integer patient_id, String start_date, String diagnosis, String prescription, String responsible_doctor, Double reg_fee, Double medical_fee){
		String endDate = null;
		String payment_status = "N";
		Double accom_fee = null;
		Integer record_id = MedicalRecordCRUD.insertMedicalRecord(patient_id, start_date, endDate, diagnosis, prescription, responsible_doctor);
		BillingAccountCRUD.insertBillingAccount(patient_id, record_id, payment_status, reg_fee, accom_fee, medical_fee);
		return null;
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
	
	public static Boolean payCash(Integer billId, String payer_ssn, String bill_address, Double amountPaid){
		
		BillingAccount bill = BillingAccountCRUD.viewBillingAccountsByBill(billId);
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
		
		return null;
	}
	
	public static Boolean payInsurance(Integer billId, String payer_ssn, String bill_address, Double amountPaid, String policy_no){
		BillingAccount bill = BillingAccountCRUD.viewBillingAccountsByBill(billId);
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
		
		return null;
	}
	
	public static Boolean payCard(Integer billId, String payer_ssn, String bill_address, Double amountPaid, String card_no){
		BillingAccount bill = BillingAccountCRUD.viewBillingAccountsByBill(billId);
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
		
		return null;
	}
}