package com.fedex.smm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fedex.smm.model.Holiday;

@Repository
public interface HolidayRepository extends JpaRepository<Holiday, Long> {

	public List<Holiday> findAll();

}
