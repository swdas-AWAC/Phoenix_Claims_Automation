package phoenix_automation_framework;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

public class ReportGenerator extends BaseTest implements IInvokedMethodListener {
	
	private static Row row ;
	private static Cell cell;
	String message="";
	
    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult result) {
    	message="";
    	if (method.isTestMethod() && ITestResult.FAILURE == result.getStatus()) {
            Throwable throwable = result.getThrowable();
             message = throwable.getMessage();
            String modulename = result.getMethod().getMethodName();
            System.out.println("Report Generator "+ result.getInstanceName());
            try {
            	writeToReport( modulename,  "Failed", message);
            	System.out.println("Module name "+modulename);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        if (method.isTestMethod() && ITestResult.SUCCESS == result.getStatus()) {
        	message="";
        	Throwable throwable = result.getThrowable();
            String modulename = result.getMethod().getMethodName();
            System.out.println("Report Generator " +result.getInstanceName());
            try {
            	writeToReport( modulename,  "Passed", message);
            	System.out.println("Module name "+modulename);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public synchronized static void writeToReport(String modulename,  String result, String... optionalmessage) {
    	row = result_sheet.createRow(++rowCount);
    	cell = row.createCell(0);
    	cell.setCellValue(modulename);
    	cell = row.createCell(1);
    	cell.setCellValue(result);
    	cell = row.createCell(2);
    	if(optionalmessage.length>0) {
    	cell.setCellValue(optionalmessage[0].trim());
    	}
    }
}
