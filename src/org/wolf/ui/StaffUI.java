package org.wolf.ui;

import java.io.BufferedReader;
import java.io.IOException;

import org.wolf.crud.StaffCRUD;

public class StaffUI {

	public static void staffUI(BufferedReader reader) throws NumberFormatException, IOException {
		
		System.out.println("1.viewAllStaff");
		System.out.println("2.viewStaffById");
		System.out.println("3.getStaffByName");
		System.out.println("4.getStaffIdByprofTitle");
		System.out.println("5.getStaffIdBydept");
		System.out.println("6.insertStaff");
		System.out.println("7.updateStaff");
		System.out.println("8.deleteStaff");
		System.out.println("9.exit");
		
		Integer id;
		String name;
		String profTitle;
		String dob;
		String gender;
		String pno;
		String address; 
		String dept; 
		Double sal;
		String[] args;
		
		String input = reader.readLine();
		switch(input){
		case "1":
			System.out.println(StaffCRUD.viewStaff());
			break;
		case "2":
			System.out.println("Enter staffId");
			id = Integer.parseInt(reader.readLine());
			System.out.println(StaffCRUD.viewStaff(id));
			break;
		case "3":
			System.out.println("Enter name");
			name = reader.readLine();
			System.out.println(StaffCRUD.getStaffByName(name));
			break;
		case "4":
			System.out.println("Enter profTitle");
			profTitle = reader.readLine();
			System.out.println(StaffCRUD.getStaffIdprofTitle(profTitle));
			break;
		case "5":
			System.out.println("Enter dept");
			dept = reader.readLine();
			System.out.println(StaffCRUD.getStaffIddept(dept));
			break;
		case "6":
			System.out.println("Enter space separated String name, String profTitle, String dob, String gender, String pno, String address, String dept, Double sal");
			args = reader.readLine().split(" ");
			name = args[0];
			profTitle = args[1]; 
			dob = args[2];
			gender =  args[3];
			pno = args[4];
			address = args[5];
			dept = args[6];
			sal = Double.parseDouble(args[7]);
			System.out.println("New staffId is");
			System.out.println(StaffCRUD.insertStaff(name, profTitle, dob, gender, pno, address, dept, sal));
			break;
		case "7":
			System.out.println("Enter space separated Integer id, String name, String profTitle, String dob, String gender, String pno, String address, String dept, Double sal");
			args = reader.readLine().split(" ");
			id = Integer.parseInt(args[0]);
			name = args[1];
			profTitle = args[2]; 
			dob = args[3];
			gender =  args[4];
			pno = args[5];
			address = args[6];
			dept = args[7];
			sal = Double.parseDouble(args[8]);
			if(StaffCRUD.updateStaff(id, name, profTitle, dob, gender, pno, address, dept, sal)){
				System.out.println("Operation Successful");
			} else{
				System.out.println("Operation Failed");	
			}
			break;
		case "8":
			System.out.println("Enter staffId");
			id = Integer.parseInt(reader.readLine());
			if(StaffCRUD.deleteStaff(id)){
				System.out.println("Operation Successful");
			} else{
				System.out.println("Operation Failed");
			}
			break;
		case "9":
			return;
		default:
			System.out.println("Enter a valid choice");
	}
	
}
}
