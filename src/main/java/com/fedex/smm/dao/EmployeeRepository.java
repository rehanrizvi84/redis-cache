package com.fedex.smm.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fedex.smm.model.Employee;

/**
 * Created by JavaDeveloperZone on 03-08-2017.
 */
@Repository
@Transactional
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

	List<Employee> findAll();
}
