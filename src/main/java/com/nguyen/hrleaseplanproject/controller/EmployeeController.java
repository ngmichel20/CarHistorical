package com.nguyen.hrleaseplanproject.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nguyen.hrleaseplanproject.model.Employee;
import com.nguyen.hrleaseplanproject.repository.CarRepository;
import com.nguyen.hrleaseplanproject.repository.EmployeeRepository;
import com.nguyen.hrleaseplanproject.service.ServiceMain;

@RestController
public class EmployeeController {

	@Autowired
	private ServiceMain serviceMain;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private CarRepository carRepository;

//	public EmployeeController(ServiceMain serviceMain, EmployeeRepository employeeRepository) {
//		//super();
//		//this.serviceMain = serviceMain;
//		this.employeeRepository = employeeRepository;
//	}

//	public EmployeeController(EmployeeRepository employeeRepository) {
//		this.employeeRepository = employeeRepository;
//	}

	@GetMapping(path = "/hello-world")
	public List<Employee> helloWorld() {
		return employeeRepository.findAll();
	}

	@GetMapping(path = "/hello-world-bean")
	public Employee helloEmployee() {
		return new Employee();
	}

//	@GetMapping(path = "/list-employees/{license_number}")
//	public List<Employee> findEmployeesByCarLicenseNumber(@PathVariable("license_number") String license_number){
//		return employeeRepository.findEmployeesByCarLicenseNumber(license_number);
//	}

	@GetMapping(path = "/employee-by-license-number")
	public List<Object[]> findEmployeesByCarLicenseNumber(@RequestParam String license_number) {
		return employeeRepository.findEmployeesByCarLicenseNumber(license_number);
	}

	@GetMapping(path = "/car-by-employee-id")
	public List<Object[]> findCarsByEmpId(@RequestParam Long employeeId) {
		return carRepository.findCarsByEmployeeId(employeeId);
	}

	@PostMapping(path="/import-excel")
	public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
		try {
            // Check if the uploaded file is an Excel file
            if (!file.getOriginalFilename().endsWith(".xlsx")) {
                return ResponseEntity.badRequest().body("Uploaded file is not an Excel file");
            }

            // Process the Excel file and store data in database
            serviceMain.readExcelFromUpload(file.getInputStream());

            return ResponseEntity.ok().body("File uploaded and processed successfully");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to process file");
        }
	}

//	@GetMapping(path="/{id}")
//	public List<Employee> findEmployeeByFirstname1(@PathVariable Long id) {
//		System.out.println("HELLO");
//		return employeeRepository.findEmployeeByFirstname1(id);
//	}

//	@PostMapping(path = "/upload-excel")
//	public void uploadExcel() {
//		serviceMain.readExcel();
//	}

//	//TODO - Create a employee when Andrea wants to add a new one
//	public ResponseEntity<Employee> createEmployee(){
//		
//		Employee empCreated
//		return ResponseEntity.created(null)
//	}
}
