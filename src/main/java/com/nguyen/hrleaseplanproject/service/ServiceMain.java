package com.nguyen.hrleaseplanproject.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
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

@Service
public class ServiceMain {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private CarService carService;

	@Autowired
	private LeaseRentalService leaseRentalService;

	public void readExcel(String filePath) {
	
		File file = new File(filePath);
		System.out.println(file.getAbsolutePath());

		try (FileInputStream inputStream = new FileInputStream(file)) {
			Workbook workbook = new XSSFWorkbook(inputStream);
			Sheet sheet = workbook.getSheetAt(0);

			int rowExcel = sheet.getLastRowNum(); // or sheet.getPhysicalNumberOfRows
			int columnExcel = 20;
			int[][] myArray = new int[rowExcel][columnExcel];

			System.out.println( "NUMBER ROW "+ sheet.getLastRowNum());
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
						System.out.println("empID " + empId);
						break;

					case 1: // EmployeeFullName
						cell = row.getCell(c);
						System.out.println(cell.toString());
						cellEmpFullname = cell.getStringCellValue();
						splittedFullName = cellEmpFullname.split(",");
						System.out.println("splittedFullName " + splittedFullName[0] + " " + splittedFullName[1]);
						break;

					case 2: // LicenseNumber
						cell = row.getCell(c);
						cellLicenseNumber = cell.getStringCellValue();
						break;

					case 3: // TaxValue
						cell = row.getCell(c);
						cellTaxValue = cell.getNumericCellValue();
						break;

					case 4: // NEDC CO2 emission
						cell = row.getCell(c);
						cellNedcCO2Emission = cell.getNumericCellValue();
						break;

					case 5: // CO2 emission
						cell = row.getCell(c);
						cellCO2Emission = cell.getNumericCellValue();
						break;

					case 6: // Lease/Rental
						cell = row.getCell(c);
						String leaseString = cell.getStringCellValue();
						if (leaseString.equalsIgnoreCase("lease"))
							cellLeaseOrNot = true;
						break;

					case 7: // fuel
						cell = row.getCell(c);
						cellFuel = cell.getStringCellValue();
						break;

					case 8: // car brand
						cell = row.getCell(c);
						cellCarBrand = cell.getStringCellValue();
						break;

					case 9: // car model
						cell = row.getCell(c);
						cellCarModel = cell.getStringCellValue();
						break;

					case 10: // car type
						cell = row.getCell(c);
						cellCarType = cell.getStringCellValue();
						break;

					case 11: // DateFirstRegistration
						dateFirstRegistration = dateExtractor(row, c);
						break;

					case 12: // Contract duration
						cell = row.getCell(c);
						cellContractDuration = cell.getNumericCellValue();
						break;

					case 13: // AnualContractMileage
						cell = row.getCell(c);
						cellAnualContractMileage = cell.getNumericCellValue();
						break;

					case 14: // Startdate contract
						cellStartDateContract = dateExtractor(row, c);
						break;

					case 15: // end date contract
						cellExpectedEndDateContract = dateExtractor(row, c);
						break;

					case 16: // Lease rate excl VAT/Fuel
						cell = row.getCell(c);
						cellLeaseRateExclVatFuel = cell.getNumericCellValue();
						break;

					case 17: // Lease company
						cell = row.getCell(c);
						cellLeaseCompany = cell.getStringCellValue();
						break;

					case 18: // Startdate leasecar-driver
						cellStartDateLeaseCarDriver = dateExtractor(row, c);
						break;

					case 19: // Exceedance
						cell = row.getCell(c);
						cellExceedance = cell.getNumericCellValue();
						break;
					}

					System.out.print("\t");

				}
				
				
				
				Employee employee = employeeService.saveEmployee(empId, splittedFullName[1], splittedFullName[0]);
				Car car = carService.saveCar(cellCarBrand, cellCarModel, cellCarType, cellLicenseNumber, cellTaxValue,
						cellNedcCO2Emission, cellCO2Emission, cellFuel, dateFirstRegistration);

				leaseRentalService.saveLeaseRental(car, employee, cellStartDateContract, cellExpectedEndDateContract,
						cellContractDuration, cellAnualContractMileage, cellLeaseCompany, cellLeaseRateExclVatFuel,
						cellLeaseOrNot, cellStartDateLeaseCarDriver, cellExceedance);

