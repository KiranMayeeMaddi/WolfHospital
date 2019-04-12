package org.wolf.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;

import org.wolf.crud.StaffCRUD;

public class StaffUI {

	public static void staffUI(BufferedReader reader) throws NumberFormatException, IOException {
		
		System.out.println("1.viewAllStaff");
		System.out.println("2.viewStaffById");
		System.out.println("3.getStaffByName");
		System.out.println("4.getStaffIdByprofTitle");
		System.out.println("5.getStaffIdByjobTitle");
		System.out.println("6.getStaffIdBydept");
		System.out.println("7.insertStaff");
		System.out.println("8.updateStaff");
		System.out.println("9.deleteStaff");
		System.out.println("10.exit");
		
		Integer id;
		String name;
		String job_title;
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
			StaffCRUD.viewStaff().forEach(System.out::println);
			break;
		case "2":
			System.out.println("Enter staffId");
			id = Integer.parseInt(reader.readLine());
			System.out.println(StaffCRUD.viewStaff(id));
			break;
		case "3":
			System.out.println("Enter name");
			name = reader.readLine();
			StaffCRUD.getStaffByName(name).forEach(System.out::println);
			break;
		case "4":
			System.out.println("Enter profTitle");
			profTitle = reader.readLine();
			StaffCRUD.getStaffIdprofTitle(profTitle).forEach(System.out::println);
			break;
		case "5":
			System.out.println("Enter jobTitle");
			job_title = reader.readLine();
			StaffCRUD.getStaffIdjobTitle(job_title).forEach(System.out::println);
			break;
		case "6":
			System.out.println("Enter dept");
			dept = reader.readLine();
			StaffCRUD.getStaffIddept(dept).forEach(System.out::println);
			break;
		case "7":
			System.out.println("Enter | separated String name, String jobTitle, String profTitle, String dob, String gender, String pno, String address, String dept, Double sal");
			args = reader.readLine().split("[|]");
			System.out.println(Arrays.toString(args));
			name = args[0];
			job_title = args[1];
			profTitle = args[2]; 
			dob = args[3];
			gender =  args[4];
			pno = args[5];
			address = args[6];
			dept = args[7];
			sal = Double.parseDouble(args[8]);
			System.out.println("New staffId is");
			System.out.println(StaffCRUD.insertStaff(name, job_title, profTitle, dob, gender, pno, address, dept, sal));
			break;
		case "8":
			System.out.println("Enter | separated Integer id, String name, String jobTitle, String profTitle, String dob, String gender, String pno, String address, String dept, Double sal");
			args = reader.readLine().split("[|]");
			id = Integer.parseInt(args[0]);
			name = args[1];
			job_title = args[2];
			profTitle = args[3]; 
			dob = args[4];
			gender =  args[5];
			pno = args[6];
			address = args[7];
			dept = args[8];
			sal = Double.parseDouble(args[9]);
			if(StaffCRUD.updateStaff(id, name,job_title, profTitle, dob, gender, pno, address, dept, sal)){
				System.out.println("Operation Successful");
			} else{
				System.out.println("Operation Failed");	
			}
			break;
		case "9":
			System.out.println("Enter staffId");
			id = Integer.parseInt(reader.readLine());
			if(StaffCRUD.deleteStaff(id)){
				System.out.println("Operation Successful");
			} else{
				System.out.println("Operation Failed");
			}
			break;
		case "10":
			return;
		default:
			System.out.println("Enter a valid choice");
	}
	
}
}
