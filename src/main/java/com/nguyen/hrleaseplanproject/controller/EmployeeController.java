package com.nguyen.hrleaseplanproject.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nguyen.hrleaseplanproject.model.Employee;

@RestController
public class EmployeeController {

	@GetMapping(path = "/hello-world")
	public String helloWorld() {
		return "Hello world";
	}
	
	@GetMapping(path = "/hello-world-bean")
	public Employee helloEmployee() {
		return new Employee();
	}
}
