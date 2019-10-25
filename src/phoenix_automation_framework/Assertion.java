package phoenix_automation_framework;

import java.util.ArrayList;
import java.util.List;

public class Assertion {
	
	public  enum RESULT {
	    PASS,
	    FAIL
	}
	public static synchronized  List<Object[][]>  initialisesoftassert(List<Object[][]> softassert) {
		softassert = new ArrayList<Object[][]>();
		return softassert;
	}
	
	public static synchronized  List<Object[][]> assertEquals(String actual, String expected, List<Object[][]> softassert, String ... optionalmessage) {
		 Object[][] obj = new Object[1][2];
		if (actual.equals(expected)) {	
			obj[0][0]=true;
			obj[0][1]="";
			softassert.add(obj);
		}
		else {
			obj[0][0]=false;
			if(optionalmessage.length>0) {
				
				obj[0][1]=	optionalmessage[0];
			}
			else {
				obj[0][1]="Expected "+expected+" but actual "+actual;
			}
			softassert.add(obj);
		}
		return softassert;
	}
	
	public static synchronized  List<Object[][]> assertNotEquals(String actual, String expected, List<Object[][]> softassert, String ... optionalmessage) {
		 Object[][] obj = new Object[1][2];
		if (!(actual.equals(expected))) {
			obj[0][0]=true;
			obj[0][1]="";
			softassert.add(obj);
		}
		else {

			obj[0][0]=false;
			if(optionalmessage.length>0) {
				obj[0][1]=	optionalmessage[0];
				
			}
			else {
				obj[0][1]="Expected "+expected+" but actual "+actual;
			}
			softassert.add(obj);
		}
		return softassert;
	}
	public static synchronized  List<Object[][]> assertEquals(int actual, int expected, List<Object[][]> softassert, String ... optionalmessage) {
		 Object[][] obj = new Object[1][2];
		if (actual==expected) {
			obj[0][0]=true;
			obj[0][1]="";
			softassert.add(obj);
		}
		else {
			obj[0][0]=false;
			if(optionalmessage.length>0) {
				obj[0][1]=	optionalmessage[0];
				
			}
			else {
				obj[0][1]="Expected "+expected+" but actual "+actual;
			}
			softassert.add(obj);
		}
		return softassert;
	}
	
	
	
	public synchronized static List<Object[][]> assertEquals(float actual, float expected, List<Object[][]> softassert, String ... optionalmessage) {
		Object[][] obj = new Object[1][2];
		if (actual==expected) {
			obj[0][0]=true;
			obj[0][1]="";
			softassert.add(obj);
		}
		else {
			obj[0][0]=false;
			if(optionalmessage.length>0) {
				
				obj[0][1]=	optionalmessage[0];
			}
			else {
				obj[0][1]="Expected "+expected+" but actual "+actual;
			}
			softassert.add(obj);
		}
		return softassert;
	}
	
	public synchronized static List<Object[][]> assertEquals(double actual, double expected, List<Object[][]> softassert, String ... optionalmessage) {
		Object[][] obj = new Object[1][2];
		actual=Math.ceil(actual);
		expected=Math.ceil(expected);
		if (actual==expected) {
			obj[0][0]=true;
			obj[0][1]="";
			softassert.add(obj);
		}
		else {
			obj[0][0]=false;
			if(optionalmessage.length>0) {
				
				obj[0][1]=	optionalmessage[0];
			}
			else {
				obj[0][1]="Expected "+expected+" but actual "+actual;
			}
			softassert.add(obj);
		}
		return softassert;
	}
	
	public synchronized static List<Object[][]> assertTrue(boolean isTrue ,List<Object[][]> softassert, String ... optionalmessage) {
		Object[][] obj = new Object[1][2];
		if (isTrue) {
			obj[0][0]=true;
			obj[0][1]="";
			softassert.add(obj);
		}
		else {
			obj[0][0]=false;
			if(optionalmessage.length>0) {
			obj[0][1]=optionalmessage[0];
			}
			softassert.add(obj);
		}
		return softassert;
	}
	
	public synchronized static List<Object[][]> assertFalse(boolean isTrue ,List<Object[][]> softassert, String ... optionalmessage) {
		Object[][] obj = new Object[1][2];
		if (!(isTrue)) {
			obj[0][0]=true;
			obj[0][1]="";
			softassert.add(obj);
		}
		else {
			obj[0][0]=false;
			if(optionalmessage.length>0) {
				obj[0][1]=optionalmessage[0];
				}
			softassert.add(obj);
		}
		return softassert;
	}
	
	
	public synchronized static List<Object[][]> assertNotNull(Object object ,List<Object[][]> softassert, String ... optionalmessage) {
		Object[][] obj = new Object[1][2];
		if (object!=null) {
			obj[0][0]=true;
			obj[0][1]="";
			softassert.add(obj);
		}
		else {
			obj[0][0]=false;
			if(optionalmessage.length>0) {
				obj[0][1]=optionalmessage[0];
				}
			softassert.add(obj);
		}
		return softassert;
	}
	
	public synchronized static List<Object[][]> assertNull(Object object ,List<Object[][]> softassert, String ... optionalmessage) {
		Object[][] obj = new Object[1][2];
		if (object==null) {
			obj[0][0]=true;
			obj[0][1]="";
			softassert.add(obj);
		}
		else {
			//return false;
			obj[0][0]=false;
			if(optionalmessage.length>0) {
				obj[0][1]=optionalmessage[0];
				}
			softassert.add(obj);
		}
		return softassert;
	}

	public synchronized static List<Object[][]> assertFail(List<Object[][]> softassert,  String ... optionalmessage){
		System.out.println(" Exception "+optionalmessage[0]);
		Object[][] obj = new Object[1][2];
		obj[0][0]=false;
		if(optionalmessage.length>0) {
			obj[0][1]=optionalmessage[0];
			}
		softassert.add(obj);
		return softassert;
	}
	public synchronized static List<Object[][]> assertAll(List<Object[][]> softassert, String modulename) {
		StringBuilder allerrors = new StringBuilder();
		boolean hasfailed=false;
		boolean checker;
		String errormessage="";
		for (Object[][] singlesoftassert : softassert) {
			checker= (boolean) singlesoftassert[0][0];
			errormessage=(String)singlesoftassert[0][1];
			if (!(checker)) {
                hasfailed=true;
                allerrors.append(errormessage).append("\t");
                errormessage="";
            }
        }
        if(!hasfailed) {
        ReportGenerator.writeToReport(modulename, "PASS" );
        }
        else {
        ReportGenerator.writeToReport(modulename, "FAIL", allerrors.toString());
        }
        allerrors.delete(0, allerrors.length());
        errormessage="";
        softassert= new ArrayList<Object[][]>();
		return softassert;
	}
	
	public synchronized static Assertion.RESULT assertall(RESULT result){
		if (result==RESULT.FAIL) {
			return RESULT.FAIL;
		}
		else {
			return RESULT.PASS;
		}
	}

	
}
