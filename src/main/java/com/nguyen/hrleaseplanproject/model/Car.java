package com.nguyen.hrleaseplanproject.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Car {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long licenseNumber;
	
	private String carBrand;
	private String carModel;
	private String carType;
	
	private String make;

	private String model;

	private Integer year;

	private String licensePlate;

	private String vin;
	
	@OneToOne(mappedBy = "car")
    private Employee employee;
}
