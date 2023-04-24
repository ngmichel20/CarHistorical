package com.nguyen.hrleaseplanproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nguyen.hrleaseplanproject.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
