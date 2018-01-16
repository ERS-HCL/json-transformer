package com.hcl.ers.json.transformer.transformer;

import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

import com.jayway.jsonpath.JsonPath;

public class Transformer {

	JSONObject resultJson = new JSONObject();

	public JSONObject transform(JSONObject inputJson, JSONObject templateJson) {
		// System.out.println("transformer method iis called");
		JSONObject resultJson = new JSONObject();
		Iterator keys = templateJson.keys();
		while (keys.hasNext()) {
			String key = (String) keys.next();
			Object value = templateJson.opt(key);
			// System.out.println("key:value===>" + key + ":" + value);

			if (value instanceof JSONArray) {
				// System.out.println("Value is JSONArray");
				Object results = transformArray(inputJson, (JSONArray) value);
				updateValueInOutputObject(resultJson, key, results);
			}

			else if (value instanceof JSONObject) {
				// System.out.println("Value is JSONObject");
				Object results = transform(inputJson, (JSONObject) value);
				updateValueInOutputObject(resultJson, key, results);
			}

			else if (value instanceof String) {
				// System.out.println("Value is String");
				Object results = pullStringData(inputJson, value.toString());
				updateValueInOutputObject(resultJson, key, results);
			}

			else {
				// System.out.println("Not found : " + value.toString());
			}

			// System.out.println("\n\n\n");
		}

		return resultJson;
	}

	public Object transformArray(JSONObject inputJson, JSONArray templateJson) {

		JSONArray resultArray = new JSONArray();
		JSONObject innerObject;

		int dataArrayLength = getArrayLength(inputJson, (JSONObject) templateJson.opt(0));
		int arrayConfigLength = templateJson.length();
		// System.out.println("Source array config length : " +
		// arrayConfigLength);
		// System.out.println("Data array config length : " + dataArrayLength);

		for (int dataIndex = 0; dataIndex < dataArrayLength; dataIndex++) {
			// System.out.println("dataIndex : "+dataIndex);
			innerObject = new JSONObject();
			for (int sourceIndex = 1; sourceIndex < arrayConfigLength; sourceIndex++) {
				// System.out.println("sourceIndex : "+sourceIndex);
				Object value = templateJson.opt(sourceIndex);
				// System.out.println("value is :" + value.toString());
				String modifiedValue = getUpdatedIndex(templateJson, dataIndex, sourceIndex);
				if (value instanceof JSONObject) {
					// System.out.println("Array value is JSONObject");
					innerObject.accumulate("", transform(inputJson, new JSONObject(modifiedValue)));
				} else if (value instanceof String) {
					// System.out.println("Array value is String");
					innerObject.accumulate("", pullStringData(inputJson, modifiedValue));
				}
			}
			// System.out.println("Inner Object : "+innerObject);
			resultArray.put(dataIndex, innerObject.get(""));
			// System.out.println("\n\n\n");
		}

		return resultArray;
	}

	public int getArrayLength(JSONObject inputJson, JSONObject arrayConfig) {
		String arrayConfigString;

		arrayConfigString = arrayConfig.get("array").toString() + ".length()";
		// System.out.println("Array config string : " + arrayConfigString);
		return JsonPath.read(inputJson.toString(), arrayConfigString);

	}

	public String getUpdatedIndex(JSONArray arrayConfig, int arrayIndex, int outerIndex) {
		String arrayName;

		String arrayConfigString = ((JSONObject) arrayConfig.opt(0)).get("array").toString();

		// System.out.println("arrayConfigString : " + arrayConfigString);

		arrayName = arrayConfigString.substring(arrayConfigString.lastIndexOf(".") + 1, arrayConfigString.length());
		// System.out.println("Array name : " + arrayName);

		String replaceStr = arrayName + "\\[\\*\\]";
		String replacableStr = arrayName + "\\[" + arrayIndex + "\\]";

		// System.out.println("replaceStr : " + replaceStr);
		// System.out.println("replacableStr : " + replacableStr);

		String modifiedValue = arrayConfig.opt(outerIndex).toString().replaceAll(replaceStr, replacableStr);

		// System.out.println("Modified Value : " + modifiedValue);

		return modifiedValue;

	}

	private Object pullStringData(JSONObject inputJson, String configuration) {
		Object results = JsonPath.read(inputJson.toString(), configuration);
		// System.out.println("Result : " + results);
		return results;
	}

	private void updateValueInOutputObject(JSONObject resultJson, String key, Object results) {
		if (results == null) {
			// System.out.println("Result is empty");
			resultJson.put(key, "");
		} else {
			resultJson.put(key, results);
		}
		// System.out.println("ResultJson : " + resultJson.toString());
		// System.out.println("\n\n\n");
	}

}
