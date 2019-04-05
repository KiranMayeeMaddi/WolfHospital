package org.wolf.ui;

import java.io.BufferedReader;
import java.io.IOException;

import org.wolf.crud.WardCRUD;

import jdk.internal.org.objectweb.asm.util.CheckAnnotationAdapter;

public class WardUI {

	public static void wardUI(BufferedReader reader) throws IOException  {
		System.out.println("1.ViewAll");
		System.out.println("2.ViewbyId");
		
		System.out.println("3.ViewBeds");
		System.out.println("4.ViewBedById");
		
		System.out.println("5.checkAvailableBeds");
		System.out.println("6.checkAvailableBedsWithWardSize");
		
		System.out.println("7.insertWard");
		System.out.println("8.addBedToWard");
		System.out.println("9.updateWard");
		System.out.println("10.removeWard")
		;
		System.out.println("11.removeBed");
		System.out.println("12.releaseBed");
		System.out.println("13.occupyBed");
		System.out.println("14.reserveBed");
		
		System.out.println("15.calculateAccomCharges");
		
		System.out.println("16.Exit");
		
		String input = reader.readLine();
		
		Integer ward_id; 
		Integer bed_id;
		Integer capacity;
		Double chargesPerDay;
		Integer patientId;
		String endTimes;
		String[] args;
		
		switch(input){
		case "1":
			System.out.println(WardCRUD.viewWard());
			break;
		case "2":
			System.out.println("Enter ward_id");
			ward_id = Integer.parseInt(reader.readLine());
			System.out.println(WardCRUD.viewWard(ward_id));
			break;
		case "3":
			System.out.println(WardCRUD.viewBeds());
			break;
		case "4":
			System.out.println("Enter ward_id and bed_id space separated");
			args = reader.readLine().split(" ");
			ward_id = Integer.parseInt(args[0]);
			bed_id = Integer.parseInt(args[1]);
			System.out.println(WardCRUD.viewBedById(ward_id, bed_id));
			break;
		case "5":
			System.out.println(WardCRUD.checkAvailableBeds());
			break;
		case "6":
			System.out.println("Enter prefered ward size");
			int ward_size = Integer.parseInt(reader.readLine());
			System.out.println(WardCRUD.checkAvailableBeds(ward_size));
			break;
		case "7":
			System.out.println("Enter capacity and charges per day space separated");
			args = reader.readLine().split(" ");
			capacity = Integer.parseInt(args[0]);
			chargesPerDay = Double.parseDouble(args[1]);
			System.out.println("Newly generate ward_id = ");
			System.out.println(WardCRUD.insertWard(capacity, chargesPerDay));
			break;
		case "8":
			System.out.println("Enter wardId");
			ward_id = Integer.parseInt(reader.readLine());
			System.out.println("Newly generate bed_id = ");
			System.out.println(WardCRUD.insertBedInWard(ward_id));
			break;
		case "9":
			System.out.println("Enter ward_id, capacity, charges per day spac separated");
			args = reader.readLine().split(" ");
			ward_id  = Integer.parseInt(args[0]);
			capacity = Integer.parseInt(args[1]);
			chargesPerDay = Double.parseDouble(args[2]);
			if(WardCRUD.updateWard(ward_id, capacity, chargesPerDay)){
				System.out.println("Operation Successful");
			} else{
				System.out.println("Operation Failed");
			}
			break;
		case "10":
			System.out.println("Enter ward_id");
			ward_id = Integer.parseInt(reader.readLine());
			if(WardCRUD.removeWard(ward_id)){
				System.out.println("Operation Successful");
			} else{
				System.out.println("Operation Failed");
			}
			break;
		case "11":
			System.out.println("Enter ward_id and bed_id space separated");
			args = reader.readLine().split(" ");
			ward_id = Integer.parseInt(args[0]);
			bed_id = Integer.parseInt(args[1]);
			if(WardCRUD.removeBed(ward_id, bed_id)){
				System.out.println("Operation Successful");
			} else{
				System.out.println("Operation Failed");
			}
			break;
		case "12":
			System.out.println("Enter ward_id and bed_id space separated");
			args = reader.readLine().split(" ");
			ward_id = Integer.parseInt(args[0]);
			bed_id = Integer.parseInt(args[1]);
			if(WardCRUD.releaseBed(ward_id, bed_id)){
				System.out.println("Operation Successful");
			} else{
				System.out.println("Operation Failed");
			}
			break;
		case "13":
			System.out.println("Enter ward_id and bed_id space separated");
			args = reader.readLine().split(" ");
			ward_id = Integer.parseInt(args[0]);
			bed_id = Integer.parseInt(args[1]);
			if(WardCRUD.occupyBed(ward_id, bed_id)){
				System.out.println("Operation Successful");
			} else{
				System.out.println("Operation Failed");
			}
			break;
		case "14":
			System.out.println("Enter patientId, wardId, bedId, endTime space separated");
			args = reader.readLine().split(" ");
			patientId = Integer.parseInt(args[0]);
			ward_id = Integer.parseInt(args[1]);
			bed_id = Integer.parseInt(args[2]);
			endTimes = args[3];
			if(WardCRUD.reserveBed(patientId, ward_id, bed_id, endTime)){
				System.out.println("Operation Successful");
			} else{
				System.out.println("Operation Failed");
			}
			break;
		case "15":
			System.out.println("Enter your checkinId");
			int checkInId =  Integer.parseInt(reader.readLine());
			if(WardCRUD.reserveBed(patientId, ward_id, bed_id, endTime)){
				System.out.println("Operation Successful");
			} else{
				System.out.println("Operation Failed");
			}
			break;
		case "16":
			break;
		default: System.out.println("Please enter correct input");
		}
	}

}
