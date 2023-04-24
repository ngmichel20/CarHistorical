package com.nguyen.hrleaseplanproject;

import org.springframework.context.ApplicationContext;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nguyen.hrleaseplanproject.service.ServiceMain;

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
		org.springframework.context.ApplicationContext context = SpringApplication.run(HrLeaseplanProjectApplication.class, args);
		ServiceMain serviceMain = context.getBean(ServiceMain.class);
		serviceMain.readExcel();
		
		//SpringApplication.run(HrLeaseplanProjectApplication.class, args);
		
		//readExcel();

	}

	private static void readExcel() {
		String filePath = "src/main/resources/hr-leaseplan.xlsx";

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

//				for (Cell cell : row) {
//					String cellValue = "";

				int cells = row.getPhysicalNumberOfCells();
				for (int c = 0; c < cells; c++) {
					Cell cell = row.getCell(c);
					if (cell == null) {
						continue;
					}
					
					
					if(c==0) {
						
					}
					
					String cellValue = "";
					switch (cell.getCellType()) {
					case NUMERIC:
						cellValue = String.valueOf(cell.getNumericCellValue());
						System.out.print(" nunu ");
						break;
					case STRING:
						cellValue = cell.getStringCellValue();
						System.out.print(" stristri ");
						break;
					case BOOLEAN:
						cellValue = String.valueOf(cell.getBooleanCellValue());
						System.out.println("bobo");
						break;
					default:
						break;

					}

					System.out.print(cellValue + "\t");

				}
				System.out.println();
			}
			workbook.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
