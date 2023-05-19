package com.nguyen.hrleaseplanproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.nguyen.hrleaseplanproject.model.Employee;
import com.nguyen.hrleaseplanproject.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
//	public void saveEmployee(Long employeeId, String firstName, String lastName) {
//        Employee employee = new Employee();
//        employee.setEmployee_id(employeeId);
//        employee.setFirstName(firstName);  
//        employee.setLastName(lastName);
//        employeeRepository.save(employee);
//    }

	public Employee saveEmployee(Long empId, String firstName, String lastName) {
		Employee employee = new Employee();
        employee.setEmployee_id(empId);
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employeeRepository.save(employee);
        return employee;
    }
	
//	public List<Object[]> addEmployeeAndHisCar(Long empId, String license_number){
//		return employeeRepository.addEmployeeAndHisCar(empId, license_number);
//	}
	
	public List<Object[]> addEmployeeAndHisCar(Long empId, String license_number){
		return employeeRepository.addEmployeeAndHisCar(empId, license_number);
	}
	
	public Page<Object[]> findAllEmployees(int page, int size){
		PageRequest pageRequest = PageRequest.of(page, size);
		return employeeRepository.findAllEmployees(pageRequest);
	}
	
	
}
