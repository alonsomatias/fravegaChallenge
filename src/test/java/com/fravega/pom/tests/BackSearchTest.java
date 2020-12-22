package com.fravega.pom.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import java.util.List;
import java.util.Map;


import com.fravega.pom.driver.BackManager;


public class BackSearchTest {
	
  @Test
  public void APISearchTest() {
	  String state = "California";
	  String id = "761";
	  String name = "Lagunitas Brewing Co";
	  String street = "1280 N McDowell Blvd";
	  String phone = "7077694495";
	  
	  List<String> idList = BackManager.getBreweriesIdsByName(name);
	  Map<String, String> brewMap = BackManager.getBreweriesInStateById(state, idList);
	  
	  AssertJUnit.assertTrue(brewMap.get("id").equals(id));
	  AssertJUnit.assertTrue(brewMap.get("name").equals(name));
	  AssertJUnit.assertTrue(brewMap.get("street").equals(street));
	  AssertJUnit.assertTrue(brewMap.get("phone").equals(phone));
  }
}
