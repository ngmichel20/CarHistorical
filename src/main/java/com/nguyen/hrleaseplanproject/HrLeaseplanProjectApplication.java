package com.nguyen.hrleaseplanproject;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@SpringBootApplication
public class HrLeaseplanProjectApplication {

//	 public static void readExcelFile(File filePath) {
//		 try {
//	            Workbook workbook = Workbook.getWorkbook(filePath);
//	            Sheet sheet = workbook.getSheet(0);
//
//	            for (int i = 0; i < sheet.getRows(); i++) {
//	                for (int j = 0; j < sheet.getColumns(); j++) {
//	                    Cell cell = sheet.getCell(j, i);
//	                    System.out.print(cell.getContents() + "\t");
//	                }
//	                System.out.println();
//	            }
//
//	            workbook.close();
//	        } catch (BiffException | IOException e) {
//	            e.printStackTrace();
//	        }
//	    }

	public static void main(String[] args) {

		// readExcelFile(new File(filePath));

		SpringApplication.run(HrLeaseplanProjectApplication.class, args);
		readExcel();

	}

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
