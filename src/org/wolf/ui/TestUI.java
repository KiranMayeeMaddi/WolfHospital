package org.wolf.ui;

import java.io.BufferedReader;
import java.io.IOException;

import org.wolf.crud.TestCRUD;

public class TestUI {

	public static void testUI(BufferedReader reader) throws IOException {
		System.out.println("1.viewAllTest");
		System.out.println("2.viewTestbyId");
		System.out.println("3.insertTest");
		System.out.println("4.updateTest");
		System.out.println("5.deleteTest");
		System.out.println("6.exit");
		
		Integer test_id;
		String test_name;
		String lab_name;
		Integer specialized_doctor;
		Double fees;
		String[] args;
		
		String input = reader.readLine();
		
		switch(input){
		case "1":
			TestCRUD.viewTest().forEach(System.out::println);
			break;
		case "2":
			System.out.println("Enter testId");
			test_id =  Integer.parseInt(reader.readLine());
			System.out.println(TestCRUD.viewTest(test_id));
			break;
		case "3":
			System.out.println("Enter | separated String test_name, String lab_name, Integer specialized_doctor, Integer fees");
			args = reader.readLine().split("[|]");
			test_name = args[0];
			lab_name = args[1];
			specialized_doctor = Integer.parseInt(args[2]);
			fees = Double.parseDouble(args[3]);
			System.out.println("Newly generated test Id is");
			System.out.println(TestCRUD.insertTest(test_name, lab_name, specialized_doctor, fees));
			break;
		case "4":
			System.out.println("Enter | separated Integer test_id, String test_name, String lab_name, Integer specialized_doctor, Integer fees");
			args = reader.readLine().split("[|]");
			test_id = Integer.parseInt(args[0]);
			test_name = args[1];
			lab_name = args[2];
			specialized_doctor = Integer.parseInt(args[3]);
			fees = Double.parseDouble(args[4]);
			if(TestCRUD.updateTest(test_id, test_name, lab_name, specialized_doctor, fees)){
				System.out.println("Test Record Updated Successfully");
			} else{
				System.out.println("Operation Failed");
			}
			break;
		case "5":
			System.out.println("Enter test_id");
			test_id = Integer.parseInt(reader.readLine());
			if(TestCRUD.deleteTest(test_id)){
				System.out.println("Test Record Deleted Successfully");
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
