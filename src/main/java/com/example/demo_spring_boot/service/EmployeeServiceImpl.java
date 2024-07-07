package com.example.demo_spring_boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo_spring_boot.entity.Employee;
import com.example.demo_spring_boot.mapper.EmployeeMapper;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeMapper employeeMapper;

	@Override
	public List<Employee> findAll() {
		return employeeMapper.findAll();
	}

	@Override
	public Employee findById(long theId) {
		return employeeMapper.findById(theId);
	}

	@Override
	public void deleteById(long theId) {
		employeeMapper.deleteById(theId);
	}

	@Override
	public void save(Employee user) {
		if (user.getId() != null) {
			employeeMapper.update(user);
		} else {
			employeeMapper.insert(user);
		}
	}

	@Override
	public Page<Employee> getPaginated(int page, int size) {
		int offset = page * size;
		List<Employee> items = employeeMapper.selectAllPaginated(size, offset);
        long totalItems = employeeMapper.count();
        Pageable pageable = PageRequest.of(page, size);
        return new PageImpl<>(items, pageable, totalItems);
	}
}
