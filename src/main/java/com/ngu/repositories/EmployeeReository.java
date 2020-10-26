package com.ngu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ngu.Model.Employee;

@Repository("employeeReository")
public interface EmployeeReository extends JpaRepository<Employee, Integer> {

	Employee findByUsername(String username);
}
