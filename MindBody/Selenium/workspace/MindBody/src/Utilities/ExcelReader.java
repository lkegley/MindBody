package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	
	public static HashMap <String, String> ReadRow(int iRow) throws IOException {

		String excelFilePath = "C:\\MindBody\\Selenium\\workspace\\MindBody\\data\\DataInput.xlsx";
        FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
         
        Workbook workbook = new XSSFWorkbook(inputStream);
        
		HashMap<String, String> DataMap = new HashMap <String, String>(); 
		int i = 0;
		while (workbook.getSheetAt(0).getRow(0).getCell(i) != null)
		{
			 if (workbook.getSheetAt(0).getRow(iRow).getCell(i) != null)
				 DataMap.put(workbook.getSheetAt(0).getRow(0).getCell(i).toString(), workbook.getSheetAt(0).getRow(iRow).getCell(i).toString());
			 else
				 DataMap.put(workbook.getSheetAt(0).getRow(0).getCell(i).toString(),""); 
			 
			i=i+1;
		 }
		 
		//System.out.println(DataMap.get("TestCaseID"));	
		//System.out.println(DataMap.get("sExpectedError"));
        workbook.close();
        inputStream.close();
        
        return  DataMap;
    }
		 
}
