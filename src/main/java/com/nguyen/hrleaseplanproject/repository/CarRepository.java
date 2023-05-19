package com.nguyen.hrleaseplanproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nguyen.hrleaseplanproject.model.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
	@Query(value = "SELECT car.license_number, lease_rental.employee_id, employee.first_name, employee.last_name, "
			+ "car.car_brand, car.car_model, car.car_type, car.date_first_registration, "
			+ "lease_rental.start_date_contract, lease_rental.expected_end_date_contract, lease_rental.start_date_lease_car_driver "
			+ "FROM car "
			+ "JOIN lease_rental "
			+ "ON car.car_id=lease_rental.car_id "
			+ "JOIN employee "
			+ "ON employee.employee_id=lease_rental.employee_id "
			+ "WHERE lease_rental.employee_id= ?1 "
			+ "ORDER BY lease_rental.start_date_lease_car_driver DESC" , nativeQuery = true)
    public List<Object[]> findCarsByEmployeeId(Long employeeId);
    
}
