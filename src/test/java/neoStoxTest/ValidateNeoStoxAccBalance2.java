package neoStoxTest;

import java.io.IOException;


import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import neoStoxPom.NeoStoxHomePage2;
import neoStoxPom.NeoStoxLoginPage;
import neoStoxPom.NeoStoxPasswordPage;
import neoStoxPom.NeoStoxSignInPage;
import neoStoxUtility.UtilityNew;
import neostoxBase.BaseNew;

@Listeners(neoStoxUtility.Listener1.class)
public class ValidateNeoStoxAccBalance2 extends BaseNew
{
	NeoStoxLoginPage login;
	NeoStoxPasswordPage password;
	NeoStoxHomePage2 home;
	NeoStoxSignInPage signIn;

	
	@BeforeClass
	public void launchNeoStox() throws InterruptedException, IOException
	{
	launchBrowser();//form base class
	
	login= new NeoStoxLoginPage(driver);
	password= new NeoStoxPasswordPage(driver);
	home= new NeoStoxHomePage2(driver);
	signIn= new NeoStoxSignInPage(driver);
	}
	
	@BeforeMethod
	public void logintoNeoStox() throws EncryptedDocumentException, IOException, InterruptedException
	{
		signIn.clickOnSignInButton(driver);
		Thread.sleep(1000);
	
		login.sendMobileNum(driver,UtilityNew.readDataFrompropertyFile("mobileno"));
	login.clickOnSignInButton(driver);
	UtilityNew.wait(driver, 1000);
	password.enterPassword(driver,UtilityNew.readDataFrompropertyFile("password"));

	Thread.sleep(1000);
	password.clickOnSubmitButton(driver);
	Thread.sleep(1000);
	home.handlePopUp(driver);
	}
	
	
	@Test
	public void validateAccBalance() throws EncryptedDocumentException, IOException
	{
	Assert.assertNotNull(home.getAccBalance(driver),"TC failed Unable to fetch account Balance");
	UtilityNew.screenshot(driver, "ACCBalance");
	//Assert.fail();
	}
	
	@Test(priority=-1)
	 public void validateUserID() throws EncryptedDocumentException, IOException
	{
	Assert.assertEquals(home.getActualUserName(driver), 
	UtilityNew.readDataFrompropertyFile("username"),"TC is failed Actual and expected User Name are not matching");
	UtilityNew.screenshot(driver, home.getActualUserName(driver));
	}

	
	@AfterMethod
	public void logOutFromNeoStox()
	{
	home.logOut(driver);
	}
	
	@AfterClass
	public void closeBrowser()
	{
	Reporter.log("closing browser", true);
	driver.close();
	}
	}
 
  
  

