package org.wolf.ui;

import java.io.BufferedReader;
import java.io.IOException;

import org.wolf.crud.Ward_NurseCRUD;

public class WardNurseUI {

	public static void wardNurseUI(BufferedReader reader) throws NumberFormatException, IOException {
		
		System.out.println("1.viewShifts");
		System.out.println("2.viewShiftbyId");
		System.out.println("3.insertWardNurse");
		System.out.println("4.updateWardNurse");
		System.out.println("5.deleteWardNurse");
		System.out.println("6.exit");
		
		Integer shiftId;
		Integer wardId; 
		Integer staffId; 
		String start_time; 
		String end_time;
		String[] args;
		String input = reader.readLine();
		switch(input){
		case "1":
			Ward_NurseCRUD.viewShifts().forEach(System.out::println);
			break;
		case "2":
			System.out.println("Enter shiftId");
			shiftId = Integer.parseInt(reader.readLine());
			System.out.println(Ward_NurseCRUD.viewShift(shiftId));
			break;
		case "3":
			System.out.println("Enter space separated  wardId,  staffId,  start_time,  end_time");
			args = reader.readLine().split(" ");
			wardId = Integer.parseInt(args[0]);
			staffId = Integer.parseInt(args[1]);
			start_time = args[2];
			end_time = args[3]; 
			System.out.println("Newly generated shiftId is");
			System.out.println(Ward_NurseCRUD.insertWardNurse(wardId, staffId, start_time, end_time));
			break;
		case "4":
			System.out.println("Enter space separated  shiftId, wardId,  staffId,  start_time,  end_time");
			args = reader.readLine().split(" ");
			shiftId = Integer.parseInt(args[0]);
			wardId = Integer.parseInt(args[1]);
			staffId = Integer.parseInt(args[2]);
			start_time = args[3];
			end_time = args[4];
			if(Ward_NurseCRUD.updateWardNurse(shiftId, wardId, staffId, start_time, end_time)){
				System.out.println("Operation Successful");
			} else{
				System.out.println("Operation Failed");
			}
			break;
		case "5":
			System.out.println("Enter shiftId");
			shiftId = Integer.parseInt(reader.readLine());
			if(Ward_NurseCRUD.deleteWardNurse(shiftId)){
				System.out.println("Operation Successful");
			} else{
				System.out.println("Operation Failed");
			}
			break;
		case "6":
			return;
		default:
			System.out.println("Enter a valid choice");
		}
		
	}

}
