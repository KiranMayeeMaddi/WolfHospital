package org.wolf.baseclasses;

public class MedicalRecord {
	
	public Integer record_id;
	public Integer patient_id;
	public String start_date;
	public String  end_date;
	public String diagnosis;
	public String prescription;
	public Integer responsible_doctor_id;
	public Integer process_treatment_plan;

	public MedicalRecord(Integer record_id, Integer patient_id, String start_date, String end_date, String diagnosis,
			String prescription, Integer responsible_doctor_id, Integer process_treatment_plan) {
		super();
		this.record_id = record_id;
		this.patient_id = patient_id;
		this.start_date = start_date;
		this.end_date = end_date;
		this.diagnosis = diagnosis;
		this.prescription = prescription;
		this.responsible_doctor_id = responsible_doctor_id;
		this.process_treatment_plan = process_treatment_plan;
	}
	
	public Integer getRecord_id() {
		return record_id;
	}
	public void setRecord_id(Integer record_id) {
		this.record_id = record_id;
	}
	public Integer getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(Integer patient_id) {
		this.patient_id = patient_id;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public String getDiagnosis() {
		return diagnosis;
	}
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
	public String getPrescription() {
		return prescription;
	}
	public void setPrescription(String prescription) {
		this.prescription = prescription;
	}
	public Integer getResponsible_doctor_id() {
		return responsible_doctor_id;
	}
	public void setResponsible_doctor_id(Integer responsible_doctor_id) {
		this.responsible_doctor_id = responsible_doctor_id;
	}
	public Integer getProcess_treatment_plan() {
		return process_treatment_plan;
	}

	public void setProcess_treatment_plan(Integer process_treatment_plan) {
		this.process_treatment_plan = process_treatment_plan;
	}
	@Override
	public String toString() {
		return "MedicalRecord [record_id=" + record_id + ", patient_id=" + patient_id + ", start_date=" + start_date
				+ ", end_date=" + end_date + ", diagnosis=" + diagnosis + ", prescription=" + prescription
				+ ", responsible_doctor_id=" + responsible_doctor_id + "]";
	}
}
