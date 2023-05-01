package com.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.entity.StudentEntity;

@Repository
public interface StudentRepository extends CrudRepository<StudentEntity, Integer> {

	List<StudentEntity> findAll();
	
	List<StudentEntity> findByNameContaining(String name);

	StudentEntity findByName(String cName);
}
