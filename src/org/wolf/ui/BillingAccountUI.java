package org.wolf.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;

import org.wolf.crud.BillingAccountCRUD;

public class BillingAccountUI {

	public static void billingAccountUI(BufferedReader reader) throws IOException, SQLException {
		System.out.println("1.ViewAll");
		System.out.println("2.ViewByBillId");
		System.out.println("3.ViewByRecordId");
		System.out.println("4.ViewByPatientId");
		System.out.println("5.GetUnpaidBillsForPatientId");
		System.out.println("6.GetLatestUnpaidBill");
		System.out.println("7.UpdateMedicalFee");
		System.out.println("8.insertBillingAccount");
		System.out.println("9.updateBillingAccount");
		System.out.println("10.deleteBillingAccount");
		System.out.println("11.amountDue");
		System.out.println("12.getPaymentStatus");
		System.out.println("13.exit");
		String input = reader.readLine();

		Integer billId;
		Integer recordId;
		Integer patient_id;
		Integer record_id;
		String payment_status; 
		Double reg_fee; 
		Double accom_fee; 
		Double medical_fee;
		String[] args;
		
		switch(input){
		case "1": 
			BillingAccountCRUD.viewBillingAccounts().forEach(System.out::println);
			break;
		case "2": 
			System.out.println("Enter billId");
			billId = Integer.parseInt(reader.readLine());
			System.out.println(BillingAccountCRUD.viewBillingAccountsByBill(billId));
			break;
		case "3":
			System.out.println("Enter recordId");
			recordId = Integer.parseInt(reader.readLine());
			System.out.println(BillingAccountCRUD.viewBillingAccountsByRecord(recordId));
			break;
		case "4":
			System.out.println("Enter patientId");
			patient_id = Integer.parseInt(reader.readLine());
			BillingAccountCRUD.getBillingAccountsForPatient(patient_id).forEach(System.out::println);
			break;
		case "5":
			System.out.println("Enter patientId");
			patient_id = Integer.parseInt(reader.readLine());
			BillingAccountCRUD.getUnpaidBillingAccountsForPatient(patient_id).forEach(System.out::println);
			break;
		case "6":
			System.out.println("Enter patientId");
			patient_id = Integer.parseInt(reader.readLine());
			System.out.println(BillingAccountCRUD.getLatestUnpaidBill(patient_id));
			break;
		case "7":
			System.out.println("Enter recordID, fee | separated");
			args = reader.readLine().split("[|]");
			if(BillingAccountCRUD.updateMedicalFee(Integer.parseInt(args[0]), Double.parseDouble(args[1]))){
				System.out.println("Operation Successful");
			} else{
				System.out.println("Operation Failed");
			}
			break;
		case "8":
			System.out.println("patient_id,record_id,payment_status,reg_fee,accom_fee,medical_fee separated by |");
			args = reader.readLine().split("[|]");
			patient_id = Integer.parseInt(args[0]);
			record_id = Integer.parseInt(args[1]);
			payment_status = args[2];
			reg_fee = Double.parseDouble(args[3]);
			if (args[4].isEmpty())
				accom_fee = 0.0;
			else
				accom_fee = Double.parseDouble(args[4]);
			medical_fee = Double.parseDouble(args[5]);
			if(BillingAccountCRUD.insertBillingAccount(patient_id, record_id, payment_status, reg_fee, accom_fee, medical_fee)){
				System.out.println("Operation Successful");
			} else{
				System.out.println("Operation Failed");
			}
			break;
		case "9":
			System.out.println("bill_id,patient_id,record_id,payment_status,reg_fee,accom_fee,medical_fee separated by |");
			args = reader.readLine().split("[|]");
			billId = Integer.parseInt(args[0]);
			patient_id = Integer.parseInt(args[1]);
			record_id = Integer.parseInt(args[2]);
			payment_status = args[3];
			reg_fee = Double.parseDouble(args[4]);
			if (args[4].isEmpty())
				accom_fee = 0.0;
			else
				accom_fee = Double.parseDouble(args[4]);
			medical_fee = Double.parseDouble(args[6]);
			if(BillingAccountCRUD.updateBillingAccount(billId, patient_id, record_id, payment_status, reg_fee, accom_fee, medical_fee)){
				System.out.println("Operation Successful");
			} else{
				System.out.println("Operation Failed");
			}
			break;
		case "10":
			System.out.println("billId");
			billId = Integer.parseInt(reader.readLine());
			if(BillingAccountCRUD.deleteBillingAccount(billId)){
				System.out.println("Operation Successful");
			} else{
				System.out.println("Operation Failed");
			}
			break;
		case "11":
			System.out.println("Enter bill_id");
			billId = Integer.parseInt(reader.readLine());
			System.out.println("Amount due = "+BillingAccountCRUD.amountDue(billId));
			break;
		case "12":
			System.out.println("Enter bill_id");
			billId = Integer.parseInt(reader.readLine());
			System.out.println("Payment status = "+BillingAccountCRUD.getPaymentStatus(billId));
			break;
		case "13":
			return;
		default:
			System.out.println("Please enter valid value");
		}
	}

}
