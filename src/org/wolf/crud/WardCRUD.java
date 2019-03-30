package org.wolf.crud;

import java.util.ArrayList;

import org.wolf.baseclasses.Bed;
import org.wolf.baseclasses.Ward;

public final class WardCRUD {
	
	//Get wardInfo by Id
	public static Ward viewWard(String ward_id){
		return null;
	} 
	
	//Get all wardInfo
	public static ArrayList<Ward> viewWard(){
		return null;
	}
	
	//Get all beds info
	public static ArrayList<Bed> viewBeds(){
		return null;
	}
	
	//Get bed info by Id
	public static Bed viewBedById(String ward_id, String bed_id){
		return null;
	}
	
	// List of info of all available beds
	public static ArrayList<Bed> checkAvailableBeds(){
		return null;
	}
	
	//List of available beds in the given ward size
	public static ArrayList<Bed> checkAvailableBeds(Integer ward_size){
		return null;
	}
		
	//Inserts new Ward
	//Returns the new WardId
	public static Boolean insertWard(Integer id,Integer capacity,Double chargesPerDay){
		return null;
	}
	
	//Inserts a Bed in the given ward
	//Check for the capacity of the ward before inserting
	//Return BedId of the newly inserted Bed
	public static Boolean insertBedInWard(Integer ward_id){
		return null;
	}
	
	//Update the ward with the given values
	//Return true if successful else false
	public static Boolean updateWard(Integer ward_id, Integer capacity,Double chargesPerDay){
		return null;
	}
	
	
	//Remove bed from the given ward
	//Return true if successful else false
	public static Boolean removeBed(Integer bed_id,Integer ward_id){
		return null;
	}
	
	//Remove the ward
	//Also remove all the beds associated with it
	//Return true if successful else false
	public static Boolean removeWard(Integer ward_id){
		return null;
	}
	
	//Set the availability of the bed to Y
	public static Boolean releaseBed(Integer wardId, Integer bedId){
		return null;
	}
	
	//Set the availability of the bed to N
	public static Boolean occupyBed(Integer wardId, Integer bedId){
		return null;
	}
	
	public static Double calculateAccomCharges(Integer checkinId){
		//Based on the rate sheet in the Wards table and the days(end time - start time)
		//Calculate the amount and return it
		return null;
	} 
	
	//Return true if successful else false
	//Assuming the bed availability has been checked
	public static Boolean reserveBed(Integer patientId, Integer ward_id,Integer bed_id, String endTime){
		//start_time will be the current time
		return null;
	}
}
