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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bean.ResponseBean;
import com.entity.DepartmentEntity;
import com.repository.DepartmentRepository;

@RestController
public class DepartmentController {

	@Autowired
	DepartmentRepository departmentRepository;
	
//	@RequestMapping(method = RequestMethod.POST, value = "/department")
	@PostMapping("/department")
	public ResponseEntity<ResponseBean<DepartmentEntity>> addDepartment(@RequestBody DepartmentEntity departmentEntity)
	{
//		Optional<DepartmentEntity> departmentOptional = departmentRepository.findByName(departmentEntity.getName());
//		if(departmentOptional.isEmpty())
//		{
//			return null;
//		}else
//		{
//			departmentOptional.get();
//		}
//		OR
		DepartmentEntity department = departmentRepository.findByName(departmentEntity.getName()).orElse(null);
		
		ResponseBean<DepartmentEntity> resp = new ResponseBean<>();
		 
//		if(departmentOptional.get() == null)
		if(department == null)
		{
		 
		 resp.setData(departmentEntity);
		 resp.setMsg("Department Added Successfully..!!");
		 
		 departmentRepository.save(departmentEntity);
		 
		 return ResponseEntity.ok(resp);
		}
		else
		{
			resp.setData(departmentEntity);
			resp.setMsg("Department Already Exist...!!");
			
			return new ResponseEntity<ResponseBean<DepartmentEntity>>(resp, HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	
	@GetMapping("/department")
	public List<DepartmentEntity> getAllDepartment() 
	{
		return departmentRepository.findAll();
	}
	
	
	@GetMapping("/department/{deptId}")
	public DepartmentEntity getDepartmentById(@PathVariable("deptId") Integer deptId)
	{
		Optional<DepartmentEntity> departmentEntity = departmentRepository.findById(deptId);
		
		if(departmentEntity.isEmpty())
		{
			return null;
		}
		else
		{
		return departmentEntity.get();
		}
	}
	
	
	@GetMapping("/departmentByName/{name}")
	public DepartmentEntity getDepartmentByName(@PathVariable("name") String name)
	{
		Optional<DepartmentEntity> departmentOptional = departmentRepository.findByName(name);
		
		if(departmentOptional.isEmpty())
		{
			return null;
		}
		else
		{
			return departmentOptional.get();
		}	
	}
	
	
	@DeleteMapping("/department/{deptId}")
	public DepartmentEntity deleteDepartmentById(@PathVariable("deptId") Integer deptId)
	{
		Optional<DepartmentEntity> departmentEntity = departmentRepository.findById(deptId);
		
		if(departmentEntity.isEmpty())
		{
			return null;
		}
		else
		{
			departmentRepository.deleteById(deptId);
			return departmentEntity.get();
		}
	}
	
	
	@PutMapping("/department")
	public DepartmentEntity updateDepartment(@RequestBody DepartmentEntity departmentEntity)
	{
		departmentRepository.save(departmentEntity);
		return departmentEntity;
	}
	
}
