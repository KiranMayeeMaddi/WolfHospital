package org.wolf.baseclasses;

public class TreatmentPlan {

	public Integer id;
	public String plan;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPlan() {
		return plan;
	}
	public void setPlan(String plan) {
		this.plan = plan;
	}
	@Override
	public String toString() {
		return "TreatmentPlan [id=" + id + ", plan=" + plan + "]";
	}
}
