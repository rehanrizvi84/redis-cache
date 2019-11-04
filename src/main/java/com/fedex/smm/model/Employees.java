package com.fedex.smm.model;

import java.io.Serializable;
import java.util.List;

public class Employees implements Serializable {
	
	public Employees() {
		
	}
	
	public Employees(List<Employee> employeeList) {
		super();
		this.employeeList = employeeList;
	}

	private List<Employee> employeeList;

	public List<Employee> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}

}
