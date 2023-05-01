package com.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.entity.CustomerEntity;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerEntity, Integer>{
	
	List<CustomerEntity> findAll() ;

	List<CustomerEntity> findByName(String name);
	

}
