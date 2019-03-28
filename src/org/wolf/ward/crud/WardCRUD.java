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
	
	//Inserts new Ward
	//Returns the new WardId
	Boolean insertWard(Integer id,Integer capacity,Double chargesPerDay){
		return null;
	}
	
	
	BooleaninsertBedInWard(){
		
	}
	
	//List of available beds in the given ward size
	ArrayList<Bed> checkAvailableBeds(Integer ward_size){
		return null;
	}
}
