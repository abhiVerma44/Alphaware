package com.alphaware.demo.service;

import java.util.List;

import com.alphaware.demo.exceptions.CustomerException;
import com.alphaware.demo.models.Customer;

public interface CustomerService {

    public Customer registerCustomer(Customer customer);
	
	public Customer getCustomerDetailsByEmail(String email)throws CustomerException;
	
	public List<Customer> getAllCustomerDetails()throws CustomerException;
}
