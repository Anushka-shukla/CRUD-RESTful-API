package net.guides.springboot2.crud.service.impl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j2;
import net.guides.springboot2.crud.dto.EmpDto;
import net.guides.springboot2.crud.exception.ResourceNotFoundException;
import net.guides.springboot2.crud.model.Employee;
import net.guides.springboot2.crud.repository.EmployeeRepository;
import net.guides.springboot2.crud.service.EmpService;

@Log4j2
@Service
@Transactional
public class EmpServiceImpl implements EmpService{
	
	@Autowired
	private MessageSource messageSource;
	private Locale locale;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	// find all
	// getAllEmployees
	@Override
	public List<Employee> findAll(){
		List<Employee> empList = employeeRepository.findAll();
	
		
		return empList;
		
	}
	
	
	// find by id 
	@Override
	public Employee findById(Long id) {
		
		Employee emp = employeeRepository.findById(id).get();

		return emp;
		
	}
	
	// create 
	@Override
	public Employee save(EmpDto empDto) {
		
		Employee emp = new Employee();
		emp.setFirstName(empDto.getFirstName());
		emp.setLastName(empDto.getLastName());
		emp.setEmailId(empDto.getEmailId());
		
		emp = employeeRepository.save(emp);
	
		
		return emp;
	}
	
	
	// update 
	@Override 
	public Employee update(EmpDto empDto) {
		
		Employee emp = employeeRepository.findById(empDto.getId()).get();
		emp.setFirstName(empDto.getFirstName());
		emp.setLastName(empDto.getLastName());
		emp.setEmailId(empDto.getEmailId());
		
		emp = employeeRepository.save(emp);
	
		
		return emp;
		
		

	}
	
	
	
	// delete
	@Override
	public String delete(Long id) {
		String msg="";
		Optional<Employee> emp = employeeRepository.findById(id);
		System.out.print(emp);
		
		if(emp.isPresent()) {
			
			 employeeRepository.deleteById(id);
			 msg = "DELETED SUCCESFULLY";
			
		}else {
			msg = "THE EMP ID DOES NOT EXIST";
		}
		
		return msg;
		
	}
		
		




	

}
