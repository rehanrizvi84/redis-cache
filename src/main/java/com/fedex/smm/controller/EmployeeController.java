package com.fedex.smm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

	// to add new employee
	@PostMapping(value = "save") // or user @GetMapping
	public void save(@RequestBody Employee employee) {
		System.out.println("employee ------" + employee.getEmployeeName());
		employeeService.save(employee);

	}

	@GetMapping(value = "employee") // or use @GetMapping
	public Employee getEmployeeById(@RequestParam String id) {
		System.out.println(" ID recieved " + id);
		Long empid = Long.valueOf(id);
		return employeeService.findById(empid);
	}

	// list of all employee
	@GetMapping(value = "listEmployee") // or use @GetMapping
	public java.util.List<Employee> listEmployee() {
		return employeeService.findAll();
	}

	// delete specific employee using employee id
	@GetMapping(value = "delete") // or use @DeleteMapping
	public void delete(@RequestParam("id") long id) {
		employeeService.delete(id);
	}

	// clear all cache using cache manager

	@GetMapping(value = "clearCache")
	public void clearCache() {
		for (String name : cacheManager.getCacheNames()) {
			cacheManager.getCache(name).clear();
		}
	}

}
