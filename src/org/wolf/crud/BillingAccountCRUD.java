package org.wolf.crud;

import java.util.ArrayList;

import org.wolf.baseclasses.BillingAccount;


public class BillingAccountCRUD {
	
	//Return All the bills
	public ArrayList<BillingAccount> viewBillingAccounts(){
		return null;
	}
	
	//Return bill for a  given  billId
	public BillingAccount viewBillingAccounts(Integer billId){
		return null;	
	}
	
	//Returns all the bills for a given PatientId
	public ArrayList<BillingAccount> getBillingAccountsForPatient(Integer patientId){
		return null;
	}
	
	//Returns all the bills for a given PatientId which are unpaid
	public ArrayList<BillingAccount> getUnpaidBillingAccountsForPatient(Integer patientId){
		return null;
	}
	
	//Insert a bill
	//Return true if successful else false
	public Boolean insertBillingAccount(Integer patient_id, Integer record_id,String payment_status, Double reg_fee, Double accom_fee, Double medical_fee){
		return null;
	}
	
	//Updates a medical bill with the given data
	//Return true if successful else false
	public Boolean updateBillingAccount(Integer billId, Integer patient_id, Integer record_id,String payment_status, Double reg_fee, Double accom_fee, Double medical_fee){
		return null;
	}
	
	//Deletes a medical bill with the given billId
	//Return true if successful else false
	public Boolean deleteBillingAccount(Integer bill_id){
		return null;
	}
}
