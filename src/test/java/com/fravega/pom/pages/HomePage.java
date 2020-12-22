package com.fravega.pom.pages;

import org.openqa.selenium.By;

public class HomePage extends BasePage{

	private String url = "http://www.fravega.com";
	
	private By headerLocator  = By.xpath("//header[@data-test-id='header']");
	private By searchBoxLocator = By.xpath("//input[@placeholder='Buscar productos']");
	private By bannerLocator = By.xpath("//div[contains(@class,'home')]");
	
	public void visit() {
		visit(url);
		waitElementIsDisplayed(headerLocator);
	}
	
	public void searchItem(String search) {
		type(search, searchBoxLocator);
		submit(searchBoxLocator);
		waitElementNotDisplayed(bannerLocator);
	}
	

}
