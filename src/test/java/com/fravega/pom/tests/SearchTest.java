package com.fravega.pom.tests;

import com.fravega.pom.pages.*;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
	
	@Test
	public class SearchTest extends TestBase{
		
		HomePage homePage = new HomePage();
		SearchPage searchPage = new SearchPage();
		
		public void seartchRefrigerator() {
			String item = "Heladera";
			String category = "Heladeras";
			
			homePage.visit();
			homePage.searchItem(item);			
			searchPage.selectCategory(category);
			String brandName = searchPage.selectBrand(1);
			
			assertTrue(searchPage.totalResultsIsEqualToGrid(), "La cantidad de objetos en la grilla difiera de la cantidad total");
			assertTrue(searchPage.textIsInTheBreadcrumb(category), category + " no se encuentra en el breadcrumb");
			assertTrue(searchPage.itemInTheGridContainsText(brandName), "Uno de los elementos de la grilla no contiene el texto buscado");
		}
	}
