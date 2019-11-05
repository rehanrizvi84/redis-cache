package com.fedex.smm.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fedex.smm.cache.Constants;
import com.fedex.smm.cache.EmployeeCacheManager;
import com.fedex.smm.configuration.RedisUtil;
import com.fedex.smm.dao.EmployeeDAO;
import com.fedex.smm.model.Employee;

/**
 * Created by JavaDeveloperZone on 04-04-2018.
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDAO employeeDAO;

	RedisUtil<Employee> redisUtil;

	@Autowired
	public EmployeeServiceImpl(RedisUtil<Employee> redisUtil) {
		this.redisUtil = redisUtil;
	}

	@Override
	public void cacheEmployeeDetails(boolean checkFlag) throws Exception {
	}

	@Override
	// @Cacheable(value="employees") // it will cache result and key name will be
	// "employees"
	public List<Employee> findAll() {
		List<Employee> employeeList = new ArrayList<Employee>();
		Map<Object, Employee> mapAsAll = redisUtil.getMapAsAll(Constants.TABLE_EMPLOYEE);
		if (mapAsAll.isEmpty()) {
			employeeList = employeeDAO.findAll();
			employeeList.forEach(emp -> redisUtil.putMap(Constants.TABLE_EMPLOYEE, emp.getEmployeeId(), emp));
			redisUtil.setExpire(Constants.TABLE_EMPLOYEE, 30, TimeUnit.SECONDS);
		}
		for (Entry<Object, Employee> entry : mapAsAll.entrySet()) {
			employeeList.add(entry.getValue());
		}
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
		employee = employeeDAO.save(employee);
		redisUtil.putMap(Constants.TABLE_EMPLOYEE, employee.getEmployeeId(), employee);
		redisUtil.setExpire(Constants.TABLE_EMPLOYEE, 30, TimeUnit.SECONDS);
		return employee;
	}

	@Override
	public Employee findById(Long id) {
		String empId = id.toString();
		Employee employee = null;
		if (redisUtil.getMapAsSingleEntry(Constants.TABLE_EMPLOYEE, empId) == null) {
			Optional<Employee> employeeOpt = employeeDAO.findById(id);
			employee = employeeOpt.get();
			redisUtil.putMap(Constants.TABLE_EMPLOYEE, employee.getEmployeeId(), employee);
			redisUtil.setExpire(Constants.TABLE_EMPLOYEE, 30, TimeUnit.SECONDS);
		}
		employee = redisUtil.getMapAsSingleEntry(Constants.TABLE_EMPLOYEE, empId);
		return employee;
	}
}
