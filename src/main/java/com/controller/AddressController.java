package com.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.entity.AddressEntity;
import com.repository.AddressRepository;
import com.repository.UserRepository;

@RestController
public class AddressController {

	@Autowired
	AddressRepository addressRepository;
	
	@Autowired
	UserRepository userRepository;
	
	
	@PostMapping("/address")
	public AddressEntity addAddress(@RequestBody AddressEntity addressEntity)
	{
//		userRepository.save(addressEntity.getUser());      --not recommanded bcz 1-1 Relationship, can not insert user via address bcz of @jsonIgnore
		
		addressRepository.save(addressEntity);
		return addressEntity;
	}
	
	
	@GetMapping("/address")
	public List<AddressEntity> getAllAddress()
	{
//		List<AddressEntity> address = addressRepository.findAll();
//		return address;
//		OR
		return addressRepository.findAll();
	}
	
	
	@GetMapping("/address/{addressId}")
	public AddressEntity getAddressByAddressId(@PathVariable("addressId") Integer addressId)
	{
		Optional<AddressEntity> addressOptional = addressRepository.findByAddressId(addressId);
		
		if(addressOptional.isEmpty())
		{
			return null;
		}else
		{
			return addressOptional.get();
		}
	}
	
	@GetMapping("/addressByCity/{city}")
	public List<AddressEntity> getAddressByCity(@PathVariable("city") String city)
	{
		return addressRepository.findBycity(city);
	}
	
	
	@DeleteMapping("/address/{addressId}")
	public AddressEntity deleteByAddressId(@PathVariable("addressId") Integer addressId)
	{
		Optional<AddressEntity> addressOptional= addressRepository.findById(addressId);
		
		
		if(addressOptional.isEmpty())
		{
			return null;
		}else
		{
			addressRepository.deleteById(addressId);
			return addressOptional.get();
		}
	}
	
	
}



