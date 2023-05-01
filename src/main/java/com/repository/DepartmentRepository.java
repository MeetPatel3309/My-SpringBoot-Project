package com.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.entity.DepartmentEntity;

@Repository
public interface DepartmentRepository extends CrudRepository<DepartmentEntity, Integer>{
	
	List<DepartmentEntity> findAll();

	Optional<DepartmentEntity> findByName(String name);

	DepartmentEntity findByDeptId(Integer deptId);

}
