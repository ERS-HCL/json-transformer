package com.hcl.ers.json.transformer.examples;

import org.json.JSONObject;

import com.hcl.ers.json.transformer.transformer.Transformer;
import com.hcl.ers.json.transformer.utils.FileHanlder;


public class ExampleRunner {

	public static void main(String[] args) {
		
		testString();
		testObject();
		testArray();
		testArrayObject();
		testArrayNested();
		
	}
	
	private static void testString()	{
		System.out.println("+++++++++++++++++++++++++++++TESTING STRING +++++++++++++++++++++++++++++++++++");
		String inputPath = "D:/Bob/Arch/Projects/json-transformer/src/main/resources/input/example-json-path.json";
		String templatePath = "D:/Bob/Arch/Projects/json-transformer/src/main/resources/template/example-string.json";
		
		JSONObject inputJson = new JSONObject(FileHanlder.getJsonTextFromFile(inputPath));
		JSONObject templateJson = new JSONObject(FileHanlder.getJsonTextFromFile(templatePath));
		
		//JsonPathHandler.validate(templatePath, inputPath);
		JSONObject resultJson = new Transformer().transform(inputJson, templateJson);
		
		System.out.println("Result JSON:"+ resultJson.toString());
	}	
	
	private static void testObject()	{
		System.out.println("+++++++++++++++++++++++++++++TESTING OBJECT +++++++++++++++++++++++++++++++++++");
		String inputPath = "D:/Bob/Arch/Projects/json-transformer/src/main/resources/input/example-json-path.json";
		String templatePath = "D:/Bob/Arch/Projects/json-transformer/src/main/resources/template/example-object.json";
		
		JSONObject inputJson = new JSONObject(FileHanlder.getJsonTextFromFile(inputPath));
		JSONObject templateJson = new JSONObject(FileHanlder.getJsonTextFromFile(templatePath));
		
		//JsonPathHandler.validate(templatePath, inputPath);
		JSONObject resultJson = new Transformer().transform(inputJson, templateJson);
		
		System.out.println("Result JSON:"+ resultJson.toString());
	}
	
	private static void testArray()	{
		System.out.println("+++++++++++++++++++++++++++++TESTING ARRAY +++++++++++++++++++++++++++++++++++");
		String inputPath = "D:/Bob/Arch/Projects/json-transformer/src/main/resources/input/example-json-path.json";
		String templatePath = "D:/Bob/Arch/Projects/json-transformer/src/main/resources/template/example-array.json";
		
		JSONObject inputJson = new JSONObject(FileHanlder.getJsonTextFromFile(inputPath));
		JSONObject templateJson = new JSONObject(FileHanlder.getJsonTextFromFile(templatePath));
		
		//JsonPathHandler.validate(templatePath, inputPath);
		JSONObject resultJson = new Transformer().transform(inputJson, templateJson);
		
		System.out.println("Result JSON:"+ resultJson.toString());
	}

	
	private static void testArrayObject()	{
		System.out.println("+++++++++++++++++++++++++++++TESTING ARRAY OBJECT+++++++++++++++++++++++++++++++++++");
		String inputPath = "D:/Bob/Arch/Projects/json-transformer/src/main/resources/input/example-json-path.json";
		String templatePath = "D:/Bob/Arch/Projects/json-transformer/src/main/resources/template/example-array-object.json";
		
		JSONObject inputJson = new JSONObject(FileHanlder.getJsonTextFromFile(inputPath));
		JSONObject templateJson = new JSONObject(FileHanlder.getJsonTextFromFile(templatePath));
		
		//JsonPathHandler.validate(templatePath, inputPath);
		JSONObject resultJson = new Transformer().transform(inputJson, templateJson);
		
		System.out.println("Result JSON:"+ resultJson.toString());
	}
	
	private static void testArrayNested()	{
		System.out.println("+++++++++++++++++++++++++++++TESTING ARRAY NESTED +++++++++++++++++++++++++++++++++++");
		String inputPath = "D:/Bob/Arch/Projects/json-transformer/src/main/resources/input/example-json-nested-array.json";
		String templatePath = "D:/Bob/Arch/Projects/json-transformer/src/main/resources/template/example-array-nested.json";
		
		JSONObject inputJson = new JSONObject(FileHanlder.getJsonTextFromFile(inputPath));
		JSONObject templateJson = new JSONObject(FileHanlder.getJsonTextFromFile(templatePath));
		
		//JsonPathHandler.validate(templatePath, inputPath);
		JSONObject resultJson = new Transformer().transform(inputJson, templateJson);
		
		System.out.println("Result JSON:"+ resultJson.toString());
	}
}
