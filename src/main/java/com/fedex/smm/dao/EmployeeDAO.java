package com.fedex.smm.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fedex.smm.model.Employee;

import java.util.List;
import java.util.Optional;


/**
 * Created by JavaDeveloperZone on 03-08-2017.
 */
@Repository
@Transactional
public interface EmployeeDAO extends CrudRepository<Employee,Long> {
    List<Employee> findAll();                           // fetch all Employee
}
