package com.fedex.smm.dto;

import java.util.List;

import com.fedex.smm.model.Employee;

public class EmployeeResponse {

	private String message;

	private List<Employee> employees;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
}
