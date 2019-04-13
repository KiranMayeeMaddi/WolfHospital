package org.wolf.reports;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.wolf.config.DatabaseConnection;
import org.wolf.baseclasses.Staff;
import org.wolf.baseclasses.Patient;
import org.wolf.baseclasses.PatientMedicalHistory;



public class Reports {
	
	//Returns the medical history of a particular patient
	public static ArrayList<PatientMedicalHistory> patientMedicalHistory(Integer patientId){
		try {
			Connection conn = DatabaseConnection.getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT MedicalRecords.record_id, Patient.patient_id, name,"
					+ "start_date, end_date, diagnosis, prescription, responsible_doctor, "
					+ "test_name, datetime, result, ward_id, bed_id, start_time, end_time" + 
					"FROM MedicalRecords INNER JOIN Patient ON MedicalRecords.patient_id" + 
					"= Patient.patient_id INNER JOIN Test_MedicalRecords ON" + 
					"MedicalRecords.record_id = Test_MedicalRecords.record_id INNER JOIN" + 
					"Ward_Patient_checks_In ON Patient.patient_id =" + 
					"Ward_Patient_checks_In.patient_id INNER JOIN Test ON Test.test_id =" + 
					"Test_MedicalRecords.test_id WHERE MedicalRecords.patient_id ="  +patientId);

			ArrayList <PatientMedicalHistory> patientHistoryList = new ArrayList <> ();
			PatientMedicalHistory p = null;

			while(rs.next()) {
				p = new PatientMedicalHistory(rs.getInt("record_id"), rs.getInt("patient_id"), 
						rs.getString("name"), rs.getString("start_date"), 
						rs.getString("end_date"), rs.getString("diagnosis"), rs.getString("prescription"),
						rs.getInt("responsible_doctor_id"), rs.getString("process_treatment_plan"), 
						rs.getString("test_name"), rs.getString("test_date"), rs.getString("result"),
						rs.getInt("ward_id"), rs.getInt("bed_id"), rs.getString("start_time"), 
						rs.getString("end_time"));
				patientHistoryList.add(p);
			}
			
			return patientHistoryList;
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
			return null;
		}
	}

	// Returns medical history of a patient within a time period
	public static ArrayList<PatientMedicalHistory> patientMedicalHistoryTimePeriod(Integer patientId,String startTime, String endTime){
		try {
			Connection conn = DatabaseConnection.getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT MedicalRecords.record_id, Patient.patient_id, name,"
					+ "start_date, end_date, diagnosis, prescription, responsible_doctor," + 
					"test_name, datetime, result, ward_id, bed_id, start_time, end_time" + 
					"FROM MedicalRecords" + 
					"INNER JOIN Patient ON MedicalRecords.patient_id = Patient.patient_id" + 
					"INNER JOIN Test_MedicalRecords ON MedicalRecords.record_id =" + 
					"Test_MedicalRecords.record_id" + 
					"INNER JOIN Ward_Patient_checks_In ON Patient.patient_id =" + 
					"Ward_Patient_checks_In.patient_id" + 
					"INNER JOIN Test ON Test.test_id = Test_MedicalRecords.test_id" + 
					"WHERE MedicalRecords.patient_id =" + patientId + "AND (start_date <= '"+ startTime +"'" + 
					"AND end_date > '"+ endTime +"' )");

			ArrayList <PatientMedicalHistory> patientHistoryList = new ArrayList <> ();
			PatientMedicalHistory p = null;

			while(rs.next()) {
				p = new PatientMedicalHistory(rs.getInt("record_id"), rs.getInt("patient_id"), 
						rs.getString("name"), rs.getString("start_date"), 
						rs.getString("end_date"), rs.getString("diagnosis"), rs.getString("prescription"),
						rs.getInt("responsible_doctor_id"), rs.getString("process_treatment_plan"), 
						rs.getString("test_name"), rs.getString("test_date"), rs.getString("result"),
						rs.getInt("ward_id"), rs.getInt("bed_id"), rs.getString("start_time"), 
						rs.getString("end_time"));
				patientHistoryList.add(p);
			}
			return patientHistoryList;
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
			return null;
		}
	}

	// Returns of the percentage usage of a particular bed with in certain time  
	public static Double bedUsageTimePeriod(String startDate, String endDate, Integer bedId) {
		try {
			Connection conn = DatabaseConnection.getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT 100 * t2.count2/t1.count1 AS BedUsage" + 
					"FROM (SELECT COUNT(*) AS count1 FROM Bed) AS t1" + 
					"CROSS JOIN (SELECT COUNT(DISTINCT" + bedId + ") AS count2 FROM" + 
					"Ward_Patient_checks_In WHERE start_time <= '"+ startDate +"' AND" + 
					"(end_time > '"+ endDate +"' OR end_time IS NULL)) AS t2");
			return rs.getDouble("BedUsage");
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
			return null;
		}
	}
	
	
	// Returns the percentage usage of a particular bed for the present day
	public static Double currentBedUsage(Integer bedId) {
		try {
			Connection conn = DatabaseConnection.getConnection();
			Statement st = conn.createStatement();
			String start_date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			ResultSet rs = st.executeQuery("SELECT 100 * t2.count2/t1.count1 AS BedUsage" + 
					"FROM (SELECT COUNT(*) AS count1 FROM Bed) AS t1" + 
					"CROSS JOIN (SELECT COUNT(DISTINCT" + bedId + ") AS count2 FROM" + 
					"Ward_Patient_checks_In WHERE start_time = '"+ start_date +"' AND" + 
					"(end_time = '"+ start_date +"' OR end_time IS NULL)) AS t2");
			return rs.getDouble("BedUsage");
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
			return null;
		}
	}
	
	// Returns the percentage usage of particular ward for the given time period
	public static Double wardUsageTimePeriod(String startDate, String endDate, Integer wardId) {
		try {
			Connection conn = DatabaseConnection.getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT 100 * t1.count1/t2.count2 AS WardUsage FROM (SELECT\n" + 
					"COUNT(*) as count1 FROM Ward_Patient_checks_In WHERE ward_id = 2 AND" + 
					"start_time <= '"+ startDate +"' AND (end_time > '"+ endDate +"' OR end_time" + 
					"is NULL)) AS t1 CROSS JOIN (SELECT capacity AS count2 FROM Ward" + 
					"WHERE ward_id =" + wardId + ") AS t2");
			return rs.getDouble("WardUsage");
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
			return null;
		}
	}
	
	
	// Returns the percentage usage of a particular ward for the present day
	public static Double currentWardUsage(Integer wardId) {
		try {
			Connection conn = DatabaseConnection.getConnection();
			Statement st = conn.createStatement();
			String start_date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			ResultSet rs = st.executeQuery("SELECT 100 * t1.count1/t2.count2 AS WardUsage FROM (SELECT\n" + 
					"COUNT(*) as count1 FROM Ward_Patient_checks_In WHERE ward_id = 2 AND" + 
					"start_time = '"+ start_date +"' AND (end_time = '"+ start_date +"' OR end_time" + 
					"is NULL)) AS t1 CROSS JOIN (SELECT capacity AS count2 FROM Ward" + 
					"WHERE ward_id =" + wardId + ") AS t2");
			return rs.getDouble("WardUsage");
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
			return null;
		}
	}
	
	//Returns the number of Patients for a given time period
	public static Integer noOfPatientsTimePeriod(String startDate, String endDate) {
		try {
			Connection conn = DatabaseConnection.getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT COUNT(*) AS TotalPatients FROM MedicalRecords WHERE" + 
					"start_date >= '"+ startDate +"' AND start_date < '"+ endDate +"' ");
			return rs.getInt("TotalPatients");
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
			return null;
		}
	}
	
	//Returns the patient information for which a doctor is responsible
	public static ArrayList<Patient> getPatientsForResponsibleDoc(Integer responsibleDoc) {
		try {
			Connection conn = DatabaseConnection.getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT record_id, Patient.patient_id,name, phone_no FROM" + 
					"MedicalRecords INNER JOIN Patient ON" + 
					"MedicalRecords.patient_id = Patient.patient_id WHERE" + 
					"MedicalRecords.responsible_doctor = '"+ responsibleDoc +"' AND end_date IS NOT NULL");
			ArrayList <Patient> patients = new ArrayList <> ();
			Patient p = null;
			while(rs.next()) {
				p = new Patient(rs.getInt("patient_id"), rs.getString("name"), rs.getString("ssn"), rs.getString("date_of_birth"), 
			    		rs.getString("gender"), rs.getString("phone_no"), rs.getString("address"));
				patients.add(p);
			}
			return patients;
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
			return null;
		}
	}
	
	//Returns the information of the staff with respect to a job title 
	public static ArrayList<Staff> getStaffByJobtitle(String jobTitle) {
		try {
			Connection conn = DatabaseConnection.getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM Staff "
					+ "GROUP BY" + jobTitle);
			ArrayList<Staff> staffList = new ArrayList <> ();
		    while(rs.next()) {
		    	Staff s = new Staff();
		    	s.setAddress(rs.getString("address"));
		    	s.setDept(rs.getString("dept"));
				s.setDob(rs.getString("date_of_birth"));
				s.setGender(rs.getString("gender"));
				s.setId(rs.getInt("staff_id"));
				s.setName(rs.getString("name"));
				s.setPno(rs.getString("phone_no"));
				s.setProfTitle(rs.getString("prof_title"));
				s.setSal(rs.getDouble("salary"));
				s.setJobTitle(rs.getString("job_title"));
				staffList.add(s);
		    }
		    return staffList;

		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
			return null;
		}
	}

}
