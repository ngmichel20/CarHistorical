package com.nguyen.hrleaseplanproject.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import com.nguyen.hrleaseplanproject.model.Employee;

@Service
public class ServiceMain {
	
	private static List<Employee> employees = new ArrayList<>();
	
	
	private static void readExcel() {
		String filePath = "src/main/resources/hr-leaseplan.xlsx";

		File file = new File(filePath);
		System.out.println(file.getAbsolutePath());

		try (FileInputStream inputStream = new FileInputStream(file)) {
			Workbook workbook = new XSSFWorkbook(inputStream);
			Sheet sheet = workbook.getSheetAt(0);

			for (Row row : sheet) {
				for (Cell cell : row) {
					String cellValue = "";
					switch (cell.getCellType()) {
					case NUMERIC:
						cellValue = String.valueOf(cell.getNumericCellValue());
						break;
					case STRING:
						cellValue = cell.getStringCellValue();
						break;
					case BOOLEAN:
						cellValue = String.valueOf(cell.getBooleanCellValue());
						break;
					default:
						break;

					}
					System.out.print(cellValue + "hallo \t");
				}
				System.out.println();
			}
			workbook.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
