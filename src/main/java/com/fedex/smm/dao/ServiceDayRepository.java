package com.fedex.smm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fedex.smm.model.ServiceDay;

@Repository
public interface ServiceDayRepository extends JpaRepository<ServiceDay, Long> {

	public List<ServiceDay> findAll();
}
