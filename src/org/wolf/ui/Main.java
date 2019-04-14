package org.wolf.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) {
		while(true){
			System.out.println("Welcome to Wolf Hospital");
			System.out.println("Menu:");
			System.out.println("1.CheckIn Patient");
			System.out.println("2.CheckOut Patient");
			System.out.println("3.Undergo Tests");
			System.out.println("4.Create Medical Record");
			System.out.println("5.End Medical Record");
			System.out.println("6.Pay");
			System.out.println("7.Basic Operations");
			System.out.println("8.Reports");
			System.out.println("9.Exit");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			try {
				String choice = reader.readLine();
				switch(choice){
				case "1": Menu_Layer1.checkInPatient(reader);
				break;
				case "2": Menu_Layer1.checkOutPatient(reader);
				break;
				case "3": Menu_Layer1.undergoTests(reader);
				break;
				case "4": Menu_Layer1.createTreatment(reader);
				break;
				case "5": Menu_Layer1.endTreatment(reader);
				break;
				case "6": Menu_Layer1.pay(reader);
				break;
				case "7": BasicOperation.basicOperations(reader);
				break;
				case "8": ReportsUI.operations(reader);
				break;
				case "9": System.out.println("Bye");
				return;
				default: System.out.println("Please enter a valid choice");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e){
				e.printStackTrace();
			}
			
		}
		

	}

}
