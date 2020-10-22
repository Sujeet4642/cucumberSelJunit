package com.visionit.automation.pageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.junit.Assert;

public class ProductDescPageObjects {

	private static final Logger logger = LogManager.getLogger(ProductDescPageObjects.class); 
	
	private WebDriver driver;
	
	private By productTitle = By.id("productTitle");
	private By addToCart = By.id("add-to-cart-button");
	
	public ProductDescPageObjects(WebDriver driver) {
		this.driver = driver;
	}
	
	public void ValidateproductDescpIsDisplayed() {
		if(driver.findElement(productTitle).isDisplayed()) {
			Assert.assertTrue(true);
			logger.info("Product Title is displayed");
		}
		else {
			Assert.fail();
			 logger.fatal("Product Title is not displayed");
		}
	}
	
	public void ValidateAddToCartButtonIsDisplayed() {
		boolean b = driver.findElement(addToCart).isDisplayed();
		Assert.assertEquals("Add to cart", true, b);
		 logger.info("Add to Cart Button is displayed");
	}
	
}
