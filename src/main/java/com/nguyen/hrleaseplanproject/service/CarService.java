package com.nguyen.hrleaseplanproject.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nguyen.hrleaseplanproject.model.Car;
import com.nguyen.hrleaseplanproject.repository.CarRepository;

@Service
public class CarService {
	
	@Autowired
	private CarRepository carRepository;
	
	public Car saveCar(String carBrand, String carModel, String carType, String licenseNumber, 
						double taxValue, double nedcCO2Emission, double CO2Emission,
						String fuel, LocalDate dateFirstRegistration) {
		
		Car car = new Car();
		car.setCarBrand(carBrand);
		car.setCarModel(carModel);
		car.setCarType(carType);
		car.setLicenseNumber(licenseNumber);
		car.setTaxValue(taxValue);
		car.setNedcCO2Emission(nedcCO2Emission);
		car.setCO2Emission(CO2Emission);
		car.setFuel(fuel);
		car.setDateFirstRegistration(dateFirstRegistration);
		carRepository.save(car);	
		return car;
	}
}
