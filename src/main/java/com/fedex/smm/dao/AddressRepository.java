package com.fedex.smm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fedex.smm.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

	public List<Address> findAll();

}
