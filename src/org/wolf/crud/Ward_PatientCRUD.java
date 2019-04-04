package org.wolf.crud;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.jdbc.core.RowMapper;
import org.wolf.baseclasses.Ward_Patient;

class WardPatientMapper implements RowMapper {
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		Ward_Patient w = new Ward_Patient();
		w.setBed_id(rs.getInt("bed_id"));
		w.setCheckin_id(rs.getInt("checkin_id"));
		w.setEnd_time(rs.getString("end_time"));
		w.setPatient_id(rs.getInt("patient_id"));
		w.setStart_time(rs.getString("end_time"));
		w.setWard_id(rs.getInt("ward_id"));
		return w;
	}
}

public final class Ward_PatientCRUD {
	
	public static ArrayList<Ward_Patient> viewWardPatients(){
		return null;
	} 
	
	public static Ward_Patient viewWardPatients(Integer checkinId){
		return null;
	} 
	
	//Returns check-in ID
	public static Integer insertWardPatient(Integer patient_id, Integer ward_id, Integer bed_id, String end_time){
		//Put the start time as the current  timestamp
		return null;
	}
	
	public static Boolean updateWardPatient(Integer checkin_id, Integer patient_id, Integer ward_id, Integer bed_id, String start_time, String end_time){
		return null;
	}
	
	public static Boolean updateWardEndtime(Integer checkin_id){
		//Put the current time stamp as end_time
		return null;
	}
	
	public static Boolean deleteWardPatient(Integer checkinId){
		return null;
	}
}
