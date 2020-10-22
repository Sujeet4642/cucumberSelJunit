package com.visionit.automation.pageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.junit.Assert;

public class CmnPageObjects {

	private static final Logger logger = LogManager.getLogger(CmnPageObjects.class);
	
	private WebDriver driver;
	
	private By SearchBox = By.id("twotabsearchtextbox");
	private By SearchButton =  By.xpath("//input[@value='Go']");
	private By HamburgerMenu = By.id("nav-hamburger-menu");
	private By AmazonLogo = By.id("nav-logo");
	private By Cart = By.id("nav-cart");
	private By TryPrime = By.id("nav-link-prime");
	private By ReturnAndOrders = By.id("nav-orders");
	private By AccountAndLists = By.id("nav-link-accountList");
	private String HamburgerMenuCategoryXpath = ("//div[@id='hmenu-content']//div[text()='%s']");
	private String HamburgerMenuSubCategoryXpath = ("//div[@id='hmenu-content']//a[text()='%s']");
	
	public CmnPageObjects(WebDriver driver) {
		this.driver = driver;
	}
	
	public void SetSearchBox(String text) {
		WebDriverWait wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.elementToBeClickable(SearchBox));
		driver.findElement(SearchBox).sendKeys(text);
		logger.info("Value entered in searchbox" + text);
	}
	
	public void clickOnSearchButton() {
		driver.findElement(SearchButton).click();
		logger.info("Clicked on Search Button");
	}
	
	public void clickOnHamburgerMenu() {
		driver.findElement(HamburgerMenu).click();
		logger.info("clicked on hamburger menu");
	}
	
	public void clickOnHamburgerMenuCategory(String linkText) {
		By HamMenu = By.xpath(String.format(HamburgerMenuCategoryXpath, linkText));
		driver.findElement(HamMenu).click();
		logger.info("Clicked on Hamburger Menu Category: " + linkText);
	}
	
	public void clickOnHamburgerMenuSubCategory(String linkText) {
		By HamMenuSub = By.xpath(String.format(HamburgerMenuSubCategoryXpath, linkText));
		driver.findElement(HamMenuSub).click();
		logger.info("Clicked on Hamburger Menu SubCategory: " + linkText);
	}
	
	public void validateHamburgerMenu() {
		boolean b = driver.findElement(HamburgerMenu).isDisplayed();
		Assert.assertEquals("Hamburger Menu", true, b);
	}
	
	public void validateAmazonLogo() {
		boolean b = driver.findElement(AmazonLogo).isDisplayed();
		Assert.assertEquals("Amazon Logo", true, b);
	}
	
	public void validatePageTitle(String expectedTitle) {
		WebDriverWait wait = new WebDriverWait(driver,20);
		boolean b = wait.until(ExpectedConditions.titleContains(expectedTitle));
		Assert.assertEquals("Title validation", true, b);
		logger.info("Page title matched: " + expectedTitle );
	}
	
	public void validateElementPresentInHeaderSection(String text) throws Exception{
		boolean b = false;
		
		switch(text.toLowerCase().trim()) {
		
		case "account and list":
			b = driver.findElement(AccountAndLists).isDisplayed();
			break;
		case "return and orders":
			b=driver.findElement(ReturnAndOrders).isDisplayed();
			break;
		case "prime":
			b=driver.findElement(TryPrime).isDisplayed();
			break;
		case "cart":
			b=driver.findElement(Cart).isDisplayed();
			break;
		case "text box":
			b=driver.findElement(SearchBox).isDisplayed();
			break;
			default:
				logger.fatal("Header Link Description is not present in the case. Please add link description first.");
				throw new Exception("Header Link Description is not present in the case. Please add link description first.");
		}
		if (b) {
			Assert.assertEquals("Header Link displayed", true, b);
			logger.info("Header Link is displayed: " + text);
		}
		else {
			Assert.fail("Header Link is not displayed: " + text);
			logger.fatal("Header Link is not displayed: " + text);
		}
	}
}
