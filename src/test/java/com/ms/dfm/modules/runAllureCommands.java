package com.ms.dfm.modules;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class runAllureCommands {
	
	public static void generateReport() throws IOException {
		
		ProcessBuilder builder = new ProcessBuilder("C:\\allure-2.13.10\\bin\\allure.bat", "serve", "C:\\DFM\\DFM\\allure-results");
		builder.redirectErrorStream(true);
		Process process = builder.start();
		InputStream is = process.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));

		String line = null;
		while ((line = reader.readLine()) != null) {
		   System.out.println("Otput of the executed command" +line);
		}

		ProcessBuilder reportGen = new ProcessBuilder("C:\\Users\\sarawa", "ctrl c");
		
	}


}
