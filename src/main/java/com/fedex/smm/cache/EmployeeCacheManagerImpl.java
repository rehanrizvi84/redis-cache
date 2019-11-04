package com.fedex.smm.cache;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.fedex.smm.configuration.RedisUtil;
import com.fedex.smm.model.Employee;
import com.fedex.smm.model.Employees;

@Configuration
public class EmployeeCacheManagerImpl implements EmployeeCacheManager {

	public static final String TABLE_EMPLOYEE = "TABLE_EMPLOYEE";
    public static final String EMPLOYEE = "EMPLOYEE_";
    private RedisUtil<Employee> redisUtilEmployee;
    
    
    
    
    @Autowired
    public EmployeeCacheManagerImpl(RedisUtil<Employee> redisUtilEmployee) {
        this.redisUtilEmployee = redisUtilEmployee;
    }
    @Override
    public void cacheEmployeeDetails(Employee employee){
    	redisUtilEmployee.putMap(TABLE_EMPLOYEE,EMPLOYEE+employee.getEmployeeId(),employee);
    	redisUtilEmployee.setExpire(EMPLOYEE+employee.getEmployeeId(),20,TimeUnit.SECONDS);
    }
    
	/*
	 * @Override public void cacheAllEmployeeDetails(Employees employeeList){
	 * redisUtilEmployee.putMap(TABLE_EMPLOYEE,EMPLOYEE,employeeList);
	 * redisUtilEmployee.setExpire(TABLE_EMPLOYEE,1,TimeUnit.DAYS); }
	 */
    
    
	@Override
	public boolean checkEmpty(String empid) {
		// TODO Auto-generated method stub
		if (redisUtilEmployee.getMapAsSingleEntry(TABLE_EMPLOYEE, EMPLOYEE + empid) != null) {

			Employee emp = redisUtilEmployee.getMapAsSingleEntry(TABLE_EMPLOYEE, EMPLOYEE + empid);
			if (emp.getEmployeeId() == Integer.parseInt(empid)) {

				return true;
			}

		}
		return false;
	}
	
	
	@Override
	public Employee getEmployeeeByIdFromCache(String empId) {
		
		Employee employee = new Employee();
		
		if(redisUtilEmployee.getMapAsSingleEntry(TABLE_EMPLOYEE, EMPLOYEE+empId) != null) {
			
			employee =	redisUtilEmployee.getMapAsSingleEntry(TABLE_EMPLOYEE, EMPLOYEE+empId);
			System.out.println(" print from cache "+ employee.getEmployeeId());
			System.out.println(" print from cache "+ employee.getEmployeeName());
				
			}
			
		return employee;
		
	}
	
	
	@Override
	public boolean isFindAllEmpty() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void cacheAllEmployeeDetails(Employees employeeList) {
		// TODO Auto-generated method stub
		
	}
	
	
	/*
	 * @Override public boolean isFindAllEmpty() { // TODO Auto-generated method
	 * stub
	 * 
	 * 
	 * if(redisUtilEmployee.getMapAsAll(TABLE_EMPLOYEE) != null) {
	 * 
	 * 
	 * List<Employee> emp= redisUtilEmployee.getValue(TABLE_EMPLOYEE);
	 * List<Employee> emp = redisUtilEmployee.getMapAsSingleEntry(TABLE_EMPLOYEE);
	 * System.out.println(" print from cache "+ emp.getEmployeeId());
	 * System.out.println(" print from cache "+ emp.getEmployeeName());
	 * 
	 * return true ; } return false; }
	 */
	
}
