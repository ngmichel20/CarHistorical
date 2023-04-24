package com.nguyen.hrleaseplanproject.model;

import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Car {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long car_id;
	
	@Column(name = "car_brand", nullable = false, length = 45)
	private String carBrand;
	
	@Column(name = "car_model", nullable = false, length = 45)
	private String carModel;
	
	@Column(name = "car_type", nullable = false, length = 45)
	private String carType;
	
	@Column(name = "license_number", nullable = false, length = 45)
	private String licenseNumber;
	
	@Column(name = "tax_value", nullable = false)
	private double taxValue;
	
	@Column(name = "nedc_co2_emission", nullable = false)
	private double nedcCO2Emission;
	
	@Column(name = "co2_emission", nullable = false)
	private double CO2Emission;
	
	@Column(name = "fuel", nullable = false, length = 20)
	private String fuel;
	
	@Temporal(TemporalType.DATE)
	private LocalDate dateFirstRegistration;

	
	
	public Long getCar_id() {
		return car_id;
	}

	public void setCar_id(Long car_id) {
		this.car_id = car_id;
	}

	public String getCarBrand() {
		return carBrand;
	}

	public void setCarBrand(String carBrand) {
		this.carBrand = carBrand;
	}

	public String getCarModel() {
		return carModel;
	}

	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public String getLicenseNumber() {
		return licenseNumber;
	}

	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	public double getTaxValue() {
		return taxValue;
	}

	public void setTaxValue(double taxValue) {
		this.taxValue = taxValue;
	}

	public double getNedcCO2Emission() {
		return nedcCO2Emission;
	}

	public void setNedcCO2Emission(double nedcCO2Emission) {
		this.nedcCO2Emission = nedcCO2Emission;
	}

	public double getCO2Emission() {
		return CO2Emission;
	}

	public void setCO2Emission(double cO2Emission) {
		this.CO2Emission = cO2Emission;
	}

	public String getFuel() {
		return fuel;
	}

	public void setFuel(String fuel) {
		this.fuel = fuel;
	}

	public LocalDate getDateFirstRegistration() {
		return dateFirstRegistration;
	}

	public void setDateFirstRegistration(LocalDate dateFirstRegistration) {
		this.dateFirstRegistration = dateFirstRegistration;
	}
	
	
	
	
//	@OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Employee> historicalsDrivers = new ArrayList<>();
}
