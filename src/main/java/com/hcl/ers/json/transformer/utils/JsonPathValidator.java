package com.hcl.ers.json.transformer.utils;

import java.util.Iterator;
import org.json.JSONObject;
import com.jayway.jsonpath.JsonPath;

public class JsonPathValidator {
	
	
	public static void main(String args[])	{
		String inputPath = "D:/example-json-path.json";
		String templatePath = "D:/template-json-path.json";
		
		JSONObject inputJson = new JSONObject(FileHanlder.getJsonTextFromFile(inputPath));
		JSONObject templateJson = new JSONObject(FileHanlder.getJsonTextFromFile(templatePath));
		
		JsonPathHandler.validate(inputJson, templateJson);
	}

	public static void validate(JSONObject inputJson, JSONObject templateJson)	{
		
		Iterator keys = templateJson.keys();
		// System.out.println("keys: " + keys);
		while (keys.hasNext()) {
			String key = (String) keys.next();
			Object value = templateJson.opt(key);
			// System.out.println("value: " + value);
			
			Object results = JsonPath.read(inputJson.toString(), value.toString());
			// System.out.println("Results: "+results);
		}
	}
}
