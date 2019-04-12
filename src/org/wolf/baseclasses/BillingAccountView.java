package org.wolf.baseclasses;

public class BillingAccountView {
	public Integer bill_id;
	public Integer patient_id;
	public Integer record_id;
	public String ssn;
	public String billing_addr;
	public String payment_method;
	public String card_no;
	public String policy_no;
	public Double reg_fee;
	public Double accom_fee;
	public Double medical_fee;
	public String visit_date;
	public String prescribed_meds;
	
	public BillingAccountView(Integer bill_id, Integer patient_id, Integer record_id, String ssn, String billing_addr,
			String payment_method, String card_no, String policy_no, Double reg_fee, Double accom_fee,
			Double medical_fee, String visit_date, String prescribed_meds) {
		super();
		this.bill_id = bill_id;
		this.patient_id = patient_id;
		this.record_id = record_id;
		this.ssn = ssn;
		this.billing_addr = billing_addr;
		this.payment_method = payment_method;
		this.card_no = card_no;
		this.policy_no = policy_no;
		this.reg_fee = reg_fee;
		this.accom_fee = accom_fee;
		this.medical_fee = medical_fee;
		this.visit_date = visit_date;
		this.prescribed_meds = prescribed_meds;
	}

	@Override
	public String toString() {
		return "BillingAccountView [bill_id=" + bill_id + ", patient_id=" + patient_id + ", record_id=" + record_id
				+ ", ssn=" + ssn + ", billing_addr=" + billing_addr + ", payment_method=" + payment_method
				+ ", card_no=" + card_no + ", policy_no=" + policy_no + ", reg_fee=" + reg_fee + ", accom_fee="
				+ accom_fee + ", medical_fee=" + medical_fee + ", visit_date=" + visit_date + ", prescribed_meds="
				+ prescribed_meds + "]";
	}
	
	
}
