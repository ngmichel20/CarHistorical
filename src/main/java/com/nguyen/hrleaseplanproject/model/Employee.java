package com.nguyen.hrleaseplanproject.model;

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long employee_id;

	private String firstName;

	private String lastName;

	//private String department;

	//private String position;

	private Date hireDate;
	
	@OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private Historical historical;
}
