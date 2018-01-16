package com.hcl.ers.json.transformer.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileHanlder {
	
	public static String getJsonTextFromFile(String filepath) {

		String text = null;

		try {

			File file = new File(filepath);
			byte[] bytes = Files.readAllBytes(file.toPath());

			text = new String(bytes, "UTF-8");

		} catch (IOException e) {
			e.printStackTrace();
		}

		return text;
	}

}
