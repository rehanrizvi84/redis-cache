package com.fedex.smm.dto;

import java.util.List;

import com.fedex.smm.model.Address;
import com.fedex.smm.model.Employee;
import com.fedex.smm.model.Holiday;
import com.fedex.smm.model.Location;
import com.fedex.smm.model.ServiceDay;

public class LoadCacheResponse {

	private String message;

	private List<Employee> employees;

	private List<Address> addresses;

	private List<Location> locations;

	private List<ServiceDay> serviceDays;

	private List<Holiday> holidays;

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

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public List<Location> getLocations() {
		return locations;
	}

	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}

	public List<ServiceDay> getServiceDays() {
		return serviceDays;
	}

	public void setServiceDays(List<ServiceDay> serviceDays) {
		this.serviceDays = serviceDays;
	}

	public List<Holiday> getHolidays() {
		return holidays;
	}

	public void setHolidays(List<Holiday> holidays) {
		this.holidays = holidays;
	}

}
