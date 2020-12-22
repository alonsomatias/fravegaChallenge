package com.fravega.pom.tests;


import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import com.fravega.pom.driver.DriverManager;


public class TestBase {



	@BeforeTest
  public void setUp() {
	  DriverManager.getDriver().manage().window().maximize();
	}

  @AfterTest
	public void finish() {
	  DriverManager.close();
	}

}
