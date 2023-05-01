package com.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.entity.AddressEntity;

@Repository
public interface AddressRepository extends CrudRepository<AddressEntity, Integer>{

	Optional<AddressEntity> findByAddressId(Integer addressId);

	List<AddressEntity> findBycity(String city);

	List<AddressEntity> findAll();
	
}
