package org.wolf.baseclasses;

public class Bed {
	
	public Integer ward_id;
	public Integer bed_id;
	public Integer is_available;
	
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
	public Integer getIs_available() {
		return is_available;
	}
	public void setIs_available(Integer is_available) {
		this.is_available = is_available;
	}
	@Override
	public String toString() {
		return "Bed [ward_id=" + ward_id + ", bed_id=" + bed_id + ", is_available=" + is_available + "]";
	}
}
