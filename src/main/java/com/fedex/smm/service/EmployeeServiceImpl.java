package com.fedex.smm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.fedex.smm.cache.EmployeeCacheManager;
import com.fedex.smm.dao.EmployeeDAO;
import com.fedex.smm.model.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by JavaDeveloperZone on 04-04-2018.
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDAO employeeDAO;

	private EmployeeCacheManager employeeCacheManager;

	@Autowired
	public EmployeeServiceImpl(EmployeeCacheManager employeeCacheManager) {
		this.employeeCacheManager = employeeCacheManager;
	}

	@Override
	public void cacheEmployeeDetails(boolean checkFlag) throws Exception {
		if (employeeCacheManager.checkEmpty("1")) {// If cache is empty the put the data
			List<Employee> employees = findAll();// studentDAO.getStudentList();
			employees.forEach(stud -> employeeCacheManager.cacheEmployeeDetails(stud));
		}
	}

	@Override
	// @Cacheable(value="employees") // it will cache result and key name will be
	// "employees"
	public List<Employee> findAll() {

		List<Employee> employeeList = new ArrayList<Employee>();

		employeeList = employeeDAO.findAll();

		return employeeList;

	}

	@Override
	// @CacheEvict(value = "employees",allEntries = true) // It will clear cache
	// when delete any employee to database
	public void delete(long employeeId) {
		Employee employee = null;// employeeDAO.findOne(employeeId);
		employeeDAO.delete(employee);
	}

	@Override
	// @CacheEvict(value = "employees", allEntries=true) // It will clear cache when
	// new employee save to database
	public Employee save(Employee employee) {
		return employeeDAO.save(employee);
	}

	@Override
	public Employee findById(Long id) {

		String empId = id.toString();
		Employee employee = new Employee();
		if (employeeCacheManager.checkEmpty(empId)) {

			// fetch from cache and return
			employee = employeeCacheManager.getEmployeeeByIdFromCache(empId);

		} else {

			Optional<Employee> employeeOpt = employeeDAO.findById(id);
			employee = employeeOpt.get();
			System.out.println("Getting from findBYID-- " + employee.getEmployeeName());
			// populate the cache
			employeeCacheManager.cacheEmployeeDetails(employee);

		}

		// TODO Auto-generated method stub
		return employee;
	}
}
