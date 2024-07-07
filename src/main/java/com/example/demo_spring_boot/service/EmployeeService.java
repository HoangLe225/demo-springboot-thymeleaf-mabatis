package com.example.demo_spring_boot.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.demo_spring_boot.entity.Employee;

public interface EmployeeService {
	
	List<Employee> findAll();

	Employee findById(long theId);

	void deleteById(long theId);

	void save(Employee user);

	public Page<Employee> getPaginated(int page, int size);

}
