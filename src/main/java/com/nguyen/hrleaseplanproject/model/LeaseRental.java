package com.nguyen.hrleaseplanproject.model;

import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LeaseRental {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "car_id")
	private Car car;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employee_id")
	private Employee employee;

	@Temporal(TemporalType.DATE)
	@Column(name = "start_date_contract", nullable = false, length = 20)
	private LocalDate startDateContract;

	@Temporal(TemporalType.DATE)
	@Column(name = "expected_end_date_contract", nullable = false, length = 20)
	private LocalDate expectedEndDateContract;

	@Column(name = "contract_duration", nullable = false)
	private double contractDuration;

	@Column(name = "anual_contract_mileage", nullable = false)
	private double anualContractMileage;

	@Column(name = "lease_company", nullable = false, length = 20)
	private String leaseCompany;

	@Column(name = "lease_rate_excl_vat_fuel", nullable = false)
	private double leaseRateExclVatFuel;

	@Column(name = "lease_rental", nullable = false)
	private boolean leaseRental;
	
	@Column(name = "start_date_lease_car_driver", nullable = false)
	private LocalDate startDateLeaseCarDriver;

	@Column(name = "exceedance", nullable = false)
	private double exceedance;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public LocalDate getStartDateContract() {
		return startDateContract;
	}

	public void setStartDateContract(LocalDate startDateContract) {
		this.startDateContract = startDateContract;
	}

	public LocalDate getExpectedEndDateContract() {
		return expectedEndDateContract;
	}

	public void setExpectedEndDateContract(LocalDate expectedEndDateContract) {
		this.expectedEndDateContract = expectedEndDateContract;
	}

	public double getContractDuration() {
		return contractDuration;
	}

	public void setContractDuration(double contractDuration) {
		this.contractDuration = contractDuration;
	}

	public double getAnualContractMileage() {
		return anualContractMileage;
	}

	public void setAnualContractMileage(double anualContractMileage) {
		this.anualContractMileage = anualContractMileage;
	}

	public String getLeaseCompany() {
		return leaseCompany;
	}

	public void setLeaseCompany(String leaseCompany) {
		this.leaseCompany = leaseCompany;
	}

	public double getLeaseRateExclVatFuel() {
		return leaseRateExclVatFuel;
	}

	public void setLeaseRateExclVatFuel(double leaseRateExclVatFuel) {
		this.leaseRateExclVatFuel = leaseRateExclVatFuel;
	}

	public boolean isLeaseRental() {
		return leaseRental;
	}

	public void setLeaseRental(boolean leaseRental) {
		this.leaseRental = leaseRental;
	}
	
	public LocalDate getStartDateLeaseCarDriver() {
		return startDateLeaseCarDriver;
	}

	public void setStartDateLeaseCarDriver(LocalDate startDateLeaseCarDriver) {
		this.startDateLeaseCarDriver = startDateLeaseCarDriver;
	}

	public double getExceedance() {
		return exceedance;
	}

	public void setExceedance(double exceedance) {
		this.exceedance = exceedance;
	}

}
