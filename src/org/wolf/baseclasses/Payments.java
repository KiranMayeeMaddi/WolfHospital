package org.wolf.baseclasses;

public class Payments {
	public Integer payment_id;
	public Integer bill_id;
	public String payer_ssn;
	public String bill_address;
	public String policy_no;
	public String card_no;
	public Double amountPaid;
	public String paymentDate;
	public Integer getPayment_id() {
		return payment_id;
	}
	public void setPayment_id(Integer payment_id) {
		this.payment_id = payment_id;
	}
	public Integer getBill_id() {
		return bill_id;
	}
	public void setBill_id(Integer bill_id) {
		this.bill_id = bill_id;
	}
	public String getPayer_ssn() {
		return payer_ssn;
	}
	public void setPayer_ssn(String payer_ssn) {
		this.payer_ssn = payer_ssn;
	}
	public String getBill_address() {
		return bill_address;
	}
	public void setBill_address(String bill_address) {
		this.bill_address = bill_address;
	}
	public String getPolicy_no() {
		return policy_no;
	}
	public void setPolicy_no(String policy_no) {
		this.policy_no = policy_no;
	}
	public String getCard_no() {
		return card_no;
	}
	public void setCard_no(String card_no) {
		this.card_no = card_no;
	}
	public Double getAmountPaid() {
		return amountPaid;
	}
	public void setAmountPaid(Double amountPaid) {
		this.amountPaid = amountPaid;
	}
	public String getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}
	@Override
	public String toString() {
		return "Payments [payment_id=" + payment_id + ", bill_id=" + bill_id + ", payer_ssn=" + payer_ssn
				+ ", bill_address=" + bill_address + ", policy_no=" + policy_no + ", card_no=" + card_no
				+ ", amountPaid=" + amountPaid + ", paymentDate=" + paymentDate + "]";
	}
	
	
}
