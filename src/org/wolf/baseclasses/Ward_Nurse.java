package org.wolf.baseclasses;

public class Ward_Nurse {
	public Integer shiftId;
	public Integer wardId;
	public Integer staffId;
	public String start_time;
	public String end_time;
	public Integer getShiftId() {
		return shiftId;
	}
	public void setShiftId(Integer shiftId) {
		this.shiftId = shiftId;
	}
	public Integer getWardId() {
		return wardId;
	}
	public void setWardId(Integer wardId) {
		this.wardId = wardId;
	}
	public Integer getStaffId() {
		return staffId;
	}
	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
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
		return "Ward_Nurse [shiftId=" + shiftId + ", wardId=" + wardId + ", staffId=" + staffId + ", start_time="
				+ start_time + ", end_time=" + end_time + "]";
	}
}
