package com.fedex.smm.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.*;

import com.fedex.smm.model.Employee;
import com.fedex.smm.service.EmployeeService;

/**
 */
@RestController // for rest response
public class EmployeeController {

	@Autowired
	private CacheManager cacheManager; // autowire cache manager

	@Autowired
	private EmployeeService employeeService;

	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	// to add new employee
	@RequestMapping(value = "save", method = RequestMethod.POST) // or user @GetMapping
	public void save(@RequestBody Employee employee) {

		System.out.println("employee ------" + employee.getEmployeeName());

		employeeService.save(employee);

	}

	@RequestMapping(value = "employee", method = RequestMethod.GET) // or use @GetMapping
	public Employee getEmployeeById(@RequestParam String id) {
		System.out.println(" ID recieved " + id);
		Long empid = Long.valueOf(id);
		return employeeService.findById(empid);
	}

	// list of all employee
	@RequestMapping(value = "listEmployee", method = RequestMethod.GET) // or use @GetMapping
	public java.util.List<Employee> listEmployee() {
		return employeeService.findAll();
	}

	// delete specific employee using employee id
	@RequestMapping(value = "delete", method = RequestMethod.DELETE) // or use @DeleteMapping
	public void delete(@RequestParam("id") long id) {
		employeeService.delete(id);
	}

	// clear all cache using cache manager

	@RequestMapping(value = "clearCache")
	public void clearCache() {
		for (String name : cacheManager.getCacheNames()) {
			cacheManager.getCache(name).clear();
		}
	}

}
