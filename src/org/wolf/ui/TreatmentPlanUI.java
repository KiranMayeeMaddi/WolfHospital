package org.wolf.ui;

import java.io.BufferedReader;
import java.io.IOException;

import org.wolf.crud.TreatmentPlanCRUD;

public class TreatmentPlanUI {
	public static void treatmentPlanUI(BufferedReader reader) throws IOException{
		System.out.println("1.viewAllTreatments");
		System.out.println("2.viewTreatmentbyId");
		System.out.println("3.insertTreatment");
		System.out.println("4.updateTreatment");
		System.out.println("5.deleteTreatment");
		System.out.println("6.exit");
		
		Integer id;
		String plan;
		String[] args;
		
		String input = reader.readLine();
		
		switch(input){
		case "1":
			System.out.println(TreatmentPlanCRUD.getAllTreatmentPlans());
			break;
		case "2":
			System.out.println("Enter treatmentId");
			id = Integer.parseInt(reader.readLine());
			System.out.println(TreatmentPlanCRUD.getTreatmentPlan(id));
			break;
		case "3":
			System.out.println("Enter treatment plan");
			plan = reader.readLine();
			System.out.println("New  Id is");
			System.out.println(TreatmentPlanCRUD.insertTreatmentPlan(plan));
			break;
		case "4":
			System.out.println("Enter | separated id, plan");
			args = reader.readLine().split("[|]");
			id = Integer.parseInt(args[0]);
			plan = args[1];
			if(TreatmentPlanCRUD.updateTreatmentPlan(id, plan)){
				System.out.println("Record updated Successfully");
			} else{
				System.out.println("Operation Failed");
			}
			break;
		case "5":
			System.out.println("Enter treatmentId");
			id = Integer.parseInt(reader.readLine());
			if(TreatmentPlanCRUD.deleteTreatmentPlan(id)){
				System.out.println("Record deleted Successfully");
			} else{
				System.out.println("Operation Failed");
			}
			break;
		case "6":
			return;
		}
		
	}
}
