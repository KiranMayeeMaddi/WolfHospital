package org.wolf.baseclasses;

public class PatientInfo {
	
	public Integer id;
	public String name;
	public String ssn;
	public String dob;
	public String gender;
	public String pno;
	public String address;
	public String process_treatment_plan;
	public String in_ward;
	public String completing_treatment;
	
	public PatientInfo(Integer id, String name, String ssn, String dob, String gender, String pno, String address,
			String process_treatment_plan, String in_ward, String completing_treatment) {
		this.id = id;
		this.name = name;
		this.ssn = ssn;
		this.dob = dob;
		this.gender = gender;
		this.pno = pno;
		this.address = address;
		this.process_treatment_plan = process_treatment_plan;
		this.in_ward = in_ward;
		this.completing_treatment = completing_treatment;
	}
	@Override
	public String toString() {
		return "PatientInfo [id=" + id + ", name=" + name + ", ssn=" + ssn + ", dob=" + dob + ", gender=" + gender
				+ ", pno=" + pno + ", address=" + address + ", process_treatment_paln=" + process_treatment_plan
				+ ", in_ward=" + in_ward + ", completing_treatment=" + completing_treatment + "]";
	}
}

