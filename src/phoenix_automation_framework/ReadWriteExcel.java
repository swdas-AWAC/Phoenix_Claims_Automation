package phoenix_automation_framework;

import java.io.File;
import java.io.FileNotFoundException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.commons.collections4.map.ListOrderedMap;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


public class ReadWriteExcel {
	
	public  ListOrderedMap<String, ListOrderedMap<String,String>> exceldata = new ListOrderedMap<String, ListOrderedMap<String,String>>();
	private static Workbook workbook;
	private static Sheet sheet;
	private static Cell cellheader,cellattr, cellkey,cellexecute;
	private static DataFormatter objDefaultFormat;
	private static  FormulaEvaluator objFormulaEvaluator;
	public  void getexcel(String workb, String sh)
	{
		try {
			System.out.println("Workbook "+ workb);
			workbook = WorkbookFactory.create(new File(workb));
			sheet=workbook.getSheet(sh);
			 objDefaultFormat = new DataFormatter();
			 objFormulaEvaluator=  workbook.getCreationHelper().createFormulaEvaluator();
			
			readexcel();
			workbook.close();
		} catch (FileNotFoundException  e) {
			// TODO Auto-generated catch block
			System.out.println("Please check the sheet or close the excel file and try again");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public  void  readexcel()
	{
		int rows = sheet.getLastRowNum();
		Row rowzero = sheet.getRow(0);
		int cols = rowzero.getLastCellNum();
		for (int j=1;j<=rows;j++)
		{
			ListOrderedMap<String,String> data = new ListOrderedMap<String,String>();
			cellkey=sheet.getRow(j).getCell(0);
			objFormulaEvaluator.evaluate(cellkey);
			String key = (objDefaultFormat.formatCellValue(cellkey,objFormulaEvaluator)).trim();
			cellexecute=sheet.getRow(j).getCell(1);
			objFormulaEvaluator.evaluate(cellexecute);
			String executeyn = (objDefaultFormat.formatCellValue(cellexecute,objFormulaEvaluator)).trim();
			//System.out.println(key);
			for (int i=1;i<cols;i++)
			{
				cellheader= rowzero.getCell(i);
				objFormulaEvaluator.evaluate(cellheader);
				String cellheadervalue= (objDefaultFormat.formatCellValue(cellheader,objFormulaEvaluator)).trim();
				cellattr= sheet.getRow(j).getCell(i);
				objFormulaEvaluator.evaluate(cellattr); 
				String cellattrvalue = (objDefaultFormat.formatCellValue(cellattr,objFormulaEvaluator)).trim();
				data.put(cellheadervalue, cellattrvalue);
			}
			if((!(key.trim()=="")) && executeyn.equalsIgnoreCase("Yes"))
			{
			exceldata.put(key,data);
			System.out.println("Key "+key);
			}
			}
		
		}
}
