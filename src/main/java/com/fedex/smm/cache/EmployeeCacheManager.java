package com.fedex.smm.cache;

import com.fedex.smm.model.Employee;
import com.fedex.smm.model.Employees;

public interface EmployeeCacheManager {

	void cacheEmployeeDetails(Employee employee);

	public boolean checkEmpty(String empId);

	public boolean isFindAllEmpty();

	void cacheAllEmployeeDetails(Employees employeeList);

	Employee getEmployeeeByIdFromCache(String empId);
}
