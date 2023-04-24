package com.nguyen.hrleaseplanproject.model;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
//@NoArgsConstructor
//@AllArgsConstructor
@Getter
@Setter
public class Employee {
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long employee_id;

	@Column(name = "first_name", nullable = false, length = 45)
	private String firstName;

	@Column(name = "last_name", nullable = false, length = 45)
	private String lastName;

	
	public Long getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(@Nonnull Long employee_id) {
		this.employee_id = employee_id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	
	
	//private String department;

	//private String position;
	
//	@OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
//    private LeaseRental historicalCars;
}
