package org.wolf.baseclasses;

public class BillingAccount {
	public Integer bill_id;
	public Integer patient_id;
	public Integer record_id;
	public String payment_status;
	public Double reg_fee;
	public Double accom_fee;
	public Double medical_fee;
	
	public Integer getBill_id() {
		return bill_id;
	}
	public void setBill_id(Integer bill_id) {
		this.bill_id = bill_id;
	}
	public Integer getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(Integer patient_id) {
		this.patient_id = patient_id;
	}
	public Integer getRecord_id() {
		return record_id;
	}
	public void setRecord_id(Integer record_id) {
		this.record_id = record_id;
	}
	public String getPayment_status() {
		return payment_status;
	}
	public void setPayment_status(String payment_status) {
		this.payment_status = payment_status;
	}
	public Double getReg_fee() {
		return reg_fee;
	}
	public void setReg_fee(Double reg_fee) {
		this.reg_fee = reg_fee;
	}
	public Double getMedical_fee() {
		return medical_fee;
	}
	public void setMedical_fee(Double medical_fee) {
		this.medical_fee = medical_fee;
	}
	
	public Double getAccom_fee() {
		return accom_fee;
	}
	public void setAccom_fee(Double accom_fee) {
		this.accom_fee = accom_fee;
	}
	
	@Override
	public String toString() {
		return "BillingAccount [bill_id=" + bill_id + ", patient_id=" + patient_id + ", record_id=" + record_id
				+ ", payment_status=" + payment_status + ", reg_fee=" + reg_fee + ", accom_fee=" + accom_fee
				+ ", medical_fee=" + medical_fee + "]";
	}
}
