package com.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.entity.CourseEntity;

@Repository
public interface CourseRepository extends CrudRepository<CourseEntity, Integer> {

	List<CourseEntity> findAll();

	CourseEntity findByName(String name);
}
