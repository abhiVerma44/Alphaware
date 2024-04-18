package com.alphaware.demo.repository;


import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.alphaware.demo.models.Customer;



public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	
	public Optional<Customer> findByEmail(String email);
	@Query("SELECT c FROM Customer c WHERE c.custId = :custId")
    Optional<Customer> findByCustId(@Param("custId") Long customerId);
}