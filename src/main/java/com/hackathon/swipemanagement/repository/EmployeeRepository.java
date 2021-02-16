package com.hackathon.swipemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hackathon.swipemanagement.entity.Employees;

public interface EmployeeRepository extends JpaRepository<Employees, Long>{
	
	public Employees findByEmployeeId(Long employeeId);

}
