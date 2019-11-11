package com.fedex.smm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fedex.smm.cache.Constants;
import com.fedex.smm.configuration.RedisHashUtil;
import com.fedex.smm.dao.AddressRepository;
import com.fedex.smm.dao.EmployeeRepository;
import com.fedex.smm.dao.HolidayRepository;
import com.fedex.smm.dao.LocationRepository;
import com.fedex.smm.dao.ServiceDayRepository;
import com.fedex.smm.dto.LoadCacheResponse;
import com.fedex.smm.model.Address;
import com.fedex.smm.model.Employee;
import com.fedex.smm.model.Holiday;
import com.fedex.smm.model.Location;
import com.fedex.smm.model.ServiceDay;

@Service
public class CacheService {

	private static final Logger logger = LoggerFactory.getLogger(CacheService.class);

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private LocationRepository locationRepository;

	@Autowired
	private ServiceDayRepository serviceDayRepository;

	@Autowired
	private HolidayRepository holidayRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	private RedisHashUtil<Employee> redisUtilEmp;

	private RedisHashUtil<Address> redisUtilAddr;

	private RedisHashUtil<Location> redisUtilLoc;

	private RedisHashUtil<ServiceDay> redisUtilSerDay;

	private RedisHashUtil<Holiday> redisUtilHol;

	@Autowired
	public CacheService(RedisHashUtil<Employee> redisUtilEmp, RedisHashUtil<Address> redisUtilAddr,
			RedisHashUtil<Location> redisUtilLoc, RedisHashUtil<ServiceDay> redisUtilSerDay,
			RedisHashUtil<Holiday> redisUtilHol) {
		super();
		this.redisUtilEmp = redisUtilEmp;
		this.redisUtilAddr = redisUtilAddr;
		this.redisUtilLoc = redisUtilLoc;
		this.redisUtilSerDay = redisUtilSerDay;
		this.redisUtilHol = redisUtilHol;
	}

	public LoadCacheResponse loadCache() {
		LoadCacheResponse cacheResponse = new LoadCacheResponse();
		if (!redisUtilEmp.isFullyLoaded() || redisUtilEmp.getKeys(Constants.EMPLOYEE).isEmpty()) {
			List<Employee> employees = employeeRepository.findAll();
			Map<Long, Employee> map = new HashMap<>();
			employees.forEach(emp -> map.put(emp.getEmployeeId(), emp));
			redisUtilEmp.addAll(Constants.EMPLOYEE, map);
			redisUtilEmp.setAllLoaded(true);
			logger.info("Loading Employees from Database. Total Employees = " + employees.size());
			cacheResponse.setEmployees(redisUtilEmp.getValues(Constants.EMPLOYEE).subList(0, 9));
		} else {
			logger.info("Loading Employees from Redis Cache.");
			cacheResponse.setEmployees(redisUtilEmp.getValues(Constants.EMPLOYEE).subList(0, 9));
		}

		if (!redisUtilAddr.isFullyLoaded() || redisUtilAddr.getKeys(Constants.ADDRESS).isEmpty()) {
			List<Address> addresses = addressRepository.findAll();
			Map<Long, Address> map = new HashMap<>();
			addresses.forEach(addr -> map.put(addr.getAddrId(), addr));
			redisUtilAddr.addAll(Constants.ADDRESS, map);
			redisUtilAddr.setAllLoaded(true);
			logger.info("Loading Addresses from Database. Total Addresses = " + addresses.size());
			cacheResponse.setAddresses(redisUtilAddr.getValues(Constants.ADDRESS).subList(0, 9));
		} else {
			logger.info("Loading Addresses from Redis Cache.");
			cacheResponse.setAddresses(redisUtilAddr.getValues(Constants.ADDRESS).subList(0, 9));
		}
		if (!redisUtilLoc.isFullyLoaded() || redisUtilLoc.getKeys(Constants.LOCATION).isEmpty()) {
			List<Location> locations = locationRepository.findAll();
			Map<Long, Location> map = new HashMap<>();
			locations.forEach(location -> map.put(location.getLocationId(), location));
			redisUtilLoc.addAll(Constants.LOCATION, map);
			redisUtilLoc.setAllLoaded(true);
			logger.info("Loading Locations from Database. Total Locations = " + locations.size());
			cacheResponse.setLocations(redisUtilLoc.getValues(Constants.LOCATION).subList(0, 9));
		} else {
			logger.info("Loading Locations from Redis Cache.");
			cacheResponse.setLocations(redisUtilLoc.getValues(Constants.LOCATION).subList(0, 9));
		}

		if (!redisUtilSerDay.isFullyLoaded() || redisUtilSerDay.getKeys(Constants.SERVICEDAY).isEmpty()) {
			List<ServiceDay> serviceDays = serviceDayRepository.findAll();
			Map<Long, ServiceDay> map = new HashMap<>();
			serviceDays.forEach(serviceDay -> map.put(serviceDay.getId(), serviceDay));
			redisUtilSerDay.addAll(Constants.SERVICEDAY, map);
			redisUtilSerDay.setAllLoaded(true);
			logger.info("Loading Service Days from Database. Total Service Days = " + serviceDays.size());
			cacheResponse.setServiceDays(redisUtilSerDay.getValues(Constants.SERVICEDAY).subList(0, 9));
		} else {
			logger.info("Loading Service Days from Redis Cache.");
			cacheResponse.setServiceDays(redisUtilSerDay.getValues(Constants.SERVICEDAY).subList(0, 9));
		}

		if (!redisUtilHol.isFullyLoaded() || redisUtilHol.getKeys(Constants.HOLIDAY).isEmpty()) {
			List<Holiday> holidays = holidayRepository.findAll();
			Map<Long, Holiday> map = new HashMap<>();
			holidays.forEach(holiday -> map.put(holiday.getId(), holiday));
			redisUtilHol.addAll(Constants.HOLIDAY, map);
			redisUtilHol.setAllLoaded(true);
			logger.info("Loading Holidays from Database. Total Holidays = " + holidays.size());
			cacheResponse.setHolidays(redisUtilHol.getValues(Constants.HOLIDAY).subList(0, 9));
		} else {
			logger.info("Loading Holidays from Redis Cache.");
			cacheResponse.setHolidays(redisUtilHol.getValues(Constants.HOLIDAY).subList(0, 9));
		}
		cacheResponse.setMessage(
				"Loaded Multiple table Data into Cache. Only 10 Records from each table are in the response.");
		return cacheResponse;
	}
}
