package org.wolf.baseclasses;

public class Test {
	public Integer test_Id;
	public String test_name;
	public String labName;
	public Integer specialized_doctor;
	public Double fees;
	public Integer getTest_Id() {
		return test_Id;
	}
	public void setTest_Id(Integer test_Id) {
		this.test_Id = test_Id;
	}
	public String getTest_name() {
		return test_name;
	}
	public void setTest_name(String test_name) {
		this.test_name = test_name;
	}
	public String getLabName() {
		return labName;
	}
	public void setLabName(String labName) {
		this.labName = labName;
	}
	public Integer getSpecialized_doctor() {
		return specialized_doctor;
	}
	public void setSpecialized_doctor(Integer specialized_doctor) {
		this.specialized_doctor = specialized_doctor;
	}
	public Double getFees() {
		return fees;
	}
	public void setFees(Double fees) {
		this.fees = fees;
	}
	@Override
	public String toString() {
		return "Test [test_Id=" + test_Id + ", test_name=" + test_name + ", labName=" + labName
				+ ", specialized_doctor=" + specialized_doctor + ", fees=" + fees + "]";
	}
}
