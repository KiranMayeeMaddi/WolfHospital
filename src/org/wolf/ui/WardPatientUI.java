package org.wolf.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;

import org.wolf.crud.Ward_PatientCRUD;

public class WardPatientUI {

	public static void wardPatientUI(BufferedReader reader) throws IOException, SQLException {
		System.out.println("1.ViewAll");
		System.out.println("2.ViewbyId");
		System.out.println("3.insertWardPatient");
		System.out.println("4.updateWardPatient");
		System.out.println("5.updateWardEndtime");
		System.out.println("6.deleteWardPatient");
		System.out.println("7.exit");
		
		String input = reader.readLine();
		Integer checkin_id;
		Integer patient_id;
		Integer ward_id;
		Integer bed_id; 
		String start_time; 
		String end_time;
		String[] args;
		
		switch(input){
		case "1":
			Ward_PatientCRUD.viewWardPatients().forEach(System.out::println);
			break;
		case "2":
			System.out.println("Enter checkinId");
			checkin_id = Integer.parseInt(reader.readLine());
			System.out.println(Ward_PatientCRUD.viewWardPatients(checkin_id));
			break;
		case "3":
			System.out.println("Enter patient_id, end_time, ward_id, bed_id separated by |");
			args = reader.readLine().split("[|]");
			patient_id = Integer.parseInt(args[0]);
			end_time = args[1];
			ward_id = Integer.parseInt(args[2]);
			bed_id = Integer.parseInt(args[3]);
			System.out.println("New checkinId is");
			System.out.println(Ward_PatientCRUD.insertWardPatient(patient_id, end_time, ward_id, bed_id));
			break;
		case "4":
			System.out.println("Enter checkinId, patient_id, start_time, end_time, ward_id, bed_id separated by |");
			args = reader.readLine().split("[|]");
			checkin_id = Integer.parseInt(args[0]);
			patient_id = Integer.parseInt(args[1]);
			start_time= args[2];
			end_time = args[3];
			ward_id = Integer.parseInt(args[4]);
			bed_id = Integer.parseInt(args[5]);
			if(Ward_PatientCRUD.updateWardPatient(checkin_id, patient_id, ward_id, bed_id, start_time, end_time)){
				System.out.println("Operation Successful");
			} else{
				System.out.println("Operation Failed");
			}
			break;
		case "5":
			System.out.println("Enter checkInId");
			checkin_id = Integer.parseInt(reader.readLine());
			if(Ward_PatientCRUD.updateWardEndtime(checkin_id)){
				System.out.println("Operation Successful");
			} else{
				System.out.println("Operation Failed");
			}
			break;
		case "6":
			System.out.println("Enter checkInId");
			checkin_id = Integer.parseInt(reader.readLine());
			if(Ward_PatientCRUD.deleteWardPatient(checkin_id)){
				System.out.println("Operation Successful");
			} else{
				System.out.println("Operation Failed");
			}
			break;
		case "7":
			return;
		default:
			System.out.println("Enter a valid choice");
		}
	}

}
