package com.nguyen.hrleaseplanproject.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
	
	private String carBrand;
	private String carModel;
	private String carType;
	
	@Temporal(TemporalType.DATE)
	private String dateFirstRegistration;

	private Integer year;

	private String licenseNumber;

	private String vin;
	
	@OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Historical> historicals = new ArrayList<>();
}
