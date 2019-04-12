package org.wolf.ui;

import java.io.BufferedReader;
import java.io.IOException;

import org.wolf.crud.MedicalRecordCRUD;

public class MedicalRecordUI {

	public static void medicalRecordUI(BufferedReader reader) throws IOException {
		System.out.println("1.viewAllMedicalRecords");
		System.out.println("2.viewMedicalRecords");
		System.out.println("3.getMedicalRecordsForPatient");
		System.out.println("4.getMedicalRecordsForDoctor");
		System.out.println("5.insertMedicalRecord");
		System.out.println("6.updateMedicalRecord");
		System.out.println("7.deleteMedicalRecord");
		System.out.println("8.exit");
		
		
		Integer recordId;
		Integer patient_id;
		String start_date;
		String end_date;
		String diagnosis;
		String prescription; 
		Integer responsible_doctor;
		Integer treatmentPlan;
		String[] args;
		
		String input = reader.readLine();
		switch(input){
		
		case "1":
			MedicalRecordCRUD.viewMedicalRecords().forEach(System.out::println);
			break;
		case "2":
			System.out.println("Enter med. recordId");
			recordId = Integer.parseInt(reader.readLine());
			System.out.println(MedicalRecordCRUD.viewMedicalRecords(recordId));
			break;
		case "3":
			System.out.println("Enter patientId");
			patient_id = Integer.parseInt(reader.readLine());
			MedicalRecordCRUD.getMedicalRecordsForPatient(patient_id).forEach(System.out::println);
			break;
		case "4":
			System.out.println("Enter doctorId");
			responsible_doctor = Integer.parseInt(reader.readLine());
			MedicalRecordCRUD.getMedicalRecordsForDoctor(responsible_doctor).forEach(System.out::println);
			break;
		case "5":
			System.out.println("Enter | separated Integer patient_id, String start_date,String end_date, String diagnosis, String prescription, Integer responsible_doctor, Integer treatmentPlan");
			args = reader.readLine().split("[|]");
			patient_id = Integer.parseInt(args[0]);
			start_date = args[1];
			end_date = args[2];
			diagnosis = args[3];
			prescription = args[4];
			responsible_doctor = Integer.parseInt(args[5]);
			treatmentPlan = Integer.parseInt(args[6]);
			System.out.println("New med record Id is");	
			System.out.println(MedicalRecordCRUD.insertMedicalRecord(patient_id, start_date, end_date, diagnosis, prescription, responsible_doctor,treatmentPlan));
			break;
		case "6":
			System.out.println("Enter | separated Integer recordId, Integer patient_id, String start_date,String end_date, String diagnosis, String prescription, Integer responsible_doctor, Integer treatmentPlan");
			args = reader.readLine().split("[|]");
			recordId = Integer.parseInt(args[0]);
			patient_id = Integer.parseInt(args[1]);
			start_date = args[2];
			end_date = args[3];
			diagnosis = args[4];
			prescription = args[5];
			responsible_doctor = Integer.parseInt(args[6]);
			treatmentPlan = Integer.parseInt(args[7]);
			if(MedicalRecordCRUD.updateMedicalRecord(recordId, patient_id, start_date, end_date, diagnosis, prescription, responsible_doctor,treatmentPlan)){
				System.out.println("Operation Successful");
			} else{
				System.out.println("Operation Failed");
			}
			break;
		case "7":
			System.out.println("Enter med record Id");
			recordId = Integer.parseInt(reader.readLine());
			if(MedicalRecordCRUD.deleteMedicalRecord(recordId)){
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
