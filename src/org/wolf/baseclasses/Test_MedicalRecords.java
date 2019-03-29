package org.wolf.baseclasses;

public class Test_MedicalRecords {
	
	public Integer record_id;
	public Integer test_id;
	public String test_date;
	public String result;
	
	public Integer getRecord_id() {
		return record_id;
	}
	public void setRecord_id(Integer record_id) {
		this.record_id = record_id;
	}
	public Integer getTest_id() {
		return test_id;
	}
	public void setTest_id(Integer test_id) {
		this.test_id = test_id;
	}
	public String getTest_date() {
		return test_date;
	}
	public void setTest_date(String test_date) {
		this.test_date = test_date;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	@Override
	public String toString() {
		return "Test_MedicalRecords [record_id=" + record_id + ", test_id=" + test_id + ", test_date=" + test_date
				+ ", result=" + result + "]";
	}
}
