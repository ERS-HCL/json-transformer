package com.hcl.ers.json.transformer.examples;

import org.json.JSONObject;

import com.hcl.ers.json.transformer.utils.FileHanlder;
import com.hcl.ers.json.transformer.utils.JsonPathHandler;

public class JsonPathExample {
	
	public static void main(String args[])	{
		String inputPath = "D:/json-transformer/src/main/resources/input/example-json-path.json";
		String templatePath = "D:/json-transformer/src/main/resources/template/example-json-path.json";
		
		JSONObject inputJson = new JSONObject(FileHanlder.getJsonTextFromFile(inputPath));
		JSONObject templateJson = new JSONObject(FileHanlder.getJsonTextFromFile(templatePath));
		
		JsonPathHandler.validate(inputJson, templateJson);
	}
	
}
