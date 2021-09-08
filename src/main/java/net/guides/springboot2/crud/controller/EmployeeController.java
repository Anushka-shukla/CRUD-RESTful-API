package net.guides.springboot2.crud.controller;

import java.net.URI;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.ws.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import net.guides.springboot2.crud.dto.EmpDto;
import net.guides.springboot2.crud.exception.ResourceNotFoundException;
import net.guides.springboot2.crud.model.Employee;
import net.guides.springboot2.crud.repository.EmployeeRepository;
import net.guides.springboot2.crud.service.EmpService;


@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
	
	
	
	
	@Autowired
	private EmpService empService;
	
	@GetMapping("/employees")
	public List<Employee> getAllEmployees(){
	return empService.findAll();
	}
	
	@GetMapping("/employees/{employeeId}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long employeeId)
	    throws ResourceNotFoundException{
		Employee emp = empService.findById(employeeId);
		return ResponseEntity.ok().body(emp);
	}
	
	@PostMapping("/create")
	public @ResponseBody ResponseEntity<Employee> createEmployee(@RequestBody EmpDto empDto)  {
		
		Employee result = empService.save(empDto);
		
		return new ResponseEntity<Employee>(result, HttpStatus.CREATED);
		//return ResponseEntity.savedEmployee(location)body(savedEmployee);
	}
	
	
	@PutMapping("/update")
	public ResponseEntity<Employee> updateEmployee(@RequestBody EmpDto empDto) throws ResourceNotFoundException {
		
		Employee emp = empService.update(empDto);
		
		
		return ResponseEntity.ok(emp);
	}
	
	@DeleteMapping("/delete/{employeeId}")
	public String deleteEmployee(@PathVariable Long employeeId) {
	    
		
		String result = empService.delete(employeeId);
		return result;
	}
	
}
