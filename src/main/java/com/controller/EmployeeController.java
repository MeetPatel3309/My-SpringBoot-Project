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

import com.entity.DepartmentEntity;
import com.entity.EmployeeEntity;
import com.repository.DepartmentRepository;
import com.repository.EmployeeRepository;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	DepartmentRepository departmentRepository;
	
	
	@PostMapping("/employee/{deptName}")
	public EmployeeEntity addEmployee(@PathVariable("deptName") String deptName, @RequestBody EmployeeEntity employeeEntity)
	{
		//Description is in AddDepartment() method
		DepartmentEntity departmentEntity = departmentRepository.findByName(deptName).orElse(null);

		if(departmentEntity == null)
		{
			return null;
		}
		else
		{	
		employeeEntity.setDepartment(departmentEntity);
		employeeRepository.save(employeeEntity);
		return employeeEntity;
		}
	}
	
	@GetMapping("/employee")
	public List<EmployeeEntity> getAllEmployee()
	{
		return employeeRepository.findAll();
	}
	
	
	@GetMapping("/employee/{empId}")
	public EmployeeEntity getEmployeeById(@PathVariable("empId")  Integer empId)
	{
		Optional<EmployeeEntity> empOptional = employeeRepository.findById(empId);
		
		if(empOptional.isEmpty())
		{
			return null;
		}else
		{
			return empOptional.get();
		}
	}
	
	
	@GetMapping("/employeeByName/{name}")
	public List<EmployeeEntity>  getEmployeeByName(@PathVariable("name") String name)
	{
		List<EmployeeEntity> emps = employeeRepository.findByName(name);
		return emps;
	}
	
	
	@DeleteMapping("/employee/{empId}")
	public EmployeeEntity deleteEmployeeById(@PathVariable("empId") Integer empId)
	{
		Optional<EmployeeEntity> empOptional = employeeRepository.findById(empId);
				
		if(empOptional.isEmpty())
		{
			return null;
		}else
		{
			employeeRepository.deleteById(empId);
			return empOptional.get();
		}
	}
	
	
	@PutMapping("/employee/{deptId}")
	public EmployeeEntity updateEmployee(@PathVariable("deptId") Integer deptId, @RequestBody EmployeeEntity employeeEntity)
	{
		DepartmentEntity departmentEntity = departmentRepository.findByDeptId(deptId);
		
		employeeEntity.setDepartment(departmentEntity);
		
		return employeeRepository.save(employeeEntity);
	}
}
