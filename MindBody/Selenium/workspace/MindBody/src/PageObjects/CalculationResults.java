package PageObjects;

import java.util.List;
import java.util.HashMap;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;



public class CalculationResults {
	
	//Entry Time Text Box
	public static WebElement txtbox_sEntryTime(WebDriver driver){
		WebElement element = driver.findElement(By.id("EntryTime"));
		return element;
		}
	
	//Entry AMPM Text Box
	public static List <WebElement> EntryTimeAMPM(WebDriver driver){
		List <WebElement> element = driver.findElements(By.name("EntryTimeAMPM"));
		return element;
		}
	
	//Entry Date Text Box
	public static WebElement txtbox_sEntryDate(WebDriver driver){
		WebElement element = driver.findElement(By.id("EntryDate"));
		return element;
		}
	
	//Exit Time Text Box
	public static WebElement txtbox_sExitTime(WebDriver driver){
		WebElement element = driver.findElement(By.id("ExitTime"));
		return element;
		}
	
	//Exit AMPM Text Box
	public static List <WebElement> ExitTimeAMPM(WebDriver driver){
		List <WebElement> element = driver.findElements(By.name("ExitTimeAMPM"));
		return element;
		}
	
	//Exit Date Text Box
	public static WebElement txtbox_sExitDate(WebDriver driver){
		WebElement element = driver.findElement(By.id("ExitDate"));
		return element;
		}
	
	//Expected Result
	public static WebElement txt_sExpResult(WebDriver driver){
		WebElement element = driver.findElement(By.cssSelector("span.BodyCopy > font > b"));
		return element;
		}
	
	//Expected Cost
	public static WebElement txt_sExpCost(WebDriver driver){
		WebElement element = driver.findElement(By.cssSelector("b"));
		return element;
		}
	
	
	public static void Calculate (WebDriver driver, HashMap <String,String> DataEntry){
		//System.out.println(DataEntry.get("sChooseLot").toString());
	new Select (driver.findElement(By.id("Lot"))).selectByVisibleText(DataEntry.get("sChooseLot").toString());
	
	if (!DataEntry.get("sStartTime").toString().isEmpty())
	{
		txtbox_sEntryTime(driver).clear();
		txtbox_sEntryTime(driver).sendKeys(DataEntry.get("sStartTime").toString());
	}
	
    if (DataEntry.get("sStartAMPM").toString().equals("PM"))
    		{
    		//List <WebElement> EntryTimeAMPM = driver.findElements(By.name("EntryTimeAMPM"));
    		EntryTimeAMPM(driver).get(0).sendKeys(Keys.ARROW_RIGHT); //select PM
    		}	
    
    if (!DataEntry.get("sStartDate").toString().isEmpty())
    {
    	txtbox_sEntryDate(driver).clear();
    	txtbox_sEntryDate(driver).sendKeys(DataEntry.get("sStartDate").toString());
    }
    
    if (!DataEntry.get("sEndTime").toString().isEmpty())
    {
    	txtbox_sExitTime(driver).clear();
    	txtbox_sExitTime(driver).sendKeys(DataEntry.get("sEndTime").toString());
    }
    
    if (DataEntry.get("sEndAMPM").toString().equals("PM"))
	{
    	ExitTimeAMPM(driver).get(0).sendKeys(Keys.ARROW_RIGHT); //select PM
	}
    
    if (!DataEntry.get("sEndDate").toString().isEmpty())
    {
    	txtbox_sExitDate(driver).clear();
    	txtbox_sExitDate(driver).sendKeys(DataEntry.get("sEndDate").toString());
    }
    driver.findElement(By.name("Submit")).submit();
	}
	
	
	public static void Validate (WebDriver driver, HashMap <String,String> DataEntry){
		String Status = new String();
	    
		if (DataEntry.get("sExpectedError").toString().equals("TRUE"))
		{
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println("The Sleep did not work properly");
				e.printStackTrace();
			} //should change this to an explicit wait that waits for the object in a loop
			
			if (driver.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(DataEntry.get("sExpCost").toString()))
				Status = "Passed";
			else 
				Status = "Failed";
				
			System.out.println("TestCaseID #" + DataEntry.get("TestCaseID").toString() + " " + Status);
			System.out.println("Expected Error is: " + DataEntry.get("sExpCost").toString());
			System.out.println("Actual Error is: " + txt_sExpCost(driver).getText());	
		}
		else
		{
			String sTemp = new String(txt_sExpResult(driver).getText().trim());
			if (sTemp.contains(DataEntry.get("sExpResult").toString()))
					Status = "Passed";
			else
					Status = "Failed";
			System.out.println("TestCaseID #" + DataEntry.get("TestCaseID").toString() + " " + Status);
			System.out.println("Expected Result is: " + DataEntry.get("sExpResult").toString());
			System.out.println("Actual Result is: " + txt_sExpResult(driver).getText().trim());
			
		
			if (DataEntry.get("sExpCost").toString().equals(txt_sExpCost(driver).getText()))
					Status = "Passed";
			else
					Status = "Failed";
						
			System.out.println("TestCaseID #" + DataEntry.get("TestCaseID").toString() + " " + Status);
			System.out.println("Expected Cost is: " + DataEntry.get("sExpCost").toString());
			System.out.println("Actual Cost is: " + txt_sExpCost(driver).getText());		
		}
	}		
}
