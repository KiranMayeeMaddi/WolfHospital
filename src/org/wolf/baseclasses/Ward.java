package org.wolf.baseclasses;

public class Ward {
	
	public Integer id;
	public Integer capacity;
	public Double chargesPerDay;
	
	public Ward(Integer id, Integer capacity, Double chargesPerDay) {
		super();
		this.id = id;
		this.capacity = capacity;
		this.chargesPerDay = chargesPerDay;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCapacity() {
		return capacity;
	}
	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}
	public Double getChargesPerDay() {
		return chargesPerDay;
	}
	public void setChargesPerDay(Double chargesPerDay) {
		this.chargesPerDay = chargesPerDay;
	}
	@Override
	public String toString() {
		return "Ward [id=" + id + ", capacity=" + capacity + ", chargesPerDay=" + chargesPerDay + "]";
	}	
}
