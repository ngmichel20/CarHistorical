package com.nguyen.hrleaseplanproject.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nguyen.hrleaseplanproject.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

//	@Query("SELECT employee.employee_id, employee.first_name, employee.last_name "
//			+ "FROM car "
//			+ "JOIN lease_rental "
//			+ "ON car.car_id=lease_rental.car_id "
//			+ "JOIN employee "
//			+ "ON employee.employee_id=lease_rental.employee_id "
//			+ "WHERE car.license_number= :license_number")
//	public List<Employee> findEmployeesByCarLicenseNumber(@Param("license_number") String license_number);
	
	@Query(value = "SELECT lease_rental.employee_id, employee.first_name, employee.last_name, "
			+ "car.license_number, car.car_brand, car.car_model, car.car_type, car.date_first_registration, "
			+ "lease_rental.start_date_contract, lease_rental.expected_end_date_contract, lease_rental.start_date_lease_car_driver "
			+ "from car "
			+ "join lease_rental on car.car_id=lease_rental.car_id "
			+ "join employee on employee.employee_id=lease_rental.employee_id "
			+ "WHERE car.license_number=?1 "
			+ "ORDER BY lease_rental.start_date_lease_car_driver DESC", nativeQuery = true)
	public List<Object[]> findEmployeesByCarLicenseNumber(String license_number);
	
	
//	@Query(value = "SELECT lease_rental.employee_id, employee.first_name, employee.last_name, "
//			+ "car.license_number, car.car_brand, car.car_model, car.car_type, car.date_first_registration, "
//			+ "lease_rental.start_date_contract, lease_rental.expected_end_date_contract, lease_rental.start_date_lease_car_driver "
//			+ "from car "
//			+ "join lease_rental on car.car_id=lease_rental.car_id "
//			+ "join employee on employee.employee_id=lease_rental.employee_id "
//			+ "ORDER BY lease_rental.employee_id ASC", nativeQuery = true) 
//	public List<Object[]> findAllEmployees();
	
	
	@Query(value = "SELECT lease_rental.employee_id, employee.first_name, employee.last_name, "
			+ "car.license_number, car.car_brand, car.car_model, car.car_type, car.date_first_registration, "
			+ "lease_rental.start_date_contract, lease_rental.expected_end_date_contract, lease_rental.start_date_lease_car_driver "
			+ "from car "
			+ "join lease_rental on car.car_id=lease_rental.car_id "
			+ "join employee on employee.employee_id=lease_rental.employee_id "
			+ "ORDER BY lease_rental.employee_id ASC", nativeQuery = true) 
	public Page<Object[]> findAllEmployees(Pageable pageRequest);
	
	
	@Query(value = "SELECT lease_rental.employee_id, car.license_number "
			+ "from car "
			+ "join lease_rental on car.car_id=lease_rental.car_id "
			+ "WHERE lease_rental.employee_id=?1 AND car.license_number=?2", nativeQuery = true) 
	public List<Object[]> addEmployeeAndHisCar(Long employeeId, String license_number);
	

	
}
