package GWPCDataDriven;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataLoginGWPC {

	public ArrayList<String> getData(String testcaseName) throws IOException
	{
		//fileInputStream argument
		
				ArrayList <String> a = new ArrayList<String>();		
				
				//Create an object of File class to open xlsx file
				
				File file = new File ("C:\\GW Automation Scripts\\GWPCDataDriven\\GWPC_Data1.xlsx");
				
				//Create an object of FileInputStream class to read excel file
				
				FileInputStream fis = new FileInputStream(file);
				
				//Creating workbook instance that refers to .xls file
				
				@SuppressWarnings("resource")
				XSSFWorkbook workbook = new  XSSFWorkbook(fis);
				
				//Creating a Sheet object using the sheet Name
				
				int sheets = workbook.getNumberOfSheets();
				for (int i=0; i<sheets; i++)
				{
					if(workbook.getSheetName(i).equalsIgnoreCase("TestData"))
							{
					XSSFSheet sheet = workbook.getSheetAt(i);
					
					// Identify Testcases column by	scaning the entire 1st row
					
					Iterator <Row> rows = sheet.iterator(); // sheet is collection of row
					Row firstRow = rows.next();
					Iterator <Cell> ce = firstRow.cellIterator(); // row is  collection of cells
					
					int k=0;
					int column = 0;
					
					while(ce.hasNext())
					{
						Cell value = ce.next();
						if(value.getStringCellValue().equalsIgnoreCase("GWPC"))
					{
						column=k;
					}
						k++;
					}
					
					System.out.println(column);
					
					//Create a row/cell object to retrieve row
					
					while (rows.hasNext())
					{
						Row r = rows.next();
						if (r.getCell(column).getStringCellValue().equalsIgnoreCase(testcaseName))
						{	
							Iterator <Cell> cv = r.cellIterator();
							while (cv.hasNext())
							{
								Cell c = cv.next();
								if (c.getCellType()==CellType.STRING)
								{
									a.add(c.getStringCellValue());
								}
								else {
									
									a.add(NumberToTextConverter.toText(c.getNumericCellValue()));
									
								}
									
							}	
						}
					}  
					
							}
				}
				
				return a;		
	} 
	
	public static void main(String[] args) throws IOException {
				
	}

}