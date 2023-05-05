package com.nguyen.hrleaseplanproject.util;

//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//import com.nguyen.hrleaseplanproject.model.Car;
//import com.nguyen.hrleaseplanproject.model.Employee;



import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nguyen.hrleaseplanproject.model.Car;
import com.nguyen.hrleaseplanproject.model.Employee;


public class ExcelReader {
	
    private String filePath = "src/main/resources/hr-leaseplan.xlsx";

    public List<Employee> readEmployees() throws IOException {
        List<Employee> employees = new ArrayList<>();       
        File file = new File(filePath);
		System.out.println(file.getAbsolutePath());

		try (FileInputStream inputStream = new FileInputStream(file)) {
			Workbook workbook = new XSSFWorkbook(inputStream);
			Sheet sheet = workbook.getSheetAt(0);

			int rowExcel = sheet.getLastRowNum(); // or sheet.getPhysicalNumberOfRows
			int columnExcel = 20;
			int[][] myArray = new int[rowExcel][columnExcel];

			System.out.println(sheet.getLastRowNum());
			for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
				Row row = sheet.getRow(rowIndex);
				if (row == null) {
					continue;
				}

				int cells = row.getPhysicalNumberOfCells();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd");

				double cellEmpId = 0;
				String cellEmpFullname = "";

				String cellLicenseNumber = "";
				double cellTaxValue = 0;
				double cellNedcCO2Emission = 0;
				double cellCO2Emission = 0;
				String cellFuel = "";
				String cellCarBrand = "";
				String cellCarModel = "";
				String cellCarType = "";
				double cellDateFirstRegistration;

				LocalDate cellStartDateContract = null;
				LocalDate cellExpectedEndDateContract = null;
				double cellContractDuration = 0;
				double cellAnualContractMileage = 0;
				String cellLeaseCompany = "";
				double cellLeaseRateExclVatFuel = 0;
				boolean cellLeaseOrNot = false;
				LocalDate cellStartDateLeaseCarDriver = null;
				double cellExceedance = 0;

				Long empId = Long.MIN_VALUE;
				String[] splittedFullName = new String[2];
				LocalDate dateFirstRegistration = null;

				for (int c = 0; c < cells; c++) { // index c is the column of the Excel
					Cell cell = row.getCell(c);

					if (cell == null) {
						continue;
					}

					switch (c) {

					case 0: // EmployeeId
						cell = row.getCell(c);
						cellEmpId = cell.getNumericCellValue();
						empId = Double.valueOf(cellEmpId).longValue();
						break;

					case 1: // EmployeeFullName
						cell = row.getCell(c);
						cellEmpFullname = cell.getStringCellValue();
						splittedFullName = cellEmpFullname.split(",");
						break;
			
					}

					System.out.print("\t");

				}

				Employee employee = new Employee();
				employee.setEmployee_id(empId);
		        employee.setFirstName(splittedFullName[1]);
		        employee.setLastName(splittedFullName[0]);
		        employees.add(employee);

				System.out.println();
			}
			
			workbook.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		
        return employees;
    }

//    public List<Car> readCars() throws IOException {
//        List<Car> cars = new ArrayList<>();
//        
//        
//        FileInputStream inputStream = new FileInputStream(new File(fileName));
//        Workbook workbook = new XSSFWorkbook(inputStream);
//        Sheet sheet = workbook.getSheetAt(1);
//
//        Iterator<Row> iterator = sheet.iterator();
//        while (iterator.hasNext()) {
//            Row currentRow = iterator.next();
//            if (currentRow.getRowNum() == 0) {
//                continue; // skip header row
//            }
//            Car car = new Car();
//            car.setBrand(currentRow.getCell(0).getStringCellValue());
//            car.setModel(currentRow.getCell(1).getStringCellValue());
//            car.setYear((int) currentRow.getCell(2).getNumericCellValue());
//            cars.add(car);
//        }
//
//        workbook.close();
//        inputStream.close();
//        
//        
//        
//        File file = new File(filePath);
//		System.out.println(file.getAbsolutePath());
//
//		try (FileInputStream inputStream = new FileInputStream(file)) {
//			Workbook workbook = new XSSFWorkbook(inputStream);
//			Sheet sheet = workbook.getSheetAt(0);
//
//			int rowExcel = sheet.getLastRowNum(); // or sheet.getPhysicalNumberOfRows
//			int columnExcel = 20;
//			int[][] myArray = new int[rowExcel][columnExcel];
//
//			System.out.println(sheet.getLastRowNum());
//			for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
//				Row row = sheet.getRow(rowIndex);
//				if (row == null) {
//					continue;
//				}
//
//				int cells = row.getPhysicalNumberOfCells();
//				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd");
//
//				double cellEmpId = 0;
//				String cellEmpFullname = "";
//
//				String cellLicenseNumber = "";
//				double cellTaxValue = 0;
//				double cellNedcCO2Emission = 0;
//				double cellCO2Emission = 0;
//				String cellFuel = "";
//				String cellCarBrand = "";
//				String cellCarModel = "";
//				String cellCarType = "";
//				double cellDateFirstRegistration;
//
//				LocalDate cellStartDateContract = null;
//				LocalDate cellExpectedEndDateContract = null;
//				double cellContractDuration = 0;
//				double cellAnualContractMileage = 0;
//				String cellLeaseCompany = "";
//				double cellLeaseRateExclVatFuel = 0;
//				boolean cellLeaseOrNot = false;
//				LocalDate cellStartDateLeaseCarDriver = null;
//				double cellExceedance = 0;
//
//				Long empId = Long.MIN_VALUE;
//				String[] splittedFullName = new String[2];
//				LocalDate dateFirstRegistration = null;
//
//				for (int c = 0; c < cells; c++) { // index c is the column of the Excel
//					Cell cell = row.getCell(c);
//
//					if (cell == null) {
//						continue;
//					}
//
//					switch (c) {
//
//					case 2: // LicenseNumber
//						cell = row.getCell(c);
//						cellLicenseNumber = cell.getStringCellValue();
//						break;
//
//					case 3: // TaxValue
//						cell = row.getCell(c);
//						cellTaxValue = cell.getNumericCellValue();
//						break;
//
//					case 4: // NEDC CO2 emission
//						cell = row.getCell(c);
//						cellNedcCO2Emission = cell.getNumericCellValue();
//						break;
//
//					case 5: // CO2 emission
//						cell = row.getCell(c);
//						cellCO2Emission = cell.getNumericCellValue();
//						break;
//
//					case 6: // Lease/Rental
//						cell = row.getCell(c);
//						String leaseString = cell.getStringCellValue();
//						if (leaseString.equalsIgnoreCase("lease"))
//							cellLeaseOrNot = true;
//						break;
//
//					case 7: // fuel
//						cell = row.getCell(c);
//						cellFuel = cell.getStringCellValue();
//						break;
//
//					case 8: // car brand
//						cell = row.getCell(c);
//						cellCarBrand = cell.getStringCellValue();
//						break;
//
//					case 9: // car model
//						cell = row.getCell(c);
//						cellCarModel = cell.getStringCellValue();
//						break;
//
//					case 10: // car type
//						cell = row.getCell(c);
//						cellCarType = cell.getStringCellValue();
//						break;
//
//					case 11: // DateFirstRegistration
//						dateFirstRegistration = dateExtractor(row, c);
//						break;
//
//					case 12: // Contract duration
//						cell = row.getCell(c);
//						cellContractDuration = cell.getNumericCellValue();
//						break;
//
//					case 13: // AnualContractMileage
//						cell = row.getCell(c);
//						cellAnualContractMileage = cell.getNumericCellValue();
//						break;
//
//					case 14: // Startdate contract
//						cellStartDateContract = dateExtractor(row, c);
//						break;
//
//					case 15: // end date contract
//						cellExpectedEndDateContract = dateExtractor(row, c);
//						break;
//
//					case 16: // Lease rate excl VAT/Fuel
//						cell = row.getCell(c);
//						cellLeaseRateExclVatFuel = cell.getNumericCellValue();
//						break;
//
//					case 17: // Lease company
//						cell = row.getCell(c);
//						cellLeaseCompany = cell.getStringCellValue();
//						break;
//
//					case 18: // Startdate leasecar-driver
//						cellStartDateLeaseCarDriver = dateExtractor(row, c);
//						break;
//
//					case 19: // Exceedance
//						cell = row.getCell(c);
//						cellExceedance = cell.getNumericCellValue();
//						break;
//					}
//
//					System.out.print("\t");
//
//				}
//
//				Employee employee = employeeService.saveEmployee(empId, splittedFullName[1], splittedFullName[0]);
//
//				Car car = carService.saveCar(cellCarBrand, cellCarModel, cellCarType, cellLicenseNumber, cellTaxValue,
//						cellNedcCO2Emission, cellCO2Emission, cellFuel, dateFirstRegistration);
//
//				leaseRentalService.saveLeaseRental(car, employee, cellStartDateContract, cellExpectedEndDateContract,
//						cellContractDuration, cellAnualContractMileage, cellLeaseCompany, cellLeaseRateExclVatFuel,
//						cellLeaseOrNot, cellStartDateLeaseCarDriver, cellExceedance);
//
//				System.out.println();
//			}
//			workbook.close();
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//        
//        
//
//        return cars;
//    }
//
//    public List<LeaseRental> readLeaseRentals() throws IOException {
//        List<LeaseRental> leaseRentals = new ArrayList<>();
//        FileInputStream inputStream = new FileInputStream(new File(fileName));
//        Workbook workbook = new XSSFWorkbook(inputStream);
//        Sheet sheet = workbook.getSheetAt(2);
//
//        Iterator<Row> iterator = sheet.iterator();
//        while (iterator.hasNext()) {
//            Row currentRow = iterator.next();
//            if (currentRow.getRowNum() == 0) {
//                continue; // skip header row
//            }
//            LeaseRental leaseRental = new LeaseRental();
//            leaseRental.setCar(currentRow.getCell(0).getStringCellValue());
//            leaseRental.setEmployee(currentRow.getCell(1).getStringCellValue());
//            leaseRental.setStartDate(currentRow.getCell(2).getDateCellValue());
//            leaseRental.setEndDate(currentRow.getCell(3).getDateCellValue());
//            leaseRentals.add(leaseRental);
//        }
//
//        workbook.close();
//        inputStream.close();
//
//        return leaseRentals;
//    }
}
