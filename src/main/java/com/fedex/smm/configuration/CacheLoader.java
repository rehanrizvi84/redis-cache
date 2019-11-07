package com.fedex.smm.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import com.fedex.smm.dto.EmployeeResponse;
import com.fedex.smm.service.EmployeeService;

@Configuration
public class CacheLoader {

	private static final Logger logger = LoggerFactory.getLogger(CacheLoader.class);

	@Autowired
	EmployeeService employeeService;

	@Value("${redis.cache.load-on-startup:false}")
	private boolean loadOnStartUp;

	@EventListener(ApplicationReadyEvent.class)
	public void loadCache() {
		if (loadOnStartUp) {
			EmployeeResponse employeesResponse = employeeService.findAll();
			logger.info("Loading All Employess into Redis Cache at the startup. Number of Employees in DB : "
					+ employeesResponse.getEmployees().size());
		}
	}
}
