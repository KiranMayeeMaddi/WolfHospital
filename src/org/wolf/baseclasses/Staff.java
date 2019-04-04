package org.wolf.baseclasses;

public class Staff {
	
	public Integer id;
	public String name;
	public String jobTitle;
	public String profTitle;
	public String dob;
	public String gender;
	public String pno;
	public String address; 
	public String dept; 
	public Double sal;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public String getProfTitle() {
		return profTitle;
	}
	public void setProfTitle(String profTitle) {
		this.profTitle = profTitle;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPno() {
		return pno;
	}
	public void setPno(String pno) {
		this.pno = pno;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public Double getSal() {
		return sal;
	}
	public void setSal(Double sal) {
		this.sal = sal;
	}
	
	@Override
	public String toString() {
		return "Staff [id=" + id + ", name=" + name + ", profTitle=" + profTitle + ", dob=" + dob + ", gender=" + gender
				+ ", pno=" + pno + ", address=" + address + ", dept=" + dept + ", sal=" + sal + "]";
	}
}
