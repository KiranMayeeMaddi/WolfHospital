package org.wolf.crud;

import java.util.ArrayList;

import org.wolf.baseclasses.BillingAccount;


public final class BillingAccountCRUD {
	
	//Return All the bills
	public static ArrayList<BillingAccount> viewBillingAccounts(){
		return null;
	}
	
	//Return bill for a  given  billId
	public static BillingAccount viewBillingAccountsByBill(Integer billId){
		return null;	
	}
	
	//Return bill for a given recordId
	public static BillingAccount viewBillingAccountsByRecord(Integer recordId){
		return null;	
	}
	
	//Returns all the bills for a given PatientId
	public static ArrayList<BillingAccount> getBillingAccountsForPatient(Integer patientId){
		return null;
	}
	
	//Returns all the bills for a given PatientId which are unpaid
	public static ArrayList<BillingAccount> getUnpaidBillingAccountsForPatient(Integer patientId){
		return null;
	}
	
	//Get the latest unpaid bill for the given patient_id
	public static BillingAccount getLatestUnpaidBill(Integer patient_Id){
		return null;
	}
	
	//Add fee to the current medical fee in BillingAccount table
	public static Boolean updateMedicalFee(Integer recordID, Double fee){
		return null;
	}
	
	//Insert a bill
	//Return true if successful else false
	public static Boolean insertBillingAccount(Integer patient_id, Integer record_id,String payment_status, Double reg_fee, Double accom_fee, Double medical_fee){
		return null;
	}
	
	//Updates a medical bill with the given data
	//Return true if successful else false
	public static Boolean updateBillingAccount(Integer billId, Integer patient_id, Integer record_id,String payment_status, Double reg_fee, Double accom_fee, Double medical_fee){
		return null;
	}
	
	//Deletes a medical bill with the given billId
	//Return true if successful else false
	public static Boolean deleteBillingAccount(Integer bill_id){
		return null;
	}
}
