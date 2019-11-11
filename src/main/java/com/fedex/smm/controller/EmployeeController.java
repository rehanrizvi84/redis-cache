package com.fedex.smm.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fedex.smm.dto.EmployeeResponse;
import com.fedex.smm.model.Employee;
import com.fedex.smm.service.EmployeeService;

@RestController
public class EmployeeController {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	private EmployeeService employeeService;

	@PostMapping(value = "saveEmp")
	public Employee save(@RequestBody Employee employee) {
		employee = employeeService.save(employee);
		logger.info("Saved Employee Successfully");
		return employee;

	}

	@PutMapping(value = "updateEmp")
	public Employee update(@RequestBody Employee employee) {
		employee = employeeService.update(employee);
		logger.info("Updated Employee Successfully");
		return employee;

	}

	@GetMapping(value = "getEmp/{id}")
	public Employee getById(@PathVariable Long id) {
		Employee employee = employeeService.getById(id);
		logger.info("Get Employee by id successful");
		return employee;
	}

	@GetMapping(value = "getAllEmp")
	public EmployeeResponse getAll() {
		EmployeeResponse employees = employeeService.findAll();
		logger.info("Get All employees successful");
		return employees;
	}

	@DeleteMapping(value = "deleteEmp/{id}")
	public String delete(@PathVariable Long id) {
		employeeService.delete(id);
		return "Deleted the Employee details with id " + id + " successfully";
	}

	@GetMapping(value = "clearAll")
	public String clearAll() {
		employeeService.clearAll();
		return "Cleared all cached content from all Redis DB";
	}

	@GetMapping(value = "clearByDb/{index}")
	public String clearByDb(@PathVariable int index) {
		employeeService.clearByDbIndex(index);
		return "Cleared cached content from Redis DB with index " + index;
	}

	@GetMapping(value = "clearByKey/{key}")
	public String clearByKey(@PathVariable String key) {
		employeeService.clearByKey(key);
		return "Cleared cached content from Redis DB with key " + key;
	}

	@GetMapping(value = "clearByKeyAndHashKey/{key}/{hashKey}")
	public String clearByKeyAndHashKey(@PathVariable String key, @PathVariable Long hashKey) {
		employeeService.clearByKeyAndHashKey(key, hashKey);
		return "Cleared cached content from Redis DB with key " + key + " and hash key " + hashKey;
	}

}
