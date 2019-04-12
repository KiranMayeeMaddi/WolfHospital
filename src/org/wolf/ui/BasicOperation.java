package org.wolf.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;

public class BasicOperation {

	public static void basicOperations(BufferedReader reader) throws IOException, SQLException {
		System.out.println("Chose your field of operations");
		System.out.println("1.Billing Account");
		System.out.println("2.Medical Record");
		System.out.println("3.Patient");
		System.out.println("4.Payments");
		System.out.println("5.Staff");
		System.out.println("6.Test_MedicalRecord");
		System.out.println("7.Test");
		System.out.println("8.Ward_Nurse");
		System.out.println("9.Ward_patient");
		System.out.println("10.Ward");
		System.out.println("11.TreatmentPlans");
		System.out.println("12.Exit");
		String input = reader.readLine();
		switch(input){
		case "1": BillingAccountUI.billingAccountUI(reader);
		break;
		case "2": MedicalRecordUI.medicalRecordUI(reader);
		break;
		case "3": PatientUI.patientUI(reader);
		break;
		case "4": PaymentUI.paymentUI(reader);
		break;
		case "5": StaffUI.staffUI(reader);
		break;
		case "6": TestMedicalRecordUI.testMedicalRecordUI(reader);
		break;
		case "7": TestUI.testUI(reader);
		break;
		case "8": WardNurseUI.wardNurseUI(reader);
		break;
		case "9": WardPatientUI.wardPatientUI(reader);
		break;
		case "10": WardUI.wardUI(reader);
		break;
		case "11": TreatmentPlanUI.treatmentPlanUI(reader);
		break;
		case "12": return;
		default: System.out.println("Please enter valid input");
		}
	}

}
