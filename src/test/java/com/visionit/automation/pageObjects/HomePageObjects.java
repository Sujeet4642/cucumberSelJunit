package com.visionit.automation.pageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePageObjects {
	
	private static final Logger logger = LogManager.getLogger(HomePageObjects.class);

	private WebDriver driver;
	
	private By SignInSecurely = By.linkText("Sign in securely");
	
	public HomePageObjects(WebDriver driver) {
		 this.driver = driver;
	}
	
	public void click_signIn_securely() {
		driver.findElement(SignInSecurely).click();
		logger.info("clicked on sign in securely");
	}
}