				System.out.println();
			}
			workbook.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void readExcelFromUpload(InputStream inputStream) {
//		String filePath = "src/main/resources/hr-leaseplan.xlsx";
//
//		File file = new File(filePath);
//		System.out.println(file.getAbsolutePath());

		Workbook workbook;
		try {
			workbook = new XSSFWorkbook(inputStream);

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

					case 2: // LicenseNumber
						cell = row.getCell(c);
						cellLicenseNumber = cell.getStringCellValue();
						break;

					case 3: // TaxValue
						cell = row.getCell(c);
						cellTaxValue = cell.getNumericCellValue();
						break;

					case 4: // NEDC CO2 emission
						cell = row.getCell(c);
						cellNedcCO2Emission = cell.getNumericCellValue();
						break;

					case 5: // CO2 emission
						cell = row.getCell(c);
						cellCO2Emission = cell.getNumericCellValue();
						break;

					case 6: // Lease/Rental
						cell = row.getCell(c);
						String leaseString = cell.getStringCellValue();
						if (leaseString.equalsIgnoreCase("lease"))
							cellLeaseOrNot = true;
						break;

					case 7: // fuel
						cell = row.getCell(c);
						cellFuel = cell.getStringCellValue();
						break;

					case 8: // car brand
						cell = row.getCell(c);
						cellCarBrand = cell.getStringCellValue();
						break;

					case 9: // car model
						cell = row.getCell(c);
						cellCarModel = cell.getStringCellValue();
						break;

					case 10: // car type
						cell = row.getCell(c);
						cellCarType = cell.getStringCellValue();
						break;

					case 11: // DateFirstRegistration
						dateFirstRegistration = dateExtractor(row, c);
						break;

					case 12: // Contract duration
						cell = row.getCell(c);
						cellContractDuration = cell.getNumericCellValue();
						break;

					case 13: // AnualContractMileage
						cell = row.getCell(c);
						cellAnualContractMileage = cell.getNumericCellValue();
						break;

					case 14: // Startdate contract
						cellStartDateContract = dateExtractor(row, c);
						break;

					case 15: // end date contract
						cellExpectedEndDateContract = dateExtractor(row, c);
						break;

					case 16: // Lease rate excl VAT/Fuel
						cell = row.getCell(c);
						cellLeaseRateExclVatFuel = cell.getNumericCellValue();
						break;

					case 17: // Lease company
						cell = row.getCell(c);
						cellLeaseCompany = cell.getStringCellValue();
						break;

					case 18: // Startdate leasecar-driver
						cellStartDateLeaseCarDriver = dateExtractor(row, c);
						break;

					case 19: // Exceedance
						cell = row.getCell(c);
						cellExceedance = cell.getNumericCellValue();
						break;
					}

					System.out.print("\t");

				}

				Employee employee = employeeService.saveEmployee(empId, splittedFullName[1], splittedFullName[0]);

				Car car = carService.saveCar(cellCarBrand, cellCarModel, cellCarType, cellLicenseNumber, cellTaxValue,
						cellNedcCO2Emission, cellCO2Emission, cellFuel, dateFirstRegistration);

				leaseRentalService.saveLeaseRental(car, employee, cellStartDateContract, cellExpectedEndDateContract,
						cellContractDuration, cellAnualContractMileage, cellLeaseCompany, cellLeaseRateExclVatFuel,
						cellLeaseOrNot, cellStartDateLeaseCarDriver, cellExceedance);

				System.out.println();
			}
			workbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private LocalDate dateExtractor(Row row, int c) {
		double cellDateFirstRegistration;
		LocalDate dateFirstRegistration;
		Cell cell;
		cell = row.getCell(c);
		cellDateFirstRegistration = cell.getNumericCellValue();
		DataFormatter dataFormatter = new DataFormatter(Locale.FRENCH);
		String myDate = dataFormatter.formatCellValue(cell);
		String formattedString = myDate.replace("/", "-");

		DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("M-d-yy");
		DateTimeFormatter outputFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)
				.withLocale(Locale.FRENCH);

		dateFirstRegistration = LocalDate.parse(formattedString, inputFormatter);
		String frenchDate = dateFirstRegistration.format(outputFormatter);

		return dateFirstRegistration;
	}

}
