package com.visionit.automation.stepdefs;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.visionit.automation.core.WebDriverFactory;
import com.visionit.automation.pageObjects.CmnPageObjects;
import com.visionit.automation.pageObjects.HomePageObjects;
import com.visionit.automation.pageObjects.ProductDescPageObjects;
import com.visionit.automation.pageObjects.SearchPageObjects;
import com.visionit.automation.pageObjects.SignInPageObjects;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

public class StepDefs {
	
	private static final Logger logger = LogManager.getLogger(StepDefs.class); 
	
	WebDriver driver;
	String base_url = "https://amazon.in";
	 int implicit_wait_timeout_in_sec = 20;
	 Scenario scn;
	 
	 CmnPageObjects cmnPageObjects;
	 HomePageObjects homePageObjects;
	 ProductDescPageObjects productDescPageObjects;
	 SearchPageObjects searchPageObjects;
	 SignInPageObjects signInPageObjects;
	 
	 @Before
	 public void setUp( Scenario scn) throws Exception {
		 this.scn = scn;
		 
		 String browserName = WebDriverFactory.getBrowserName();
		 driver = WebDriverFactory.getWebDriverForBrowser(browserName);
		logger.info("browser invoked");
		
		cmnPageObjects = new CmnPageObjects(driver);
		homePageObjects = new HomePageObjects(driver);
		productDescPageObjects = new ProductDescPageObjects(driver);
		searchPageObjects = new SearchPageObjects(driver);
		signInPageObjects = new SignInPageObjects(driver);
	 }  
	 
	 
   @After(order=1)
	public void cleanUp() {
		WebDriverFactory.quitDriver();
		scn.log("closed browser");
	}
   
   @After(order=2)
   public void takeScreenShot(Scenario s) {
	   if(s.isFailed()) {
		   TakesScreenshot scrnShot = (TakesScreenshot)driver;
		   byte[] data = scrnShot.getScreenshotAs(OutputType.BYTES);
		   scn.attach(data, "image/png", "failed step name: " +s.getName());
	   }
	   else {
		   scn.log("Test case is passed, no screen shot captured");
	   }
   }
	 
	@Given("User navigated to the home appilation url")
	public void user_navigated_to_the_home_appilation_url() {
		driver.get(base_url);
		scn.log("navigated to the URl: " + base_url);
		
		String expected = "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";
		cmnPageObjects.validatePageTitle(expected);
	   
	}
	@When("User search for product {string}")
	public void user_search_for_product(String productName) {
		cmnPageObjects.SetSearchBox(productName); 
	    cmnPageObjects.clickOnSearchButton();
	    scn.log("Product Searched: " + productName);
	}
	@Then("Search result is displayed")
	public void search_result_is_displayed() {
		searchPageObjects.validateProductSearchIsSuccessful();
	}

	@When("User click on any product")
	public void user_click_on_anyn_product() {
	searchPageObjects.ClickOnTheProductLink(0);
	}


	@Then("Product Description is displayed in new tab")
	public void product_description_is_displayed_in_new_tab() {
		WebDriverFactory.switchBrowserToTab();
		 scn.log("Switched to the new window/tab");
		 
		 productDescPageObjects.ValidateproductDescpIsDisplayed();
		 productDescPageObjects.ValidateAddToCartButtonIsDisplayed();
		 
		
	}
	

}
