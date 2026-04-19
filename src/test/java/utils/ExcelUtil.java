package utils;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
    private Workbook workbook;
	  public ExcelUtil(String excelPath) {
	        try {
	            FileInputStream fis = new FileInputStream(excelPath);
	            workbook = new XSSFWorkbook(fis);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    public String getCellData(String sheetName, int row, int col) {
	        Sheet sheet = workbook.getSheet(sheetName);
	        Row r = sheet.getRow(row);
	        Cell cell = r.getCell(col);
	        return cell.getStringCellValue();
	    }
    
}