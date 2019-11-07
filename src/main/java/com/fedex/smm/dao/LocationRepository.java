package com.fedex.smm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fedex.smm.model.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

	public List<Location> findAll();
}
