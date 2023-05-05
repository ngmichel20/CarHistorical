package com.nguyen.hrleaseplanproject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.util.unit.DataSize;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.nguyen.hrleaseplanproject.service.ServiceMain;

import jakarta.servlet.MultipartConfigElement;
import jakarta.servlet.annotation.MultipartConfig;

@SpringBootApplication
//@MultipartConfig(maxFileSize = 500 * 1024 * 1024, maxRequestSize = 500 * 1024 * 1024)
//@EntityScan("com.nguyen.hrleaseplanproject.model")
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
		String filePath = "src/main/resources/hr-leaseplan.xlsx";
		String filePath2 = "src/main/resources/hr-leaseplan-import-second.xlsx";
		serviceMain.readExcel(filePath);
//		System.out.println("AHERHERHERHER");
//		serviceMain.readExcel(filePath2);
		
		//Uncomment this to launch without codes above
		//SpringApplication.run(HrLeaseplanProjectApplication.class, args);
		
		//readExcel();
		
		//http://localhost:3000/ to 8080
		//Cross Origin Requests
		//Allow all requests only from http://localhost:3000/
		

	}
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
					.allowedMethods("*")
					.allowedOrigins("http://localhost:3000");
			}
		};
	}
	
//	@Bean
//	public MultipartConfigElement multipartConfigElement (){
//        MultipartConfigFactory factory = new MultipartConfigFactory();
//        factory.setMaxFileSize(DataSize.parse("10MB"));
//        factory.setMaxRequestSize(DataSize.parse("10MB"));
//        return factory.createMultipartConfig();
//    }
	
	@Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(DataSize.ofMegabytes(500));
        factory.setMaxRequestSize(DataSize.ofMegabytes(500));
        return factory.createMultipartConfig();
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
