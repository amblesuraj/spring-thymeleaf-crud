package com.ngu.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ngu.Model.Employee;
import com.ngu.repositories.EmployeeReository;
@Transactional
@Service("employeeService")
public class EmployeeService {

	
	@Autowired
	private EmployeeReository employeeRepository;
	
	public Employee create(Employee employee) {
		
		return employeeRepository.save(employee);
	}

	public Employee update(Employee employee)
	{
		return employeeRepository.save(employee);
	}
	
	public void delete(Integer id)
	{
		employeeRepository.deleteById(id);
	}

	public List<Employee> findAll()
	{
		return employeeRepository.findAll();
	}
	
	public  Employee get(Integer id)
	{
		return employeeRepository.getOne(id);
	}


	public void deleteAll() {
		employeeRepository.deleteAll();
	}

	public <S extends Employee> Optional<S> findOne(Example<S> example) {
		return employeeRepository.findOne(example);
	}

	public Page<Employee> findAll(Pageable pageable) {
		return employeeRepository.findAll(pageable);
	}

	public Optional<Employee> findById(Integer id) {
		return employeeRepository.findById(id);
	}

	public <S extends Employee> List<S> saveAll(Iterable<S> entities) {
		return employeeRepository.saveAll(entities);
	}

	public boolean existsById(Integer id) {
		return employeeRepository.existsById(id);
	}

	public void deleteById(Integer id) {
		employeeRepository.deleteById(id);
	}

	public Employee getOne(Integer id) {
		return employeeRepository.getOne(id);
	}

	public <S extends Employee> boolean exists(Example<S> example) {
		return employeeRepository.exists(example);
	}

	public void delete(Employee employee) {
	
		employeeRepository.delete(employee);
	}
	

	public void deleteAll(Iterable<? extends Employee> employees) {
		employeeRepository.deleteAll(employees);
	}

	public long count() {
		return employeeRepository.count();
	}

	public List<Employee> findAll(Sort sort) {
		return employeeRepository.findAll(sort);
	}
	
	public void changeRole(String role)
	{
		Employee employee = new Employee();
			employee.setRole(role);
		
			employeeRepository.save(employee);
	}

	public Employee findByUsername(String username) {
		
		return employeeRepository.findByUsername(username);
	}
	
	
	
	
}