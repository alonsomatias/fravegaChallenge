package com.fravega.pom.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.fravega.pom.driver.DriverManager;

public class SearchPage extends BasePage{

	private By categoriesListLocator = By.xpath("//ul[@name = 'categoriesAggregation']/li[not(@name)]//h4");
	private By brandListLocator = By.xpath("//li[@name='brandsFilter']//li//label");
	private By breadCrumbLocator = By.xpath("//div[@name='breadcrumb']//li/a[1]");
	private By itemsNamesLocators = By.xpath("//ul[@name='itemsGrid']//h4");
	private By totalResultLocator = By.xpath("//li[@name=\"totalResult\"]/span");
	private By filterSpanLocator = By.xpath("//span[@data-testid='tag-pill']");
	

	public boolean selectCategory(String categoyName) {
		List<WebElement> categoriesList = findElements(categoriesListLocator);
		int i = 0;
		boolean found = false;
		while(i < categoriesList.size() && found != true) {
			WebElement category = categoriesList.get(i);
			if (category.getText().contains(categoyName)) {
				category.click();
				DriverManager.wait.until(ExpectedConditions.presenceOfElementLocated(breadCrumbLocator));
				found = true;
			}else {
				i++;
			}
		}
		try {
			return found;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	
	public String selectBrand(int index) {
		List<WebElement> brandsList = findElements(brandListLocator);
		WebElement brand = brandsList.get(index-1);
		brand.click();
		DriverManager.wait.until(ExpectedConditions.presenceOfElementLocated(filterSpanLocator));
		return findElement(filterSpanLocator).getText();
	}
	
	public List<String> getBreadCrumbList() {
		List<WebElement> breadCrumbElementsList = findElements(breadCrumbLocator);
		List<String> breadCrumbList = new ArrayList<String>();
		for (WebElement breadCrumbElement : breadCrumbElementsList) {
			breadCrumbList.add(breadCrumbElement.getText());
		}
		return breadCrumbList;
	}
	
	public boolean isInBreadCrumb(String search) {
		List<String> breadCrumbList = getBreadCrumbList();
		if(breadCrumbList.contains(search)) {
			return true;
		}
		return false;
	}
	
	public boolean totalResultsIsEqualToGrid() {
		String totalResults = DriverManager.getDriver().findElement(totalResultLocator).getText();
		List<WebElement> itemsList = DriverManager.getDriver().findElements(itemsNamesLocators);
		if(totalResults.equals(Integer.toString(itemsList.size()))) {
			return true;
		}
		return false;
	}
	
	public boolean textIsInTheBreadcrumb(String text) {
		List<String> breadCrumbList = getBreadCrumbList();
		if(breadCrumbList.contains(text)) {
			return true;
		}
		return false;
	}
	
	public boolean itemInTheGridContainsText(String text) {
		List<WebElement> itemsList = findElements(itemsNamesLocators);
		for (WebElement item : itemsList) {
			String itemText = item.getText().toLowerCase();
			if(!itemText.contains(text)) {
				return false;
			}
		}
		return true;
	}
}
