package com.nguyen.hrleaseplanproject.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nguyen.hrleaseplanproject.model.Car;
import com.nguyen.hrleaseplanproject.model.Employee;
import com.nguyen.hrleaseplanproject.model.LeaseRental;
import com.nguyen.hrleaseplanproject.repository.LeaseRentalRepository;

@Service
public class LeaseRentalService {

	@Autowired
	private LeaseRentalRepository leaseRentalRepository;

	public void saveLeaseRental(Car car, Employee employee, LocalDate startDateContract,
			LocalDate expectedEndDateContract, double contractDuration, double anualContractMileage,
			String leaseCompany, double leaseRateExclVatFuel, boolean leaseOrNot, 
			LocalDate startDateLeaseaCarDriver, double exceedance) {

		LeaseRental leaseRental = new LeaseRental();
		leaseRental.setCar(car);
		leaseRental.setEmployee(employee);
		leaseRental.setStartDateContract(startDateContract);
		leaseRental.setExpectedEndDateContract(expectedEndDateContract);
		leaseRental.setContractDuration(contractDuration);
		leaseRental.setAnualContractMileage(anualContractMileage);
		leaseRental.setLeaseCompany(leaseCompany);
		leaseRental.setLeaseRateExclVatFuel(leaseRateExclVatFuel);
		leaseRental.setStartDateLeaseCarDriver(startDateLeaseaCarDriver);
		leaseRental.setExceedance(exceedance);
		leaseRental.setLeaseRental(leaseOrNot);
		leaseRentalRepository.save(leaseRental);
	}
}
