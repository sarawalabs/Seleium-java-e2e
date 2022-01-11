// This class is to load all the properties and secrets required to run the tests on different browsers 

package com.ms.readFiles;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class readPropertiesFile {

	public static Properties props;

// -------------------- Read config.property file -------------------------------------------//

	public static String readsetupdata(String key) throws IOException {

		File src = new File("./configuration/setupdata.properties");

		FileInputStream fis = new FileInputStream(src);

		props = new Properties();

		props.load(fis);

		return props.get(key).toString();

	}

// -------------------- Read loginPageElements.property file -------------------------------------------//

	public static String readloginPageElements(String key) throws IOException {

		File src = new File("./configuration/WebPageElements.properties");

		FileInputStream fis = new FileInputStream(src);

		props = new Properties();

		props.load(fis);

		return props.get(key).toString();

	}

}
