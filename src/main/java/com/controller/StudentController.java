package com.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bean.ResponseBean;
import com.entity.CourseEntity;
import com.entity.StudentEntity;
import com.repository.CourseRepository;
import com.repository.StudentRepository;

@RestController
public class StudentController {
	
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	CourseRepository courseRepository;
	
	
	@PostMapping("/student")
	public StudentEntity addStudentWithCourse(@RequestBody StudentEntity studentEntity)
	{	
				
		studentRepository.save(studentEntity);
		return studentEntity;
	}
	
	
//	@PutMapping("/{sId}/course/{cId}")
//	@PutMapping("/{sId}/course/{cName}")
	@PutMapping("/{sName}/course/{cName}")
//	public StudentEntity assignCourseToStudent(@PathVariable("sId") Integer sId , @PathVariable("cId") Integer cId)
//	public StudentEntity assignCourseToStudent(@PathVariable("sId") Integer sId , @PathVariable("cName") String cName)
	public ResponseEntity<ResponseBean<StudentEntity>>  assignCourseToStudent(@PathVariable("sName") String sName , @PathVariable("cName") String cName)
	{
//		StudentEntity studentEntity = studentRepository.findById(sId).get();
		StudentEntity studentEntity = studentRepository.findByName(sName);
//		CourseEntity courseEntity = courseRepository.findById(cId).get();
		CourseEntity courseEntity = courseRepository.findByName(cName);
		
		if(studentEntity == null)
		{
			ResponseBean<StudentEntity> resp = new ResponseBean<>();
			resp.setMsg("Student Not Found..!! Please add the student");
			return new ResponseEntity<ResponseBean<StudentEntity>>(resp, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		else
		{
		Set<CourseEntity> courses = studentEntity.getCourses();
		courses.add(courseEntity);
		
		studentEntity.setCourses(courses);
		
		StudentEntity student = studentRepository.save(studentEntity);
		
		ResponseBean<StudentEntity> resp = new ResponseBean<>();
		resp.setData(student);
		resp.setMsg("Course Assigned Successfull..!");
		
		return ResponseEntity.ok(resp);
		
		}
	}
	
	
	@GetMapping("/student")
	public List<StudentEntity> getAllStudents()
	{
		return studentRepository.findAll();
	
	}
	
	@GetMapping("/student/{sId}")
	public StudentEntity getStudentById(@PathVariable("sId") Integer sId)
	{
		Optional<StudentEntity> studentoptional = studentRepository.findById(sId);
		
		if(studentoptional.isEmpty())
		{
			return null;
 		}else
		{
			return studentoptional.get();
		}
	}
	
	@GetMapping("/studentbyName/{name}")
	public List<StudentEntity> getStudentByName(@PathVariable("name") String name)
	{
		
		List<StudentEntity> students = studentRepository.findByNameContaining(name);
		
		return students;
	}
	
	@DeleteMapping("/student/{sId}")
	public StudentEntity deleteById(@PathVariable("sId") Integer sId)
	{		
		Optional<StudentEntity> studentOptional = studentRepository.findById(sId);
				
		if(studentOptional.isEmpty())
		{
			return null;
		}else
		{
			studentRepository.deleteById(sId);
			return studentOptional.get();
		}
	}
	

	
	
}
