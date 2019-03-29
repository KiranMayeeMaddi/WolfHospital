package org.wolf.ward.crud;

import java.util.ArrayList;

import org.wolf.baseclasses.Bed;
import org.wolf.baseclasses.Ward;

public class WardCRUD {
	
	//Get wardInfo by Id
	Ward viewWard(String ward_id){
		return null;
	} 
	
	//Get all wardInfo
	ArrayList<Ward> viewWard(){
		return null;
	}
	
	//Get all beds info
	ArrayList<Bed> viewBeds(){
		return null;
	}
	
	//Get bed info by Id
	Bed viewBedById(String ward_id, String bed_id){
		return null;
	}
	
	// List of info of all available beds
	ArrayList<Bed> checkAvailableBeds(){
		return null;
	}
	
	//List of available beds in the given ward size
	ArrayList<Bed> checkAvailableBeds(Integer ward_size){
		return null;
	}
		
	//Inserts new Ward
	//Returns the new WardId
	Boolean insertWard(Integer id,Integer capacity,Double chargesPerDay){
		return null;
	}
	
	//Inserts a Bed in the given ward
	//Check for the capacity of the ward before inserting
	//Return BedId of the newly inserted Bed
	Boolean insertBedInWard(Integer ward_id){
		return null;
	}
	
	//Update the ward with the given values
	//Return true if successful else false
	Boolean updateWard(Integer ward_id, Integer capacity,Double chargesPerDay){
		return null;
	}
	
	
	//Remove bed from the given ward
	//Return true if successful else false
	Boolean removeBed(Integer bed_id,Integer ward_id){
		return null;
	}
	
	//Remove the ward
	//Also remove all the beds associated with it
	//Return true if successful else false
	Boolean removeWard(Integer ward_id){
		return null;
	}
	
	//Set the availability of the bed to Y
	Boolean releaseBed(Integer wardId, Integer bedId){
		return null;
	}
	
	//Set the availability of the bed to N
	Boolean occupyBed(Integer wardId, Integer bedId){
		return null;
	}
}
