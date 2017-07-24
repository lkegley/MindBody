package Webdriver_class;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.*;
import PageObjects.CalculationResults;
import Utilities.ExcelReader;

public class Webdriver_class {

	public static void main(String[] args) {
		//Read in the Test Case # you want to execute
		Scanner in = new Scanner(System.in); 
	    System.out.printf("Enter i Value:  ");
	    int i = in.nextInt();
	    in.close();

	    System.out.print(i);	
	    if ((i > 0) && (i < 9))
	    {	
	    
	    	//Prepare Browser
			System.setProperty("webdriver.edge.driver", "..\\..\\drivers\\MicrosoftWebDriver.exe");
			WebDriver driver = new EdgeDriver();
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
			String baseUrl = "http://adam.goucher.ca"; 
			driver.get(baseUrl + "/parkcalc/index.php");																// should
																			
			 try {
					//Put the row of data into a hashmap with the excel headers
				 	HashMap<String, String> DataInput = ExcelReader.ReadRow(i);
					
				 	//Call a method to fill everything out and do the calculations
				 	CalculationResults.Calculate(driver, DataInput);
					
				 	//Call a validator method to validate the results
				 	CalculationResults.Validate(driver, DataInput);
				} catch (IOException e)  {
					System.out.println("There was a problem reading from the data file");
					e.printStackTrace();
				}
			 
			 driver.close();
	    }
	    else //spit out error for bad data //TODO:  this really should be in a loop
	    	System.out.println("Test Case ID must be between 1-8"); 
	}	
}
