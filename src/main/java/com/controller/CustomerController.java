package com.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.entity.CustomerEntity;
import com.repository.CustomerRepository;

@RestController
public class CustomerController {
	
	@Autowired
	CustomerRepository customerRepository;

	
	@PostMapping("/customer")
	public CustomerEntity addCustomer(@RequestBody CustomerEntity customerEntity)
	{
		customerRepository.save(customerEntity);
		return customerEntity;
	}
	
	@GetMapping("/customer")
	public List<CustomerEntity> getAllCustomers()
	{
		return customerRepository.findAll();
	}
	
	@GetMapping("/customer/{cId}")
	public CustomerEntity getCustomerById(@PathVariable("cId") Integer cId)
	{
		Optional<CustomerEntity> customerOptional = customerRepository.findById(cId);
		
		if(customerOptional.isEmpty())
		{
			return null;
		}else
		{
			return customerOptional.get();
		}
		
	}
	
	@GetMapping("/customerByName/{name}")
	public List<CustomerEntity> getCustomerByname(@PathVariable("name") String name)
	{
		return customerRepository.findByName(name);
	}
	
	
	@DeleteMapping("/customer/{cId}")
	public CustomerEntity deleteById(@PathVariable("cId") Integer cId)
	{
		CustomerEntity customerEntity = customerRepository.findById(cId).get();
		customerRepository.deleteById(cId);
		return customerEntity;
	}
	
	@PutMapping("/customer")
	public CustomerEntity updateCustomer(@RequestBody CustomerEntity customerEntity)
	{
		customerRepository.save(customerEntity);
		return customerEntity;
	}
}
