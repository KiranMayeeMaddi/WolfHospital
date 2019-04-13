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
		System.out.println("6.currentWardUsage");
		System.out.println("7.noOfPatientsTimePeriod");
		System.out.println("8.getPatientsForResponsibleDoc");
		System.out.println("9.getStaffByJobtitle");
		System.out.println("10.Exit");
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
			Reports.patientMedicalHistory(patientId);
			break;
		case "2":
			System.out.println("Enter | separated patientId, startTime, endTime");
			args = reader.readLine().split("[|]");
			patientId = Integer.parseInt(args[0]);
			startTime = args[1];
			endTime = args[2];
			Reports.patientMedicalHistoryTimePeriod(patientId, startTime, endTime);
			break;
		case "3":
			System.out.println("Enter | separated startDate, endDate, bedId");
			args = reader.readLine().split("[|]");
			startDate = args[0];
			endDate = args[1];
			bedId = Integer.parseInt(args[2]);
			Reports.bedUsageTimePeriod(startDate, endDate, bedId);
			break;
		case "4":
			System.out.println("Enter bedId");
			bedId = Integer.parseInt(reader.readLine());
			Reports.currentBedUsage(bedId);
			break;
		case "5":
			System.out.println("Enter | separated startDate, endDate, wardId");
			args = reader.readLine().split("[|]");
			startDate = args[0];
			endDate = args[1];
			wardId = Integer.parseInt(args[2]);
			Reports.wardUsageTimePeriod(startDate, endDate, wardId);
			break;
		case "6":
			wardId = Integer.parseInt(reader.readLine());
			Reports.currentWardUsage(wardId);
			break;
		case "7":
			args = reader.readLine().split("[|]");
			startDate = args[0];
			endDate = args[1];
			Reports.noOfPatientsTimePeriod(startDate, endDate);
			break;
		case "8":
			responsibleDoc = Integer.parseInt(reader.readLine());
			Reports.getPatientsForResponsibleDoc(responsibleDoc);
			break;
		case "9":
			jobTitle = reader.readLine();
			Reports.getStaffByJobtitle(jobTitle);
			break;
		case "10":
			return;
		default:
			System.out.println("Enter a valid choice");
		}
	}

}
