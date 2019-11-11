package com.fedex.smm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.stereotype.Service;

import com.fedex.smm.cache.Constants;
import com.fedex.smm.configuration.RedisHashUtil;
import com.fedex.smm.dao.EmployeeRepository;
import com.fedex.smm.dto.EmployeeResponse;
import com.fedex.smm.model.Employee;

@Service
public class EmployeeService {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private RedisConnection redisConnection;

	private RedisHashUtil<Employee> redisHashUtil;

	@Autowired
	public EmployeeService(RedisHashUtil<Employee> redisHashUtil) {
		this.redisHashUtil = redisHashUtil;
	}

	public Employee save(Employee employee) {
		employee = employeeRepository.save(employee);
		redisHashUtil.add(Constants.EMPLOYEE, Long.valueOf(employee.getEmployeeId()), employee);
		return employee;
	}

	public Employee update(Employee employee) {
		employee = employeeRepository.save(employee);
		redisHashUtil.add(Constants.EMPLOYEE, Long.valueOf(employee.getEmployeeId()), employee);
		return employee;
	}

	public Employee getById(Long id) {
		if (!redisHashUtil.isExists(Constants.EMPLOYEE, id)) {
			redisHashUtil.setAllLoaded(false);
			Optional<Employee> empDb = employeeRepository.findById(id);
			if (empDb.isPresent()) {
				Employee employee = empDb.get();
				redisHashUtil.add(Constants.EMPLOYEE, id, employee);
			}
		}
		return redisHashUtil.get(Constants.EMPLOYEE, id);
	}

	public void delete(Long id) {
		employeeRepository.deleteById(id);
		redisHashUtil.remove(Constants.EMPLOYEE, id);
	}

	public EmployeeResponse findAll() {
		EmployeeResponse employees = new EmployeeResponse();
		if (!redisHashUtil.isFullyLoaded() || redisHashUtil.getKeys(Constants.EMPLOYEE).isEmpty()) {
			List<Employee> allEmps = employeeRepository.findAll();
			Map<Long, Employee> empMap = new HashMap<>();
			allEmps.forEach(emp -> empMap.put(emp.getEmployeeId(), emp));
			redisHashUtil.addAll(Constants.EMPLOYEE, empMap);
			redisHashUtil.setAllLoaded(true);
			employees.setEmployees(allEmps.subList(0, 9));
			employees.setMessage("Loaded All Employees from Database. Only 10 records are in response.");
			logger.info("Loading Employees from Database. Total Employees = " + allEmps.size());

		} else {
			logger.info("Loading Employees from Redis Cache.");
			employees.setEmployees(redisHashUtil.getValues(Constants.EMPLOYEE).subList(0, 9));
			employees.setMessage("Loaded All employees from Cache. Only 10 records are in response.");
		}
		return employees;
	}

	public void clearAll() {
		redisConnection.flushAll();
	}

	public void clearByDbIndex(int index) {
		redisConnection.select(index);
		redisConnection.flushDb();
	}

	public void clearByKey(String key) {
		redisConnection.del(key.getBytes());
	}

	public void clearByKeyAndHashKey(String key, Long hashKey) {
		redisHashUtil.setAllLoaded(false);
		redisHashUtil.remove(key, hashKey);
	}

}
