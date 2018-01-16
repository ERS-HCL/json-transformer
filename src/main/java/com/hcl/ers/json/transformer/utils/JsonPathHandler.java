package com.hcl.ers.json.transformer.utils;

import java.util.Collection;
import java.util.Iterator;

import org.json.JSONObject;

import com.jayway.jsonpath.JsonPath;

public class JsonPathHandler {

	public static void validate(JSONObject inputJson, JSONObject templateJson) {

		Iterator keys = templateJson.keys();
		// System.out.println("keys: " + keys);
		while (keys.hasNext()) {
			String key = (String) keys.next();
			System.out.println("key: " + key);
			Object value = templateJson.opt(key);
			System.out.println("value: " + value);

			Object results = JsonPath.read(inputJson.toString(), value.toString());
			if (results instanceof String) {
				System.out.println("Results: " + results);
			} else if (results instanceof Collection) {
				System.out.println("List Results :" + results.toString());
			} else if (results instanceof Integer) {
				System.out.println("Results: " + results);
			}
		}
	}

	public static JSONObject getValue(JSONObject inputJson, String configuration) {
		JSONObject resultJSON = null;

		return resultJSON;
	}

	public static int getArrayLength(JSONObject inputJson, String configValue) {
		int arrayLen = 0;

		// System.out.println("Finding array length for : "+configValue);

		int endIndex = configValue.indexOf("[*]");
		int startIndex = configValue.indexOf("$", -endIndex);

		// System.out.println("Start index : "+startIndex+", End index :
		// "+endIndex);

		String arrayLenConfig = configValue.substring(startIndex, endIndex) + ".length()";
		// System.out.println("Array length configuration : "+arrayLenConfig);

		arrayLen = JsonPath.read(inputJson.toString(), arrayLenConfig);
		// System.out.println("Array length : "+arrayLen);

		return arrayLen;
	}

}
