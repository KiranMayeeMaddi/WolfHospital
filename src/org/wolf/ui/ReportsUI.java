package org.wolf.ui;

import java.io.BufferedReader;
import java.io.IOException;

import org.wolf.reports.Reports;

public class ReportsUI {

	public static void operations(BufferedReader reader) throws IOException {
		System.out.println("1.patientMedicalHistory");
		System.out.println("2.patientMedicalHistoryTimePeriod");
		System.out.println("3.bedUsageTimePeriod");
		System.out.println("4.currentBedUsage");
		System.out.println("5.wardUsageTimePeriod");
		System.out.println("6.wardUsageByIdTimePeriod");
		System.out.println("7.currentWardUsage");
		System.out.println("8.currentWardUsageById");
		System.out.println("9.noOfPatientsTimePeriod");
		System.out.println("10.getPatientsForResponsibleDoc");
		System.out.println("11.getStaffByJobtitle");
		System.out.println("12.Exit");
		
		String[] args;
		Integer patientId;
		String startTime;
		String endTime;
		String startDate;
		String endDate;
		Integer bedId;
		Integer wardId;
		Integer responsibleDoc;
		String jobTitle;
		String input = reader.readLine();
		switch(input){
		
		case "1":
			System.out.println("Enter patientId");
			patientId = Integer.parseInt(reader.readLine());
			Reports.patientMedicalHistory(patientId).forEach(System.out::println);
			break;
		case "2":
			System.out.println("Enter | separated patientId, startTime, endTime");
			args = reader.readLine().split("[|]");
			patientId = Integer.parseInt(args[0]);
			startTime = args[1];
			endTime = args[2];
			Reports.patientMedicalHistoryTimePeriod(patientId, startTime, endTime).forEach(System.out::println);
			break;
		case "3":
			System.out.println("Enter | separated startDate, endDate");
			args = reader.readLine().split("[|]");
			startDate = args[0];
			endDate = args[1];
			System.out.println(Reports.bedUsageTimePeriod(startDate, endDate));
			break;
		case "4":
			System.out.println(Reports.currentBedUsage());
			break;
		case "5":
			System.out.println("Enter | separated startDate, endDate");
			args = reader.readLine().split("[|]");
			startDate = args[0];
			endDate = args[1];
			System.out.println(Reports.wardUsageTimePeriod(startDate, endDate));
			break;
		case "6":
			System.out.println("Enter | separated startDate, endDate, ward_Id");
			args = reader.readLine().split("[|]");
			startDate = args[0];
			endDate = args[1];
			wardId = Integer.parseInt(args[2]);
			System.out.println(Reports.wardUsageByIdTimePeriod(startDate, endDate, wardId));
			break;
		case "7":
			System.out.println(Reports.currentWardUsage());
			break;
		case "8":
			System.out.println("Enter wardId");
			wardId = Integer.parseInt(reader.readLine());
			System.out.println(Reports.currentWardUsageById(wardId));
			break;
		case "9":
			System.out.println("Enter | separated startDate, endDate");
			args = reader.readLine().split("[|]");
			startDate = args[0];
			endDate = args[1];
			System.out.println(Reports.noOfPatientsTimePeriod(startDate, endDate));
			break;
		case "10":
			System.out.println("Enter doctor id");
			responsibleDoc = Integer.parseInt(reader.readLine());
			Reports.getPatientsForResponsibleDoc(responsibleDoc).forEach(System.out::println);
			break;
		case "11":
			Reports.getStaffByJobtitle().forEach(System.out::println);
			break;
		case "12":
			return;
		default:
			System.out.println("Enter a valid choice");
		}
	}

}
