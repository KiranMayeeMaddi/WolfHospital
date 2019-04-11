package org.wolf.baseclasses;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.time.Period;

public class Patient {

	public Integer id;
	public String name;
	public String ssn;
	public String dob;
	public String gender;
	public String pno;
	public String address;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Patient(Integer id, String name, String ssn, String dob, String gender, String pno, String address) {
		this.id = id;
		this.name = name;
		this.ssn = ssn;
		this.dob = dob;
		this.gender = gender;
		this.pno = pno;
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
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

	public Integer getAge(String dob) {
		LocalDate dobDate = LocalDate.parse(dob);
		LocalDate curDate = LocalDate.now();
		return Period.between(dobDate, curDate).getYears();
	}
	
	@Override
	public String toString() {
		return "Patient [id=" + id + ", name=" + name + ", ssn=" + ssn + ", dob=" + dob + ", age="+getAge(dob)+", gender=" + gender
				+ ", pno=" + pno + ", address=" + address + "]";
	}
}
