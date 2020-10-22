package com.visionit.automation.pageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.junit.Assert;

public class SignInPageObjects {
	
	private static final Logger logger = LogManager.getLogger(SignInPageObjects.class);

	private WebDriver driver;
	
	private By LogInButton = By.id("ap_email");
	
	public SignInPageObjects(WebDriver driver) {
		this.driver = driver;
	}
	
	public void validateLogInBoxIdDisplayed() {
		Boolean b = driver.findElement(LogInButton).isDisplayed();
		Assert.assertEquals("Validate Login", true, b);
		logger.info("Email input box is displayed");
	}
	
	public void EnterEmailInLogInBox(String text) {
		driver.findElement(LogInButton).sendKeys(text);
		logger.info("Text entered in email id: " + text);
	}
}

