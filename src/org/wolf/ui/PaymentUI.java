package org.wolf.ui;

import java.io.BufferedReader;
import java.io.IOException;

import org.wolf.crud.PaymentsCRUD;

public class PaymentUI {

	public static void paymentUI(BufferedReader reader) throws IOException {
		System.out.println("1.getAllPayments");
		System.out.println("2.getPaymentsForBill");
		System.out.println("3.getPaymentsforPayment");
		System.out.println("4.insertPayment");
		System.out.println("5.updatePayment");
		System.out.println("6.deletePayment");
		System.out.println("7.exit");
		Integer payment_id;
		Integer bill_id;
		String payer_ssn;
		String bill_address; 
		String policy_no;
		String card_no;
		Double amountPaid;
		String[] args;
		String input = reader.readLine();
		switch(input){
		case "1":
			PaymentsCRUD.getAllPayments().forEach(System.out::println);
			break;
		case "2":
			System.out.println("Enter billId");
			bill_id = Integer.parseInt(reader.readLine());
			System.out.println(PaymentsCRUD.getPaymentsForBill(bill_id));
			break;
		case "3":
			System.out.println("Enter paymentId");
			payment_id = Integer.parseInt(reader.readLine());
			System.out.println(PaymentsCRUD.getPaymentsForBill(payment_id));
			break;
		case "4":
			System.out.println("Enter | separated Integer bill_id, String payer_ssn, String bill_address, String policy_no, String card_no, Double amountPaid");
			args = reader.readLine().split("[|]");
			bill_id = Integer.parseInt(args[0]);
			payer_ssn = args[1];
			bill_address = args[2]; 
			policy_no = args[3];
			card_no = args[4]; 
			amountPaid = Double.parseDouble(args[5]);
			System.out.println("PaymentId is");
			System.out.println(PaymentsCRUD.insertPayment(bill_id, payer_ssn, bill_address, policy_no, card_no, amountPaid));
			break;
		case "5":
			System.out.println("Enter | separated Integer payment_id, Integer bill_id, String payer_ssn, String billing_address, String policy_no, String card_no, String amount_paid, String payment_date");
			args = reader.readLine().split("[|]");
			payment_id = Integer.parseInt(args[0]);
			bill_id = Integer.parseInt(args[1]);
			payer_ssn = args[2];
			bill_address = args[3]; 
			policy_no = args[4];
			card_no = args[5]; 
			amountPaid = Double.parseDouble(args[6]);
			String payment_date = args[7];
			if(PaymentsCRUD.updatePayment(payment_id, bill_id, payer_ssn, bill_address, policy_no, card_no, amountPaid, payment_date))
				System.out.println("Operation Successful");
			else
				System.out.println("Operation Failed");
			break;
		case "6":
			System.out.println("Enter paymentId");
			payment_id = Integer.parseInt(reader.readLine());
			if(PaymentsCRUD.deletePayment(payment_id)){
				System.out.println("Operation Successful");
			} else{
				System.out.println("Operation Failed");
			}
			break;
		case "7":
			return;
		default:
			System.out.println("Enter a valid choice");
		}
	}

}
