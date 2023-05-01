package com.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bean.ResponseBean;
import com.entity.CourseEntity;
import com.repository.CourseRepository;

@RestController
public class CourseController {

	@Autowired
	CourseRepository courseRepository;
	
	
	
	@PostMapping("/course")
	public ResponseEntity<ResponseBean<CourseEntity>> addCourse(@RequestBody CourseEntity courseEntity)
	{
		CourseEntity existingCourse = courseRepository.findByName(courseEntity.getName());
		
		ResponseBean<CourseEntity> resp = new ResponseBean<>();
		
		if(existingCourse != null)
		{
			resp.setData(courseEntity);
			resp.setMsg("Course Already Exist..!!");
			return new ResponseEntity<ResponseBean<CourseEntity>>(resp, HttpStatus.UNPROCESSABLE_ENTITY);
			
		}else
		{
			resp.setData(courseEntity);
			resp.setMsg("Course Added Successfully..!!");
			courseRepository.save(courseEntity);
			return ResponseEntity.ok(resp);
		}
		
	}
	
	
	@GetMapping("/course")
	public List<CourseEntity> getAllCourses()
	{
		return courseRepository.findAll();
	}
	
	
	@GetMapping("/course/{cId}")
	public CourseEntity getCourseById(@PathVariable("cId") Integer cId)
	{
		Optional<CourseEntity> courseOptional =  courseRepository.findById(cId);
		
		if(courseOptional.isEmpty())
		{
			return null;
		}else
		{
			return courseOptional.get();
			
		}
	}
	
	
	@GetMapping("/coursebyname/{name}")
	public CourseEntity getCourseByName(@PathVariable("name") String name)
	{
		return courseRepository.findByName(name);
	}
	
	
	@DeleteMapping("/course/{cId}")
	public CourseEntity deleteById(@PathVariable("cId") Integer cId)
	{
		Optional<CourseEntity> courseOptional =  courseRepository.findById(cId);
		
		if(courseOptional.isEmpty())
		{
			return null;
		}else
		{
			courseRepository.deleteById(cId);
			return courseOptional.get();
			
		}
	}
}
