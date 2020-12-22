package com.fravega.pom.driver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.*;


public class BackManager {
	
	private BackManager() {
		
	}
	
	public static Map<String, String> getBreweriesInStateById(String state, List<String> idList) {
		
		Map<String, String> brewMap = new HashMap<String, String>(); 
		int i = 0;
		while (i< idList.size() && brewMap.isEmpty()) {
			Response response = RestAssured.given()
					.get("https://api.openbrewerydb.org/breweries/"+idList.get(i)).andReturn();
			JsonPath jsonPath = response.jsonPath();
			if(jsonPath.getString("state").equals(state)) {
				brewMap.put("id", jsonPath.getString("id"));
				brewMap.put("name", jsonPath.getString("name"));
				brewMap.put("street", jsonPath.getString("street"));
				brewMap.put("phone", jsonPath.getString("phone"));
			}else {
				i++;
			}
		}
		return brewMap;
	}
	
	public static List<String> getBreweriesIdsByName(String nameSearch) {
		String query = nameSearch;
	     if(query.contains(" ")){
	    	query= query.substring(0, query.indexOf(" ")); 
	     }
		
		Response response = RestAssured.given()
				.queryParam("query", query.toLowerCase())
				.get("https://api.openbrewerydb.org/breweries/autocomplete").andReturn();
		JsonPath jsonPath = response.jsonPath();
		int jsonSize = jsonPath.getList("$").size();
		List<String> idList = new ArrayList<String>();
		for (int i=0; i<jsonSize; i++) {
			String name = jsonPath.getString("name["+i+"]");
			if(name.equals(nameSearch)) {
				idList.add(jsonPath.getString("id["+i+"]"));
			}
		}
		return idList;
	}
}
