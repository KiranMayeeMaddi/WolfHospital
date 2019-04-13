package org.wolf.crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.wolf.baseclasses.PatientInfo;
import org.wolf.config.DatabaseConnection;

/**
 * Basic Operations for the Patient table such as viewing, inserting, updating and deleting.
 */
public class PatientCRUD {
	
	/**
	 * @return All Patients information
	 */
	public static ArrayList<PatientInfo> viewPatients() {
		Connection conn;
		try {
			conn = DatabaseConnection.getConnection();
			Statement st = conn.createStatement();
		    ResultSet rs = st.executeQuery("select P.*,"
		    		+ "(case WHEN B.patient_id is NULL THEN 'NO' ELSE 'YES' END) as completing_treatment,"
		    		+ "(case WHEN W.patient_id is NOT NULL THEN 'YES' ELSE 'NO' END) as in_ward,"
		    		+ " T.process_treatment_plan as process_treatment_plan from Patient P"
		    		+ " left outer join "
		    		+ "(select distinct patient_id from MedicalRecords where end_date is not null) B"
		    		+ " on B.patient_id = P.patient_id"
		    		+ " LEFT OUTER JOIN "
		    		+ "(select distinct patient_id from Ward_Patient_checks_In where end_time is null) W"
		    		+ " on P.patient_id = W.patient_id "
		    		+ "left outer join  "
		    		+ "(select patient_id,process_treatment_plan from MedicalRecords "
		    		+ "where record_id in (select max(record_id) from MedicalRecords group by patient_id)) T "
		    		+ "on  P.patient_id = T.patient_id");
		    ArrayList<PatientInfo> list = new ArrayList<PatientInfo>();
		    while(rs.next()){
		    	PatientInfo p = new PatientInfo(rs.getInt("patient_id"), rs.getString("name"), rs.getString("ssn"), rs.getString("date_of_birth"), rs.getString("gender"), rs.getString("phone_no"), rs.getString("address"), rs.getString("process_treatment_plan"), rs.getString("in_ward"), rs.getString("completing_treatment"));
		    	list.add(p);
		    }
		    return  list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * @param patient_id
	 * @return Patient info for the given patient_id
	 */
	public static PatientInfo viewPatients(Integer patient_id) {
		Connection conn;
		try {
			conn = DatabaseConnection.getConnection();
			Statement st = conn.createStatement();
		    ResultSet rs = st.executeQuery("select P.*,"
		    		+ "(case WHEN B.patient_id is NULL THEN 'NO' ELSE 'YES' END) as completing_treatment,"
		    		+ "(case WHEN W.patient_id is NOT NULL THEN 'YES' ELSE 'NO' END) as in_ward,"
		    		+ " T.process_treatment_plan as process_treatment_plan from Patient P"
		    		+ " left outer join "
		    		+ "(select distinct patient_id from MedicalRecords where end_date is not null) B"
		    		+ " on B.patient_id = P.patient_id"
		    		+ " LEFT OUTER JOIN "
		    		+ "(select distinct patient_id from Ward_Patient_checks_In where end_time is null) W"
		    		+ " on P.patient_id = W.patient_id "
		    		+ "left outer join  "
		    		+ "(select patient_id,process_treatment_plan from MedicalRecords "
		    		+ "where record_id in (select max(record_id) from MedicalRecords group by patient_id)) T "
		    		+ "on  P.patient_id = T.patient_id "
		    		+ "where P.patient_id = "+patient_id);
		    PatientInfo p = null;
		    while(rs.next()){
		    	p = new PatientInfo(rs.getInt("patient_id"), rs.getString("name"), rs.getString("ssn"), rs.getString("date_of_birth"), rs.getString("gender"), rs.getString("phone_no"), rs.getString("address"), rs.getString("process_treatment_plan"), rs.getString("in_ward"), rs.getString("completing_treatment"));
		    }
		    if(p == null){
		    	System.out.println("No such  Id");
		    }
		    return  p;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//Returns list of patient information searched by given name
	//String matching LIKE query 
	/**
	 * @param name
	 * @return Patient info for the list of patients which matches the name
	 */
	public static ArrayList<PatientInfo> viewPatientsByName(String name) {
		Connection conn;
		try {
			conn = DatabaseConnection.getConnection();
			Statement st = conn.createStatement();
		    ResultSet rs = st.executeQuery("select P.*,"
		    		+ "(case WHEN B.patient_id is NULL THEN 'NO' ELSE 'YES' END) as completing_treatment,"
		    		+ "(case WHEN W.patient_id is NOT NULL THEN 'YES' ELSE 'NO' END) as in_ward,"
		    		+ " T.process_treatment_plan as process_treatment_plan from Patient P"
		    		+ " left outer join "
		    		+ "(select distinct patient_id from MedicalRecords where end_date is not null) B"
		    		+ " on B.patient_id = P.patient_id"
		    		+ " LEFT OUTER JOIN "
		    		+ "(select distinct patient_id from Ward_Patient_checks_In where end_time is null) W"
		    		+ " on P.patient_id = W.patient_id "
		    		+ "left outer join  "
		    		+ "(select patient_id,process_treatment_plan from MedicalRecords "
		    		+ "where record_id in (select max(record_id) from MedicalRecords group by patient_id)) T "
		    		+ "on  P.patient_id = T.patient_id "
		    		+ "where LOWER(P.name) LIKE '%"+name.toLowerCase()+"%'");
		    
		    ArrayList<PatientInfo> list = new ArrayList<PatientInfo>();
		    while(rs.next()){
		    	PatientInfo p = new PatientInfo(rs.getInt("patient_id"), rs.getString("name"), rs.getString("ssn"), rs.getString("date_of_birth"), rs.getString("gender"), rs.getString("phone_no"), rs.getString("address"), rs.getString("process_treatment_plan"), rs.getString("in_ward"), rs.getString("completing_treatment"));
		    	list.add(p);
		    }
		    if(list.size()==0){
		    	System.out.println("No such Patient");
		    }
		    return  list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * @param ssn
	 * @return Patient info for the list of patients which matches the ssn
	 */
	public static PatientInfo viewPatientsBySSN(String ssn) {
		Connection conn;
		try {
			conn = DatabaseConnection.getConnection();
			Statement st = conn.createStatement();
		    ResultSet rs = st.executeQuery("select P.*,"
		    		+ "(case WHEN B.patient_id is NULL THEN 'NO' ELSE 'YES' END) as completing_treatment,"
		    		+ "(case WHEN W.patient_id is NOT NULL THEN 'YES' ELSE 'NO' END) as in_ward,"
		    		+ " T.process_treatment_plan as process_treatment_plan from Patient P"
		    		+ " left outer join "
		    		+ "(select distinct patient_id from MedicalRecords where end_date is not null) B"
		    		+ " on B.patient_id = P.patient_id"
		    		+ " LEFT OUTER JOIN "
		    		+ "(select distinct patient_id from Ward_Patient_checks_In where end_time is null) W"
		    		+ " on P.patient_id = W.patient_id "
		    		+ "left outer join  "
		    		+ "(select patient_id,process_treatment_plan from MedicalRecords "
		    		+ "where record_id in (select max(record_id) from MedicalRecords group by patient_id)) T "
		    		+ "on  P.patient_id = T.patient_id "
		    		+ "where P.ssn = '"+ssn+"'");
		    PatientInfo p = null;
		    while(rs.next()){
		    	p = new PatientInfo(rs.getInt("patient_id"), rs.getString("name"), rs.getString("ssn"), rs.getString("date_of_birth"), rs.getString("gender"), rs.getString("phone_no"), rs.getString("address"), rs.getString("process_treatment_plan"), rs.getString("in_ward"), rs.getString("completing_treatment"));
		    }
		    if(p == null){
		    	System.out.println("No such Patient");
		    }
		    return  p;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 *Inserts the patient information in the patient table based on the params below
	 * @param name
	 * @param ssn
	 * @param dob
	 * @param gender
	 * @param pno
	 * @param address
	 * @return new patientId
	 */
	public static Integer insertPatient(String name, String ssn, String dob, String gender, String pno, String address){
		try {
			Connection conn = DatabaseConnection.getConnection();
			
			String query = "insert into Patient (name, address, phone_no, gender, ssn, date_of_birth)"
					+ " values (?,?,?,?,?,?)";
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, name);
			st.setString(2, address);
			st.setString(3, pno);
			st.setString(4, gender);
			if (ssn.isEmpty())
				st.setNull(5, java.sql.Types.VARCHAR);
			else
				st.setString(5, ssn);
			st.setString(6, dob);
			
		    st.executeUpdate();
		    
		    ResultSet rs = st.executeQuery("select patient_id from Patient order by patient_id desc limit 1");
			int patient_id = 0;
		    while (rs.next())
		    	patient_id = rs.getInt("patient_id");
		    return patient_id;
	    }
	    catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	    	return null;
	    }
	}
	
	/**
	 * Updates the record with this new information using the ID.
	 * @param id
	 * @param name
	 * @param ssn
	 * @param dob
	 * @param gender
	 * @param pno
	 * @param address
	 * @return true if the update was successful else false
	 */
	public static Boolean updatePatient(Integer id, String name, String ssn, String dob, String gender, String pno, String address){
		try {
			Connection conn = DatabaseConnection.getConnection();
			
			String query = "Update Patient set name=?, address=?, phone_no=?, gender=?, ssn=?, date_of_birth=? where patient_id=?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, name);
			st.setString(2, address);
			st.setString(3, pno);
			st.setString(4, gender);
			if (ssn.isEmpty())
				st.setNull(5, java.sql.Types.VARCHAR);
			else
				st.setString(5, ssn);
			st.setString(6, dob);
			st.setInt(7, id);
			
			st.executeUpdate();
			
			return true;
		}
		catch(SQLException e) {
			System.err.println(e.getMessage());
			return false;
		}
	}
	
	/**
	 * Deletes the patient information  using the patient Id
	 * @param id
	 * @return true if successful else false
	 */
	public static Boolean deletePatient(Integer id){
		try {
			Connection conn = DatabaseConnection.getConnection();
			
		    Statement st = conn.createStatement();
		    st.executeUpdate("DELETE FROM Patient WHERE patient_id = " + id );
		    
		    return true;
	    } catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	    	return false;
	    }
	}
}
