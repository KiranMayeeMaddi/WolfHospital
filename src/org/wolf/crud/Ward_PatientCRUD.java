package org.wolf.crud;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.wolf.baseclasses.Ward_Patient;
import org.wolf.config.DatabaseConnection;

public final class Ward_PatientCRUD {
	
	/**
	 * @return array list of all records in ward_patient_checks_in table
	 */
	public static ArrayList<Ward_Patient> viewWardPatients(){
		try {
			Connection conn = DatabaseConnection.getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("select * from Ward_Patient_checks_In");
			ArrayList<Ward_Patient> patients = new ArrayList<Ward_Patient>();
			
			while (rs.next()) {
				Ward_Patient p = new Ward_Patient();
				p.setBed_id(rs.getInt("bed_id"));
				p.setCheckin_id(rs.getInt("checkin_id"));
				p.setEnd_time(rs.getString("end_time"));
				p.setPatient_id(rs.getInt("patient_id"));
				p.setStart_time(rs.getString("start_time"));
				p.setWard_id(rs.getInt("ward_id"));
				
				patients.add(p);
			}
			return patients;
		}
		catch (SQLException e) {
			System.err.println(e.getMessage());
			return null;
		}
	} 
	
	/**
	 * @param Integer checkinId
	 * @return return the record for the associated checkin id
	 * @throws SQLException
	 */
	public static Ward_Patient viewWardPatients(Integer checkinId) throws SQLException{
		Connection conn = DatabaseConnection.getConnection();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("select * from Ward_Patient_checks_In where checkin_id="+checkinId);
		Ward_Patient p = new Ward_Patient();
		
		while (rs.next()) {
			p.setBed_id(rs.getInt("bed_id"));
			p.setCheckin_id(rs.getInt("checkin_id"));
			p.setEnd_time(rs.getString("end_time"));
			p.setPatient_id(rs.getInt("patient_id"));
			p.setStart_time(rs.getString("start_time"));
			p.setWard_id(rs.getInt("ward_id"));
		}
		return p;
	} 
	
	//Returns check-in ID
	/**
	 * @param Integer patient_id
	 * @param Integer ward_id
	 * @param Integer bed_id
	 * @param String end_time
	 * @return recently inserted check_in id 
	 * @throws SQLException
	 */
	public static Integer insertWardPatient(Integer patient_id, Integer ward_id, Integer bed_id, String end_time) throws SQLException {
		//Put the start time as the current  timestamp
		Connection conn = DatabaseConnection.getConnection();
			
			//DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			//LocalDateTime localDate = LocalDateTime.now();
			//String start_time = dtf.format(localDate);
			
		String query = "insert into Ward_Patient_checks_In (patient_id, ward_id, bed_id, start_time, end_time)"
				+ " values (?,?,?,NOW(),?)";
		PreparedStatement st = conn.prepareStatement(query);
		st.setInt(1, patient_id);
		st.setInt(2, ward_id);
		st.setInt(3, bed_id);
		//st.setString(4, start_time);
		if(end_time.isEmpty())
			st.setNull(5, java.sql.Types.VARCHAR);
		else 
			st.setString(4, end_time);
		st.executeUpdate();
			
		ResultSet rs = st.executeQuery("select checkin_id from Ward_Patient_checks_In order by checkin_id desc limit 1");
		int shift_id = 0;
	    while (rs.next())
	    	shift_id = rs.getInt("checkin_id");
	    return shift_id;
	}
	
	/**
	 * @param Integer checkin_id
	 * @param Integer patient_id
	 * @param Integer ward_id
	 * @param Integer bed_id
	 * @param String start_time
	 * @param String end_time
	 * @return true if the update was successful else false
	 */
	public static Boolean updateWardPatient(Integer checkin_id, Integer patient_id, Integer ward_id, Integer bed_id, String start_time, String end_time){
		try {
			Connection conn = DatabaseConnection.getConnection();
			String query = "UPDATE Ward_Patient_checks_In SET patient_id=?, ward_id=?, bed_id=?, start_time=?, end_time=? where checkin_id=?";
		    PreparedStatement st = conn.prepareStatement(query);
		    st.setInt(1, patient_id);
		    st.setInt(2, ward_id);
		    st.setInt(3, bed_id);
		    st.setString(4, start_time);
		    if(end_time.isEmpty())
				st.setNull(5, java.sql.Types.VARCHAR);
			else 
				st.setString(5, end_time);
		    st.setInt(6, checkin_id);
		    st.executeUpdate();
		    
		    return true;
	    }
	    catch (SQLException ex) {
	    	ex.printStackTrace();
	    	return false;
	    }
	}
	
	/**
	 * @param Integer checkin_id
	 * @return true if update was successful else false
	 * @throws SQLException
	 */
	public static Boolean updateWardEndtime(Integer checkin_id) throws SQLException {
		//Put the current time stamp as end_time
		
		Connection conn = DatabaseConnection.getConnection();
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime localDate = LocalDateTime.now();
		String end_time = dtf.format(localDate);
		
		String query = "Update Ward_Patient_checks_In set end_time=? where checkin_id=?";
		PreparedStatement st = conn.prepareStatement(query);
		st.setString(1, end_time);
		st.setInt(2, checkin_id);
		
		st.executeUpdate();
		
		return true;
	}
	
	/**
	 * @param Integer checkinId
	 * @return true if deleted successfully else false
	 */
	public static Boolean deleteWardPatient(Integer checkinId){
		try {
			Connection conn = DatabaseConnection.getConnection();
			
		    Statement st = conn.createStatement();
		    st.executeUpdate("DELETE FROM Ward_Patient_checks_In WHERE checkin_id= " + checkinId);
		    
		    return true;
	    }
	    catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	    	return false;
	    }
	}
}
