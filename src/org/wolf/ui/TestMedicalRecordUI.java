package org.wolf.ui;

import java.io.BufferedReader;
import java.io.IOException;

import org.wolf.crud.Test_MedicalRecordsCRUD;

public class TestMedicalRecordUI {

	public static void testMedicalRecordUI(BufferedReader reader) throws IOException {
		System.out.println("1.viewAllTestMedicalRecords");
		System.out.println("2.viewTestMedicalRecordsByRecordId");
		System.out.println("3.viewTestMedicalRecordsByTestId");
		System.out.println("4.viewTestMedicalRecordsByRecordIdAndTestId");
		System.out.println("5.insertTest_MedicalRecords");
		System.out.println("6.updateTest_MedicalRecords");
		System.out.println("7.deleteTest_MedicalRecords");
		System.out.println("8.exit");
		
		Integer record_id;
		Integer test_id;
		String test_date;
		String result;
		
		String[] args;
		String input = reader.readLine();
		switch(input){
		case "1":
			Test_MedicalRecordsCRUD.viewTestMedicalRecords().forEach(System.out::println);
			break;
		case "2":
			System.out.println("Enter rercordId");
			record_id = Integer.parseInt(reader.readLine());
			Test_MedicalRecordsCRUD.viewTestMedicalRecordsByRecordId(record_id).forEach(System.out::println);
			break;
		case "3":
			System.out.println("Enter testId");
			test_id = Integer.parseInt(reader.readLine());
			Test_MedicalRecordsCRUD.viewTestMedicalRecordsByTestId(test_id).forEach(System.out::println);
			break;
		case "4":
			System.out.println("Enter | separated record_id, test_id");
			args = reader.readLine().split("[|]");
			record_id = Integer.parseInt(args[0]);
			test_id = Integer.parseInt(args[1]);
			
			System.out.println(Test_MedicalRecordsCRUD.viewTestMedicalRecords(record_id, test_id));
			break;
			
		case "5":
			System.out.println("Enter | separated Integer record_id, Integer test_id, String test_date, String result");
			args = reader.readLine().split("[|]");
			record_id = Integer.parseInt(args[0]);
			test_id = Integer.parseInt(args[1]);
			test_date = args[2];
			result = args[3];
			if(Test_MedicalRecordsCRUD.insertTest_MedicalRecords(record_id, test_id, test_date, result)){
				
			} else{
				
			}
			break;
		case "6":
			System.out.println("Enter | separated Integer record_id, Integer test_id, String test_date, String result");
			args = reader.readLine().split("[|]");
			record_id = Integer.parseInt(args[0]);
			test_id = Integer.parseInt(args[1]);
			test_date = args[2];
			result = args[3];
			if(Test_MedicalRecordsCRUD.updateTest_MedicalRecords(record_id, test_id, test_date, result)){
				
			} else{
				
			}
			break;
		case "7":
			System.out.println("Enter | separated record_id, test_id");
			args = reader.readLine().split("[|]");
			record_id = Integer.parseInt(args[0]);
			test_id = Integer.parseInt(args[1]);
			if(Test_MedicalRecordsCRUD.deleteTest_MedicalRecords(record_id, test_id)){
				System.out.println("Operation Successful");
			} else{
				System.out.println("Operation Failed");
			}
			break;
		case "8":
			return;
		default:
			System.out.println("Enter a valid choice");
		}
	}

}
