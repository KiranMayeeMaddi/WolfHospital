package org.wolf.reports;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.wolf.config.DatabaseConnection;
import org.wolf.baseclasses.PatientMedicalHistory;
import org.wolf.baseclasses.Staff;
import org.wolf.baseclasses.StaffGroupedDetails;


public class Reports {
	
	//Returns the medical history of a particular patient
	/**
	 * @param Integer patientId
	 * @return medical history of the given patientId
	 */
	public static ArrayList<PatientMedicalHistory> patientMedicalHistory(Integer patientId){
		try {
			Connection conn = DatabaseConnection.getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT MedicalRecords.record_id, Patient.patient_id, Patient.name, "
					+ "start_date, end_date, diagnosis, prescription, responsible_doctor, test_name, datetime, result, "
					+ "ward_id, bed_id, start_time, end_time, process_treatment_plan FROM MedicalRecords " + 
					"INNER JOIN Patient ON MedicalRecords.patient_id= Patient.patient_id " + 
					"INNER JOIN Test_MedicalRecords ON MedicalRecords.record_id = Test_MedicalRecords.record_id " + 
					"INNER JOIN Ward_Patient_checks_In ON Patient.patient_id = Ward_Patient_checks_In.patient_id " + 
					"INNER JOIN Test ON Test.test_id =Test_MedicalRecords.test_id WHERE MedicalRecords.patient_id  = " + patientId);

			ArrayList <PatientMedicalHistory> patientHistoryList = new ArrayList <> ();
			PatientMedicalHistory p = null;

			while(rs.next()) {
				p = new PatientMedicalHistory(rs.getInt("record_id"), rs.getInt("patient_id"), 
						rs.getString("name"), rs.getString("start_date"), 
						rs.getString("end_date"), rs.getString("diagnosis"), rs.getString("prescription"),
						rs.getInt("responsible_doctor"), rs.getString("process_treatment_plan"), 
						rs.getString("test_name"), rs.getString("datetime"), rs.getString("result"),
						rs.getInt("ward_id"), rs.getInt("bed_id"), rs.getString("start_time"), 
						rs.getString("end_time"));
				patientHistoryList.add(p);
			}
			
			return patientHistoryList;
		} catch (SQLException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	// Returns medical history of a patient within a time period
	/**
	 * @param Integer patientId
	 * @param String startTime
	 * @param String endTime
	 * @return medical history of the given patientId for the given time period
	 */
	public static ArrayList<PatientMedicalHistory> patientMedicalHistoryTimePeriod(Integer patientId,String startTime, String endTime){
		try {
			Connection conn = DatabaseConnection.getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT MedicalRecords.record_id, Patient.patient_id, name,"
					+ "start_date, end_date, diagnosis, prescription, responsible_doctor," + 
					" test_name, datetime, result, ward_id, bed_id, start_time, end_time, process_treatment_plan" + 
					" FROM MedicalRecords" + 
					" INNER JOIN Patient ON MedicalRecords.patient_id = Patient.patient_id" + 
					" INNER JOIN Test_MedicalRecords ON MedicalRecords.record_id =" + 
					" Test_MedicalRecords.record_id" + 
					" INNER JOIN Ward_Patient_checks_In ON Patient.patient_id =" + 
					" Ward_Patient_checks_In.patient_id" + 
					" INNER JOIN Test ON Test.test_id = Test_MedicalRecords.test_id" + 
					" WHERE MedicalRecords.patient_id =" + patientId + " AND (start_date <= '"+ endTime +"'" + 
					" AND (end_date IS NULL OR end_date > '"+ startTime +"' ))");

			ArrayList <PatientMedicalHistory> patientHistoryList = new ArrayList <> ();
			PatientMedicalHistory p = null;

			while(rs.next()) {
				p = new PatientMedicalHistory(rs.getInt("record_id"), rs.getInt("patient_id"), 
						rs.getString("name"), rs.getString("start_date"), 
						rs.getString("end_date"), rs.getString("diagnosis"), rs.getString("prescription"),
						rs.getInt("responsible_doctor"), rs.getString("process_treatment_plan"), 
						rs.getString("test_name"), rs.getString("datetime"), rs.getString("result"),
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

	// Returns of the percentage usage of all beds with in certain time  
	/**
	 * @param String startDate
	 * @param String endDate
	 * @return Average bed usage for the given time period
	 */
	public static Double bedUsageTimePeriod(String startDate, String endDate) {
		try {
			Connection conn = DatabaseConnection.getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT 100 * t2.count2/t1.count1 AS BedUsage" + 
					" FROM (SELECT COUNT(*) AS count1 FROM Bed) AS t1" + 
					" CROSS JOIN (SELECT COUNT(DISTINCT ward_id, bed_id ) AS count2 FROM" + 
					" Ward_Patient_checks_In WHERE start_time <= '"+ endDate +"' AND" + 
					" (end_time > '"+ startDate +"' OR end_time IS NULL)) AS t2");
			Double bedUsage = null;
			while(rs.next()) {
				bedUsage = rs.getDouble("BedUsage");
			}
			return bedUsage;
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
			return null;
		}
	}
	
	
	// Returns the current percentage usage of all beds
	/**
	 * @returns current usage of total beds
	 */
	public static Double currentBedUsage() {
		try {
			Connection conn = DatabaseConnection.getConnection();
			Statement st = conn.createStatement();
			String start_date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			ResultSet rs = st.executeQuery("SELECT 100 * t2.count2/t1.count1 AS BedUsage" + 
					" FROM (SELECT COUNT(*) AS count1 FROM Bed) AS t1" + 
					" CROSS JOIN (SELECT COUNT(DISTINCT ward_id, bed_id)  AS count2 FROM" + 
					" Ward_Patient_checks_In WHERE start_time <= '"+ start_date +"' AND" + 
					" (end_time > '"+ start_date +"' OR end_time IS NULL)) AS t2");
			Double bedUsage = null;
			while(rs.next()) {
				bedUsage = rs.getDouble("BedUsage");
			}
			return bedUsage;
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
			return null;
		}
	}
	
	// Returns the percentage usage of all wards for the given time period
	/**
	 * @param String startDate
	 * @param String endDate
	 * @return total ward usage of all wards in the given time period
	 */
	public static Double wardUsageTimePeriod(String startDate, String endDate) {
		try {
			Connection conn = DatabaseConnection.getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT 100 * t1.count1/t2.count2 AS WardUsage FROM (SELECT" + 
					" COUNT(DISTINCT ward_id) as count1 FROM Ward_Patient_checks_In  WHERE" + 
					" start_time <= '"+ endDate +"' AND (end_time > '"+ startDate +"' OR end_time" + 
					" is NULL)) AS t1 CROSS JOIN (SELECT COUNT(*) AS count2 FROM Ward) AS t2");
			Double WardUsage = null;
			while(rs.next()) {
				WardUsage = rs.getDouble("WardUsage");
			}
			return WardUsage;
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
			return null;
		}
	}
	
	/**
	 * @param String startDate
	 * @param String endDate
	 * @param Integer wardId
	 * @return total ward usage of the given ward id in the given time period
	 */
	public static Double wardUsageByIdTimePeriod(String startDate, String endDate, Integer wardId) {
		try {
			Connection conn = DatabaseConnection.getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT 100 * t1.count1/t2.count2 AS WardUsage FROM (SELECT" + 
					" COUNT(*) as count1 FROM Ward_Patient_checks_In WHERE ward_id = "+wardId+" AND" + 
					" start_time <= '"+ endDate +"' AND (end_time > '"+ startDate +"' OR end_time" + 
					" is NULL)) AS t1 CROSS JOIN (SELECT capacity AS count2 FROM Ward" + 
					" WHERE ward_id =" + wardId + ") AS t2");
			Double WardUsage = null;
			while(rs.next()) {
				WardUsage = rs.getDouble("WardUsage");
			}
			return WardUsage;
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
			return null;
		}
	}
	
	// Returns the current percentage usage of all wards
		/**
		 * @return current total usage of all wards
		 */
		public static Double currentWardUsage() {
			try {
				Connection conn = DatabaseConnection.getConnection();
				Statement st = conn.createStatement();
				String start_date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
				ResultSet rs = st.executeQuery("SELECT 100 * t1.count1/t2.count2 AS WardUsage FROM (SELECT" + 
						" COUNT(DISTINCT ward_id) as count1 FROM Ward_Patient_checks_In  WHERE" + 
						" start_time <= '"+ start_date +"' AND (end_time > '"+ start_date +"' OR end_time" + 
						" is NULL)) AS t1 CROSS JOIN (SELECT COUNT(*) AS count2 FROM Ward) AS t2");
				Double WardUsage = null;
				while(rs.next()) {
					WardUsage = rs.getDouble("WardUsage");
				}
				return WardUsage;
			} catch (SQLException ex) {
				System.err.println(ex.getMessage());
				return null;
			}
		}
		
	// Returns the current percentage usage of a particular ward 
	/**
	 * @param Integer wardId
	 * @return current ward usage for the given ward id
	 */
	public static Double currentWardUsageById(Integer wardId) {
		try {
			Connection conn = DatabaseConnection.getConnection();
			Statement st = conn.createStatement();
			String start_date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			ResultSet rs = st.executeQuery("SELECT 100 * t1.count1/t2.count2 AS WardUsage FROM (SELECT" + 
					" COUNT(*) as count1 FROM Ward_Patient_checks_In WHERE ward_id = "+wardId+" AND" + 
					" start_time <= '"+ start_date +"' AND (end_time > '"+ start_date +"' OR end_time" + 
					" is NULL)) AS t1 CROSS JOIN (SELECT capacity AS count2 FROM Ward" + 
					" WHERE ward_id =" + wardId + ") AS t2");
			Double WardUsage = null;
			while(rs.next()) {
				WardUsage = rs.getDouble("WardUsage");
			}
			return WardUsage;
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
			return null;
		}
	}
	
	//Returns the number of Patients for a given time period
	/**
	 * @param String startDate
	 * @param String endDate
	 * @return Total number of patients given the time period
	 */
	public static Integer noOfPatientsTimePeriod(String startDate, String endDate) {
		try {
			Connection conn = DatabaseConnection.getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT COUNT(*) AS TotalPatients FROM MedicalRecords WHERE" + 
					" start_date >= '"+ startDate +"' AND start_date < '"+ endDate +"' ");
			Integer TotalPatients = null;
			while(rs.next()) {
				TotalPatients = rs.getInt("TotalPatients");
			}
			return TotalPatients;
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
			return null;
		}
	}
	
	//Returns the patient information for which a doctor is responsible
	/**
	 * @param Integer responsibleDoc
	 * @return Details of the patients given the currently responsible doctor
	 */
	public static ArrayList<String> getPatientsForResponsibleDoc(Integer responsibleDoc) {
		try {
			Connection conn = DatabaseConnection.getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT record_id, Patient.patient_id, name, phone_no FROM" + 
					" MedicalRecords INNER JOIN Patient ON" + 
					" MedicalRecords.patient_id = Patient.patient_id WHERE" + 
					" MedicalRecords.responsible_doctor = '"+ responsibleDoc +"' AND end_date IS NULL");
			ArrayList <String> patients = new ArrayList <> ();
			String p = null;
			while(rs.next()) {
				p = "";
				p += rs.getInt("record_id") + " " + rs.getInt("patient_id") + " "+ rs.getString("name") + " "+ rs.getString("phone_no");
				
				patients.add(p);
			}
			return patients;
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
			return null;
		}
	}
	
	//Returns the information of the staff with respect to a job title 
	/**
	 * @return staff details grouped by their job role
	 */
	public static ArrayList<Staff> getStaffByJobtitle() {
		try {
			Connection conn = DatabaseConnection.getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM Staff GROUP BY job_title");
			ArrayList<Staff> staffList = new ArrayList <> ();
			Staff s = null;
			while(rs.next()) {
			    s = new Staff();
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
			ex.printStackTrace();
			return null;
		}
	}
}
