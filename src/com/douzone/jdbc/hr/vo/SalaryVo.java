package com.douzone.jdbc.hr.vo;

public class SalaryVo 
{
	private String name;
	private long minSalary;
	private long maxSalary;
	private long salary;
	
	public long getSalary() {
		return salary;
	}

	public void setSalary(long salary) {
		this.salary = salary;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public long getMinSalary() {
		return minSalary;
	}
	public void setMinSalary(long inputMin) {
		this.minSalary = inputMin;
	}
	public long getMaxSalary() {
		return maxSalary;
	}
	public void setMaxSalary(long maxSalary) {
		this.maxSalary = maxSalary;
	}
}
