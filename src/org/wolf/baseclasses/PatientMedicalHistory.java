package org.wolf.baseclasses;

public class PatientMedicalHistory {
	public Integer record_id;
	public Integer patient_id;
	public String name;
	public String start_date;
	public String  end_date;
	public String diagnosis;
	public String prescription;
	public Integer responsible_doctor_id;
	public String process_treatment_plan;
	public String test_name;
	public String test_date;
	public String result;
	public Integer ward_id;
	public Integer bed_id;
	public String start_time;
	public String end_time;
	
	
	public PatientMedicalHistory(Integer record_id, Integer patient_id, String name, String start_date, String end_date,
			String diagnosis, String prescription, Integer responsible_doctor_id, String process_treatment_plan,
			String test_name, String test_date, String result, Integer ward_id, Integer bed_id, String start_time,
			String end_time) {
		super();
		this.record_id = record_id;
		this.patient_id = patient_id;
		this.name = name;
		this.start_date = start_date;
		this.end_date = end_date;
		this.diagnosis = diagnosis;
		this.prescription = prescription;
		this.responsible_doctor_id = responsible_doctor_id;
		this.process_treatment_plan = process_treatment_plan;
		this.test_name = test_name;
		this.test_date = test_date;
		this.result = result;
		this.ward_id = ward_id;
		this.bed_id = bed_id;
		this.start_time = start_time;
		this.end_time = end_time;
	}


	@Override
	public String toString() {
		return "PatientMedicalHistory [record_id=" + record_id + ", patient_id=" + patient_id + ", name=" + name
				+ ", start_date=" + start_date + ", end_date=" + end_date + ", diagnosis=" + diagnosis
				+ ", prescription=" + prescription + ", responsible_doctor_id=" + responsible_doctor_id
				+ ", process_treatment_plan=" + process_treatment_plan + ", test_name=" + test_name + ", test_date="
				+ test_date + ", result=" + result + ", ward_id=" + ward_id + ", bed_id=" + bed_id + ", start_time="
				+ start_time + ", end_time=" + end_time + "]";
	}
		
}
