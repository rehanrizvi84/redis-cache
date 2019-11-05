package com.fedex.smm.service;

import java.util.List;

import com.fedex.smm.model.Employee;

/**
 * Created by JavaDeveloperZone on 04-04-2018.
 */

public interface EmployeeService {

	List<Employee> findAll();

	Employee save(Employee employee);

	Employee findById(Long id);

	void delete(long employeeId);

	void cacheEmployeeDetails(boolean checkFlag) throws Exception;
}
