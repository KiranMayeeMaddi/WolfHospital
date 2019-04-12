package org.wolf.ui;

import java.io.BufferedReader;
import java.io.IOException;

import org.wolf.crud.PatientCRUD;

public class PatientUI {

	public static void patientUI(BufferedReader reader) throws IOException {
		System.out.println("1.ViewAll");
		System.out.println("2.ViewbyId");
		System.out.println("3.ViewByName");
		System.out.println("4.ViewBySSN");
		System.out.println("5.insertPatient");
		System.out.println("6.updatePatient");
		System.out.println("7.delete Patient");
		System.out.println("8.exit");
		String input = reader.readLine();
		
		Integer id;
		String name;
		String ssn;
		String dob;
		String gender;
		String pno;
		String address;
		String[] args;
		
		switch(input){
		case "1": 
			PatientCRUD.viewPatients().forEach(System.out::println);
			break;
		case "2": 
			System.out.println("Enter patientId");
			id = Integer.parseInt(reader.readLine());
			System.out.println(PatientCRUD.viewPatient(id));
			break;
		case "3": 
			System.out.println("Enter patient name");
			name = reader.readLine();
			PatientCRUD.getPatientIdByName(name).forEach(System.out::println);
			break;
		case "4":
			System.out.println("Enter SSN");
			ssn = reader.readLine();
			System.out.println(PatientCRUD.getPatientIdBySSN(ssn));
			break;
		case "5":
			System.out.println("Enter name, ssn, dob, gender,pno, address separated by |");
			args = reader.readLine().split("[|]");
			name = args[0];
			ssn = args[1];
			dob = args[2];
			gender = args[3];
			pno = args[4];
			address = args[5];
			System.out.println("Newly generated patient Id = ");
			System.out.println(PatientCRUD.insertPatient(name, ssn, dob, gender, pno, address));
			break;
		case "6":
			System.out.println("Enter id, name, ssn, dob, gender,pno, address separated by |");
			args = reader.readLine().split("[|]");
			id = Integer.parseInt(args[0]);
			name = args[1];
			ssn = args[2];
			dob = args[3];
			gender = args[4];
			pno = args[5];
			address = args[6];
			if(PatientCRUD.updatePatient(id, name, ssn, dob, gender, pno, address)){
				System.out.println("Operation successful");
			} else{
				System.out.println("Operation failed");
			}
			break;
		case "7":
			System.out.println("Enter PatientId");
			id = Integer.parseInt(reader.readLine());
			if(PatientCRUD.deletePatient(id)){
				System.out.println("Operation successful");
			} else{
				System.out.println("Operation failed");
			}
			break;
			
		case "8":
			return;
		default:
			System.out.println("Please enter valid value");
		}
	}

}
