package org.wolf.baseclasses;

public class Ward_Patient {
	
	public Integer checkin_id;
	public Integer patient_id;
	public Integer ward_id;
	public Integer bed_id;
	public String start_time;
	public String end_time;
	public Integer getCheckin_id() {
		return checkin_id;
	}
	public void setCheckin_id(Integer checkin_id) {
		this.checkin_id = checkin_id;
	}
	public Integer getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(Integer patient_id) {
		this.patient_id = patient_id;
	}
	public Integer getWard_id() {
		return ward_id;
	}
	public void setWard_id(Integer ward_id) {
		this.ward_id = ward_id;
	}
	public Integer getBed_id() {
		return bed_id;
	}
	public void setBed_id(Integer bed_id) {
		this.bed_id = bed_id;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	@Override
	public String toString() {
		return "WardPatient [checkin_id=" + checkin_id + ", patient_id=" + patient_id + ", ward_id=" + ward_id
				+ ", bed_id=" + bed_id + ", start_time=" + start_time + ", end_time=" + end_time + "]";
	}

}
