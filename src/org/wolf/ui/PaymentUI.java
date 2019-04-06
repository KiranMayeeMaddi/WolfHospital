package org.wolf.ui;

import java.io.BufferedReader;
import java.io.IOException;

import org.wolf.crud.PaymentsCRUD;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

public class PaymentUI {

	public static void paymentUI(BufferedReader reader) throws IOException {
		System.out.println("1.getAllPayments");
		System.out.println("2.getPaymentsForBill");
		System.out.println("3.insertPayment");
		System.out.println("4.deletePayment");
		System.out.println("5.exit");
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
			System.out.println(PaymentsCRUD.getAllPayments());
			break;
		case "2":
			System.out.println("Enter billId");
			bill_id = Integer.parseInt(reader.readLine());
			System.out.println(PaymentsCRUD.getPaymentsForBill(bill_id));
			break;
		case "3":
			System.out.println("Enter space separated Integer bill_id, String payer_ssn, String bill_address, String policy_no, String card_no, Double amountPaid");
			args = reader.readLine().split(" ");
			bill_id = Integer.parseInt(args[0]);
			payer_ssn = args[1];
			bill_address = args[2]; 
			policy_no = args[3];
			card_no = args[4]; 
			amountPaid = Double.parseDouble(args[5]);
			System.out.println("PaymentId is");
			System.out.println(PaymentsCRUD.insertPayment(bill_id, payer_ssn, bill_address, policy_no, card_no, amountPaid));
			break;
		case "4":
			System.out.println("Enter paymentId");
			payment_id = Integer.parseInt(reader.readLine());
			if(PaymentsCRUD.deletePayment(payment_id)){
				System.out.println("Operation Successful");
			} else{
				System.out.println("Operation Failed");
			}
			break;
		case "5":
			return;
		default:
			System.out.println("Enter a valid choice");
		}
	}

}
