package neoStoxUtility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.testng.Reporter;


public class UtilityNew 
{
	public static String readDataFrompropertyFile(String key) throws IOException
	{
	//create object of Properties class--> java
	Properties prop= new Properties();
	
	//create object of FileInputStream and pass properties file path as a parameter
	FileInputStream myFile= new FileInputStream("C:\\Users\\prash\\eclipse-workspace\\Selenium_proj\\NeoStox.properties");
	
	prop.load(myFile);
	String value = prop.getProperty(key);
	Reporter.log("Reading " +key+ " from property file", true);
	return value;
	}
	
	public static void screenshot(WebDriver driver, String screenShotName) throws IOException
	{
	wait(driver, 500);
	File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	File dest= new File("D:\\screenshot"+screenShotName+".png");
	FileHandler.copy(src, dest);
	Reporter.log("taking screenshot", true);
	}
	public static void scrollIntoView(WebDriver driver, WebElement element)
	{
	wait(driver, 500);
	JavascriptExecutor js = (JavascriptExecutor)driver;
	js.executeScript("arguments[0].scrollIntoView(true)", element);
	Reporter.log("SCrolling into view",true);
	}
	public static void wait(WebDriver driver, int waitTime)
	{
	driver.manage().timeouts().implicitlyWait(Duration.ofMillis(waitTime));
	Reporter.log("Waiting for "+waitTime+"ms",true);
	}


}
