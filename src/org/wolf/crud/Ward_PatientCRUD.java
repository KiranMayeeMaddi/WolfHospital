package org.wolf.crud;

import java.util.ArrayList;

import org.wolf.baseclasses.Ward_Patient;

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
