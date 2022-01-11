package com.ms.dfm.utils.listeners;

import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;

public class Log4J {

	public static Logger log = Logger.getLogger(Log.class.getName());
	
	public static void StartTestcase(String TestCaseName) {
		
		log.info("Testcase " +TestCaseName+ " Started" );
		
	}
	
	public static void EndTestcase(String TestCaseName) {
		
		log.info("Testcase " +TestCaseName+ " Finished" );
		
	}
	
	public static void Debug(String message) {
		
		log.debug(message);
	}
}
