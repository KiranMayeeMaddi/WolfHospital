package org.wolf.baseclasses;

public class StaffGroupedDetails {
	public String job_title;
	public Integer count_staff;
	public Double total_salary;
	public Double average_salary;
	public Double max_salary;
	public Double min_salary;
	
	public StaffGroupedDetails(String job_title, Integer count_staff, Double total_salary, Double average_salary,
			Double max_salary, Double min_salary) {
		this.job_title = job_title;
		this.count_staff = count_staff;
		this.total_salary = total_salary;
		this.average_salary = average_salary;
		this.max_salary = max_salary;
		this.min_salary = min_salary;
	}

	@Override
	public String toString() {
		return "StaffGroupedDetails [job_title=" + job_title + ", count_staff=" + count_staff + ", total_salary="
				+ total_salary + ", average_salary=" + average_salary + ", max_salary=" + max_salary + ", min_salary="
				+ min_salary + "]";
	}
}
