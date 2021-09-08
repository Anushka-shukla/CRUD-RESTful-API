package net.guides.springboot2.crud.service;

import java.util.List;

import net.guides.springboot2.crud.dto.EmpDto;
import net.guides.springboot2.crud.exception.ResourceNotFoundException;
import net.guides.springboot2.crud.model.Employee;

public interface EmpService {
	
	public List<Employee> findAll();
	
	public Employee findById(Long id);
	
	public Employee save(EmpDto empDto);
	
	public Employee update(EmpDto empDto) ;
	
	public String delete(Long id);

	

}
