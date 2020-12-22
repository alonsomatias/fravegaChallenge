package com.fravega.pom.pages;

import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import com.fravega.pom.driver.DriverManager;

public abstract class BasePage {
		
	public WebElement findElement(By locator) {
		return DriverManager.getDriver().findElement(locator);
	}
	
	public List<WebElement> findElements(By locator) {
		return DriverManager.getDriver().findElements(locator);
	}
	
	public String getText(WebElement element) {
		return element.getText();
	}
	
	public String getText(By locator) {
		return DriverManager.getDriver().findElement(locator).getText();
	}
	
	public void type(String inputText, By locator) {
		DriverManager.getDriver().findElement(locator).sendKeys(inputText);
	}
	
	public void type(String inputText, WebElement element) {
		element.sendKeys(inputText);
	}
	
	public void submit(By locator) {
		DriverManager.getDriver().findElement(locator).submit();
	}
	
	public void submit(WebElement element) {
		element.submit();
	}
	
	public void click(By locator) {
		DriverManager.getDriver().findElement(locator).click();
	}
	
	public void click(WebElement element) {
		element.click();
	}
	
	public void visit(String url) {
		DriverManager.getDriver().get(url);
	}
	
	public boolean isDisplayed(By locator) {
		try {
			return DriverManager.getDriver().findElement(locator).isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	
	public void waitElementIsDisplayed (By locator) {
		DriverManager.wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	public void waitElementNotDisplayed (By locator) {
		DriverManager.wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}
}
