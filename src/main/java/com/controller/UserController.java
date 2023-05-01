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

import com.entity.UserEntity;
import com.repository.AddressRepository;
import com.repository.UserRepository;

@RestController
public class UserController {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AddressRepository addressRepository;
	
	
	
	@PostMapping("/user")
	public UserEntity addUser(@RequestBody UserEntity userEntity)
	{
		addressRepository.save(userEntity.getAddress());
		
		userRepository.save(userEntity);
		return userEntity;
	}
	
	
	@GetMapping("/user/{userId}")
	public UserEntity getUserByuserId(@PathVariable("userId") Integer userId)
	{
			Optional<UserEntity> userOptional = userRepository.findByUserId(userId);
			if(userOptional.isEmpty())
			{
				return null;
			}else
			{
				return userOptional.get();
			}
	}
	
	
	@GetMapping("/user")
	public List<UserEntity> getAllUser()
	{
		return userRepository.findAll();
	}
	
	
	@DeleteMapping("/user/{userId}")
	public UserEntity deleteByUserId(@PathVariable("userId") Integer userId)
	{
//		Optional<UserEntity> userOptional = userRepository.findByUserId(userId);     --not applicable bcz it finds PK byDefault 
		Optional<UserEntity> userOptional = userRepository.findById(userId);
		
		if(userOptional.isEmpty())
		{
			return null;
		}else
		{
//			userRepository.deleteByUserId(userId);     --This method can not be override.
			userRepository.deleteById(userId);
			return userOptional.get();
		}
	}
	
	
	@PutMapping("/user")
	public UserEntity updateUser(@RequestBody UserEntity userEntity)
	{
//		addressRepository.save(userEntity.getAddress());
		
		userRepository.save(userEntity);
		return userEntity;
	}
	
	
}
